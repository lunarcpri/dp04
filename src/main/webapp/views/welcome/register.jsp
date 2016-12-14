

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<section class="main">

    <h1>Register</h1>

    <ul class="register-list">
        <li>
            <h2><a href="/user/register.do"> <i class="fa fa-user"></i>Register as User</a></h2>
            <ul>
                <li>Can follow Users or Nutritionist</li>
                <li>Can write comments</li>
                <li>Can like a recipe</li>
            </ul>
        </li>
        <li>
            <h2><a href="/nutritionist/register.do"><i class="fa fa-envira"></i> Register as Nutritionist</a></h2>
            <ul>
                <li>Can follow Users or Nutritionist</li>
                <li>Can write comments</li>
                <li>Can like a recipe</li>
                <li>Can manage his/her curricula</li>
                <li>Can manage the catalogue of properties</li>
                <li>Can manage ingredients</li>
            </ul>
        </li>
        <li>
            <h2><a href="/sponsor/register.do"><i class="fa fa-dollar"></i> Register as Sponsor</a></h2>
            <ul>
                <li>Manage a campaign</li>
            </ul>
        </li>
    </ul>

</section>