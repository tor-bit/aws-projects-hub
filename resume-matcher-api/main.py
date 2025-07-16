from fastapi import FastAPI
from app.routes import match  # assuming match.py is your route file

app = FastAPI()
app.include_router(match.router)
