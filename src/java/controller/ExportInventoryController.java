/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import inventoryAccountant.DAOInventory;
import inventoryAccountant.UserInventoryFull;
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
import time.Timer;
import user.UserDTO;

/**
 *
 * @author 84348
 */
@WebServlet(name = "ExportInventoryController", urlPatterns = {"/ExportInventoryController"})
public class ExportInventoryController extends HttpServlet {

    private static final String ERROR = "MainController?action=ShowInventory";
    private static final String SUCCESS = "MainController?action=SearchInventoryAlpha&productID=&name=";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            Timer t = new Timer();
            String a = t.dateFull();
            DAOInventory dao = new DAOInventory();
            FileOutputStream file = new FileOutputStream("C:/Users/quang/Documents/Zalo Received Files/SWP391_Again/SWP391_Again6/" + a + ".xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("name");
            XSSFRow row;
            XSSFRow row1;
            XSSFRow row2;
            XSSFRow row3;
            XSSFRow row4;
            XSSFCell cellA;
            XSSFCell cellB;
            XSSFCell cellC;
            XSSFCell cellD;
            XSSFCell cellE;
            XSSFCell cellDL;
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
            Cell cell0 = row.createCell(3);
            cell0.setCellValue("Phiếu Nhập Báo Cáo Kho");
            cell0.setCellStyle(style);

            row3 = sheet.createRow(1);
            Cell cellDE = row3.createCell(9);
            cellDE.setCellValue("Date: " + t.timeNowUser());
            Cell cellUS = row3.createCell(0);
            cellUS.setCellValue("Name: " + loginUser.getName());

            row4 = sheet.createRow(2);
            Cell cellNU = row4.createCell(0);
            cellNU.setCellValue("Phone Number: " + loginUser.getPhoneNumber());

            row1 = sheet.createRow(4);
            Cell cell3 = row1.createCell(0);
            cell3.setCellValue("STT");
            styleSeceond.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
            styleSeceond.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell3.setCellStyle(styleSeceond);
            Cell cell4 = row1.createCell(1);
            cell4.setCellValue("productID");
            styleSeceond.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
            styleSeceond.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell4.setCellStyle(styleSeceond);
            Cell cell5 = row1.createCell(3);
            cell5.setCellValue("quality");
            styleSeceond.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
            styleSeceond.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell5.setCellStyle(styleSeceond);
            Cell cell6 = row1.createCell(5);
            cell6.setCellValue("quantityInChecking");
            styleSeceond.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
            styleSeceond.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell6.setCellStyle(styleSeceond);
            Cell cell7 = row1.createCell(7);
            cell7.setCellValue("quantity");
            styleSeceond.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
            styleSeceond.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell7.setCellStyle(styleSeceond);
            Cell cell8 = row1.createCell(9);
            cell8.setCellValue("Note");
            styleSeceond.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
            styleSeceond.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell8.setCellStyle(styleSeceond);

            Cell cell20 = row1.createCell(2);
            styleSeceond.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
            styleSeceond.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell20.setCellStyle(styleSeceond);
            Cell cell21 = row1.createCell(4);
            styleSeceond.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
            styleSeceond.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell21.setCellStyle(styleSeceond);
            Cell cell22 = row1.createCell(6);
            styleSeceond.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
            styleSeceond.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell22.setCellStyle(styleSeceond);
            Cell cell23 = row1.createCell(8);
            styleSeceond.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
            styleSeceond.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell23.setCellStyle(styleSeceond);
            Cell cell24 = row1.createCell(10);
            styleSeceond.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
            styleSeceond.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell24.setCellStyle(styleSeceond);

            int report = dao.getReportID();
            List<UserInventoryFull> list = dao.getListShowReportFull(report);
            if (list != null) {
                int i = 1;
                if (list.size() > 0) {
                    for (UserInventoryFull userInventoryFull : list) {
                        if (i % 2 == 0) {
                            row2 = sheet.createRow((short) i + 4);
                            cellE = row2.createCell((short) 0);
                            cellE.setCellValue(i);
                            styleLast.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
                            styleLast.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                            cellE.setCellStyle(styleLast);

                            cellA = row2.createCell((short) 1);
                            cellA.setCellValue(userInventoryFull.getProductID());
                            styleLast.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
                            styleLast.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                            cellA.setCellStyle(styleLast);

                            cellB = row2.createCell((short) 3);
                            cellB.setCellValue(userInventoryFull.getQuality());
                            styleLast.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
                            styleLast.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                            cellB.setCellStyle(styleLast);

                            cellC = row2.createCell((short) 5);
                            cellC.setCellValue(userInventoryFull.getQuantityInChecking());
                            styleLast.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
                            styleLast.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                            cellC.setCellStyle(styleLast);

                            cellD = row2.createCell((short) 7);
                            cellD.setCellValue(userInventoryFull.getQuantity());
                            styleLast.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
                            styleLast.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                            cellD.setCellStyle(styleLast);

                            cellDL = row2.createCell((short) 9);
                            cellDL.setCellValue(userInventoryFull.getNote());
                            styleLast.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
                            styleLast.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                            cellDL.setCellStyle(styleLast);

                            Cell cellF = row2.createCell((short) 2);
                            styleLast.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
                            styleLast.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                            cellF.setCellStyle(styleLast);
                            Cell cellG = row2.createCell((short) 4);
                            styleLast.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
                            styleLast.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                            cellG.setCellStyle(styleLast);
                            Cell cellH = row2.createCell((short) 6);
                            styleLast.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
                            styleLast.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                            cellH.setCellStyle(styleLast);
                            Cell cellJ = row2.createCell((short) 8);
                            styleLast.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
                            styleLast.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                            cellJ.setCellStyle(styleLast);
                            Cell cellK = row2.createCell((short) 10);
                            styleLast.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
                            styleLast.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                            cellK.setCellStyle(styleLast);
                        } else {
                            row2 = sheet.createRow((short) i + 4);
                            cellE = row2.createCell((short) 0);
                            cellE.setCellValue(i);
                            cellE.setCellStyle(styleCenter);
                            cellA = row2.createCell((short) 1);
                            cellA.setCellValue(userInventoryFull.getProductID());
                            cellA.setCellStyle(styleCenter);
                            cellB = row2.createCell((short) 3);
                            cellB.setCellValue(userInventoryFull.getQuality());
                            cellB.setCellStyle(styleCenter);
                            cellC = row2.createCell((short) 5);
                            cellC.setCellValue(userInventoryFull.getQuantityInChecking());
                            cellC.setCellStyle(styleCenter);
                            cellD = row2.createCell((short) 7);
                            cellD.setCellValue(userInventoryFull.getQuantity());
                            cellD.setCellStyle(styleCenter);
                            cellDL = row2.createCell((short) 9);
                            cellDL.setCellValue(userInventoryFull.getNote());
                            cellDL.setCellStyle(styleCenter);
                        }
                        i++;
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
