from fastapi import APIRouter
from app.modules.cv import CV
from app.routes.MatchEngine import MatchEngine

router = APIRouter()
engine = MatchEngine()

# Some example job descriptions to match against
jobs = [
    "Looking for a Python backend engineer with AWS experience.",
    "Frontend developer with React and CSS skills.",
    "Cloud architect with experience in Java, Spring Boot, and Docker.",
    "Data analyst skilled in SQL, Python, and data visualization."
]

engine.set_job_descriptions(jobs)

@router.post("/match")
def match_cv(cv: CV):

    engine.set_cv(cv)
    results = engine.get_ranked_jobs_from_cv(top_n=5)
    return {
        "cv_id": cv.id,
        "matches": results
    }
