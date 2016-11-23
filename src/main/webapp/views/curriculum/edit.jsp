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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>Curriculum</h1>
<form:form action="curriculum/edit.do" method="POST" modelAttribute="curriculum">

<h2><spring:message code="educational"/> </h2>
<form:textarea path="educational" rows="10" cols="50"/>
<form:errors cssClass="error" path="educational" />

<h2><spring:message code="experience"/> </h2>
<form:textarea path="experience" rows="10" cols="50"/>
<form:errors cssClass="error" path="experience" />


<h2><spring:message code="hobbies"/> </h2>
<form:textarea path="hobbies" rows="10" cols="50"/>
<form:errors cssClass="error" path="hobbies" />

<h2><spring:message code="references" /> </h2>
	<c:forEach var="reference" items="${curriculum.references}" varStatus="status">
		<b>Reference ${status.index}</b>
		<form:input path="reference[${status.index}].name" name="Rname" id="Rname" />
		<form:errors cssClass="error" path="reference[${status.index}].name" />
		<form:input path="reference[${status.index}].homepage" name="Rhomepage" id="Rhomepage" />
		<form:errors cssClass="error" path="reference[${status.index}].homepage" />
		<br>
	</c:forEach>

	<input type="submit" value="<spring:message code="edit"/> "/>
</form:form>

