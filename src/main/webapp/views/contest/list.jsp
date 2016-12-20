<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<section class="main">

    <h1>List of Contests</h1>

    <article>
        <display:table pagesize="5" class="displaytag" keepStatus="true"
                       name="contests" requestURI="${requestURI}" id="row">

            <!-- Action links -->

            <!-- Attributes -->

            <spring:message code="recipes.title" var="titleHeader" />
            <display:column property="title" title="${titleHeader}" sortable="true" />

            <spring:message code="contest.openedat" var="openedatHeader" />
            <display:column property="opened_at" title="${openedatHeader}" sortable="true" format="{0,date,dd/MM/yyyy HH:mm}"/>

            <spring:message code="contest.closedat" var="closedatHeader" />
            <display:column property="closed_at" title="${closedatHeader}" sortable="true" format="{0,date,dd/MM/yyyy HH:mm}"/>


            <spring:message code="browse" var="browseHeader" />
            <display:column title="${browseHeader}">
                <a href="http://localhost:8080/contest/${row.id}.do">${browseHeader}</a>
            </display:column>

        </display:table>
    </article>
</section>




