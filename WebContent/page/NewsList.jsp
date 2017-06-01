<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
+ path + "/";
%>
	<meta charset="utf-8">
	<link href="<%=basePath %>css/main.css" rel="stylesheet">
<head>
    <title></title>
    <meta charset="utf-8">
    <link href="<%=basePath %>css/main.css" rel="stylesheet">
    <style type="text/css">
        .b50{
            width: 50%;
        }
        .b50:hover{
            color: red;
            /*text-decoration: underline;*/
        }
        .nulls:hover{
            color: #33698a;
        }
        .b15{
            width: 15%;
        }
        .b10{
            width: 10%;
        }
        #top{
            color: red;
        }
        #stick,#unstick{
            color: #238e32;
            cursor: pointer;
            text-align: center;
        }
        #stick:hover,#unstick:hover{
            text-decoration: underline;
            color: red;
        }
    </style>
</head>
<script type="text/javascript" src="<%=basePath %>jquery/jquery-3.1.1.js"></script>
<script type="text/javascript">
	function del_new() {
		var new_id = $("input:checkbox[name=n]:checked");
		var new_id_length = new_id.length;
		if(new_id_length>0){
			var new_ids = "";
			new_id.each(function (){
				var nid = $(this).attr("id");
				new_ids = new_ids+nid+",";
			})
			$("input:text[name=del_new_ids]").val(new_ids);	
			$("#delete_column").fadeIn(100);
		}else{
			alert("请选择至少一个")
		}
		
	}
	function edit_new(){
		var new_id = $("input:checkbox[name=n]:checked").attr("id");
		if(new_id==null){
			alert("请选择一个！");
		}else{		
			
		document.getElementById("edit_column_btn").href="Edit_New_Servlet?id="+new_id;
		var hr = document.getElementById("edit_column_btn").href;
		}
		
	}
</script>
<body>	<div class="AccountManagement_c" id="NewsManagement_c">
         <!--搜索 栏 -->
				<form action="Sea_New_Servlet" method="post">
                <h3>新闻管理 
                    <div class="amcl fr">
                        <select class="fl NewsManagement_search" name="column">
                            <c:forEach items="${pn.rights }" var="r">
                        		 <option value="r${r.id }">${r.rname }</option>
                        </c:forEach>
                        </select>
                        <input type="text" placeholder="关键字" class="fl" name="sea_new">
                         <input type="submit" class="search fl" value="搜索"  style="width:50px;background:#fff;">
                    
                    </div> 
                </h3>
                </form>
                <!--菜单栏  -->
                <div class="AM_ct text_center">
                    <div class="AM_ct_in">
                         <a class="add_btn df_btn fl" id="add_column_btn" href="Add_New_Servlet" >添加</a>
                        <a class="edit_btn df_btn fl" id="edit_column_btn"onclick="edit_new();">编辑</a>
                        <div class="delete_btn df_btn fl"  onclick="del_new();">删除</div>
                    </div>
                </div>
                <!--  标题栏  -->
                <div class="list">
                    <ul class="list_h">
                        <li class="b50 nulls">
                        <label>
                        <!-- <input type="checkbox" name=""> -->
                        <span>新闻标题</span>
                        </label></li>
                        <li class="b10"><label>栏目名称</label></li>
                        <li class="b20"><label>发布时间</label></li>
                        <li class="b20"><label>操作</label></li>
                    </ul>
                    <div class="list_b_c">
                       <!--遍历所有置顶新闻  -->
                            <c:forEach items="${pn.top_news }" var="top_new">
                          
                 
                             <ul class="list_b">
                            <li class="b50"><label> <c:choose> <c:when test="${fn:contains(urs, top_new.rname) }"><input type="checkbox" name="n" id="${top_new.id }">  </c:when><c:otherwise>无权限</c:otherwise></c:choose><span id="top">[置顶]</span></label><span >${top_new.title }</span></li>
                            <li class="b10"><label>${top_new.rname }</label></li>
                            <li class="b20"><label>${top_new.cdate }</label></li>
                            <li class="b20">
                                <!-- <span id="stick">[置顶]</span> -->
                                  <c:choose> <c:when test="${fn:contains(urs, top_new.rname) }"><a id="stick" href="Top_Switch?id=${top_new.id }&top=y">[取消置顶]</a></c:when><c:otherwise>无权限</c:otherwise></c:choose>
                            </li>
                            </ul>
                            </c:forEach>
                            <!--遍历所有非置顶新闻  -->
                         <c:forEach items="${pn.no_top_news }" var="no_top_new">
                        <ul class="list_b">
                             <li class="b50"><label>  <c:choose> <c:when test="${fn:contains(urs, no_top_new.rname) }"><input type="checkbox" name="n" id="${no_top_new.id }">  </c:when><c:otherwise>无权限</c:otherwise></c:choose><span></span></label>
                             <span>${no_top_new.title }</span></li>
                            <li class="b10"><label>${no_top_new.rname }</label></li>
                            <li class="b20"><label>${no_top_new.cdate }</label></li>
                            <li class="b20">
                            <!-- 有权限可以置顶，无权限不能 -->
                           <c:choose> <c:when test="${fn:contains(urs, no_top_new.rname) }"><a id="stick" href="Top_Switch?id=${no_top_new.id }&top=n">[置顶]</a></c:when><c:otherwise>无权限</c:otherwise></c:choose>
                                <!-- <span id="unstick">[取消置顶]</span> -->
                            </li>
                        </ul>
                        </c:forEach>
                    </div>
                  
                    <div class="pull_page">
                      
                       <div class="fl pull_page_down"><a href="<c:url value="NewsServlet?pageCode=${pn.pageCode>1?pn.pageCode-1:pn.pageCode }&title=${title }&tid=${tid }"/>">上一页</a></div>
                     
                            <ul>
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
         <li class="on" ><a href="<c:url value="NewsServlet?pageCode=${i }&title=${title }&tid=${tid }"/>" >${i}</a></li>
           </c:when>
        <c:otherwise>
            <li ><a href="<c:url value="NewsServlet?pageCode=${i }&title=${title }&tid=${tid }"/>" >${i}</a></li>
        </c:otherwise>
     </c:choose>
     </c:forEach>
              </ul>
                      <div class="fl pull_page_down"><a href="<c:url value="NewsServlet?pageCode=${pn.pageCode<pn.totalPage?pn.pageCode+1:pn.pageCode }&title=${title }&tid=${tid }"/>">下一页</a></div>
                    </div> <!-- end - page -->  
                </div>
            </div>
    <!-- 删除栏目 -->
    <div class="add_Account dn" id="delete_column">
    <form action="Del_n_New" method="post">
        <div class="add_Account_c">
            <div class="add_Account_h">
                <div class="add_Account_h_in">
                    删除新闻		<input type="text" name="del_new_ids" class="dd">
                    <span class="fr add_Account_close"><img src="<%=basePath %>images/close.png"></span>
                </div>
            </div>
            <div class="delete_text">确定删除这几条<!-- “ <!-- <span id="del_new">jiaopwuchu</span> --> 新闻吗？</div>
            <input type="submit" class="add_Account_ok_btn text_center" id="delete_column_ok_btn" value="确定">
        </div>
        </form>
    </div>
</body>
<script type="text/javascript" src="<%=basePath %>js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath %>js/main.js"></script>
</html>