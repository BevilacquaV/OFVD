package it.unisa.ofvd.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import it.unisa.ofvd.connection.DBMySQLConnectionPool;
import it.unisa.ofvd.exception.LocalException;
import it.unisa.ofvd.model.AccountsModel;
import it.unisa.ofvd.model.dao.operations.AccountsDaoInterface;
import it.unisa.ofvd.utils.Utility;

public class AccountsDao implements AccountsDaoInterface {

	public static final String TABLE_NAME = "accounts";

	@Override
	public synchronized AccountsModel login(String email, String password) throws LocalException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		AccountsModel account = null;

		try {
			con = DBMySQLConnectionPool.getConnection();
			stmt = con.prepareStatement("SELECT * FROM " + AccountsDao.TABLE_NAME + " WHERE email = ? AND password = MD5(?)");
			stmt.setString(1, email);
			stmt.setString(2, password);

			rs = stmt.executeQuery();

			if (rs.next()) {
				account = new AccountsModel();
				account.setId(rs.getInt("matricola"));
				account.setName(rs.getString("nome"));
				account.setSurname(rs.getString("cognome"));
				account.setEmail(rs.getString("email"));
				account.setRole(rs.getString("telefono"));
				account.setPassword(rs.getString("password"));
				account.setRole(rs.getString("code_ospedale"));
				account.setRole(rs.getString("code_cav"));
				account.setRole(rs.getString("code_ospedale"));
				account.setRole(rs.getString("code_admin"));

			} else {
				throw new LocalException("Invalid login or password.");
			}
		} catch (SQLException sqlException) {
			Utility.severe(sqlException.getMessage());
			throw new LocalException("Invalid login or password.");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
			} catch (SQLException sqlException) {
				Utility.severe(sqlException.getMessage());
				throw new LocalException("Invalid login or password.");
			} finally {
				try {
					if (con != null)
						DBMySQLConnectionPool.releaseConnection(con);
				} catch (SQLException sqlException) {
					Utility.severe(sqlException.getMessage());
					throw new LocalException("Invalid login or password.");
				}
			}

		}
		return account;
	}

	@Override
	public synchronized Collection<AccountsModel> getAll() throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		Collection<AccountsModel> accountList = new LinkedList<AccountsModel>();

		try {
			con = DBMySQLConnectionPool.getConnection();
			stmt = con.prepareStatement("SELECT * FROM " + AccountsDao.TABLE_NAME);

			rs = stmt.executeQuery();

			while (rs.next()) {
				AccountsModel account = new AccountsModel();
				account.setId(rs.getInt("matricola"));
				account.setName(rs.getString("nome"));
				account.setSurname(rs.getString("cognome"));
				account.setEmail(rs.getString("email"));
				account.setRole(rs.getString("telefono"));
				account.setPassword(rs.getString("password"));
				account.setRole(rs.getString("code_ospedale"));
				account.setRole(rs.getString("code_cav"));
				account.setRole(rs.getString("code_ospedale"));
				account.setRole(rs.getString("code_admin"));

				accountList.add(account);
			}
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
			} catch (SQLException sqlException) {
				Utility.severe(sqlException.getMessage());
			} finally {
				if (con != null)
					DBMySQLConnectionPool.releaseConnection(con);
			}
		}
		return accountList;
	}

	
	@Override
	public synchronized void delete(String email) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DBMySQLConnectionPool.getConnection();

			stmt = con.prepareStatement("DELETE FROM " + AccountsDao.TABLE_NAME + " WHERE " + " email = ?");
			stmt.setString(1, email);

			stmt.executeUpdate();
			con.commit();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException sqlException) {
				Utility.severe(sqlException.getMessage());
			} finally {
				if (con != null)
					DBMySQLConnectionPool.releaseConnection(con);
			}
		}
	}



	@Override
	public synchronized AccountsModel retrieve(String email) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		AccountsModel account = null;

		try {
			con = DBMySQLConnectionPool.getConnection();

			stmt = con.prepareStatement("SELECT * FROM " + AccountsDao.TABLE_NAME + " WHERE email = ?");
			stmt.setString(1, email);

			rs = stmt.executeQuery();

			if (rs.next()) {
				account = new AccountsModel();
				account.setId(rs.getInt("matricola"));
				account.setName(rs.getString("nome"));
				account.setSurname(rs.getString("cognome"));
				account.setEmail(rs.getString("email"));
				account.setRole(rs.getString("telefono"));
				account.setPassword(rs.getString("password"));
				account.setRole(rs.getString("code_ospedale"));
				account.setRole(rs.getString("code_cav"));
				account.setRole(rs.getString("code_ospedale"));
				account.setRole(rs.getString("code_admin"));
			}
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
			} catch (SQLException sqlException) {
				Utility.severe(sqlException.getMessage());
			} finally {
				if (con != null)
					DBMySQLConnectionPool.releaseConnection(con);
			}
		}
		return account;
	}

	@Override
	public synchronized void create(AccountsModel account) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;

		try {
			con = DBMySQLConnectionPool.getConnection();
			stmt = con.prepareStatement("INSERT INTO " + AccountsDao.TABLE_NAME + " (matricola,email, nome, cognome, password,telefono,code_ospedale,code_cav,code_admin) VALUES (?, ?, ?, ?, MD5(?), ?, ?, ?, ?)");

			stmt.executeUpdate();
			con.commit();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException sqlException) {
				Utility.severe(sqlException.getMessage());
			} finally {
				if (con != null)
					DBMySQLConnectionPool.releaseConnection(con);
			}
		}
	}

	@Override
	public synchronized void update(AccountsModel account) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;

		try {
			con = DBMySQLConnectionPool.getConnection();
			if(account.getPassword().equals(""))
				stmt = con.prepareStatement("UPDATE " + AccountsDao.TABLE_NAME + " SET nome = ?, cognome = ?, telefono= ?, code_ospedale=? ,code_cav=? ,code_admin=?  WHERE email = ?");
			else
				stmt = con.prepareStatement("UPDATE " + AccountsDao.TABLE_NAME + " SET nome = ?, cognome = ?, password = MD5(?),cognome = ?, telefono= ?, code_ospedale=? ,code_cav=? ,code_admin=?   WHERE email = ?");
			
			stmt.executeUpdate();
			con.commit();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException sqlException) {
				Utility.severe(sqlException.getMessage());
			} finally {
				if (con != null)
					DBMySQLConnectionPool.releaseConnection(con);
			}
		}

	}

}
