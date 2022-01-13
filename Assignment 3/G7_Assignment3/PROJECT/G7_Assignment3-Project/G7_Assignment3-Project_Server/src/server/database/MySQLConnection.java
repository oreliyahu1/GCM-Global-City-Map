package server.database;

import entities.Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.HashMap;

// TODO: Auto-generated Javadoc
/**
 * The Class MySQLConnection.
 * Abstract class
 */
public class MySQLConnection {

	/** The db config. */
	private Database dbConfig;
	
	/** The mysql connection. */
	private Connection mysqlConnection;
	
	/**
	 * Instantiates a new my SQL connection.
	 *
	 * @param dbConfig the db config
	 */
	public MySQLConnection(Database dbConfig) {
		this.dbConfig = dbConfig;
	}
	
	/**
	 * Connect schema.
	 *
	 * @return true, if successfully connected
	 * @throws Exception the exception
	 * @throws SQLException the SQL exception
	 */
	protected boolean ConnectSchema() throws Exception, SQLException{	
		Class.forName("com.mysql.jdbc.Driver");	
        mysqlConnection = DriverManager.getConnection(dbConfig.getFullURL(), dbConfig.getDbUsername(), dbConfig.getDbPassword());
        return true;
	}

	/**
	 * Connect.
	 *
	 * @return true, if successfully connected
	 * @throws Exception the exception
	 * @throws SQLException the SQL exception
	 */
	protected boolean Connect() throws Exception, SQLException{	
		Class.forName("com.mysql.jdbc.Driver");
        mysqlConnection = DriverManager.getConnection(dbConfig.getShortURL(), dbConfig.getDbUsername(), dbConfig.getDbPassword());
        return true;
	}
	
	/**
	 * Gets the db config.
	 *
	 * @return the db config
	 */
	public Database getDbConfig() {
		return dbConfig;
	}
	
	/**
	 * Sets the db config.
	 *
	 * @param dbConfig the new db config
	 */
	public void setDbConfig(Database dbConfig) {
		this.dbConfig=dbConfig;
	}
	
	/**
	 * Close connection.
	 *
	 * @return true, if close was successful
	 * @throws SQLException the SQL exception
	 */
	protected boolean CloseConnection() throws SQLException{
		if(mysqlConnection == null) return false;
		mysqlConnection.close();
		return true;
	}
	
	/**
	 * Execute select query.
	 *
	 * @param query the query
	 * @param values the values of parsing in query
	 * @return the hash map of returned values
	 * @throws SQLException the SQL exception
	 */
	public HashMap<String, ArrayList<String>> executeSelectQuery(String query, ArrayList<String> values) throws SQLException
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		HashMap<String, ArrayList<String>> hr = new HashMap<String, ArrayList<String>>(); 
		try {
			ps = mysqlConnection.prepareStatement(query);	
			setNString(ps,values);
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
			
			// Create arr list,each returned columns 
			for (int i = 1; i <= rsmd.getColumnCount(); i++)
				hr.put(rsmd.getColumnLabel(i), new ArrayList<String>());
			 
			// Parsing the rows values in each column
			int rows = 0;
		    while (rs.next()) {
		    	rows++;
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					hr.get(rsmd.getColumnLabel(i)).add(rs.getString(i));
				}
			}
			System.out.println("MySQLConnection: SQL get query result. number of columns : " + rsmd.getColumnCount() + " rows : " + rows);
		}
		finally {
			if(ps != null)
				ps.close();
			if(rs != null)
				rs.close();
		}
		return hr;
	}
	
	/**
	 * Execute select query.
	 *
	 * @param query the query
	 * @return the hash map of returned values
	 * @throws SQLException the SQL exception
	 */
	public HashMap<String, ArrayList<String>> executeSelectQuery(String query) throws SQLException
	{
		return executeSelectQuery(query, null);
	}
	
	/**
	 * Execute query.
	 *
	 * @param query the query
	 * @param values the values of parsing in query
	 * @return true, if executed successful
	 * @throws SQLException the SQL exception
	 */
	public boolean executeQuery(String query, ArrayList<String> values) throws SQLException
	{
		PreparedStatement ps = null;
		int _reVal = 0;
		try {
			ps = mysqlConnection.prepareStatement(query);
			setNString(ps,values);
			_reVal = ps.executeUpdate();
			System.out.println("MySQLConnection: SQL update query succeed");
		}
		finally {
			try {
			if(ps != null)
				ps.close();
			}catch(Exception ex) { ex.printStackTrace(); }
			
		}
		return (_reVal != 0); // If there is any affected rows on table
	}
	
	/**
	 * Execute query.
	 *
	 * @param query the query
	 * @return true, if executed successful
	 * @throws SQLException the SQL exception
	 */
	public boolean executeQuery(String query) throws SQLException
	{
		Statement stmt = null;
		boolean _reVal = true;
		try {
			stmt = mysqlConnection.createStatement();
			System.out.println("MySQLConnection: " + query);
			_reVal = stmt.execute(query);
			
			System.out.println("MySQLConnection: SQL execute query succeed");
		}
		finally {
			if(stmt != null)
				stmt.close();
		}
		return _reVal;
	}
	
	/**
	 * Sets the N string.
	 *
	 * @param ps the ps
	 * @param values the values
	 * @throws SQLException the SQL exception
	 */
	private void setNString(PreparedStatement ps, ArrayList<String> values) throws SQLException {
		if((values != null) && (!values.isEmpty())) {
			for(int i = 0; i < values.size(); i++) {
				if(values.get(i).isEmpty() || values.get(i).toLowerCase().equals("null"))
					ps.setNString(i + 1, null);
				else
					ps.setNString(i + 1, values.get(i));
			}
			System.out.println("MySQLConnection: " + ps);
		}
	}
}
	
