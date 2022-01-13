/*
 * this class defines errors for Requests actions
 */
package config.packetTransfer.server.errors;

// TODO: Auto-generated Javadoc
/**
 * The Class Requests.
 */
public class Requests {
	
	/** The Constant WINDOW_RateRequest. */
	//public static final String WINDOW = "Requests";

	/* ======================== GET ============================= */
	/** The Constant SUB_ACTION_GET_ALL_REQUESTS. */
	public static final String SUB_ACTION_ERROR_GET_ALL_REQUESTS = "Get all requests error";
	
	/** The Constant SUB_ACTION_GET_ALL_RATE_REQUESTS. */
	//public static final String SUB_ACTION_ERROR_GET_ALL_RATE_REQUESTS = "GET_ALL_RATE_REQUESTS";
	
	/** The Constant SUB_ACTION_ERROR_GET_USER_REQUEST_RESPONSE. */
	public static final String SUB_ACTION_ERROR_GET_USER_REQUEST_RESPONSE = "Get request response error";
	
	
	/** The Constant SUB_ACTION_GET_ALL_RATE_REQUESTS. */
	//public static final String SUB_ACTION_ERROR_GET_USER_RATE_REQUEST_RESPONSE = "GET_USER_RATE_REQUESTS";
	
	/** The Constant SUB_ACTION_GET_ALL_VERSION_REQUESTS. */
	//public static final String SUB_ACTION_ERROR_GET_ALL_VERSION_REQUESTS = "GET_ALL_VERSION_REQUESTS";
	
	/** The Constant SUB_ACTION_GET_ALL_VERSION_REQUESTS. */
	//public static final String SUB_ACTION_ERROR_GET_USER_VERSION_REQUEST_RESPONSE = "GET_USER_VERSION_REQUEST_RESPONSE";
	
	
	/* ======================== ADD ============================= */
	/** The ConstantSUB_ACTION_ADD_RATE. */
	public static final String SUB_ACTION_ERROR_ADD_RATE = "Rate already exists";
	
	/** The ConstantSUB_ACTION_ADD_RATE. */
	public static final String SUB_ACTION_ERROR_ADD_VERSION = "Version already exists";
	
	
	/** The Constant SUB_ACTION_ADD_REQUEST. */
	//public static final String SUB_ACTION_ERROR_ADD_REQUEST = "Add request error";
	
	/** The Constant SUB_ACTION_ADD_RATE_REQUEST. */
	public static final String SUB_ACTION_ERROR_ADD_RATE_REQUEST = "another similar request was sent";
	
	/** The Constant SUB_ACTION_ADD_VERSION_REQUEST. */
	public static final String SUB_ACTION_ERROR_ADD_VERSION_REQUEST = "there is another request waits for approve";
	
	/** The Constant SUB_ACTION_ADD_ONETIME_RATE_REQUEST. */
	//public static final String SUB_ACTION_ERROR_ADD_ONETIME_RATE_REQUEST = "Add onetime rate request error";
	
	/** The Constant SUB_ACTION_ADD_SUBSCRIPTION_RATE_REQUEST. */
//	public static final String SUB_ACTION_ERROR_ADD_SUBSCRIPTION_RATE_REQUEST = "Add subscription rate request error";
	
	/* ======================== UPDATE ============================= */
	/** The Constant SUB_ACTION_UPDATE_REQUEST. */
	public static final String SUB_ACTION_ERROR_UPDATE_REQUEST = "This request has been updated";

	
	/** The Constant SUB_ACTION_UPDATE_RATE_REQUEST. */
	//public static final String SUB_ACTION_ERROR_UPDATE_APPROVAL_RATE_REQUEST = "Update rate request error";

	/** The Constant SUB_ACTION_UPDATE_APPROVAL_VERSION_REQUEST. */
	//public static final String SUB_ACTION_ERROR_UPDATE_APPROVAL_VERSION_REQUEST = "Update version request error";
	
	/* ======================== DELETE ============================= */
	
	/** The Constant SUB_ACTION_DELETE_REQUEST. */
	public static final String SUB_ACTION_ERROR_DELETE_REQUEST = "request alreday deleted";
	
	
	/** The Constant SUB_ACTION_DELETE_RATE_REQUEST. */
	//public static final String SUB_ACTION_ERROR_DELETE_RATE_REQUEST = "Delets rate request error";
	
	/** The Constant SUB_ACTION_DELETE_VERSION_REQUEST. */
	//public static final String SUB_ACTION_ERROR_DELETE_VERSION_REQUEST = "Delets version request error";


	/**
	 * Override public contractor to make it static.
	 */
	private Requests() {}

}
