<%@page import="java.awt.Window"%>
<%@page import="java.lang.Object" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="org.irdresearch.tbr3web.server.MobileService"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sehatmand Zindagi Dashboard</title>
</head>
<script>
function getParameter()
{
	var day = document.getElementById("paramDay");
	var week = document.getElementById("paramWeek");
	var month = document.getElementById("paramMonth");
	var param;
	
	if(day.checked == true)
	{
		param = day.value;
	}
	else if(week.checked == true)
	{
		param = week.value;
	}
	else if(month.checked == true)
	{
		param = month.value;
	}
	window.location = "/tbreach3web/dashboard.jsp?parameter=" + param;	
}

</script>
</head>
<body>
	<%
	String[][] screeningData;
	String dayChecked ="checked";
	String weekChecked ="";
	String monthChecked = "";
	
	if(request.getParameter("parameter") == null)
	{
		screeningData = MobileService.getService ().getScreeningData ("day", "10____");
	}
	else
	{
		if(request.getParameter("parameter").equals("day"))
		{
			dayChecked = " checked";		
		}
		else if(request.getParameter("parameter").equals("week"))
		{
			weekChecked = " checked";
			dayChecked ="";		
		}
		else if(request.getParameter("parameter").equals("month"))
		{
			monthChecked = " checked";
			dayChecked ="";		
		}
		
		screeningData = MobileService.getService ().getScreeningData (request.getParameter("parameter"), "10____");
	}
	 %>
	Screening Summary
	<table>
	<tr>
	<td><input type="radio" name="paramRadio" id="paramDay" value="day" <%= dayChecked %>>Day</td>
	<td><input type="radio" name="paramRadio" id="paramWeek" value="week" <%= weekChecked %>>Week</td>
	<td><input type="radio" name="paramRadio" id="paramMonth" value="month" <%= monthChecked %>>Month</td>
	<td><input type="submit" value="Select" onclick="getParameter()"/></td>
	</tr>
	</table>
	
	<table border=1>
	<tr style="font-weight: bold;">
		<td>Location</td>
		<td>Username</td>
		<td>Suspects</td>
		<td>Non Suspects</td>
	</tr>
		<%
			String sc = "";
			int nSusp = 0;
			int susp = 0;
			if(screeningData != null)
			{
				sc = screeningData[0][0];
			}
			for (int i = 0; i < screeningData.length; i++)
			{
				String location = screeningData[i][0];
				String user = screeningData[i][1];
				String suspects = screeningData[i][2];
				String nonSuspects = screeningData[i][3];
				// Skip all zeros
				if (suspects.equals ("0") && nonSuspects.equals("0"))
				{
					continue;
				}
				if (!location.equals (sc))
				{%>
					<tr style="font-weight: bold;">
						<td></td>
						<td>Total</td>
						<td><%=susp%></td>
						<td><%=nSusp%></td>
					</tr>
					<tr></tr>
					<tr></tr>
					<%
					sc = location;
					susp = 0;
					nSusp = 0;
				}%>
				<tr>
					<td><%=sc%></td>
					<td><%=user%></td>
					<td><%=suspects%></td>
					<td><%=nonSuspects%></td>
				</tr>
				<%
				nSusp += Integer.parseInt (nonSuspects);
				susp += Integer.parseInt (suspects);
				%>
			<%}%>
			<tr style="font-weight: bold;">
				<td></td>
				<td>Total</td>
				<td><%=susp%></td>
				<td><%=nSusp%></td>
			</tr>
			<tr></tr>
	</table>
</body>
</html>