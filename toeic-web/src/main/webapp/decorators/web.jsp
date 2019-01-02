<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<html>
<head>
    <title><dec:title default="Web page"></dec:title></title>
    <meta charset="UTF-8">
    <!-- Bootstrap -->
    <link href="<c:url value='/template/web/css/bootstrap.css'/>" rel="stylesheet">
    <link href="<c:url value='/template/web/css/bootstrap-responsive.css'/>" rel="stylesheet">
    <link href="<c:url value='/template/web/css/style.css'/>" rel="stylesheet">
    <!--Font-->
    <link href='http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,600' rel='stylesheet' type='text/css'>
    <!-- SCRIPT
    ============================================================-->
    <script src="http://code.jquery.com/jquery.js"></script>
    <script src="<c:url value='/template/web/js/bootstrap.min.js'/>"></script>
    <dec:head></dec:head>
</head>
<body>
<%@include file="/common/web/header.jsp"%>
<div class="container">
    <dec:body></dec:body>
</div>
<%@include file="/common/web/footer.jsp"%>
</body>
</html>
