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
               name="campaigns" requestURI="${requestURI}" id="row">

    <!-- Action links -->


    <!-- Attributes -->

    <spring:message code="campaign.sponsor" var="sponsorHeader" />
    <display:column property="sponsor" title="${sponsorHeader}" sortable="true" />

    <spring:message code="campaign.banners" var="bannersHeader" />
    <display:column property="banners" title="${bannersHeader}" sortable="false" />

    <spring:message code="campaign.star_campaign" var="starHeader" />
    <display:column property="star_campaign" title="${starHeader}" sortable="true" />

    <spring:message code="campaign.start_at" var="starAtHeader" />
    <display:column property="start_at" title="${startAtHeader}" sortable="true" format="{0,date,dd/MM/yyyy HH:mm}" />

    <spring:message code="campaign.end_at" var="endAtHeader" />
    <display:column property="end_at" title="${endAtHeader}" sortable="true" format="{0,date,dd/MM/yyyy HH:mm}" />

</display:table>