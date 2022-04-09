package ca.jrvs.apps.jdbc;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCRunner {

   static final Logger logger = LoggerFactory.getLogger(JDBCRunner.class);

    /**
     * Main class to establish database connection
     * @param args [host] [username] [password] [databaseName]
     */
   public static void main(String[] args){

         if (args.length != 4){
            throw new IllegalArgumentException("USAGE: JDBCRunner host username password databaseName");
         }

         String host = args[0];
         String username = args[1];
         String password = args[2];
         String databaseName = args[3];

         DatabaseConnectionManager databaseConnectionManager = new DatabaseConnectionManager(host, username, password, databaseName);

         try{
            Connection connection = databaseConnectionManager.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM customer");

            while(resultSet.next()){
                System.out.println(resultSet.getInt(1));
            }
         } catch (SQLException e) {
            logger.error("Encountered SQL Exception ", e);
         }
   }
}
