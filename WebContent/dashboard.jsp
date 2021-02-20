<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="header.jsp" %>
	<title>Dashboard | Health Track</title>
</head>
<body>
	<div class="body-adjust pb-5 ">
	    <div class="d-flex align-items-center justify-content-center">
	        <c:if test="${not empty user.photo}">
           		<img src="${user.photo}" alt="Foto de perfil de ${user.name}" class="d-inline-block user-img rounded-circle " width="100"/>
           	</c:if>
           	<c:if test="${empty user.photo}">
           		<img src="./assets/images/userplaceholder.png" alt="Foto de perfil de ${user.name}" class="d-inline-block user-img rounded-circle " width="100"/>
           	</c:if>
	        <span class="ml-3">
	            <h4>
	                ${user.name}
	            </h4>
	            <span>
	                ${user.age} anos
	            </span>
	        </span>
	    </div>
	
	    <div>
	        <ol class="imc-bar mt-5 px-2" data-weight="${wValue}" data-height="${user.height}">
	        	<li id="imc-1"></li>
	        	<li id="imc-2"></li>
	        	<li id="imc-3"></li>
	        	<li id="imc-4"></li>
	        	<li id="imc-5"></li>
	        	<li id="imc-6"></li>
	        	<li id="imc-7"></li>
	        	<li id="imc-8"></li>
	        </ol>
	        <div id="imc-name" class="text-center mt-2">
	            Peso ideal
	        </div>
	    </div>
	
	    <ul class="nav flex-column mt-4">
	        <li>
	        	<c:if test="${ not empty ftName }">
		            <a href="./food" title="Ver alimentação" class="nav-link d-flex align-items-center justify-content-between py-3">
		                <i class="fa fa-utensils mr-3 fa-2x"></i> 
	                	${ftName} ● 
	                	<fmt:formatDate dateStyle="long" timeStyle="long" value="${fCreatedAt.time}" />
	                	<i class="fa fa-angle-right ml-3"></i>
		            </a>
	        	</c:if>
	        	<c:if test="${ empty ftName }">
		            <a href="./food" title="Ver alimentação" class="nav-link d-flex align-items-center justify-content-between py-3">
		                <i class="fa fa-utensils mr-3 fa-2x"></i> Alimentação <i class="fa fa-angle-right ml-3"></i>
		            </a>
	        	</c:if>
	        </li>
	        <li>
	        	<c:if test="${ not empty patName }">
		            <a href="./physicalactivity" title="Ver atividades físicas" class="nav-link d-flex align-items-center justify-content-between py-3">
		                <i class="fa fa-walking mr-3 fa-2x"></i> 
		                ${patName} ● 
		                <fmt:formatDate dateStyle="long" timeStyle="long" value="${paCreatedAt.time}" />  
		                <i class="fa fa-angle-right ml-3"></i>
		            </a>
	        	</c:if>
	        	<c:if test="${ empty patName }">
		            <a href="./physicalactivity" title="Ver atividades físicas" class="nav-link d-flex align-items-center justify-content-between py-3">
		                <i class="fa fa-walking mr-3 fa-2x"></i> Atividade física <i class="fa fa-angle-right ml-3"></i>
		            </a>
	        	</c:if>
	        </li>
	        <li>
	        	<c:if test="${ not empty wValue }">
		            <a href="./weight" title="Ver peso" class="nav-link d-flex align-items-center justify-content-between py-3">
		                <i class="fa fa-weight mr-3 fa-2x"></i> 
		                ${wValue}kg ● 
		                <fmt:formatDate dateStyle="long" timeStyle="long" value="${wCreatedAt.time}" /> 
		                <i class="fa fa-angle-right ml-3"></i>
		            </a>
	        	</c:if>
	        	<c:if test="${ empty wValue }">
		            <a href="./weight" title="Ver peso" class="nav-link d-flex align-items-center justify-content-between py-3">
		                <i class="fa fa-weight mr-3 fa-2x"></i> Peso <i class="fa fa-angle-right ml-3"></i>
		            </a>
	        	</c:if>
	        </li>
	        <li>
	        	<c:if test="${ not empty bpValue }">
		            <a href="./bloodpressure" title="Ver pressão arterial" class="nav-link d-flex align-items-center justify-content-between py-3">
		                <i class="fa fa-ruler-horizontal mr-3 fa-2x"></i> 
		                ${bpValue} mmHg ● 
		                <fmt:formatDate dateStyle="long" timeStyle="long" value="${bpCreatedAt.time}" /> ● 
		                ${bpNivel}
		                <i class="fa fa-angle-right ml-3"></i>
		            </a>
	        	</c:if>
	        	<c:if test="${ empty bpValue }">
		            <a href="./bloodpressure" title="Ver pressão arterial" class="nav-link d-flex align-items-center justify-content-between py-3">
		                <i class="fa fa-ruler-horizontal mr-3 fa-2x"></i> Pressão arterial <i class="fa fa-angle-right ml-3"></i>
		            </a>
	        	</c:if>
	        </li>
	    </ul>
	</div>
	<%@ include file="menu.jsp" %>
	<%@ include file="footer.jsp" %>
</body>
</html>