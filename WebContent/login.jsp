<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>登陆</title>
	<meta charset="utf-8">
	<link rel="stylesheet" type="text/css" href="css/main.css">
</head>

<script type="text/javascript" src="jquery/jquery-3.1.1.js"></script>
<script type="text/javascript">
	function change(srcObj) {
        srcObj.src ="CodeSevlet?" + Math.random();
    }
	
	function nameR(){
		var username = $("#username").val();
		var url = "NameReg";
		var param  = {"username":username};
		$.ajax({
			type:"post",
			url:url,
			data:param,
			success:(function(db,status,XHR) {
					if(db=="true"){
						$("#nn").text("用户名可以");
					}else{
						$("#nn").text("用户名bu可以");
					}
			})
		})
	}

</script>
<body>
	<div class="login_head">
		<div class="container">
			<img src="images/logo.png">
		</div>
	</div>

	<div class="login_banner">
		<div class="container">
			<div class="login">
			<form action="LoginServlet" method="post" onsubmit="return loginR();">
                <div class="login_in">
                    <div class="login_h">后台登陆</div>
                    <div class="user_login">
                        <input type="text" placeholder="账号" id="username" name="username" onblur="nameR(); "><span id="nn"></span>
                        <input type="text" placeholder="密码" id="password" name="password">
                    </div>
                    <div class="img_code">
                        <input type="text" placeholder="验证码" id="code" name="code">  <span id="cod"></span>
                        <img src="CodeSevlet" alt="" onclick="change(this);" style="width:90px;height:40px">
                    </div>
                     <input type="submit" value="登录" class="login_btn" style="border:none"/>
                </div>
              </form>  
            </div>
		</div>
	</div>
	<div class="login_end text_center">版权所有&copy;：新开普电子股份有限公司  豫ICP备08102576号  未经授权本站内容禁止私自转载使用！</div>
</body>
</html>