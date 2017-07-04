# import sql magic
from sqlalchemy import Column, ForeignKey, Integer, String, Boolean, Datetime, Float
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import relationship
from sqlalchemy import create_engine

# import flask API magic
from flask import Flask, request, g, jsonify, abort, make_response

#import database tables
from create_schluckspecht_databse import User, BeerCaseLog

# flask instantiation
app = Flask(__name__) # create the application instance
app.config.from_object(__name__) # load config from this file , flaskr.py

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



