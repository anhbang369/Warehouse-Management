/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import receiptAccountant.DAOReceipt;
import receiptAccountant.UserFakeList;
import time.Timer;
import user.UserDTO;

/**
 *
 * @author 84348
 */
@WebServlet(name = "ExportController", urlPatterns = {"/ExportController"})
public class ExportController extends HttpServlet {

    private static final String ERROR = "MainController?action=SeacrhReceiptDetail&searchDetail=";
    private static final String SUCCESS = "MainController?action=SeacrhReceipt&search=";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            Timer t = new Timer();
            String a = t.dateFull();
            DAOReceipt dao = new DAOReceipt();
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
            XSSFCell cellA;
            XSSFCell cellC;
            XSSFCell cellD;
            XSSFCell cellE;
            XSSFCell cellF;
            XSSFCell cellG;
            XSSFCell cellH;
            XSSFCell cellJ;
            XSSFCell cellM;
            XSSFCell cellN;
            XSSFCell cellL;
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
            styleSeceond.setAlignment(HorizontalAlignment.CENTER);

            CellStyle styleLast = workbook.createCellStyle();
            XSSFFont fontLast = workbook.createFont();
            fontLast.setFontHeight(12);
            styleLast.setFont(fontLast);
            styleLast.setAlignment(HorizontalAlignment.CENTER);

            CellStyle styleCenter = workbook.createCellStyle();
            XSSFFont fontCenter = workbook.createFont();
            fontCenter.setFontHeight(12);
            styleCenter.setFont(fontCenter);
            styleCenter.setAlignment(HorizontalAlignment.CENTER);

            row = sheet.createRow(0);
            Cell cell = row.createCell(7);
            cell.setCellValue("Phiếu Nhập Kho");
            cell.setCellStyle(style);

            row3 = sheet.createRow(1);
            Cell cellDE = row3.createCell(14);
            cellDE.setCellValue("Date: " + t.timeNowUser());
            Cell cellUS = row3.createCell(0);
            cellUS.setCellValue("Name: " + loginUser.getName());

            row4 = sheet.createRow(2);
            Cell cellNU = row4.createCell(0);
            cellNU.setCellValue("Phone Number: " + loginUser.getPhoneNumber());

            row1 = sheet.createRow(4);
            Cell cell01 = row1.createCell(0);
            cell01.setCellValue("STT");
            styleSeceond.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
            styleSeceond.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell01.setCellStyle(styleSeceond);
            Cell cell1 = row1.createCell(2);
            cell1.setCellValue("productID");
            styleSeceond.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
            styleSeceond.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell1.setCellStyle(styleSeceond);
            Cell cell3 = row1.createCell(4);
            cell3.setCellValue("Name");
            styleSeceond.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
            styleSeceond.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell3.setCellStyle(styleSeceond);
            Cell cell4 = row1.createCell(6);
            cell4.setCellValue("Brand");
            styleSeceond.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
            styleSeceond.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell4.setCellStyle(styleSeceond);
            Cell cell5 = row1.createCell(8);
            cell5.setCellValue("Model");
            styleSeceond.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
            styleSeceond.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell5.setCellStyle(styleSeceond);
            Cell cell6 = row1.createCell(10);
            cell6.setCellValue("receiptDetailID");
            styleSeceond.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
            styleSeceond.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell6.setCellStyle(styleSeceond);
            Cell cell7 = row1.createCell(12);
            cell7.setCellValue("quantityInBill");
            styleSeceond.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
            styleSeceond.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell7.setCellStyle(styleSeceond);
            Cell cell8 = row1.createCell(14);
            cell8.setCellValue("quantityInShipping");
            styleSeceond.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
            styleSeceond.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell8.setCellStyle(styleSeceond);
            Cell cell9 = row1.createCell(16);
            cell9.setCellValue("solution");
            styleSeceond.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
            styleSeceond.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell9.setCellStyle(styleSeceond);

            Cell cell20 = row1.createCell(1);
            styleSeceond.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
            styleSeceond.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell20.setCellStyle(styleSeceond);
            Cell cell21 = row1.createCell(3);
            styleSeceond.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
            styleSeceond.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell21.setCellStyle(styleSeceond);
            Cell cell22 = row1.createCell(5);
            styleSeceond.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
            styleSeceond.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell22.setCellStyle(styleSeceond);
            Cell cell23 = row1.createCell(7);
            styleSeceond.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
            styleSeceond.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell23.setCellStyle(styleSeceond);
            Cell cell24 = row1.createCell(9);
            styleSeceond.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
            styleSeceond.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell24.setCellStyle(styleSeceond);
            Cell cell25 = row1.createCell(11);
            styleSeceond.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
            styleSeceond.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell25.setCellStyle(styleSeceond);
            Cell cell26 = row1.createCell(13);
            styleSeceond.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
            styleSeceond.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell26.setCellStyle(styleSeceond);
            Cell cell27 = row1.createCell(15);
            styleSeceond.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
            styleSeceond.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell27.setCellStyle(styleSeceond);

            int receipt = dao.getReceiptID();
            List<UserFakeList> list = dao.getListReceiptFull(receipt);
            if (list != null) {
                if (list.size() > 0) {
                    int i = 1;
                    for (UserFakeList userFakeList : list) {
                        if (i % 2 == 0) {
                            row2 = sheet.createRow((short) i + 4);
                            cellL = row2.createCell((short) 0);
                            cellL.setCellValue(i);
                            styleLast.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
                            styleLast.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                            cellL.setCellStyle(styleLast);

                            cellA = row2.createCell((short) 2);
                            cellA.setCellValue(userFakeList.getProductID());
                            styleLast.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
                            styleLast.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                            cellA.setCellStyle(styleLast);

                            cellC = row2.createCell((short) 4);
                            cellC.setCellValue(userFakeList.getName());
                            styleLast.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
                            styleLast.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                            cellC.setCellStyle(styleLast);

                            cellD = row2.createCell((short) 6);
                            cellD.setCellValue(userFakeList.getBrand());
                            styleLast.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
                            styleLast.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                            cellD.setCellStyle(styleLast);

                            cellE = row2.createCell((short) 8);
                            cellE.setCellValue(userFakeList.getModel());
                            styleLast.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
                            styleLast.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                            cellE.setCellStyle(styleLast);

                            cellF = row2.createCell((short) 10);
                            cellF.setCellValue(userFakeList.getReceiptDetailID());
                            styleLast.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
                            styleLast.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                            cellF.setCellStyle(styleLast);

                            cellG = row2.createCell((short) 12);
                            cellG.setCellValue(userFakeList.getQuantityInBill());
                            styleLast.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
                            styleLast.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                            cellG.setCellStyle(styleLast);

                            cellH = row2.createCell((short) 14);
                            cellH.setCellValue(userFakeList.getQuantityInShipping());
                            styleLast.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
                            styleLast.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                            cellH.setCellStyle(styleLast);

                            cellJ = row2.createCell((short) 16);
                            cellJ.setCellValue(userFakeList.getSolution());
                            styleLast.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
                            styleLast.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                            cellJ.setCellStyle(styleLast);

                            Cell cell30 = row2.createCell(1);
                            styleLast.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
                            styleLast.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                            cell30.setCellStyle(styleLast);
                            Cell cell31 = row2.createCell(3);
                            styleLast.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
                            styleLast.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                            cell31.setCellStyle(styleLast);
                            Cell cell32 = row2.createCell(5);
                            styleLast.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
                            styleLast.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                            cell32.setCellStyle(styleLast);
                            Cell cell33 = row2.createCell(7);
                            styleLast.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
                            styleLast.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                            cell33.setCellStyle(styleLast);
                            Cell cell34 = row2.createCell(9);
                            styleLast.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
                            styleLast.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                            cell34.setCellStyle(styleLast);
                            Cell cell35 = row2.createCell(11);
                            styleLast.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
                            styleLast.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                            cell35.setCellStyle(styleLast);
                            Cell cell36 = row2.createCell(13);
                            styleLast.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
                            styleLast.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                            cell36.setCellStyle(styleLast);
                            Cell cell37 = row2.createCell(15);
                            styleLast.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
                            styleLast.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                            cell37.setCellStyle(styleLast);
                        } else {
                            row2 = sheet.createRow((short) i + 4);
                            cellL = row2.createCell((short) 0);
                            cellL.setCellValue(i);
                            cellL.setCellStyle(styleCenter);
                            cellA = row2.createCell((short) 2);
                            cellA.setCellValue(userFakeList.getProductID());
                            cellA.setCellStyle(styleCenter);
                            cellC = row2.createCell((short) 4);
                            cellC.setCellValue(userFakeList.getName());
                            cellC.setCellStyle(styleCenter);
                            cellD = row2.createCell((short) 6);
                            cellD.setCellValue(userFakeList.getBrand());
                            cellD.setCellStyle(styleCenter);
                            cellE = row2.createCell((short) 8);
                            cellE.setCellValue(userFakeList.getModel());
                            cellE.setCellStyle(styleCenter);
                            cellF = row2.createCell((short) 10);
                            cellF.setCellValue(userFakeList.getReceiptDetailID());
                            cellF.setCellStyle(styleCenter);
                            cellG = row2.createCell((short) 12);
                            cellG.setCellValue(userFakeList.getQuantityInBill());
                            cellG.setCellStyle(styleCenter);
                            cellH = row2.createCell((short) 14);
                            cellH.setCellValue(userFakeList.getQuantityInShipping());
                            cellH.setCellStyle(styleCenter);
                            cellJ = row2.createCell((short) 16);
                            cellJ.setCellValue(userFakeList.getSolution());
                            cellJ.setCellStyle(styleCenter);
                        }
                        i++;
                    }
                }
            }

            List<UserFakeList> listT = dao.getListReceiptFull(receipt);
            if (listT != null) {
                if (listT.size() > 0) {
                    for (int i = 0; i < listT.size(); i++) {
                        row3 = sheet.createRow(3);
                        Cell cellLL = row3.createCell(0);
                        cellLL.setCellValue("Accountant: " + loginUser.getAccountID());
                        Cell cellUSK = row3.createCell(14);
                        cellUSK.setCellValue("StockKeeper: " + listT.get(i).getStockKeeperID());

                        int totalBill = dao.sumBill(listT.get(i).getReceiptID());
                        int totalShip = dao.sumShip(listT.get(i).getReceiptID());
                        row7 = sheet.createRow((short) listT.size() + 1 + 5);
                        cellM = row7.createCell((short) 0);
                        cellM.setCellValue("Note: " + listT.get(i).getNote());

                        row5 = sheet.createRow((short) listT.size() + 2 + 5);
                        cellM = row5.createCell((short) 0);
                        cellM.setCellValue("Total Bill: " + totalBill);

                        row6 = sheet.createRow((short) listT.size() + 3 + 5);
                        cellN = row6.createCell((short) 0);
                        cellN.setCellValue("Total Shipping: " + totalShip);
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
