/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 84348
 */
public class MainController extends HttpServlet {

    private static final String LOGIN = "Login";
    private static final String LOGIN_CONTROLLER = "LoginController";
    private static final String ERROR = "error.jsp";
    private static final String LOGOUT = "Logout";
    private static final String LOGOUT_CONTROLLER = "LogoutController";
    private static final String SEARCH_RECEIPT = "SeacrhReceipt";
    private static final String SEARCH_RECEIPT_CONTROLLER = "SearchReceptController";
    private static final String SHOW_PRODUCT_CREATE = "ShowProductCreate";
    private static final String SHOW_PRODUCT_CREATE_CONTROLLER = "ShowProductCreateController";
    private static final String ADD_RECEIPT = "Finish";
    private static final String ADD_RECEIPT_CONTROLLER = "AddReceiptController";
    private static final String ADD_RECEIPT_DETAIL = "AddReceiptDetail";
    private static final String ADD_RECEIPT_DETAIL_CONTROLLER = "AddReceiptDetailController";
    private static final String SEARCH_RECEIPT_DETAIL = "SeacrhReceiptDetail";
    private static final String SEARCH_RECEIPT_DETAIL_CONTROLLER = "SeacrhReceiptDetailController";
    private static final String EXPROT = "Export";
    private static final String EXPROT_CONTROLLER = "ExportController";
    private static final String SHOW_REPORT = "ShowReport";
    private static final String SHOW_REPORT_CONTROLLER = "ShowReportController";
    private static final String SEARCH_INVENTORY = "SearchInventory";
    private static final String SEARCH_INVENTORY_CONTROLLER = "SearchInventoryController";
    private static final String SEARCH_INVENTORY_F = "SearchInventoryF";
    private static final String SEARCH_INVENTORY_F_CONTROLLER = "SearchInventoryFController";
    private static final String ADD_RECEI_DETAIL_VIRTUAL = "AddVirtualReceiDetail";
    private static final String ADD_RECEI_DETAIL_VIRTUAL_CONTROLLER = "AddVirtualReceiDetailController";
    private static final String ADD_RECEI_VIRTUAL = "AddReceiptVirtual";
    private static final String ADD_RECEI_VIRTUAL_CONTROLLER = "AddReceiptVirtualController";
    private static final String ADD_INVEN_VIRTUAL = "AddVirtualInventory";
    private static final String ADD_INVEN_VIRTUAL_CONTROLLER = "AddVirtualInventoryController";
    private static final String SHOW_PRODUCT_REPORT = "ShowProductReprot";
    private static final String SHOW_PRODUCT_REPORT_CONTROLLER = "ShowProductReprotController";
    private static final String INSERT_REPORT = "Complete";
    private static final String INSERT_REPORT_CONTROLLER = "InsertReportController";
    private static final String INSERT_REPORT_DETAIL = "InsertReportDetail";
    private static final String INSERT_REPORT_DETAIL_CONTROLLER = "InsertReportDetailController";
    private static final String REMOVE_INVENTORY_VIRTUAL = "RemoveInventoryVirtual";
    private static final String REMOVE_INVENTORY_VIRTUAL_CONTROLLER = "RemoveInventoryVirtualController";
    private static final String UPDATE_INVENTORY_VIRTUAL = "UpdateInventoryVirtua";
    private static final String UPDATE_INVENTORY_VIRTUAL_CONTROLLER = "UpdateInventoryVirtuaController";
    private static final String REMOVE_RECEIPT_DETAIL_VIRTUAL = "RemoveReceiptDetailVirtual";
    private static final String REMOVE_RECEIPT_DETAIL_VIRTUAL_CONTROLLER = "RemoveReceiptDetailVirtualController";
    private static final String UPDATE_RECEIPT_DETAIL_VIRTUAL = "UpdateReceiptDetailVirtual";
    private static final String UPDATE_RECEIPT_DETAIL_VIRTUAL_CONTROLLER = "UpdateReceiptDetailVirtualController";
    private static final String SEARCH_ORDER = "SeacrhOrder";
    private static final String SEARCH_ORDER_CONTROLLER = "SeacrhOrderController";
    private static final String INSERT_ISSUE = "InsertIssue";
    private static final String INSERT_ISSUE_CONTROLLER = "InsertIssueController";
    private static final String SEARCH_ISSUE = "SearchIssue";
    private static final String SEARCH_ISSUE_CONTROLLER = "SearchIssueController";
    private static final String UPDATE_ISSUE = "Update Issue";
    private static final String UPDATE_ISSUE_CONTROLLER = "UpdateIssueController";
    private static final String NOTIFY = "Notify";
    private static final String NOTIFY_CONTROLLER = "NotifyImportController";
    private static final String NOTIFY1 = "Notify1";
    private static final String NOTIFY1_CONTROLLER = "NotifyExportController";
    private static final String SEARCH_ORDER_DATE = "SearchOrderDate";
    private static final String SEARCH_ORDER_DATE_CONTROLLER = "SearchOrderDateController";
    private static final String SEARCH_RECEIPT_DATE = "SearchReceiptDate";
    private static final String SEARCH_RECEIPT_DATE_CONTROLLER = "SearchReceiptDateController";
    private static final String SEARCH_INVENTORY_ALPHA = "SearchInventoryAlpha";
    private static final String SEARCH_INVENTORY_ALPHA_CONTROLLER = "SearchInventoryAlphaController";
    private static final String UPDATE_RECEIPT = "Conclusion";
    private static final String UPDATE_RECEIPT_CONTROLLER = "UpdateReceiptController";
    private static final String SHOW_ORDER_DEATIL = "ShowDetailOrder";
    private static final String SHOW_ORDER_DEATIL_CONTROLLER = "ShowDetailOrderController";
    private static final String SHOW_RECEIPT_DETAIL = "ShowDetailReceipt";
    private static final String SHOW_RECEIPT_DETAIL_CONTROLLER = "ShowDetailReceiptController";
    private static final String SHOW_INVENTORY = "ShowInventory";
    private static final String SHOW_INVENTORY_CONTROLLER = "ShowInventoryController";
    private static final String FILE_INVENTORY = "ExportInventory";
    private static final String FILE_INVENTORY_CONTROLLER = "ExportInventoryController";
    private static final String FILE_REPORT = "Export Report";
    private static final String FILE_REPORT_CONTROLLER = "ExportReportController";
    private static final String FILE_ISSUE = "ExportIssue";
    private static final String FILE_ISSUE_CONTROLLER = "ExportIssueController";
    private static final String SHOW_ISSUE_FULL = "ShowIssueFull";
    private static final String SHOW_ISSUE_FULL_CONTROLLER = "ShowIssueFullController";
    private static final String SORT_RECEIPT = "SortReceipt";
    private static final String SORT_RECEIPT_CONTROLLER = "SortReceiptController";
    private static final String SORT_ISSUE = "SortIssue";
    private static final String SORT_ISSUE_CONTROLLER = "SortIssueController";
    private static final String SORT_INVENTORY = "SortInventory";
    private static final String SORT_INVENTORY_CONTROLLER = "SortInventoryController";
    private static final String SORT_REPORT = "SortReport";
    private static final String SORT_REPORT_CONTROLLER = "SortReportController";
    private static final String SORT_ORDER = "SortOrder";
    private static final String SORT_ORDER_CONTROLLER = "SortOrderController";
    private static final String SEARCH_ISSUE_DATE = "SearchIssueDate";
    private static final String SEARCH_ISSUE_DATE_CONTROLLER = "SearchIssueDateController";
    private static final String LOAD_ACCOUNT = "LoadAccount";
    private static final String LOAD_ACCOUNT_CONTROLLER = "LoadAccountController";
    private static final String PRODUCT_ACCOUNTANT = "SearchProductAccount";
    private static final String PRODUCT_ACCOUNTANT_CONTROLLER = "SearchProductAccountController";
    private static final String VIEW_PRODUCT_ACCOUNTANT = "View";
    private static final String VIEW_PRODUCT_ACCOUNTANT_CONTROLLER = "ViewController";
    private static final String COUNT_PRODUCT_Z = "CountZero";
    private static final String COUNT_PRODUCT_Z_CONTROLLER = "CountZeroController";
    private static final String SEND_ORDER_ACCOUNT = "Send Order";
    private static final String SEND_ORDER_ACCOUNT_CONTROLLER = "SendOrderAccountController";
    private static final String VIEW_ORDER_DETAIL_ACCOUNT = "ViewOrderDetailAccount";
    private static final String VIEW_ORDER_DETAIL_ACCOUNT_CONTROLLER = "ViewOrderDetailAccountController";
    private static final String SORT_ORDER_DATE_ACCOUNT = "Sort Date";
    private static final String SORT_ORDER_DATE_ACCOUNT_CONTROLLER = "SortOrderAccountController";
    private static final String SEND_STATUS_ACCOUNT = "Send Receipt";
    private static final String SEND_STATUS_ACCOUNT_CONTROLLER = "SendReceiptController";
    private static final String SEND_STATUS_ISSUE_ACCOUNT = "Send Issue";
    private static final String SEND_STATUS_ISSUE_ACCOUNT_CONTROLLER = "SendStatusIssueAccountController";

    private static final String SEARCH_ORDER_SELLER = "SearchOrderSeller";
    private static final String SEARCH_ORDER_SELLER_CONTROLLER = "SearchOrderSellerController";
    private static final String SEARCH_PRODUCT_ORDER_SELLER = "SearchProductOrderSeller";
    private static final String SEARCH_PRODUCT_ORDER_SELLER_CONTROLLER = "SearchProductOrderSellerController";
    private static final String ADD_VIRTUAL_ORDER_SELLER = "AddVirtualOrderSeller";
    private static final String ADD_VIRTUAL_ORDER_SELLER_CONTROLLER = "AddVirtualOrderSellerController";
    private static final String UPDATE_VIRTUAL_ORDER_SELLER = "UpdateOrderVirtualSeller";
    private static final String UPDATE_VIRTUAL_ORDER_SELLER_CONTROLLER = "UpdateOrderVirtualSellerController";
    private static final String DELETE_VIRTUAL_ORDER_SELLER = "DeleteOrderVirtualSeller";
    private static final String DELETE_VIRTUAL_ORDER_SELLER_CONTROLLER = "DeleteOrderVirtualSellerController";
    private static final String INSRT_ORDER_SELLER = "InsertOrderDetailSeller";
    private static final String INSRT_ORDER_SELLER_CONTROLLER = "InsertOrderDetailSellerController";
    private static final String INSRT_ORDER_DETAIL_SELLER = "InsertOrderDetailSellerDetail";
    private static final String INSRT_ORDER_DETAIL_SELLER_CONTROLLER = "InsertOrderDetailSellerDetailController";
    private static final String SHOW_FULL_ORDER_SELLER = "ShowFullOrderSeller";
    private static final String SHOW_FULL_ORDER_SELLER_CONTROLLER = "ShowFullOrderSellerController";
    private static final String SORT_ORDER_SELLER = "SortOrderSeller";
    private static final String SORT_ORDER_SELLER_CONTROLLER = "SortOrderSellerController";
    private static final String VIEW_ORDER_DETAIL_SELLER = "ViewOrderDetailSeller";
    private static final String VIEW_ORDER_DETAIL_SELLER_CONTROLLER = "ViewOrderDetailSellerController";
    private static final String UPDATE_ORDER_SELLER = "Update Order";
    private static final String UPDATE_ORDER_SELLER_CONTROLLER = "UpdateOrderSellerController";
    private static final String SEND_ORDER_SELLER = "Send";
    private static final String SEND_ORDER_SELLER_CONTROLLER = "SendSellerController";
    private static final String EXPORT_ORDER_SELLER = "ExportOrderSeller";
    private static final String EXPORT_ORDER_SELLER_CONTROLLER = "ExportOrderSellerController";
    private static final String SEARCH_DATE_ORDER_ACCOUNT = "SearchOrderAccountDate";
    private static final String SEARCH_DATE_ORDER_ACCOUNT_CONTROLLER = "SearchOrderAccountDateController";

    private static final String LIST_RECEIPT_MANAGER = "SearchReceiptManager";//Manager
    private static final String LIST_RECEIPT_MANAGER_CONTROLLER = "SearchReceiptManagerController";
    private static final String VIEW_RECEIPT_MANAGER = "ViewRceiptManager";//Manager
    private static final String VIEW_RECEIPT_MANAGER_CONTROLLER = "ViewRceiptManagerController";
    private static final String LIST_ISSUE_MANAGER = "SearchIssueManager";//Manager
    private static final String LIST_ISSUE_MANAGER_CONTROLLER = "SearchIssueManagerController";
    private static final String VIEW_ISSUE_MANAGER = "ViewIssueDetailManager";//Manager
    private static final String VIEW_ISSUE_MANAGER_CONTROLLER = "ViewIssueDetailManagerController";
    private static final String SORT_RECEIPT_MANAGER = "SortReceiptManager";//Manager
    private static final String SORT_RECEIPT_MANAGER_CONTROLLER = "SortReceiptManagerController";
    private static final String SORT_ISSUE_MANAGER = "SortIssueManager";//Manager
    private static final String SORT_ISSUE_MANAGER_CONTROLLER = "SortIssueManagerController";
    private static final String LIST_PRODUCT_MANAGER = "SearchProductManager";//Manager
    private static final String LIST_PRODUCT_MANAGER_CONTROLLER = "SearchProductManagerController";
    private static final String VIEW_PRODUCT_MANAGER = "ViewProductManager";//Manager
    private static final String VIEW_PRODUCT_MANAGER_CONTROLLER = "ViewProductManagerController";
    private static final String CREATE_PRODUCT_MANAGER = "CreateProductManager";//Manager
    private static final String CREATE_PRODUCT_MANAGER_CONTROLLER = "CreateProductManagerController";
    private static final String UPDATE_PRODUCT_MANAGER = "UpdateProductManager";//Manager
    private static final String UPDATE_PRODUCT_MANAGER_CONTROLLER = "UpdateProductManagerController";

    private static final String VIEW_PRODUCT = "ViewProductSK";
    private static final String VIEW_PRODUCT_CONTROLLER = "ViewProductStockkeeperController";
    private static final String VIEW_ISSUE = "ViewIssueSK";
    private static final String VIEW_ISSUE_CONTROLLER = "ViewIssueStockkeeperController";
    private static final String VIEW_BIN = "ViewBinSK";
    private static final String VIEW_BIN_CONTROLLER = "ViewBinStockkeeperController";
    private static final String VIEW_CONSIGNMENT = "ViewConsignmentSK";
    private static final String VIEW_CONSIGNMENT_CONTROLLER = "ViewConsignmentStockkeeperController";
    private static final String CREATE_CONSIGNMENT = "CreateConsignmentSK";
    private static final String CREATE_CONSIGNMENT_CONTROLLER = "CreateConsignmentStockkeeperController";
    private static final String VIEW_CONSIGNMENT_DETAIL = "ViewSK";
    private static final String VIEW_CONSIGNMENT_DETAIL_CONTROLLER = "ViewConsignmentDetailStockkeeperController";
    private static final String VIEW_CREATE_CONSIGNMENT = "ViewCreateConsignmentSK";
    private static final String VIEW_CREATE_CONSIGNMENT_CONTROLLER = "ViewCreateConsignmentStockkeeperController";
    private static final String ADD_BIN = "AddBinSK";
    private static final String ADD_BIN_CONTROLLER = "AddBinStockkeeperController";
    private static final String FINISH = "FinishSK";
    private static final String FINISH_CONTROLLER = "FinishStockkeeperController";
    private static final String DELETE_BIN = "DeleteBinSK";
    private static final String DELETE_BIN_CONTROLLER = "DeleteBinStockkeeperController";
    private static final String CREATE_CLICK = "CreateBinSK";
    private static final String CREATE_CLICK_PAGE = "createBin.jsp";
    private static final String CREATE_BIN = "SaveCreateSK";
    private static final String CREATE_BIN_CONTROLLER = "SaveCreateBinStockkeeperController";
    private static final String SEARCH_INVEN = "SearchInvenSK";
    private static final String SEARCH_INVEN_CONTROLLER = "SearchInvenStockkeeperController";
    private static final String SAVE_INVEN = "SaveInvenSK";
    private static final String SAVE_INVEN_CONTROLLER = "SaveInvenStockkeeperController";
    private static final String SEARCH_ISSUE_STOCKEEPER = "SearchIssueStockeeper";
    private static final String SEARCH_ISSUE_STOCKEEPER_CONTROLLER = "SearchIssueStockeeperController";
    private static final String SEARCH_VIEW_ISSUE_STOCKEEPER = "ViewIssueDetailStockeepr";
    private static final String SEARCH_VIEW_ISSUE_STOCKEEPER_CONTROLLER = "ViewIssueDetailStockeeprController";
    private static final String SORT_ISSUE_STOCKEEP = "SortIssueStockeeper";
    private static final String SORT_ISSUE_STOCKEEP_CONTROLLER = "SortIssueStockeeperController";
    private static final String UPDATE_BIN_STOCKEEP = "UpdateBinStockepp";
    private static final String UPDATE_BIN_STOCKEEP_CONTROLLER = "UpdateBinStockeppController";
    private static final String UPDATE_ISSUE_STOCKEEP = "UpdateStatusStockeep";
    private static final String UPDATE_ISSUE_STOCKEEP_CONTROLLER = "UpdateStatusStockeepController";
    private static final String SORT_CONSIGNMENT_SK = "SortConsignmentSK";
    private static final String SORT_CONSIGNMENT_SK_CONTROLLER = "SortConsignmentStockkeeperController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String action = request.getParameter("action");
            if (LOGIN.equals(action)) {
                url = LOGIN_CONTROLLER;
            } else if (LOGOUT.equals(action)) {
                url = LOGOUT_CONTROLLER;
            } else if (SEARCH_RECEIPT.equals(action)) {
                url = SEARCH_RECEIPT_CONTROLLER;
            } else if (SHOW_PRODUCT_CREATE.equals(action)) {
                url = SHOW_PRODUCT_CREATE_CONTROLLER;
            } else if (ADD_RECEIPT.equals(action)) {
                url = ADD_RECEIPT_CONTROLLER;
            } else if (ADD_RECEIPT_DETAIL.equals(action)) {
                url = ADD_RECEIPT_DETAIL_CONTROLLER;
            } else if (SEARCH_RECEIPT_DETAIL.equals(action)) {
                url = SEARCH_RECEIPT_DETAIL_CONTROLLER;
            } else if (EXPROT.equals(action)) {
                url = EXPROT_CONTROLLER;
            } else if (SHOW_REPORT.equals(action)) {
                url = SHOW_REPORT_CONTROLLER;
            } else if (SORT_REPORT.equals(action)) {
                url = SORT_REPORT_CONTROLLER;
            } else if (SEARCH_INVENTORY.equals(action)) {
                url = SEARCH_INVENTORY_CONTROLLER;
            } else if (SEARCH_INVENTORY_F.equals(action)) {
                url = SEARCH_INVENTORY_F_CONTROLLER;
            } else if (ADD_RECEI_DETAIL_VIRTUAL.equals(action)) {
                url = ADD_RECEI_DETAIL_VIRTUAL_CONTROLLER;
            } else if (ADD_RECEI_VIRTUAL.equals(action)) {
                url = ADD_RECEI_VIRTUAL_CONTROLLER;
            } else if (ADD_INVEN_VIRTUAL.equals(action)) {
                url = ADD_INVEN_VIRTUAL_CONTROLLER;
            } else if (SHOW_PRODUCT_REPORT.equals(action)) {
                url = SHOW_PRODUCT_REPORT_CONTROLLER;
            } else if (INSERT_REPORT.equals(action)) {
                url = INSERT_REPORT_CONTROLLER;
            } else if (INSERT_REPORT_DETAIL.equals(action)) {
                url = INSERT_REPORT_DETAIL_CONTROLLER;
            } else if (REMOVE_INVENTORY_VIRTUAL.equals(action)) {
                url = REMOVE_INVENTORY_VIRTUAL_CONTROLLER;
            } else if (UPDATE_INVENTORY_VIRTUAL.equals(action)) {
                url = UPDATE_INVENTORY_VIRTUAL_CONTROLLER;
            } else if (REMOVE_RECEIPT_DETAIL_VIRTUAL.equals(action)) {
                url = REMOVE_RECEIPT_DETAIL_VIRTUAL_CONTROLLER;
            } else if (UPDATE_RECEIPT_DETAIL_VIRTUAL.equals(action)) {
                url = UPDATE_RECEIPT_DETAIL_VIRTUAL_CONTROLLER;
            } else if (SEARCH_ORDER.equals(action)) {
                url = SEARCH_ORDER_CONTROLLER;
            } else if (INSERT_ISSUE.equals(action)) {
                url = INSERT_ISSUE_CONTROLLER;
            } else if (SEARCH_ISSUE.equals(action)) {
                url = SEARCH_ISSUE_CONTROLLER;
            } else if (UPDATE_ISSUE.equals(action)) {
                url = UPDATE_ISSUE_CONTROLLER;
            } else if (NOTIFY.equals(action)) {
                url = NOTIFY_CONTROLLER;
            } else if (NOTIFY1.equals(action)) {
                url = NOTIFY1_CONTROLLER;
            } else if (SEARCH_ORDER_DATE.equals(action)) {
                url = SEARCH_ORDER_DATE_CONTROLLER;
            } else if (SEARCH_RECEIPT_DATE.equals(action)) {
                url = SEARCH_RECEIPT_DATE_CONTROLLER;
            } else if (SEARCH_INVENTORY_ALPHA.equals(action)) {
                url = SEARCH_INVENTORY_ALPHA_CONTROLLER;
            } else if (UPDATE_RECEIPT.equals(action)) {
                url = UPDATE_RECEIPT_CONTROLLER;
            } else if (SHOW_ORDER_DEATIL.equals(action)) {
                url = SHOW_ORDER_DEATIL_CONTROLLER;
            } else if (SHOW_RECEIPT_DETAIL.equals(action)) {
                url = SHOW_RECEIPT_DETAIL_CONTROLLER;
            } else if (SHOW_INVENTORY.equals(action)) {
                url = SHOW_INVENTORY_CONTROLLER;
            } else if (FILE_INVENTORY.equals(action)) {
                url = FILE_INVENTORY_CONTROLLER;
            } else if (FILE_REPORT.equals(action)) {
                url = FILE_REPORT_CONTROLLER;
            } else if (FILE_ISSUE.equals(action)) {
                url = FILE_ISSUE_CONTROLLER;
            } else if (SHOW_ISSUE_FULL.equals(action)) {
                url = SHOW_ISSUE_FULL_CONTROLLER;
            } else if (SORT_RECEIPT.equals(action)) {
                url = SORT_RECEIPT_CONTROLLER;
            } else if (SORT_ISSUE.equals(action)) {
                url = SORT_ISSUE_CONTROLLER;
            } else if (SORT_INVENTORY.equals(action)) {
                url = SORT_INVENTORY_CONTROLLER;
            } else if (SORT_ORDER.equals(action)) {
                url = SORT_ORDER_CONTROLLER;
            } else if (SEARCH_ISSUE_DATE.equals(action)) {
                url = SEARCH_ISSUE_DATE_CONTROLLER;
            } else if (LOAD_ACCOUNT.equals(action)) {
                url = LOAD_ACCOUNT_CONTROLLER;
            } else if (PRODUCT_ACCOUNTANT.equals(action)) {
                url = PRODUCT_ACCOUNTANT_CONTROLLER;
            } else if (VIEW_PRODUCT_ACCOUNTANT.equals(action)) {
                url = VIEW_PRODUCT_ACCOUNTANT_CONTROLLER;
            } else if (SEARCH_ORDER_SELLER.equals(action)) {//selller
                url = SEARCH_ORDER_SELLER_CONTROLLER;
            } else if (SEARCH_PRODUCT_ORDER_SELLER.equals(action)) {//selller
                url = SEARCH_PRODUCT_ORDER_SELLER_CONTROLLER;
            } else if (ADD_VIRTUAL_ORDER_SELLER.equals(action)) {//selller
                url = ADD_VIRTUAL_ORDER_SELLER_CONTROLLER;
            } else if (UPDATE_VIRTUAL_ORDER_SELLER.equals(action)) {//selller
                url = UPDATE_VIRTUAL_ORDER_SELLER_CONTROLLER;
            } else if (DELETE_VIRTUAL_ORDER_SELLER.equals(action)) {//selller
                url = DELETE_VIRTUAL_ORDER_SELLER_CONTROLLER;
            } else if (INSRT_ORDER_SELLER.equals(action)) {//selller
                url = INSRT_ORDER_SELLER_CONTROLLER;
            } else if (INSRT_ORDER_DETAIL_SELLER.equals(action)) {//selller
                url = INSRT_ORDER_DETAIL_SELLER_CONTROLLER;
            } else if (SHOW_FULL_ORDER_SELLER.equals(action)) {//selller
                url = SHOW_FULL_ORDER_SELLER_CONTROLLER;
            } else if (SORT_ORDER_SELLER.equals(action)) {//selller
                url = SORT_ORDER_SELLER_CONTROLLER;
            } else if (VIEW_ORDER_DETAIL_SELLER.equals(action)) {//selller
                url = VIEW_ORDER_DETAIL_SELLER_CONTROLLER;
            } else if (UPDATE_ORDER_SELLER.equals(action)) {//selller
                url = UPDATE_ORDER_SELLER_CONTROLLER;
            } else if (SEND_ORDER_SELLER.equals(action)) {//selller
                url = SEND_ORDER_SELLER_CONTROLLER;
            } else if (EXPORT_ORDER_SELLER.equals(action)) {//selller
                url = EXPORT_ORDER_SELLER_CONTROLLER;
            } else if (LIST_RECEIPT_MANAGER.equals(action)) {//manager
                url = LIST_RECEIPT_MANAGER_CONTROLLER;
            } else if (VIEW_RECEIPT_MANAGER.equals(action)) {//manager
                url = VIEW_RECEIPT_MANAGER_CONTROLLER;
            } else if (LIST_ISSUE_MANAGER.equals(action)) {//manager
                url = LIST_ISSUE_MANAGER_CONTROLLER;
            } else if (VIEW_ISSUE_MANAGER.equals(action)) {//manager
                url = VIEW_ISSUE_MANAGER_CONTROLLER;
            } else if (SORT_RECEIPT_MANAGER.equals(action)) {//manager
                url = SORT_RECEIPT_MANAGER_CONTROLLER;
            } else if (SORT_ISSUE_MANAGER.equals(action)) {//manager
                url = SORT_ISSUE_MANAGER_CONTROLLER;
            } else if (LIST_PRODUCT_MANAGER.equals(action)) {//manager
                url = LIST_PRODUCT_MANAGER_CONTROLLER;
            } else if (VIEW_PRODUCT_MANAGER.equals(action)) {//manager
                url = VIEW_PRODUCT_MANAGER_CONTROLLER;
            } else if (CREATE_PRODUCT_MANAGER.equals(action)) {//manager
                url = CREATE_PRODUCT_MANAGER_CONTROLLER;
            } else if (UPDATE_PRODUCT_MANAGER.equals(action)) {//manager
                url = UPDATE_PRODUCT_MANAGER_CONTROLLER;
            } else if (VIEW_PRODUCT.equals(action)) {
                url = VIEW_PRODUCT_CONTROLLER;
            } else if (VIEW_ISSUE.equals(action)) {
                url = VIEW_ISSUE_CONTROLLER;
            } else if (VIEW_BIN.equals(action)) {
                url = VIEW_BIN_CONTROLLER;
            } else if (VIEW_CONSIGNMENT.equals(action)) {
                url = VIEW_CONSIGNMENT_CONTROLLER;
            } else if (CREATE_CONSIGNMENT.equals(action)) {
                url = CREATE_CONSIGNMENT_CONTROLLER;
            } else if (VIEW_CONSIGNMENT_DETAIL.equals(action)) {
                url = VIEW_CONSIGNMENT_DETAIL_CONTROLLER;
            } else if (VIEW_CREATE_CONSIGNMENT.equals(action)) {
                url = VIEW_CREATE_CONSIGNMENT_CONTROLLER;
            } else if (ADD_BIN.equals(action)) {
                url = ADD_BIN_CONTROLLER;
            } else if (FINISH.equals(action)) {
                url = FINISH_CONTROLLER;
            } else if (DELETE_BIN.equals(action)) {
                url = DELETE_BIN_CONTROLLER;
            } else if (CREATE_CLICK.equals(action)) {
                url = CREATE_CLICK_PAGE;
            } else if (CREATE_BIN.equals(action)) {
                url = CREATE_BIN_CONTROLLER;
            } else if (SEARCH_INVEN.equals(action)) {
                url = SEARCH_INVEN_CONTROLLER;
            } else if (SAVE_INVEN.equals(action)) {
                url = SAVE_INVEN_CONTROLLER;
            } else if (SEARCH_ISSUE_STOCKEEPER.equals(action)) {
                url = SEARCH_ISSUE_STOCKEEPER_CONTROLLER;
            } else if (SEARCH_VIEW_ISSUE_STOCKEEPER.equals(action)) {
                url = SEARCH_VIEW_ISSUE_STOCKEEPER_CONTROLLER;
            } else if (SORT_ISSUE_STOCKEEP.equals(action)) {
                url = SORT_ISSUE_STOCKEEP_CONTROLLER;
            } else if (UPDATE_BIN_STOCKEEP.equals(action)) {
                url = UPDATE_BIN_STOCKEEP_CONTROLLER;
            } else if (UPDATE_ISSUE_STOCKEEP.equals(action)) {
                url = UPDATE_ISSUE_STOCKEEP_CONTROLLER;
            }else if (COUNT_PRODUCT_Z.equals(action)) {
                url = COUNT_PRODUCT_Z_CONTROLLER;
            }else if (SEND_ORDER_ACCOUNT.equals(action)) {
                url = SEND_ORDER_ACCOUNT_CONTROLLER;
            }else if (VIEW_ORDER_DETAIL_ACCOUNT.equals(action)) {
                url = VIEW_ORDER_DETAIL_ACCOUNT_CONTROLLER;
            }else if (SORT_ORDER_DATE_ACCOUNT.equals(action)) {
                url = SORT_ORDER_DATE_ACCOUNT_CONTROLLER;
            }else if (SEND_STATUS_ACCOUNT.equals(action)) {
                url = SEND_STATUS_ACCOUNT_CONTROLLER;
            }else if (SEND_STATUS_ISSUE_ACCOUNT.equals(action)) {
                url = SEND_STATUS_ISSUE_ACCOUNT_CONTROLLER;
            }else if (SEARCH_DATE_ORDER_ACCOUNT.equals(action)) {
                url = SEARCH_DATE_ORDER_ACCOUNT_CONTROLLER;
            } else if (SORT_CONSIGNMENT_SK.equals(action)) {
                url = SORT_CONSIGNMENT_SK_CONTROLLER;
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
