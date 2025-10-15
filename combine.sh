#!/bin/bash

# Define the output Markdown file
OUTPUT_FILE="/home/abi/Projects/Temp/DSUP_LAB/DSUP_LAB_Combined.md"
# Define the root directory where your notebooks are located
NOTEBOOK_ROOT_DIR="/home/abi/Projects/Temp/DSUP_LAB"

# Clear the output file if it already exists
> "$OUTPUT_FILE"

echo "# DSUP Lab Exercises (Combined)" >> "$OUTPUT_FILE"
echo "" >> "$OUTPUT_FILE"
echo "This document contains the combined content of all Jupyter Notebooks from the DSUP Lab." >> "$OUTPUT_FILE"
echo "" >> "$OUTPUT_FILE"

# Find all .ipynb files recursively, sort them by path for consistent ordering, and process each one
find "$NOTEBOOK_ROOT_DIR" -name "*.ipynb" | sort | while read -r notebook_file; do
    # Extract the relative path for a cleaner heading in the combined Markdown
    relative_path="${notebook_file#$NOTEBOOK_ROOT_DIR/}"
    
    echo "---" >> "$OUTPUT_FILE"
    echo "" >> "$OUTPUT_FILE"
    echo "## Exercise: $relative_path" >> "$OUTPUT_FILE"
    echo "" >> "$OUTPUT_FILE"
    
    # Convert the notebook to markdown and append its content to the output file
    # Using --stdout to print the markdown directly to standard output, which is then redirected.
    jupyter nbconvert --to markdown "$notebook_file" --stdout >> "$OUTPUT_FILE"
    
    echo "" >> "$OUTPUT_FILE" # Add an extra newline for better separation between exercises
done

echo "---" >> "$OUTPUT_FILE"
echo "" >> "$OUTPUT_FILE"
echo "Conversion complete. All Jupyter Notebooks combined into: $OUTPUT_FILE"