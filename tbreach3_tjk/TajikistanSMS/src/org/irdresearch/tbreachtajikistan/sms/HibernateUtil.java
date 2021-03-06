/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */
package org.irdresearch.tbreachtajikistan.sms;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.ListIterator;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.Work;
/*import org.irdresearch.tbreach2.server.HibernateUtil;*/
import org.irdresearch.tbreach2.shared.model.BaselineDetails;
import org.irdresearch.tbreach2.shared.model.PatientDetails;
import org.irdresearch.tbreach2.shared.model.PatientReminder;
import org.irdresearch.tbreach2.shared.model.PatientResponse;
import org.irdresearch.tbreach2.shared.model.Reminder;
import org.irdresearch.tbreach2.shared.model.ReminderHistory;
import org.irdresearch.tbreach2.shared.model.ReminderText;
import org.irdresearch.tbreach2.shared.model.Users;
import org.irdresearch.tbreachtajikistan.utils.DateUtils;

public class HibernateUtil implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static HibernateUtil		util				= new HibernateUtil ();
	private static SessionFactory	factory;
	private Class<?>[]				classes;
	
	
	/**
	 * Default Constructor to initialize Session
	 */
	@SuppressWarnings("deprecation")
	public HibernateUtil ()
	{
		/* Use when trying Annotation */
		// factory = getInitializedConfiguration().buildSessionFactory();
		factory = new Configuration ().configure ().buildSessionFactory ();
	}
	
	/**
	 * Get a list of classes in the Hibernate model
	 * 
	 * @return
	 */
	public Class<?>[] getModelClasses ()
	{
		return classes;
	}

	/**
	 * Create a query object from query string
	 * 
	 * @param queryString
	 * @return
	 */
	public Query create (String query)
	{
		return getSession ().createQuery (query);
	}

	/**
	 * Get record count for given query
	 * 
	 * @return
	 */
	public long count (String query)
	{
		return count (create (query));
	}

	/**
	 * Get record count for given query
	 * 
	 * @return
	 */
	public long count (Query query)
	{
		return Long.parseLong (query.uniqueResult ().toString ());
	}

	/**
	 * Overloaded method for findObject(String)
	 * 
	 * @param query
	 * @return
	 */
	public Object findObject (String query)
	{
		Object obj[] = findObjects (query);
		if(obj != null)
			return obj[0];
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<PatientDetails> findPatients ()
	{
		try{
		Session session = getSession ();
		Query q=session.createQuery("from PatientDetails");
		return q.list();
		}
		
		catch (Exception e)
		{
			e.printStackTrace ();
			return null;
		}
	}
	
	/**
	 * Get a list of PatientResponses
	 * 
	 * */
	
	@SuppressWarnings("unchecked")
	public List<PatientResponse> getResponses(Integer patientIdentifier, Date datesmall, Date dategreater) {
		String daybgn=DateUtils.truncateDate(datesmall);
		String dayend=DateUtils.roundoffDate(dategreater);
		System.out.println(daybgn+".........."+dayend);
		Session session = getSession ();
		Query q=session.createQuery("from PatientResponse where PID=? and recieve_date between ? and ? order by recieve_date desc" );
		q.setInteger(0,patientIdentifier);
		q.setString(1, daybgn);
		q.setString(2, dayend);
		return q.list();
	}
	
	public void addReminderHistory(ReminderHistory record) {
		Session session = getSession ();
		session.save(record);
		session.flush ();
		session.close ();
	}
	
		
	@SuppressWarnings("unchecked")
	public List<ReminderHistory> getStatusByReferenceNumber() {
		Session session = getSession ();
		Query q=session.createQuery("from ReminderHistory where reminder_status='LOGGED'");
		return q.list();
	}
	
	
	public PatientDetails getPatientHavingCellNumber(String cellNum) {
		
		return (PatientDetails) util.findObject ("from PatientDetails where phone1 like '%"+cellNum+"'");
		
	}
	
	public Reminder getReminder(int id) {
		
		return (Reminder) util.findObject ("from Reminder where reminder_id ="+id);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<BaselineDetails> getAllPatientsFromBaselineDetails() {
		Session session = getSession ();
		Query q=session.createQuery("from BaselineDetails");
		return q.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<PatientDetails> getAllPatientsFromPatientDetails() {
		Session session = getSession ();
		Query q=session.createQuery("from PatientDetails");
		return q.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<PatientDetails> getAllActivePatientsFromPatientDetails() {
		Session session = getSession ();
		Query q=session.createQuery("from PatientDetails where Active = '1'");
		return q.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<PatientReminder> gettimeofReminder(Integer patientIdentifier) {
		Session session = getSession ();
		Query q=session.createQuery("from PatientReminder where p_id=?");
		q.setInteger(0, patientIdentifier);
		return q.list();
	}
	
	/**
	 * Get a count of PatientResponses
	 * 
	 * */
	
	@SuppressWarnings("unchecked")
	public List<ReminderHistory> getReminderNumber(Integer patientIdentifier, Date datesmall, Date dategreater) {
		String daybgn=DateUtils.truncateDate(datesmall);
		String dayend=DateUtils.roundoffDate(dategreater);
		System.out.println(daybgn+".........."+dayend);
		Session session = getSession ();
		Query q=session.createQuery("from ReminderHistory where PID=? and sent_date between ? and ? " );
		q.setInteger(0,patientIdentifier);
		q.setString(1, daybgn);
		q.setString(2, dayend);
		return q.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<ReminderText> getReminderText(int reminderType) {
		Session session = getSession ();
		Query q=session.createQuery("from ReminderText where reminder_id=? order by record_num asc" );
		q.setInteger(0,reminderType);
		return q.list();
	}
	
	public void addResponseRecord(PatientResponse response) {
		Session session = getSession ();
		session.save(response);
		session.flush ();
		session.close ();
	}
	
	

	/**
	 * Get a list of all PatientReminders
	 * 
	 * */
	
	@SuppressWarnings("unchecked")
	public List<PatientReminder> getAllPatientReminderRecord() {
		Session session = getSession ();
		Query q=session.createQuery("from PatientReminder");
		if (q == null) System.out.print ("hell!");
		return q.list();
	}

	/**
	 * Get a list of objects from given query.
	 * 
	 * @param query
	 * @return
	 */
	public Object[] findObjects (String query)
	{
		try
		{
			Session session = getSession ();
			Query q = session.createQuery (query);
			List<?> list = q.list ();
			return list.toArray ();
		}
		catch (Exception e)
		{
			e.printStackTrace ();
			return null;
		}
	}

	/**
	 * Get a list of objects from given SQL query
	 * 
	 * @param SQLQuery
	 * @return
	 */
	public Object selectObject (String SqlQuery)
	{
		return selectObjects (SqlQuery)[0];
	}

	/**
	 * Get a list of objects from given SQL query
	 * 
	 * @param SQLQuery
	 * @return
	 */
	public Object[] selectObjects (String SqlQuery)
	{
		Session session = getSession ();
		// session.beginTransaction();
		SQLQuery q = session.createSQLQuery (SqlQuery);
		List<?> list = q.list ();
		return list.toArray ();
	}

	/**
	 * Get a list of objects from given SQL query
	 * 
	 * @param SQLQuery
	 * @return
	 */
	public Object[][] selectData (String sqlQuery)
	{
		Session session = getSession ();
		SQLQuery q = session.createSQLQuery (sqlQuery);
		List<?> list = q.list ();
		Object[][] tableData = new Object[list.size ()][];
		try
		{
			int cnt = 0;
			for (ListIterator<?> iter = list.listIterator (); iter.hasNext ();)
			{
				Object[] array = (Object[]) iter.next ();
				tableData[cnt++] = array;
			}
			return tableData;
		}
		catch (Exception e)
		{
			e.printStackTrace ();
			return null;
		}
	}

	/**
	 * Update an object present in the database
	 * 
	 * @param obj
	 * @return
	 */
	public boolean update (Object obj)
	{
		try
		{
			Session session = getSession ();
			session.update (obj);
			session.flush ();
			session.close ();
			//recordLog (LogType.UPDATE, obj);
			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace ();
			return false;
		}
	}

	/**
	 * Saves an object into database
	 * 
	 * @param obj
	 * @return
	 */
	public boolean save (Object obj)
	{
		try
		{
			Session session = getSession ();
			session.save (obj);
			session.flush ();
			session.close ();
			//recordLog (LogType.INSERT, obj);
			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace ();
			return false;
		}
	}

	/**
	 * Deletes an Object from database
	 * 
	 * @param obj
	 * @return
	 */
	public boolean delete (Object obj)
	{
		try
		{
			Session session = getSession ();
			session.delete (obj);
			session.flush ();
			session.close ();
			//recordLog (LogType.DELETE, obj);
			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace ();
			return false;
		}
	}

	/**
	 * Records an object into one of the Log tables
	 * 
	 * @param logType
	 *            determine type of log (Delete Log, Change Log, etc.)
	 * @param obj
	 *            actual object to be logged
	 */
	/*public void recordLog (LogType logType, Object obj)
	{
		Object logObj = null;
		Class<?> objClass = obj.getClass ();
		String className = obj.getClass ().getSimpleName ();
		logObj = new Log_Data (UserAlg.getCurrentUser (), new Date (), logType.toString (), className, (objClass).cast (obj).toString ());
		try
		{
			Session session = getSession ();
			session.save (logObj);
			session.flush ();
			session.close ();
		}
		catch (Exception e)
		{
			e.printStackTrace ();
		}
	}*/

	/**
	 * Executes a DML command
	 * 
	 * @param command
	 *            SQL statement as command
	 * @return number of records affected
	 */
	public int runCommand (String command)
	{
		Session session = getSession ();
		Transaction transaction = session.beginTransaction ();
		SQLQuery q = session.createSQLQuery (command);
		int results = q.executeUpdate ();
		transaction.commit ();
		return results;
	}

	/**
	 * Executes a Stored Procedure
	 * 
	 * @param procedure
	 *            SQL statement as procedure
	 * @return number of records affected
	 */
	public boolean runProcedure (final String procedure)
	{
		final Session session = getSession ();
		session.beginTransaction ();
		session.doWork (new Work()
		{
			public void execute (Connection conn) throws SQLException
			{
				CallableStatement callableStatement = conn.prepareCall (procedure);
				callableStatement.execute ();
				session.getTransaction ().commit ();
			}
		});
		return true;
	}

	/**
	 * @return the session
	 */
	public Session getSession ()
	{
		return factory.openSession ();
	}

	/**
	 * Returns the respective Database class to which an object belongs
	 * 
	 * @param obj
	 *            object to be identified
	 * @return Class object of class 'Class'
	 */
	public Class<?> identifyClass (Object obj)
	{
		return obj.getClass ();
	}

	/**
	 * Scans all classes accessible from the context class loader which belong
	 * to the given package and sub packages.
	 * 
	 * @param packageName
	 *            The base package
	 * @return The classes
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public static Class<?>[] getClasses (String packageName) throws ClassNotFoundException, IOException
	{
		ClassLoader classLoader = Thread.currentThread ().getContextClassLoader ();
		assert classLoader != null;
		String path = packageName.replace ('.', '/');
		Enumeration<URL> resources = classLoader.getResources (path);
		List<File> dirs = new ArrayList<File> ();
		while (resources.hasMoreElements ())
		{
			URL resource = resources.nextElement ();
			dirs.add (new File (resource.getFile ()));
		}
		ArrayList<Class<?>> classes = new ArrayList<Class<?>> ();
		for (File directory : dirs)
		{
			classes.addAll (findClasses (directory, packageName));
		}
		return classes.toArray (new Class[classes.size ()]);
	}

	/**
	 * Recursive method used to find all classes in a given directory and sub
	 * directories.
	 * 
	 * @param directory
	 *            The base directory
	 * @param packageName
	 *            The package name for classes found inside the base directory
	 * @return The classes
	 * @throws ClassNotFoundException
	 */
	private static List<Class<?>> findClasses (File directory, String packageName) throws ClassNotFoundException
	{
		List<Class<?>> classes = new ArrayList<Class<?>> ();
		if (!directory.exists ())
		{
			return classes;
		}
		File[] files = directory.listFiles ();
		for (File file : files)
		{
			if (file.isDirectory ())
			{
				assert !file.getName ().contains (".");
				classes.addAll (findClasses (file, packageName + "." + file.getName ()));
			}
			else if (file.getName ().endsWith (".class"))
			{
				classes.add (Class.forName (packageName + '.' + file.getName ().substring (0, file.getName ().length () - 6)));
			}
		}
		return classes;
	}

	public PatientDetails getPatientDetail (int p_id)
	{
		return (PatientDetails) util.findObject ("from PatientDetails where PID = "+p_id);
	}
	
	public Users getUser (String u_id)
	{
		return (Users) util.findObject ("from Users where PID = '"+u_id+"'");
	}


}
