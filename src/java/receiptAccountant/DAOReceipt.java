/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package receiptAccountant;

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
public class DAOReceipt {
    //private static final String CHECK_EXIST_ACCOUNT = "SELECT name,role,status,phoneNumber FROM Account WHERE accountID like ?";
    private static final String CHECK_EXIST_ORDERID = "SELECT customerName,address,phoneNumber,status,note,deliveryDate,sellerID FROM Orders WHERE orderID like ?";
    private static final String ADD_RECEIPT_DETAIL = "INSERT INTO ReceiptDetail(quantityInBill, quantityInShipping, productID, receiptID,solution) VALUES(?,?,?,?,?)";
    private static final String GET_RECEIPTID = "SELECT top 1 * FROM Receipt order by receiptID desc";
    private static final String SEARCH_DETAIL = "SELECT pt.productID, pt.model,pt.brand,pt.type,rd.quantityInBill,rd.quantityInShipping,r.inputDate,r.status,r.totalQuantity,r.note,r.stockKeeperID,r.accountantID,rd.receiptDetailID,rd.solution FROM Receipt as r,ReceiptDetail as rd,Product as pt WHERE r.receiptID=rd.receiptID AND rd.productID=pt.productID AND r.receiptID like ?";
    private static final String ADD_RECEIPT = "INSERT INTO Receipt(inputDate, status, totalQuantity, note, accountantID, stockKeeperID) VALUES(?,?,?,?,?,?)";
    private static final String CHECK_DUPLICATE = "SELECT name,role,status,phoneNumber,password FROM Account WHERE accountID=?";
    private static final String SEARCH = "SELECT receiptID,inputDate,status,totalQuantity,note,accountantID,stockKeeperID FROM Receipt WHERE receiptID like ? ";
    private static final String SHOW = "SELECT receiptID,inputDate,status,totalQuantity,note,accountantID,stockKeeperID FROM Receipt";
    private static final String SEARCH_RECEIPT_DATE = "SELECT receiptID,inputDate,status,totalQuantity,note,accountantID,stockKeeperID FROM Receipt WHERE inputDate BETWEEN ? AND ?";
    private static final String UPDATE = "UPDATE Receipt SET note=? WHERE receiptID=?";
    private static final String SEARCH_DETAIL_RECEIPT = "SELECT receiptDetailID ,quantityInBill,quantityInShipping,productID,receiptID,solution  FROM ReceiptDetail WHERE receiptID=?";
    private static final String SEARCH_DETAIL_RECEIPT_FULL = "SELECT pt.productID, pt.model,pt.brand,pt.name,rd.quantityInBill,rd.quantityInShipping,r.inputDate,r.status,r.totalQuantity,r.note,r.stockKeeperID,r.accountantID,rd.receiptDetailID,rd.solution,r.receiptID FROM Receipt as r,ReceiptDetail as rd,Product as pt WHERE r.receiptID=rd.receiptID AND rd.productID=pt.productID AND r.receiptID = ?";
    private static final String SUM_BILL = "SELECT SUM(quantityInBill) AS quantity FROM ReceiptDetail WHERE receiptID=?";
    private static final String SUM_SHIP = "SELECT SUM(quantityInShipping) AS quantity FROM ReceiptDetail WHERE receiptID=?";
    private static final String SEARCH_STATUS = "SELECT receiptID,inputDate,status,totalQuantity,note,accountantID,stockKeeperID FROM Receipt WHERE status = ? ";
    public boolean checkExistOrderID(int orderID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_EXIST_ORDERID);
                ptm.setInt(1, orderID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    check = true;
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
    
    public boolean createReceiptDetail(UserReceiptDetail receiptDetail) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(ADD_RECEIPT_DETAIL);
                ptm.setInt(1, receiptDetail.getQuantityInBill());
                ptm.setInt(2, receiptDetail.getQuantityInShipping());
                ptm.setString(3, receiptDetail.getProductID());
                ptm.setInt(4, receiptDetail.getReceiptID());
                ptm.setString(5, receiptDetail.getSolution());
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
    
    public int getReceiptID() throws SQLException {
        int check = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_RECEIPTID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    int receiptID = Integer.parseInt(rs.getString("receiptID"));
                    check = receiptID;
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
    
    public List<UserFakeList> getListShowDetail(int a) throws SQLException {
        List<UserFakeList> listFake = new ArrayList<>();
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(SEARCH_DETAIL);
                ptm.setString(1, "%" + a + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String model = rs.getString("model");
                    String brand = rs.getString("brand");
                    String type = rs.getString("type");
                    int quantityInBill = Integer.parseInt(rs.getString("quantityInBill"));
                    int quantityInShipping = Integer.parseInt(rs.getString("quantityInShipping"));
                    String inputDate = rs.getString("inputDate");
                    String status = rs.getString("status");
                    int totalQuantity = Integer.parseInt(rs.getString("totalQuantity"));
                    String note = rs.getString("note");
                    String accountantID = rs.getString("accountantID");
                    String stockKeeperID = rs.getString("stockKeeperID");
                    int receiptDetailID = Integer.parseInt(rs.getString("receiptDetailID"));
                    String solution = rs.getString("solution");
                    
                    listFake.add(new UserFakeList(productID, model, brand, type, quantityInBill, quantityInShipping, receiptDetailID, inputDate, status, totalQuantity, note, accountantID, stockKeeperID, solution, receiptDetailID));
                    
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
        return listFake;
    }
    
    public boolean createReceipt(UserReceiptVirtual receipt) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(ADD_RECEIPT);
                ptm.setString(1, receipt.getInputDate());
                ptm.setString(2, receipt.getStatus());
                ptm.setInt(3, receipt.getTotalQuantity());
                ptm.setString(4, receipt.getNote());
                ptm.setString(5, receipt.getAccountantID());
                ptm.setString(6, receipt.getStockKeeperID());
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
    
    public boolean checkDupAccountantID(String accountantID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_DUPLICATE);
                ptm.setString(1, accountantID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    check = true;
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

    public boolean checkDupStockKeeperID(String stockKeeperID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_DUPLICATE);
                ptm.setString(1, stockKeeperID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    check = true;
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
    
    public List<UserReceipt> getListUser(String search) throws SQLException {
        List<UserReceipt> listUser = new ArrayList<>();
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(SEARCH);
                ptm.setString(1, "%" + search + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int receiptID = Integer.parseInt(rs.getString("receiptID"));
                    String inputDate = rs.getString("inputDate");
                    String status = rs.getString("status");
                    int totalQuantity = Integer.parseInt(rs.getString("totalQuantity"));
                    String note = rs.getString("note");
                    String accountantID = rs.getString("accountantID");
                    String stockKeeperID = rs.getString("stockKeeperID");
                    listUser.add(new UserReceipt(receiptID, inputDate, status, totalQuantity, note, accountantID, stockKeeperID));

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
        return listUser;
    }
    
    public List<UserReceipt> getListReceipt() throws SQLException {
        List<UserReceipt> listReceipt = new ArrayList<>();
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(SHOW);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int receiptID = Integer.parseInt(rs.getString("receiptID"));
                    String inputDate = rs.getString("inputDate");
                    String status = rs.getString("status");
                    int totalQuantity = Integer.parseInt(rs.getString("totalQuantity"));
                    String note = rs.getString("note");
                    String accountantID = rs.getString("accountantID");
                    String stockKeeperID = rs.getString("stockKeeperID");
                    listReceipt.add(new UserReceipt(receiptID, inputDate, status, totalQuantity, note, accountantID, stockKeeperID));

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
        return listReceipt;
    }
    
    public List<UserReceipt> getListReceiptSearchDate(String search, String search1) throws SQLException {
        List<UserReceipt> listUser = new ArrayList<>();
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(SEARCH_RECEIPT_DATE);
                ptm.setString(1, search);
                ptm.setString(2, search1);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int receiptID = Integer.parseInt(rs.getString("receiptID"));
                    String inputDate = rs.getString("inputDate");
                    String status = rs.getString("status");
                    int totalQuantity = Integer.parseInt(rs.getString("totalQuantity"));
                    String note = rs.getString("note");
                    String accountantID = rs.getString("accountantID");
                    String stockKeeperID = rs.getString("stockKeeperID");
                    listUser.add(new UserReceipt(receiptID, inputDate, status, totalQuantity, note, accountantID, stockKeeperID));

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
        return listUser;
    }
    
    
    public boolean updateReceipt(UserReceiptUp user) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE);
                ptm.setString(1, user.getNote());
                ptm.setInt(2, user.getReceiptID());
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
    
    public List<UserReceiptDetailS> getListShowOrderDetail(int receipt) throws SQLException {
        List<UserReceiptDetailS> listDetail = new ArrayList<>();
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(SEARCH_DETAIL_RECEIPT);
                ptm.setInt(1,receipt);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int receiptDetailID = Integer.parseInt(rs.getString("receiptDetailID"));
                    int quantityInBill = Integer.parseInt(rs.getString("quantityInBill"));
                    int quantityInShipping = Integer.parseInt(rs.getString("quantityInShipping"));
                    String productID = rs.getString("productID");
                    String solution = rs.getString("solution");                  
                    listDetail.add(new UserReceiptDetailS(receiptDetailID,quantityInBill, quantityInShipping, productID, receipt, solution));
                    
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
        return listDetail;
    }
    
    public List<UserFakeList> getListReceiptFull(int a) throws SQLException {
        List<UserFakeList> listFake = new ArrayList<>();
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(SEARCH_DETAIL_RECEIPT_FULL);
                ptm.setInt(1, a);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String model = rs.getString("model");
                    String brand = rs.getString("brand");
                    String name = rs.getString("name");
                    int quantityInBill = Integer.parseInt(rs.getString("quantityInBill"));
                    int quantityInShipping = Integer.parseInt(rs.getString("quantityInShipping"));
                    String inputDate = rs.getString("inputDate");
                    String status = rs.getString("status");
                    int totalQuantity = Integer.parseInt(rs.getString("totalQuantity"));
                    String note = rs.getString("note");
                    String accountantID = rs.getString("accountantID");
                    String stockKeeperID = rs.getString("stockKeeperID");
                    int receiptDetailID = Integer.parseInt(rs.getString("receiptDetailID"));
                    String solution = rs.getString("solution");
                    int receiptID = Integer.parseInt(rs.getString("receiptID"));
                    
                    listFake.add(new UserFakeList(productID, model, brand, name, quantityInBill, quantityInShipping, receiptID, inputDate, status, totalQuantity, note, accountantID, stockKeeperID, solution, receiptDetailID));
                    
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
        return listFake;
    }
    
    public int sumBill(int a) throws SQLException {
        int check = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SUM_BILL);
                ptm.setInt(1, a);
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
    
    public int sumShip(int a) throws SQLException {
        int check = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SUM_SHIP);
                ptm.setInt(1, a);
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
    
    public List<UserReceipt> getListSearchStatusAccount(String search) throws SQLException {
        List<UserReceipt> listUser = new ArrayList<>();
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(SEARCH_STATUS);
                ptm.setString(1, search);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int receiptID = Integer.parseInt(rs.getString("receiptID"));
                    String inputDate = rs.getString("inputDate");
                    String status = rs.getString("status");
                    int totalQuantity = Integer.parseInt(rs.getString("totalQuantity"));
                    String note = rs.getString("note");
                    String accountantID = rs.getString("accountantID");
                    String stockKeeperID = rs.getString("stockKeeperID");
                    listUser.add(new UserReceipt(receiptID, inputDate, status, totalQuantity, note, accountantID, stockKeeperID));

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
        return listUser;
    }
}
