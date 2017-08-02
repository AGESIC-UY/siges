/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sofis.sofisform.to;

import java.io.Serializable;

/**
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
public class PaginatorTO implements Serializable{

    private boolean paginator=false;
    private boolean databasePaginator= false;
    private int fastStep=1;
    private int paginatorMaxPages = 5;
    private int rows = 5;
    String fastRewindUrl;
    String fastRewindTitle;
    String fastRewindStyle;

    String nextUrl;
    String nextTitle;
    String nextStyle;

    String previousUrl;
    String previousTitle;
    String previousStyle;

    String lastUrl;
    String lastTitle;
    String lastStyle;

    String firstUrl;
    String firstTitle;
    String firstStyle;

    String fastForwardUrl;
    String fastForwardTitle;
    String fastForwardStyle;

    public String getPreviousStyle() {
        return previousStyle;
    }

    public void setPreviousStyle(String previousStyle) {
        this.previousStyle = previousStyle;
    }

    public String getPreviousTitle() {
        return previousTitle;
    }

    public void setPreviousTitle(String previousTitle) {
        this.previousTitle = previousTitle;
    }

    public String getPreviousUrl() {
        return previousUrl;
    }

    public void setPreviousUrl(String previousUrl) {
        this.previousUrl = previousUrl;
    }

    

    public String getFastForwardStyle() {
        return fastForwardStyle;
    }

    public void setFastForwardStyle(String fastForwardStyle) {
        this.fastForwardStyle = fastForwardStyle;
    }

    public String getFastForwardTitle() {
        return fastForwardTitle;
    }

    public void setFastForwardTitle(String fastForwardTitle) {
        this.fastForwardTitle = fastForwardTitle;
    }

    public String getFastForwardUrl() {
        return fastForwardUrl;
    }

    public void setFastForwardUrl(String fastForwardUrl) {
        this.fastForwardUrl = fastForwardUrl;
    }

    public String getFastRewindStyle() {
        return fastRewindStyle;
    }

    public void setFastRewindStyle(String fastRewindStyle) {
        this.fastRewindStyle = fastRewindStyle;
    }

    public String getFastRewindTitle() {
        return fastRewindTitle;
    }

    public void setFastRewindTitle(String fastRewindTitle) {
        this.fastRewindTitle = fastRewindTitle;
    }

    public String getFastRewindUrl() {
        return fastRewindUrl;
    }

    public void setFastRewindUrl(String fastRewindUrl) {
        this.fastRewindUrl = fastRewindUrl;
    }

    public String getFirstStyle() {
        return firstStyle;
    }

    public void setFirstStyle(String firstStyle) {
        this.firstStyle = firstStyle;
    }

    public String getFirstTitle() {
        return firstTitle;
    }

    public void setFirstTitle(String firstTitle) {
        this.firstTitle = firstTitle;
    }

    public String getFirstUrl() {
        return firstUrl;
    }

    public void setFirstUrl(String firstUrl) {
        this.firstUrl = firstUrl;
    }

    public String getLastStyle() {
        return lastStyle;
    }

    public void setLastStyle(String lastStyle) {
        this.lastStyle = lastStyle;
    }

    public String getLastTitle() {
        return lastTitle;
    }

    public void setLastTitle(String lastTitle) {
        this.lastTitle = lastTitle;
    }

    public String getLastUrl() {
        return lastUrl;
    }

    public void setLastUrl(String lastUrl) {
        this.lastUrl = lastUrl;
    }

    public String getNextStyle() {
        return nextStyle;
    }

    public void setNextStyle(String nextStyle) {
        this.nextStyle = nextStyle;
    }

    public String getNextTitle() {
        return nextTitle;
    }

    public void setNextTitle(String nextTitle) {
        this.nextTitle = nextTitle;
    }

    public String getNextUrl() {
        return nextUrl;
    }

    public void setNextUrl(String nextUrl) {
        this.nextUrl = nextUrl;
    }

    public int getFastStep() {
        return fastStep;
    }

    public void setFastStep(int fastStep) {
        this.fastStep = fastStep;
    }

    public boolean isPaginator() {
        return paginator;
    }

    public void setPaginator(boolean paginator) {
        this.paginator = paginator;
    }

    public int getPaginatorMaxPages() {
        return paginatorMaxPages;
    }

    public void setPaginatorMaxPages(int paginatorMaxPages) {
        this.paginatorMaxPages = paginatorMaxPages;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public boolean isDatabasePaginator() {
        return databasePaginator;
    }

    public void setDatabasePaginator(boolean databasePaginator) {
        this.databasePaginator = databasePaginator;
    }

}
