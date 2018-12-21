<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/tablib.jsp" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">&times;</button>
            <c:choose>
                <c:when test="${not empty item.pojo.userId}">
                    <h4 class="modal-title"><fmt:message key="label.user.edit" bundle="${lang}"/></h4>
                </c:when>
                <c:otherwise>
                    <h4 class="modal-title"><fmt:message key="label.user.add" bundle="${lang}"/></h4>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="modal-body">
            <div class="form-group">
                <label for="name"><fmt:message key="label.user.name" bundle="${lang}"/>:</label>
                <input type="text" class="form-control" id="name" value="${item.pojo.name}">
            </div>
            <div class="form-group">
                <label for="password"><fmt:message key="label.user.password" bundle="${lang}"/>:</label>
                <input type="password" class="form-control" id="password" value="${item.pojo.password}">
            </div>
            <div class="form-group">
                <label for="fullName"><fmt:message key="label.user.fullname" bundle="${lang}"/>:</label>
                <input type="text" class="form-control" id="fullName" value="${item.pojo.fullName}">
            </div>
            <div class="form-group">
                <label for="roleId"><fmt:message key="label.user.role" bundle="${lang}"/>:</label>
                <select class="form-control" id="roleId">
                    <c:forEach items="${item.roles}" var="role">
                        <option value="${role.roleId}"
                                <c:if test="${item.pojo.roleDTO.roleId eq role.roleId}">selected</c:if>>
                                ${role.name}
                        </option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default btn-sm" data-dismiss="modal"><fmt:message key="label.close"
                                                                                                   bundle="${lang}"/></button>
            <button type="button" class="btn btn-primary btn-sm" data-dismiss="modal"><fmt:message key="label.save"
                                                                                                   bundle="${lang}"/></button>
        </div>
    </div>
</div>
</body>
</html>
