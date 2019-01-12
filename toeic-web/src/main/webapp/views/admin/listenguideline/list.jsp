<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url value='/admin-guideline-listen-edit.html' var="listenGuidelineEditUrl">
    <c:param name="urlType" value="url_edit"></c:param>
</c:url>

<c:url value='/admin-guideline-listen-edit.html' var="listenGuidelineImportUrl">
    <c:param name="urlType" value="url_import"></c:param>
</c:url>

<c:url value="/admin-guideline-listen-list.html" var="listenGuidelineSearchUrl">
    <c:param name="urlType" value="url_list"></c:param>
</c:url>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title><fmt:message key="label.user.import" bundle="${lang}"/></title>
    <title><fmt:message key="label.guideline.listen.list" bundle="${lang}"/></title>
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
                    <fmt:message key="label.guideline.listen.list" bundle="${lang}"/>
                </li>
            </ul>
            <!-- /.breadcrumb -->
            <div class="nav-search" id="nav-search">
                <form class="form-search" action="${listenGuidelineSearchUrl}" method="get">
                    <input type="hidden" name="urlType" value="url_list">
                    <span class="input-icon">
                     <input type="text" name="pojo.title" value="${items.pojo.title}" placeholder="Search ..."
                            class="nav-search-input" id="nav-search-input"
                            autocomplete="off">
                     <i class="ace-icon fa fa-search nav-search-icon"></i>
                     </span>
                    <input type="submit" class="hidden">
                </form>
            </div>
            <!-- /.nav-search -->
        </div>
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->
                    <c:if test="${not empty messageResponse}">
                        <div class="alert alert-dismissible fade in alert-${alert}">
                            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                            <strong>${messageResponse}</strong>
                        </div>
                    </c:if>
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="pull-right">
                                <div class="btn-group btn-overlap">
                                    <div class="ColVis btn-group" title="" data-original-title="Show/hide columns">
                                        <a href="${listenGuidelineImportUrl}"
                                           class="ColVis_Button ColVis_MasterButton btn btn-success btn-sm btn-bold"
                                           style="display: flex; align-items: center">
                                            <span><i class="icon-only  ace-icon ace-icon fa fa-file-excel-o bigger-140"
                                                     style="padding-right: 5px"></i></span><fmt:message
                                                key="label.import"
                                                bundle="${lang}"/>
                                        </a>
                                        <a href="${listenGuidelineEditUrl}"
                                           class="ColVis_Button ColVis_MasterButton btn btn-primary btn-sm btn-bold"
                                           style="display: flex; align-items: center">
                                            <span><i class="icon-only  ace-icon ace-icon fa fa-plus bigger-140"
                                                     style="padding-right: 5px"></i></span><fmt:message
                                                key="label.add"
                                                bundle="${lang}"/>
                                        </a>
                                        <button class="ColVis_Button ColVis_MasterButton btn btn-danger btn-sm btn-bold"
                                                id="btnDeleteAll" style="display: flex; align-items: center" disabled
                                                onclick="btnClickEvent(this)">
                                            <span><i class="ace-icon fa fa-trash-o bigger-140"
                                                     style="padding-right: 5px"></i></span><fmt:message
                                                key="label.delete" bundle="${lang}"/></button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="table-responsive">
                        <form id="formDelete" action="${listenGuidelineSearchUrl}" method="get">
                            <fmt:bundle basename="ApplicationResources">
                                <display:table id="tableList" name="items.listResult" partialList="true"
                                               size="${items.totalItems}" pagesize="${items.maxPageItems}"
                                               class="table table-fcv-ace table-striped table-bordered table-hover dataTable no-footer"
                                               style="margin: 3em 0 1.5em;"
                                               sort="external"
                                               requestURI="">
                                    <display:column
                                            title="<input type='checkbox' class='ace check-box-element' id='chkCheckAll'><span class='lbl'></span>"
                                            class="center select-cell" headerClass="center select-cell">
                                        <input type="checkbox" class="ace check-box-element" name="checkList"
                                               id="checkbox_${tableList.listenGuideLineId}"
                                               value="${tableList.listenGuideLineId}">
                                        <span class="lbl"></span>
                                    </display:column>
                                    <display:column property="title" titleKey="label.guideline.title" sortable="true"
                                                    sortName="title"/>
                                    <display:column property="content" titleKey="label.guideline.content"
                                                    sortable="true"
                                                    sortName="content"/>
                                    <display:column titleKey="label.action">
                                        <c:url var="listenGuidelineRowEditUrl"
                                               value="/admin-guideline-listen-edit.html">
                                            <c:param name="urlType" value="url_edit"></c:param>
                                            <c:param name="pojo.listenGuideLineId"
                                                     value="${tableList.listenGuideLineId}"></c:param>
                                        </c:url>
                                        <div class="hidden-sm hidden-xs btn-group">
                                            <a href="${listenGuidelineRowEditUrl}" class="btn btn-xs btn-info"
                                               data-toggle="tooltip"
                                               title="<fmt:message key="label.edit" bundle="${lang}"/>">
                                                <i class="ace-icon fa fa-pencil bigger-120"></i>
                                            </a>
                                            <button class="btn btn-xs btn-danger" type="button"
                                                    onclick="btnClickEvent(this)"
                                                    data-id="${tableList.listenGuideLineId}"
                                                    data-toggle="tooltip"
                                                    title="<fmt:message key="label.delete" bundle="${lang}"/>">
                                                <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                            </button>
                                        </div>
                                    </display:column>
                                </display:table>
                            </fmt:bundle>
                            <input type="hidden" name="urlType" value="url_list">
                            <input type="hidden" name="crudAction" value="redirect_delete">
                        </form>
                    </div>
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
    <script type="application/javascript">
        function btnClickEvent(button) {
            if ($(button).data("id") != null) {
                $('#checkbox_' + $(button).data("id")).prop('checked', true);
            }

            var confirmCallback = function () {
                $('#formDelete').submit();
            };

            var cancelCallback = function () {
                $('#checkbox_' + $(button).data("id")).prop('checked', false);
            };

            showSweetAlertBeforeDelete(confirmCallback, cancelCallback);
        }
    </script>
</content>
</body>
</html>