package dws.itemrecommender;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class lFM_tag {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap<String, ArrayList<String>> tag_index = new HashMap<String, ArrayList<String>>();
		HashMap<String, Integer> tag_count = new HashMap<String, Integer>();
		// Ask how to define it properly!

		HashMap<String, ArrayList<String>> reverse_tag_index = new HashMap<String, ArrayList<String>>();

		Connection c = null;

		try {
			Statement stmt3 = null;
			// Statement 3
			Class.forName("org.sqlite.JDBC");
			c = DriverManager
					.getConnection("jdbc:sqlite:C:/Users/Laz/Desktop/WM Project/Datasets/lastfm_tags.db");
			c.setAutoCommit(false);

			stmt3 = c.createStatement();

			ResultSet rs_lFM_tag = stmt3.executeQuery("SELECT "
					+ " tids.tid AS track_id," + " tags.tag AS tag, "
					+ " tid_tag.val AS weigth  FROM tid_tag "
					+ " JOIN tids ON tid_tag.tid = tids.rowid "
					+ " JOIN tags ON tid_tag.tag = tags.rowid "
					+ " WHERE track_ID = 'TRCCKNV128F149573B';");

			while (rs_lFM_tag.next()) {
				String track_id = rs_lFM_tag.getString("track_id");
				String tag = rs_lFM_tag.getString("tag");

				/*
				 * // frequency according to other codings! Value range from
				 * 0-100
				 */
				int weight = rs_lFM_tag.getInt("weigth");

				if (tag_index.containsKey(tag) == true) {
					ArrayList<String> check_track = tag_index.get(tag);
					if (check_track.contains(track_id) != true) {
						check_track.add(track_id);
					}
				} else if (tag_index.containsKey(tag) != true) {
					ArrayList<String> initial_track = new ArrayList<String>();
					initial_track.add(track_id);
					tag_index.put(tag, initial_track);
				}
				// reverse_tag_index
				if (reverse_tag_index.containsKey(track_id) == true) {
					ArrayList<String> check_tag = reverse_tag_index
							.get(track_id);
					if (check_tag.contains(tag) != true) {
						check_tag.add(tag);
					}
				} else if (reverse_tag_index.containsKey(track_id) != true) {
					ArrayList<String> initial_tag = new ArrayList<String>();
					initial_tag.add(tag);
					reverse_tag_index.put(track_id, initial_tag);
				}

				// System.out.println("TrackID: ");
				// System.out.println(track_id);
				// System.out.println("Tag: ");
				// System.out.println(tag);
				// System.out.println("Weight: " + weight);

			}
			rs_lFM_tag.close();
			stmt3.close();
			c.close();

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

		System.out.println("Reverse Tag Index: ");
		System.out.println(reverse_tag_index);
		// Output:
		// {TRCCKNV128F149573B=[pop, female vocalists, soul, love songs]}

		System.out.println("Tag Index: ");
		System.out.println(tag_index);
		// Output:
		// {pop=[TRCCKNV128F149573B], female vocalists=[TRCCKNV128F149573B],
		// soul=[TRCCKNV128F149573B], love songs=[TRCCKNV128F149573B]}

	}

}
