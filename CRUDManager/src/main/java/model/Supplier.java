package model;

public class Supplier {
	
	private int id;
	private String name;
	private String email;
	private String fone;
	private String adress;
	private Company company;
	
	public Supplier() {
		this(0);
	}
	
	public Supplier(int id) {
		this.id = id;
		setName("");
		setEmail("");
		setFone("");
		setAdress("");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
}
