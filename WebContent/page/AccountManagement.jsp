<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
+ path + "/";
%>

	<meta charset="utf-8">
	<link href="<%=basePath %>/css/main.css" rel="stylesheet">
</head>
<script type="text/javascript" src="<%=basePath %>jquery/jquery-3.1.1.js"></script>
<script type="text/javascript">
	//编辑
	function setN(){
		var old = $("input:checkbox[name=u]:checked");
		if(old.length>0){
			if(old.length>1){
				alert("只能选择一个！");
			}else{
			var old = $("input:checkbox[name=u]:checked").next().text();
			var id = $("input:checkbox[name=u]:checked").attr("id");
			$("input:text[name=id]").val(id);
			$("#now").text(old);
			$("#user_column").fadeIn(100);
			}
		}else{
			alert("请选择一个");
		}
		
	}
	//删除
	function setND() {
		var old = $("input:checkbox[name=u]:checked");
		
		if(old.length>0){
			
			$("#delete_Account").fadeIn(100);
			var names = "";
			var ids = "";
			old.each(function (){
				names = names+$(this).next().text()+",";
				var iu = $(this).attr("id");
				ids =ids+iu+ "," ;
			})
			$("#del").text(names);
			$("input:text[name=del_id]").val(ids);
		}else{
			alert("请选择一个！")
		}
	}
	
	//账户授权
	function ur(){
		var id = $("input:checkbox[name=u]:checked");
		if(id.length>0&&id.length<2){
			var url = "<c:url value='ShowColumn'/>";
			var uid = id.attr("id");
			var param = {"uid":uid};
			$.ajax({
				type:"post",
				url:url,
				data:param,
				dataType:"json",
				success:function (db,status,XHR){
					$("#ucc").html("");
					$.each(db,function(index,li){
						$("#ucc").html(db);
					})
				},
				error:function(){
					alert("error");
				}
			})
			uur();
	
		}else{
			alert("选择一个!");
		}
		
		
	}
	
	function userReg(){
		var username=  $("#cname").val();
		if(isNull(username)){
		var param = {"username":username};
		var url = "UserReg";
		$.ajax({
			type:"post",
			url:url,
			data:param,
			dataType:"json",
			success:function (db,status,XHR){
				if(db!="true"){
					alert("用户名已存在")
				}else{
					alert("可以注册！")
				}
			},
			error:function(){
				alert("error");
			}
		})
		}else{
			alert("用户名不能为空！");
		}
	}
	
	function uur(){
		var id = $("input:checkbox[name=u]:checked").attr("id");
		var rd = $("input:checkbox[name=r]:checked");
		$("#ac_Account").fadeIn(100);
			var rds = "";
			rd.each(function () {
				var ir = $(this).val();
				rds =rds+ir+ "," ;
		})
		$("input:text[name=right_user_id]").val(id);
		$("input:text[name=right_ids]").val(rds);
		var pageCode =$("li[class=on] a").text();
		$("input:text[name=pageCode]").val(pageCode);	
	}
	
	
	$(document).ready(function(){
		$("#ac_all").change(function(){
				if($(this).attr('checked')){
					var id = $("input:checkbox[name=u]").attr('checked',true);
				}else{
					var id = $("input:checkbox[name=u]").attr('checked',false);
				}
			
		})
	})
	
	function isNull(str){
		if(str!=null&&str!=""){
			return true;
		}
		return false;
	}
	
	function nameR(){
		var username = $("#add_name").val();
		if(username!=null&&username!=""){
			$.ajax({
				type:"post",
				url:"UserReg",
				data:{"username":username},
				dataType:"json",
				success:function (db,status,XHR){
					if(db==true){
						alert("用户名已存在")
					}else{
						alert("可以注册！")
					}
				},
				error:function(){
					alert("error");
				}
			})
		}else{
			alert("不能为空")
		}
	}
</script>
<body>
			
			<div class="AccountManagement_c " id="AccountManagement_c">
                <form action="SeaServlet" method="post"> <h3>账户管理 
                    <div class="amcl fr">
                    	
                        <input type="text" placeholder="用户名" class="fl" name="username">
                        <input type="submit" class="search fl" value="搜索"  style="width:50px;background:#fff;">
                    </div> 
                </h3> </form>
                <div class="AM_ct text_center">
                    <div class="AM_ct_in">
                        <div class="add_btn df_btn fl" id="add_Account_btn">添加</div>
                        <div class="edit_btn df_btn fl"  onclick="setN();">编辑</div>
                        <div class="delete_btn df_btn fl" onclick="setND();">删除</div>
                       <c:if test="${user.name=='admin' }"><div class="fr df_btn ac_btn" onclick="ur();" >账号授权</div></c:if> 
                    </div>
                </div>

                <div class="list">
                    <ul class="list_h">
                        <li class="b20"><label><input type="checkbox" id="ac_all"><span>用户名</span></label></li>
                        <li class="b20"><label>密码</label></li>
                        <li class="b60"><label>授权形式</label></li>
                    </ul>
                    <!--第一栏到底现不现实管理员?  -->
                   <%--   <ul class="list_null">
                            <li class="text_center">
                            	<c:choose>
                            	<c:when test="${user.getName()=='admin'}">
                            	</c:when>
                            	<c:otherwise></c:otherwise>
                            	</c:choose>
                            	</li>
                        </ul> --%>
                    <div class="list_b_c" id="ul">
                      
                       <c:forEach items="${pageBean.users }" var="user" >
                       <c:if test="${user.name!='admin' }">
                       <ul class="list_b">
                            <li class="b20"><label>
                            	<input type="checkbox" name="u" id="${user.getId() }" ><span >${user.getName()}</span></label></li>
                            <li class="b20"><label >${user.getPassword()}</label></li>
                            <li class="b60"><label >${user.getRight() }</label></li>
                        </ul>
                        </c:if>
                       </c:forEach>
                    </div>
                    <div class="pull_page">
                        <div class="fl pull_page_up"><a href="<c:url value="UserServlet?pageCode=${pageBean.pageCode>1?pageBean.pageCode-1:pageBean.pageCode }&un=${un }"/>">上一页</a></div>
             <ul>
                            <!-- 这里只是定义了两个变量 begin and end -->
  <c:choose>
      <%-- 不够10页，显示所有的页码 --%>
      <c:when test="${pageBean.getTotalPage() <=10 }">
        <c:set var = "begin" value="1"/>
        <c:set var = "end" value="${pageBean.getTotalPage() }"/>
      </c:when>
     	<c:otherwise><!-- 12页  当前页为3页 -->
      	<c:set var = "begin" value="${pageBean.getPageCode() -5}"/>
        <c:set var = "end" value="${pageBean.getPageCode() +4 }"/>
        <!-- 头部溢出 -->
        <c:if test="${begin < 1 }">  
          <c:set var = "begin" value="1"/>
          <c:set var = "end" value="10"/>
        </c:if>
        <!-- 尾部溢出 -->
        
        
        
         <c:if test="${end > pageBean.getTotalPage() }">  
          <c:set var = "begin" value="#{pageBean.getTotalPage() -9}"/>
          <c:set var = "end" value="#{pageBean.getTotalPage() }"/>
        </c:if>
     </c:otherwise>
  </c:choose>
  <!-- 输出数字  -->
     <c:forEach begin="${begin }" end="${end }" var="i">
     <c:choose>
     <%-- 判断数字 = pagecode时，添加不同的样式 -  eq：等于的意思--%>
        <c:when test="${i eq pageBean.getPageCode() }">
           <li class="on" > <a href="<c:url value="UserServlet?pageCode=${i }&un=${un }"/>">${i}</a></li>
        </c:when>
        <c:otherwise>
        <li ><a href="<c:url value="UserServlet?pageCode=${i }"/>">${i}</a></li>
        </c:otherwise>
     </c:choose>
     </c:forEach>
              </ul>
                        <div class="fl pull_page_down"><a href="<c:url value="UserServlet?pageCode=${pageBean.pageCode<pageBean.totalPage?pageBean.pageCode+1:pageBean.pageCode }&un=${un }"/>">下一页</a></div>
                    </div> 
                </div>
            </div>
            <!-- 弹窗 -->
    <!-- 添加账户 -->
    <div class="add_Account dn" id="add_Account">
    <form action="<c:url value="/AddServlet"/>" method="post">
        <div class="add_Account_c">
            <div class="add_Account_h">
                <div class="add_Account_h_in">
                    添加账户
             <span class="fr add_Account_close"><img src="<%=basePath %>images/close.png"></span>
                </div>
            </div>
            <div class="user_name user_i">
                <label>用户名</label> <input type="text" placeholder="输入用户名" id="add_name"  name="name" onblur="nameR();">     
            </div>
            <div class="user_password user_i">
                <label>密<i>调</i>码</label> <input type="text" placeholder="输入密码" id="add_password" name="password" onblur="passwordR();">     
            </div>
            <input type="submit" class="add_Account_ok_btn text_center" id="add_Account_ok_btn" value="确定">
        </div></form>
    </div>

    <!-- 账户编辑 -->
    <div class="add_Account dn" id="user_column">
    <form action="ChServlet" method="post">
        <div class="add_Account_c">
            <div class="add_Account_h">
                <div class="add_Account_h_in">
                    编辑账户
                    <span class="fr add_Account_close"><img src="<%=basePath %>images/close.png"></span>
                </div>
            </div>
            <div class="column_now ">正在编辑 “<span id="now">jiaowuchu</span>”账户</div>
            <div class="user_name user_i">
                   <input type="text" value="" name="id" style="display:none">
                <label>用户名</label> <input type="text" id="cname" placeholder="输入用户名" name="name" onblur="userReg();">     
            </div>
            <div class="user_password user_i">
                <label>密<i>调</i>码</label> <input type="text" id="cpassword" placeholder="输入密码" name="password">     
            </div>
            <input type="submit" class="add_Account_ok_btn text_center" id="user_column_ok_btn"value="确定">
        </div>
        </form>
    </div>

    <!-- 删除账户 -->
    <div class="add_Account dn" id="delete_Account">
    <form action="DelServlet" method="post">
        <div class="add_Account_c">
            <div class="add_Account_h">
                <div class="add_Account_h_in">
                    删除账户
                    <span class="fr add_Account_close"><img src="<%=basePath %>images/close.png"></span>
                </div>
            </div>
            <input type="text" name="del_id" class="dd" >
            <div class="delete_text">确定删除“ <span id="del">jiaopwuchu</span> ”账户吗？</div>
            <input type="submit" class="add_Account_ok_btn text_center" id="delete_Account_ok_btn" value="确定">
        </div>
        </form>
    </div>

    <!-- 账户授权 -->
    <div class="add_Account dn" id="ac_Account">
    <form action="URServlet" method="post">
        <div class="add_Account_c">
            <div class="add_Account_h">
                <div class="add_Account_h_in">
                    账户栏目授权
                    <span class="fr add_Account_close"><img src="<%=basePath %>images/close.png"></span>
                </div>
            </div>
            <input type="text" name="right_user_id" class="dd">
            <input type="text" name="right_ids" class="dd">
            <input type="text" name="pageCode" class="dd">
            <ul id="ucc">
            <c:forEach items="${pageBean.rights }" var="right">		
            	<li><label><input type="checkbox" name="r" value="r${right.id }">&nbsp;<span>${right.rname }</span></label></li>
            </c:forEach>
            </ul>
            <input type="submit" class="add_Account_ok_btn text_center" id="ac_Account_ok_btn" onclick="ur();" value="保存">
        </div>
        </form>
    </div>	
</body>
<script type="text/javascript" src="<%=basePath %>js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath %>js/main.js"></script>
</html>