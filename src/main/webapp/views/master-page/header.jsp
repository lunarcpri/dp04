<%--
 * header.jsp
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<header>
	<h1><a href="http://localhost:8080/">Acme Pad Thai</a></h1>
	<nav>
		<ul>
			<li class="search-section">
				<form method="GET" name="form_search" id="form_search">
					<input type="search" name="search" title="Search" placeholder="Busca un receta...">
					<button class="fa fa-search" form="form_search"></button>
				</form>
			</li>
			<li class="user-section">
			<div class="user-section-menu">
				<a href="#" class="icon"><i class="fa fa-user"></i> Account</a>
				<ul>
					<security:authorize access="isAuthenticated()">
							<li>Profile</li>
							<li>Messages</li>
							<li>Preferences</li>
						<li><a href="http://localhost:8080/security/j_spring_security_logout" > Logout</a></li>
					</security:authorize>
					<security:authorize access="!isAuthenticated()">
						<li><a href="http://localhost:8080/security/login.do">Login</a></li>
						<li><a href="http://localhost:8080/register.do">Signup</a></li>

					</security:authorize>

						</ul>
			</div>
				<a href="#" class="icon"><i class="fa fa-trophy"></i> Contests</a>
				<a href="#" class="icon"><i class="fa fa-envira"></i> Recipes </a>
				<a href="#" class="icon"><i class="fa fa-book"></i> Master Classes </a>
			</li>
		</ul>
	</nav>
</header>
<section class="banners">
	<article>
		<img src="assets/img/banner1.jpg">
		<img src="assets/img/banner2.jpg">
		<img src="assets/img/banner3.jpg">
		<img src="assets/img/banner4.jpg">
	</article>
</section>