<%@page language="java" contentType="text/html; charset=ISO-8859-1"
        pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<section class="main">
<h1>New Message</h1>

    <div class="form-group-1">
<form:form action="message/new.do" modelAttribute="newMessage" method="POST">
    <form:label path="recipients">
        <spring:message code="recipients" />:
    </form:label>
    <form:select path="recipients">
        <form:options items="${actors}" itemValue="id"
                      itemLabel="userAccount.username" />
    </form:select>
    <form:errors cssClass="error" path="recipients"  />
    <form:label path="priority">
        <spring:message code="priority" />:
    </form:label>
    <form:select path="priority">
        <form:option value="LOW" title="value"><spring:message code="low"/> </form:option>
        <form:option value="NEUTRAL" title="value">Neutral</form:option>
        <form:option value="HIGH" title="value"><spring:message code="high"/></form:option>
    </form:select>
    <form:errors cssClass="error" path="priority"  />
        <form:label path="subject">
            <spring:message code="subject" />
        </form:label>
        <form:input class="s8" path="subject" required="required" />
        <form:errors cssClass="error" path="subject" />
    <form:label path="body">
        <spring:message code="body" />:
    </form:label>
    <form:textarea class="s8" path="body" />
    <form:errors cssClass="error" path="body" />
    <input type="submit" name="send" value="<spring:message code="send" /> " />
</form:form>
    </div>
</section>

