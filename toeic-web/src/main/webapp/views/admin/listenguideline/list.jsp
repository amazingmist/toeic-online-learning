<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/tablib.jsp"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title><fmt:message key="label.guideline.listen.list" bundle="${lang}"/></title>
</head>
<body>
<display:table name="testList" sort="external" defaultsort="1" id="element">
    <display:column property="id" title="ID" sortable="true" sortName="id" />
    <display:column property="firstName" sortable="true" sortName="firstName" title="First Name" />
    <display:column property="lastName" sortable="true" sortName="lastName" title="Last Name" />
    <display:column property="address" sortable="true" sortName="address" title="Email Address"/>
</display:table>
</body>
</html>