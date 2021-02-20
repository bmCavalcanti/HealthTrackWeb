<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<nav class="fixed-bottom" id="bar-navigation">
    <div class="btn-group w-100" role="group" aria-label="Basic example">
        <a type="button" href="./dashboard" title="Dashboard" class="btn btn-primary text-white">
            <i class="fa fa-home"></i><br>
            <small>Home</small>
        </a>
        <a type="button" href="./profile" title="Perfil" class="btn btn-primary text-white">
            <i class="fa fa-cogs"></i><br>
            <small>Perfil</small>
        </a>
        <a type="button" data-toggle="modal" data-target="#modalConfirmLogout" title="Sair" class="btn btn-primary text-white">
            <i class="fa fa-door-open"></i><br>
            <small>Sair</small>
        </a>
    </div>
</nav>

<div class="modal" id="modalConfirmLogout" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close btn-action" data-dismiss="modal">
                    <span>&times;</span>
                </button>
            </div>
            <div class="modal-body text-center">
                <h4 class="">Tem certeza que deseja sair?</h4>
            </div>
            <div class="modal-footer">
                <div class="btn-group btn-group-toggle w-100" data-toggle="buttons">
                    <button data-dismiss="modal" type="button" title="Não" class="btn btn-outline-primary">
                        Não
                    </button>
                    <a href="./login" title="Sair" class="btn btn-primary">
                        Sair
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>