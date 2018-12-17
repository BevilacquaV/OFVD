package it.unisa.ofvd.connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.unisa.ofvd.utils.Constants;
import it.unisa.ofvd.utils.Utility;

public class DBMySQLConnectionPool {
	




		private static List<Connection> freeDbConnections;

		static {
			freeDbConnections = new LinkedList<Connection>();
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				Utility.severe("DB driver not found:" + e.getMessage());
			}
		}

		private static synchronized Connection createDBConnection() throws SQLException {
			Connection newConnection = null;

			newConnection = DriverManager.getConnection(
					"jdbc:mysql://" + Constants.mysqlIp + ":" + Constants.mysqlPort + "/" + Constants.mysqlDb
							+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", //&useSSL=false",
					Constants.mysqlLogin, Constants.mysqlPwd);

			newConnection.setAutoCommit(false);
			return newConnection;
		}

		public static synchronized void test() throws SQLException {
			Connection _connection = DBMySQLConnectionPool.getConnection();
			if (_connection != null)
				DBMySQLConnectionPool.releaseConnection(_connection);
		}

		public static synchronized Connection getConnection() throws SQLException {
			Connection connection;

			if (!freeDbConnections.isEmpty()) {
				connection = (Connection) freeDbConnections.get(0);
				freeDbConnections.remove(0);

				try {
					if (connection.isClosed())
						connection = getConnection();
				} catch (SQLException e) {
					connection.close();
					connection = getConnection();
				}
			} else {
				connection = createDBConnection();
			}

			return connection;
		}

		public static synchronized void releaseConnection(Connection connection) throws SQLException {
			if (connection != null)
				freeDbConnections.add(connection);
		}

	}



