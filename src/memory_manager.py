import json
import os
from pathlib import Path

log_path = Path("src/gemini_log.json")

# Clears everything on start
def initialize_log():
    if not log_path.parent.exists():
        log_path.parent.mkdir(parents=True)
    log_path.write_text("[\n]", encoding="utf-8")

# Deletes the last ] from the json file to add a new entry and replace it later
def remove_last_line():
    with open("src/gemini_log.json", "r+") as f:
        f.seek(0, os.SEEK_END)
        pos = f.tell() - 1
        while pos > 0 and f.read(1) != '\n':
            pos -= 1
            f.seek(pos, os.SEEK_SET)
        if pos > 0:
            f.seek(pos, os.SEEK_SET)
            f.truncate()

def create_first_log(safe_input, safe_response):
    remove_last_line()

    log_object = {
        "user_prompt" : safe_input,
        "gemini_response" : safe_response
    }

    with open("src/gemini_log.json", "a", encoding="utf-8") as f:
        f.write(json.dumps(log_object, ensure_ascii=False) + "\n]")

# Creates a new entry in memory
def create_log(safe_input, safe_response):
    remove_last_line()

    with open("src/gemini_log.json", "a", encoding="utf-8") as f:
        f.write(",")

    log_object = {
        "user_prompt" : safe_input,
        "gemini_response" : safe_response
    }

    with open("src/gemini_log.json", "a", encoding="utf-8") as f:
        f.write(json.dumps(log_object, ensure_ascii=False) + "\n]")

# Gets the n most recent logs from memory
def get_recent_memory(n):
    try:
        with open(log_path, "r", encoding="utf-8") as f:
            logs = json.load(f)
        memory_entries = logs[-n:]
        history = "\n\n".join([f"User: {entry['user_prompt']}\nAI: {entry['gemini_response']}" for entry in memory_entries])
        return history
    except Exception as e:
        print(f"Failed to load from memory: {e}")
        return ""
    
def clear_memory():
    try:
        with open("src/gemini_log.json", 'w') as f:
            pass
    except Exception as e:
        print(f"Failed to clear log: {e}")