<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<div class="modal" id="modalConfirmDelete" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close btn-action" data-dismiss="modal">
                    <span>&times;</span>
                </button>
            </div>
            <div class="modal-body text-center">
                <h4 class="">Tem certeza que deseja deletar?</h4>
            </div>
            <div class="modal-footer">
            	<form action="#" method="POST" class="col-12" id="delete_form">
            		<input type="hidden" name="action" value="delete" id="delete_action">
            		<input type="hidden" name="id" value="" id="delete_id">
            		<div class="btn-group btn-group-toggle w-100" data-toggle="buttons">
                     <button data-dismiss="modal" type="button" title="Cancelar" class="btn btn-outline-primary">
                         Cancelar
                     </button>
                     <button type="submit" title="Deletar" class="btn btn-primary">
                         Deletar
                     </button>
                 </div>
            	</form>
            </div>
        </div>
    </div>
</div>
    
<script src="./assets/vendor/js/jquery-3.5.1.min.js" crossorigin="anonymous"></script>
<script src="./assets/vendor/js/popper.min.js" crossorigin="anonymous"></script>
<script src="./assets/vendor/js/bootstrap.min.js" crossorigin="anonymous"></script>
<script src="./assets/vendor/js/jquery.mask.min.js" crossorigin="anonymous"></script>
<script src="./assets/vendor/js/jquery.validate.min.js" crossorigin="anonymous"></script>
<script src="./assets/vendor/js/additional-methods.min.js" crossorigin="anonymous"></script>
<script src="./assets/vendor/js/messages_pt_PT.min.js" crossorigin="anonymous"></script>
<script src="./assets/vendor/js/select2.min.js" crossorigin="anonymous"></script>
<script src="./assets/vendor/js/chart.js" crossorigin="anonymous"></script>
<script src="./assets/vendor/js/jquery.dataTables.min.js" crossorigin="anonymous"></script>
<script src="./assets/vendor/js/dataTables.bootstrap4.min.js" crossorigin="anonymous"></script>

<script src="./assets/js/src/App.js"></script>
<script src="./assets/js/src/Calculator.js"></script>
<script src="./assets/js/app.js"></script>