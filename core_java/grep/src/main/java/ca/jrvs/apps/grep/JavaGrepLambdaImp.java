package ca.jrvs.apps.grep;

import java.io.File;
import java.util.List;

public class JavaGrepLambdaImp extends JavaGrepImp{

    @Override
    public List<File> listFiles(String rootDir) {
        return listFiles().stream()
    }

    @Override
    public List<String> readLines(File inputFile) {
        return super.readLines(inputFile);
    }

    public static void main(String[] args){

        if (args.length != 3) {

            JavaGrepLambdaImp javaGrepLambdaImp = new JavaGrepLambdaImp();
            javaGrepLambdaImp.setRegex(args[0]);
            javaGrepLambdaImp.setRootPath(args[1]);
            javaGrepLambdaImp.setOutFile(args[2]);

            try {
                javaGrepLambdaImp.process();
            } catch (Exception ex){
                ex.printStackTrace();
            }


        }

    }
}
