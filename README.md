# Open Source Java Hash

An open source implementation of a Java Hashing function and Hash Set data structure. 

## Table of Contents
- [Overview](#overview)
- [Installation & Setup](#installation--setup)  
    1. [Prerequisites](#1-prerequisites)  
    2. [Clone the Repository](#2-clone-the-repository)
    3. [Change Directory](#3-change-directory)
    3. [Compile the Project](#4-compile-the-project)
- [Testing](#testing)
- [License](#license)

## Overview

Mind the structure of the repository below. There are two seperate implementations within this project, as well as a folder full
of sample dictionaries for testing purposes.  
  
**Note that** some of the text files in the repository may take up a lot of space, so they have been compressed here.

```bash
root/.  
├───Dictionaries
│ ├─────12Mwords.zip
│ ├─────170Kwords.txt
│ ├─────50MillionWords.zip
│ └─────5Mwords.zip
│
├───Hash Function
│ ├─────HashCode.java
│ └─────HashTester.java
│
└───Hash Set
│ ├─────HashSet.java
│ ├─────HashSetTester.java
│ └─────HS_Interface.java
```  

## Installation & Setup

### 1. Prerequisites

Make sure you have the latest version of the Java Development Kit installed. You can download it [<u>here</u>](https://www.oracle.com/java/technologies/downloads/)

### 2. Clone the Repository
```bash
git clone https://github.com/prebish/open-hash.git
```

### 3. Change Directory

#### The Hash Function
```bash
cd ./'Hash Function'
```

#### The Hash Set
```bash
cd ./'Hash Set'
```

### 4. Compile the Project
```bash
javac -d build *.java
```

## Testing 

Refer to the project working tree in the [Overview section above](#overview).  
Each subdirectory (excluding the dictionaries) contains a tester.  
You can run them both like so:

**Hash Function**
```bash
java HashTester <numOfBuckets> <maxBucketSize> <fileOfStrings>
```

**Hash Set**
```bash
java HashSetTester <fileOfStrings> <numOfBuckets>
```


## License

This project is licensed under the **MIT License** - see the `LICENSE` file for details

