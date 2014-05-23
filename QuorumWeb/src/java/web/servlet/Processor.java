/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package web.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.jar.Attributes;
import java.util.jar.Manifest;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import quorum.Libraries.Web.WebRequest;
import quorum.Libraries.Web.WebResponse$Interface;

/**
 * This Servlet class gathers HTTP requests and sends them to a WebResponder
 * object in Quorum.
 * 
 * @author Andreas Stefik
 */
public class Processor extends HttpServlet {
    quorum.Libraries.Web.WebResponder$Interface main;
    public Processor() {
        
    }
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config); //To change body of generated methods, choose Tools | Templates.
        ServletContext application = config.getServletContext();
        InputStream inputStream = application.getResourceAsStream("/META-INF/MANIFEST.MF");
        try {
            Manifest manifest = new Manifest(inputStream);
            Attributes attributes = manifest.getMainAttributes();
            if(attributes != null) {
                String className = attributes.getValue("Main-Class");
                String manifestVersion = attributes.getValue("Manifest-Version");
                String createdBy = attributes.getValue("Created-by");
                if(className != null) {
                    setMainWebResponder(className);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Processor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void setMainWebResponder(String className) {
        try {
            Class<?> clazz = Class.forName(className);
            if(clazz == null) {
                return;
            }
            main = (quorum.Libraries.Web.WebResponder$Interface) clazz.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException  | SecurityException | IllegalArgumentException ex) {
            Logger.getLogger(Processor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        WebRequest quorumRequest = new WebRequest();
        quorumRequest.SetPath(request.getRequestURI());
        Enumeration<String> parameterNames = request.getParameterNames();
        while(parameterNames.hasMoreElements()) {
            String name = parameterNames.nextElement();
            String value = request.getParameter(name);
            quorumRequest.AddParameter(name, value);
        }
        
        if(main != null) {
            WebResponse$Interface quorumResponse = main.Respond(quorumRequest);
            try {
                String text = quorumResponse.GetPageText();
                out.println(text);
            } finally {
                out.close();
            }
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
        return "This is a default servlet for the Quorum Programming Language.";
    }// </editor-fold>

}
