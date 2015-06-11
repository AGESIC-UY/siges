package com.sofis.web.listener;

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
        System.out.println("sessionCreated - add one session into counter(" + totalActiveSessions + ")");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent arg0) {
        totalActiveSessions--;
        System.out.println("sessionDestroyed - deduct one session from counter (" + totalActiveSessions + ")");
    }
}
