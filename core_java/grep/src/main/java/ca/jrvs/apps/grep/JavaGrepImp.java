package ca.jrvs.apps.grep;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;

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
        List<String> matchedLines = new ArrayList<>();

        for (File file : listFiles(this.getRootPath())){
            for (String line : readLines(file)){
                if (containsPattern(line)){
                    matchedLines.add(line);
                }
            }
        }
        writeToFile(matchedLines);
    }

    @Override
    public List<File> listFiles(String rootDir) {
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

        return fileList;
    }

    @Override
    public List<String> readLines(File inputFile) {
        List<String> lineList = new ArrayList<>();

        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));

            String line = bufferedReader.readLine();

            while(line != null){
                lineList.add(line);
                line = bufferedReader.readLine();
            }

        }catch (FileNotFoundException ex){
            this.logger.error("File not found", ex);
        }catch (IOException ex){
            this.logger.error("IO exception", ex);
        }
        return lineList;
    }

    @Override
    public boolean containsPattern(String line) {
        if (line.matches(this.getRegex())){
            return true;
        }
        return false;
    }

    @Override
    public void writeToFile(List<String> lines) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(this.getOutFile());
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

        for(String line : lines){
            bufferedWriter.write(line);
            bufferedWriter.newLine();
        }
        bufferedWriter.flush();
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
        } catch (Exception ex){
            javaGrepImp.logger.error("Error: Unable to process", ex);
        }
    }
}
