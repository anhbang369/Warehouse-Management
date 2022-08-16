/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import reportAccountant.DAOReport;
import reportAccountant.UserProductName;
import reportAccountant.UserReport;
import time.Timer;

/**
 *
 * @author 84348
 */
@WebServlet(name = "ShowReportController", urlPatterns = {"/ShowReportController"})
public class ShowReportController extends HttpServlet {

    private static final String ERROR = "report.jsp";
    private static final String SUCCESS = "report.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            if (session != null) {
                session.removeAttribute("LIST_REPORT");
                session.removeAttribute("DATE_PO");
            }
            Timer t  = new Timer();
            String product = request.getParameter("productID");
            String name = request.getParameter("name");
            String search = request.getParameter("date");
            String search1 = request.getParameter("date2");
            request.setAttribute("DATE_PO", "From: "+search + " To: "+search1);
            DAOReport dao = new DAOReport();

            Calendar c = Calendar.getInstance();
            c.add(Calendar.DAY_OF_YEAR, -1095);
            Date d = c.getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String strDate = dateFormat.format(d);
            if (search.compareTo(search1) <= 0 && search.compareTo(t.timeNow())<=0 && search1.compareTo(t.timeNow())<=0) {
                if (search.compareTo(strDate) >= 0 && search1.compareTo(strDate) >= 0) {
                    List<UserProductName> listProduct = dao.getListShowProductName(product, name);
                    if (listProduct.size() > 0) {
                        request.setAttribute("LIST_PRODUCT_NAME", listProduct);
                    }

                    List<UserProductName> listUser = (List<UserProductName>) request.getAttribute("LIST_PRODUCT_NAME");
                    List<UserReport> tp = new ArrayList<>();
                    if (listUser != null) {
                        if (listUser.size() > 0) {
                            for (UserProductName up : listUser) {
                                String ProductID = up.getProductID();
                                String Name = up.getName();
                                String Brand = up.getBrand();
                                int quantityBegin = dao.quantityBeginnI(ProductID, search) - dao.quantityBeginnO(ProductID, search);
                                int quantityEnd = quantityBegin + (dao.quantityEndI(ProductID, search, search1) - dao.quantityEndO(ProductID, search, search1));
                                int importP = dao.importt(ProductID, search, search1);
                                int exportP = dao.exportt(ProductID, search, search1);
                                UserReport tm = new UserReport(ProductID, Name, Brand, quantityBegin, quantityEnd, importP, exportP);
                                tp.add(tm);
                                session.setAttribute("LIST_REPORT", tp);

                            }
                            url = SUCCESS;
                        }
                    } else {
                        request.setAttribute("ERROR_REPORT", "Result not found!");
                    }
                } else {
                    request.setAttribute("ERROR_REPORT", "The current reporting date is more than 3 years from today.");
                }
            } else {
                request.setAttribute("ERROR_REPORT", "Invalid date entered.");
            }

        } catch (Exception e) {
            System.out.println("Error at ShowController: " + e.toString());
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
