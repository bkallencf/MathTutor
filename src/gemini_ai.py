from dotenv import load_dotenv
import os
import sys
import io
import google.generativeai as genai
import memory_manager

# Use UTF-8 characters
sys.stdout = io.TextIOWrapper(sys.stdout.buffer, encoding='utf-8', errors='replace')

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
try:
    model = genai.GenerativeModel(MODEL_ID)
except Exception as e:
    print(f"Error Creating AI model: {str(e)}")

# Cleans up text inputs and outputs
def clean_text(text: str) -> str:
    return text.encode('utf-8', errors='replace').decode('utf-8')

# Generates a response from gemini
def generate_response(user_input: str) -> str:
    # Gets the most recent memory logs along with the current input and cleans it up to match UTF-8
    memory = memory_manager.get_recent_memory(3)
    safe_input = clean_text(memory + "\n\nCurrent Question:\n" + user_input)

    # Tries to create a new response from Gemini
    try:
        response = model.generate_content(safe_input)
        return response.text
    except Exception as e:
        return f"Exception while generating a response: {e}"

# Runs the file
if __name__ == "__main__":
    user_prompt = ""
    for line in sys.stdin:
        user_prompt += line
    user_prompt = user_prompt.strip()

    if not user_prompt:
        print("No input received from user")
        exit(1)

    response = generate_response(user_prompt)
    safe_response = clean_text(response)
    
    try:
        memory_manager.create_log(clean_text(user_prompt), safe_response)
    except Exception as e:
        print(f"Failed to write log file: {e}")
    
    print(safe_response)
