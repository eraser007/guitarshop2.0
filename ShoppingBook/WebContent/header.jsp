<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
     <div class="header">
       		<div class="logo"><a href="index.htm"><img src="images/logo.gif" alt="" title="" border="0" /></a></div>            
        <div id="menu">
            <ul>                                                                       
            <li class="selected"><a href="index.jsp">首页</a></li>
            <li><a href="about.jsp">关于我们</a></li>
            <li><a href="page?type=0">书籍库</a></li>
            <li><a href="page?type=4">特别推荐</a></li>
            <li><a href="login.jsp">登录</a></li>
            <li><a href="register.jsp">注册</a></li>
            <li><a href="pageNoteServlet">用户留言</a></li>
            <li><a href="contact.jsp">联系我们</a></li>
            </ul>
        </div>                        
       </div> 
       <div id="serachDiv">
       	<div style="padding-left:50px">
       	 <form action="pageSearchServlet" method="post" id="searchForm">
       	 	<table>
       	 		<tr>
       				<td><input type="text" name="keywords" id="keywords" /></td>
       				<td><input type="image" src="images/search.gif" name="submit" /></td>
       				<td><img src="images/gjsearch.png" name="gjsearch" /></td>
       				<td><a href="showSuperTypeServlet">高级搜索</a></td>
       				<td></td>
       			</tr>
       		</table>
       	 </form>
       	</div> 		
       </div>
       <!--end of leftTop content-->