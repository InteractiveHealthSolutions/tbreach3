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
<script type='text/JavaScript' src='scw.js'></script>
<title>Daily OR Patient's Response Status</title>
</head>
<body>

	<form id="frm" action="WebEventHandlerServlet" method="post">
		
			<center>
			<font size="5">	<b>Daily OR Patient's Response Status</b></font>
			</center>
			<table align="center">
				<tr><td>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="UserAdmin.jsp">&lt;&lt;Back</a> <br>
				<div align="center" class="divCenter">
				
				    <p>
                    Show:
                    <select id="show" name="show">
							<option value="1">Patient's who responded</option>
							<option value="2">Patient's who didn't responded</option>
					</select>
					</p>
				    <p>
				    on (date):
 					<input id="date1" name="date1" onclick='scwShow(this,event);' readonly="readonly" value=""/>
                    <input onclick="scwShow(scwID('date1'),event);" style="cursor:pointer;width: 16px;height:16px;background-image: url('images/calendar_icon.png');"/>
                    </p>
                    <p>
				    <input type="submit" value="Show" /> 
				     </p>
				    <p>
				<span style="color: red"> <%
 	            if (request.getAttribute ("Error") != null)
 	            {
                 %> <%=request.getAttribute ("Error")%>
	             <%
		         }
	            %> </span>
				</p> 
					
				    <input type="hidden" id="reqType" name="reqType" value = "OrResponse">
				   
				<center> 
				 <% if (request.getAttribute("length") != null){
					 if(request.getAttribute("show").equals("2")){
					%> <b> List of Patients who didn't responded on <%=request.getAttribute("date") %> </b> <br>
				<% } else { %>
				     <b> List of Patients who responded on <%=request.getAttribute("date") %> </b> <br>
				<%}
				   int len = (Integer)request.getAttribute("length");
				   for(int i=0; i<len; i++){
					   %>
					   <br>
					   <%=request.getAttribute("id"+i) %>
					   <% 
				   }
				 }
				 %>
				 
				</center>  
				   
				</div>		
			   </td></tr>
			</table>
		
	</form>
</body>

</html>
