<%--
  Created by IntelliJ IDEA.
  User: vothanhtai
  Date: 1/3/19
  Time: 15:26
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="formUrl" value="/admin-user-import.html"></c:url>
<html>
<head>
    <title><fmt:message key="label.user.import" bundle="${lang}"/></title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try {
                    ace.settings.check('breadcrumbs', 'fixed')
                } catch (e) {
                }
            </script>
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Home</a>
                </li>
                <li>
                    <a href="#">
                        <fmt:message key="label.user.management" bundle="${lang}"/>
                    </a>
                </li>
                <li class="active">
                    <fmt:message key="label.user.import" bundle="${lang}"/>
                </li>
            </ul>
            <!-- /.breadcrumb -->
            <div class="nav-search" id="nav-search">
                <form class="form-search">
                     <span class="input-icon">
                     <input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input"
                            autocomplete="off">
                     <i class="ace-icon fa fa-search nav-search-icon"></i>
                     </span>
                </form>
            </div>
            <!-- /.nav-search -->
        </div>
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->
                    <form action="${formUrl}" enctype="multipart/form-data" method="post">
                        <div class="form-group">
                            <label for="inputFile"><fmt:message key="label.import.excel.title"
                                                                bundle="${lang}"/> </label>
                            <input type="file" id="inputFile" name="file">
                        </div>
                        <input type="hidden" name="urlType" value="url_import">
                        <button type="submit" class="btn btn-default btn-sm"><fmt:message key="label.done"
                                                                                          bundle="${lang}"/></button>
                    </form>
                    <!-- PAGE CONTENT ENDS -->
                </div>
                <!-- /.col -->
            </div>
            <!-- /.row -->
        </div>
        <!-- /.page-content -->
    </div>
</div>
</body>
</html>
