<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<jsp:directive.page import="com.lovo.cq.shoppingbook.vo.Cart"/>
<jsp:directive.page import="com.lovo.cq.shoppingbook.vo.CartItem"/>
<%@include file="common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>free-css-templates-211</title>
<link rel="stylesheet" type="text/css" href="style.css" />

</head>
<body>
<div id="wrap">
		
		<%@include file="header.jsp" %>
       <div class="center_content">
       	<div class="left_content">
        	<form name="register" action="addOrderServlet" method="post">          
					<div class="form_row">
                    <label class="contact"><strong>用户:</strong></label>
                    <font color="red">${user.trueName}</font>
                    </div>
					
                    <div class="form_row">
                    <label class="contact"><strong>地址:</strong></label>
                    <input type="text" name="address" class="contact_input" />
                    </div>
                    
                    <div class="form_row">
                    <label class="contact"><strong>邮政编码:</strong></label>
                    <input type="text" name="address" class="contact_input" />
                    </div>
					
                    <div class="form_row">
                    <label class="contact"><strong>固定电话:</strong></label>
                    <input type="text" name="phone" class="contact_input" />
                    </div>
                    
                     <div class="form_row">
                    <label class="contact"><strong>移动电话:</strong></label>
                    <input type="text" name="mphone" class="contact_input" />
                    </div>

					<div class="feat_prod_box_details">
            <table class="cart_table">
               	<tr class="cart_title">
                	<td>ISBN</td>
                	<td>书籍名</td>
                    <td>单价</td>
                    <td>数量</td>
                    <td>总价</td>           
                </tr>
				<%
					Collection ci = (Collection)request.getSession().getAttribute("ci");
					
						Iterator<CartItem> it= ci.iterator();
						while(it.hasNext()){
							CartItem cartItem = it.next();
							%>
								<tr>
									<td><%=cartItem.getBook().getISBN()%></td>
									<td><%=cartItem.getBook().getBookName() %></td>
									<td><%=cartItem.getBook().getNowPrice()%></td>
									<td><%=cartItem.getCount()%></td>
									<td><%=cartItem.getItemPrice() %></td>
								</tr>
							<%
						}
				 %>  
                <tr>
                <td colspan="4" class="cart_total"><span class="red">总价:</span></td>
                	<%
					Cart c = (Cart)session.getAttribute("cart");
					if(c != null){
    	 	   			%>
    	 	   			  <td><%=c.getTotalPrice()%></td>
    	 	   			<%
    	 	  	   }
					%>              
                </tr>             
            </table>
            <a href="cart.jsp" class="continue">&lt; 返回购物车</a>
            <span class="submit_order"><input type="image" value="submit" src="images/register_bt.gif"/></span>
            </div>	   
        </form>  
 		</div><!--end of left content-->
        <%@include file="right.jsp" %> 
       <!--end of right content-->
   
       <div class="clear"></div>
       </div><!--end of center content-->
       
       <%@include file="footer.jsp"%>
</div>

</body>
</html>