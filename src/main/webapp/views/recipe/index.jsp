<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<section class="main">

    <h1>Recipe ${recipe.title}</h1>
    <article class="col s7 ingredients">
        <h2>Ingredients</h2>
        <ul>
        <jstl:forEach var="quantity" items="${recipe.quantities}">
            <li>${quantity.quantity} ${quantity.unit} <spring:message code="of"/> ${quantity.ingredient.name} </li>
        </jstl:forEach>
        </ul>
    </article>
    <article class="col s4">
        <h2> Recipe info</h2>
        <p>
<c:if test="${not empty recipe.picture}">
            <img src="${recipe.picture}" width="100" height="100"/>
</c:if>
    </p>
        <p><b><spring:message code="ticker"/>:</b> ${recipe.ticker} </p>
        <p><b><spring:message code="category"/>:</b> ${recipe.category.name} </p>
        <p><b><spring:message code="author"/>:</b>
            <a href="http://localhost:8080/user/${recipe.author.id}.do">${recipe.author.userAccount.username} </a></p>
        <p><b><spring:message code="created_at"/>:</b> ${recipe.created_at} </p>
        <p><b><spring:message code="updated_at"/>:</b> ${recipe.updated_at} </p>
    </article>
    <article class="col s11">
       <h2><spring:message code="summary"/></h2>
        <p>${recipe.summary}</p>
        <h2><spring:message code="steps"/> </h2>
        <ul class="step">
            <jstl:forEach var="step" items="${recipe.steps}"  varStatus="loop">
                <li><h3>Step ${loop.index+1}</h3>
                    <p>
                        <c:if test="${not empty step.picture}">
                            <img src="${step.picture}" width="80" height="80" />
                        </c:if>
                       ${step.description}</p>
                    <h4><spring:message code="hints"/></h4>
                    <p>${step.hints}</p>
                </li>
            </jstl:forEach>
        </ul>
    </article>
</section>




