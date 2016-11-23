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

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="actors" requestURI="contest/list.do" id="row">
	
	<!-- Attributes -->

	<spring:message code="contest.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" sortable="true" />

	<spring:message code="contest.openedAt" var="openedAtHeader" />
	<display:column property="opened_at" title="${openedAtHeader}" sortable="true" />

	<spring:message code="contest.closedAt" var="closedAtHeader" />
	<display:column property="closed_at" title="${closedAtHeader}" sortable="true" />

	<spring:message code="contest.qualify"  />
	<display:column>
		<form:select path="recipes">
			<form:options items="${recipes}" itemValue="id" itemLabel="title" />
		</form:select> }"
	</display:column>

</display:table>

