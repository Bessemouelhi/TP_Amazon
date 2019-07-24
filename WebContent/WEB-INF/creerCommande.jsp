<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Création d'une commande</title>
<link type="text/css" rel="stylesheet"
	href="<c:url value="/inc/style.css"/>" />
</head>
<body>
	<c:import url="/inc/menu.jsp" />

	<%-- Si et seulement si la Map des clients en context n'est pas vide, alors il est possible de créer une commande --%>
	<c:if test="${ !empty clients }">
		<div>
			<form method="post" action="<c:url value="/creationCommande"/>">
				<fieldset>
					<legend>Informations client</legend>
					<div id="ancienClient">
						<select name="listeClients" id="listeClients">
							<option value="">Choisissez un client...</option>
							<%-- Boucle sur la map des clients --%>
							<c:forEach items="${ clients }" var="client">
								<%--  L'expression EL ${mapClients.value} permet de cibler l'objet Client stocké en tant que valeur dans la Map, 
	                                  et on cible ensuite simplement ses propriétés nom et prenom comme on le ferait avec n'importe quel bean. --%>
								<option value="${ client.id }">${ client.prenom }
									${ client.nom } (${ client.id })</option>
							</c:forEach>
						</select>
					</div>
				</fieldset>
				<fieldset>
					<legend>Informations commande</legend>

					<label for="dateCommande">Date <span class="requis">*</span></label>
					<input type="text" id="v" name="dateCommande"
						value="<c:out value="${commande.date}"/>" size="30" maxlength="30"
						disabled /> <span class="erreur">${form.erreurs['dateCommande']}</span>
					<br /> <label for="modePaiementCommande">Mode de paiement
						<span class="requis">*</span>
					</label> <input type="text" id="modePaiementCommande"
						name="modePaiementCommande"
						value="<c:out value="${commande.modePaiement}"/>" size="30"
						maxlength="30" /> <span class="erreur">${form.erreurs['modePaiementCommande']}</span>
					<br /> <label for="statutPaiementCommande">Statut du
						paiement</label> <input type="text" id="statutPaiementCommande"
						name="statutPaiementCommande"
						value="<c:out value="${commande.statutPaiement}"/>" size="30"
						maxlength="30" /> <span class="erreur">${form.erreurs['statutPaiementCommande']}</span>
					<br /> <label for="modeLivraisonCommande">Mode de
						livraison <span class="requis">*</span>
					</label> <input type="text" id="modeLivraisonCommande"
						name="modeLivraisonCommande"
						value="<c:out value="${commande.modeLivraison}"/>" size="30"
						maxlength="30" /> <span class="erreur">${form.erreurs['modeLivraisonCommande']}</span>
					<br /> <label for="statutLivraisonCommande">Statut de la
						livraison</label> <input type="text" id="statutLivraisonCommande"
						name="statutLivraisonCommande"
						value="<c:out value="${commande.statutLivraison}"/>" size="30"
						maxlength="30" /> <span class="erreur">${form.erreurs['statutLivraisonCommande']}</span>
					<br />

					<p class="info">${ form.resultat }</p>
				</fieldset>

				<fieldset>
					<legend>Articles</legend>

					<table>
						<tr>
							<th>Reference</th>
							<th>Nom</th>
							<th>Prix</th>
							<th>Quantité</th>
						</tr>

						<c:forEach items="${ applicationScope.articles }" var="article">
							<tr>
								<td><c:out value="${ article.value.id }" /></td>
								<td><c:out value="${ article.value.nom }" /></td>
								<td><c:out value="${ article.value.prix }" /></td>
								<input type="text" id="<c:out value="${ article.value.id }"/>"
									name="<c:out value="${ article.value.id }"/>" value="0"
									size="3" />
								<span class="erreur">${form.erreurs['dateCommande']}</span>
								<br />
							</tr>
						</c:forEach>
					</table>

					<p class="info">${ form.resultat }</p>
				</fieldset>

				<input type="submit" value="Valider" /> <input type="reset"
					value="Remettre à zéro" /> <br />
			</form>
		</div>
		
	</c:if>

	<%-- Sinon on retourne une erreur --%>
	<c:if test="${ empty applicationScope.clients }">
        Merci de commencer par créer un client.
        </c:if>
</body>
</html>