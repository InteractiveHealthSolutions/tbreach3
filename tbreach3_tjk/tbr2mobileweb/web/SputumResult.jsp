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
<script type='text/JavaScript' src='scw.js'>
</script>
<script type="text/javascript">
function dateResult()
{
	var date1_var=document.getElementById("date1").value;
	
}

function reaper()
{
	
	var x=document.getElementById("resulttype").selectedIndex;
	var y=document.getElementById("resulttype").options;
	if(y[x].text=="Smear Microscopy")
		{
		document.getElementById("smearoptions").style.display='block';
		document.getElementById("genexpertoptions").style.display='none';
		document.getElementById("rifoptions").style.display='none';
		}
	else
		{
		document.getElementById("genexpertoptions").style.display='block';
		document.getElementById("rifoptions").style.display='block';
		document.getElementById("smearoptions").style.display='none';
		}
	
}
</script>

<form id="frm" method="post">
<div style="margin-left: 2em">
<table>
<tr>
<td align="left" valign="top">Sputum Result Date:
 <input id="date1" name="date1" onclick='scwShow(this,event);' readonly="readonly" value=""/>
                    <input onclick="scwShow(scwID('date1'),event);" style="cursor:pointer;width: 16px;height:16px;background-image: url('images/calendar_icon.png');"/>
</td>
</tr>
  <tr></tr>
    <tr>
    <td align="left" valign="top">Health Worker ID: &nbsp; <input type="text" name="hw" size=20/></td>
    <td></td>
  </tr>
  <tr></tr>
<tr><td>Which Test Result are you entering:
 <select id="resulttype" name="resulttype" onchange="reaper();">
 	<option>Smear Microscopy</option>
 	<option>GeneXpert</option>
    </select>
</td></tr>
  <tr><td id="smearoptions" style="display:none;">
 
Smear Microscopy Result:<br>
 <select name="Smear Result" >
 	<option>Negative</option>
 	<option>1-9 AFB</option>
 	<option>Scanty</option>
 	<option>1+</option>
 	<option>2+</option>
 	<option>3+</option>
    </select>
</td></tr>

<tr><td id="genexpertoptions" style="display:none;">
 
GeneXpert Result:<br>
 <select name="Xpert Result" >
 	<option>MTB Not Detected</option>
 	<option>MTB Detected</option>
 	<option>MTB Indeterminate</option>
 	<option>Invalid</option>
 	
    </select>
</td></tr>

<tr><td id="rifoptions" style="display:none;">
 
GeneXpert RIF Resistance Result:<br>
 <select name="RIF Result" >
 	<option>Rif Resistant</option>
 	<option>Rif Sensitive</option>
 	<option>Not Detected</option>
 	
    </select>
</td></tr>
</table>
</div>
</form>
