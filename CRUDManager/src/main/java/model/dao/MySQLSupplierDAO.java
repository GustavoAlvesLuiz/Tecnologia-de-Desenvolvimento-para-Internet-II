package model.dao;

import java.util.ArrayList;
import java.util.List;

import model.Company;
import model.ModelException;
import model.Supplier;

public class MySQLSupplierDAO implements SupplierDAO {

	@Override
	public boolean save(Supplier supplier) throws ModelException {
		DBHandler db = new DBHandler();
		
		String sqlInsert = "INSERT INTO suppliers VALUES (DEFAULT, ?, ?, ?, ?, ?);";
		
		db.prepareStatement(sqlInsert);
		db.setString(1, supplier.getName());
		db.setString(2, supplier.getEmail());
		db.setString(3, supplier.getFone());
		db.setString(4, supplier.getAdress());
		db.setInt(5, supplier.getCompany().getId());
		
		return db.executeUpdate() > 0;
	}

	@Override
	public boolean update(Supplier supplier) throws ModelException {
		DBHandler db = new DBHandler();
		
		String sqlUpdate = "UPDATE suppliers "
							+ "SET name = ?, "
							+ "email = ?, "
							+ "fone = ?, "
							+ "adress = ?, "
							+ "company_id = ? "
							+ "WHERE id = ?";
				
		
		db.prepareStatement(sqlUpdate);
		
		db.setString(1, supplier.getName());
		db.setString(2, supplier.getEmail());
		db.setString(3, supplier.getFone());
		db.setString(4, supplier.getAdress());
		db.setInt(5, supplier.getCompany().getId());
		db.setInt(6, supplier.getId());
		
		return db.executeUpdate() > 0;
	}

	@Override
	public boolean delete(Supplier supplier) throws ModelException {
		DBHandler db = new DBHandler();
		
		String sqlDelete = "DELETE from suppliers "
							+ "WHERE id = ?;";
		
		db.prepareStatement(sqlDelete);
		db.setInt(1, supplier.getId());
		
		return db.executeUpdate() > 0;
	}

	@Override
	public List<Supplier> listAll() throws ModelException {
		DBHandler db = new DBHandler();
		
		List<Supplier> suppliers = new ArrayList<Supplier>();
		
		String sqlQuery = "SELECT * FROM suppliers";
		
		db.createStatement();
		
		db.executeQuery(sqlQuery);
		
		while (db.next()) {
			Supplier s = createSupplier(db);
			suppliers.add(s);
		}
		
		return suppliers;
	}

	@Override
	public Supplier findById(int id) throws ModelException {
		DBHandler db = new DBHandler();
		
		String sqlQuery = "SELECT * FROM suppliers WHERE id = ?;";
		
		db.prepareStatement(sqlQuery);
		db.setInt(1, id);
		
		db.executeQuery();
		
		Supplier s = null;
		while(db.next()) {
			s = createSupplier(db);
			break;
		}
		
		return s;
	}
	
	private Supplier createSupplier(DBHandler db) throws ModelException {
		Supplier s = new Supplier(db.getInt("id"));
		s.setName(db.getString("name"));
		s.setEmail(db.getString("email"));
		s.setFone(db.getString("fone"));
		s.setAdress(db.getString("adress"));
		CompanyDAO companyDAO = DAOFactory.createDAO(CompanyDAO.class);
		Company c = companyDAO.findById(db.getInt("company_id"));
		s.setCompany(c);
		
		return s;
	}

}
