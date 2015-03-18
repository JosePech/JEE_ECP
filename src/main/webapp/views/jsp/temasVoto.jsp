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
              <h2>Elija un Tema de la lista</h2>
            </header>
            <div>
                <c:if test="${temasVotoBean.temas.size() == 0}">
                   <h4>No hay temas registrados.</h4>
                </c:if>
                <form method="post">
                    <ul style="list-style-type:none;">
                        <c:forEach var="tema" items="${temasVotoBean.temas}">
                            <li style="padding:5px;">
                               <label class="topcoat-radio-button">
                                  <input type="radio" checked name="id" value="${tema.id}">
                                  <div class="topcoat-radio-button__checkmark">&nbsp;</div>
                                  ${tema.nombre}
                               </label>
                            </li>
                        </c:forEach>
                    </ul>
                    <p>
                        <button type="submit" class="topcoat-button--large--cta">Votar</button>
                    </p>
                </form>
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