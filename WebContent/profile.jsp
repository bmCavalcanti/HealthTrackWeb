<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="header.jsp" %>
	<title>Perfil | Health Track</title>
</head>
<body>
    <div class="body-adjust pb-5 ">
        <div class="d-flex align-items-center justify-content-center">
            <div class="overlay-box">
            	<c:if test="${not empty user.photo}">
            		<img src="${user.photo}" alt="Foto de perfil de ${user.name}" class="d-inline-block user-img rounded-circle " width="100"/>
            	</c:if>
            	<c:if test="${empty user.photo}">
            		<img src="./assets/images/userplaceholder.png" alt="Foto de perfil de ${user.name}" class="d-inline-block user-img rounded-circle " width="100"/>
            	</c:if>
                
                <div class="overlay">
                    <a href="#" title="Alterar foto de perfil" class="add-profile-pic">
                        <i class="fa fa-pencil-alt overlay-icon"></i>
                    </a>
                </div>
            </div>
            
            <span class="ml-3">
                <h4>
                    ${user.name}
                </h4>
                <span>
                    ${user.age} anos
                </span>
            </span>
        </div>
        
        <c:if test="${ not empty message }">           
		    <div class="my-4 alert alert-${ type }">
		        ${ message }
		        <a title="Fechar" href="#" class="close-message text-dark float-right">
		            <i class="fa fa-times"></i>
		        </a>
		    </div>
	    </c:if>

        <form action="profile" method="POST" autocomplete="off">
            <div class="pt-3">
                <div class="form-group">
                    <label>Nome</label>
                    <input type="text" required name="name" class="form-control" placeholder="Informe seu nome completo" data-rule-fullname="true" minlength="3" value="${user.name}">
                </div>

                <div class="form-group">
                    <label>E-mail</label>
                    <input type="email" required name="email" class="form-control" placeholder="Informe seu e-mail" minlength="6" value="${user.email}">
                </div>

                <div class="form-group">
                    <label>Altura</label>
                    <input type="text" required name="height" class="form-control meter" placeholder="Informe sua altura" data-rule-number="true" max="3" min="0.5" value="${user.height}">
                </div>
                
                <div class="form-group">
                    <label>Gênero</label>
                    <select name="gender" required class="form-control">
	                    <c:if test="${ user.gender.id == 1 }">	
	                        <option value="1" selected>Feminino</option>
	                        <option value="2">Masculino</option>
	                    </c:if>
	                    <c:if test="${ user.gender.id == 2 }">	
	                        <option value="1">Feminino</option>
	                        <option value="2" selected>Masculino</option>
	                    </c:if>
                    </select>
                </div>

                <div class="form-group">
                    <label>Data de nascimento</label>
                    <input type="date" required name="birthday" class="form-control" value="<fmt:formatDate value="${user.birthday.time}" pattern="yyyy-MM-dd"/>">
                </div>

                <h6>Alteração de senha</h6>
                <small>
                    Se não deseja alterar a senha deixe em branco
                </small>
                <div class="form-group">
                    <label>Senha atual</label>
                    <input type="password" name="old_password" class="form-control" placeholder="Digite sua senha atual" id="old_password" minlength="6">
                </div>

                <div class="form-group">
                    <label>Nova senha</label>
                    <input type="password" name="password" class="form-control" placeholder="Crie uma senha" id="password" minlength="6">
                </div>

                <div class="form-group">
                    <label>Confirme a nova senha</label>
                    <input type="password" name="password_confirm" class="form-control" placeholder="Confirme sua senha" data-rule-equalto="#password" id="password_confirm" minlength="6">
                </div>

                <input type="file" name="profile_pic" hidden class="input-profile-pic">

                <button type="submit" class="btn btn-primary btn-block">
                    Salvar alterações
                </button>
            </div>
        </form>
    </div>

	<%@ include file="menu.jsp" %>
	<%@ include file="footer.jsp" %>
</body>
</html>