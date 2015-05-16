/**
 * Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.
 * 
 * This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
 * You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html
 * Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors.
 * Contributors: Tahira Niazi
 */
package com.ihsinformatics.tbr3fieldmonitoring.server;

import java.io.File;
import java.util.Properties;

import com.ihsinformatics.tbr3fieldmonitoring.server.util.HibernateUtil;
import com.ihsinformatics.tbr3fieldmonitoring.shared.model.Defaults;
import com.ihsinformatics.tbr3fieldmonitoring.shared.model.Definition;

import org.openmrs.api.APIException;
import org.openmrs.api.context.Context;
import org.openmrs.api.context.ContextAuthenticationException;
import org.openmrs.module.ModuleMustStartException;
import org.openmrs.util.DatabaseUpdateException;
import org.openmrs.util.InputRequiredException;
import org.openmrs.util.OpenmrsUtil;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.ihsinformatics.tbr3fieldmonitoring.client.ServerService;
import com.ihsinformatics.tbr3fieldmonitoring.shared.CustomMessage;
import com.ihsinformatics.tbr3fieldmonitoring.shared.ErrorType;
import com.ihsinformatics.tbr3fieldmonitoring.shared.TBR3;
import com.ihsinformatics.tbr3fieldmonitoring.shared.model.Location;

/**
 * @author Tahira
 * 
 */
public class ServerServiceImpl extends RemoteServiceServlet implements
		ServerService
{
	private static final long serialVersionUID = 4123609914579659870L;
	private static String applicationPath = "";
	private static String propertiesFilePath = "";

	public ServerServiceImpl()
	{
		String currentDirectory = System.getProperty("user.dir");
		System.out.println("Current directory:" + currentDirectory);
		if (currentDirectory.startsWith("/"))
			applicationPath = "/var/lib/tomcat6/webapps/irzimbabwe/"; // change
																		// this
																		// later
		else
			applicationPath = "C:\\workspace2\\tbr3fieldmonitoring\\";
		propertiesFilePath = applicationPath + "tbr3fieldmonitoring.properties";
		initOpenMrs();
	}

	// public static void main(String[] args)
	// {
	// ServerServiceImpl service = new ServerServiceImpl();
	// }

	public Boolean initOpenMrs()
	{
		File propsFile = new File(OpenmrsUtil.getApplicationDataDirectory(),
				"openmrs-runtime.properties");
		Properties props = new Properties();
		OpenmrsUtil.loadProperties(props, propsFile);
		try
		{
			Context.startup(
					"jdbc:mysql://localhost:3306/openmrs?autoReconnect=true",
					"root", "liamtekcor", props);
		}
		catch (ModuleMustStartException e)
		{
			e.printStackTrace();
		}
		catch (DatabaseUpdateException e)
		{
			e.printStackTrace();
		}
		catch (InputRequiredException e)
		{
			e.printStackTrace();
		}
		try
		{
			Context.openSession();
			// Context.authenticate("tahira", "TahiraNiazi007");
		}
		finally
		{
			Context.closeSession();
		}

		return false;
	}

	public String authenticate(String userName, String password)
	{
		String response = "success";
		try
		{
			Context.openSession();
			Context.authenticate(userName, password);
		}
		catch (ContextAuthenticationException e)
		{
			// e.printStackTrace();
			response = CustomMessage
					.getErrorMessage(ErrorType.AUTHENTICATION_ERROR);
		}
		return response;
	}

	@Override
	public Location getLocation(String locationName)
	{
		Context.openSession();
		Context.authenticate("tahira", "TahiraNiazi007");
		System.out.println("Context session has started");
		Location location = new Location();
		try
		{
			org.openmrs.Location omrsLocation = Context.getLocationService()
					.getLocation(locationName);
			location.setLocationId(omrsLocation.getLocationId().toString());
			location.setLocationName(omrsLocation.getName());
			if (location != null)
			{
				System.out.println("location found");
			}
		}
		catch (APIException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return location;
	}

	@Override
	public Boolean saveLocation(Location location)
	{
		Boolean result = true;
		org.openmrs.Location omrsLocation = new org.openmrs.Location();
		omrsLocation.setName(location.getLocationName());
		// Rest of the properties
		org.openmrs.Location savedLocation = Context.getLocationService()
				.saveLocation(omrsLocation);
		if (savedLocation == null)
			result = false;
		return result;
	}

	/**
	 * Sets current user name, this is due to a strange GWT bug/feature that
	 * shared variables, set from Client-side appear to be empty on Server-side
	 * code
	 * 
	 * @return
	 */
	public void setCurrentUser(String userName)
	{
		TBR3.setCurrentUserName(userName);
		// IRZ.setCurrentRole(role);
	}

	public static void main(String[] args)
	{
		try
		{
			ServerServiceImpl impl = new ServerServiceImpl();
			Defaults[] defaults = impl.getDefaults();
			System.out.println("defaults table length is " + defaults.length);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Get default values to be used on front-ends
	 */
	public Defaults[] getDefaults()
	{
		Object[] objs = HibernateUtil.util.findObjects("from Defaults");
		Defaults[] defaults = new Defaults[objs.length];
		for (int i = 0; i < objs.length; i++)
		{
			Defaults def = (Defaults) objs[i];
			defaults[i] = def;
		}
		return defaults;
	}

	/**
	 * Get all definitions for static data
	 */
	public Definition[] getDefinitions()
	{
		Object[] objs = HibernateUtil.util.findObjects("from Definition");
		Definition[] definitions = new Definition[objs.length];
		for (int i = 0; i < objs.length; i++)
		{
			Definition def = (Definition) objs[i];
			definitions[i] = def;
		}
		return definitions;
	}

}
