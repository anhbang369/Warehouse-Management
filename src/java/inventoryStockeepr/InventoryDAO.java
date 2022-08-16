/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventoryStockeepr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ListStockeeper.InventoryTempDTO;
import utils.DBUtils;

/**
 *
 * @author GMT
 */
public class InventoryDAO {

    private static final String SHOW_INVENTORIES = "SELECT * FROM Inventory WHERE productID like ? AND inventoryID <> 1";
    private static final String SHOW_INVENTORY_FULL = "SELECT * FROM Inventory WHERE inventoryID <> 1";
    private static final String SHOW_INVENTORY = "SELECT * FROM Inventory WHERE inventoryID = ?";
    private static final String SHOW_INVENTORY_STATUS_PRODUCT = "SELECT * FROM Inventory WHERE productID = ? AND note = ? AND inventoryID <> 1";
    private static final String SHOW_INVENTORY_STATUS = "SELECT * FROM Inventory WHERE note = ?";
    private static final String ADD = "INSERT INTO Inventory(inputDate, warranty, note, stockKeeperID, productID, quantityOnHand) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String INSERT_INVENTORY = "INSERT INTO Inventory(inputDate, warranty, note, stockKeeperID, productID, quantityOnHand) VALUES(?,?,?,?,?,?)";
    private static final String GET_INSERT_INVENTORY = "SELECT inventoryID FROM Inventory WHERE inputDate = ? AND warranty = ? AND note = ? AND stockKeeperID = ? AND productID = ? AND quantityOnHand = ?";
    private static final String GET_QUANTITY_BIN = "SELECT (sum(quantity)+?) AS quantity, capacity FROM Bin WHERE binID like ? GROUP BY capacity";
    private static final String GET_ID_UPDATE = "SELECT i.inventoryID FROM Inventory AS i WHERE not exists(SELECT * FROM Bin AS b WHERE i.inventoryID = b.inventoryID)";
    private static final String UPDATE_ALL_STATUS = "UPDATE Inventory SET note = 'Not available' WHERE inventoryID = ?";

    public boolean updateAllStatus() throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null, ptm1 = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ID_UPDATE);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int invenID = rs.getInt("inventoryID");
                    ptm1 = conn.prepareStatement(UPDATE_ALL_STATUS);
                    ptm1.setInt(1, invenID);
                    check = ptm1.executeUpdate() > 0 ? true : false;
                    if (!check) {
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm1 != null) {
                ptm1.close();
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

    public List<InventoryDTO> getListInven(String prodID) throws SQLException {
        List<InventoryDTO> listInventory = new ArrayList<>();
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                if (prodID != "") {
                    if (prodID.contains("vailable")) {
                        ptm = con.prepareStatement(SHOW_INVENTORY_STATUS);
                        ptm.setString(1, prodID);
                        rs = ptm.executeQuery();
                    } else {
                        ptm = con.prepareStatement(SHOW_INVENTORIES);
                        ptm.setString(1, "%" + prodID + "%");
                        rs = ptm.executeQuery();
                    }
                } else {
                    ptm = con.prepareStatement(SHOW_INVENTORY_FULL);
                    rs = ptm.executeQuery();
                }
                while (rs.next()) {
                    int inventoryID = rs.getInt("inventoryID");
                    String inputDate = rs.getString("inputDate");
                    String warranty = rs.getString("warranty");
                    String note = rs.getString("note");
                    String stockKeeperID = rs.getString("stockKeeperID");
                    String productID = rs.getString("productID");
                    int quantityOnHand = rs.getInt("quantityOnHand");
                    listInventory.add(new InventoryDTO(inventoryID, inputDate, warranty, note, stockKeeperID, productID, quantityOnHand));
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

    public InventoryDTO getInven(int invenID) throws SQLException {
        InventoryDTO inven = null;
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(SHOW_INVENTORY);
                ptm.setInt(1, invenID);
                rs = ptm.executeQuery();

                while (rs.next()) {
                    int inventoryID = rs.getInt("inventoryID");
                    String inputDate = rs.getString("inputDate");
                    String warranty = rs.getString("warranty");
                    String note = rs.getString("note");
                    String stockKeeperID = rs.getString("stockKeeperID");
                    String productID = rs.getString("productID");
                    int quantityOnHand = rs.getInt("quantityOnHand");
                    inven = new InventoryDTO(inventoryID, inputDate, warranty, note, stockKeeperID, productID, quantityOnHand);
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
        return inven;
    }

    public boolean addInven(InventoryTempDTO inven) throws SQLException {
        boolean check = false;
        Connection con = null;
        PreparedStatement ptm = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(ADD);
                ptm.setString(1, inven.getInputDate());
                ptm.setString(2, inven.getWarranty());
                ptm.setString(3, inven.getNote());
                ptm.setString(4, inven.getStockKeeperID());
                ptm.setString(5, inven.getProductID());
                ptm.setInt(6, inven.getQuantityOnHand());
                check = ptm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return check;
    }

    public List<InventoryDTO> getListInven(String status, String prodID) throws SQLException {
        List<InventoryDTO> listInventory = new ArrayList<>();
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(SHOW_INVENTORY_STATUS_PRODUCT);
                ptm.setString(1, "%" + prodID + "%");
                ptm.setString(2, status);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int inventoryID = rs.getInt("inventoryID");
                    String inputDate = rs.getString("inputDate");
                    String warranty = rs.getString("warranty");
                    String note = rs.getString("note");
                    String stockKeeperID = rs.getString("stockKeeperID");
                    String productID = rs.getString("productID");
                    int quantityOnHand = rs.getInt("quantityOnHand");
                    listInventory.add(new InventoryDTO(inventoryID, inputDate, warranty, note, stockKeeperID, productID, quantityOnHand));
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

    public int createInventory(InventoryTempDTO receipt) throws SQLException {
        int invenID = 0;
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT_INVENTORY);
                ptm.setString(1, receipt.getInputDate());
                ptm.setString(2, receipt.getWarranty());
                ptm.setString(3, receipt.getNote());
                ptm.setString(4, receipt.getStockKeeperID());
                ptm.setString(5, receipt.getProductID());
                ptm.setInt(6, receipt.getQuantityOnHand());
                check = ptm.executeUpdate() > 0 ? true : false;
                if (check) {
                    ptm = conn.prepareStatement(GET_INSERT_INVENTORY);
                    ptm.setString(1, receipt.getInputDate());
                    ptm.setString(2, receipt.getWarranty());
                    ptm.setString(3, receipt.getNote());
                    ptm.setString(4, receipt.getStockKeeperID());
                    ptm.setString(5, receipt.getProductID());
                    ptm.setInt(6, receipt.getQuantityOnHand());
                    rs = ptm.executeQuery();
                    if (rs.next()){
                        invenID = rs.getInt("inventoryID");
                    }
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
        return invenID;
    }

    public int sumBin(int a, String b) throws SQLException {
        int check = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_QUANTITY_BIN);
                ptm.setInt(1, a);
                ptm.setString(2, b + "%");
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

}
