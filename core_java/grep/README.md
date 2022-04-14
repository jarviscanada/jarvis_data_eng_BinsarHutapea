# Introduction
This app, called Java Grep app, can be used to look up files inside a directory and any of its subdirectories containing particular words or word patterns. The application was written in Java 8, and some of its features, such as lambda and streams, were also used to implement several functionalities of this app. Also, Maven is utilized for managing this project, including its dependencies and building the executable JAR file. In addition, Docker is utilized for distributing the build of the app, which is available on Docker Hub under **binsarphutapea/grep**.

# Quick Start
To use the app, run the executable JAR file via Docker with the following commands

```
docker run --rm -v `pwd`[input directory]:[input directory] -v `pwd`[output directory]:/[output directory] binsarphutapea/grep [Regex pattern] [input directory] [path to output file]
```

Example:
```
docker run --rm -v `pwd`/data:/data -v `pwd`/log:/log binsarphutapea/grep .*Solar.*System.* /data /log/grep.out
```

#Implementation
## Pseudocode
```
for file in listFiles(rootDir)
    for line in readLines(file)
        if containsPattern(line)
            writeToFile(line)        
```

## Performance Issue
Initially, _List_ was used for storing lines from every input file and the ones that match the given Regex pattern. However, this would cause problems when the available memory was not enough to process files. To solve this issue, I utilized a feature of Java 8 called _Stream API_, removing the necessity of _List_ for storing lines.

# Test
A _.txt_ file containing at least 900,000 words was used for testing this app. In one test scenario, I set the regex pattern in a way that the app returns 29 words of text that matched. I compared the output file with the result generated using an online regex tool, and both had the same contents. In another test scenario, I set the heap memory used by JVM to just 5 MB to see how well it performed under such constraints. Initially, the tests failed because I utilized List for storing the lines from the input file, and there was not enough memory to perform the tasks. After I utilized Stream API instead of List for the app to perform the same tasks, the test was successful.

# Deployment
I deployed this project on Docker Hub. These are the steps I took to do so.
1. Created Dockerfile.
2. Packaged the app using Maven.
3. Built a new Docker image locally.
4. Verified the Docker image.
5. Ran Docker container to see if the app worked via Docker.
6. Pushed the image to Docker Hub.

# Improvement
Here are the three things I would like to improve on this project:
1. Accessing archived files.
2. Listing files that contain text that matched with any given regex pattern.
3. Code refactoring.
