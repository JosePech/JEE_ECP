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
<body class="light">
    <c:set var="consultaBean" value="${ConsultarVotosBean}" />
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
              <h2>Sistema de Votaciones</h2>
            </header>
            <div>
                <c:set var="registros" value="${consultaBean.getResumen()}" />
                <table id="tablaFacturas" class="tabla">
                    <thead>
                        <th>Tema</th>
                        <th>Escolaridad</th>
                        <th>Media</th>
                    </thead>
                    <tbody>                        
                        <c:forEach var="fila" items="${registros}">
                            <c:choose>
                                <c:when test="${filaAnterior == null || !fila.tema.equals(filaAnterior.tema)}">
                                   <c:set var="totalTema" value="${consultaBean.getTotalPorTema(fila.tema)}" />
                                   <tr>
                                   <td colspan=2>&nbsp;</td>
                                    <td>Total de votos:
	                                    <c:choose>
			                                <c:when test="${totalTema == null}">
			                                   0
			                                </c:when>
			                                <c:otherwise>
		                                     ${totalTema}
			                                </c:otherwise>
			                            </c:choose>
                                    </td>
                                   </tr>
                                </c:when>
                            </c:choose>
                            <tr>
                                <td>${fila.tema.nombre}</td>
                                <td>${fila.escolaridad}</td>
                                <td>
                                    <c:choose>
					                    <c:when test="${fila.media != null}">
					                       ${fila.media}
					                    </c:when>
					                    <c:otherwise>
					                       --
					                    </c:otherwise>
					                </c:choose>					                
                                </td>
                            </tr>
                            <c:set var="filaAnterior" value="${fila}" />
                        </c:forEach>                       
                    </tbody>
                </table>
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
</html>