<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <link rel="stylesheet" href="css/font-awesome-4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="css/dmaku.css">
    <script type="text/javascript">
    function changeImg() {
    	document.getElementById("verificationImg").
		src="<c:url value='/Verification?date'/>"+ new Date();
	}
    
    </script>
    <script type="text/javascript">
    function check() {
    	var name = document.getElementById("name").value;
    	if(name.equls("")){
    		alert("name null");
    	}
	}
    </script>
    
</head>

<body>
    <div class="materialContainer">
        <form class="box" action="<c:url value='/UserServlet'  />" method="get" >
           <input type="hidden" value="login" name="method" />
            <div class="title">登录</div>
            <div class="input">
                <label for="name">用户名</label>
                <input type="text" name="uname" id="name" onblur="check()">
                <span class="spin"></span>
            </div>
            <div style="margin-left:90px">
              <img  src="<c:url value='/Verification' />" id="verificationImg" />
	             <a href="javascript:changeImg()" style="text-decoration: none">看不清，换一张</a>    
            </div>
            <div class="input">
            <label for="yzm">验证码</label>
              <input type="text" maxlength="4" name="verificationcode" id="yzm"/>
	              <span class="spin"></span>
            </div>
            <div class="input">
                <label for="pass">密码</label>
                <input type="password" name="password" id="pass">
                <span class="spin"></span>
            </div>
            <div class="button login">
                <button>
                    <span>登录</span>
                    <i class="fa fa-check"></i>
                </button>
            </div>
            <a href="javascript:" class="pass-forgot">忘记密码？</a>
        </form>

        <div class="overbox">
            <div class="material-button alt-2">
                <span class="shape"></span>
            </div>
            <div class="title">注册</div>
            <div class="input">
                <label for="regname">用户名</label>
                <input type="text" name="regname" id="regname">
                <span class="spin"></span>
            </div>
            <div class="input">
                <label for="regpass">密码</label>
                <input type="password" name="regpass" id="regpass">
                <span class="spin"></span>
            </div>
            <div class="input">
                <label for="reregpass">确认密码</label>
                <input type="password" name="reregpass" id="reregpass">
                <span class="spin"></span>
            </div>
            <div class="button">
                <button>
                    <span>注册</span>
                </button>
            </div>
        </div>

    </div>
    <script src="js/jquery.min.js"></script>
    <script src="js/dmaku.js"></script>
</body>
</html>



	
	
	                            
	                            
	                            