package dws.itemrecommender;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	
		Connection c = null;
	    Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:C:/xampp/htdocs/wmp/artist_similarity.db");
	      c.setAutoCommit(false);
	      System.out.println("Opened database successfully");

	      stmt = c.createStatement();
	      ResultSet rs = stmt.executeQuery( "SELECT target, similar FROM similarity;" );
	      while ( rs.next() ) {
	         String artist_id = rs.getString("target");
	         String  similar = rs.getString("similar");
	         System.out.println( "ID = " + artist_id );
	         System.out.println( "NAME = " + similar );
	         System.out.println();
	      }
	      rs.close();
	      stmt.close();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Operation done successfully");
	
	}

}
