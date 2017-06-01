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
	<link href="<%=basePath %>/css/main.css" rel="stylesheet">
</head>
<script type="text/javascript" src="<%=basePath %>/jquery/jquery-3.1.1.js"></script>
<script type="text/javascript">
	function edit(){
		var rid = $("input:checkbox[name=p]:checked");
		var rname =  $("input:checkbox[name=p]:checked").parent().parent().next().children().text();
		if(rid.length>0){
			if(rid.length>1){
				alert("只能选择一个！")
			}else{
			$("#ds").text(rid.attr("id"));
			$(":text[name=edit_right_id]").val(rid.attr("id"));
			$(":text[name=name]").val(rname);
			$("#edit_column").fadeIn(100);
			}
		}else{
			alert("请选择！！");
		}
		
	}
	
	function del(){
		var del_id =  $(":checkbox[name=p]:checked");
		if(del_id.length>0){
			var del_ids ="";
			del_id.each(function (){
				var i = $(this).attr("id");
				del_ids = del_ids+i+",";
			})
			$("#del").text(del_ids);
			$(":text[name=del_right_id]").val(del_ids);
			$("#delete_column").fadeIn(100);
			
		}else{
			alert("请至少选择一个！")
		}
	}
	
	$(document).ready(function (){
		$("#col_all").change(function(){
			if($(this).attr('checked')){
				 $(":checkbox[name=p]").attr('checked',true);
			}else{
				$(":checkbox[name=p]").attr('checked',false);
			}
		})
	})
	
	function rnameR(){
		var rname = $("#add_column").val();
		if(isNull(rname)){
		var url = "Edit_r_Reg";
		var param = {"rname":rname};
		$.ajax({
			type:"post",
			url:url,
			data:param,
			dataType:"json",
			success:function(db,status,sn){
				alert(db);
			},
			error:function(){
				alert("error");
			}
		})
		}
	}
	
	function rnameRR(){
		var rname = $("#edit_name").val();
		if(isNull(rname)){
			$("#edit_column").fadeOut(100);
		}else{
			alert("不能为空！");
			
		}
	}
	function isNull(str){
		if(str==""||str==null){
			return false;
		}
		return true;
	}
	
</script>
<body>
    <!-- 栏目管理 -->
            <div class="AccountManagement_c" id="ColumnManagement_c">
                   <form action="Sea_col_Servlet" method="post"> <h3>栏目管理 
                    <div class="amcl fr">
                        <input type="text" placeholder="栏目名" class="fl" name="rname">
                        <input type="submit" class="search fl" value="搜索"  style="width:50px;background:#fff;">
                    </div> 
                </h3> </form>
                
                <div class="AM_ct text_center">
                <c:if test="${user.name=='admin' }">
                    <div class="AM_ct_in">
                        <div class="add_btn df_btn fl" id="add_column_btn">添加</div>
                        <div class="edit_btn df_btn fl" onclick="edit();">编辑</div>.
                        <div class="delete_btn df_btn fl" onclick="del();">删除</div>
                    </div>
                    </c:if>
                </div>

                <div class="list">
                    <ul class="list_h">
                        <li class="b20"><label><c:choose>
                            <c:when test="${user.name=='admin' }">
                             <input type="checkbox" name="p" id="col_all"  >
                            </c:when>
                            <c:otherwise>
                            	无权限
                            </c:otherwise>
                            </c:choose><span>序号----${user.name}</span></label></li>
                        <li class="b80"><label>栏目名称</label></li>
                    </ul>
                    <div class="list_b_c">
 						<c:forEach items="${pr.rights }" var="right" varStatus="i" >
                       <ul class="list_b">
                            <li class="b20"><label id="num" class="dd">${right.num }</label>
                            <label> 
                            <c:choose>
                            <c:when test="${user.name=='admin' }">
                             <input type="checkbox" name="p" id="${right.id }" value="${right.id }" >
                            </c:when>
                            <c:otherwise>
                            	无权限
                            </c:otherwise>
                            </c:choose>
                           <span>${i.index+1}</span></label>
                           </li>
                            <li class="b20"><label id="rname">${right.getRname()}</label></li>
                        </ul>
                       </c:forEach>
                    </div>

                   <div class="pull_page">
                        <div class="fl pull_page_down"><a href="<c:url value="Column_Servlet?pageCode=${pr.pageCode>1?pr.pageCode-1:pr.pageCode }&rn=${rn }"/>">上一页</a></div>
                            <ul>
                            <!-- 这里只是定义了两个变量 begin and end -->
           <c:choose>
             <%-- 不够10页，显示所有的页码 --%>
           <c:when test="${pr.getTotalPage() <=10 }">
             <c:set var = "begin" value="1"/>
           <c:set var = "end" value="${pr.getTotalPage() }"/>
             </c:when>
          	<c:otherwise><!-- 12页  当前页为3页 -->
            	<c:set var = "begin" value="${pr.getPageCode() -5}"/>
                <c:set var = "end" value="${pr.getPageCode() +4 }"/>
           <!-- 头部溢出 -->
            <c:if test="${begin < 1 }">  
             <c:set var = "begin" value="1"/>
                   <c:set var = "end" value="10"/>
           </c:if>
          <!-- 尾部溢出 -->
               <c:if test="${end > pr.getTotalPage() }">  
               <c:set var = "begin" value="#{pr.getTotalPage() -9}"/>
               <c:set var = "end" value="#{pr.getTotalPage() }"/>
             </c:if>
            </c:otherwise>
          </c:choose>
 
  <!-- 输出数字  -->
     <c:forEach begin="${begin }" end="${end }" var="i">
     <c:choose>
     <%-- 判断数字 = pagecode时，添加不同的样式 -  eq：等于的意思--%>
        <c:when test="${i eq pr.getPageCode() }">
           <li class="on" ><a href="<c:url value="Column_Servlet?pageCode=${i }&rn=${rn }"/>">${i}</a></li>
        </c:when>
        <c:otherwise>
        <li onclick="get(${i});"><a href="<c:url value="Column_Servlet?pageCode=${i }&rn=${rn }"/>">${i}</a></li>
        </c:otherwise>
     </c:choose>
     </c:forEach>
              </ul>
                        <div class="fl pull_page_down"><a href="<c:url value="Column_Servlet?pageCode=${pr.pageCode<pr.totalPage?pr.pageCode+1:pr.pageCode }&rn=${rn }"/>">下一页</a></div>
                    </div> 
                    
                </div>
            </div>


            <!-- 添加栏目 -->
    <div class="add_Account dn" id="add_column">
    <form action="AddColumnServlet" method="post">
        <div class="add_Account_c">
            <div class="add_Account_h">
                <div class="add_Account_h_in">
                    添加栏目
                    <span class="fr add_Account_close"><img src="<%=basePath %>images/close.png"></span>
                </div>
            </div>
            <div class="user_name user_i">
                <label>栏目名</label> <input type="text" placeholder="输入栏目名" name="add_column" onblur="rnameR();">      
            </div>
            <input type="submit" class="add_Account_ok_btn text_center" id="add_column_ok_btn" value="确定">
        </div>
        </form>
    </div>

    <!-- 编辑栏目 -->
    <div class="add_Account dn" id="edit_column">
    <form action="EditColumnServlet" method="post">
        <div class="add_Account_c">
            <div class="add_Account_h">
                <div class="add_Account_h_in">
                    编辑栏目id为<span id="ds"></span>
                    <span class="fr add_Account_close"><img src="<%=basePath %>images/close.png"></span>
                </div>
            </div>
            <div class="user_name user_i">
            <input type="text" name="edit_right_id" class="dd">
                <label>栏目名</label> <input type="text" placeholder="输入栏目名" id="edit_name" name="name" onblur="rnameR();">     
            </div>
            <input type="submit" value="确定" class="add_Account_ok_btn text_center"  onclick="rnameRR();">
        </div>
        </form>
    </div>
    
    
    <!-- 删除栏目 -->
    <div class="add_Account dn" id="delete_column">
    <form action="Del_Column_Servlet" method="post">
        <div class="add_Account_c">
            <div class="add_Account_h">
                <div class="add_Account_h_in">
                    删除栏目			<input type="text" name="del_right_id" class="dd">
                    <span class="fr add_Account_close"><img src="<%=basePath %>images/close.png"></span>
                </div>
            </div>
            <div class="delete_text">确定删除“ <span id="del">jiaopwuchu</span> ”栏目吗？</div>
            <input type="submit" class="add_Account_ok_btn text_center"  value="删除">
        </div>
        </form>
    </div>
</body>
<script type="text/javascript" src="<%=basePath %>js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath %>js/main.js"></script>
</html>