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
  	<script type="text/javascript" src="<%=basePath %>jquery/jquery-3.1.1.js"></script>
</head>
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
                <div class="list" id="release_news"style="height:1000px">
                    <ul class="list_h">
                        <li class="b80"><label>编辑新闻</label></li>
                    </ul>
                    <div class="list_b_c" id="release_news_in">
                   	<form action="<c:url value="Edit_n_New"/>" method="post">
                   	
                        <div class="release_news" >
                            <div class="news_title">
                                <label class="text_center">标题</label>
                                <input type="text" name="id" value="${n.id }" class="dd">
                                <input type="text" placeholder="填写标题" name="title" value="${n.title }">
                            </div>
                            <div class="news_title">
                               		<p style="padding:5px 5px;margin:  0 20px" id="old_title">原标题:${n.title }</p>
                            </div>
                            <div class="column_name">
					            <label class="text_center">关键字设置</label>
					            <input type="text" placeholder="关键字设置" id="keyword" name="keyword" maxlength="20" value="${n.keyword }">
					        </div>
  							<div class="column_name">
					            <label class="text_center">是否置顶</label>
					            <select class="column_name_release" name="top">
					            <c:choose >
					            <c:when test="${n.top==1 }">
					              <option value="1" selected>是</option>
					                <option value="0">否</option>
					            </c:when>
					            <c:otherwise>
					             <option value="0" selected>否</option>
					                <option value="1">是</option>
					            </c:otherwise>
					            </c:choose>
					            </select>
					        </div>
                            <div class="column_name">
                                <label class="text_center">栏目</label>
                                <select class="column_name_release" name="right">
                                  <c:forEach items="${rights }" var="r">
                        		 <c:choose><c:when test="${n.id==r.id }"><option selected>${r.rname }</option></c:when>
                        		 	<c:otherwise><option  >${r.rname }</option></c:otherwise>
                        		 </c:choose>
                        		 
                        				</c:forEach>
                                </select>
                            </div>
                            <textarea id="container" class="release_news_content" rows="50" cols="60" placeholder="编辑正文" name="detail" >${n.detail }</textarea>     
                             <script type="text/javascript">
                						CKEDITOR.replace('container');
            				</script>
                            <input type="submit" class="release_news_ok_btn text_center" value="确定修改" style="width:80px;height:30px;border:0 ">
                        </div>
                       </form>
                    </div>
                </div>
            </div>
</body>
</html>