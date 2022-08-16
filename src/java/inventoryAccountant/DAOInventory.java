/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventoryAccountant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author 84348
 */
public class DAOInventory {
    private static final String SEARCH_INVENTORY = "SELECT p.productID,p.name,rd.quality,rd.quantityInChecking,r.checkingDate,rd.note,rd.quantity FROM Product as p,ReportDetail as rd,Report as r WHERE p.productID=rd.productID AND rd.reportID=r.reportID AND r.checkingDate BETWEEN ? AND ?";
    private static final String INSERT_REPORT = "INSERT INTO Report(checkingDate) VALUES(?)";
    private static final String INSERT_REPORT_DETAIL = "INSERT INTO ReportDetail(reportID,productID,quality,quantityInChecking,quantity,note) VALUES(?,?,?,?,?,?)";
    private static final String GET_REPORT_ID = "SELECT TOP 1 * from Report ORDER BY reportID DESC";
    private static final String GET_QUANTITY_PRODUCT = "SELECT quantity FROM Product WHERE productID=?";
    private static final String SEARCH_INVENTORY_ALPHA = "SELECT p.productID,p.name,rd.quality,rd.quantityInChecking,r.checkingDate,rd.note,rd.quantity FROM Product as p,ReportDetail as rd,Report as r WHERE p.productID=rd.productID AND rd.reportID=r.reportID AND p.productID like ? AND p.name like ? ";
    private static final String SHOW_REPORT_FULL = "SELECT r.reportID,r.checkingDate,rd.reportDetailID,rd.productID,rd.quality,rd.quantityInChecking,rd.quantity,rd.note FROM Report AS r, ReportDetail AS rd WHERE r.reportID=rd.reportID AND r.reportID=?";
    
    public List<UserInventory> getListSearchInventory(String searchInventory, String searchInventoryM) throws SQLException {
        List<UserInventory> listInventory = new ArrayList<>();
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(SEARCH_INVENTORY);
                ptm.setString(1, searchInventory);
                ptm.setString(2, searchInventoryM);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String name = rs.getString("name");
                    String checkingDate = rs.getString("checkingDate");
                    int quantityInChecking = Integer.parseInt(rs.getString("quantityInChecking"));
                    int quality = Integer.parseInt(rs.getString("quality"));
                    String note = rs.getString("note");
                    int quatity = Integer.parseInt(rs.getString("quantity"));
                    listInventory.add(new UserInventory(productID, name, checkingDate, quantityInChecking, quality, quatity, note));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return listInventory;
    }
    
    public int getReportID() throws SQLException {
        int check = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_REPORT_ID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    int reportID = Integer.parseInt(rs.getString("reportID"));
                    check = reportID;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
    
    public int getQuantityInProduct(String productID) throws SQLException {
        int check = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_QUANTITY_PRODUCT);
                ptm.setString(1, productID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    int quantity = Integer.parseInt(rs.getString("quantity"));
                    check = quantity;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
    
    public boolean createReport(UserReportInventory userReport) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT_REPORT);
                ptm.setString(1, userReport.getCheckingDate());
                check = ptm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
            
        }
        return check;
    }
    
    public boolean createReportDetail(UserInventoryVirtual reportDetail) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT_REPORT_DETAIL);
                ptm.setInt(1, reportDetail.getReportID());
                ptm.setString(2, reportDetail.getProductID());
                ptm.setInt(3, reportDetail.getQuality());
                ptm.setInt(4, reportDetail.getQuantityInChecking());
                ptm.setInt(5, reportDetail.getQuantity());
                ptm.setString(6, reportDetail.getNote());
                check = ptm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
            
        }
        return check;
    }
    
    public List<UserInventory> getListSearchInventoryAlpha(String search, String search1) throws SQLException {
        List<UserInventory> listInventory = new ArrayList<>();
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(SEARCH_INVENTORY_ALPHA);
                ptm.setString(1, "%" + search + "%");
                ptm.setString(2, "%" + search1 + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String name = rs.getString("name");
                    String checkingDate = rs.getString("checkingDate");
                    int quantityInChecking = Integer.parseInt(rs.getString("quantityInChecking"));
                    int quality = Integer.parseInt(rs.getString("quality"));
                    String note = rs.getString("note");
                    int quatity = Integer.parseInt(rs.getString("quantity"));
                    listInventory.add(new UserInventory(productID, name, checkingDate, quantityInChecking, quality, quatity, note));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return listInventory;
    }
    
    public List<UserInventoryFull> getListShowReportFull(int report) throws SQLException {
        List<UserInventoryFull> listInventory = new ArrayList<>();
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(SHOW_REPORT_FULL);
                ptm.setInt(1, report);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int reportID = Integer.parseInt(rs.getString("reportID"));
                    String checkingDate = rs.getString("checkingDate");
                    int reportDetailID = Integer.parseInt(rs.getString("reportDetailID"));
                    String productID = rs.getString("productID");
                    int quality = Integer.parseInt(rs.getString("quality"));
                    int quantityInChecking = Integer.parseInt(rs.getString("quantityInChecking"));
                    int quantity = Integer.parseInt(rs.getString("quantity"));
                    String note = rs.getString("note");
                    listInventory.add(new UserInventoryFull(reportID, checkingDate, reportDetailID, productID, quality, quantityInChecking, quantity, note));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return listInventory;
    }
   
}
