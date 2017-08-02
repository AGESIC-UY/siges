package pruebas;
import java.lang.invoke.MethodHandles;

/**
 *
 * @author Usuario
 */
public class Pruebas {

    public static boolean validarRUT(String rut) {
        boolean respuesta = true;
        if (rut != null) {
            rut = rut.trim();
            {
                if (rut.length() == 11) {
                    rut = "0" + rut;
                }
                if (rut.length() == 12 || rut.length() == 11) {
                    Integer ultDig = Integer.parseInt(rut.charAt(rut.length() - 1) + "");

                    int[] coefs = {2, 3, 4, 5, 6, 7, 2, 3, 4, 5, 6, 7};
                    int suma = 0;
                    for (int i = 0; i < rut.length(); i++) {
                        Integer elem = Integer.parseInt(rut.charAt(i) + "");
                        int val = coefs[i];
                        suma += (val * elem);
                    }
                    //System.out.println("suma = "+suma);
                    int modulo = suma % 11;
                    int checkDigit = 11 - modulo;
                    if (checkDigit == 11) {
                        checkDigit = 1;
                    }

                    //System.out.println("checkdigit = "+checkDigit);
                    respuesta = checkDigit == ultDig.intValue();
                }
            }
        } else {
            respuesta = false;
        }
        return respuesta;
    }

    public static void main(String[] args) {
//        //System.out.println("verificar: "+Pruebas.validarRUT("215119370019"));
//        Date d1 = new Date(1425869999999L);
//        System.out.println("d1: " + d1);
//
//        Date d2 = new Date(1425826800000L);
//        System.out.println("d2: " + d2);
//
//        Calendar calIni = new GregorianCalendar(2015, 1, 1, 0, 0);
//        Calendar calFin = new GregorianCalendar(2015, 11, 31, 0, 0);
//        Long timeIni = calIni.getTimeInMillis();
//        Long timeFin = calFin.getTimeInMillis();
//
//        System.out.println("timeIni:" + timeIni);
//        System.out.println("timeFin:" + timeFin);
//        System.out.println("timeIni.toS:" + timeIni.toString());
//        System.out.println("timeFin.toS:" + timeFin.toString());
//
//        Date d3 = new Date();
//        System.out.println("d3:" + d3.toString());
//        Date d4 = new Date(timeIni);
//        System.out.println("d4:" + d4.toString());
//        Date d5 = new Date(timeFin);
//        System.out.println("d4:" + d5.toString());
//
//        Calendar calEnt = new GregorianCalendar();
//        calEnt.set(Calendar.YEAR, 2015);
//        calEnt.set(Calendar.MONTH, 5);
//        calEnt.set(Calendar.DATE, 01);
//        calEnt.set(Calendar.HOUR_OF_DAY, 00);
//        calEnt.set(Calendar.MINUTE, 00);
//        calEnt.set(Calendar.MILLISECOND, 00);
//        System.out.println("calEnt:" + calEnt.get(Calendar.YEAR) + "/" + (calEnt.get(Calendar.MONTH) + 1) + "/" + calEnt.get(Calendar.DATE) + "-" + calEnt.get(Calendar.HOUR_OF_DAY) + ":" + calEnt.get(Calendar.MINUTE));
//
//        Calendar calEntFin = new GregorianCalendar();
//        calEntFin.set(Calendar.YEAR, 2016);
//        calEntFin.set(Calendar.MONTH, 5);
//        calEntFin.set(Calendar.DATE, 01);
//        calEntFin.set(Calendar.HOUR_OF_DAY, 00);
//        calEntFin.set(Calendar.MINUTE, 00);
//        calEntFin.set(Calendar.MILLISECOND, 00);
//        System.out.println("calEntFin:" + calEntFin.get(Calendar.YEAR) + "/" + (calEntFin.get(Calendar.MONTH) + 1) + "/" + calEntFin.get(Calendar.DATE) + "-" + calEntFin.get(Calendar.HOUR_OF_DAY) + ":" + calEntFin.get(Calendar.MINUTE));
//
//        System.out.println("Diff:" + ((calEntFin.getTimeInMillis() - calEnt.getTimeInMillis()) / 86400000));
//
//        Date d6 = new Date(1447124399999L);
//        Calendar calEnt6 = new GregorianCalendar();
//        calEnt6.setTime(d6);
//        System.out.println("calEnt6:" + calEnt6.get(Calendar.YEAR) + "/" + (calEnt6.get(Calendar.MONTH) + 1) + "/" + calEnt6.get(Calendar.DATE) + "-" + calEnt6.get(Calendar.HOUR_OF_DAY) + ":" + calEnt6.get(Calendar.MINUTE) + ":" + calEnt6.get(Calendar.MILLISECOND));
//
//        Calendar calEnt7 = new GregorianCalendar();
//        calEnt7.set(Calendar.YEAR, 2015);
//        calEnt7.set(Calendar.MONTH, 10);
//        calEnt7.set(Calendar.DATE, 15);
//        calEnt7.set(Calendar.HOUR_OF_DAY, 0);
//        calEnt7.set(Calendar.MINUTE, 59);
//        calEnt7.set(Calendar.MILLISECOND, 999);
//        System.out.println("calEnt7:" + calEnt7.get(Calendar.YEAR) + "/" + (calEnt7.get(Calendar.MONTH) + 1) + "/" + calEnt7.get(Calendar.DATE) + "-" + calEnt7.get(Calendar.HOUR_OF_DAY) + ":" + calEnt7.get(Calendar.MINUTE));
//        System.out.println("calEnt7 inMills:" + calEnt7.getTimeInMillis());
//
//        System.out.println("uuid:" + UUID.randomUUID().toString());
//
//        Calendar calnow = new GregorianCalendar();
//        calnow.add(Calendar.HOUR_OF_DAY, -24);
//        System.out.println("calnow:" + calnow.get(Calendar.YEAR) + "/" + (calnow.get(Calendar.MONTH) + 1) + "/" + calnow.get(Calendar.DATE) + "-" + calnow.get(Calendar.HOUR_OF_DAY) + ":" + calnow.get(Calendar.MINUTE));

        double mod;
        int azulInt;
        int rojoInt;
        double step = 0.1;
        double azul;
        double rojo;
        for (int azulE = 0; azulE <= 1000; ++azulE) {
            for (int rojoE = 0; rojoE <= 1000; ++rojoE) {
                if ( azulE + rojoE == 0) continue;
                azul = azulE * 100 / (azulE + rojoE);
                rojo = rojoE * 100 / (azulE + rojoE);
                mod = azul % 1;
                if (mod >= 0.5) {
                    azulInt = (int) Math.abs(Math.ceil(azul));
                    rojoInt = (int) Math.abs(Math.floor(rojo));
                } else {
                    azulInt = (int) Math.abs(Math.floor(azul));
                    rojoInt = (int) Math.abs(Math.ceil(rojo));
                }
                if(azulInt + rojoInt > 100){
                    System.out.println("MAYOR : ("+ azul +", "+ azulInt +"), ("+ rojo +", "+ rojoInt +"), "+ mod);
                    return;
                }
            }
        }

    }
}
