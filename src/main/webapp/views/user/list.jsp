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

<!-- Listing grid -->
<section class="main">

	<h1>List of Users</h1>
	<form method="GET" name="search_users_form">
		<input type="search" name="query" placeholder="Search for a user..."/>
		<input type="submit" value="Search"/>
	</form>

	<article>
<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="users" requestURI="user/list.do" id="row">
	
	<!-- Attributes -->

	<spring:message code="name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}" sortable="true" />

	<spring:message code="surnames" var="surnamesHeader" />
	<display:column property="name" title="${surnamesHeader}" sortable="true" />

	<spring:message code="email" var="emailHeader" />
	<display:column property="email" title="${emailHeader}" sortable="true" />

	<spring:message code="phone" var="phoneHeader" />
	<display:column property="phone" title="${phoneHeader}" sortable="true" />

	<spring:message code="address" var="addressHeader" />
	<display:column property="address" title="${addressHeader}"	sortable="false" />

	<spring:message code="browse" var="browseHeader" />
	<display:column title="${browseHeader}">
		<a href="http://localhost:8080/user/${row.id}.do">${browseHeader}</a>
	</display:column>

</display:table>
	</article>
</section>
