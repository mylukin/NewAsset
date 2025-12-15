#!/bin/bash
# Start frontend Vue development server

echo "Starting Asset Management Frontend..."

cd "$(dirname "$0")/.."

# Check if node_modules exists
if [ ! -d "node_modules" ]; then
    echo "Installing dependencies..."
    npm install
fi

npm run dev
