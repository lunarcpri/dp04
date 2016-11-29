

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
        pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<form:form action="campaign/edit.do" modelAttribute="campaign" method="POST">

    <form:hidden path="id" />
    <form:hidden path="version" />
    <form:hidden path="sponsor"/>

    <form:label path="start_at">
        <spring:message code="campaign.start_at"/>
    </form:label>
    <form:input path="start_at"/>
    <form:errors cssClass="error" path="start_at" />
    <br />

    <form:label path="end_at">
        <spring:message code="campaign.end_at"/>
    </form:label>
    <form:input path="end_at"/>
    <form:errors cssClass="error" path="start_at" />
    <br />



    <form:label path="star_campaign">
        <spring:message code="campaign.star_campaign"/>
    </form:label>
    <form:input path="star_campaign"/>
    <form:errors cssClass="error" path="start_campaign" />
    <br />

    <form:label path="banners">
        <spring:message code="campaign.banners"/>
    </form:label>
    <form:input path="banners"/>
    <form:errors cssClass="error" path="start_at" />
    <br />

    <form:label path="max_time_banners">
        <spring:message code="campaign.max_time_banners"/>
    </form:label>
    <form:input path="max_time_banners"/>
    <form:errors cssClass="error" path="start_campaign" />
    <br />


    <input type="submit" value="<spring:message code="campaign.edit"/> "/>


</form:form>