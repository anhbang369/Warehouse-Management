/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import orderSeller.DAOOrder;
import orderSeller.UserOrderFullSeller;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import time.Timer;
import user.UserDTO;

/**
 *
 * @author 84348
 */
@WebServlet(name = "ExportOrderSellerController", urlPatterns = {"/ExportOrderSellerController"})
public class ExportOrderSellerController extends HttpServlet {

    private static final String ERROR = "";
    private static final String SUCCESS = "MainController?action=SearchOrderSeller&OrderI=";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            Timer t = new Timer();
            String a = t.dateFull();
            DAOOrder dao = new DAOOrder();
            FileOutputStream file = new FileOutputStream("C:/Users/quang/Documents/Zalo Received Files/SWP391_Again/SWP391_Again6/" + a + ".xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("name");
            XSSFRow row;
            XSSFRow row1;
            XSSFRow row2;
            XSSFRow row3;
            XSSFRow row4;
            XSSFRow row5;
            XSSFRow row6;
            XSSFRow row7;
            XSSFRow row8;
            XSSFRow row9;
            XSSFCell cellA;
            XSSFCell cellB;
            XSSFCell cellC;
            XSSFCell cellD;
            XSSFCell cellE;
            XSSFCell cellF;
            XSSFCell cellG;
            XSSFCell cellH;
            XSSFCell cellJ;
            XSSFCell cellZ;
            XSSFCell cellX;
            XSSFCell cellM;
            XSSFCell cellV;
            XSSFCell cellQ;
            XSSFCell cellW;
            XSSFCell cellR;
            XSSFCell cellT;
            XSSFCell cellY;
            CellStyle style = workbook.createCellStyle();
            XSSFFont font = workbook.createFont();
            font.setBold(true);
            font.setFontHeight(20);
            style.setFont(font);

            CellStyle styleSeceond = workbook.createCellStyle();
            XSSFFont fontSeceond = workbook.createFont();
            fontSeceond.setBold(true);
            fontSeceond.setFontHeight(12);
            styleSeceond.setFont(fontSeceond);

            CellStyle styleLast = workbook.createCellStyle();
            XSSFFont fontLast = workbook.createFont();
            fontLast.setFontHeight(12);
            styleLast.setFont(fontLast);

            row = sheet.createRow(0);
            Cell cell0 = row.createCell(3);
            cell0.setCellValue("PHIẾU XUẤT KHO");
            cell0.setCellStyle(style);

            row1 = sheet.createRow(1);
            Cell cell1 = row1.createCell(3);
            cell1.setCellValue("Ngày xuất kho: " + t.timeNowUser());

            row4 = sheet.createRow(4);
            Cell cell6 = row4.createCell(0);
            cell6.setCellValue("STT");
            styleSeceond.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
            styleSeceond.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell6.setCellStyle(styleSeceond);
            Cell cell8 = row4.createCell(2);
            cell8.setCellValue("Mã đăt hàng");
            styleSeceond.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
            styleSeceond.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell8.setCellStyle(styleSeceond);
            Cell cell2 = row4.createCell(5);
            cell2.setCellValue("Mã mặt hàng");
            styleSeceond.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
            styleSeceond.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell2.setCellStyle(styleSeceond);
            Cell cell3 = row4.createCell(8);
            cell3.setCellValue("Số lượng");
            styleSeceond.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
            styleSeceond.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell3.setCellStyle(styleSeceond);

            Cell cell20 = row4.createCell(1);
            styleSeceond.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
            styleSeceond.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell20.setCellStyle(styleSeceond);
            Cell cell21 = row4.createCell(3);
            styleSeceond.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
            styleSeceond.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell21.setCellStyle(styleSeceond);
            Cell cell22 = row4.createCell(4);
            styleSeceond.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
            styleSeceond.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell22.setCellStyle(styleSeceond);
            Cell cell23 = row4.createCell(6);
            styleSeceond.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
            styleSeceond.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell23.setCellStyle(styleSeceond);
            Cell cell24 = row4.createCell(7);
            styleSeceond.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
            styleSeceond.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell24.setCellStyle(styleSeceond);

            int orderId = dao.getOrderId();
            List<UserOrderFullSeller> list = dao.getListOrderFull(orderId);
            if (list != null) {
                if (list.size() > 0) {
                    int i = 1;
                    for (UserOrderFullSeller tm : list) {
                        if (i % 2 == 0) {
                            row5 = sheet.createRow((short) i + 4);
                            cellF = row5.createCell((short) 0);
                            cellF.setCellValue(i);
                            styleLast.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
                            styleLast.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                            cellF.setCellStyle(styleLast);

                            cellA = row5.createCell((short) 2);
                            cellA.setCellValue(tm.getOrderDetailID());
                            styleLast.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
                            styleLast.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                            cellA.setCellStyle(styleLast);

                            cellB = row5.createCell((short) 5);
                            cellB.setCellValue(tm.getProductID());
                            styleLast.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
                            styleLast.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                            cellB.setCellStyle(styleLast);

                            cellC = row5.createCell((short) 8);
                            cellC.setCellValue(tm.getQuantity());
                            styleLast.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
                            styleLast.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                            cellC.setCellStyle(styleLast);

                            

                            cellG = row5.createCell((short) 1);
                            styleLast.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
                            styleLast.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                            cellG.setCellStyle(styleLast);
                            cellH = row5.createCell((short) 3);
                            styleLast.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
                            styleLast.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                            cellH.setCellStyle(styleLast);
                            cellJ = row5.createCell((short) 4);
                            styleLast.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
                            styleLast.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                            cellJ.setCellStyle(styleLast);
                            cellZ = row5.createCell((short) 6);
                            styleLast.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
                            styleLast.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                            cellZ.setCellStyle(styleLast);
                            cellX = row5.createCell((short) 7);
                            styleLast.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
                            styleLast.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                            cellX.setCellStyle(styleLast);
                            

                        } else {
                            row5 = sheet.createRow((short) i + 4);
                            cellF = row5.createCell((short) 0);
                            cellF.setCellValue(i);
                            cellA = row5.createCell((short) 2);
                            cellA.setCellValue(tm.getOrderDetailID());
                            cellB = row5.createCell((short) 5);
                            cellB.setCellValue(tm.getProductID());
                            cellC = row5.createCell((short) 8);
                            cellC.setCellValue(tm.getQuantity());
                        }
                        i++;
                    }
                }
            }

            List<UserOrderFullSeller> listT = dao.getListOrderFull(orderId);
            if (listT != null) {
                if (listT.size() > 0) {
                    for (UserOrderFullSeller tm : listT) {
                        row3 = sheet.createRow(3);
                        Cell cellLL = row3.createCell(0);
                        cellLL.setCellValue("Accountant: " + loginUser.getAccountID());
                        Cell cellUSK = row3.createCell(4);
                        cellUSK.setCellValue("StockKeeper: " + tm.getSellerID());

                        row2 = sheet.createRow(2);
                        Cell cellND = row2.createCell(3);
                        cellND.setCellValue("Đơn đặt hàng: " + tm.getOrderID());


                        row7 = sheet.createRow((short) listT.size() + 2 + 5);
                        cellW = row7.createCell((short) 0);
                        cellW.setCellValue("Ghi chú: " + tm.getNote());

                        row8 = sheet.createRow((short) listT.size() + 3 + 5);
                        cellR = row8.createCell((short) 0);
                        cellR.setCellValue("Khách hàng: " + tm.getCustomerName());

                        cellT = row8.createCell((short) 4);
                        cellT.setCellValue("Điện thoại: " + tm.getPhoneNumber());

                        row9 = sheet.createRow((short) listT.size() + 4 + 5);
                        cellY = row9.createCell((short) 0);
                        cellY.setCellValue("Địa chỉ: " + tm.getAddress());
                        break;
                    }
                }
            }

            workbook.write(file);
            workbook.close();
            file.close();
            url = SUCCESS;

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
