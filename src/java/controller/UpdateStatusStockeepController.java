/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import inventoryStockeepr.BinDAO;
import inventoryStockeepr.InventoryDAO;
import inventoryStockeepr.IssueDAO;
import inventoryStockeepr.UserBinQuantity;
import inventoryStockeepr.UserInventoryID;
import inventoryStockeepr.UserUpdateStatus;
import issueAccountant.DAOIssue;
import issueAccountant.UserOrderDetail;
import issueAccountant.UserProductI;
import issueAccountant.UserProductU;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 84348
 */
@WebServlet(name = "UpdateStatusStockeepController", urlPatterns = {"/UpdateStatusStockeepController"})
public class UpdateStatusStockeepController extends HttpServlet {

    private static final String ERROR="viewIssue.jsp";
    private static final String SUCCESS="MainController?action=SearchIssueStockeeper&searchIssue=&searchCustomer=";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            boolean flag = false;
            BinDAO dao = new BinDAO();
            DAOIssue dao1 = new DAOIssue();
            int issueID = Integer.parseInt(request.getParameter("issueID"));
            int orderID = Integer.parseInt(request.getParameter("orderID"));//
            String note = "Implemented";
            InventoryDAO daoI = new InventoryDAO();
            boolean checkStatusUpdate = false, checkClear = false;
            boolean checkIssue = dao.checkIssueStatus(issueID);
            if (checkIssue == false) {
                UserUpdateStatus user = new UserUpdateStatus(issueID, note);
                boolean check = dao.updateIssueStockeep(user);
                //
                List<UserOrderDetail> listOrderDetail = dao1.getListOrderDetail(orderID);
                if (listOrderDetail != null) {
                    if (listOrderDetail.size() > 0) {
                        for (UserOrderDetail userOrderDetail : listOrderDetail) {
                            String productID = userOrderDetail.getProductID();
                            int quantityOrderDetail = userOrderDetail.getQuantity();

                            List<UserProductI> listProduct = dao1.getListProduct(productID);
                            if (listProduct != null) {
                                if (listOrderDetail.size() > 0) {
                                    if (check == true) {
                                        for (UserProductI userProductI : listProduct) {
                                            if ((userProductI.getQuantity() - quantityOrderDetail) > 0) {
                                                int quantity = userProductI.getQuantity() - quantityOrderDetail;
                                                UserProductU pro = new UserProductU(productID, quantity);
                                                boolean update = dao1.updateQuantityProductS(pro);

                                                boolean okNha = false;
                                                List<UserInventoryID> listInvento = dao.getBinID(userProductI.getProductID());
                                                if (listInvento != null) {
                                                    if (listInvento.size() > 0) {
                                                        for (UserInventoryID userInventoryID : listInvento) {
                                                            List<UserBinQuantity> listBinQuan = dao.getBinQuantity(userInventoryID.getInventoryID());
                                                            if (listBinQuan != null) {
                                                                int finish = quantityOrderDetail;
                                                                boolean updateLarge = false;
                                                                boolean updateSmall = false;
                                                                for (UserBinQuantity userBinQuantity : listBinQuan) {
                                                                    int subtracBin = userBinQuantity.getQuantity() - finish;
                                                                    if (subtracBin >= 0) {
                                                                        updateLarge = dao.updateBinQ(subtracBin, userBinQuantity.getBinID());
                                                                    } else {
                                                                        updateSmall = dao.updateBinQ(0, userBinQuantity.getBinID());
                                                                    }

                                                                    if (updateLarge == true || updateSmall == true) {
                                                                        finish = finish - userBinQuantity.getQuantity();
                                                                        if (finish <= 0) {
                                                                            okNha = true;
                                                                            break;
                                                                        }
                                                                    }

                                                                }
                                                            }
                                                        }
                                                    }
                                                }

                                                if (update == true && okNha == true) {
                                                    checkClear = dao.clearBin();
                                                    checkStatusUpdate = daoI.updateAllStatus();
                                                    flag = true;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                //
                if (flag == true) {
                    url = SUCCESS;
                }
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
