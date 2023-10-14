# wordcount program for Quorum

## Table of Contents

- [wordcount program for Quorum](#wordcount-program-for-quorum)
  - [Table of Contents](#table-of-contents)
  - [Introduction](#introduction)
  - [Features](#features)
  - [Testing](#testing)
  - [Usage](#usage)
  - [Updates](#updates)
    - [TODO](#todo)
  - [Author](#author)

## Introduction

This program takes a file as input, and will output the number of occurences for each word in the text file.

## Features

List the key features of your project. This section gives readers a quick overview of what your project can do.

## Testing

There are currently 2 input files for testing, `input0.txt` and `input1.txt`. 0 contains a "quick brown fox" text on repeat, while 1 contains a 1000-word lorem text.

## Usage

Currently, there is no prompt for input. Rename the input file as such:
```quorum
inputFile:SetPath("filename.txt")
```
Via the QuorumStudio application, hit ▶ to run the program.
The program will output the contents of the file.

## Updates

Currently this program only opens a text file and outputs its contents.
### TODO
- Implement method to input data from .csv file
- Ignore any other characters and symbols that is not a word
- Input/output dataset 

## Author

- [ProjectZuki - Willie Alcaraz](https://github.com/ProjectZuki)
- [Arian Izadi](https://github.com/arianizadi)