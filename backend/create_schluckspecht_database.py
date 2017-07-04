from sqlalchemy import Column, ForeignKey, Integer, String, Boolean, Datetime, Float
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import relationship
from sqlalchemy import create_engine

# set up sqlalchemy base
Base = declarative_base()
 
class User(Base):
    __tablename__ = 'user'
    # Here we define columns for the table person
    # Notice that each column is also a normal Python instance attribute.
    id = Column(Integer, primary_key=True)
    name = Column(String(250), nullable=False)
    cool_name = Column(String(250))
    on_vacation = Column(Boolean, nullable=False, default=False)
 
class BeerCaseLog(Base):
    __tablename__ = 'beercaselog'
    # Here we define columns for the table address.
    # Notice that each column is also a normal Python instance attribute.
    id = Column(Integer, primary_key=True)
    timestamp = Column(Datetime, nullable=False)
    brand = Column(String(250))
    price = Column(Float, nullable=False)
    user_id = Column(Integer, ForeignKey('user.id'))

 
# Create an engine that stores data in the local directory's
# sqlalchemy_example.db file.
engine = create_engine('sqlite:///schluckspecht.db')
 
# Create all tables in the engine. This is equivalent to "Create Table"
# statements in raw SQL.
Base.metadata.create_all(engine)



