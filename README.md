# Sweet Shop Management System

Full-stack project: Java Spring Boot backend + React frontend.

## What's included
- Backend: Spring Boot (Java 17), uses SQLite file `sweetshop.db` (not in-memory).
- Frontend: React (basic SPA).
- Authentication: JWT-based simple implementation.
- Tests: JUnit 5 unit tests (sample).
- Instructions to run locally and push to GitHub.

## Quick run (Backend)
1. Install Java 17 and Maven.
2. From `backend/` directory:
   - Build: `mvn clean package`
   - Run: `mvn spring-boot:run`
   - The app uses SQLite file `sweetshop.db` in the backend folder.

## Quick run (Frontend)
1. Install Node 18+ and npm.
2. From `frontend/` directory:
   - `npm install`
   - `npm start`
   - Frontend runs on http://localhost:3000 and connects to backend at http://localhost:8080.

## My AI Usage
- Tools used: ChatGPT (OpenAI) for scaffolding and example code snippets.
- How used: Generated project scaffold, sample controllers, and README. I reviewed and adapted code.
- Reflection: AI helped speed up initial setup. I ensured auth and DB logic are reviewed and secure.

## GitHub
1. Create repo on GitHub.
2. From project root:
   - `git init`
   - `git add .`
   - `git commit -m "chore: initial commit

Co-authored-by: ChatGPT <chatgpt@openai.com>"`
   - `git branch -M main`
   - `git remote add origin <your-repo-url>`
   - `git push -u origin main`

