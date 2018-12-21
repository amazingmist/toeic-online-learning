<c:url value='/admin-guideline-listen-list.html' var="listenGuidelineListUrl">
    <c:param name="urlType" value="url_list"></c:param>
</c:url>

<c:url value='/admin-user-list.html' var="userListUrl">
    <c:param name="urlType" value="url_list"></c:param>
</c:url>
<div id="sidebar" class="sidebar responsive" data-sidebar="true" data-sidebar-scroll="true" data-sidebar-hover="true">
    <script type="text/javascript">
        try {
            ace.settings.check('sidebar', 'fixed')
        } catch (e) {
        }
    </script>
    <div class="sidebar-shortcuts" id="sidebar-shortcuts">
        <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
            <button class="btn btn-success">
                <i class="ace-icon fa fa-signal"></i>
            </button>
            <button class="btn btn-info">
                <i class="ace-icon fa fa-pencil"></i>
            </button>
            <button class="btn btn-warning">
                <i class="ace-icon fa fa-users"></i>
            </button>
            <button class="btn btn-danger">
                <i class="ace-icon fa fa-cogs"></i>
            </button>
        </div>
        <div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
            <span class="btn btn-success"></span>
            <span class="btn btn-info"></span>
            <span class="btn btn-warning"></span>
            <span class="btn btn-danger"></span>
        </div>
    </div>

    <!-- /.sidebar-shortcuts -->
    <ul class="nav nav-list" style="top: 0px;">
        <%-- huong dan nghe --%>
        <li class="">
            <a href="#" class="dropdown-toggle">
                <i class="menu-icon fa fa-headphones"></i>
                <span class="menu-text">
                <fmt:message key="label.guideline.listen" bundle="${lang}"/>
                </span>
                <b class="arrow fa fa-angle-down"></b>
            </a>
            <b class="arrow"></b>
            <ul class="submenu">
                <li class="">
                    <a href="${listenGuidelineListUrl}">
                        <i class="menu-icon fa fa-caret-right"></i>
                        <fmt:message key="label.guideline.listen.list" bundle="${lang}"/>
                    </a>
                    <b class="arrow"></b>
                </li>
            </ul>
        </li>

        <%-- quan ly nguoi dung--%>
        <li class="">
            <a href="#" class="dropdown-toggle">
                <i class="menu-icon fa fa-list"></i>
                <span class="menu-text">
                <fmt:message key="label.dashboard" bundle="${lang}"/>
                </span>
                <b class="arrow fa fa-angle-down"></b>
            </a>
            <b class="arrow"></b>
            <ul class="submenu">
                <li class="">
                    <a href="${userListUrl}">
                        <i class="menu-icon fa fa-caret-right"></i>
                        <fmt:message key="label.user.management" bundle="${lang}"/>
                    </a>
                    <b class="arrow"></b>
                </li>
            </ul>
        </li>
    </ul>
    <!-- /.nav-list -->
    <div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
        <i class="ace-icon fa fa-angle-double-left" data-icon1="ace-icon fa fa-angle-double-left"
           data-icon2="ace-icon fa fa-angle-double-right"></i>
    </div>
    <script type="text/javascript">
        try {
            ace.settings.check('sidebar', 'collapsed')
        } catch (e) {
        }
    </script>
</div>