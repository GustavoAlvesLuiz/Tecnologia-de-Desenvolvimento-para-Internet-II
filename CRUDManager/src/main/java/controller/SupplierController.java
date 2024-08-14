package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Company;
import model.ModelException;
import model.Seller;
import model.Supplier;
import model.dao.CompanyDAO;
import model.dao.DAOFactory;
import model.dao.SellerDAO;
import model.dao.SupplierDAO;


@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/suppliers", "/supplier/form", "/supplier/insert", "/supplier/update", "/supplier/delete"})
public class SupplierController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getRequestURI();
		
		switch (action) {
		case "/crud-manager/supplier/form": {
			loadCompanies(req);
			req.setAttribute("action", "insert");
			ControllerUtil.forward(req, resp, "/form-supplier.jsp");
			break;
		}
		case "/crud-manager/supplier/update": {
			loadSupplier(req);
			loadCompanies(req);
			req.setAttribute("action", "update");
			ControllerUtil.forward(req, resp, "/form-supplier.jsp");
			break;
		}
		default:
			listSuppliers(req);
			
			ControllerUtil.transferSessionMessagesToRequest(req);
			
			ControllerUtil.forward(req, resp, "/suppliers.jsp");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getRequestURI();
		
		switch (action) {
		case "/crud-manager/supplier/insert": {
			insertSupplier(req);
			ControllerUtil.redirect(resp, req.getContextPath() + "/suppliers");
			break;
		}
		case "/crud-manager/supplier/update": {
			updateSupplier(req);
			ControllerUtil.redirect(resp, req.getContextPath() + "/suppliers");
			break;
		}
		case "/crud-manager/supplier/delete": {
			String supplierIdStr = req.getParameter("id");
			String supplierName = req.getParameter("entityName");
			int supplierId = Integer.parseInt(supplierIdStr);
			
			SupplierDAO dao = DAOFactory.createDAO(SupplierDAO.class);
			
			try {
				if (dao.delete(new Supplier(supplierId))) {
					ControllerUtil.sucessMessage(req, "Fornecedor " + supplierName + " excluido com sucesso.");
				} else {
					ControllerUtil.errorMessage(req, "Fornecedor " + supplierName + " não pode ser excluido.");
				}
			} catch (ModelException e) {
				ControllerUtil.errorMessage(req, "Erro ao excluir fornecedor");
			}finally {
				ControllerUtil.redirect(resp, req.getContextPath() + "/suppliers");
			}
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + action);
		}
	}
	
	private void insertSupplier(HttpServletRequest req) {
		Supplier supplier = createSupplier(req, 0);
		
		SupplierDAO dao = DAOFactory.createDAO(SupplierDAO.class);
		
		try {
			if(dao.save(supplier)) {
				ControllerUtil.sucessMessage(req, "Fornecedor " + supplier.getName() + " salvo com sucesso.");
			} else {
				ControllerUtil.errorMessage(req, "Fornecedor " + supplier.getName() + " não pode ser salvo.");
			}
		} catch (ModelException e) {
			ControllerUtil.errorMessage(req, "Erro ao salvar dados do fornecedor");
		}
	}
	
	private void updateSupplier(HttpServletRequest req) {
		String supplierIdStr = req.getParameter("supplierId");
		int supplierId = Integer.parseInt(supplierIdStr);
		Supplier supplier = createSupplier(req, supplierId);
		
		SupplierDAO dao = DAOFactory.createDAO(SupplierDAO.class);
		
		try {
			if(dao.update(supplier)) {
				ControllerUtil.sucessMessage(req, "Fornecedor " + supplier.getName() + " alterado com sucesso.");
			} else {
				ControllerUtil.errorMessage(req, "Fornecedor " + supplier.getName() + " não pode ser alterado.");
			}  					
		} catch (ModelException e) {
			e.printStackTrace();
			ControllerUtil.errorMessage(req, "Erro ao alterar dados do fornecedor");
		}
	}
	
	private Supplier createSupplier(HttpServletRequest req, int supplierId) {
		String supplierName = req.getParameter("supplier_name");
		String supplierEmail = req.getParameter("supplier_email");
		String supplierFone = req.getParameter("supplier_fone");
		String supplierAdress = req.getParameter("supplier_adress");
		String supplierCompany = req.getParameter("supplier_company");
		int supplierCompanyId = Integer.parseInt(supplierCompany);
		Supplier supplier;
		
		if(supplierId == 0) {
			supplier = new Supplier();
		}else {
			supplier = new Supplier(supplierId);
		}
		
		supplier.setName(supplierName);
		supplier.setEmail(supplierEmail);
		supplier.setFone(supplierFone);
		supplier.setAdress(supplierAdress);
		supplier.setCompany(new Company(supplierCompanyId));
		return supplier;
	}
	
	private void loadCompanies(HttpServletRequest req) {
		List<Company> companies = new ArrayList<>();
		CompanyDAO dao = DAOFactory.createDAO(CompanyDAO.class);
		try {
			companies = dao.listAll();
		} catch (ModelException e) {
			ControllerUtil.errorMessage(req, "Erro ao carregar dados das empresas");
			e.printStackTrace();
		}
		
		req.setAttribute("companies", companies);
	}
	
	private void listSuppliers(HttpServletRequest req) {
		SupplierDAO dao = DAOFactory.createDAO(SupplierDAO.class);
		
		List<Supplier> suppliers = new ArrayList<>();
		
		try {
			suppliers = dao.listAll();
		} catch(ModelException e) {
			ControllerUtil.errorMessage(req, "Erro ao carregar dados dos fornecedores");
		}
		
		req.setAttribute("suppliers", suppliers);
	}
	
	private void loadSupplier(HttpServletRequest req) {
		String supplierIdStr = req.getParameter("supplierId");
		int supplierId = Integer.parseInt(supplierIdStr);
		
		SupplierDAO dao = DAOFactory.createDAO(SupplierDAO.class);
		Supplier supplier = new Supplier();
		
		try {
			supplier = dao.findById(supplierId);
		} catch (ModelException e) {
			ControllerUtil.errorMessage(req, e.getMessage());
			ControllerUtil.errorMessage(req, "Erro ao carregar dados do fornecedor");
		}
		
		req.setAttribute("supplierEdit", supplier);
	}
}
