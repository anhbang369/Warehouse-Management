/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import productManager.DAOProduct;
import user.UserProduct;

/**
 *
 * @author 84348
 */
@WebServlet(name = "UpdateProductManagerController", urlPatterns = {"/UpdateProductManagerController"})
public class UpdateProductManagerController extends HttpServlet {

    private static final String ERROR="listProductManager.jsp";
    private static final String SUCCESS="MainController?action=SearchProductManager&name=";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            DAOProduct dao = new DAOProduct();
            String productID = request.getParameter("productID");
            String name = request.getParameter("name");
            String status = request.getParameter("status");
            String type = request.getParameter("type");
            String madeIn = request.getParameter("madein");
            float depth = Float.parseFloat(request.getParameter("depth"));
            float height = Float.parseFloat(request.getParameter("geight"));
            float width = Float.parseFloat(request.getParameter("width"));
            int manufacturingDate = Integer.parseInt(request.getParameter("manu"));
            float screenSize = Float.parseFloat(request.getParameter("screen"));
            String bluetooth = request.getParameter("blue");//luu y 2 tk nay
            if(bluetooth==null){
                bluetooth=dao.getBlue(productID);
            }
            String voiceRemote = request.getParameter("voice");  
            if(voiceRemote==null){
                voiceRemote=dao.getVoice(productID);
            }
            UserProduct user = new UserProduct(productID, name, madeIn, name, status, type, width, depth, height, screenSize, voiceRemote, bluetooth, manufacturingDate, madeIn, manufacturingDate);
            boolean check =dao.updateProduct(user);
            if(check){
                
                url = SUCCESS; 
            }
        } catch (Exception e) {
            log("Error at UpdateController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
