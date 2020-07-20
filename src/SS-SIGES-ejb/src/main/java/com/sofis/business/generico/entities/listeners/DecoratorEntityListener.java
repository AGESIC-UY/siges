//package com.sofis.business.generico.entities.listeners;
//
////import com.sofis.business.ejbs.DatosUsuario;
////import com.sofis.business.ejbs.DatosUsuarioRemote;
//import java.util.Hashtable;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.annotation.Resource;
//import javax.ejb.SessionContext;
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import javax.persistence.PrePersist;
//import javax.persistence.PreUpdate;
//
///**
// *
// * @author Usuario
// */
//public class DecoratorEntityListener {
//    
////    @Inject
////    private DatosUsuario datoUsuario;
//    
//    @PrePersist
//    @PreUpdate
//    public void anotar(Object entity) {
//        try {
////            final Hashtable jndiProperties = new Hashtable();
////            jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
////            final Context context = new InitialContext(jndiProperties);
////            final String appName = "SS-SIGES-ear-5.10.1";
////            final String moduleName = "SS-SIGES-ejb-5.10.1";
////            final String distinctName = "";
////            final String beanName="DatosUsuario";
////            final String viewClassFullName= DatosUsuarioRemote.class.getName();
////           DatosUsuarioRemote du=   (DatosUsuarioRemote) context.lookup("ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassFullName+ "?stateful");
//            
////            if (du!=null) {
//                //logger.info("ES DISTINTO DE NULL!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//                //logger.info("usuario = "+du.getCodigoUsuario()+" origen = "+du.getOrigen());
////            } else {
////                logger.info("ES NULO..............................................");
////            }
//        } catch (NamingException ex) {
//            Logger.getLogger(DecoratorEntityListener.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    
//}
