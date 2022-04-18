package ca.jrvs.apps.grep;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class JavaGrepImp implements JavaGrep{

    final Logger logger = LoggerFactory.getLogger(JavaGrep.class);

    private String regex;
    private String rootPath;
    private String outFile;

    @Override
    public String getRegex() {
        return regex;
    }

    @Override
    public void setRegex(String regex) {
        this.regex = regex;
    }

    @Override
    public void process() throws IOException {

        listFiles(this.getRootPath()).stream().flatMap(file -> {
            try {
                return readLines(file);
            } catch (IOException e) {
                throw new RuntimeException();
            }
        }).filter(line -> containsPattern(line))
                .forEach(line -> {
                    writeToFile(line);
                });
    }

    @Override
    public List<File> listFiles(String rootDir) {
        List<File> filesList = new ArrayList<>();

        try(Stream<Path> pathStream = Files.walk(Paths.get(rootDir), Integer.MAX_VALUE)){
            pathStream.forEach(path -> {
                if (path.toFile().isFile()){
                    filesList.add(path.toFile());
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return filesList;
    }

    @Override
    public Stream<String> readLines(File inputFile) throws IOException {
        return Files.lines(inputFile.toPath());
    }

    @Override
    public boolean containsPattern(String line) {
        if (line.matches(this.getRegex())){
            return true;
        }
        return false;
    }

    @Override
    public void writeToFile(String line){

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(this.getOutFile(), true);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

            bufferedWriter.write(line);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public String getRootPath() {
        return rootPath;
    }

    @Override
    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    @Override
    public String getOutFile() {
        return outFile;
    }

    @Override
    public void setOutFile(String outFile) {
        this.outFile = outFile;
    }

    public static void main(String[] args){

        if (args.length != 3){
            throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFile");
        }

        JavaGrepImp javaGrepImp = new JavaGrepImp();
        javaGrepImp.setRegex(args[0]);
        javaGrepImp.setRootPath(args[1]);
        javaGrepImp.setOutFile(args[2]);

        try{
            javaGrepImp.process();
        } catch (IOException ex){
            javaGrepImp.logger.error("IO Exception caught", ex);
        }
    }
}
