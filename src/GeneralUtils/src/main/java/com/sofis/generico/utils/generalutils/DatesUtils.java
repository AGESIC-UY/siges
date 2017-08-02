package com.sofis.generico.utils.generalutils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 *
 * @author Usuario
 */
public class DatesUtils {

    public static final String CALENDAR_PATTERN = "dd/MM/yyyy";

    public static boolean sonStringIguales(Date s1, Date s2) {
        if (s1 != null) {
            if (s2 != null) {
                return s1.equals(s2);
            } else {
                return false;
            }
        } else {
            return s2 == null;
        }
    }

    /**
     * Compara si dos fechas son iguales tomando en cuenta el año, mes y día.
     *
     * @param d1
     * @param d2
     * @return true si son iguales.
     */
    public static boolean fechasIguales(Date d1, Date d2) {
        if (d1 != null && d2 != null) {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
            return fmt.format(d1).equals(fmt.format(d2));
        }
        return false;
    }

    /**
     * Retorna true si la primer fecha es mayor a la segunda. Solo compara la
     * fecha y no las horas.
     *
     * @param d1
     * @param d2
     * @return boolean
     */
    public static boolean esMayor(Date d1, Date d2) {
        if (d1 != null && d2 != null) {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
            Integer d1Int = Integer.parseInt(fmt.format(d1));
            Integer d2Int = Integer.parseInt(fmt.format(d2));
            return d1Int > d2Int;
        }
        return false;
    }

    /**
     * Retorna true si la primer fecha es mayor a la segunda. Solo compara la
     * fecha y no las horas.
     *
     * @param d1
     * @param d2
     * @return boolean
     */
    public static boolean esMayor(Date d1, Date d2, boolean hours) {
        if (d1 != null && d2 != null && !hours) {
            return esMayor(d1, d2);
        } else if (d1 != null && d2 != null) {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmssSS");
            Long d1Int = Long.parseLong(fmt.format(d1));
            Long d2Int = Long.parseLong(fmt.format(d2));
            return d1Int.longValue() > d2Int.longValue();
        }
        return false;

    }

    /**
     * Retorna true si d0 está comprendida entre d1 y d2.
     *
     * @param d0
     * @param d1
     * @param d2
     * @param pattern
     * @return boolean
     */
    public static boolean esEntreFechas(Date d0, Date d1, Date d2, String pattern) {
        if (d0 != null && d1 != null && d2 != null) {
            SimpleDateFormat fmt = new SimpleDateFormat(pattern);
            Integer d0Int = Integer.parseInt(fmt.format(d0));
            Integer d1Int = Integer.parseInt(fmt.format(d1));
            Integer d2Int = Integer.parseInt(fmt.format(d2));
            return d0Int >= d1Int && d0Int <= d2Int;
        }
        return false;
    }

    /**
     * Se resta una hora para los casos en que se guardó con GMT-2.
     *
     * @param datetime
     * @return Long datetime
     */
    public static Long corregirGMT(Long datetime) {
        if (datetime != null) {
            Calendar cal = new GregorianCalendar();
            cal.setTimeInMillis(datetime);
            //Resto una hora para los casos que se guardó con GMT-2.
            if (cal.get(Calendar.HOUR_OF_DAY) == 0 && cal.get(Calendar.MINUTE) == 59) {
                cal.add(Calendar.HOUR_OF_DAY, -1);
                datetime = cal.getTimeInMillis();
            }
        }
        return datetime;
    }

    public static String toStringFormat(Date d, String pattern) {
        if (d != null) {
            if (StringsUtils.isEmpty(pattern)) {
                pattern = CALENDAR_PATTERN;
            }

            SimpleDateFormat sdf = new SimpleDateFormat(pattern);

            Calendar cal = new GregorianCalendar();
            cal.setTime(d);
            //Resto una hora para los casos que se guardó con GMT-2.
            if (cal.get(Calendar.HOUR_OF_DAY) == 0 && cal.get(Calendar.MINUTE) == 59) {
                cal.add(Calendar.HOUR_OF_DAY, -1);
                d = cal.getTime();
            }

            return sdf.format(d);
        }
        return "";
    }

    public static Integer diasEntreFechas(Date d1, Date d2) {
        if (d1 != null && d2 != null) {

            Calendar c1 = new GregorianCalendar();
            c1.setTime(d1);
            c1.set(Calendar.HOUR_OF_DAY, 12);
            c1.set(Calendar.MINUTE, 0);
            c1.set(Calendar.SECOND, 0);
            c1.set(Calendar.MILLISECOND, 0);

            Calendar c2 = new GregorianCalendar();
            c2.setTime(d2);
            c2.set(Calendar.HOUR_OF_DAY, 12);
            c2.set(Calendar.MINUTE, 0);
            c2.set(Calendar.SECOND, 0);
            c2.set(Calendar.MILLISECOND, 0);

            int diff = 0;
            boolean d2EsAnterior = false;
            if (c2.before(c1)) {
                Calendar calAux = c1;
                c1 = c2;
                c2 = calAux;
                d2EsAnterior = true;
            }

            while (c1.before(c2)) {
                diff++;
                c1.add(Calendar.DAY_OF_MONTH, 1);
            }
//            return diff;
            return d2EsAnterior ? diff * (-1) : diff;
        }
        return null;
    }

    public static Date incrementarDias(Date d, int dias) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(d);
        return incrementarDias(cal, dias).getTime();
    }

    public static Calendar incrementarDias(Calendar cal, int dias) {
        cal.add(Calendar.DAY_OF_YEAR, dias);
        return cal;
    }

    /**
     * Retorna true si el año aportado pertenece al rango de fechas.
     *
     * @param inicio
     * @param fin
     * @param anio
     * @return boolean
     */
    public static boolean isAnioEntreFechas(Date inicio, Date fin, int anio) {
        if (inicio != null && fin != null) {
            Calendar calIni = new GregorianCalendar();
            calIni.setTime(inicio);
            Calendar calFin = new GregorianCalendar();
            calFin.setTime(fin);
            if (calIni.get(Calendar.YEAR) <= anio
                    && calFin.get(Calendar.YEAR) >= anio) {
                return true;
            }
        }
        return false;
    }

    public static String sumarHora(String hora1, String hora2) {
        String pattern = "[0-9]*:[0-9]*";
        if (hora1 == null || hora1.trim().isEmpty()) {
            hora1 = "0:00";
        }
        if (!hora2.matches(pattern)) {
            try {
                Integer h2 = Integer.valueOf(hora2);
                hora2 = h2.toString() + ":00";
            } catch (NumberFormatException numberFormatException) {
            }
        }

        if (!StringsUtils.isEmpty(hora2) && hora2.matches(pattern)) {
            String[] hora1Split = hora1.split(":");
            String[] hora2Split = hora2.split(":");
            int h1 = Integer.valueOf(hora1Split[0]);
            int m1 = Integer.valueOf(hora1Split[1]);
            int h2 = Integer.valueOf(hora2Split[0]);
            int m2 = Integer.valueOf(hora2Split[1]);

            h1 += h2;
            m1 += m2;
            if (m1 > 59) {
                h1 += m2 / 60;
                m1 += Math.floor((m2 % 60) * 100);
            }

            return h1 + ":" + (m1 < 10 ? "0" + m1 : m1);
        }
        return hora1;
    }

    public static String toFormatoLargo(Date d) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd 'de' MMMMM 'de' yyyy", new Locale("es"));
        return sdf.format(d);
    }
}
