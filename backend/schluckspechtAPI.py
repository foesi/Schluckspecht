# import sql magic
from sqlalchemy import Column, ForeignKey, Integer, String, Boolean, DateTime, Float, func
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import relationship, sessionmaker
from sqlalchemy import create_engine

# import flask API magic
from flask import Flask, request, g, jsonify, abort, make_response
from flask_restful import Resource, Api, reqparse

#import database tables
from create_schluckspecht_database import User, BeerCaseLog

import json

# flask instantiation
app = Flask(__name__) # create the application instance
app.config.from_object(__name__) # load config from this file , flaskr.py
api = Api(app)

# set up sqalchemy base
Base = declarative_base()

# Create an engine that stores data in the local directory's
# sqlalchemy_example.db file.
engine = create_engine('sqlite:///schluckspecht.db')
# Bind the engine to the metadata of the Base class so that the
# declaratives can be accessed through a DBSession instance
Base.metadata.bind = engine

DBSession = sessionmaker(bind=engine)
# A DBSession() instance establishes all conversations with the database
# and represents a "staging zone" for all the objects loaded into the
# database session object. Any change made against the objects in the
# session won't be persisted into the database until you call
# session.commit(). If you're not happy about the changes, you can
# revert all of them back to the last commit by calling
# session.rollback()
session = DBSession()


#####################################################
# Begin API functions here

class HelloWorld(Resource):
    def get(self):
        return {'hello': 'world'}

api.add_resource(HelloWorld, '/api')

class Login(Resource):
    def post(self):
        parser = reqparse.RequestParser()
        parser.add_argument('UserName', type=String)
        args = parser.parse_args()
        result = session.query(User.user_name).filter(User.user_name == args['UserName'])
        if result is None:
            return {'User':False, 'Token':-1, 'ID':-1},400
        else:
            return {'User':True, 'Token':1, 'ID':result.id}

api.add_resource(Login, '/api/login')


class UserList(Resource):
    def get(self):
        beer_bought = session.query(BeerCaseLog.user_id,BeerCaseLog.price,BeerCaseLog.brand,User.user_name,
                                           func.max(BeerCaseLog.timestamp)) \
                                 .group_by(BeerCaseLog.user_id).join(User, User.id == BeerCaseLog.user_id).all()
        return jsonify(beer_bought)

api.add_resource(UserList, '/api/list')

class BeerList(Resource):
    def get(self,user_id):
        beerlist = session.query(BeerCaseLog.brand, BeerCaseLog.price, BeerCaseLog.timestamp)\
                          .filter(User.id == user_id).all()
        return jsonify(beerlist)

    def post(self,user_id):
        parser = reqparse.RequestParser()
        parser.add_argument('Brand', type=String)
        parser.add_argument('Price', type=Float)
        args = parser.parse_args()
        beer_case = BeerCaseLog()
        beer_case.price = args['Price']
        beer_case.brand = args['Brand']
        beer_case.user_id = user_id
        beer_case.timestamp = func.now()
        session.insert(beer_case)
        return session.flush()

api.add_resource(BeerList, '/api/beerlog/<int:user_id>')





if __name__ == '__main__':
    app.run(debug=True)
