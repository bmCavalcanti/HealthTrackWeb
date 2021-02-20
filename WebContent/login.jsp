<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="header.jsp" %>
	<title>Login | Health Track</title>
</head>
<body>
    <div class="container px-lg-5 pb-5">
        <div class="col-12 col-md-8 col-lg-6 mx-auto mt-5">
            <div class="text-center">
                <a title="Health Track" href="./index.jsp">
                    <img alt="Logo Health Track" src="./assets/images/logos/logotipo.png" class="w-100 mb-4">
                </a>
            </div>

            <a title="Acesso ao painel" href="#" class="nav-link btn-outline-dark h5 active rounded text-center">
                <span class="badge badge-pill bg-primary">
                    <i class="fa fa-angle-right"></i>
                </span>
                Acesso ao painel
            </a>
            
	  		<c:if test="${ not empty message }">           
			    <div class="my-4 alert alert-${ type }">
			        ${ message }
			        <a title="Fechar" href="#" class="close-message text-dark float-right">
			            <i class="fa fa-times"></i>
			        </a>
			    </div>
		    </c:if>

            <form action="login" method="POST" autocomplete="off">
                <div class="py-3">
                    <div class="form-group">
                        <label>E-mail</label>
                        <input type="email" required name="email" class="form-control" value="${email}" placeholder="Informe seu e-mail" minlength="6">
                    </div>

                    <div class="form-group">
                        <label>Senha</label>
                        <input type="password" required name="password" class="form-control" placeholder="Digite a sua senha">
                    </div>

                    <button type="submit" title="Entrar" class="btn btn-primary btn-block mt-3 next">
                        Entrar
                    </button>
                </div>
            </form>
            <span>
                Ainda não possui uma conta?
                <a title="Cadastre-se" href="./register">
                    Cadastre-se
                </a>
            </span>
        </div>
    </div>
	<%@ include file="footer.jsp" %>
</body>
</html>