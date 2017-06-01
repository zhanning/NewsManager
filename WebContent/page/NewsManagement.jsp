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

	<meta charset="utf-8">
	<link href="<%=basePath %>css/main.css" rel="stylesheet">
</head>
<script type="text/javascript" src="<%=basePath %>jquery/jquery-3.1.1.js"></script>
<body>
    <!-- 栏目管理 -->
            <div class="AccountManagement_c" id="NewsManagement_c">
                <h3>新闻管理 
                    <div class="amcl fr">
                        <select class="fl NewsManagement_search">
                        <c:forEach items="${pn.rights }" var="r">
                        		 <option>${r.rname }</option>
                        </c:forEach>
                        </select>
                        <input type="text" placeholder="栏目名" class="fl" name="">
                        <%-- <div class="search fl"><img src="<%=basePath %>images/search.png"></div> --%>
                         <input type="submit" class="search fl" value="搜索"  style="width:50px;background:url(search.png) no-repeat;">
                    </div> 
                    </h3>
                <div class="AM_ct text_center">
                    <div class="AM_ct_in">
                        <a class="add_btn df_btn fl" id="add_column_btn" href="Add_New_Servlet">添加</a>
                        <a class="edit_btn df_btn fl" id="edit_column_btn" href="Edit_New_Servlet">编辑</a>
                        <a class="delete_btn df_btn fl" id="delete_column_btn" href="Del_New_Servlet">删除</a>
                    </div>
                </div>

                <div class="list">
                    <ul class="list_h">
                        <li class="b20"><label><input type="checkbox" name=""><span>序号</span></label></li>
                        <li class="b20"><label>新闻标题</label></li>
                        <li class="b20"><label>发布时间</label></li>
                        <li class="b20"><label>修改时间</label></li>
                    </ul>
                    <div class="list_b_c">
                   
 						<c:forEach items="${pn.getNews() }" var="n" varStatus="i">
                       <ul class="list_b">
                            <li class="b20"><label><input type="checkbox" name="p" id="n${n.getId() }" ><span>${n.getId()}</span></label></li>
                            <li class="b20"><label>${n.getTitle() }</label></li>
                            <li class="b20"><label>${n.getUdate()}</label></li>
                            <li class="b20"><label>${n.getCdate()}</label></li>
                        </ul>
                       </c:forEach>
                    </div>
                  <div class="pull_page">
                        <div class="fl pull_page_up">上一页</div>
                            <ul>
                            <!-- 这里只是定义了两个变量 begin and end -->
  <c:choose>
      <%-- 不够10页，显示所有的页码 --%>
      <c:when test="${pn.getTotalPage() <=10 }">
        <c:set var = "begin" value="1"/>
        <c:set var = "end" value="${pn.getTotalPage() }"/>
      </c:when>
     	<c:otherwise><!-- 12页  当前页为3页 -->
      	<c:set var = "begin" value="${pn.getPageCode() -5}"/>
        <c:set var = "end" value="${pn.getPageCode() +4 }"/>
        <!-- 头部溢出 -->
        <c:if test="${begin < 1 }">  
          <c:set var = "begin" value="1"/>
          <c:set var = "end" value="10"/>
        </c:if>
        <!-- 尾部溢出 -->
         <c:if test="${end > pn.getTotalPage() }">  
          <c:set var = "begin" value="#{pn.getTotalPage() -9}"/>
          <c:set var = "end" value="#{pn.getTotalPage() }"/>
        </c:if>
     </c:otherwise>
  </c:choose>
 
  <!-- 输出数字  -->
     <c:forEach begin="${begin }" end="${end }" var="i">
     <c:choose>
     <%-- 判断数字 = pagecode时，添加不同的样式 -  eq：等于的意思--%>
        <c:when test="${i eq pn.getPageCode() }">
           <li class="on" onclick="get(${i});">${i}</li>
        </c:when>
        <c:otherwise>
        <li onclick="get(${i});">${i }</li>
        </c:otherwise>
     </c:choose>
     </c:forEach>
              </ul>
                        <div class="fl pull_page_down">下一页</div>
                    </div> 
                </div>
            </div>
</body>
<script type="text/javascript" src="<%=basePath %>js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath %>js/main.js"></script>
</html>