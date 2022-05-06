package ca.jrvs.apps.jdbc;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCRunner {

    /**
     * Main class to establish database connection
     * @param args [host] [username] [password] [databaseName] [firstName] [lastName] [email] [address] [city] [state] [phone] [zipcode] [newEmail] [orderId]
     */
   public static void main(String[] args){

         if (args.length != 14){
            throw new IllegalArgumentException("USAGE: JDBCRunner host username password databaseName firstName lastName email address city state phone zipcode newEmail orderId");
         }

         String host = args[0];
         String username = args[1];
         String password = args[2];
         String databaseName = args[3];
         String firstName = args[4];
         String lastName = args[5];
         String email = args[6];
         String address = args[7];
         String city = args[8];
         String state = args[9];
         String phone = args[10];
         String zipCode = args[11];
         String newEmail = args[12];
         long orderId = Long.parseLong(args[13]);

         DatabaseConnectionManager databaseConnectionManager = new DatabaseConnectionManager(host, username, password, databaseName);

         try{
            Connection connection = databaseConnectionManager.getConnection();
            CustomerDAO customerDAO = new CustomerDAO(connection);
            Customer customer = new Customer();
            customer.setFirstName(firstName);
            customer.setLastName(lastName);
            customer.setEmail(email);
            customer.setAddress(address);
            customer.setCity(city);
            customer.setState(state);
            customer.setPhone(phone);
            customer.setZipCode(zipCode);

            Customer dbCustomer = customerDAO.create(customer);
            System.out.println(dbCustomer);
            dbCustomer = customerDAO.findById(dbCustomer.getId());
            System.out.println(dbCustomer);
            dbCustomer.setEmail(newEmail);
            dbCustomer = customerDAO.update(dbCustomer);
            System.out.println(dbCustomer);
            customerDAO.delete(dbCustomer.getId());

            OrderDAO orderDAO = new OrderDAO(connection);
            Order order = orderDAO.findById(orderId);
            System.out.println(order);
         } catch (SQLException e) {
             throw new RuntimeException(e);
         }
   }
}
