<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Liste des commandes existantes</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css"/>" />
    </head>
    <body>
        <c:import url="/inc/menu.jsp" />
        <div id="corps">
        <c:choose>
            <%-- Si aucune commande n'existe en context, affichage d'un message par défaut. --%>
            <c:when test="${ empty commandes }">
                <p class="erreur">Aucune commande enregistrée.</p>
            </c:when>
            <%-- Sinon, affichage du tableau. --%>
            <c:otherwise>
            <table>
                <tr>
                    <th>Id</th>
                    <th>Date</th>
                    <th>Mode de paiement</th>
                    <th>Statut de paiement</th>
                    <th>Mode de livraison</th>
                    <th>Statut de livraison</th>
                    <th>Client</th>
                    <th class="action">Action</th>                    
                </tr>
                <%-- Parcours de la Map des commandes en context, et utilisation de l'objet varStatus. --%>
                <c:forEach items="${ commandes }" var="commande" varStatus="boucle">
                <%-- Simple test de parité sur l'index de parcours, pour alterner la couleur de fond de chaque ligne du tableau. --%>
                <tr class="${boucle.index % 2 == 0 ? 'pair' : 'impair'}">
                    <%-- Affichage des propriétés du bean Commande, qui est stocké en tant que valeur de l'entrée courante de la map --%>
                    <td>
                    	<a href="<c:url value="/afficherCommande"><c:param name="idCommande" value="${ commande.id }" /></c:url>">
                    		<c:out value="${ commande.id }"/>
                    	</a>
                    </td>
                    <td><c:out value="${ commande.date }"/></td>
                    <td><c:out value="${ commande.modePaiement }"/></td>
                    <td><c:out value="${ commande.statutPaiement }"/></td>
                    <td><c:out value="${ commande.modeLivraison }"/></td>
                    <td><c:out value="${ commande.statutLivraison }"/></td>
                    <td><c:out value="${ commande.clientId }"/></td>
                    <%-- Lien vers la servlet de suppression, avec passage de la date de la commande - c'est-à-dire la clé de la Map - en paramètre grâce à la balise <c:param/>. --%>
                    <td class="action">
                        <a href="<c:url value="/suppressionCommande"><c:param name="idCommande" value="${ commande.id }" /></c:url>">
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