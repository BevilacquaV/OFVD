package it.unisa.ofvd.model;

public class AccountsModel {
	
	private String matricola;
	private String nome;
	private String cognome;	
	private String email;
	private String password;
	private String code_ospedale;
	private String code_cav;
	private String code_admin;
	private String telefono;
	
	public AccountsModel(String matricola, String nome,String cognome, String email, String password, String code_ospedale,String code_cav,String code_admin, String telefono) {
		this.matricola=matricola;
		this.nome=nome;
		this.cognome=cognome;
		this.email=email;
		this.password=password;
		this.code_ospedale=code_ospedale;
		this.code_cav=code_cav;
		this.code_admin=code_admin;
		this.telefono=telefono;
	}

	public String getMatricola() {
		return matricola;
	}

	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCode_ospedale() {
		return code_ospedale;
	}

	public void setCode_ospedale(String code_ospedale) {
		this.code_ospedale = code_ospedale;
	}

	public String getCode_cav() {
		return code_cav;
	}

	public void setCode_cav(String code_cav) {
		this.code_cav = code_cav;
	}

	public String getCode_admin() {
		return code_admin;
	}

	public void setCode_admin(String code_admin) {
		this.code_admin = code_admin;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public AccountsModel() {
	}	
	

	
	public boolean isAdmin() {
		return role.equals("admin");
	}
	
	public boolean isUser() {
		return role.equals("user");
	}	
	
	@Override
	public String toString() {
		return "Accounts [id=" + id + 
				", name=" + name + 
				", surname=" + surname + 
				", email=" + email + 
				", password=" + password + 
				", role=" + role + "]";
	}	

}
