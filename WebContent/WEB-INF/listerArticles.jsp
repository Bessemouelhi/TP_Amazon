<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Articles disponibles sur À ma zone</title>
		<link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css"/>" />
	</head>
	<body>
        <c:import url="/inc/menu.jsp" />
        <div id="corps">
        <c:choose>
            <%-- Si aucun client n'existe en context, affichage d'un message par défaut. --%>
            <c:when test="${ empty applicationScope.articles }">
                <p class="erreur">Aucun article disponible... On va fermer boutique !</p>
            </c:when>
            <%-- Sinon, affichage du tableau. --%>
            <c:otherwise>
            <table>
                <tr>
                    <th>Id</th>
                    <th>Nom</th>
                    <th>Description</th>
                    <th>Prix</th>
                    <th class="action">Action</th>                    
                </tr>
                <%-- Parcours de la Map des articles en context, et utilisation de l'objet varStatus. --%>
                <c:forEach items="${ applicationScope.articles }" var="article" varStatus="boucle">
                <tr class="${boucle.index % 2 == 0 ? 'pair' : 'impair'}">
                    <td>
                    	<a href="<c:url value="/afficherArticle"><c:param name="idArticle" value="${ article.key }" /></c:url>">
                    		<c:out value="${ article.value.id }"/>
                    	</a>
                    </td>
                    <td><c:out value="${ article.value.nom }"/></td>
                    <td><c:out value="${ article.value.description }"/></td>
                    <td><c:out value="${ article.value.prix }"/></td>
                    <%-- Lien vers la servlet de suppression, avec passage du nom du client - c'est-à-dire la clé de la Map - en paramètre grâce à la balise <c:param/>. --%>
                    <td class="action">
                        <a href="<c:url value="/suppressionArticle"><c:param name="idArticle" value="${ article.key }" /></c:url>">
                            <img src="<c:url value="/inc/supprimer.png"/>" alt="Supprimer" />
                        </a>
                    </td>
                </tr>
                </c:forEach>
            </table>
            </c:otherwise>
        </c:choose>
        </div>
	</body>
</html>