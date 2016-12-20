<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<section class="main">

    <h1>Contest ${contest.title}</h1>
    <article class="col s11">
        <p><b><spring:message code="contest.openedat" />:</b> ${contest.opened_at}</p>
        <p><b><spring:message code="contest.closedat" />:</b> ${contest.closed_at}</p>
    </article>
    <article class="col s5">
        <h2>Qualified Recipes</h2>
        <display:table pagesize="5" class="displaytag" keepStatus="true"
                       name="recipesQualified" requestURI="${requestURI}" id="row">

            <spring:message code="recipes.title" var="titleHeader" />
            <display:column property="title" title="${titleHeader}" sortable="true" />

            <spring:message code="recipes.summary" var="summaryHeader" />
            <display:column property="summary" title="${summaryHeader}" sortable="false"/>

            <spring:message code="recipes.author" var="authorHeader" />
            <display:column title="${authorHeader}">
                <a href="http://localhost:8080/actor/actor.do?id=${row.author.userAccount.id}">${row.author.userAccount.username}</a>
            </display:column>
            <spring:message code="browse" var="browseHeader" />
            <display:column title="${browseHeader}">
                <a href="http://localhost:8080/recipe/${row.id}.do">${browseHeader}</a>
            </display:column>

        </display:table>
    </article>
    <article class="col s5">
        <h2>Winner Recipes</h2>
        <display:table pagesize="5" class="displaytag" keepStatus="true"
                       name="recipesWinner" requestURI="${requestURI}" id="row">

            <spring:message code="recipes.title" var="titleHeader" />
            <display:column property="title" title="${titleHeader}" sortable="true" />

            <spring:message code="recipes.summary" var="summaryHeader" />
            <display:column property="summary" title="${summaryHeader}" sortable="false"/>

            <spring:message code="recipes.author" var="authorHeader" />
            <display:column title="${authorHeader}">
                <a href="http://localhost:8080/actor/actor.do?id=${row.author.userAccount.id}">${row.author.userAccount.username}</a>
            </display:column>
            <spring:message code="browse" var="browseHeader" />
            <display:column title="${browseHeader}">
                <a href="http://localhost:8080/recipe/${row.id}.do">${browseHeader}</a>
            </display:column>

        </display:table>
    </article>
</section>




