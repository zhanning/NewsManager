<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
+ path + "/";
%>

<head>
	<title></title>
	<meta charset="utf-8">
    <link href="<%=basePath %>css/main.css" rel="stylesheet">
    <script type="text/javascript" src="<%=basePath %>ckeditor/ckeditor.js"></script>
</head>
<script type="text/javascript" src="<%=basePath%>jquery/jquery-3.1.1.js"></script>
<script type="text/javascript">
	function titleR(){
		var title = $("input:text[name=title]").val();
		if(!isNull(title)){
			alert("标题不能为空！")
		}
	}
	function isNull(title){
		if(title!=null&&title!=""){
			return true;
			
		}else{
			return false;
		}
	}
</script>
<body>
<!-- 新闻管理 -->
            <div class="AccountManagement_c" id="NewsManagement_c">
               
				<form action="Sea_New_Servlet" method="post">
                <h3>新闻管理 
                    <div class="amcl fr">
                        <select class="fl NewsManagement_search" name="column">
                            <c:forEach items="${rights }" var="r">
                        		 <option value="r${r.id }">${r.rname }</option>
                        </c:forEach>
                        </select>
                        <input type="text" placeholder="关键字" class="fl" name="sea_new">
                         <input type="submit" class="search fl" value="搜索"  style="width:50px;background:#fff;">
                    
                    </div> 
                </h3>
                </form>
                <div class="list" id="release_news"  style="height:1000px">
                    <ul class="list_h">
                        <li class="b80"><label>发布新闻</label></li>
                    </ul>
                    <!-- 发布新闻表单 -->
                    <form action="Add_n_New" method="post">
                    <div class="list_b_c" id="release_news_in">
                        <div class="release_news">
                            <div class="news_title">
                                <label class="text_center">标题</label>
                                <input type="text" placeholder="填写标题" name="title" onblur="titleR()">
                            </div>
                             <div class="column_name">
					            <label class="text_center">关键字设置</label>
					            <input type="t
					            
					            
					            .
					            .........................................................................................................................................................................................................................................
					            ext" placeholder="关键字设置" id="keyword" name="keyword" maxlength="20">
					        </div>
  							<div class="column_name">
					            <label class="text_center">是否置顶</label>
					            <select class="column_name_release" name="top">
					                <option value="0" selected>否</option>
					                <option value="1">是</option>
					            </select>
					        </div>
                            <div class="column_name">
                                <label class="text_center">栏目</label>
                                <select class="column_name_release" name="right">
                           		<c:forEach items="${rs }" var="right">
                        		 <option value="${right }">${right}</option>
                        </c:forEach>
                                </select>
                            </div>
                            
                            <textarea id="container" class="release_news_content" rows="50" cols="60" placeholder="编辑正文" name="detail">${n.detail }</textarea>     
                             <script type="text/javascript">
                				CKEDITOR.replace('container');
            				</script>    
                            <input type="submit" style="border:0" class="release_news_ok_btn text_center" value="确定">
                        </div>
                    </div>
                    </form>
                </div>
            </div>
</body>
</html>