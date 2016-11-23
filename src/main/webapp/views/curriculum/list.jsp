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
<a href="curriculum/edit.do"><spring:message code="edit"/> </a>

<h2><spring:message code="educational"/> </h2>
<p>${curriculum.educational}</p>


<h2><spring:message code="experience"/> </h2>
<p>${curriculum.experience}</p>


<h2><spring:message code="hobbies"/> </h2>
<p>${curriculum.hobbies}</p>

<h2><spring:message code="references" /> </h2>
<ul>
<c:forEach var="itemValue" items="${curriculum.references}">
	<li><a href="${itemValue.homepage}" target="_blank" >${itemValue.name}</a></li>
</c:forEach>
</ul>