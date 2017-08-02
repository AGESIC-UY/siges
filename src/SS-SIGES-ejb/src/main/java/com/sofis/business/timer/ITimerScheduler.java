package com.sofis.business.timer;

import javax.ejb.Remote;

/**
 *
 * @author Usuario
 */
@Remote
public interface ITimerScheduler {
    
    /** starts the timer */ 
    public void startUpTimer(); 


    /** stops all the timers */ 
    public void shutDownTimer();  
}
