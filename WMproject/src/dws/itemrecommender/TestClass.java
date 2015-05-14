package dws.itemrecommender;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection c = null;

		// Getting track_metadata
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager
					.getConnection("jdbc:sqlite:C:/Users/Laz/Desktop/WM Project/Datasets/track_metadata.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT track_id, title, song_id, artist_id, artist_mbid, artist_name, duration, track_7digitalid, year    FROM songs;");
			while (rs.next()) {
				// String track_id = rs.getString("track_id");
			}
			rs.close();
			stmt.close();
			c.close();

			// Statement 2
			Statement stmt2 = null;

			Class.forName("org.sqlite.JDBC");
			c = DriverManager
					.getConnection("jdbc:sqlite:C:/Users/Laz/Desktop/WM Project/Datasets/artist_similarity.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt2 = c.createStatement();
			ResultSet rs_sim = stmt2
					.executeQuery("SELECT target, similar FROM similarity;");
			while (rs_sim.next()) {
				// String artist_id = rs.getString("target");
				// System.out.println( "ID = " + artist_id );
			}
			rs_sim.close();
			stmt2.close();
			c.close();

			// Statement 3
			Statement stmt3 = null;
			Class.forName("org.sqlite.JDBC");
			c = DriverManager
					.getConnection("jdbc:sqlite:C:/Users/Laz/Desktop/WM Project/Datasets/artist_term.db");
			c.setAutoCommit(false);

			stmt3 = c.createStatement();
			ResultSet rs_artist_mbtag = stmt3
					.executeQuery("SELECT artist_id, mbtag FROM artist_mbtag;");
			while (rs_artist_mbtag.next()) {
				// String artist_id = rs.getString("target");
				// System.out.println( "ID = " + artist_id );
			}
			rs_artist_mbtag.close();
			stmt3.close();
			c.close();

			// Statement 4
			Statement stmt4 = null;
			Class.forName("org.sqlite.JDBC");
			c = DriverManager
					.getConnection("jdbc:sqlite:C:/Users/Laz/Desktop/WM Project/Datasets/artist_term.db");
			c.setAutoCommit(false);

			stmt4 = c.createStatement();
			ResultSet rs_artist_term = stmt4
					.executeQuery("SELECT artist_id, term FROM artist_term;");
			while (rs_artist_term.next()) {
				// String artist_id = rs.getString("target");
				// System.out.println( "ID = " + artist_id );
			}
			rs_artist_term.close();
			stmt4.close();
			c.close();

			// Statement 5
			Statement stmt5 = null;
			Class.forName("org.sqlite.JDBC");
			c = DriverManager
					.getConnection("jdbc:sqlite:C:/Users/Laz/Desktop/WM Project/Datasets/mxm_dataset.db");
			c.setAutoCommit(false);

			stmt5 = c.createStatement();
			ResultSet rs_lyrics = stmt5
					.executeQuery("SELECT track_id, mxm_tid, word, count FROM lyrics;");
			while (rs_lyrics.next()) {
				// String artist_id = rs.getString("target");
				// System.out.println( "ID = " + artist_id );
			}
			rs_lyrics.close();
			stmt5.close();
			c.close();

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Operation done successfully");



	}

}
