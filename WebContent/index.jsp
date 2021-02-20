<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="header.jsp" %>
	<title>Health Track</title>
</head>
<body>
	<nav class="navbar fixed-top bg-primary">
       <a class="navbar-brand" href="#" title="Início">
           <img src="assets/images/logos/icon.png" alt="Logo do Health Track" class="py-1 img-fluid white-image" width="50">
       </a>
       <div class="ml-auto">
           <a href="./login" role="button" title="Entrar">
               Entrar
           </a>
           |
           <a href="./register" role="button" title="Entrar">
               Cadastro
           </a>
       </div>
   </nav>

   <section class="parallax vh-100 pt-5" id="start">
       <div class="container pt-5">
           <div class="mr-auto col-lg-9 bg-opacity p-3 p-lg-5">
               <img alt="Logo Health Track" src="./assets/images/logos/logotipo.png" width="300" class="mb-4 img-fluid">
               <div class="">
                   <h6>
                       Seu sistema de acompanhamento de saúde
                       <br>
                       <br>

                       Lorem ipsum dolor sit amet consectetur adipisicing elit. Explicabo sed blanditiis quam quo neque consectetur cupiditate, molestiae exercitationem. Adipisci est nesciunt eaque placeat ab rem aperiam saepe maiores harum illo.
                   </h6>

                   <a href="./register" class="btn btn-primary mt-4">
                       Cadastre-se
                   </a>
               </div>
           </div>
       </div>
   </section>

   <footer class="bg-primary p-3">
       <div class="container my-auto">
           <div class="py-3">
               <div class="row">
                   <div class="col-12 col-lg-4">
                       <h4 class="title">Contato</h4>
                       <ul class="navbar-nav">
                           <li class="nav-item active">
                               <a title="Fale conosco" href="#" class="nav-link">
                                   <i class="fa fa-envelope"></i>
                                   example@healthtrack.com.br
                               </a>
                           </li>
                           <li class="nav-item">
                               <a title="Entre em contato" class="nav-link" href="#">
                                   <i class="fab fa-whatsapp"></i>
                                   (11) 0 0000-0000
                               </a>
                           </li>
                       </ul>
                   </div>
                   <div class="col-12 col-lg-4">
                       <h4 class="title mb-3">Redes sociais</h4>
                       <a title="Facebook" class="mx-1" href="#"><i class="fab fa-facebook-f h4"></i></a>
                       <a title="Instagram" class="mx-1" href="#"><i class="fab fa-instagram h4"></i></a>
                   </div>
               </div>
           </div>
           <div class="text-center my-auto border-top border-light py-3">
               Todos os direitos reservados &copy; 2020 | Desenvolvido com <i class="fa fa-heart text-light"></i> por <a href="https://biancacavalcanti.000webhostapp.com/" target="_blank">Bianca Cavalcanti</a>
           </div>
       </div>
    </footer>
	<%@ include file="footer.jsp" %>
</body>
</html>