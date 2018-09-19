<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/tablib.jsp" %>
<html>
<head>
    <title><dec:title default="Login page"></dec:title></title>
    <!-- bootstrap & fontawesome -->
    <link rel="stylesheet" href="<c:url value='/template/admin/css/bootstrap.min.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/template/admin/font-awesome/4.2.0/css/font-awesome.min.css' />"/>
    <!-- page specific plugin styles -->
    <!-- text fonts -->
    <link rel="stylesheet" href="<c:url value='/template/admin/fonts/fonts.googleapis.com.css'/>"/>
    <!-- ace styles -->
    <link rel="stylesheet" href="<c:url value='/template/admin/css/ace.min.css'/>" class="ace-main-stylesheet"
          id="main-ace-style"/>
    <dec:head></dec:head>
</head>
<body class="login-layout">
<div class="main-container">
    <div class="main-content">
        <div class="row">
            <div class="col-sm-10 col-sm-offset-1">
                <div class="login-container">
                    <%@include file="/common/login/header.jsp"%>
                    <div class="position-relative">
                        <dec:body></dec:body>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- ace settings handler -->
<script src="<c:url value='/template/admin/js/jquery.2.1.1.min.js'/>"></script>
<script src="<c:url value='/template/admin/js/ace-extra.min.js'/>"></script>
<script src="<c:url value='/template/admin/js/bootstrap.min.js'/>"></script>

<!-- page specific plugin scripts -->
<!--[if lte IE 8]>
<script src="<c:url value='/template/admin/js/excanvas.min.js'/>"></script>
<![endif]-->

<script src="<c:url value='/template/admin/js/jquery-ui.custom.min.js'/>"></script>
<script src="<c:url value='/template/admin/js/jquery.ui.touch-punch.min.js'/>"></script>
<script src="<c:url value='/template/admin/js/jquery.easypiechart.min.js'/>"></script>
<script src="<c:url value='/template/admin/js/jquery.sparkline.min.js'/>"></script>
<script src="<c:url value='/template/admin/js/jquery.flot.min.js'/>"></script>
<script src="<c:url value='/template/admin/js/jquery.flot.pie.min.js'/>"></script>
<script src="<c:url value='/template/admin/js/jquery.flot.resize.min.js'/>"></script>


<!-- ace scripts -->
<script src="<c:url value='/template/admin/js/ace-elements.min.js'/>"></script>
<script src="<c:url value='/template/admin/js/ace.min.js'/>"></script>
<script type="text/javascript">
    jQuery(function($) {
        $('body').attr('class', 'login-layout blur-login');
        $('#id-text2').attr('class', 'white');
        $('#id-company-text').attr('class', 'light-blue');
    });
</script>
</body>
</html>
