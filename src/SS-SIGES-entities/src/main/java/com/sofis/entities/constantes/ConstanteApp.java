package com.sofis.entities.constantes;

import com.sofis.generico.utils.generalutils.StringsUtils;

/**
 * Constantes genericas de la aplicación.
 *
 * @author Usuario
 */
public class ConstanteApp {

    public static final String APP_VERSION = "4.4";
    public static final String APP_BUILD = "13";

    public static final String APP_NAME = "SIGES";

    public static final String PERSISTENCE_CONTEXT_UNIT_NAME = "SS-SIGES-PU";
    public static final String PERSISTENCE_CONTEXT_UNIT_NAME_CACHE = "SS-SIGES-PU-CACHE";
    public static final String PERSISTENCE_CONTEXT_UNIT_NAME_NONE_JTA = "JPAService";
    
    

    public static final String LOGGER_NAME = "siges";
    public static final String SIGES_WEB = "SIGES_WEB";
    public static final String JNDI_MAIL = "java:jboss/mail/SigesMail";
    
    public static final String DB_MYSQL = "mysql";
    public static final String DB_POSTGRESQL = "postgresql";

    public static boolean isVersion(int verMayor, int verMenor, int build) {
        if (!StringsUtils.isEmpty(APP_VERSION) && !StringsUtils.isEmpty(APP_BUILD)) {
            String[] version = APP_VERSION.split("\\.");
            int iMayor = Integer.valueOf(version[0]);
            int iMenor = Integer.valueOf(version[1]);
            int iBuild = Integer.valueOf(APP_BUILD);

            return verMayor == iMayor && verMenor == iMenor && build == iBuild;
        }
        return false;
    }

    public static boolean isVersionMenor(int verMayor, int verMenor, int build) {
        if (!StringsUtils.isEmpty(APP_VERSION) && !StringsUtils.isEmpty(APP_BUILD)) {
            String[] version = APP_VERSION.split("\\.");
            int iMayor = Integer.valueOf(version[0]);
            int iMenor = Integer.valueOf(version[1]);
            int iBuild = Integer.valueOf(APP_BUILD);

            return verMayor < iMayor
                    || (verMayor == iMayor && verMenor < iMenor)
                    || (verMenor == iMenor && build < iBuild);
        }
        return false;
    }
}
