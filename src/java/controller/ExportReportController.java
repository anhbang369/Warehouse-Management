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
import reportAccountant.UserReport;
import time.Timer;
import user.UserDTO;

/**
 *
 * @author 84348
 */
@WebServlet(name = "ExportReportController", urlPatterns = {"/ExportReportController"})
public class ExportReportController extends HttpServlet {

    private static final String ERROR = "report.jsp";
    private static final String SUCCESS = "report.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String errorp = (String) request.getAttribute("DATE_PO");
            HttpSession session = request.getSession();
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            Timer t = new Timer();
            String a = t.dateFull();
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
            XSSFCell cellF;
            XSSFCell cellG;
            XSSFCell cellH;
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
            Cell cell0 = row.createCell(6);
            cell0.setCellValue("Phiếu Báo Cáo Kho");
            cell0.setCellStyle(style);

            row3 = sheet.createRow(1);
            Cell cellDE = row3.createCell(12);
            cellDE.setCellValue("Date: " + t.timeNowUser());
            Cell cellUS = row3.createCell(0);
            cellUS.setCellValue("Name: " + loginUser.getName());

            row4 = sheet.createRow(2);
            Cell cellNU = row4.createCell(0);
            cellNU.setCellValue("Phone Number: " + loginUser.getPhoneNumber());
            
            Cell cellNUP = row4.createCell(6);
            cellNUP.setCellValue(errorp);

            row1 = sheet.createRow(4);
            Cell cell09 = row1.createCell(0);
            cell09.setCellValue("STT");
            styleSeceond.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
            styleSeceond.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell09.setCellStyle(styleSeceond);

            Cell cell1 = row1.createCell(1);
            cell1.setCellValue("ProductID");
            styleSeceond.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
            styleSeceond.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell1.setCellStyle(styleSeceond);
            Cell cell2 = row1.createCell(3);
            cell2.setCellValue("Name");
            styleSeceond.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
            styleSeceond.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell2.setCellStyle(styleSeceond);
            Cell cell3 = row1.createCell(5);
            cell3.setCellValue("Brand");
            styleSeceond.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
            styleSeceond.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell3.setCellStyle(styleSeceond);
            Cell cell4 = row1.createCell(7);
            cell4.setCellValue("Quantity Begin");
            styleSeceond.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
            styleSeceond.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell4.setCellStyle(styleSeceond);
            Cell cell5 = row1.createCell(9);
            cell5.setCellValue("Quantity End");
            styleSeceond.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
            styleSeceond.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell5.setCellStyle(styleSeceond);
            Cell cell6 = row1.createCell(11);
            cell6.setCellValue("Import");
            styleSeceond.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
            styleSeceond.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell6.setCellStyle(styleSeceond);
            Cell cell7 = row1.createCell(13);
            cell7.setCellValue("Export");
            styleSeceond.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
            styleSeceond.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell7.setCellStyle(styleSeceond);

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
            Cell cell25 = row1.createCell(12);
            styleSeceond.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
            styleSeceond.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell25.setCellStyle(styleSeceond);

            List<UserReport> list = (List<UserReport>) session.getAttribute("LIST_REPORT");
            if (list != null) {
                if (list.size() > 0) {
                    int i = 1;
                    for (UserReport tm : list) {
                        if (i % 2 == 0) {
                            row2 = sheet.createRow((short) i + 4);
                            cellH = row2.createCell((short) 0);
                            cellH.setCellValue(i);
                            styleLast.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
                            styleLast.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                            cellH.setCellStyle(styleLast);

                            cellA = row2.createCell((short) 1);
                            cellA.setCellValue(tm.getProductID());
                            styleLast.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
                            styleLast.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                            cellA.setCellStyle(styleLast);

                            cellB = row2.createCell((short) 3);
                            cellB.setCellValue(tm.getName());
                            styleLast.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
                            styleLast.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                            cellB.setCellStyle(styleLast);

                            cellC = row2.createCell((short) 5);
                            cellC.setCellValue(tm.getBrand());
                            styleLast.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
                            styleLast.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                            cellC.setCellStyle(styleLast);

                            cellD = row2.createCell((short) 7);
                            cellD.setCellValue(tm.getQuantityBegin());
                            styleLast.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
                            styleLast.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                            cellD.setCellStyle(styleLast);

                            cellE = row2.createCell((short) 9);
                            cellE.setCellValue(tm.getQuantityEnd());
                            styleLast.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
                            styleLast.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                            cellE.setCellStyle(styleLast);

                            cellF = row2.createCell((short) 11);
                            cellF.setCellValue(tm.getImport());
                            styleLast.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
                            styleLast.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                            cellF.setCellStyle(styleLast);

                            cellG = row2.createCell((short) 13);
                            cellG.setCellValue(tm.getExport());
                            styleLast.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
                            styleLast.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                            cellG.setCellStyle(styleLast);

                            Cell cell30 = row2.createCell(2);
                            styleLast.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
                            styleLast.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                            cell30.setCellStyle(styleLast);
                            Cell cell31 = row2.createCell(4);
                            styleLast.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
                            styleLast.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                            cell31.setCellStyle(styleLast);
                            Cell cell32 = row2.createCell(6);
                            styleLast.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
                            styleLast.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                            cell32.setCellStyle(styleLast);
                            Cell cell33 = row2.createCell(8);
                            styleLast.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
                            styleLast.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                            cell33.setCellStyle(styleLast);
                            Cell cell44 = row2.createCell(10);
                            styleLast.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
                            styleLast.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                            cell44.setCellStyle(styleLast);
                            Cell cell35 = row2.createCell(12);
                            styleLast.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
                            styleLast.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                            cell35.setCellStyle(styleLast);

                        } else {
                            row2 = sheet.createRow((short) i + 4);
                            cellH = row2.createCell((short) 0);
                            cellH.setCellValue(i);
                            cellH.setCellStyle(styleCenter);
                            cellA = row2.createCell((short) 1);
                            cellA.setCellValue(tm.getProductID());
                            cellA.setCellStyle(styleCenter);
                            cellB = row2.createCell((short) 3);
                            cellB.setCellValue(tm.getName());
                            cellB.setCellStyle(styleCenter);
                            cellC = row2.createCell((short) 5);
                            cellC.setCellValue(tm.getBrand());
                            cellC.setCellStyle(styleCenter);
                            cellD = row2.createCell((short) 7);
                            cellD.setCellValue(tm.getQuantityBegin());
                            cellD.setCellStyle(styleCenter);
                            cellE = row2.createCell((short) 9);
                            cellE.setCellValue(tm.getQuantityEnd());
                            cellE.setCellStyle(styleCenter);
                            cellF = row2.createCell((short) 11);
                            cellF.setCellValue(tm.getImport());
                            cellF.setCellStyle(styleCenter);
                            cellG = row2.createCell((short) 13);
                            cellG.setCellValue(tm.getExport());
                            cellG.setCellStyle(styleCenter);
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
