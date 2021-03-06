<%-- Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. --%>
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
    text-decoration: underline;
    color: #601407;
}

a:active {
    text-decoration: underline;
    color: #601407;
}

</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Panel</title>
</head>
<body>

<%
String userName = null;
Cookie[] cookies = request.getCookies();
if(cookies !=null){
for(Cookie cookie : cookies){
    if(cookie.getName().equals("user")) userName = cookie.getValue();
}
}
if(userName == null) response.sendRedirect("UserManagement.jsp");
%>

 
 
	<center>
		<font size="5">
			<b> Admin Panel </b>
		</font>
	</center>

	<table align="center" style="font-size: 20px">	
	<tr><td>
	
    <table align="right">
     <tr><td>
     <form action="logoutServlet" method="post">
	 <input type="submit" value="Logout" >
	 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 </form>
	 </td></tr>
	</table>
    
    </td></tr>
    
    <tr><td>	
	<div align="center" class="divCenter">
		<a href="changeLocation.jsp">Change Location of User</a> <br> 
		<a href="AddUser.jsp">Add User</a> <br> 
		<a href="UpdateUser.jsp">UpdateUser</a> <br> 
		<a href="AddDocContactNumber.jsp">Add/Update Doctor's Contact Information</a> <br> 
		<a href="ChangePatientMobileNo.jsp">Update Patient's Contact Information</a> <br>
		<a href="EditForm.jsp">Edit OR Forms</a> <br>  
		<a href="ScreeningCSV.jsp">Screening Reports</a> <br>
		<a href="CSVReports.jsp">Generate OR Reports</a> <br> 
		<a href="ReminderAdmin.jsp">Reminder Scheduling</a> <br> 
		<a href="SystemStatus.jsp">Screening Status</a> <br> 
		<a href="ReminderStatus.jsp">Reminder Status</a> <br>
		<a href="DailyResponseStatus.jsp">Daily Reminders Response Status</a> <br>
	</div>
	</td></tr>
	</table>
</body>
</html>
