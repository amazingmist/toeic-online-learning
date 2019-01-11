<%--
  Created by IntelliJ IDEA.
  User: vothanhtai
  Date: 1/3/19
  Time: 15:26
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="validateFormUrl" value="/admin-user-import-validate.html"></c:url>
<c:url var="doImportUrl" value="/admin-user-import.html"></c:url>
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
                    <form action="${validateFormUrl}" enctype="multipart/form-data" method="post"
                          onsubmit="return checkIsSeletedFile();">
                        <div class=" form-group
                    ">
                            <label for="inputFile"><fmt:message key="label.import.excel.title"
                                                                bundle="${lang}"/> </label>
                            <input type="file" id="inputFile" name="file">
                        </div>
                        <input type="hidden" name="urlType" value="url_validate_import">
                        <button type="submit" id="validateFile" class="btn btn-primary btn-sm" disabled>
                            <fmt:message key="label.import.validate" bundle="${lang}"/></button>
                    </form>
                    <!-- PAGE CONTENT ENDS -->
                </div>
                <!-- /.col -->
            </div>
            <!-- /.row -->

            <c:if test="${not empty items.userImportDTOS}">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="table-responsive">
                            <fmt:bundle basename="ApplicationResources">
                                <display:table id="tableList" name="items.userImportDTOS" partialList="true"
                                               size="${items.totalItems}" pagesize="${items.maxPageItems}"
                                               class="table table-fcv-ace table-striped table-bordered table-hover dataTable no-footer"
                                               style="margin: 3em 0 1.5em;"
                                               sort="external"
                                               requestURI="">
                                    <display:column property="name" titleKey="label.user.name"
                                                    class="${tableList.isValid ? '' : 'danger'}"/>
                                    <display:column property="password" titleKey="label.user.password"
                                                    class="${tableList.isValid ? '' : 'danger'}"/>
                                    <display:column property="fullName" titleKey="label.user.fullName"
                                                    class="${tableList.isValid ? '' : 'danger'}"/>
                                    <display:column property="roleName" titleKey="label.user.role"
                                                    class="${tableList.isValid ? '' : 'danger'}"/>
                                    <display:column property="error" titleKey="label.import.error.info"
                                                    class="${tableList.isValid ? '' : 'danger'}"/>
                                </display:table>
                            </fmt:bundle>
                        </div>
                        <form action="${doImportUrl}" method="post">
                            <input type="hidden" name="urlType" value="url_import">
                            <button type="submit" class="btn btn-success btn-sm"><fmt:message
                                    key="label.import.validate.done"
                                    bundle="${lang}"/></button>
                        </form>
                    </div>
                    <!-- /.col -->
                </div>
                <!-- /.row -->
            </c:if>
        </div>
        <!-- /.page-content -->
    </div>
</div>

<content tag="local_script">
    <script type="application/javascript">
        $(document).ready(function () {
            $('#inputFile').change(function () {
                if ($('#inputFile').val() != '') {
                    $('#validateFile').attr('disabled', false);
                }
            })
        })

        function checkIsSeletedFile() {
            if ($('#inputFile').val() == '') {
                alert("Vui lòng chọn file cần import");
                $('#validateFile').attr('disabled', true);
                return false;
            }

            return true;
        }
    </script>
</content>
</body>
</html>
