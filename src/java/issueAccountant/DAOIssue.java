/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package issueAccountant;

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
public class DAOIssue {

    private static final String SEARCH_ORDER = "SELECT orderID,customerName,address,phoneNumber,status,note,deliveryDate,sellerID FROM Orders WHERE orderID NOT IN ( SELECT orderID FROM Issue) AND status!='Cancel' AND status!='Confirm' AND orderID like ? AND customerName like ? AND address like ? AND phoneNumber like ?";
    private static final String INSERT_ISSUE = "INSERT INTO Issue(note, accountantID, sellerID, orderID, DateP) VALUES(?,?,?,?,?)";
    private static final String SEARCH_ISSUE = "SELECT issueID,note,accountantID,sellerID,orderID,DateP FROM Issue WHERE orderID like ? ";
    private static final String UPDATE_ISSUE = "UPDATE Issue SET note=? WHERE issueID=?";
    private static final String SEARCH_ORDER_DATE = "SELECT orderID,customerName,address,phoneNumber,status,note,deliveryDate,sellerID FROM Orders WHERE orderID NOT IN ( SELECT orderID FROM Issue) AND status!='Cancel' AND status!='Confirm' AND deliveryDate BETWEEN ? AND ?";
    private static final String SEARCH_ORDER_DETAIL = "SELECT orderDetailID,quantity,orderID,productID FROM OrderDetail WHERE orderID=?";
    private static final String SEARCH_PRODUCT = "SELECT productID,model,brand,status,quantity,name  FROM Product WHERE productID=?";
    private static final String SEARCH_ORDER_DETAIL_S = "SELECT orderDetailID,quantity,orderID,productID FROM OrderDetail WHERE orderID=?";
    private static final String SEARCH_ISSUE_FULL = "SELECT i.issueID,i.note,i.accountantID,i.sellerID,i.DateP,i.orderID,o.customerName,o.address,o.phoneNumber,o.deliveryDate,od.orderDetailID,od.quantity,od.productID,p.name,p.brand,p.model FROM Issue AS i,Orders AS o,OrderDetail AS od,Product AS p WHERE i.orderID=o.orderID AND o.orderID = od.orderID AND od.productID=p.productID AND i.issueID=?";
    private static final String UPDATE_PRODUCT_SUCCESS = "UPDATE Product SET quantity=? WHERE productID=?";
    private static final String UPDATE_PRODUCT_SUCCESS_S = "UPDATE Orders SET status=? WHERE orderID =?";
    private static final String UPDATE_PRODUCT_FAIL = "UPDATE Orders SET status=? WHERE orderID =?";
    private static final String GET_ISSUEID = "SELECT TOP 1 issueID FROM Issue ORDER BY issueID DESC";
    private static final String SEARCH_ISSUE_DATE = "SELECT issueID,note,accountantID,sellerID,orderID,DateP FROM Issue as i WHERE DateP BETWEEN ? AND ?";
    private static final String GET_TOTAL_QUANTITY = "SELECT SUM(od.quantity) AS quantity FROM Issue AS i,Orders AS o,OrderDetail AS od WHERE i.orderID=o.orderID AND o.orderID = od.orderID AND i.issueID=?";
    private static final String GET_SELLERID = "SELECT sellerID FROM Orders WHERE orderID=?";
    private static final String SEARCH_ORDER_ACCOUNT = "SELECT orderID,customerName,address,phoneNumber,status,note,deliveryDate,sellerID FROM Orders WHERE status=?";
    private static final String SEARCH_ISSUE_STATUS = "SELECT issueID,note,accountantID,sellerID,orderID,DateP FROM Issue WHERE note = ? ";

    public List<UserOrder> getListSearchOrder(String search, String search1, String search2, String search3) throws SQLException {
        List<UserOrder> listOrder = new ArrayList<>();
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(SEARCH_ORDER);
                ptm.setString(1, "%" + search + "%");
                ptm.setString(2, "%" + search1 + "%");
                ptm.setString(3, "%" + search2 + "%");
                ptm.setString(4, "%" + search3 + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int orderID = Integer.parseInt(rs.getString("orderID"));
                    String customerName = rs.getString("customerName");
                    String address = rs.getString("address");
                    int phoneNumber = Integer.parseInt(rs.getString("phoneNumber"));
                    String status = rs.getString("status");
                    String note = rs.getString("note");
                    String deliveryDate = rs.getString("deliveryDate");
                    String sellerID = rs.getString("sellerID");
                    listOrder.add(new UserOrder(orderID, customerName, address, phoneNumber, status, note, deliveryDate, sellerID));
                    
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
        return listOrder;
    }
    
    public boolean createIssue(UserIssue issue) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT_ISSUE);
                ptm.setString(1, issue.getNote());
                ptm.setString(2, issue.getAccountantID());
                ptm.setString(3, issue.getSellerID());
                ptm.setInt(4, issue.getOrderID());
                ptm.setString(5, issue.getDateP());
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
    
    public List<UserIssueS> getListSearchIssue(String search) throws SQLException {
        List<UserIssueS> listIssue = new ArrayList<>();
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(SEARCH_ISSUE);
                ptm.setString(1, "%" + search + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int issueID = Integer.parseInt(rs.getString("issueID"));
                    String note = rs.getString("note");
                    String accountantID = rs.getString("accountantID");
                    int orderID = Integer.parseInt(rs.getString("orderID"));
                    String sellerID = rs.getString("sellerID");
                    String DateP = rs.getString("DateP");
                    listIssue.add(new UserIssueS(issueID, note, accountantID, sellerID, orderID, DateP));
                    
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
        return listIssue;
    }
    
    public boolean updateIssue(UserIssueU issue) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_ISSUE);
                ptm.setString(1, issue.getNote());
                ptm.setInt(2, issue.getIssueID());
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
    
    public List<UserOrder> getListSearchOrderDate(String search, String search1) throws SQLException {
        List<UserOrder> listOrder = new ArrayList<>();
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(SEARCH_ORDER_DATE);
                ptm.setString(1, search);
                ptm.setString(2, search1);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int orderID = Integer.parseInt(rs.getString("orderID"));
                    String customerName = rs.getString("customerName");
                    String address = rs.getString("address");
                    int phoneNumber = Integer.parseInt(rs.getString("phoneNumber"));
                    String status = rs.getString("status");
                    String note = rs.getString("note");
                    String deliveryDate = rs.getString("deliveryDate");
                    String sellerID = rs.getString("sellerID");
                    listOrder.add(new UserOrder(orderID, customerName, address, phoneNumber, status, note, deliveryDate, sellerID));
                    
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
        return listOrder;
    }
    
    public List<UserOrderDetail> getListOrderDetail(int orderId) throws SQLException {
        List<UserOrderDetail> listOrderDetail = new ArrayList<>();
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(SEARCH_ORDER_DETAIL);
                ptm.setInt(1, orderId);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int orderDetailID = Integer.parseInt(rs.getString("orderDetailID"));
                    int quantity = Integer.parseInt(rs.getString("quantity"));
                    int orderID = Integer.parseInt(rs.getString("orderID"));
                    String productID = rs.getString("productID");
                    listOrderDetail.add(new UserOrderDetail(orderDetailID, quantity, orderID, productID));
                    
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
        return listOrderDetail;
    }
    
    public List<UserProductI> getListProduct(String productI) throws SQLException {
        List<UserProductI> listProduct = new ArrayList<>();
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(SEARCH_PRODUCT);
                ptm.setString(1, productI);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String model = rs.getString("model");
                    String brand = rs.getString("brand");
                    String status = rs.getString("status");
                    int quantity = Integer.parseInt(rs.getString("quantity"));
                    String name = rs.getString("name");                    
                    listProduct.add(new UserProductI(productID, model, brand, status, quantity, name));
                    
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
    
    public boolean updateQuantityProductS(UserProductU user) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_PRODUCT_SUCCESS);
                ptm.setInt(1, user.getQuantity());
                ptm.setString(2, user.getProductID());
                check = ptm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            
        }
        return check;
    }
    
    public boolean updateQuantityProductSs(UserProductUS user) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_PRODUCT_SUCCESS_S);
                ptm.setString(1, user.getStatus());
                ptm.setInt(2, user.getOrderID());
                check = ptm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            
        }
        return check;
    }
    
    public boolean updateQuantityProductF(UserProductUF user) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_PRODUCT_FAIL);
                ptm.setString(1, user.getStatus());
                ptm.setInt(2, user.getOrderID());
                check = ptm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            
        }
        return check;
    }
    
    public List<UserOrderDetail> getListSearchOrderDetail(int search) throws SQLException {
        List<UserOrderDetail> listOrderDetail = new ArrayList<>();
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(SEARCH_ORDER_DETAIL_S);
                ptm.setInt(1, search);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int orderDetailID = Integer.parseInt(rs.getString("orderDetailID"));
                    int quantity = Integer.parseInt(rs.getString("quantity"));
                    int orderID = Integer.parseInt(rs.getString("orderID"));
                    String productID = rs.getString("productID");
                    listOrderDetail.add(new UserOrderDetail(orderDetailID, quantity, orderID, productID));
                    
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
        return listOrderDetail;
    }
    
    public List<UserIssueFull> getListIssueFull(int search) throws SQLException {
        List<UserIssueFull> listIssue = new ArrayList<>();
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(SEARCH_ISSUE_FULL);
                ptm.setInt(1, search );
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int issueID = Integer.parseInt(rs.getString("issueID"));
                    String note = rs.getString("note");
                    String accountantID = rs.getString("accountantID");
                    String sellerID = rs.getString("sellerID");
                    String DateP = rs.getString("DateP");
                    int orderID = Integer.parseInt(rs.getString("orderID"));
                    String customerName = rs.getString("customerName");
                    String address = rs.getString("address");
                    int phoneNumber = Integer.parseInt(rs.getString("phoneNumber"));
                    String deliveryDate = rs.getString("deliveryDate");
                    int orderDetailID = Integer.parseInt(rs.getString("orderDetailID"));
                    int quantity = Integer.parseInt(rs.getString("quantity"));
                    String productID = rs.getString("productID");
                    String name = rs.getString("name");
                    String brand = rs.getString("brand");
                    String model = rs.getString("model");
                    listIssue.add(new UserIssueFull(issueID, note, accountantID, sellerID, DateP, orderID, customerName, address, phoneNumber, deliveryDate, orderDetailID, quantity, productID, name, model, brand));
                    
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
        return listIssue;
    }
    
    public int getIssueID() throws SQLException {
        int check = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ISSUEID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    int issueID = Integer.parseInt(rs.getString("issueID"));
                    check = issueID;
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
    
    public List<UserIssueS> getListSearchIssueDate(String search, String search1) throws SQLException {
        List<UserIssueS> listIssue = new ArrayList<>();
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(SEARCH_ISSUE_DATE);
                ptm.setString(1,  search );
                ptm.setString(2,  search1 );
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int issueID = Integer.parseInt(rs.getString("issueID"));
                    String note = rs.getString("note");
                    String accountantID = rs.getString("accountantID");
                    int orderID = Integer.parseInt(rs.getString("orderID"));
                    String sellerID = rs.getString("sellerID");
                    String DateP = rs.getString("DateP");
                    listIssue.add(new UserIssueS(issueID, note, accountantID, sellerID, orderID, DateP));
                    
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
        return listIssue;
    }
    
    public int getTotalQuantity(int Issue) throws SQLException {
        int check = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_TOTAL_QUANTITY);
                ptm.setInt(1, Issue);
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
    
    public String getSellerID(int order) throws SQLException {//
        String check = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_SELLERID);
                ptm.setInt(1, order);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String sellerID = rs.getString("sellerID");
                    check = sellerID;
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
    
    public List<UserOrder> getListSearchOrderManager(String search) throws SQLException {
        List<UserOrder> listOrder = new ArrayList<>();
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(SEARCH_ORDER_ACCOUNT);
                ptm.setString(1, search);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int orderID = Integer.parseInt(rs.getString("orderID"));
                    String customerName = rs.getString("customerName");
                    String address = rs.getString("address");
                    int phoneNumber = Integer.parseInt(rs.getString("phoneNumber"));
                    String status = rs.getString("status");
                    String note = rs.getString("note");
                    String deliveryDate = rs.getString("deliveryDate");
                    String sellerID = rs.getString("sellerID");
                    listOrder.add(new UserOrder(orderID, customerName, address, phoneNumber, status, note, deliveryDate, sellerID));
                    
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
        return listOrder;
    }
    
    public List<UserIssueS> getListSearchIssueStatus(String search) throws SQLException {
        List<UserIssueS> listIssue = new ArrayList<>();
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(SEARCH_ISSUE_STATUS);
                ptm.setString(1,  search );
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int issueID = Integer.parseInt(rs.getString("issueID"));
                    String note = rs.getString("note");
                    String accountantID = rs.getString("accountantID");
                    int orderID = Integer.parseInt(rs.getString("orderID"));
                    String sellerID = rs.getString("sellerID");
                    String DateP = rs.getString("DateP");
                    listIssue.add(new UserIssueS(issueID, note, accountantID, sellerID, orderID, DateP));
                    
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
        return listIssue;
    }
    
}
