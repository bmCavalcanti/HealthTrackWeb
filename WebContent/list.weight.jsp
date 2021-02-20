<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="header.jsp" %>
	<title>Listagem de pesos | Health Track</title>
</head>
<body>
    <nav class="fixed-top nav-top d-flex align-items-center justify-content-between">
        <a title="Voltar para a dashboard" href="./dashboard" class="mx-3">
            <i class="fa fa-angle-left fa-2x"></i>
        </a>
        <h3><a href="">Peso</a></h3>
        <a title="Adicionar peso" data-toggle="modal" data-target="#modalCrud" href="" class="mr-3" onclick="crud_form.reset(); crud_action.value ='insert'">
            <i class="fa fa-plus fa-2x"></i>
        </a>
    </nav>

    <div class="body-adjust pb-5">
        <div class="pb-3 mb-5">
            <!--<nav class="nav-top d-flex bg-transparent align-items-center justify-content-between mb-3">
                <a href="">
                    <i class="fa fa-angle-left fa-2x"></i>
                </a>
                <h4>2020</h4>
                <a href="">
                    <i class="fa fa-angle-right fa-2x"></i>
                </a>
            </nav>-->
            <div class="text-center">
            	<h4>${chart_year}</h4>
            </div>
            <canvas id="chart-weight" data-labels='${chart_labels}' data-values='${chart_data}'>
            </canvas>
        </div>

        <div class="bg-dark marker-left">
            <h6>
                Peso recomendado: <span id="recomended-weight" data-height="${user.height}" data-gender="${user.gender.id}"></span> kg
            </h6>           
        </div>
                
		<c:if test="${ not empty message }">           
            <div class="my-4 alert alert-${ type }">
                ${ message }
                <a title="Fechar" href="#" class="close-message text-dark float-right">
                    <i class="fa fa-times"></i>
                </a>
            </div>
        </c:if>

        <div class="table-responsive mt-4">
            <table class="table">
            	<thead>
            		<tr>
            			<th></th>
            			<th></th>
            		</tr>
            	</thead>
                <tbody>
                	<c:forEach items="${weights}" var="w">
	                	<tr>
	                        <td data-sort="<fmt:formatDate value="${w.createdAt.time}" pattern="yyyy-MM-dd"/>">
	                            <small>
	                            	<fmt:formatDate dateStyle="long" timeStyle="long" value="${w.createdAt.time}" />
                            	</small>
	                            <br>
	                            ${w.value} kg
	                        </td>
	                        <td class="text-right">
	                            <div class="btn-group" role="group">
	                                <a title="Editar peso" data-toggle="modal" data-target="#modalCrud" role="button" class="ml-3 btn-action" onclick="
	                                	crud_form.reset(); 
	                                	crud_action.value = 'update'; 
	                                	value.value = '${w.value}'; 
	                                	created_at.value = '<fmt:formatDate value="${w.createdAt.time}" pattern="yyyy-MM-dd"/>'; 
	                                	crud_id.value = '${w.id}'
	                                ">
	                                    <i class="fa fa-pencil-alt"></i>
	                                </a>
	                                <a title="Deletar peso" data-toggle="modal" data-target="#modalConfirmDelete" href="" role="button" class="ml-3 btn-action" onclick="delete_id.value = ${w.id}; delete_form.action = 'weight'">
	                                    <i class="far fa-trash-alt"></i>
	                                </a>
	                            </div>
	                        </td>
	                    </tr>
                	</c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <div class="modal" id="modalCrud" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Gestão de peso</h5>
                    <button type="button" class="close btn-action" data-dismiss="modal">
                        <span>&times;</span>
                    </button>
                </div>
                <form action="weight" method="POST" autocomplete="off" id="crud_form">
                	<input type="hidden" name="action" value="" id="crud_action">
                	<input type="hidden" name="id" value="" id="crud_id">
                    <div class="modal-body">
                        <div class="form-group">
                            <label>Peso (kg)</label>
                            <input type="text" required name="value" id="value" class="form-control kg" placeholder="Informe seu peso (kg)" data-rule-number="true" max="900" min="10">
                        </div>
                        <div class="form-group">
                            <label>Data da pesagem</label>
                            <input type="date" required name="created_at" id="created_at" class="form-control">
                        </div>
                    </div>
                    <div class="modal-footer">
                        <div class="btn-group btn-group-toggle w-100" data-toggle="buttons">
                            <button data-dismiss="modal" type="button" title="Fechar" class="btn btn-outline-primary">
                                Fechar
                            </button>
                            <button type="submit" title="Salvar" class="btn btn-primary">
                                Salvar
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

	<%@ include file="menu.jsp" %>
	<%@ include file="footer.jsp" %>
</body>
</html>