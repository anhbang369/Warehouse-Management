/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import productManager.DAOProduct;
import time.Timer;
import user.UserProduct;
import virtualSeller.ListProduct;

/**
 *
 * @author 84348
 */
@WebServlet(name = "CreateProductManagerController", urlPatterns = {"/CreateProductManagerController"})
public class CreateProductManagerController extends HttpServlet {

    private static final String ERROR = "createProductManager.jsp";
    private static final String SUCCESS = "createProductManager.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            String name = request.getParameter("name");
            String model = request.getParameter("model");
            String brand = request.getParameter("brand");
            String type = request.getParameter("type");
            String madeIn = request.getParameter("madeIn");
            float depth = Float.parseFloat(request.getParameter("depth"));
            float height = Float.parseFloat(request.getParameter("height"));
            float width = Float.parseFloat(request.getParameter("width"));
            int manufacturingDate = Integer.parseInt(request.getParameter("manufacturingDate"));
            float screenSize = Float.parseFloat(request.getParameter("screenSize"));
            int quantity = 0;//
            String bluet = request.getParameter("bluetooth");
            String voiceR = request.getParameter("voiceRemote");
            String bluetooth, voiceRemote;
            if(bluet==null){
                bluetooth = "Not have";
            }else {
                bluetooth = "Have";
            }
            if(voiceR==null){
                voiceRemote = "Not have";
            }else {
                voiceRemote = "Have";
            } 
            boolean regexx = false;
            String regex = "^[A-Z]{2}[-][A-Z]{3,10}$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(brand);
            if(matcher.find()){
                regexx = true;
            }else{
                regexx = false;
            }
            
            
            
            if(regexx==true){
                String productID = (brand.substring(0, 2)).toUpperCase() + ("-").concat(model);
            DAOProduct dao = new DAOProduct();

            Calendar c = Calendar.getInstance();
            c.add(Calendar.DAY_OF_YEAR, -1095);
            Date d = c.getTime();
            Timer t = new Timer();
            DateFormat dateFormat = new SimpleDateFormat("yyyy");
            int strDate = Integer.parseInt(dateFormat.format(d));//qk
            int strD = Integer.parseInt(t.timeNowYear());//ht

            boolean checkModel = dao.getModel(model);
            if (checkModel == false) {
                if (name.length() >= 5 && name.length() <= 50) {
                    if (brand.length() > 1 && brand.length() <= 10) {
                        if (manufacturingDate >= strDate && manufacturingDate <= strD) {
                            UserProduct product = new UserProduct(productID, name, model, brand, "True", type, width, depth, height, screenSize, voiceRemote, bluetooth, manufacturingDate, madeIn, quantity);
                            ListProduct cart = (ListProduct) session.getAttribute("LIST_PRODUCT_SUCCESS_ADD");
                            if (cart == null) {
                                cart = new ListProduct();
                            }
                            cart.add(product);
                            session.setAttribute("LIST_PRODUCT_SUCCESS_ADD", cart);

                            boolean creatProduct = dao.createProduct(product);
                            if (creatProduct == true) {
                                url = SUCCESS;
                            }
                        } else {
                            request.setAttribute("ERROR_PRODUCT", "Product year must be between" + strDate + " and this year");
                        }

                    } else {
                        request.setAttribute("ERROR_PRODUCT", "Full Brand must be [1, 10].");
                    }
                } else {
                    request.setAttribute("ERROR_PRODUCT", "Full Name must be [5, 50].");
                }
            } else {
                request.setAttribute("ERROR_PRODUCT", "Duplicated Model.");
            }
            }else{
                request.setAttribute("ERROR_PRODUCT", "Wrong format!!! Example: SS-SUMSUNG");
            }

        } catch (Exception e) {
            e.printStackTrace();
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
