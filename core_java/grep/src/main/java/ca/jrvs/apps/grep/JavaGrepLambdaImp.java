package ca.jrvs.apps.grep;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class JavaGrepLambdaImp extends JavaGrepImp{

    @Override
    public List<File> listFiles(String rootDir){

        List<File> filesList = new ArrayList<>();

        try (Stream<Path> pathStream = Files.walk(Paths.get(rootDir), Integer.MAX_VALUE)){
            pathStream.forEach(path -> {
                if (path.toFile().isFile()){
                    filesList.add(path.toFile());
                }
            });
        } catch (IOException e) {
            this.logger.error("IO Exception", e);
        }

        return filesList;
    }

    @Override
    public List<String> readLines(File inputFile){
        List<String> linesList = new ArrayList<>();

        try (Stream<String> lineStream = Files.lines(inputFile.toPath())){
            lineStream.forEach(line -> linesList.add(line));
        } catch (IOException e) {
            this.logger.error("IO Exception", e);
        }

        return linesList;
    }

    public static void main(String[] args){

        if (args.length == 3) {

            JavaGrepLambdaImp javaGrepLambdaImp = new JavaGrepLambdaImp();
            javaGrepLambdaImp.setRegex(args[0]);
            javaGrepLambdaImp.setRootPath(args[1]);
            javaGrepLambdaImp.setOutFile(args[2]);

            try {
                javaGrepLambdaImp.process();
            } catch (Exception ex){
                javaGrepLambdaImp.logger.error("Exception", ex);
            }

        }

    }
}
