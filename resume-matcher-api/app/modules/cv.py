from pydantic import BaseModel

class CV(BaseModel):
    id: str
    skills: str
    experience: str
    education: str
