from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity
import re

class MatchEngine:
    def __init__(self):
        self.tfidf_vectorizer = TfidfVectorizer(stop_words='english') # doesnt use native words like and, the, etc.

    def set_job_descriptions(self, job_descriptions: list[str]):
        """
        Set the job descriptions to be used for matching.
        """
        self.job_descriptions = job_descriptions

    def set_cv(self, cv: dict):
        """
        Get the current candidate cv.
        """
        self.cv = cv.dict()
    
    def __preprocess(self, text: str) -> str:
        """
        Preprocess the input text by converting it to lowercase and removing punctuation.
        """
        text = text.lower()
        text = re.sub(r'[^\w\s]', '', text) 
        return text
    
    def __vectorize_job_descriptions(self):
        """
        Vectorizes the job description using TF-IDF.
        """
        # Clean up all the descriptions and add to list. Then vectorize the list
        cleaned = [self.__preprocess(desc) for desc in self.job_descriptions]
        vectorised_desc = self.tfidf_vectorizer.fit_transform(cleaned)
        return vectorised_desc
    
    def __vectorize_cv(self):
        """
        Vectorizes the cv using TF-IDF.
        """
        # Combine cv fields into a single string, clean it up, and vectorize
        cv_text = ' '.join([self.__preprocess(str(value)) for value in self.cv.values()])
        vectorised_cv = self.tfidf_vectorizer.transform([cv_text])
        return vectorised_cv
    
    def get_ranked_jobs_from_cv(self, top_n):
        """
        Compares the cv against the job descriptions and returns a ranked list of jobs based on cosine similarity.
        """
        # Vectorize the job descriptions and the cv
        vectorised_desc = self.__vectorize_job_descriptions()
        vectorised_cv = self.__vectorize_cv()
        
        # Calculate cosine similarity between the cv and each job description
        cos_similarities = cosine_similarity(vectorised_cv, vectorised_desc)[0]
        
        # Get indices of jobs sorted by similarity score in descending order
        ranked_indices = cos_similarities.argsort()[::-1]
        print(f"Ranked Indices: {ranked_indices}")
        
        # Create a ranked list of jobs with their similarity scores
        ranked_jobs = [(self.job_descriptions[i], cos_similarities[i]) for i in ranked_indices]
        print(f"Ranked Jobs: {ranked_jobs[:top_n]}")
        
        return ranked_jobs

# Example usage: Testing the MatchEngine class
# if __name__ == "__main__":
#     # Example job descriptions and a cv
#     job_descriptions = [
#         "Software Engineer with experience in Python and machine learning.",
#         "Data Scientist with expertise in data analysis and statistical modeling.",
#         "Web Developer skilled in HTML, CSS, and JavaScript.",
#         "DevOps Engineer with a focus on cloud infrastructure and automation.",
#         "Project Manager with strong leadership and communication skills."
#     ]
    
#     cv = {
#         "name": "John Doe",
#         "skills": "Python, machine learning, data analysis",
#         "experience": "Worked on various software projects involving Python and data science."
#     }
#     match_engine = MatchEngine(job_descriptions, cv)
#     ranked_jobs = match_engine.get_ranked_jobs_from_cv(top_n=3)
    
#     for job, score in ranked_jobs:
#         print(f"Job: {job}, Similarity Score: {score:.4f}")


# Example usage:












 # ------ Testing the cosine similarity with TF-IDF vectorization ----
# text1 = 'I love playing basketball'
# text2 = 'I love playing soccer and basketball'
# compare_list = [text1, text2]

# tfidf_vectorizer = TfidfVectorizer()

# #vectorize the texts
# tf_idf_matrix = tfidf_vectorizer.fit_transform(compare_list)

# print(tfidf_vectorizer.vocabulary_)

# # calculate the cosine simularity
# cos_sim = cosine_similarity(tf_idf_matrix)[0,1]

# print(cos_sim)

