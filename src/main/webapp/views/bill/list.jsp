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
               name="bills" requestURI="bill/list.do" id="row">

    <spring:message code="bill.created_at" var="created_at" />
    <display:column property="created_at" title="${created_at}" sortable="true" />

    <spring:message code="bill.paid_at" var="paid_at" />
    <display:column property="paid_at" title="${paid_at}" sortable="true" />

    <spring:message code="bill.cost" var="cost" />
    <display:column property="cost" title="${cost}" sortable="true" />

    <spring:message code="bill.description" var="description" />
    <display:column property="description" title="${description}" sortable="true" />

    <spring:message code="bill.sponsor" var="sponsor" />
    <display:column property="sponsor" title="${sponsor}" sortable="true" />

    </display:table>