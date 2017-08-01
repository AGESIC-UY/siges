/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sofis.web.utils;

import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
public class CookieHelper {

    public void setCookie(String name, int expiry) {

    }

    public void setCookie(String name, String value, Integer expiry) {

        FacesContext facesContext = FacesContext.getCurrentInstance();

        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        Cookie cookie = null;

        Cookie[] userCookies = request.getCookies();
        if (userCookies != null && userCookies.length > 0) {
            for (Cookie userCookie : userCookies) {
                if (userCookie.getName().equals(name)) {
                    cookie = userCookie;
                    break;
                }
            }
        }

        if (cookie != null) {
            cookie.setValue(value);
        } else {
            cookie = new Cookie(name, value);
            cookie.setPath(request.getContextPath());
        }

        cookie.setMaxAge(expiry);

        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
        response.addCookie(cookie);
    }

    public Cookie getCookie(String name) {

        FacesContext facesContext = FacesContext.getCurrentInstance();

        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        Cookie cookie = null;

        Cookie[] userCookies = request.getCookies();
        if (userCookies != null && userCookies.length > 0) {
            for (int i = 0; i < userCookies.length; i++) {
                if (userCookies[i].getName().equals(name)) {
                    cookie = userCookies[i];
                    return cookie;
                }
            }
        }
        return null;
    }

    public void delete(String name) {
        FacesContext facesContext = FacesContext.getCurrentInstance();

        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        Cookie cookie = null;

        Cookie[] userCookies = request.getCookies();
        if (userCookies != null && userCookies.length > 0) {
            for (Cookie userCookie : userCookies) {
                if (userCookie.getName().equals(name)) {
                    cookie = userCookie;
                    break;
                }
            }
        }

        if (cookie != null) {
            cookie.setMaxAge(0);
            HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
            response.addCookie(cookie);
        }
    }

    public void deleteAll() {
        FacesContext facesContext = FacesContext.getCurrentInstance();

        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
        Cookie[] userCookies = request.getCookies();
        if (userCookies != null && userCookies.length > 0) {
            for (Cookie userCookie : userCookies) {
                userCookie.setMaxAge(0);
                response.addCookie(userCookie);
            }
        }
    }

}
