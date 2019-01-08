<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="requestURI" value="/admin-user-list.html">
    <c:if test="${empty param.urlType}">
        <c:param name="urlType" value="url_list"/>
    </c:if>
</c:url>
<c:url var="userAddNewUrl" value='/ajax-admin-user-edit.html'>
    <c:param name="urlType" value="url_edit"/>
</c:url>
<c:url var="userEditUrl" value="/ajax-admin-user-edit.html">
    <c:param name="urlType" value="url_edit"/>
    <c:param name="pojo.userId" value=""/>
</c:url>
<c:url var="importUserUrl" value='/admin-user-import.html'>
    <c:param name="urlType" value="url_import"/>
</c:url>

<!doctype html>
<html lang="en">
<head>
    <title><fmt:message key="label.user.management" bundle="${lang}"/></title>
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
                    <fmt:message key="label.user.list" bundle="${lang}"/>
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
                </div>
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
                                        <a href="${importUserUrl}"
                                           class="ColVis_Button ColVis_MasterButton btn btn-success btn-sm btn-bold"
                                           style="display: flex; align-items: center">
                                            <span><i class="icon-only  ace-icon ace-icon fa fa-file-excel-o bigger-140"
                                                     style="padding-right: 5px"></i></span><fmt:message
                                                key="label.import"
                                                bundle="${lang}"/>
                                        </a>
                                        <button onclick="showEditFormModal(this)"
                                                class="ColVis_Button ColVis_MasterButton btn btn-primary btn-sm btn-bold"
                                                id="btnAddNew" style="display: flex; align-items: center">
                                            <span><i class="icon-only  ace-icon ace-icon fa fa-plus bigger-140"
                                                     style="padding-right: 5px"></i></span><fmt:message key="label.add"
                                                                                                        bundle="${lang}"/>
                                        </button>
                                        <button class="ColVis_Button ColVis_MasterButton btn btn-danger btn-sm btn-bold"
                                                id="btnDeleteAll" style="display: flex; align-items: center" disabled>
                                            <span><i class="ace-icon fa fa-trash-o bigger-140"
                                                     style="padding-right: 5px"></i></span><fmt:message
                                                key="label.delete" bundle="${lang}"/></button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="table-responsive">
                        <fmt:bundle basename="ApplicationResources">
                            <display:table id="tableList" name="items.listResult" partialList="true"
                                           size="${items.totalItems}" pagesize="${items.maxPageItems}"
                                           class="table table-fcv-ace table-striped table-bordered table-hover dataTable no-footer"
                                           style="margin: 3em 0 1.5em;"
                                           sort="external"
                                           requestURI="${requestURI}">
                                <display:column
                                        title="<input type='checkbox' class='ace check-box-element' id='chkCheckAll'><span class='lbl'></span>"
                                        class="center select-cell" headerClass="center select-cell">
                                    <input type="checkbox" class="ace check-box-element" name="checkList"
                                           id="checkbox_${tableList.userId}">
                                    <span class="lbl"></span>
                                </display:column>
                                <display:column property="name" titleKey="label.user.name" sortable="true"
                                                sortName="name"/>
                                <display:column property="fullName" titleKey="label.user.fullName" sortable="true"
                                                sortName="fullName"/>
                                <display:column titleKey="label.action">

                                    <div class="hidden-sm hidden-xs btn-group">
                                        <button onclick="showEditFormModal(this)" data-id="${tableList.userId}"
                                                class="btn btn-xs btn-info" data-toggle="tooltip"
                                                title="<fmt:message key="label.edit" bundle="${lang}"/>">
                                            <i class="ace-icon fa fa-pencil bigger-120"></i>
                                        </button>
                                        <button class="btn btn-xs btn-danger" data-toggle="tooltip"
                                                title="<fmt:message key="label.delete" bundle="${lang}"/>">
                                            <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                        </button>
                                    </div>
                                </display:column>
                            </display:table>
                        </fmt:bundle>
                    </div>
                    <form action="${requestURI}" method="get" id="reloadAfterAction">
                        <input type="hidden" name="urlType" id="urlTypeReload">
                        <input type="hidden" name="crudAction" id="crudActionReload">
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
<!-- Modal -->
<div class="modal fade" id="addNewModal" role="dialog"></div>
<script type="application/javascript">
    $(document).ready(function () {

    });

    function showEditFormModal(btn) {
        var id = $(btn).data('id');
        var submitUrl = '${userAddNewUrl}';

        if (id != undefined) {
            submitUrl = '${userEditUrl}' + id;
        }

        $('#addNewModal').load(submitUrl, function () {
            $('#addNewModal').modal('toggle');
            addEditFormEvent();
        });
    }

    function addEditFormEvent() {
        $('#editUserForm').submit(function (evt) {
            evt.preventDefault();
            $('#crudActionEdit').val('insert_update');
            submitEditForm();
        });
    }

    function submitEditForm() {
        $.ajax({
            type: $('#editUserForm').attr("method"),
            url: $('#editUserForm').attr('action'),
            data: $('#editUserForm').serialize(),
            dataType: 'html',
            success: function (resp) {
                $('#urlTypeReload').val('url_list');
                $('#crudActionReload').val(resp.trim());
                $('#reloadAfterAction').submit();
            },
            error: function (resp) {
                console.log(resp);
            }
        });
    }
</script>
</body>
</html>