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
package com.ihsinformatics.tbr3fieldmonitoring.client;

import com.ihsinformatics.tbr3fieldmonitoring.shared.model.Defaults;
import com.ihsinformatics.tbr3fieldmonitoring.shared.model.Definition;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.ihsinformatics.tbr3fieldmonitoring.shared.model.Location;

/**
 * @author Tahira
 * 
 */
@RemoteServiceRelativePath("greet")
public interface ServerService extends RemoteService
{
	/**
	 * @param locationName
	 * @return
	 */
	Location getLocation(String locationName) throws Exception;
	/**
	 * @param location
	 * @return
	 */
	Boolean saveLocation(Location location) throws Exception;
	
	String authenticate(String userName, String password) throws Exception;
	
	void  setCurrentUser(String userName) throws Exception;
	
	Definition[] getDefinitions () throws Exception;

	Defaults[] getDefaults () throws Exception;
	
}
