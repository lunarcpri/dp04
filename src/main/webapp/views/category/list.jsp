<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<section class="main">

    <h1>List of Categories</h1>
    <article>
<display:table pagesize="5" class="displaytag" keepStatus="true"
               name="categories" requestURI="${requestURI}" id="row">

    <!-- Action links -->

    <!-- Attributes -->

    <spring:message code="categories.name" var="nameHeader" />
    <display:column property="name" title="${nameHeader}" sortable="true" />

    <spring:message code="categories.description" var="descriptionHeader" />
    <display:column property="description" title="${descriptionHeader}" sortable="false"/>


    <spring:message code="categories.tags" var="tagsHeader" />
    <display:column property="tags" title="${tagsHeader}" sortable="false"/>

    <spring:message code="browse" var="browseHeader" />
    <display:column title="${browseHeader}">
        <a href="http://localhost:8080/category/${row.id}.do">${browseHeader}</a>
    </display:column>

</display:table>
    </article>
</section>




