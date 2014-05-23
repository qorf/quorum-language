/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package web.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import quorum.Libraries.Web.WebRequest;
import quorum.Libraries.Web.WebResponse$Interface;

/**
 *
 * @author stefika
 */
public class Processor extends HttpServlet {
    quorum.Libraries.Web.WebResponder$Interface main;
    WebRequest quorumRequest;
    public Processor() {
        quorumRequest = new WebRequest();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream input = classLoader.getResourceAsStream("META-INF/MANIFEST.MF");
        InputStreamReader isr = new InputStreamReader(input);
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(isr);
        
        String read;
        try {
            read = br.readLine();
            setMainWebResponder(read);
            
            if(main != null) {
                return;
            }
            while(read != null) {
                sb.append(read);
                read = br.readLine();
                setMainWebResponder(read);
                if(main != null) {
                    return;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Processor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void setMainWebResponder(String line) {
        final String mainClass = "Main-Class: ";
        if(line != null && line.startsWith(mainClass)) {
            String className = line.substring(mainClass.length());
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
        quorumRequest.SetPath(request.getRequestURI());
        WebResponse$Interface quorumResponse = main.Respond(quorumRequest);
        
        try {
            String text = quorumResponse.GetPageText();
            out.println(text);
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
        return "This is a default servlet for the Quorum Programming Language.";
    }// </editor-fold>

}
