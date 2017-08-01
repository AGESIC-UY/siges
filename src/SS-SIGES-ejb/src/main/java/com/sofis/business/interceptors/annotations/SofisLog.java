package com.sofis.business.interceptors.annotations;

import static java.lang.annotation.ElementType.TYPE;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;

/**
 *
 * @author Usuario
 */

@Retention(RUNTIME)
@Target({TYPE})
public @interface SofisLog {

}
