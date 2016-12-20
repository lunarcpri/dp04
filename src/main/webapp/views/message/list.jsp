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
    <article class="col s2 folder-list">
        <ul>
            <jstl:forEach items="${folders}" var="item">
            <li><a href="${contextPath}/message/list.do?folderId=${item.id}">${item.name}</a></li>
            </jstl:forEach>
        </ul>
    </article>
    <article class="col s9 message-list">
        <ul class="horizontal-list message-list-options">
            <li><a href="${contextPath}/message/new.do"><i class="fa fa-plus"></i> New Message</a></li>
        </ul>
    <h1>${folder.name}</h1>
        <display:table pagesize="5" class="displaytag" keepStatus="true"
                       name="messageList" requestURI="${requestURI}" id="row">


            <spring:message code="subject" var="subjectHeader" />
            <display:column property="subject" title="${subjectHeader}" sortable="true" />

            <spring:message code="sender" var="senderHeader" />
            <display:column title="${senderHeader}">
                <a href="http://localhost:8080/user/${row.sender.id}.do">${row.sender.userAccount.username}</a>
            </display:column>
            <spring:message code="recipients" var="recipientsHeader" />
            <display:column title="${recipientsHeader}">
                <jstl:forEach items="${row.recipients}" var="recipient">
                    <a href="${contextPath}/actor/${recipient.id}.do">${recipient.userAccount.username}</a>,
                </jstl:forEach>
            </display:column>
            <spring:message code="sendedat" var="sendedatHeader" />
            <display:column property="sended_at" title="${sendedatHeader}" sortable="true" format="{0,date,dd/MM/yyyy HH:mm}"/>
            <spring:message code="browse" var="browseHeader" />
            <display:column title="${browseHeader}">
                <a href="http://localhost:8080/message/${row.id}.do">${browseHeader}</a>
            </display:column>
        </display:table>
    </article>
</section>

