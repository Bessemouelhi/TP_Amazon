<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Création d'un article</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css"/>" />
    </head>
    <body>
        <c:import url="/inc/menu.jsp" />
        <div>
            <form method="post" action="<c:url value="/creationArticle"/>">
                <fieldset>
                    <legend>Informations article</legend>
                    
					<label for="nomArticle">Nom <span class="requis">*</span></label>
					<input type="text" id="nomArticle" name="nomArticle" value="<c:out value="${article.nom}"/>" size="30" maxlength="30" />
					<span class="erreur">${form.erreurs['nomArticle']}</span>
					<br />
					
					<label for="descriptionArticle">Description </label>
					<input type="text" id="descriptionArticle" name="descriptionArticle" value="<c:out value="${article.description}"/>" size="30" maxlength="30" />
					<span class="erreur">${form.erreurs['descriptionArticle']}</span>
					<br />
					
					<label for="prixArticle">Prix <span class="requis">*</span></label>
					<input type="text" id="prixArticle" name="prixArticle" value="<c:out value="${article.prix}"/>" size="30" maxlength="60" />
					<span class="erreur">${form.erreurs['prixArticle']}</span>
					<br />
                </fieldset>  
                <p class="info">${ form.resultat }</p>
                <input type="submit" value="Valider"  />
                <input type="reset" value="Remettre à zéro" /> <br />
            </form>
        </div>
    </body>
</html>
