<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="header.jsp" %>
	<title>Alimentação | Health Track</title>
</head>
<body>
	<nav class="fixed-top nav-top d-flex align-items-center justify-content-between">
        <a title="Voltar para a dashboard" href="./dashboard" class="mx-3">
            <i class="fa fa-angle-left fa-2x"></i>
        </a>
        <h3><a href="">Alimentação</a></h3>
        <a title="Adicionar refeição" data-toggle="modal" data-target="#modalInsert" href="" class="mr-3">
            <i class="fa fa-plus fa-2x"></i>
        </a>
    </nav>

    <div class="body-adjust pb-5">
        <div class="bg-dark marker-left">
            <h6>
                Consumo diário recomendado: 
                <span id="recomended-calories" data-height="${user.height}" data-weight="${weight}" data-age="${user.age}" data-gender="${user.gender.id}"></span> 
                Cal por dia
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
            		</tr>
            	</thead>
                <tbody>
                	<c:forEach items="${foods}" var="fm" varStatus="loop">
                		<tr>
	                        <td data-sort="<fmt:formatDate value="${fm.key.time}" pattern="yyyy-MM-dd"/>">
	                            <div>
	                                <a title="Ver alimentos" data-toggle="collapse" href="#food-box-${loop.index}" role="button" aria-expanded="false">
	                                    <span class="h5">
	                                    	<i class="fa fa-angle-right"></i>
	                                    	
	                                    	<fmt:formatDate dateStyle="long" timeStyle="long" value="${fm.key.time}" />
                                    	</span>
	                                </a>
	                            </div>                           
	                            <div class="pl-3 mt-3 collapse" id="food-box-${loop.index}">
			                        <c:forEach items="${fm.value}" var="f">
	                            		<span>
		                                    ${f.foodType.name} (${ f.totalCalories } Cal)
		                                    
		                                    <div class="btn-group" role="group">
		                                        <a title="Editar refeição" data-toggle="modal" data-target="#modalUpdate" href="" role="button" class="ml-3 btn-action" onclick="
														crud_form.reset(); 
														crud_action.value = 'update'; 
														food_type.value = '${f.foodType.id}';
														$('#food_type').change(); 
														created_at.value = '<fmt:formatDate value="${f.createdAt.time}" pattern="yyyy-MM-dd"/>';
														description.value = '${f.description}';
														crud_id.value = '${f.id}'
													">
		                                            <i class="fa fa-pencil-alt"></i>
		                                        </a>
				                                <a title="Deletar refeição" data-toggle="modal" data-target="#modalConfirmDelete" href="" role="button" class="ml-3 btn-action" onclick="delete_id.value = ${f.id}; delete_form.action = 'food'; delete_action.value = 'delete'">
				                                    <i class="far fa-trash-alt"></i>
				                                </a>
				                                <a title="Adicionar novo alimento" data-toggle="modal" data-target="#modalUpdateItem" href="" role="button" class="ml-3 btn-action" onclick="
													crud_form_item.reset(); 
													crud_action_item.value = 'insert_item';
													food_id_item.value = '${f.id}';
												">
		                                            <i class="fa fa-plus"></i>
		                                        </a>
		                                    </div>
		                                </span> 
		                                <ul>
		                                	<c:forEach items="${f.foodItems}" var="fi">
		                                    	<li>
		                                    		${fi.name} (${fi.calories} Cal)
		                                    		<div class="btn-group" role="group">
				                                        <a title="Editar item" data-toggle="modal" data-target="#modalUpdateItem" href="" role="button" class="btn-action btn-sm" onclick="
															crud_form_item.reset(); 
															crud_action_item.value = 'update_item';
															name_item.value = '${fi.name}';
															calories_item.value = '${fi.calories}';
															food_id_item.value = '${fi.food.id}';
															crud_id_item.value = '${fi.id}'
														">
				                                            <i class="fa fa-pencil-alt"></i>
				                                        </a>
				                                        <a title="Remover item" data-toggle="modal" data-target="#modalConfirmDelete" href="" role="button" class="btn-action btn-sm" onclick="delete_id.value = ${fi.id}; delete_form.action = 'food'; delete_action.value = 'delete_item'">
				                                            <i class="far fa-trash-alt"></i>
				                                        </a>
				                                    </div>
		                                    	</li>
		                                    </c:forEach>
		                                </ul>
		                            </c:forEach>
	                            </div>
	                        </td>
	                    </tr>
                	</c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <div class="modal" id="modalInsert" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Gestão de refeição</h5>
                    <button type="button" class="close btn-action" data-dismiss="modal">
                        <span>&times;</span>
                    </button>
                </div>
                <form action="food" method="POST" autocomplete="off">
                	<input type="hidden" name="action" value="insert">
                    <div class="modal-body">
                        <div class="form-group">
                            <label>Tipo</label>
                            <select required name="food_type" class="form-control">
                                <option value="" selected disabled>Selecione uma opção</option>
                                <c:forEach items="${food_types}" var="ft">
                                	<option value="${ft.id}">${ft.name}</option>	
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>
                                Alimentos
                                <a type="button" class="ml-1 btn-action add-food" title="Adicionar mais alimentos">
                                    <i class="fa fa-plus"></i>
                                </a>
                            </label>
                            <div class="food form-row mt-3">
                                <div class="col-12 col-lg-7">
                                    <small>Alimento 1</small>
                                    <input required type="text" name="food_item[name]" class="form-control" placeholder="Digite o alimento">
                                </div>
                                <div class="col-10 col-lg-4">
                                    <small>Calorias</small>
                                    <input required type="text" name="food_item[cal]" class="form-control number" placeholder="Calorias">
                                </div>
                                <div class="col-2 col-lg-1">
                                    <a type="button" class="btn-action remove-food mt-3" title="Remover alimento">
                                        <i class="far fa-trash-alt"></i>
                                    </a>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label>Data da refeição</label>
                            <input type="date" required name="created_at" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Descrição</label>
                            <input type="text" name="description" class="form-control">
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
    
    <div class="modal" id="modalUpdate" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Gestão de refeição</h5>
                    <button type="button" class="close btn-action" data-dismiss="modal">
                        <span>&times;</span>
                    </button>
                </div>
                <form action="food" method="POST" autocomplete="off" id="crud_form">
                	<input type="hidden" name="action" value="update" id="crud_action">
                	<input type="hidden" name="id" value="" id="crud_id">
                    <div class="modal-body">
                        <div class="form-group">
                            <label>Tipo</label>
                            <select required name="food_type" id="food_type" class="form-control">
                                <option value="" selected disabled>Selecione uma opção</option>
                                <c:forEach items="${food_types}" var="ft">
                                	<option value="${ft.id}">${ft.name}</option>	
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>Data da refeição</label>
                            <input type="date" required name="created_at" id="created_at" class="form-control">
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
    
    <div class="modal" id="modalUpdateItem" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Gestão de alimento</h5>
                    <button type="button" class="close btn-action" data-dismiss="modal">
                        <span>&times;</span>
                    </button>
                </div>
                <form action="food" method="POST" autocomplete="off" id="crud_form_item">
                	<input type="hidden" name="action" value="" id="crud_action_item">
                	<input type="hidden" name="id" value="" id="crud_id_item">
                	<input type="hidden" name="food" value="" id="food_id_item">
                    <div class="modal-body">
                        <div class="form-group">
                            <label>Alimento</label>
                            <input type="text" name="name" id="name_item" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Calorias</label>
                            <input type="text" name="calories" id="calories_item" class="form-control number">
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