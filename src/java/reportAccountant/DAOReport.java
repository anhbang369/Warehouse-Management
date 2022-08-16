/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportAccountant;

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
public class DAOReport {

    private static final String SHOW_PRODUCT = "SELECT productID,brand FROM Product";
    private static final String IMPORT = "SELECT SUM(rd.quantityInBill) AS Import FROM Receipt AS r,ReceiptDetail AS rd WHERE r.receiptID=rd.receiptID AND rd.productID = ? AND r.inputDate BETWEEN ? AND ?";
    private static final String EXPORT = "SELECT SUM(od.quantity) AS Export FROM Orders AS o,OrderDetail AS od, Issue AS i WHERE i.orderID=o.orderID AND o.orderID=od.orderID AND i.orderID=od.orderID AND od.productID=? AND i.DateP BETWEEN ? AND ?";
    private static final String TOTAL_INPUT_BEGIN = "SELECT SUM(rd.quantityInBill) AS quantity FROM Receipt as r, ReceiptDetail as rd WHERE r.receiptID = rd.receiptID AND r.inputDate < ? AND rd.productID= ?";
    private static final String TOTAL_OUTPUT_BEGIN = "SELECT SUM(od.quantity) AS quantity FROM Issue AS i,Orders AS o, OrderDetail AS od WHERE i.orderID = o.orderID AND o.orderID=od.orderID AND i.DateP < ? AND od.productID = ?";
    private static final String TOTAL_INPUT_END = "SELECT SUM(rd.quantityInBill) AS quantity FROM Receipt AS r, ReceiptDetail AS rd WHERE r.receiptID=rd.receiptID AND rd.productID = ? AND r.inputDate BETWEEN ? AND ?";
    private static final String TOTAL_OUTPUT_END = "SELECT SUM(od.quantity) AS quantity FROM Issue AS i, Orders AS o, OrderDetail AS od WHERE i.orderID=o.orderID AND o.orderID=od.orderID AND i.orderID=od.orderID AND od.productID= ? AND i.DateP BETWEEN ? AND ? ";
    private static final String PRODUCT_NAME = "SELECT productID, name, brand FROM Product WHERE productID like ? AND name like ?";
    
    public List<UserProductReport> getListShowProduct() throws SQLException {
        List<UserProductReport> listProduct = new ArrayList<>();
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(SHOW_PRODUCT);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String brand = rs.getString("brand");
                    listProduct.add(new UserProductReport(productID, brand));

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
        return listProduct;
    }

    public int importt(String productID,String value1, String value2) throws SQLException {
        int check = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(IMPORT);
                ptm.setString(1, productID);
                ptm.setString(2, value1);
                ptm.setString(3, value2);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    int Import = Integer.parseInt(rs.getString("Import"));
                    check = Import;
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

    public int exportt(String productID,String value1, String value2) throws SQLException {
        int check = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(EXPORT);
                ptm.setString(1, productID);
                ptm.setString(2, value1);
                ptm.setString(3, value2);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    int Export = Integer.parseInt(rs.getString("Export"));
                    check = Export;
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
    
    
    public int quantityBeginnI(String productID,String value) throws SQLException {
        int check = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(TOTAL_INPUT_BEGIN);
                ptm.setString(1, value);
                ptm.setString(2, productID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    int TotalI = Integer.parseInt(rs.getString("quantity"));
                    check = TotalI;
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
    
    public int quantityBeginnO(String productID,String value1) throws SQLException {
        int check = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(TOTAL_OUTPUT_BEGIN);               
                ptm.setString(1, value1);
                ptm.setString(2, productID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    int TotalO = Integer.parseInt(rs.getString("quantity"));
                    check = TotalO;
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
    
    public int quantityEndI(String productID,String value2, String value) throws SQLException {
        int check = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(TOTAL_INPUT_END);
                ptm.setString(1, productID);
                ptm.setString(2, value2);
                ptm.setString(3, value);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    int TotalI = Integer.parseInt(rs.getString("quantity"));
                    check = TotalI;
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
    
    public int quantityEndO(String productID,String value2, String value) throws SQLException {
        int check = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(TOTAL_OUTPUT_END);
                ptm.setString(1, productID);
                ptm.setString(2, value2);
                ptm.setString(3, value);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    int TotalO = Integer.parseInt(rs.getString("quantity"));
                    check = TotalO;
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
    
    
    public List<UserProductName> getListShowProductName(String productI, String nam) throws SQLException {
        List<UserProductName> listProduct = new ArrayList<>();
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(PRODUCT_NAME);
                ptm.setString(1, "%" + productI + "%");
                ptm.setString(2, "%" + nam + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String name = rs.getString("name");
                    String brand = rs.getString("brand");
                    listProduct.add(new UserProductName(productID, name,brand));

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
        return listProduct;
    }
    
}
