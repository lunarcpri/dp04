
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
        pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<form:form action="master-class/edit.do" modelAttribute="masterClass" method="POST">

    <form:hidden path="id" />
    <form:hidden path="version" />


    <form:label path="title">
        <spring:message code="masterClass.title"/>
    </form:label>
    <form:input path="title"/>
    <form:errors cssClass="error" path="title" />
    <br />

    <form:label path="description">
        <spring:message code="masterClass.description"/>
    </form:label>
    <form:input path="description"/>
    <form:errors cssClass="error" path="description" />
    <br />

    <form:label path="learningMaterials">
        <spring:message code="masterClass.learningMaterials"/>
    </form:label>
    <form:input path="learningMaterials"/>
    <form:errors cssClass="error" path="learningMaterials" />
    <br />

    <form:label path="promoted">
        <spring:message code="masterClass.promoted"/>
    </form:label>
    <form:input path="promoted"/>
    <form:errors cssClass="error" path="promoted" />
    <br />

    <form:label path="attendingUsers">
        <spring:message code="masterClass.attendingUsers"/>
    </form:label>
    <form:input path="attengindUsers"/>
    <form:errors cssClass="error" path="attendingUsers" />
    <br />

    <input type="submit" value="<spring:message code="masterClass.edit"/> "/>

    </form:form>