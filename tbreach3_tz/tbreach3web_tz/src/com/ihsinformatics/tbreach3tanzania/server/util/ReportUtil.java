/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */
/**
 * Implements reporting features. 
 */

package com.ihsinformatics.tbreach3tanzania.server.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;

import com.ihsinformatics.tbreach3tanzania.shared.Parameter;
import com.ihsinformatics.tbreach3tanzania.shared.TBRT;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * @author owais.hussain@irdresearch.org
 * 
 */
public class ReportUtil
{
	public static final String[]	allowedExtensions	= {"jrxml", "doc", "docx", "xls", "xlsx", "rar", "zip"};
	
	
	// for mobile reports!
	
	@SuppressWarnings("deprecation")
	public static String getReport (String report, String filter)
	{
		String query = null;
		try
		{
			if (report.equalsIgnoreCase("No. of Patient Screened"))
				query = "select count(*) as SCREENED from tbr3tanzania_rpt.patient as pa, tbr3tanzania_rpt.person as pr where pr.pid = pa.pid AND pa.patient_status != 'REGISTERED'" + filter;
			
			else if (report.equalsIgnoreCase("No. of Suspect Identified"))
				query = "select count(*) as SUSPECT from tbr3tanzania_rpt.patient as pa, tbr3tanzania_rpt.person as pr where pr.pid = pa.pid AND (patient_status != 'REGISTERED' AND patient_status != 'SCREENED')" + filter;
							
			else if (report.equalsIgnoreCase ("TB Suspects Symptoms Stats"))
				query = "select sum(CHEST_PAIN = 'Y') as CHEST_PAIN, sum(COUGH = 'Y') as COUGH, sum(FEVER = 'Y') as FEVER, sum(HAEMOPTYSIS = 'Y') as HAEMOPTYSIS, sum(NIGHT_SWEAT = 'Y') as NIGHT_SWEAT from tbr3tanzania_rpt.person as pr, tbr3tanzania_rpt.patient as pa left outer join (SELECT max(e_id), pid1, CHEST_PAIN, COUGH, FEVER, HAEMOPTYSIS, NIGHT_SWEAT FROM tbr3tanzania_rpt.Enc_SCREEN_A group by pid1) as sc on sc.pid1 = pa.pid  where (patient_status != 'REGISTERED' AND patient_status != 'SCREENED') and pr.pid = pa.pid" + filter; 
			
			else if (report.equalsIgnoreCase ("No. of Confirmed Cases"))
				query = "select count(*) as CONFIRMED from tbr3tanzania_rpt.patient inner join tbr3tanzania_rpt.person using (pid) where disease_confirmed = 1";
			
			else if (report.equalsIgnoreCase ("No. of Closed Cases"))
				query = "select count(*) as CLOSED from tbr3tanzania_rpt.patient as pa, tbr3tanzania_rpt.person as pr where pr.pid = pa.pid AND (patient_status = 'CLOSED')" + filter;
			
			else if (report.equalsIgnoreCase ("Closed Cases Stats"))
				query = "select sum(treatment_outcome = 'CURED') as CURED, sum(treatment_outcome = 'DIED') as DIED, sum(treatment_outcome = 'TX_FAIL') as FAIL, sum(treatment_outcome = 'TX_COMP') as COMP, sum(treatment_outcome = 'OTHER') as OTHER from tbr3tanzania_rpt.patient as pa, tbr3tanzania_rpt.person as pr where pr.pid = pa.pid AND (patient_status = 'CLOSED')" + filter;

			
			ResultSet result;
			Connection con = HibernateUtil.util.getSession ().connection ();
			con.setCatalog ("tbr3tanzania_rpt");
			Statement statement = con.createStatement ();
			result = statement.executeQuery (query);
			result.next ();
			if(report.equalsIgnoreCase("No. of Patient Screened")){
				int count = result.getInt("SCREENED");	
				return ("# of Patient screened:"+String.valueOf (count));
			}
			else if (report.equalsIgnoreCase("No. of Suspect Identified")){
				int count = result.getInt("SUSPECT");	
				return ("# of TB Suspects:"+String.valueOf (count));
			}
			else if (report.equalsIgnoreCase ("TB Suspects Symptoms Stats")){
				int c_chestPain = result.getInt("CHEST_PAIN");
				int c_cough = result.getInt("COUGH");
				int c_fever = result.getInt("FEVER");
				int c_haemoptysis = result.getInt("HAEMOPTYSIS");
				int c_nightSweat = result.getInt("NIGHT_SWEAT");
				return ("# of TB Suspects with Chest Pain:"+String.valueOf (c_chestPain)+", Cough:"+String.valueOf (c_cough)+", Fever:"+String.valueOf (c_fever)+", Haemoptysis:"+String.valueOf (c_haemoptysis)+", Night Sweat:"+String.valueOf (c_nightSweat));
			}
			else if (report.equalsIgnoreCase ("No. of Confirmed Cases")){
				int confirm = result.getInt("CONFIRMED");
				return ("# of Confirmed Cases:"+String.valueOf (confirm));
			}
			else if (report.equalsIgnoreCase("No. of Closed Cases")){
				int count = result.getInt("CLOSED");	
				return ("# of Closed Cases:"+String.valueOf (count));
			}
			else if (report.equalsIgnoreCase ("Closed Cases Stats")){
				int c_cured = result.getInt("CURED");
				int c_comp = result.getInt("COMP");
				int c_fail = result.getInt("FAIL");
				int c_died = result.getInt("DIED");
				int c_other = result.getInt("OTHER");
				return ("# of Treatment Outcome as Cured:"+String.valueOf (c_cured)+", Completed:"+String.valueOf (c_comp)+", Failed:"+String.valueOf (c_fail)+", Died:"+String.valueOf (c_died)+", Other:"+String.valueOf (c_other));
			}
			
			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
			
		
		return null;
	}
	
	// creating PDF drom query without reporting tool!
	
	@SuppressWarnings("deprecation")
	public static String generatePDFfromQuery (String database, String query) 
	{
		
	    try
		{
	    	String dest = TBRT.getResourcesPath () + String.valueOf (new Date ().getTime ()) + ".pdf";
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(dest));
			document.open();	

			Connection con = HibernateUtil.util.getSession ().connection ();
			con.setCatalog (database);
			Statement statement = con.createStatement ();
			ResultSet result = statement.executeQuery (query);
			int range = result.getMetaData ().getColumnCount ();
			PdfPTable table = new PdfPTable(range);
			
			for (int i = 0; i < range; i++)
			{
				String columnName = result.getMetaData ().getColumnName (i + 1);
				PdfPCell c1 = new PdfPCell(new Phrase(columnName));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(c1);
			}
			table.setHeaderRows(1);
			
			while (result.next ())
			{
				for (int i = 0; i < range; i++){
					PdfPCell c1 = new PdfPCell(new Phrase(result.getString (i + 1)));
					c1.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(c1);
				}
			}
			
		    document.add(table);
		    document.close ();
		    return dest.substring (dest.lastIndexOf ('/') + 1);
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
			return "";
		}

		
	}
	
	
	@SuppressWarnings("deprecation")
	public static String generateCSVfromQuery (String database, String query, char separator)
	{
		try
		{
			Connection con = HibernateUtil.util.getSession ().connection ();
			con.setCatalog (database);
			Statement statement = con.createStatement ();
			ResultSet result = statement.executeQuery (query);
			ArrayList<String> list = new ArrayList<String> ();
			int range = result.getMetaData ().getColumnCount ();
			String record = "";
			for (int i = 0; i < range; i++)
				record += result.getMetaData ().getColumnName (i + 1) + separator;
			list.add (record.substring (0, record.length () - 1));
			while (result.next ())
			{
				record = "";
				for (int i = 0; i < range; i++)
					record += result.getString (i + 1) + separator;
				if (record.length () > 0)
					list.add (record.substring (0, record.length () - 1));
			}
			String dest = TBRT.getResourcesPath () + String.valueOf (new Date ().getTime ()) + ".csv";
			StringBuilder text = new StringBuilder ();
			for (int i = 0; i < list.size (); i++)
				text.append (list.get (i) + "\r\n");
			// Delete file if existing
			try
			{
				File file = new File (dest);
				file.delete ();
				Writer output = null;
				output = new BufferedWriter (new FileWriter (file));
				output.write (text.toString ());
				output.close ();
				output.flush ();
			}
			catch (Exception e)
			{
				// Not implemented
			}
			return dest.substring (dest.lastIndexOf ('/') + 1);
		}
		catch (Exception e)
		{
			e.printStackTrace ();
			return "";
		}
	}

	@SuppressWarnings({"deprecation", "rawtypes", "unchecked"})
	public static String generateReportFromQuery (String database, String fileName, String query, boolean export)
	{
		try
		{
			
			Connection con = HibernateUtil.util.getSession ().connection ();
			con.setCatalog (database);
			Statement statement = con.createStatement ();
			ResultSet result = statement.executeQuery (query);
			JRResultSetDataSource resultSource = new JRResultSetDataSource (result);	

			Map hashMap = new HashMap();    
			hashMap.put("UserName", TBRT.getCurrentUserName ());
			
			JasperReport jasperReport = JasperCompileManager.compileReport (TBRT.getReportPath () + fileName
					+ (fileName.endsWith (".jrxml") ? "" : ".jrxml"));
			JasperPrint print = JasperFillManager.fillReport (jasperReport, hashMap, resultSource);
			String dest = TBRT.getResourcesPath () + String.valueOf (new Date ().getTime ())
					+ (export == true ? ".csv" : ".pdf"); 
			
			// Delete file if existing
			try
			{
				File file = new File (dest);
				file.delete ();
			}
			catch (Exception e)
			{
				// Not implemented
			}
			JRAbstractExporter exporter;
			if (export)
				exporter = new JRCsvExporter ();
			else
				exporter = new JRPdfExporter ();
			//exporter = new JRHtmlExporter ();
			exporter.setParameter (JRExporterParameter.JASPER_PRINT, print);
			exporter.setParameter (JRExporterParameter.OUTPUT_FILE, new File (dest));
			exporter.exportReport ();
			return dest.substring (dest.lastIndexOf ('/') + 1);
		}
		catch (Exception e)
		{
			e.printStackTrace ();
			return "error!";
		}
	}

	@SuppressWarnings("deprecation")
	public static String generateReport (String fileName, Parameter[] params, boolean export)
	{
		try
		{
			Connection con = HibernateUtil.util.getSession ().connection ();
			con.setCatalog (TBRT.getDatabaseName ());
			HashMap<String, Object> map = new HashMap<String, Object> ();
			for (int i = 0; i < params.length; i++)
			{
				/**
				 * Cast the parameter into appropriate type
				 */
				map.put (params[i].getName (), toObject (params[i]));
			}
			String filePath = TBRT.getReportPath () + fileName + (fileName.endsWith (".jrxml") ? "" : ".jrxml");
			JasperReport jasperReport = JasperCompileManager.compileReport (filePath);
			JasperPrint print = JasperFillManager.fillReport (jasperReport, map, con);
			String dest = TBRT.getResourcesPath () + String.valueOf (new Date ().getTime ())
					+ (export == true ? ".csv" : ".pdf");
			// Delete file if existing
			try
			{
				File file = new File (dest);
				file.delete ();
			}
			catch (Exception e)
			{
				// Not implemented
			}
			JRAbstractExporter exporter;
			if (export)
				exporter = new JRCsvExporter ();
			else
				exporter = new JRPdfExporter ();
			exporter.setParameter (JRExporterParameter.JASPER_PRINT, print);
			exporter.setParameter (JRExporterParameter.OUTPUT_FILE, new File (dest));
			exporter.exportReport ();
			return dest.substring (dest.lastIndexOf ('/') + 1);
		}
		catch (Exception e)
		{
			e.printStackTrace ();
			return "";
		}
	}

	/**
	 * Get list of Reports in reports directory with last modified date
	 * 
	 * @return String[][]
	 */
	public static String[][] getReportList ()
	{
		String[][] reports;
		File dir = new File (TBRT.getReportPath ());
		FilenameFilter filter = new FilenameFilter ()
		{
			public boolean accept (File dir, String name)
			{
				for (String s : allowedExtensions)
					if (name.endsWith (s))
						return true;
				return false;
			}
		};
		File[] files = dir.listFiles (filter);
		reports = new String[files.length][3];
		for (int i = 0; i < files.length; i++)
		{
			reports[i][0] = String.valueOf (i);
			reports[i][1] = files[i].getName ();

			Date date = new Date (files[i].lastModified ());
			SimpleDateFormat format = new SimpleDateFormat ("dd-MMM-yyyy");
			reports[i][2] = format.format (date);
		}
		return reports;
	}

	
	public static Object toObject (Parameter param)
	{
		try
		{
			String value = param.getValue ();
			switch (param.getType ())
			{
				case BOOLEAN :
					return Boolean.parseBoolean (value);
				case BYTE :
					return Byte.parseByte (value);
				case CHAR :
					return value.charAt (0);
				case DATE :
					return new Date (Long.parseLong (value));
				case DOUBLE :
					return Double.parseDouble (value);
				case FLOAT :
					return Float.parseFloat (value);
				case INT :
					return Integer.parseInt (value);
				case LONG :
					return Long.parseLong (value);
				case SHORT :
					return Short.parseShort (value);
				case STRING :
					return value;
				default :
					return null;
			}
		}
		catch (NumberFormatException e)
		{
			e.printStackTrace ();
			return null;
		}
	}

}
