<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/tablib.jsp" %>
<c:url var="submitUrl" value="/ajax-admin-user-edit.html">
    <c:param name="urlType" value="url_edit"></c:param>
</c:url>
<c:choose>
    <c:when test="${not empty messageResponse}">
        ${messageResponse}
    </c:when>
    <c:otherwise>
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
                <form action="${submitUrl}" method="post" id="edit-user-form">
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="name"><fmt:message key="label.user.name" bundle="${lang}"/>:</label>
                            <input type="text" class="form-control" id="name" value="${item.pojo.name}"
                                   name="pojo.name" required>
                        </div>
                        <div class="form-group">
                            <label for="password"><fmt:message key="label.user.password" bundle="${lang}"/>:</label>
                            <input type="password" class="form-control" id="password" value="${item.pojo.password}"
                                   name="pojo.password" required>
                        </div>
                        <div class="form-group">
                            <label for="fullName"><fmt:message key="label.user.fullname" bundle="${lang}"/>:</label>
                            <input type="text" class="form-control" id="fullName" value="${item.pojo.fullName}"
                                   name="pojo.fullName" required>
                        </div>
                        <div class="form-group">
                            <label for="roleId"><fmt:message key="label.user.role" bundle="${lang}"/>:</label>
                            <select class="form-control" id="roleId" name="roleId">
                                <c:forEach items="${item.roles}" var="role">
                                    <option value="${role.roleId}"
                                            <c:if test="${item.pojo.roleDTO.roleId eq role.roleId}">selected</c:if>>
                                            ${role.name}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                        <c:if test="${not empty item.pojo.userId}">
                            <input type="hidden" name="pojo.userId" value="${item.pojo.userId}">
                        </c:if>
                        <input type="hidden" name="crudAction" id="crudactionEdit">

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default btn-sm" data-dismiss="modal" id="btn-close">
                            <fmt:message key="label.close" bundle="${lang}"/></button>
                        <button type="submit" class="btn btn-primary btn-sm" id="btn-save">
                            <fmt:message key="label.save" bundle="${lang}"/></button>
                    </div>
                </form>
            </div>
        </div>
    </c:otherwise>
</c:choose>
