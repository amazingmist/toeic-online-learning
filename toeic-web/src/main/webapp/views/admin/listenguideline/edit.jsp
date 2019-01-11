<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="formUrl" value="/admin-guideline-listen-edit.html"></c:url>
<html>
<head>
    <title><fmt:message key="label.guideline.listen.edit" bundle="${lang}"/></title>
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
                        <fmt:message key="label.guideline.listen" bundle="${lang}"/>
                    </a>
                </li>
                <li class="active">
                    <fmt:message key="label.guideline.listen.edit" bundle="${lang}"/>
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
                            <label for="title"><fmt:message key="label.guideline.title" bundle="${lang}"/></label>
                            <input type="text" class="form-control" id="title" name="pojo.title">
                        </div>
                        <div class="form-group">
                            <label for="inputFile"><fmt:message key="label.guideline.upload.image"
                                                                bundle="${lang}"/> </label>
                            <input type="file" id="inputFile" name="file">
                        </div>
                        <div class="form-group">
                            <label for="content"><fmt:message key="label.guideline.content"
                                                              bundle="${lang}"/></label>
                            <textarea class="form-control" rows="5" id="content" name="pojo.content"></textarea>
                        </div>
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
<content tag="local_script">
    <script type="application/javascript" src="/ckeditor/ckeditor.js"></script>
</content>
</body>
</html>
