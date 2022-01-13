/*
 * By using this class , an automatic daily report created everyday , 
 * and an automatic examination executes , which check and send clients that their package will expires in 3 days
 *
 */

package server.database.requestHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;
import java.util.Map.Entry;

import entities.AbstractJsonToString;
import entities.Activity;
import entities.City;
import entities.Message;
import entities.Purchase;
import entities.RateRequest;
import entities.User;
import entities.VersionRequest;
import server.ClientDB;

// TODO: Auto-generated Javadoc
/**
 * The Class DailyRequests.
 * Extends AbstractRequest
 */
public class DailyRequests {
	
	/**
	 * Instantiates a new daily requests.
	 */
	private DailyRequests() {}
	
	/**
	 * Creates the daily report.
	 *
	 * @param cdb the cdb
	 * @return true, if successful
	 * @throws SQLException the SQL exception
	 */
	public static boolean createDailyReport(ClientDB cdb) throws SQLException {
		ArrayList<String> whereVal = new ArrayList<String>();
		Set<Entry<String, ArrayList<String>>> hdbDate = cdb.executeSelectQuery(config.database.queries.DailyReport.MySQL_QUERY_GET_DB_DATE).entrySet();
		whereVal.add(hdbDate.iterator().next().getValue().get(0));
		
		ArrayList<User> user = AbstractJsonToString.hashMapToArrayListEntities(entities.User::fromHashMap, cdb.executeSelectQuery(config.database.queries.User.MySQL_QUERY_GET_NEW_TODAY_USERS, whereVal));
		ArrayList<City> city = AbstractJsonToString.hashMapToArrayListEntities(entities.City::fromHashMap, cdb.executeSelectQuery(config.database.queries.City.MySQL_QUERY_GET_NEW_TODAY_COUNTRY_CITIES));
		ArrayList<Activity> activity = AbstractJsonToString.hashMapToArrayListEntities(entities.Activity::fromHashMap, cdb.executeSelectQuery(config.database.queries.Activity.MySQL_QUERY_GET_NEW_TODAY_ACTIVITIES, whereVal));
		ArrayList<Message> message = AbstractJsonToString.hashMapToArrayListEntities(entities.Message::fromHashMap, cdb.executeSelectQuery(config.database.queries.Message.MySQL_QUERY_GET_NEW_TODAY_NOTIFICATIONS, whereVal));
		ArrayList<Purchase> purchase = AbstractJsonToString.hashMapToArrayListEntities(entities.Purchase::fromHashMap, cdb.executeSelectQuery(config.database.queries.Purchase.MySQL_QUERY_GET_NEW_TODAY_PURCHASES, whereVal));
		ArrayList<RateRequest> rateRequest = AbstractJsonToString.hashMapToArrayListEntities(entities.RateRequest::fromHashMap, cdb.executeSelectQuery(config.database.queries.RateRequest.MySQL_QUERY_GET_NEW_TODAY_RATE_REQUESTS, whereVal));
		ArrayList<VersionRequest> versionRequest = AbstractJsonToString.hashMapToArrayListEntities(entities.VersionRequest::fromHashMap, cdb.executeSelectQuery(config.database.queries.VersionRequest.MySQL_QUERY_GET_NEW_TODAY_VERSION_REQUESTS, whereVal));
	
		entities.DailyReport dr = new entities.DailyReport(whereVal.get(0));
		dr.setUser(user);
		dr.setCity(city);
		dr.setActivity(activity);
		dr.setMessage(message);
		dr.setPurchase(purchase);
		dr.setRateRequest(rateRequest);
		dr.setVersionRequest(versionRequest);
		whereVal.add(dr.toString());
		return cdb.executeQuery(config.database.queries.DailyReport.MySQL_QUERY_ADD_RATE_REQUEST, whereVal);
	}
	
	/**
	 * Send message subscription reminder.
	 *
	 * @param cdb the cdb
	 * @return true, if successful
	 * @throws SQLException the SQL exception
	 */
	public static boolean sendMessageSubscriptionReminder(ClientDB cdb) throws SQLException {
		ArrayList<Purchase> purchases = AbstractJsonToString.hashMapToArrayListEntities(entities.Purchase::fromHashMap, cdb.executeSelectQuery(config.database.queries.Purchase.MySQL_QUERY_GET_PURCHASES_SUBSCRIPTION_EXPIRES_IN_X_DAYS));
		if(purchases.isEmpty())
			return false;
		
		for(Purchase purchase : purchases) {
			ArrayList<String> whereVal = new ArrayList<String>();
			whereVal.add(purchase.getUser().getUsername());
			whereVal.add(purchase.getUser().getUsername());
			whereVal.add(String.format("Your subscription of %s will expire soon", purchase.getCity().getName()));
			whereVal.add(String.format("Hello %s,\r\n" 
					+ "We see that your subscription of %s will expire in %s days,\r\n" + 
					"In order to enjoy our service, we want to offer you to renew the subscription" + 
					((purchase.getRenewal() == 0) ? ", with special discount of 10 present!" : ",")
					+ "\r\n"
					+ "As followed: price %s for %s days.\r\n" 
					+ "To renew subscription you need enter to Profile page.\r\n" +
					"We hope you enojoy our services,\r\n",
					purchase.getUser().getUsername(),
					purchase.getCity().getName(),
					config.GCM.SERVER_SUBSCRIPTION_REMINDER_DAYS,
					((purchase.getRenewal() == 0) ? purchase.getPurchasePrice().getPrice() * 0.9 : purchase.getPurchasePrice().getPrice()),
					purchase.getPurchasePrice().getDays()));
			
			cdb.executeQuery(config.database.queries.Message.MySQL_QUERY_ADD_NOTIFICATION, whereVal);
		}	
		return true;
	}
	
}
