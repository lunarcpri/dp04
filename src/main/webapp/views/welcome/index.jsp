<%--
 * action.jsp
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<section class="main">

	<h1>Welcome to Acme Pad Thai</h1>

	<article>
		<h2>Latest Recipes</h2>
		<ul class="list">
			<jstl:forEach var="item" items="${recipes}">
			<a href="http://localhost:8080/recipe/${item.id}.do"><li class="card">
				<h3>${item.title}</h3>
				<img src="${item.picture}">
				<p>${item.summary}</p>
			</li></a>
			</jstl:forEach>
		</ul>
	</article>
</section>