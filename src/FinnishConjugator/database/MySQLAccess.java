

package FinnishConjugator.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alvaro
 */
public class MySQLAccess {
  private Connection connect = null;
  private Statement statement = null;
  private PreparedStatement preparedStatement = null;
  private ResultSet resultSet = null;

    public MySQLAccess() throws Exception {
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager
                .getConnection("jdbc:mysql://localhost/conjugator?"
                    + "user=conjugator&password=conjugator");
        } catch (SQLException ex) {
           Logger.getLogger(MySQLAccess.class.getName()).log(Level.SEVERE, " acess database error", ex);
        }
    }
  
  

//  public void readDataBase() throws Exception {
//    try {
//      // This will load the MySQL driver, each DB has its own driver
//      Class.forName("com.mysql.jdbc.Driver");
//      // Setup the connection with the DB
//      connect = DriverManager
//          .getConnection("jdbc:mysql://localhost/conjugator?"
//              + "user=conjugator&password=conjugator");
//
//      // Statements allow to issue SQL queries to the database
//      statement = connect.createStatement();
//      // Result set get the result of the SQL query
//      resultSet = statement
//          .executeQuery("select * from conjugator.verb");
//      writeResultSet(resultSet);
//
//      // PreparedStatements can use variables and are more efficient
//      preparedStatement = connect
//          .prepareStatement("insert into verb (type, name, description) values (?,?,?)");
//      // "myuser, webpage, datum, summary, COMMENTS from conjugator.verb");
//      // Parameters start with 1
//      preparedStatement.setString(1, "4");
//      preparedStatement.setString(2, "haluta");
//      preparedStatement.setString(3, "to want");
//
//      preparedStatement.executeUpdate();
//
//      preparedStatement = connect
//          .prepareStatement("SELECT * from conjugator.verb");
//      resultSet = preparedStatement.executeQuery();
//      writeResultSet(resultSet);
//
//      // Remove again the insert comment
//      preparedStatement = connect
//      .prepareStatement("delete from conjugator.verb where name= ? ; ");
//      preparedStatement.setString(1, "haluta");
//      preparedStatement.executeUpdate();
//      
//      resultSet = statement
//      .executeQuery("select * from conjugator.verb");
//      writeMetaData(resultSet);
//      
//    } catch (Exception e) {
//      throw e;
//    } finally {
//      close();
//    }
//
//  }

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
  private void close() {
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
  public String[] readVerb(String verb) throws Exception{
      try {
          // Statements allow to issue SQL queries to the database
          statement = connect.createStatement();
          // Result set get the result of the SQL query
          resultSet = statement
                  .executeQuery("select * from conjugator.verb where name = '"+ verb +"'");
          
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
      } finally {
          close();
      }
  }
} 
