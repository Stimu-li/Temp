#!/bin/bash

# Install tools
sudo apt update
sudo apt install -y pandoc texlive-xetex texlive-fonts-recommended texlive-plain-generic texlive-latex-extra

# Convert all notebooks to PDF
find ./DSUP_LAB -name "Exercise*.ipynb" -exec jupyter nbconvert --to pdf {} \;

# Zip all generated PDFs
zip -r exercises_latex_pdfs.zip $(find ./DSUP_LAB -name "Exercise*.pdf")
