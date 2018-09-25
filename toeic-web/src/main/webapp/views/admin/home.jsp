<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/tablib.jsp"%>
<html>
<head>
    <title><fmt:message key="label.admin.title" bundle="${lang}"/></title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
            </script>
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Home</a>
                </li>
                <li>
                    <a href="#">UI &amp; Elements</a>
                </li>
                <li class="active">Treeview</li>
            </ul>
            <!-- /.breadcrumb -->
            <div class="nav-search" id="nav-search">
                <form class="form-search">
                     <span class="input-icon">
                     <input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off">
                     <i class="ace-icon fa fa-search nav-search-icon"></i>
                     </span>
                </form>
            </div>
            <!-- /.nav-search -->
        </div>
        <div class="page-content">
            <div class="page-header">
                <h1>
                    Treeview
                    <small>
                        <i class="ace-icon fa fa-angle-double-right"></i>
                        with selectable items(single &amp; multiple) and custom icons
                    </small>
                </h1>
            </div>
            <!-- /.page-header -->
            <div class="row">
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="widget-box widget-color-blue2">
                                <div class="widget-header">
                                    <h4 class="widget-title lighter smaller">Choose Categories</h4>
                                </div>
                                <div class="widget-body">
                                    <div class="widget-main padding-8">
                                        <ul id="tree1" class="tree tree-selectable" role="tree">
                                            <li class="tree-branch hide" data-template="treebranch" role="treeitem" aria-expanded="false">
                                                <div class="tree-branch-header">					<span class="tree-branch-name">						<i class="icon-folder ace-icon tree-plus"></i>						<span class="tree-label"></span>					</span>				</div>
                                                <ul class="tree-branch-children" role="group"></ul>
                                                <div class="tree-loader hide" role="alert">
                                                    <div class="tree-loading"><i class="ace-icon fa fa-refresh fa-spin blue"></i></div>
                                                </div>
                                            </li>
                                            <li class="tree-item hide" data-template="treeitem" role="treeitem">				<span class="tree-item-name">				  <i class="icon-item ace-icon fa fa-times"></i>				  <span class="tree-label"></span>				</span>			</li>
                                            <li class="tree-branch" role="treeitem" aria-expanded="false">
                                                <div class="tree-branch-header">					<span class="tree-branch-name">						<i class="icon-folder ace-icon tree-plus"></i>						<span class="tree-label">For Sale</span>					</span>				</div>
                                                <ul class="tree-branch-children" role="group"></ul>
                                                <div class="tree-loader hide" role="alert">
                                                    <div class="tree-loading"><i class="ace-icon fa fa-refresh fa-spin blue"></i></div>
                                                </div>
                                            </li>
                                            <li class="tree-branch" role="treeitem" aria-expanded="false">
                                                <div class="tree-branch-header">					<span class="tree-branch-name">						<i class="icon-folder ace-icon tree-plus"></i>						<span class="tree-label">Vehicles</span>					</span>				</div>
                                                <ul class="tree-branch-children" role="group"></ul>
                                                <div class="tree-loader hide" role="alert">
                                                    <div class="tree-loading"><i class="ace-icon fa fa-refresh fa-spin blue"></i></div>
                                                </div>
                                            </li>
                                            <li class="tree-branch" role="treeitem" aria-expanded="false">
                                                <div class="tree-branch-header">					<span class="tree-branch-name">						<i class="icon-folder ace-icon tree-plus"></i>						<span class="tree-label">Rentals</span>					</span>				</div>
                                                <ul class="tree-branch-children" role="group"></ul>
                                                <div class="tree-loader hide" role="alert">
                                                    <div class="tree-loading"><i class="ace-icon fa fa-refresh fa-spin blue"></i></div>
                                                </div>
                                            </li>
                                            <li class="tree-branch" role="treeitem" aria-expanded="false">
                                                <div class="tree-branch-header">					<span class="tree-branch-name">						<i class="icon-folder ace-icon tree-plus"></i>						<span class="tree-label">Real Estate</span>					</span>				</div>
                                                <ul class="tree-branch-children" role="group"></ul>
                                                <div class="tree-loader hide" role="alert">
                                                    <div class="tree-loading"><i class="ace-icon fa fa-refresh fa-spin blue"></i></div>
                                                </div>
                                            </li>
                                            <li class="tree-branch" role="treeitem" aria-expanded="false">
                                                <div class="tree-branch-header">					<span class="tree-branch-name">						<i class="icon-folder ace-icon tree-plus"></i>						<span class="tree-label">Pets</span>					</span>				</div>
                                                <ul class="tree-branch-children" role="group"></ul>
                                                <div class="tree-loader hide" role="alert">
                                                    <div class="tree-loading"><i class="ace-icon fa fa-refresh fa-spin blue"></i></div>
                                                </div>
                                            </li>
                                            <li class="tree-item" role="treeitem">				<span class="tree-item-name">				  <i class="icon-item ace-icon fa fa-times"></i>				  <span class="tree-label">Tickets</span>				</span>			</li>
                                            <li class="tree-item" role="treeitem">				<span class="tree-item-name">				  <i class="icon-item ace-icon fa fa-times"></i>				  <span class="tree-label">Services</span>				</span>			</li>
                                            <li class="tree-item" role="treeitem">				<span class="tree-item-name">				  <i class="icon-item ace-icon fa fa-times"></i>				  <span class="tree-label">Personals</span>				</span>			</li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="widget-box widget-color-green2">
                                <div class="widget-header">
                                    <h4 class="widget-title lighter smaller">Browse Files</h4>
                                </div>
                                <div class="widget-body">
                                    <div class="widget-main padding-8">
                                        <ul id="tree2" class="tree tree-unselectable" role="tree">
                                            <li class="tree-branch hide" data-template="treebranch" role="treeitem" aria-expanded="false">
                                                <div class="tree-branch-header">					<span class="tree-branch-name">						<i class="icon-folder ace-icon fa fa-folder"></i>						<span class="tree-label"></span>					</span>				</div>
                                                <ul class="tree-branch-children" role="group"></ul>
                                                <div class="tree-loader hide" role="alert">
                                                    <div class="tree-loading"><i class="ace-icon fa fa-refresh fa-spin blue"></i></div>
                                                </div>
                                            </li>
                                            <li class="tree-item hide" data-template="treeitem" role="treeitem">				<span class="tree-item-name">				  				  <span class="tree-label"></span>				</span>			</li>
                                            <li class="tree-branch" role="treeitem" aria-expanded="false">
                                                <div class="tree-branch-header">					<span class="tree-branch-name">						<i class="icon-folder ace-icon fa fa-folder red"></i>						<span class="tree-label">Pictures</span>					</span>				</div>
                                                <ul class="tree-branch-children" role="group"></ul>
                                                <div class="tree-loader hide" role="alert">
                                                    <div class="tree-loading"><i class="ace-icon fa fa-refresh fa-spin blue"></i></div>
                                                </div>
                                            </li>
                                            <li class="tree-branch" role="treeitem" aria-expanded="false">
                                                <div class="tree-branch-header">					<span class="tree-branch-name">						<i class="icon-folder ace-icon fa fa-folder orange"></i>						<span class="tree-label">Music</span>					</span>				</div>
                                                <ul class="tree-branch-children" role="group"></ul>
                                                <div class="tree-loader hide" role="alert">
                                                    <div class="tree-loading"><i class="ace-icon fa fa-refresh fa-spin blue"></i></div>
                                                </div>
                                            </li>
                                            <li class="tree-branch" role="treeitem" aria-expanded="false">
                                                <div class="tree-branch-header">					<span class="tree-branch-name">						<i class="icon-folder ace-icon fa fa-folder blue"></i>						<span class="tree-label">Video</span>					</span>				</div>
                                                <ul class="tree-branch-children" role="group"></ul>
                                                <div class="tree-loader hide" role="alert">
                                                    <div class="tree-loading"><i class="ace-icon fa fa-refresh fa-spin blue"></i></div>
                                                </div>
                                            </li>
                                            <li class="tree-branch" role="treeitem" aria-expanded="false">
                                                <div class="tree-branch-header">					<span class="tree-branch-name">						<i class="icon-folder ace-icon fa fa-folder green"></i>						<span class="tree-label">Documents</span>					</span>				</div>
                                                <ul class="tree-branch-children" role="group"></ul>
                                                <div class="tree-loader hide" role="alert">
                                                    <div class="tree-loading"><i class="ace-icon fa fa-refresh fa-spin blue"></i></div>
                                                </div>
                                            </li>
                                            <li class="tree-branch" role="treeitem" aria-expanded="false">
                                                <div class="tree-branch-header">					<span class="tree-branch-name">						<i class="icon-folder ace-icon fa fa-folder"></i>						<span class="tree-label">Backup</span>					</span>				</div>
                                                <ul class="tree-branch-children" role="group"></ul>
                                                <div class="tree-loader hide" role="alert">
                                                    <div class="tree-loading"><i class="ace-icon fa fa-refresh fa-spin blue"></i></div>
                                                </div>
                                            </li>
                                            <li class="tree-item" role="treeitem">				<span class="tree-item-name">				  				  <span class="tree-label"><i class="ace-icon fa fa-file-text grey"></i> ReadMe.txt</span>				</span>			</li>
                                            <li class="tree-item" role="treeitem">				<span class="tree-item-name">				  				  <span class="tree-label"><i class="ace-icon fa fa-book blue"></i> Manual.html</span>				</span>			</li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <script type="text/javascript">
                        var $assets = "dist";//this will be used in fuelux.tree-sampledata.js
                    </script>
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