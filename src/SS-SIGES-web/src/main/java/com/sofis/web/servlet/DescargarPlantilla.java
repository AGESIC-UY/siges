package com.sofis.web.servlet;

import com.sofis.entities.constantes.ConstanteApp;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author usuario
 */
@WebServlet(name = "DescargarPlantilla", urlPatterns = {"/DescargarPlantilla"})
public class DescargarPlantilla extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletOutputStream out = response.getOutputStream();
        final String pdfFileType = "pdf";
        final String xlsFileType = "xls";
        final String jpgFileTipe = "jpg";
        try {

            String sesIdParam = request.getParameter("sesId");
            String sesId = request.getSession().getId();
            sesIdParam = sesIdParam.replace(' ', '+');
            sesId = sesId.replace(' ', '+');
            //Comprobar que el id de sesion sea el mismo
            if (!sesId.equals(sesIdParam)) {

                out.print("Acceso denegado");
                return;
            }

            HttpSession session = request.getSession();
            byte[] bytes = (byte[]) session.getAttribute("bytes");
            String fileName = (String) session.getAttribute("fileName");
            String[] split = fileName.split("\\.");
            String fileExtension = "";
            if (split != null && split.length > 0) {
                fileExtension = split[split.length - 1];
            }
            if (bytes == null || fileName == null) {
                bytes = "Error descargando el contenido: no existe.".getBytes();
                String nombre = "plantilla.txt";
                response.setContentType("text/plain");
                response.setContentLength((int) bytes.length);
                response.setHeader("Content-Disposition", "attachment; filename=\"" + nombre + "\"");
            } else {
                if (fileExtension.equalsIgnoreCase("pdf")) {
                    String nombre = fileName;
                    response.setContentType("application/pdf");
                    response.setContentLength((int) bytes.length);
                    response.setHeader("Content-Disposition", "attachment; filename=\"" + nombre + "\"");
                } else if (fileExtension.equalsIgnoreCase("xls")) {
                    String nombre = fileName;
                    response.setContentType("application/excel");
                    response.setContentLength((int) bytes.length);
                    response.setHeader("Content-Disposition", "attachment; filename=\"" + nombre + "\"");
                } else if (fileExtension.equalsIgnoreCase("jpg") || fileExtension.equalsIgnoreCase("jpeg") || fileExtension.equalsIgnoreCase("jpe")) {
                    String nombre = fileName;
                    response.setContentType("image/jpeg");
                    response.setContentLength((int) bytes.length);
                    response.setHeader("Content-Disposition", "attachment; filename=\"" + nombre + "\"");
                } else if (fileExtension.equalsIgnoreCase("tif") || fileExtension.equalsIgnoreCase("tiff")) {
                    String nombre = fileName;
                    response.setContentType("image/tiff");
                    response.setContentLength((int) bytes.length);
                    response.setHeader("Content-Disposition", "attachment; filename=\"" + nombre + "\"");
                } else if (fileExtension.equalsIgnoreCase("bmp")) {
                    String nombre = fileName;
                    response.setContentType("image/bmp");
                    response.setContentLength((int) bytes.length);
                    response.setHeader("Content-Disposition", "attachment; filename=\"" + nombre + "\"");
                } else {
                    String nombre = fileName;
                    response.setContentLength((int) bytes.length);
                    response.setHeader("Content-Disposition", "attachment; filename=\"" + nombre + "\"");
                }

            }
            out.write(bytes);
            out.flush();
            session.removeAttribute("bytes");
            session.removeAttribute("fileName");
        } catch (Throwable ex) {
            logger.log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
