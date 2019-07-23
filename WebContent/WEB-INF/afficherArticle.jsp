<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
        <meta charset="utf-8" />
		<title><c:out value="${ article.nom }" /></title>
		<link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css"/>" />
	</head>
	<body>
        <c:import url="/inc/menu.jsp" />
        <div id="corps">
            <p>Id          : <c:out value="${ article.id }"/></p>
            <p>Nom         : <c:out value="${ article.nom }"/></p>
            <p>Description : <c:out value="${ article.description }"/></p>
            <p>Prix        : <c:out value="${ article.prix }"/></p>
        </div>
	</body>
</html>