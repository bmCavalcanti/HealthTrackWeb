<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="header.jsp" %>
	<title>Atividade física | Health Track</title>
</head>
<body>
    <nav class="fixed-top nav-top d-flex align-items-center justify-content-between">
        <a title="Voltar para a dashboard" href="./dashboard" class="mx-3">
            <i class="fa fa-angle-left fa-2x"></i>
        </a>
        <h3><a href="">Atividade física</a></h3>
        <a title="Adicionar atividade física" data-toggle="modal" data-target="#modalCrud" href="" class="mr-3" onclick="crud_form.reset(); crud_action.value ='insert'">
            <i class="fa fa-plus fa-2x"></i>
        </a>
    </nav>

    <div class="body-adjust pb-5">

        <div class="bg-dark marker-left">
            <h6>
                Estatísticas de hoje
            </h6>
            <div>
                Tempo em movimento: ${today_time} minutos
                <br>
                Calorias gastas: ${today_calories} Cal
            </div>
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
            		</tr>
            	</thead>
                <tbody>
                	<c:forEach items="${physical_activities}" var="pam" varStatus="loop">
	                	<tr>
	                        <td data-sort="<fmt:formatDate value="${pam.key.time}" pattern="yyyy-MM-dd"/>">
	                            <div>
	                                <a title="Ver atividades" data-toggle="collapse" href="#activity-box-${loop.index}" role="button" aria-expanded="false">
	                                    <span class="h5">
	                                    	<i class="fa fa-angle-right"></i>
	                                    	<fmt:formatDate dateStyle="long" timeStyle="long" value="${pam.key.time}" /> 
										</span>
	                                </a>
	                            </div>
	                            <div class="mt-3 collapse" id="activity-box-${loop.index}">
	                                <ul>
		                               <c:forEach items="${pam.value}" var="pa">
			                               <li>
		                                        ${pa.physicalActivityType.name} (${pa.duration})
		                                        <div class="btn-group" role="group">
		                                            <a title="Editar atividade física" data-toggle="modal" data-target="#modalCrud" href="" role="button" class="ml-3 btn-action" onclick="
														crud_form.reset(); 
														crud_action.value = 'update'; 
														physical_activity_type.value = '${pa.physicalActivityType.id}';
														$('#physical_activity_type').change(); 
														created_at.value = '<fmt:formatDate value="${pa.createdAt.time}" pattern="yyyy-MM-dd"/>';
														duration.value = '${pa.duration}';
														calories.value = '${pa.calories}';
														description.value = '${pa.description}';
														crud_id.value = '${pa.id}'
													">
		                                                <i class="fa fa-pencil-alt"></i>
		                                            </a>
		                                            <a title="Deletar atividade física" data-toggle="modal" data-target="#modalConfirmDelete" href="" role="button" class="ml-3 btn-action" onclick="delete_id.value = ${pa.id}; delete_form.action = 'physicalactivity'">
					                                    <i class="far fa-trash-alt"></i>
					                                </a>
		                                        </div>
		                                    </li>
		                               </c:forEach>
	                                </ul>
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
                    <h5 class="modal-title">Gestão de atividade física</h5>
                    <button type="button" class="close btn-action" data-dismiss="modal">
                        <span>&times;</span>
                    </button>
                </div>
                <form action="physicalactivity" method="POST" autocomplete="off" id="crud_form">
                	<input type="hidden" name="action" value="" id="crud_action">
                	<input type="hidden" name="id" value="" id="crud_id">
                    <div class="modal-body">
                        <div class="form-group">
                            <label>Tipo</label>
                            <select required name="physical_activity_type" id="physical_activity_type" class="form-control">
                                <option value="" selected disabled>Selecione uma opção</option>
                                <c:forEach items="${physical_activity_types}" var="pat">
                                	<option value="${pat.id}">${pat.name}</option>	
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>Data da atividade</label>
                            <input type="date" required name="created_at" id="created_at" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Duração (hh:mm)</label>
                            <input required type="time" name="duration" id="duration" class="form-control time">
                        </div>
                        <div class="form-group">
                            <label>Calorias gastas</label>
                            <input required type="text" name="calories" id="calories" class="form-control number">
                        </div>
                        <div class="form-group">
                            <label>Descrição</label>
                            <input type="text" name="description" id="description" class="form-control">
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