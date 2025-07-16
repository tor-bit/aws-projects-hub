from fastapi import FastAPI
from app.routes import match 

app = FastAPI()
app.include_router(match.router)
