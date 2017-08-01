package com.sofis.web.seguridad;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 *
 * @author Usuario
 */
public class SessionCounterListener implements HttpSessionListener {

    private static int totalActiveSessions;

    public static int getTotalActiveSession() {
        return totalActiveSessions;
    }

    @Override
    public void sessionCreated(HttpSessionEvent arg0) {
        totalActiveSessions++;
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent arg0) {
        totalActiveSessions--;
    }
}
