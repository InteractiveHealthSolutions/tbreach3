<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style>
div
{
border:2px solid;
border-radius:50px;
padding: 20px;
background-color: #F1ECDD;
border-color: #601407;
}

input[type = "submit"]:hover { 
    color: #601407;
}

a:link {
    text-decoration: none;
    color: brown;
}

a:visited {
    text-decoration: none;
    color: brown;
}

a:hover {
    text-decoration: none;
    color: #601407;
}

a:active {
    text-decoration: none;
    color: #601407;
}
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Patient's contact Number</title>
</head>
<body>
	<form id="frm" action="WebEventHandlerServlet" method="post">
		    <center>
			<font size=5>
				<b>Update Patient's Contact Number</b>
			</font></center>
			<table align="center">
				<tr><td>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="UserAdmin.jsp">&lt;&lt;Back</a> <br>
				<div align="center" class="divCenter">
				<p align="left">
				Patient Id&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="text" id="upid" name="upid" size=20 />
				</p>
				<p align="left">
				Mobile Number&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="text" id="uphone" name="uphone" size=20 />
				</p>
				<p align="right">
				(for sending out reminders)
				</p>
				<p>
				<span style="color: red"> <%
 	             if (request.getAttribute ("updateuserexception") != null)
 	             {
                  %> <%=request.getAttribute ("updateuserexception")%>
	               <%
		         }
	             %> </span>
				</p>
				<p>
				 
    	      <input type="hidden" id="reqType" name="reqType" value = "UpdatePatientContactNo">
    	     
				</p>
				<p>
					<input type="submit"
						value="Submit" /></p>
				</div>		
				</td></tr>		
			</table>
		
	</form>
</body>
<br>
</html>