package javafxapplication6;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
   A simple data source for getting database connections. 
*/
public class SimpleDataSourceV2
{

   private static String dbserver;
   private static String database;
   private static String username;
   private static String password;

   private static Connection activeConn;

   private static void init()
         
   {
      try {
        String driver = "com.mysql.jdbc.Driver";
        Class.forName(driver);
      }
      catch (ClassNotFoundException e) {
          System.out.println(e);
      }


      dbserver="localhost";
      database="Project_Voedselbank";
      username = "root";
      password = "123";
      
   }

   /**
      Gets a connection to the database.
      @return the database connection
     * @throws java.sql.SQLException
   */
   public static Connection getConnection() throws SQLException
   {
       if (activeConn==null) {
           init();
           activeConn=createConnection();
       }
       else {
           if (!activeConn.isValid(0)) {
               activeConn=createConnection();
           }
       }

       return activeConn;

   }

   private static Connection createConnection() throws SQLException
   {

        String connectionString = "jdbc:mysql://" + dbserver + "/" + database + "?" +
                "user=" + username + "&password=" + password;

       return DriverManager.getConnection(connectionString);
   }
   
   public static void closeConnection() {
       if (activeConn!=null) {
           try {
                activeConn.close();
           }
           catch(SQLException e) {
               //to catch and do nothing is the best option
               //don't know how to recover from this exception
               
           }
           finally {
               activeConn=null;
           }
               
       }
       
   }
}
