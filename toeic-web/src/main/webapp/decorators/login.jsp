<%@include file="/common/tablib.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title><dec:title default="Login page"></dec:title></title>
    <!-- bootstrap & fontawesome -->
    <link rel="stylesheet" href="<c:url value='/template/admin/assets/css/bootstrap.min.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/template/admin/assets/font-awesome/4.2.0/css/font-awesome.min.css' />"/>
    <!-- page specific plugin styles -->
    <!-- text fonts -->
    <link rel="stylesheet" href="<c:url value='/template/admin/assets/fonts/fonts.googleapis.com.css'/>"/>
    <!-- ace styles -->
    <link rel="stylesheet" href="<c:url value='/template/admin/assets/css/ace.min.css'/>" class="ace-main-stylesheet"
          id="main-ace-style"/>
    <dec:head></dec:head>
</head>
<body class="login-layout blur-login">
<div class="main-container">
    <div class="main-content">
        <div class="row">
            <div class="col-sm-10 col-sm-offset-1">
                <div class="login-container">
                    <%@include file="/common/login/header.jsp" %>
                    <div class="position-relative">
                        <dec:body></dec:body>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- ace settings handler -->
<script src="<c:url value='/template/admin/assets/js/jquery.2.1.1.min.js'/>"></script>
<script src="<c:url value='/template/admin/assets/js/ace-extra.min.js'/>"></script>
<script src="<c:url value='/template/admin/assets/js/bootstrap.min.js'/>"></script>

<!-- page specific plugin scripts -->
<!--[if lte IE 8]>
<script src="<c:url value='/template/admin/assets/js/excanvas.min.js'/>"></script>
<![endif]-->

<script src="<c:url value='/template/admin/assets/js/jquery-ui.custom.min.js'/>"></script>
<script src="<c:url value='/template/admin/assets/js/jquery.ui.touch-punch.min.js'/>"></script>
<script src="<c:url value='/template/admin/assets/js/jquery.easypiechart.min.js'/>"></script>
<script src="<c:url value='/template/admin/assets/js/jquery.sparkline.min.js'/>"></script>
<script src="<c:url value='/template/admin/assets/js/jquery.flot.min.js'/>"></script>
<script src="<c:url value='/template/admin/assets/js/jquery.flot.pie.min.js'/>"></script>
<script src="<c:url value='/template/admin/assets/js/jquery.flot.resize.min.js'/>"></script>


<!-- ace scripts -->
<script src="<c:url value='/template/admin/assets/js/ace-elements.min.js'/>"></script>
<script src="<c:url value='/template/admin/assets/js/ace.min.js'/>"></script>
<script type="text/javascript">
    jQuery(function ($) {
        $(document).on('click', '.toolbar a[data-target]', function (e) {
            e.preventDefault();
            var target = $(this).data('target');
            $('.widget-box.visible').removeClass('visible');//hide others
            $(target).addClass('visible');//show target
        });
    });
</script>
</body>
</html>
