<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>APLICACION VOTACIONES</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="user-scalable=no,initial-scale = 1.0,maximum-scale = 1.0">
<link rel="stylesheet" type="text/css" href="/votacionApp/resources/font/stylesheet.css">
<link rel="stylesheet" type="text/css" href="/votacionApp/resources/css/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="/votacionApp/resources/css/main.css">
<link rel="stylesheet" type="text/css" href="/votacionApp/resources/css/topcoat-desktop-light.min.css">    
<link rel="stylesheet" type="text/css" href="/votacionApp/resources/css/estilos.css">    
</head>
<body>
    <c:set var="temasVotoBean" value="${TemasVotoBean}" />
    <c:set var="votarTemaBean" value="${VotarTemaBean}" />
    <body class="light">
    <div id="wrapper">
      <div class="max-width">
        <div id="sideNav">         
          <div class="combo">
            <p><strong>Menu</strong></p>                          
              <a style="width:150px;" class="topcoat-button--large" href="<c:url value="/jsp/temasVoto"/>">VOTAR</a>
              <a style="width:150px;" class="topcoat-button--large" href="<c:url value="/jsp/agregarTema"/>">AGREGAR TEMA</a>
              <a style="width:150px;" class="topcoat-button--large" href="<c:url value="/jsp/accesoTema"/>">LISTA TEMAS</a>
              <a style="width:150px;" class="topcoat-button--large" href="<c:url value="/jsp/consultarVotos"/>">CONSULTAR</a>                        
          </div>          
        </div>
      </div>
      <div id="site">
        <header id="main-header">
          <div class="max-width">
            <hgroup>
              <h1><a id="titulo11">JEE</a></h1>
              <p>Aplicación web</p>
            </hgroup>
          </div>
        </header>
        <div id="content" class="max-width">
          <div id="listaFacturas" class="navSection">          
            <header>
              <h2>Registrar Voto</h2>
            </header>
            <div>
                <c:if test="${votarTemaBean.result == -1}">
                   <h4>Ocurrio un error. Vaya a la selección de tema e intente la operacion nuevamente.</h4>
                </c:if>
                <c:if test="${temasVotoBean != null}">
	                <form action="<c:url value="/jsp/votarTema"/>" method="post">
	                    <div>
	                        <p>Tema: ${ temasVotoBean.tema.nombre }</p>
	                        <input type="hidden" name="id" value="${ temasVotoBean.tema.id }" />
	                    </div>
	                    <div>
	                        <p>Pregunta: ${ temasVotoBean.tema.pregunta }</p>
	                    </div>
	                    <div>
	                        <label for="temaPregunta">Su voto:</label>
	                        <select name="voto">
	                            <option value="0">0</option>
	                            <option value="1">1</option>
	                            <option value="2">2</option>
	                            <option value="3">3</option>
	                            <option value="4">4</option>
	                            <option value="5">5</option>
	                            <option value="6">6</option>
	                            <option value="7">7</option>
	                            <option value="8">8</option>
	                            <option value="9">8</option>
	                            <option value="10">10</option>
	                        </select>              
	                    </div>   
	                    <div>
	                        <label for="temaPregunta">Seleccione su nivel de escolaridad:</label>
	                        <select name="escolaridad">                            
	                            <c:forEach var="nivel" items="${votarTemaBean.getEscolaridadValues()}">
	                               <option value="${nivel}">${nivel}</option>                                  
	                           </c:forEach>
	                        </select>
	                    </div>   
	                    <br/>  
	                    <p>
	                        <button type="submit" class="topcoat-button--large--cta">Votar</button>
	                    </p>
	                </form>
                </c:if>
            </div>
          </div>
        </div>
        <footer></footer>
      </div>
    </div>
    <script src="resources/js/jquery-1.11.1.min.js" type="text/javascript"></script>
    <script src="resources/js/jquery-ui.min.js" type="text/javascript"></script>    
    <script src="resources/js/jquery-color.js" type="text/javascript"></script>    
  </body>
</body>
</html>