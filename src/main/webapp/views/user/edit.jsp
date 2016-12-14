<%@page language="java" contentType="text/html; charset=ISO-8859-1"
        pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<form:form action="user/edit.do" modelAttribute="user" method="POST">
    <form:label path="userAccount.username">
        <spring:message code="userAccount.username" />:
    </form:label>
    <form:input path="userAccount.username" />
    <form:errors cssClass="error" path="userAccount.username" />
    <br>
    <form:label path="userAccount.password">
        <spring:message code="userAccount.password" />:
    </form:label>
    <form:password path="userAccount.password" />
    <form:errors cssClass="error" path="userAccount.password" />
    <br>
    <form:label path="userAccount.authorities">
        <spring:message code="userAccount.authorities" />:
    </form:label>
    <form:select id="authorities" path="userAccount.authorities" >
        <form:option value="0" label="----" />
        <form:options items="${authorities}" itemValue="authority"
                      itemLabel="authority" />
    </form:select>
    <form:errors cssClass="error" path="email" />
    <h2>Personal data</h2>
    <form:label path="email">
        <spring:message code="email" />:
    </form:label>
    <form:input path="email" />
    <form:errors cssClass="error" path="email" />
    <br>
    <form:label path="name">
        <spring:message code="name" />:
    </form:label>
    <form:input path="name" />
    <form:errors cssClass="error" path="name" />
    <br>
    <form:label path="surnames">
        <spring:message code="surnames" />:
    </form:label>
    <form:input path="surnames" />
    <form:errors cssClass="error" path="surnames" />
    <br>
    <form:label path="phone">
        <spring:message code="phone" />:
    </form:label>
    <form:input path="phone" />
    <form:errors cssClass="error" path="phone" />
    <br>
    <form:label path="address">
        <spring:message code="address" />:
    </form:label>
    <form:input path="address" />
    <form:errors cssClass="error" path="address" />

    <input type="submit" value="<spring:message code="edit.submit" />" />
</form:form>