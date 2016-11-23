<%--
 * list.jsp
 *
 * Copyright (C) 2016 Universidad de Sevilla
 *
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
        pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<h1>Search a Recipe</h1>
<form method="get" action="">
    <input type="search" name="search" placeholder="<spring:message code="searchInput"/>" />7
    <input type="submit" name="submit" value="<spring:message code="searchButton"/> " />
</form>
<c:if test="${recipes.length ==0}" >
    <spring:message code="noResultsFound"/>

</c:if>
<c:if test="${search !='null'}" >
<display:table pagesize="5" class="displaytag" keepStatus="true"
               name="actors" requestURI="recipe/list.do" id="row">


    <spring:message code="recipe.ticker" var="tickerHeader" />
    <display:column property="ticker" title="${tickerHeader}" sortable="true" />

    <spring:message code="recipe.title" var="titleHeader" />
    <display:column property="title" title="${titleHeader}" sortable="true" />

    <spring:message code="recipe.summary" var="summaryHeader" />
    <display:column property="summary" title="${summaryHeader}" sortable="true" />

    <spring:message code="recipe.createdAt" var="createdAtHeader" />
    <display:column property="created_at" title="${createdAtHeader}" sortable="true" />

    <spring:message code="recipe.actor" var="authorHeader" />
    <display:column property="author.userAccount.username" title="${authorHeader}"	sortable="true" />
</display:table>
</c:if>
