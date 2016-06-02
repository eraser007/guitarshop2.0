<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'updataPwd.jsp' starting page</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  <script type="text/javascript">
    function checkPwd(){
		var password1 = document.getElementById("password1");
		var password2 = document.getElementById("password2");
		var checkPwd = document.getElementById("checkPwd");
		if(password1.value!=password2.value){
			checkPwd.style.color="red";
			checkPwd.innerHTML="两次密码输入不一致!";
			return false;
		}else{
			checkPwd.style.color="green";
			checkPwd.innerHTML="两次密码输入一致!";
			return true;
		}
	}
  </script>
  </head>
  
  <body>
    <form action="updataPwd">
      <table>
      <tr>
                    		<td class="label">密码:</td>
                    		<td><input type="password" id="password1" name="password" class="contact_input" onfocus="clearPwd()" onblur="checkPassword()" /></td>
                    		<td class="remind" id="pwdRemind"><span style="color: red">*</span><span style="color:black">由英文字母和数字(6-16)字符组成</span></td>
                    	</tr>
                    	<tr>
                    		<td class="label">确认密码:</td>
                    		<td> <input type="password" id="password2" name="password" class="contact_input" onblur="checkPwd()" /></td>
                    		<td id="checkPwd" class="remind">&nbsp;</td>
                    	</tr>
      
      </table>
 <input type="submit" value="确定" onblur="checkPwd()">
    </form>
  </body>
</html>
