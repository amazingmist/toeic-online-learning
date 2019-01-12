<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="formUrl" value="/admin-guideline-listen-edit.html"></c:url>
<html>
<head>
    <title><fmt:message key="label.guideline.listen.edit" bundle="${lang}"/></title>
    <style>
        #guidelineImage {
            display: none;
        }

        .imagePreviewWrapper {
            width: 150px;
            height: 150px;
            background: url("/template/admin/image/icon_preview.png") center;
            background-size: cover;
            border: 2px dashed #909090;
            cursor: pointer;
        }

        #imagePreview {
            width: 100%;
            height: 100%;
            object-fit: cover;
        }
    </style>
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
                    <form action="${formUrl}" enctype="multipart/form-data" method="post" id="editingForm">
                        <c:if test="${not empty item.pojo.listenGuideLineId}">
                            <input type="hidden" name="pojo.listenGuideLineId" value="${item.pojo.listenGuideLineId}">
                        </c:if>
                        <div class="row">
                            <div class="col-xs-6">
                                <div class="form-group">
                                    <label for="guidelineTitle"><fmt:message key="label.guideline.title"
                                                                             bundle="${lang}"/></label>
                                    <input type="text" class="form-control" id="guidelineTitle" name="pojo.title"
                                           value="${item.pojo.title}">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="guidelineImage"><fmt:message key="label.guideline.upload.image"
                                                                     bundle="${lang}"/> </label>
                            <input type="file" id="guidelineImage" name="file">
                            <div class="imagePreviewWrapper">
                                <c:if test="${not empty item.pojo.image}">
                                    <c:url value="/file_upload/listenguideline/${item.pojo.image}"
                                           var="imageUrl"></c:url>
                                </c:if>
                                <img src="${imageUrl}" alt="" id="imagePreview">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="guidelineContent"><fmt:message key="label.guideline.content"
                                                                       bundle="${lang}"/></label>
                            <textarea class="form-control" rows="20" id="guidelineContent"
                                      name="pojo.content">${item.pojo.content}</textarea>
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
    <script type="application/javascript" src="/template/library/ckeditor/ckeditor.js"></script>
    <script type="application/javascript" src="/template/admin/assets/js/jquery.validate.min.js"></script>

    <script type="application/javascript">
        $(document).ready(function () {
            CKEDITOR.replace('guidelineContent');
            validateFormInput();
            getImagePreview();
        })

        function getImagePreview() {
            $(".imagePreviewWrapper").click(function () {
                $("#guidelineImage").trigger('click');
            })

            $("#guidelineImage").change(function () {
                readURL(this);
            })
        }

        function readURL(input) {
            if (input.files && input.files[0]) {
                var reader = new FileReader();

                reader.onload = function (e) {
                    $('#imagePreview').attr('src', reader.result);
                }

                reader.readAsDataURL(input.files[0]);
            }
        }

        function validateFormInput() {
            $('#editingForm').validate({
                ignore: [],
                rules: [],
                messages: []
            });

            $("#guidelineTitle").rules("add", {
                required: true,
                minlength: 5,
                maxlength: 255,
                messages: {
                    required: '<fmt:message key="label.input.empty" bundle="${lang}"/>',
                    minlength: jQuery.validator.format('<fmt:message key="label.input.minlenght" bundle="${lang}"/>'),
                    maxlength: jQuery.validator.format('<fmt:message key="label.input.maxlenght" bundle="${lang}"/>')
                }
            });

            <c:if test="${empty item.pojo.listenGuideLineId}">
            $("#guidelineImage").rules("add", {
                required: true,
                messages: {
                    required: '<fmt:message key="label.input.empty" bundle="${lang}"/>',
                }
            });
            </c:if>


            $("#guidelineContent").rules("add", {
                required: function () {
                    CKEDITOR.instances.guidelineContent.updateElement();
                },
                messages: {
                    required: '<fmt:message key="label.input.empty" bundle="${lang}"/>',
                }
            });
        }
    </script>
</content>
</body>
</html>
