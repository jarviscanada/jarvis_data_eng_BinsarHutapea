package ca.jrvs.apps.grep;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
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

        listFiles(this.getRootPath()).flatMap(file -> {
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
    public Stream<File> listFiles(String rootDir) {
        File directory = new File(rootDir);
        List<File> fileList = new ArrayList<>();

        Queue<File> queue = new LinkedList<>();
        queue.add(directory);

        while(!queue.isEmpty()){

            File currentFile = queue.poll();
            if (currentFile.isDirectory()){
                queue.addAll(Arrays.asList(currentFile.listFiles()));
            }
            else{
                fileList.add(currentFile);
            }
        }

        return fileList.stream();
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
