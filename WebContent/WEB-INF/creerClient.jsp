<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Création d'un client</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css"/>" />
    </head>
    <body>
        <c:import url="/inc/menu.jsp" />
        <div>
            <form method="post" action="<c:url value="/creationClient"/>">
                <fieldset>
                    <legend>Informations client</legend>
                    
					<label for="nomClient">Nom <span class="requis">*</span></label>
					<input type="text" id="nomClient" name="nomClient" value="<c:out value="${client.nom}"/>" size="30" maxlength="30" />
					<span class="erreur">${form.erreurs['nomClient']}</span>
					<br />
					
					<label for="prenomClient">Prénom </label>
					<input type="text" id="prenomClient" name="prenomClient" value="<c:out value="${client.prenom}"/>" size="30" maxlength="30" />
					<span class="erreur">${form.erreurs['prenomClient']}</span>
					<br />
					
					<label for="passwordClient">Mot de passe <span class="requis">*</span></label>
					<input type="text" id="passwordClient" name="passwordClient" value="<c:out value="${client.password}"/>" size="30" maxlength="60" />
					<span class="erreur">${form.erreurs['passwordClient']}</span>
					<br />
					
					<label for="telephoneClient">Numéro de téléphone <span class="requis">*</span></label>
					<input type="text" id="telephoneClient" name="telephoneClient" value="<c:out value="${client.telephone}"/>" size="30" maxlength="30" />
					<span class="erreur">${form.erreurs['telephoneClient']}</span>
					<br />
					
					<label for="emailClient">Adresse email</label>
					<input type="email" id="emailClient" name="emailClient" value="<c:out value="${client.email}"/>" size="30" maxlength="60" />
					<span class="erreur">${form.erreurs['emailClient']}</span>
					<br />
                </fieldset>  
                <p class="info">${ form.resultat }</p>
                <input type="submit" value="Valider"  />
                <input type="reset" value="Remettre à zéro" /> <br />
            </form>
        </div>
    </body>
</html>