package server.database;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * The Class QueryCreator.
 * Static class 
 */
public class QueryCreator {
	
	/** The Constant createSchemaQueryFormat. */
	private static final String createSchemaQueryFormat = "CREATE SCHEMA %s;";
	
	/** The Constant insertQueryFormat. */
	private static final String insertQueryFormat = "INSERT INTO %s(%s) values(%s);";
	
	/** The Constant selectQueryFormat. */
	private static final String selectQueryFormat = "SELECT %s FROM %s %s %s;";
	
	/** The Constant selectDistinctQueryFormat. */
	private static final String selectDistinctQueryFormat = "SELECT DISTINCT %s FROM %s %s %s;";
	
	/** The Constant updateQueryFormat. */
	private static final String updateQueryFormat = "UPDATE %s SET %s WHERE %s;";
	
	/** The Constant deleteQueryFormat. */
	private static final String deleteQueryFormat = "DELETE FROM %s WHERE %s;";
	
	/** The Constant createTableQueryFormat. */
	private static final String createTableQueryFormat = "CREATE TABLE %s(%s);";
	
	/**
	 * Override public contractor to make it static.
	 */
	private QueryCreator() {}
	
	/**
	 * Creates the parameters line. (for PreparedStatement)
	 * Example ?, ?, ?, .....
	 * @param size the size
	 * @return the string
	 */
	private static String createParametersLine(int size) {
		return String.join(", ", Collections.nCopies(size, "?"));
	}
	
	/**
	 * Creates the columns line.
	 *
	 * @param column the column values
	 * @return the string
	 */
	private static String createColumnsLine(List<String> column) {
		if((column == null) || column.isEmpty()) return "*";
		String columns = String.format("`%s`", column.get(0));
		for(int i=1; i<column.size(); i++) {
			columns += String.format(",`%s`", column.get(i));
		}
		return columns;
	}
	
	/**
	 * Creates the equalities line.
	 *
	 * @param whereCol the where columns
	 * @param operator the operator
	 * @return the string
	 */
	private static String createEqualitiesLine(List<String> whereCol, List<String> operator) {
		if((whereCol == null) || (whereCol.isEmpty())) return "";
		String whereCols = String.format("`%s`%s?", whereCol.get(0), (((operator == null) || (operator.isEmpty())) ? "=" : operator.get(0)));
		for(int i=1; i<whereCol.size(); i++) {
			whereCols += String.format(", `%s`%s?", whereCol.get(i), (((operator == null) || (operator.isEmpty())) ? "=" : operator.get(i)));
		}
		return whereCols;
	}
	
	/**
	 * Creates the schema query format.
	 *
	 * @param schema the schema name
	 * @return the string query
	 */
	public static String createSchemaQueryFormat(String schema) {
		return String.format(QueryCreator.createSchemaQueryFormat, schema);
	}
	
	/**
	 * Creates the insert query format.
	 *
	 * @param table the table name
	 * @param column the columns list
	 * @return the string query
	 */
	public static String createInsertQueryFormat(String table, List<String> column) {
		return String.format(QueryCreator.insertQueryFormat, table, createColumnsLine(column), createParametersLine(column.size()));
	}
	
	/**
	 * Creates the select query format.
	 *
	 * @param table the table name
	 * @param selectCol the select columns
	 * @param whereCol the where columns
	 * @param operators the operators
	 * @return the string query
	 */
	public static String createSelectQueryFormat(String table, List<String> selectCol, List<String> whereCol, List<String> operators) {
		if((whereCol == null) || whereCol.isEmpty())
			return String.format(QueryCreator.selectQueryFormat, createColumnsLine(selectCol), table, "" ,createEqualitiesLine(whereCol,operators));
		return String.format(QueryCreator.selectQueryFormat, createColumnsLine(selectCol), table, "WHERE", createEqualitiesLine(whereCol,operators));
	}

	/**
	 * Creates the select distinct query format.
	 *
	 * @param table the table name
	 * @param selectCol the select columns
	 * @param whereCol the where columns
	 * @param operators the operators
	 * @return the string query
	 */
	public static String createSelectDistinctQueryFormat(String table, List<String> selectCol, List<String> whereCol, List<String> operators) {
		if((whereCol == null) || whereCol.isEmpty())
			return String.format(QueryCreator.selectDistinctQueryFormat, createColumnsLine(selectCol), table, "", createEqualitiesLine(whereCol,operators));
		return String.format(QueryCreator.selectDistinctQueryFormat, createColumnsLine(selectCol), table, "WHERE", createEqualitiesLine(whereCol,operators));
	}
	
	/**
	 * Creates the update query format.
	 *
	 * @param table the table name
	 * @param setCol the set columns
	 * @param whereCol the where columns
	 * @param operators the operators
	 * @return the string query
	 */
	public static String createUpdateQueryFormat(String table, List<String> setCol, List<String> whereCol, List<String> operators) {
		return String.format(QueryCreator.updateQueryFormat, table, createEqualitiesLine(setCol, null), createEqualitiesLine(whereCol, operators));
	}
	
	/**
	 * Creates the delete query format.
	 *
	 * @param table the table name
	 * @param whereCol the where columns
	 * @param operators the operators
	 * @return the string query
	 */
	public static String createDeleteQueryFormat(String table, List<String> whereCol, List<String> operators) {
		return String.format(QueryCreator.deleteQueryFormat, table, createEqualitiesLine(whereCol, operators));
	}
	
	/**
	 * Creates the table query format.
	 *
	 * @param table the table name
	 * @param map the map schema tables
	 * @return the string query
	 */
	public static String createTableQueryFormat(String table, Map<String, String> map) {
		List<String> colms = new ArrayList<String>();
		for (String col : map.keySet())
			colms.add(String.format("`%s` %s", col, map.get(col)));
		return String.format(QueryCreator.createTableQueryFormat, table, String.join(", ", colms));
	}
	
}
