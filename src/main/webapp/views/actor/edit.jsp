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
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>

<h1>Edit Personal Info</h1>
<article class="col s3 navbar">
    <ul>
        <li><a href="${contextPath}/actor/edit.do?edit=personal">Edit Personal Data</a> </li>
<security:authorize access="hasAnyRole('NUTRITIONIST')">
        <li><a href="${contextPath}/actor/edit.do?edit=curriculum">Edit Curriculum</a> </li>
</security:authorize>
    </ul>
</article>
<article class="col s8 <jstl:if test="${edit  != 'personal'}" > hidden</jstl:if>" style="margin-left:5%">
    <form:form action="actor/edit.do" modelAttribute="actor" method="POST">
        <form:hidden path="id" />
        <h2>Personal data</h2>
        <input type="hidden" value="${role}" name="role"/>
        <div class="form-group-2">
            <form:label path="email">
                <spring:message code="email" />:
            </form:label>
            <form:input path="email" type="email" required="required"/>
            <form:errors cssClass="error" path="email" />
            <br>
            <form:label path="name">
                <spring:message code="name" />:
            </form:label>
            <form:input path="name" required="required" />
            <form:errors cssClass="error" path="name" />
            <br>
            <form:label path="surnames">
                <spring:message code="surnames" />:
            </form:label>
            <form:input path="surnames" required="required" />
            <form:errors cssClass="error"  path="surnames" />
            <br>
        </div>
        <div class="form-group-2">
            <form:label path="phone">
                <spring:message code="phone" />:
            </form:label>
            <form:input path="phone" pattern="((\+[0-9]{1,3})?\s*(\([0-9]{3}\))?\s*([a-zA-Z0-9\- ]{4,}))$" />
            <form:errors cssClass="error" path="phone" />
            <br>
            <form:label path="address">
                <spring:message code="address" />:
            </form:label>
            <form:input path="address" />
            <form:errors cssClass="error" path="address" />
        </div>
        <h2>Social Identities</h2>
        <div class="form-group-1 socialIdentity">
            <jstl:forEach var="si" items="${actor.socialIdentities}" varStatus="loop">
                <div class="card inline s5">
                    <h2>${socialIdentities[loop.index].name}</h2>
                    <form:hidden path="socialIdentities[${loop.index}].id"/>
                    <form:label path="socialIdentities[${loop.index}].name">
                        <spring:message code="name" />:
                    </form:label>
                    <form:errors cssClass="error" path="socialIdentities[${loop.index}].name" />
                    <form:input required="required" path="socialIdentities[${loop.index}].name" />
                    <form:label path="socialIdentities[${loop.index}].nick">
                        Nickname
                    </form:label>
                    <form:input  required="required" path="socialIdentities[${loop.index}].nick" />
                    <form:errors cssClass="error" path="socialIdentities[${loop.index}].nick" />
                    <form:label path="socialIdentities[${loop.index}].link">
                        <spring:message code="link" />:
                    </form:label>
                    <form:input type="url" equired="required"  path="socialIdentities[${loop.index}].link" />
                    <form:errors cssClass="error" path="socialIdentities[${loop.index}].link" />
                    <form:label path="socialIdentities[${loop.index}].picture">
                        <spring:message code="picture" />:
                    </form:label>
                    <form:input type="url" path="socialIdentities[${loop.index}].picture" />
                    <form:errors cssClass="error" path="socialIdentities[${loop.index}].picture" />
                </div>
            </jstl:forEach>

        </div>
        <button class="button" id="addSocialIdentity"><spring:message code="addSI"/></button>
        <input  name="save" type="submit" value="<spring:message code="save" />" />
        <a  href="${contextPath}" class="button cancel_button"><spring:message code="cancel" /></a>
    </form:form>
</article>
<article class="col s5 <jstl:if test="${edit  == 'personal'}" > hidden </jstl:if>">

</article>
<script>
    var size = ${fn:length(actor.socialIdentities)};
    $(document).ready(function(){
        $("#addSocialIdentity").on('click',function (e) {
            e.preventDefault();
            var html = `<div class="card inline s5">
        <label for="socialIdentities`+size+`.name">
        Name:
        </label>
        <input id="socialIdentities`+size+`.name" name="socialIdentities[`+size+`].name" required="required" type="text" value="">
        <label for="socialIdentities`+size+`.nick">
        Nickname
        </label>
        <input id="socialIdentities`+size+`.nick" name="socialIdentities[`+size+`].nick" required="required" type="text" value="">
        <label for="socialIdentities`+size+`.link">
        Link:
        </label>
        <input id="socialIdentities`+size+`.link" name="socialIdentities[`+size+`].link" type="text" >
        <label for="socialIdentities`+size+`.picture">
        Picture:
        </label>
        <input id="socialIdentities`+size+`.picture" name="socialIdentities[`+size+`].picture" type="text">
        </div>`;
            ++size;
            $(".socialIdentity").append(html);
        })
    })
</script>