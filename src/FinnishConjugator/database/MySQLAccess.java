package FinnishConjugator.database;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author alvaro
 */
public class MySQLAccess {
  private Connection connect = null;
  private Statement statement = null;
  private PreparedStatement preparedStatement = null;
  private ResultSet resultSet = null;

    public MySQLAccess() throws ClassNotFoundException {
        try {
            // This will load the MySQL/SQLite driver, each DB has its own driver
            Class.forName("org.sqlite.JDBC");
            // Setup the connection with the DB
            connect = DriverManager
                .getConnection("jdbc:sqlite:database.db");
            
            // This create database if don't exist without destroying the 
            // the user data
            try{
                statement = connect.createStatement();
            resultSet = statement
                  .executeQuery("select * from verb where name = '"+ "puhua" +"'");
            statement = connect.createStatement();
            } catch (Exception ex) {
                try {
                String sql = readFile("sqlFiles/sqlite.sql", Charset.forName("UTF-8"));
                statement.executeUpdate(sql);
                } catch (IOException ex2) {
                    JOptionPane.showMessageDialog(null, "sql database file not found");
                }
            }
            
            
        } catch (SQLException ex) {
            
        }
    }
  
  


  private void writeMetaData(ResultSet resultSet) throws SQLException {
    //   Now get some metadata from the database
    // Result set get the result of the SQL query
    
    System.out.println("The columns in the table are: ");
    
    System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
    for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
      System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
    }
  }

  private void writeResultSet(ResultSet resultSet) throws SQLException {
    // ResultSet is initially before the first data set
    while (resultSet.next()) {
      // It is possible to get the columns via name
      // also possible to get the columns via the column number
      // which starts at 1
      // e.g. resultSet.getSTring(2);
      String type = resultSet.getString("type");
      String name = resultSet.getString("name");
      String description = resultSet.getString("description");
      System.out.println("type: " + type);
      System.out.println("name: " + name);
      System.out.println("description: " + description);
    }
  }

  // You need to close the resultSet
  public void close() {
    try {
      if (resultSet != null) {
        resultSet.close();
      }

      if (statement != null) {
        statement.close();
      }

      if (connect != null) {
        connect.close();
      }
    } catch (Exception e) {

    }
  }
  
  // this method reads the database and retrieves one verb row in a string array
  public String[] readVerb(String verb) throws SQLException{
      try {
          // Statements allow to issue SQL queries to the database
          statement = connect.createStatement();
          // Result set get the result of the SQL query
          resultSet = statement
                  .executeQuery("select * from verb where name = '"+ verb +"'");
          
          // we are going to retrieve only one row
          resultSet.next();
          String[] result = new String[resultSet.getMetaData().getColumnCount()];
          
          // warning: getString index starts in 1
          for(int i = 1; i <= result.length; i++){
              result[i-1] = resultSet.getString(i);
          }
          return result;
      } catch (SQLException ex) {
          Logger.getLogger(MySQLAccess.class.getName()).log(Level.SEVERE, " query database error", ex);
          throw ex;
      }
  }
  public boolean insertVerb(String verb, int type, String description) throws SQLException{
      try {
          preparedStatement = connect.prepareStatement("insert into verb (type,"
                  + " name, description) values (?,?,?)");
          
          preparedStatement.setInt(1, type);
          preparedStatement.setString(2, verb);
          preparedStatement.setString(3, description);
          preparedStatement.executeUpdate();
          return true;
      } catch (SQLException ex) {
          return false;
      }
  }
  
  public boolean deleteVerb(String verb) throws SQLException{
      try {
          preparedStatement = connect
      .prepareStatement("delete from verb where name=?;");
      preparedStatement.setString(1, verb);
      preparedStatement.executeUpdate();
      return true;
      
      } catch (SQLException ex) {
          return false;
      }
  }
  
//  Function to read the sql file
  private static String readFile(String path, Charset encoding) 
  throws IOException {
    byte[] encoded = Files.readAllBytes(Paths.get(path));
    return new String(encoded, encoding);
  }
  
} 
