<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Liste des clients existants</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css"/>" />
    </head>
    <body>
        <c:import url="/inc/menu.jsp" />
        <div id="corps">
        <c:choose>
            <%-- Si aucun client n'existe en context, affichage d'un message par défaut. --%>
            <c:when test="${ empty clients }">
                <p class="erreur">Aucun client enregistré.</p>
            </c:when>
            <%-- Sinon, affichage du tableau. --%>
            <c:otherwise>
            <table>
                <tr>
                	<th>Id</th>
                    <th>Nom</th>
                    <th>Prénom</th>
                    <th>Téléphone</th>
                    <th>Email</th>
                    <th class="action">Action</th>                    
                </tr>
                <%-- Parcours de la Map des clients en context, et utilisation de l'objet varStatus. --%>
                <c:forEach items="${ clients }" var="client" varStatus="boucle">
                <%-- Simple test de parité sur l'index de parcours, pour alterner la couleur de fond de chaque ligne du tableau. --%>
                <tr class="${boucle.index % 2 == 0 ? 'pair' : 'impair'}">
                    <%-- Affichage des propriétés du bean Client, qui est stocké en tant que valeur de l'entrée courante de la map --%>
                    <td>
                    	<a href="<c:url value="/afficherClient"><c:param name="idClient" value="${ client.id }" /></c:url>">
                    		<c:out value="${ client.id }"/>
                    	</a>
                    </td>
                    <td><c:out value="${ client.nom }"/></td>
                    <td><c:out value="${ client.prenom }"/></td>
                    <td><c:out value="${ client.telephone }"/></td>
                    <td><c:out value="${ client.email }"/></td>
                    <%-- Lien vers la servlet de suppression, avec passage du nom du client - c'est-à-dire la clé de la Map - en paramètre grâce à la balise <c:param/>. --%>
                    <td class="action">
                        <a href="<c:url value="/suppressionClient"><c:param name="idClient" value="${ client.id }" /></c:url>">
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