# CV Scorer – Resume to Job Matcher (Project #2)

This project is a Python-based FastAPI application that takes a candidate's CV and compares it against a list of job descriptions, ranking them based on relevance using text similarity algorithms.

## Features

- FastAPI-based RESTful API
- TF-IDF + Cosine Similarity for text matching
- Modular, testable design with clear separation of concerns
- Designed to highlight CS fundamentals (algorithms, OOP, data structures)
- Easily extendable for real-world recruiting platforms

---

## Tech Stack

- **Python 3.10+**
- **FastAPI** – for building the REST API
- **scikit-learn** – for TF-IDF and cosine similarity
- **Pydantic** – for data validation
- **NumPy** – for efficient vector operations
- **Uvicorn** – for running the server

---

## How it Works

1. **User submits a CV** (structured as JSON with fields like name, experience, skills, etc.)
2. The `MatchEngine`:
   - Preprocesses the text
   - Vectorizes it using `TfidfVectorizer`
   - Computes cosine similarity against predefined job descriptions
3. Returns the top N job matches based on score

---

## Example API Call

**POST** `/match`

```json
{
  "id": "cv_123",
  "skills": "Python, AWS, FastAPI",
  "experience": "Built scalable APIs using FastAPI and deployed to AWS.",
  "education": "Computer Science"
}
