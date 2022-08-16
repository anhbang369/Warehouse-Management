/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderSeller;

import issueAccountant.UserOrder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import user.UserProduct;
import utils.DBUtils;

/**
 *
 * @author 84348
 */
public class DAOOrder {
        private static final String SEARCH_ORDER = "SELECT orderID,customerName,address,phoneNumber,status,note,deliveryDate,sellerID FROM Orders WHERE orderID like ?";
        private static final String SHOW_PRODUCT_PRO = "SELECT productID,model,brand,status,type,width,depth,height,screenSize,voiceRemote,bluetooth,manufacturingDate,madeIn,quantity,name FROM Product WHERE productID like ? AND name like ?";
        private static final String INSRT_ORDER_SELLER = "INSERT INTO Orders(customerName, address, phoneNumber, status,note,deliveryDate,sellerID) VALUES(?,?,?,?,?,?,?)";
        private static final String GET_ORDERID = "SELECT TOP 1 orderID FROM Orders ORDER BY orderID DESC";
        private static final String INSRT_ORDER_DETAIL_SELLER = "INSERT INTO OrderDetail(quantity, orderID, productID) VALUES(?,?,?)";
        private static final String SHOW_FULL_ORDER_DETAIL_SELLER = "SELECT o.orderID,customerName,address,phoneNumber,status,note,deliveryDate,sellerID,orderDetailID,quantity,productID FROM Orders AS o, OrderDetail AS od WHERE o.orderID = od.orderID AND o.orderID = ?";
        private static final String SEARCH_ORDER_DETAIL_VIEW = "SELECT orderDetailID,quantity,orderID,productID FROM OrderDetail WHERE orderID = ?";
        private static final String UPDATE_ORDER = "UPDATE Orders SET customerName=?, deliveryDate=?, note=?, status=? WHERE orderID=?";
        private static final String GET_QUANTITY_NOT_IMPLEMENT = "SELECT SUM(od.quantity) AS quantity FROM Orders AS o, OrderDetail AS od WHERE o.orderID=od.orderID AND o.status='Not Implement' AND productID=?";
        private static final String GET_QUANTITY_PRODUCT = "SELECT SUM(quantity) AS quantity FROM Product WHERE productID=?";
        private static final String SEARCH_STATUS = "SELECT orderID,customerName,address,phoneNumber,status,note,deliveryDate,sellerID FROM Orders WHERE status = ?";
        private static final String GET_STATUS_ORDER = "SELECT status FROM Orders WHERE orderID=?";
        private static final String SEARCH_ORDER_DATE = "SELECT orderID,customerName,address,phoneNumber,status,note,deliveryDate,sellerID FROM Orders WHERE deliveryDate BETWEEN ? AND ?";

        public List<UserOrder> getListSearchOrder(String search) throws SQLException {
        List<UserOrder> listOrder = new ArrayList<>();
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(SEARCH_ORDER);
                ptm.setString(1, "%" + search + "%");
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
    
    public List<UserProduct> getListShowProSeller(String search, String search1) throws SQLException {
        List<UserProduct> listProduct = new ArrayList<>();
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(SHOW_PRODUCT_PRO);
                ptm.setString(1, "%" + search + "%");
                ptm.setString(2, "%" + search1 + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String model = rs.getString("model");
                    String brand = rs.getString("brand");
                    String status = rs.getString("status");
                    String type = rs.getString("type");
                    float width = Float.parseFloat(rs.getString("width"));
                    float depth = Float.parseFloat(rs.getString("depth"));
                    float height = Float.parseFloat(rs.getString("height"));
                    float screenSize = Float.parseFloat(rs.getString("screenSize"));
                    String voiceRemote = rs.getString("voiceRemote");
                    String bluetooth = rs.getString("bluetooth");
                    int manufacturingDate = Integer.parseInt(rs.getString("manufacturingDate"));
                    String madeIn = rs.getString("madeIn");
                    int quantity = Integer.parseInt(rs.getString("quantity"));
                    String name = rs.getString("name");
                    listProduct.add(new UserProduct(productID, name, model, brand, status, type, width, depth, height, screenSize, voiceRemote, bluetooth, manufacturingDate, madeIn, quantity));

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
    
    public boolean createOrder(UserOrderSeller receiptDetail) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSRT_ORDER_SELLER);
                ptm.setString(1, receiptDetail.getCustomerName());
                ptm.setString(2, receiptDetail.getAddress());
                ptm.setString(3, receiptDetail.getPhoneNumber());
                ptm.setString(4, receiptDetail.getStatus());
                ptm.setString(5, receiptDetail.getNote());
                ptm.setString(6, receiptDetail.getDeliveryDate());
                ptm.setString(7, receiptDetail.getSellerID());
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
    
    public int getOrderId() throws SQLException {
        int check = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ORDERID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    int orderId = Integer.parseInt(rs.getString("orderID"));
                    check = orderId;
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
    
    public boolean createOrderDetail(UserOrderDetailFullSeller receiptDetail) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSRT_ORDER_DETAIL_SELLER);
                ptm.setInt(1, receiptDetail.getQuantity());
                ptm.setInt(2, receiptDetail.getOrderID());
                ptm.setString(3, receiptDetail.getProductId());
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
    
    
    public List<UserOrderFullSeller> getListOrderFull(int search) throws SQLException {
        List<UserOrderFullSeller> listProduct = new ArrayList<>();
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(SHOW_FULL_ORDER_DETAIL_SELLER);
                ptm.setInt(1, search);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int orderID = Integer.parseInt(rs.getString("orderID"));
                    String customerName = rs.getString("customerName");
                    String address = rs.getString("address");
                    String phoneNumber = rs.getString("phoneNumber");
                    String status = rs.getString("status");
                    String note = rs.getString("note");
                    String deliveryDate = rs.getString("deliveryDate");
                    String sellerID = rs.getString("sellerID");
                    String orderDetailID = rs.getString("orderDetailID");
                    int quantity = Integer.parseInt(rs.getString("quantity"));
                    String productID = rs.getString("productID");
                    listProduct.add(new UserOrderFullSeller(orderID, customerName, address, phoneNumber, status, note, deliveryDate, sellerID, orderDetailID, quantity, productID));

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
    
    public List<UserOrderDetailView> getListOrderDetailView(String search) throws SQLException {
        List<UserOrderDetailView> listOrder = new ArrayList<>();
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(SEARCH_ORDER_DETAIL_VIEW);
                ptm.setString(1, search);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String orderDetailID = rs.getString("orderDetailID");
                    int quantity = Integer.parseInt(rs.getString("quantity"));
                    int orderID = Integer.parseInt(rs.getString("orderID"));                   
                    String productID = rs.getString("productID");
                    listOrder.add(new UserOrderDetailView(orderDetailID, quantity, orderID, productID));
                    
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
    
    
    public boolean updateOrder(UserOrderUpdate user) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_ORDER);
                ptm.setString(1, user.getCustomerName());
                ptm.setString(2, user.getDeliveryDate());
                ptm.setString(3, user.getNote());
                ptm.setString(4, user.getStatus());
                ptm.setInt(5, user.getOrderId());

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
    
    public int getQuantityOrderNotImplement(String productID) throws SQLException {
        int check = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_QUANTITY_NOT_IMPLEMENT);
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
    
    public int getQuantityProduct(String productID) throws SQLException {
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
    
    public List<UserOrder> getListSearchOrderStatus(String search) throws SQLException {
        List<UserOrder> listOrder = new ArrayList<>();
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
    
    public String getStatusOrder(int orderID) throws SQLException {//
        String check = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_STATUS_ORDER);
                ptm.setInt(1, orderID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String status = rs.getString("status");
                    check = status;
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
    
    public List<UserOrderDetailView> getListOrderDetailViewAccount(String search) throws SQLException {
        List<UserOrderDetailView> listOrder = new ArrayList<>();
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(SEARCH_ORDER_DETAIL_VIEW);
                ptm.setString(1, search);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String orderDetailID = rs.getString("orderDetailID");
                    int quantity = Integer.parseInt(rs.getString("quantity"));
                    int orderID = Integer.parseInt(rs.getString("orderID"));                   
                    String productID = rs.getString("productID");
                    listOrder.add(new UserOrderDetailView(orderDetailID, quantity, orderID, productID));
                    
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
    
    public List<UserOrder> getListSearchOrderDate(String search,String search1) throws SQLException {
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
}
