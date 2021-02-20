<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="header.jsp" %>
	<title>Cadastro | Health Track</title>
</head>
<body>
    <div class="container px-lg-5 pb-5">
        <div class="col-12 col-md-8 col-lg-6 mx-auto mt-5">
            <div class="text-center">
                <a title="Health Track" href="./index.jsp">
                    <img alt="Logo Health Track" src="./assets/images/logos/logotipo.png" class="w-100 mb-4">
                </a>
            </div>
            
            <c:if test="${ not empty message }">           
			    <div class="my-4 alert alert-${ type }">
			        ${ message }
			        <a title="Fechar" href="#" class="close-message text-dark float-right">
			            <i class="fa fa-times"></i>
			        </a>
			    </div>
		    </c:if>

            <ul class="nav nav-fill nav-pills">
                <li class="nav-item mr-1">
                    <a title="Dados pessoais" class="nav-link btn-outline-dark changeStep active h5" href="#user" step="1">
                        <span class="badge badge-pill bg-primary">1</span>
                        Dados pessoais
                    </a>
                </li>
                <li class="nav-item">
                    <a title="Informações" class="nav-link btn-outline-dark changeStep h5" href="#info" step="2">
                        <span class="badge badge-pill bg-primary">2</span>
                        Informações
                    </a>
                </li>
            </ul>
            <form action="register" method="POST" autocomplete="off">
            	<input type="hidden" name="action" value="insert">
                <div class="step show py-3" id="user" step="1">
                    <div class="form-group">
                        <label>Nome</label>
                        <input type="text" required name="name" class="form-control" placeholder="Informe seu nome completo" data-rule-fullname="true" minlength="3">
                    </div>

                    <div class="form-group">
                        <label>E-mail</label>
                        <input type="email" required name="email" class="form-control" placeholder="Informe seu e-mail" minlength="6">
                    </div>

                    <div class="form-group">
                        <label>Senha</label>
                        <input type="password" required name="password" class="form-control" placeholder="Crie uma senha" id="password" minlength="6">
                    </div>

                    <div class="form-group">
                        <label>Confirme a senha</label>
                        <input type="password" required name="password_confirm" class="form-control" placeholder="Confirme sua senha" data-rule-equalto="#password" minlength="6">
                    </div>
                    <a title="Próximo" class="changeStep btn btn-primary btn-block mt-3 next" href="#info" step="2">
                        Próximo
                    </a>
                </div>
                <div class="step py-3" id="info" style="display:none" step="2">
                    <div class="form-group">
                        <label>Altura</label>
                        <input type="text" required name="height" class="form-control meter" placeholder="Informe sua altura" data-rule-number="true" max="3" min="0.5">
                    </div>

                    <div class="form-group">
                        <label>Peso (kg)</label>
                        <input type="text" required name="weight" class="form-control kg" placeholder="Informe seu peso (kg)" data-rule-number="true" max="900" min="10">
                    </div>

                    <div class="form-group">
                        <label>Gênero</label>
                        <select name="gender" required class="form-control">
                            <option value="" selected disabled>Selecione uma opção</option>
                            <option value="1">Feminino</option>
                            <option value="2">Masculino</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label>Data de nascimento</label>
                        <input type="date" required name="birthday" class="form-control">
                    </div>

                    <div class="btn-group btn-group-toggle w-100" data-toggle="buttons">
                        <a title="Voltar" class="changeStep btn btn-outline-primary next" href="#user" step="1">
                            Voltar
                        </a>
                        <button type="submit" class="btn btn-primary btn-block">
                            Finalizar
                        </button>
                    </div>
                </div>
            </form>
            <span>
                Já possui uma conta?
                <a title="Acesse o painel" href="./login">
                    Acesse o painel
                </a>
            </span>
        </div>
    </div>
	<%@ include file="footer.jsp" %>
</body>
</html>