<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>新闻发布管理平台</title>
<meta name="description" content="">
<meta name="keywords" content="">
<!--导入样式表  -->
<link href="css/main.css" rel="stylesheet">
</head>
<body>
	<!--顶栏  -->
    <div class="header">
    <!--顶栏内  -->
        <div class="header_in">
        <!--logo  -->
            <img src="images/tit.png">
            <!-- 退出按钮 -->
            <a class="quit text_center" href="<c:url value="ExitServlet" />">安全退出</a>
        </div>
        
    </div>
	<!-- 主体 -->
    <div class="content ">
    <!-- 当前用户 -->
        <div class="user">
        <!-- 显示 -->
            <div class="user_status fl text_center">当前用户： <span>
            <!-- 判断用户 -->
            <c:choose>
            <c:when test="${user.getName()=='admin' }">
            	${user.getName() }(管理员)
            </c:when>
           	<c:otherwise>
           		${user.getName() }(普通用户)
           	</c:otherwise>
            </c:choose>
            </span></div>
            
            <div class="user_location fl">当前： 首页-<span>账户管理</span>  </div>
        </div>
        <div class="nav_side text_center fl">
            <div class="nav AccountManagement active">账户管理</div>
            <div class="nav ColumnManagement" >栏目管理</div>
            <div class="nav NewsManagement">新闻管理</div>
        </div>
        <div class="main_fx">
            <div class="AccountManagement_c_iframe">
                <iframe src="UServlet"></iframe>
            </div>

        </div>
    </div>
</body>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/main.js"></script>

</html>