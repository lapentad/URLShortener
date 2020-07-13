package database;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBhandler {

	private String path;
	private String dbName;
	private String tableName;

	public DBhandler(String dName, String tName) {
		setDbName(dName);
		setTableName(tName);
		setPath("jdbc:sqlite:" + System.getProperty("user.dir") + "/database/");

		File file = new File(System.getProperty("user.dir") + "/database/" + dbName);
		File folder = new File(System.getProperty("user.dir") + "/database");
		if (!file.exists()) {
			folder.mkdir();
			createNewDatabase();
			createNewTable();
			insert("1h3d9bM1p", "www.test.com/image.png");
		}
	}

	public void createNewDatabase() {

		String url = path + dbName;

		try (Connection conn = DriverManager.getConnection(url)) {
			if (conn != null) {
				DatabaseMetaData meta = conn.getMetaData();
				System.out.println("The driver name is " + meta.getDriverName());
				System.out.println("A new database has been created.");
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void createNewTable() {

		String url = path + dbName;

		// SQL statement for creating a new table
		String sql = "CREATE TABLE IF NOT EXISTS " + tableName + " (\n" + "	id integer PRIMARY KEY,\n"
				+ "	shortUrl text,\n" + "	longUrl text NOT NULL,\n" + "	clicks integer NOT NULL\n"+ ");";

		try (Connection conn = DriverManager.getConnection(url); Statement stmt = conn.createStatement()) {
			stmt.execute(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private Connection connect() {

		String url = path + dbName;

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}

	public void insert(String shortUrl, String longUrl) {
		String sql = "INSERT INTO " + tableName + "(shortUrl,longUrl,clicks) VALUES(?,?,1)";

		try (Connection conn = this.connect();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, shortUrl);
				pstmt.setString(2, longUrl);
				pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void selectAll() {
		String sql = "SELECT id, shortUrl, longUrl , clicks FROM URLS";

		try (Connection conn = this.connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			// loop through the result set
			while (rs.next()) {
				System.out.println(rs.getInt("id") + "\t" + rs.getString("shortUrl") + "\t" + rs.getString("longUrl") + "\t" + rs.getInt("clicks"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void selectMostClicked() {
		String sql = "SELECT id, shortUrl, longUrl , clicks FROM "+ tableName +" WHERE clicks = (SELECT MAX(clicks) FROM " + tableName + ")";

		try (Connection conn = this.connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			// loop through the result set
			while (rs.next()) {
				System.out.println(rs.getInt("id") + "\t" + rs.getString("shortUrl") + "\t" + rs.getString("longUrl") + "\t" + rs.getInt("clicks"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void updateClick(String shortUrl) {
        String sql = "UPDATE "+ tableName +" SET clicks = clicks+1"
                + " WHERE shortUrl LIKE " + "'"+shortUrl+"'";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
        		pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

	public String selectLongUrl(String shortUrlQuery) {
		String sql = "SELECT longUrl FROM URLS WHERE shortUrl LIKE '" + shortUrlQuery + "'";
		String longUrl = "";

		try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			ResultSet rs = pstmt.executeQuery();

			// loop through the result set
			while (rs.next()) {
				longUrl = rs.getString("longUrl");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return longUrl;
	}

	public boolean checkShortUrl(String shortUrlQuery) {
		String sql = "SELECT COUNT(*) FROM URLS WHERE shortUrl LIKE '%" + shortUrlQuery + "'";

		boolean found = false;
		try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			try (ResultSet rs = pstmt.executeQuery()) {

				if (rs.next()) {
					found = rs.getBoolean(1); // "found" column

				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return found;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public void setPath(String path) {
		this.path = path;
	}
}
