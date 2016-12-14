<%--
 * footer.jsp
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

<jsp:useBean id="date" class="java.util.Date" />

<footer>
	<article>
		<ul>
			<li><h3><i class="fa fa-list"></i> Sections</h3></li>
			<li><a href="#">Recipes</a></li>
			<li><a href="#">Contests</a></li>
			<li><a href="#">Master Classes</a></li>
		</ul>
	</article>
	<article>
		<ul>
			<li><h3><i class="fa fa-user"></i> User</h3></li>
			<li><a href="http://localhost:8080/">Profile</a></li>
			<li><a href="#">Messages</a></li>
			<li><a href="http://localhost:8080/security/login.do">Login</a></li>
			<li><a href="http://localhost:8080/security/j_spring_security_logout" > Logout</a></li>
		</ul>
	</article>
	<article>
		<ul>
			<li><h3><i class="fa fa-phone"></i> About</h3></li>
			<li><a href="#">Contact us</a></li>
			<li><a href="#">About Acme Pad Thai</a></li>
			<li><a href="#">Advertising</a></li>
			<li><a href="#">Cook</a></li>
		</ul>
	</article>
	<div class="copyright">Copyright <i class="fa fa-copyright"></i> 2016 ACME, Inc.</div>
</footer>