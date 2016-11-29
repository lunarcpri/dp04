
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
               name="masterClasses" requestURI="${requestURI}" id="row">


    <spring:message code="masterClass.title" var="titleHeader" />
    <display:column property="title" title="${titleHeader}" sortable="true" />

    <spring:message code="masterClass.description" var="descriptionHeader" />
    <display:column property="description" title="${descriptionHeader}" sortable="true" />

    <spring:message code="masterClass.Cook" var="cookHeader" />
    <display:column property="cook" title="${cookHeader}" sortable="true" />

    <spring:message code="masterClass.learningMaterials" var="learningMaterialsHeader" />
    <display:column property="learningMaterials" title="${learningMaterialsHeader}" sortable="true" />

    <spring:message code="masterClass.promoted" var="promotedHeader" />
    <display:column property="promoted" title="${promotedHeader}" sortable="true" />

    <spring:message code="masterClass.attendingUsers" var="attendingUsersHeader" />
    <display:column property="attendingUsers" title="${attendingUsersHeader}" sortable="true" />

    <a href="#"> <spring:message code="masterClass.attend"/> </a>

    </display:table>