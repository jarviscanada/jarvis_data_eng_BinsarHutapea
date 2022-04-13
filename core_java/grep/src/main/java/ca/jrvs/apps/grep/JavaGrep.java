package ca.jrvs.apps.grep;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

public interface JavaGrep {

    /**
     * Top level search workflow
     * @throws IOException
     */
    void process() throws IOException;

    /**
     * Traverse a given directory and return all files
     * @param rootDir input directory
     * @return files under the rootDir
     */
    Stream<File> listFiles(String rootDir);

    /**
     * Read a file and return all the lines
     * @param inputFile
     * @return
     */
    Stream<String> readLines(File inputFile) throws IOException;

    /**
     * Check if a file contains the regex pattern
     * @param line input string
     * @return
     */
    boolean containsPattern(String line);

    /**
     * Write lines to a file
     * @param line
     * @throws IOException
     */
    void writeToFile(String line) throws IOException;

    /**
     * Get the root path
     * @return root path
     */
    String getRootPath();

    /**
     * Set the root path
     * @param rootPath new root path
     */
    void setRootPath(String rootPath);

    /**
     * Get regex
     * @return regex
     */
    String getRegex();

    /**
     * Set new regex
     * @param regex new regex
     */
    void setRegex(String regex);
    /**
     * Get the output file
     * @return output file
     */
    String getOutFile();

    /**
     * Set the output file
     * @param outFile path to the output file, including the filename
     */
    void setOutFile(String outFile);
}
