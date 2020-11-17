package ftt.uash.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;

public class DbUtil {
	private static Connection connection = null;

	public static Connection getConnection() {
		if (connection != null) {
			return connection;
		} else {
			try {
				Properties properties = new Properties();
				InputStream inputStream = DbUtil.class.getClassLoader().getResourceAsStream("/db.resources");

				properties.load(inputStream);

				String driver = properties.getProperty("driver");
				String url = properties.getProperty("url");
				String user = properties.getProperty("user");
				String password = properties.getProperty("password");

				System.out.println(driver + " - " + url + " - " + new Date());

				Class.forName(driver);
				connection = DriverManager.getConnection(url, user, password);

				System.out.println(connection);
			} catch (IOException | ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			
			return connection;
		}
	}
}
