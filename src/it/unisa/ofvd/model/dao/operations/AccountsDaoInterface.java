package it.unisa.ofvd.model.dao.operations;

import java.sql.SQLException;
import java.util.Collection;

import it.unisa.ofvd.exception.LocalException;
import it.unisa.ofvd.model.AccountsModel;

public interface AccountsDaoInterface {

	public Collection<AccountsModel> getAll() throws SQLException;
	
	public void delete(String id) throws SQLException;
	
	
	public AccountsModel login(String email, String password) throws LocalException;
	
	public AccountsModel retrieve(String id) throws SQLException;
	
	
	public void create(AccountsModel account) throws SQLException;
	
	public void update(AccountsModel account) throws SQLException;
}
