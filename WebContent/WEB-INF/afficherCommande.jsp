<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Affichage d'une commande</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css"/>" />
    </head>
    <body>
        <c:import url="/inc/menu.jsp" />
        <div id="corps">
            <p class="info">${ form.resultat }</p>
            <p>Client</p>
            <p>Id                  : <c:out value="${ client.id }"/></p>
            <p>Nom                 : <c:out value="${ client.nom }"/></p>
            <p>Prénom              : <c:out value="${ client.prenom }"/></p>
            <p>Numéro de téléphone : <c:out value="${ client.telephone }"/></p>
            <p>Email               : <c:out value="${ client.email }"/></p>
            <p>Commande</p>
            <p>Id                  : <c:out value="${ commande.id }"/></p> 
            <p>Date                : <c:out value="${ commande.date }"/></p> 
            <p>Mode de paiement    : <c:out value="${ commande.modePaiement }"/></p> 
            <p>Statut du paiement  : <c:out value="${ commande.statutPaiement }"/></p> 
            <p>Mode de livraison   : <c:out value="${ commande.modeLivraison }"/></p> 
            <p>Statut de la livraison  : <c:out value="${ commande.statutLivraison }"/></p> 
            <p>Articles : </p> 
            <table>
                <tr>
                    <th>Référence</th>
                    <th>Nom</th>
                    <th>Description</th>
                    <th>Prix</th>
                    <th>Quantité</th>
                </tr>
                <%-- Parcours de la Map des articles en context, et utilisation de l'objet varStatus. --%>
            	<c:forEach items="${ articles }" var="article" varStatus="boucle">
                <tr class="${boucle.index % 2 == 0 ? 'pair' : 'impair'}">
                    <td><c:out value="${ article.id }"/></td>
                    <td><c:out value="${ article.nom }"/></td>
                    <td><c:out value="${ article.description }"/></td>
                    <td><c:out value="${ article.prix }"/></td>
                    <td><c:out value="${ quantities.get(article.id) }"/></td>
                </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>