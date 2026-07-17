/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sofis.web.filtros;

import com.sofis.business.ejbs.ConfiguracionBean;
import com.sofis.business.ejbs.SsUsuarioBean;
import com.sofis.entities.data.Configuracion;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.tipos.TokenSegundoFactor;
import com.sofis.web.seguridad.SigesPrincipal;
import com.sofis.web.utils.EJBUtils;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.Principal;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author bruno
 */
public class SegundoFactorFilter implements Filter {

    private static final boolean debug = true;
    private static Logger logger = Logger.getLogger(SegundoFactorFilter.class.getName());
    @Inject
    private ConfiguracionBean configuracionBean;
    @Inject
    private SsUsuarioBean usuBean;
    
    private Integer registerSsUser = 0;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured.
    private FilterConfig filterConfig = null;

    public SegundoFactorFilter() {
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (httpRequest.getDispatcherType() != javax.servlet.DispatcherType.REQUEST) {
            chain.doFilter(request, response);
            return;
        }

        String uri = httpRequest.getRequestURI();

        if (uri.contains("/javax.faces.resource/")
                || uri.endsWith(".css")
                || uri.endsWith(".js")
                || uri.endsWith(".png")
                || uri.endsWith(".jpg")
                || uri.endsWith(".gif")
                || uri.endsWith(".ico")) {

            chain.doFilter(request, response);
            return;
        }

        // 2️⃣ Dejar pasar la página de segundo factor
        if (uri.endsWith("segundoFactor.jsf")) {
            chain.doFilter(request, response);
            return;
        }

        // 3️⃣ Usuario autenticado
        Principal principal = httpRequest.getUserPrincipal();

        if (principal instanceof SigesPrincipal) {

            SigesPrincipal sigesPrincipal = (SigesPrincipal) principal;
            TokenSegundoFactor token = sigesPrincipal.getToken();

            // 4️⃣ Falta validar segundo factor → redirigir
            if (token != null && token.getFechaConfirmado() == null) {

                Configuracion confCod = configuracionBean.obtenerCnfPorCodigoYOrg("DOBLE_FACTOR_ACTIVO", null);
                if (confCod.getCnfValor().equals("true")) {

                    httpResponse.sendRedirect(
                            httpRequest.getContextPath() + "/paginasPrivadas/segundoFactor.jsf"
                    );
                    return;
                } else {
                    chain.doFilter(request, response);
                    return;
                }

            }
        }
        
        // 5️⃣ Continuar normalmente
        chain.doFilter(request, response);
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("JAASFixFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("SegundoFactorFilter()");
        }
        StringBuffer sb = new StringBuffer("SegundoFactorFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
