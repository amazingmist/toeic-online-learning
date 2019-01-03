<%--
  Created by IntelliJ IDEA.
  User: vothanhtai
  Date: 1/3/19
  Time: 21:56
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<html>
<head>
    <title><fmt:message key="label.error" bundle="${lang}"/></title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->
                    <div class="error-container">
                        <div class="well">
                            <h1 class="grey lighter smaller">
                                        <span class="blue bigger-125">
                                            <i class="ace-icon fa fa-sitemap"></i>
                                            <fmt:message key="label.message.error" bundle="${lang}"/>
                                        </span>
                            </h1>

                            <hr/>
                            <h3 class="lighter smaller">${messageResponse}</h3>
                            <hr/>
                            <div class="space"></div>

                            <div class="center">
                                <a href="javascript:history.back()" class="btn btn-grey">
                                    <i class="ace-icon fa fa-arrow-left"></i>
                                    <fmt:message key="label.goBack" bundle="${lang}"/>
                                </a>
                            </div>
                        </div>
                    </div>

                    <!-- PAGE CONTENT ENDS -->
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div><!-- /.page-content -->
    </div>
</div><!-- /.main-content -->
</body>
</html>
