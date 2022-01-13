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

/**
 * The Class MySQLConnection.
 * Abstract class
 */
public abstract class MySQLConnection {

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
		HashMap<String, ArrayList<String>> hr = new HashMap<String, ArrayList<String>>(); 
		System.out.println("MySQLConnection: " + query + values);
		PreparedStatement ps = mysqlConnection.prepareStatement(query);

		// Parsing values
		if((values != null) && (!values.isEmpty()))
			for(int i = 0; i < values.size(); i++)
				ps.setNString(i + 1, values.get(i));
		
		ResultSet rs = ps.executeQuery();
		ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
		
		// Create arr list,each returned columns 
		for (int i = 1; i <= rsmd.getColumnCount(); i++) {
			hr.put(rsmd.getColumnLabel(i), new ArrayList<String>());
		}
		 
		// Parsing the rows values in each column
		int rows = 0;
	    while (rs.next()) {
	    	rows++;
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				hr.get(rsmd.getColumnLabel(i)).add(rs.getString(i));
			}
		}
		System.out.println("MySQLConnection: SQL get query result. number of columns : " + rsmd.getColumnCount() + " rows : " + rows);
		rs.close();
		ps.close();
		return hr;
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
		int _reVal;
		PreparedStatement ps = mysqlConnection.prepareStatement(query);
		System.out.println(query + values);
		
		// Parsing values
		if((values != null) && (!values.isEmpty()))
			for(int i = 0; i < values.size(); i++)
				ps.setNString(i + 1, values.get(i));
		_reVal = ps.executeUpdate();
		ps.close();
		System.out.println("MySQLConnection: SQL update query succeed");
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
		boolean _reVal = true;
		Statement stmt = mysqlConnection.createStatement();
		System.out.println("MySQLConnection: " + query);
		_reVal = stmt.execute(query);
		stmt.close();
		System.out.println("MySQLConnection: SQL execute query succeed");
		return _reVal;
	}
}
	
