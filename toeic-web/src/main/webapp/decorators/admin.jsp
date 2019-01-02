<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>
        <dec:title default="Admin page"></dec:title>
    </title>
    <meta charset="utf-8"/>
    <!-- bootstrap & fontawesome -->
    <link rel="stylesheet" href="<c:url value='/template/admin/assets/css/bootstrap.min.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/template/admin/assets/font-awesome/4.2.0/css/font-awesome.min.css' />"/>
    <!-- page specific plugin styles -->
    <!-- text fonts -->
    <link rel="stylesheet" href="<c:url value='/template/admin/assets/fonts/fonts.googleapis.com.css'/>"/>
    <!-- ace styles -->
    <link rel="stylesheet" href="<c:url value='/template/admin/assets/css/ace.min.css'/>" class="ace-main-stylesheet"
          id="main-ace-style"/>
    <%-- jquery library --%>
    <script src="<c:url value='/template/admin/assets/js/jquery.2.1.1.min.js'/>"></script>
    <%-- my custome css --%>
    <link rel="stylesheet" href="<c:url value="/template/admin/css/global_admin_style.css"/>">
    <dec:head></dec:head>
</head>
<body class="no-skin">
<%@include file="/common/admin/header.jsp" %>
<div class="main-container" id="main-container">
    <script type="text/javascript">
        try {
            ace.settings.check('main-container', 'fixed')
        } catch (e) {
        }
    </script>
    <%@include file="/common/admin/menu.jsp" %>
    <dec:body></dec:body>
    <%@include file="/common/admin/footer.jsp" %>
    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
        <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
    </a>
</div>
<!-- ace settings handler -->
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
<%-- my custom js --%>
<script src="<c:url value="/template/admin/js/global_admin_script.js"/>"></script>
</body>
</html>