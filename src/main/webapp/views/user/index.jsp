<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<section class="main">

    <h1>${user.userAccount.username}</h1>
    <article class="col s11">
        <p><b><spring:message code="name" />:</b> ${user.name}</p>
        <p><b><spring:message code="surnames" />:</b> ${user.surnames}</p>
        <p><b><spring:message code="email" />:</b> ${user.email}</p>
        <p><b><spring:message code="phone" />:</b> ${user.phone}</p>
        <p><b><spring:message code="address" />:</b> ${user.address}</p>
    </article>
    <article class="col s12">
        <h2>Recipes Authored</h2>
        <display:table pagesize="5" class="displaytag" keepStatus="true"
                       name="recipes" requestURI="${requestURI}" id="row">

            <!-- Action links -->

            <!-- Attributes -->

            <spring:message code="recipes.title" var="titleHeader" />
            <display:column property="title" title="${titleHeader}" sortable="true" />

            <spring:message code="recipes.summary" var="summaryHeader" />
            <display:column property="summary" title="${summaryHeader}" sortable="false"/>

            <spring:message code="browse" var="browseHeader" />
            <display:column title="${browseHeader}">
                <a href="http://localhost:8080/recipe/${row.id}.do">${browseHeader}</a>
            </display:column>

        </display:table>
    </article>
</section>




