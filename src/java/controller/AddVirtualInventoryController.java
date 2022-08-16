/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import inventoryAccountant.DAOInventory;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import inventoryAccountant.UserInventoryVirtual;
import virtual.ListInventory;

/**
 *
 * @author 84348
 */
@WebServlet(name = "AddVirtualInventoryController", urlPatterns = {"/AddVirtualInventoryController"})
public class AddVirtualInventoryController extends HttpServlet {

    private static final String ERROR = "createInventory.jsp";
    private static final String SUCCESS = "createInventory.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            boolean k = true;
            DAOInventory dao = new DAOInventory();
            HttpSession session = request.getSession();
            String productID = request.getParameter("productID");
            int quality = Integer.parseInt(request.getParameter("quality"));
            int quantityInChecking = Integer.parseInt(request.getParameter("quantityInChecking"));
            int quantity = dao.getQuantityInProduct(productID);
            String note = request.getParameter("note");
            ListInventory ca = (ListInventory) session.getAttribute("LIST_INVEN_VIRTUAL");

            if (quantity != quantityInChecking) {
                if (!note.equalsIgnoreCase("")) {
                    if (ca != null) {
                        if (ca.getListInventory().size() > 0) {
                            for (UserInventoryVirtual tm : ca.getListInventory().values()) {
                                if (tm.getProductID().equalsIgnoreCase(productID)) {
                                    k = false;
                                }
                            }
                        }
                    }
                    if (k == true) {
                        if (quality > 0) {
                            if (quality < 100) {
                                if (!note.equalsIgnoreCase("")) {
                                    if (quantityInChecking >= 0) {
                                        UserInventoryVirtual tm = new UserInventoryVirtual(quantity, productID, quality, quantityInChecking, quantity, note);

                                        ListInventory cart = (ListInventory) session.getAttribute("LIST_INVEN_VIRTUAL");
                                        if (cart == null) {
                                            cart = new ListInventory();
                                        }
                                        cart.add(tm);
                                        session.setAttribute("LIST_INVEN_VIRTUAL", cart);
                                        url = SUCCESS;
                                    } else {
                                        request.setAttribute("ERROR_ADD", "The number of checks must be greater than 0. Please re-enter.");
                                    }
                                } else {
                                    request.setAttribute("ERROR_ADD", "Please enter a note.");
                                }
                            } else if (quality == 100) {                                
                                UserInventoryVirtual tm = new UserInventoryVirtual(quantity, productID, quality, quantityInChecking, quantity, note);

                                ListInventory cart = (ListInventory) session.getAttribute("LIST_INVEN_VIRTUAL");
                                if (cart == null) {
                                    cart = new ListInventory();
                                }
                                cart.add(tm);
                                session.setAttribute("LIST_INVEN_VIRTUAL", cart);
                                url = SUCCESS;
                            }
                        } else {
                            request.setAttribute("ERROR_ADD", "Quality must be greater than 0. Please re-enter.");
                        }
                    } else {
                        request.setAttribute("ERROR_ADD", "Product ID already exists. Please re-enter.");
                    }
                } else {
                    request.setAttribute("ERROR_ADD", "Please enter a note because your quantity in checking is different.");
                }
            }

            if (quantity == quantityInChecking) {
                if (ca != null) {
                    if (ca.getListInventory().size() > 0) {
                        for (UserInventoryVirtual tm : ca.getListInventory().values()) {
                            if (tm.getProductID().equalsIgnoreCase(productID)) {
                                k = false;
                            }
                        }
                    }
                }
                if (k == true) {
                    if (quality > 0) {
                        if (quality < 100) {
                            if (!note.equalsIgnoreCase("")) {
                                if (quantityInChecking >= 0) {
                                    UserInventoryVirtual tm = new UserInventoryVirtual(quantity, productID, quality, quantityInChecking, quantity, note);

                                    ListInventory cart = (ListInventory) session.getAttribute("LIST_INVEN_VIRTUAL");
                                    if (cart == null) {
                                        cart = new ListInventory();
                                    }
                                    cart.add(tm);
                                    session.setAttribute("LIST_INVEN_VIRTUAL", cart);
                                    url = SUCCESS;
                                } else {
                                    request.setAttribute("ERROR_ADD", "The number of checks must be greater than 0. Please re-enter.");
                                }
                            } else {
                                request.setAttribute("ERROR_ADD", "Please enter a note.");
                            }
                        } else if (quality == 100) {                                
                                UserInventoryVirtual tm = new UserInventoryVirtual(quantity, productID, quality, quantityInChecking, quantity, note);

                                ListInventory cart = (ListInventory) session.getAttribute("LIST_INVEN_VIRTUAL");
                                if (cart == null) {
                                    cart = new ListInventory();
                                }
                                cart.add(tm);
                                session.setAttribute("LIST_INVEN_VIRTUAL", cart);
                                url = SUCCESS;
                            }
                    } else {
                        request.setAttribute("ERROR_ADD", "Quality must be greater than 0. Please re-enter.");
                    }
                } else {
                    request.setAttribute("ERROR_ADD", "Product ID already exists. Please re-enter.");
                }
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
