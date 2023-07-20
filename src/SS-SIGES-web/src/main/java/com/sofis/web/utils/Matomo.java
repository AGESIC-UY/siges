package com.sofis.web.utils;

import com.icesoft.faces.context.effects.JavascriptContext;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

public class Matomo {

	private static final String EXECUTION_ARRAY = "_paq";
	private static final String SET_USER_ID = "setUserId";
	private static final String TRACK_LINK = "trackLink";
	private static final String DOWNLOAD = "download";
	private static final String RESET_USER_ID = "resetUserId";
	private static final String TRACK_PAGE_VIEW = "trackPageView";
	private static final String SET_CUSTOM_DIMENSION = "setCustomDimension";
	private static final String ENABLE_LINK_TRACKING = "enableLinkTracking";
	private static final String SET_TRACKER_URL = "setTrackerUrl";
	private static final String MATOMO_PHP = "matomo.php";
	private static final String SET_SITE_ID = "setSiteId";
	private static final String APPEND_TO_TRACKING_URL = "appendToTrackingUrl";
	private static final String NEW_VISIT_1 = "new_visit=1";

	public static String getInit() {

		return "var " + EXECUTION_ARRAY + " = window." + EXECUTION_ARRAY + " || [];";
	}
	
	public static String getSetUserID(String userId) {

		return push(SET_USER_ID, userId);
	}
	
	public static void callResetUserID() {

		javasriptCall(push(RESET_USER_ID));
	}
	
	public static String getSetCustomDimension(Integer id, String value) {

		return push(SET_CUSTOM_DIMENSION, id.toString(), value);
	}

	public static String getTrackPageView() {

		return push(TRACK_PAGE_VIEW);
	}

	public static String getEnableLinkTracking() {

		return push(ENABLE_LINK_TRACKING);
	}

	public static String getSetTrackerUrl(String url) {

		return push(SET_TRACKER_URL, url + MATOMO_PHP);
	}

	public static String getSetSiteId(Integer siteId) {

		return push(SET_SITE_ID, siteId.toString());
	}

	public static String getTrackDownload(String fileName) {

		return push(TRACK_LINK, getCurrentURL() + fileName, DOWNLOAD);
	}

	public static String getResetUserID() {

		return push(APPEND_TO_TRACKING_URL, NEW_VISIT_1);
	}

	public static String getForceNewVisit() {
	
		return push();
	}
	
	public static void callInit() {

		javasriptCall(getInit());
	}

	public static void callSetUserID(String userId) {

		javasriptCall(getSetUserID(userId));
	}

	public static void callSetCustomDimension(Integer id, String value) {

		javasriptCall(getSetCustomDimension(id, value));
	}

	public static void callTrackPageView() {

		javasriptCall(getTrackPageView());
	}

	public static void callEnableLinkTracking() {

		javasriptCall(getEnableLinkTracking());
	}

	public static void callSetTrackerUrl(String url) {

		javasriptCall(getSetTrackerUrl(url));
	}

	public static void callSetSiteId(Integer siteId) {

		javasriptCall(getSetSiteId(siteId));
	}

	public static void callTrackDownload(String fileName) {

		javasriptCall(getTrackDownload(fileName));
	}

	public static void callForceNewVisit() {

		javasriptCall(getResetUserID());
	}

	private static void javasriptCall(String jsCode) {

		JavascriptContext.addJavascriptCall(FacesContext.getCurrentInstance(), jsCode);
	}

	private static String push(String... arguments) {

		StringBuilder array = new StringBuilder("['");

		for (int i = 0; i < arguments.length; i++) {

			array.append(arguments[i]);

			if (i < arguments.length - 1) {
				array.append("', '");
			}
		}

		array.append("']");

		return EXECUTION_ARRAY + ".push(" + array + ");";
	}

	private static String getCurrentURL() {
		try {
			HttpServletRequest request = (HttpServletRequest) FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.getRequest();

			String file = request.getRequestURI();

			//if (request.getQueryString() != null) {
			//	file += '?' + request.getQueryString();
			//}

			URL reconstructedURL = new URL(request.getScheme(),
					request.getServerName(),
					request.getServerPort(),
					file);

			return reconstructedURL.toString() + "/";

		} catch (MalformedURLException ex) {
			Logger.getLogger(Matomo.class.getName()).log(Level.WARNING, "Error al obtener URL", ex);
		}

		return "localhost/";
	}
}
