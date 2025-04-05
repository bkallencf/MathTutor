from dotenv import load_dotenv
import os
import sys
import google.generativeai as genai

# Load variables from .env file
load_dotenv()

# Configure the API with .env variable
api_key = os.getenv("GEMINI_API_KEY")

if not api_key:
    print("Error: GEMINI_API_KEY not found in environment variables.")
    exit(1)

# Configure AI model
client = genai.configure(api_key=api_key)
MODEL_ID="gemini-2.0-flash"
model = genai.GenerativeModel(MODEL_ID)

# Defines how the file is run
def generate_response(user_input: str) -> str:
    response = model.generate_content(user_input)
    return response.text

# Runs the file
if __name__ == "__main__":
    user_prompt = sys.stdin.read().strip()
    if not user_prompt:
        print("No input received from user")
    else:
        response = generate_response(user_prompt)
        print(response)