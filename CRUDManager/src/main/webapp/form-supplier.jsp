<jsp:directive.page contentType="text/html; charset=UTF-8" />
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<%@include file="base-head.jsp"%>
		<meta charset="UTF-8">
		<title>CRUD MANAGER - ${action eq "insert" ? "ADICIONAR " : "EDITAR "} FORNECEDOR</title>
	</head>
	<body>
		<%@include file="nav-menu.jsp"%>
		
		<div id="container" class="container-fluid">
			<h3 class="page-header">${action eq "insert" ? "Adicionar " : "Editar "} Fornecedor</h3>
			
			<form action="${pageContext.request.contextPath}/supplier/${action}" method="POST">
				<input type="hidden" value="${supplierEdit.getId()}" name="supplierId">
				
				<div class="row">
					<div class="form-group col-md-6">
						<label for="supplier_name">Nome</label>
							<input type="text" class="form-control" id="supplier_name" name="supplier_name" 
							  autofocus="autofocus" placeholder="Nome do Fornecedor" 
							  required oninvalid="this.setCustomValidity('Por favor, informe o nome do fornecedor.')"
							  oninput="setCustomValidity('')"
							  value="${supplierEdit.getName()}"
							  >
					</div>
					
					
					
					<div class="form-group col-md-6">
						<label for="supplier_email">Email</label>
							<input type="email" class="form-control" id="supplier_email" name="supplier_email" 
							   autofocus="autofocus" placeholder="Email do Fornecedor" 
							   required oninvalid="this.setCustomValidity('Por favor, informe o email do fornecedor.')"
							   oninput="setCustomValidity('')"
							   value="${supplierEdit.getEmail()}"
							   >
					</div>
					
					
				
					<div class="form-group col-md-6">
						<label for="supplier_fone">Telefone</label>
							<input type="number" class="form-control" id="supplier_fone" name="supplier_fone" 
								  autofocus="autofocus" placeholder="Telefone do Fornecedor" 
								  required oninvalid="this.setCustomValidity('Por favor, informe o telefone do fornecedor.')"
								  oninput="setCustomValidity('')"
								  value="${supplierEdit.getFone()}"
								  >
					</div>
					
					<div class="form-group col-md-6">
						<label for="supplier_adress">Endereço</label>
							<input type="text" class="form-control" id="supplier_adress" name="supplier_adress" 
								  autofocus="autofocus" placeholder="Endereço do Fornecedor" 
								  required oninvalid="this.setCustomValidity('Por favor, informe o endereço do fornecedor.')"
								  oninput="setCustomValidity('')"
								  value="${supplierEdit.getAdress()}"
								  >
					</div>
					
					<div class="form-group col-md-6">
							<label for="supplier_company">Empresa</label>
							<select id="supplier_company" class="form-control selectpicker" name="supplier_company" 
								    required oninvalid="this.setCustomValidity('Por favor, informe a empresa.')"
								    oninput="setCustomValidity('')">
							  <option value="" disabled ${not empty supplierEdit ? "" : "selected"}>Selecione uma empresa</option>
							  <c:forEach var="company" items="${companies}">
							  	<option value="${company.getId()}" 
							  		${supplierEdit.getCompany().getId() eq company.getId() 
							  		? "selected" : ""}>
							  		${company.getName()}
							  	</option>	
							  </c:forEach>
							</select>
					</div>
				</div>
				<hr />
				<div id="actions" class="row pull-right">
					<div class="col-md-12">
						<a href="${pageContext.request.contextPath}/suppliers" class="btn btn-default">Cancelar</a>
						<button type="submit" class="btn btn-primary">${action eq "insert" ? "Criar " : "Editar "} Fornecedor</button>
					</div>
				</div>
			</form>
			
		</div>
		<script src="js/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script type="text/javascript">
		 $(document).ready(function()) {
			 setTimeout(function() {
			 	$("#alert").slideUp(500);
			 }, 5000);
			 
			 
		 });
		</script>
	</body>
</html>