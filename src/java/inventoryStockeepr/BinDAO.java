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
import ListStockeeper.ChooseBinDTO;
import utils.DBUtils;

/**
 *
 * @author GMT
 */
public class BinDAO {

    private static final String SHOW_BINS = "SELECT * FROM Bin WHERE inventoryID = ?";
    private static final String SHOW_BINS_BRAND = "SELECT * FROM Bin WHERE quantity < capacity AND available = ? ORDER BY (capacity-quantity) DESC";
    private static final String GET_BIN = "SELECT sum(quantity) as quantity, capacity, available FROM Bin WHERE binID like ? GROUP BY capacity, available";
    private static final String BIN = "SELECT * FROM Bin";
    private static final String DELETE = "DELETE FROM Bin WHERE binID like ? AND quantity = 0";
    private static final String CREATE = "INSERT INTO Bin(binID, inventoryID, quantity, capacity, available) VALUES (?, 1, 0, ?, ?)";
    private static final String UPDATE = "UPDATE Bin SET capacity = ?, available = ? WHERE binID = ?";
    private static final String ADD = "UPDATE Bin SET quantity = quantity + ? WHERE binID = ?";
    private static final String UPDATE_BIN_SK = "UPDATE Bin SET capacity=?,available=? WHERE binID= like ?";
    private static final String UPDATE_QUANTITY_BIN = "UPDATE Bin SET quantity=?, inventoryID=? WHERE binID=?";
    private static final String CHECK_STATUS_ISSUE = "SELECT * FROM Issue WHERE issueID=? AND note='Implemented'";
    private static final String UPDATE_ISSUE_SK = "UPDATE Issue SET note=? WHERE issueID=?";
    private static final String GET_INVENID = "SELECT inventoryID FROM Inventory  WHERE productID=? ORDER BY DAY(inputDate) DESC,MONTH(inputDate) DESC,YEAR(inputDate) DESC";//
    private static final String GET_BIN_QUANTITY = "SELECT quantity,b.binID FROM Inventory AS i,Bin AS b  \n"
            + "WHERE quantity!=0 AND i.inventoryID=b.inventoryID  AND i.inventoryID=?\n"
            + "ORDER BY DAY(inputDate) DESC,MONTH(inputDate) DESC,YEAR(inputDate) DESC";//
    private static final String UPDATE_BIN_Q = "UPDATE Bin SET quantity=? WHERE binID=?";//
    private static final String GET_BIN_SUM = "SELECT sum(quantity) as quantity, capacity FROM Bin WHERE binID like ? GROUP BY capacity";
    private static final String GET_BINID = "SELECT binID FROM Bin WHERE binID like ?";//
    private static final String GET_BIN_CORRECT = "SELECT inventoryID, quantity, capacity, available FROM Bin WHERE binID = ?";
    private static final String CLEAR_BINS = "DELETE BIN WHERE inventoryID <> 1 AND quantity = 0";//

    public boolean updateQuantityBin(int begin, int a, String binID, int invenID) throws SQLException {
        boolean check = false;
        BinDTO bin = new BinDTO();
        int count = 0, checkCount = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_BINID);
                ptm.setString(1, binID + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String bID = rs.getString("binID");
                    bin = getBinChooseCorrect(bID);
                    checkCount = Integer.parseInt(bin.getBinID().split("-")[1]) + 1;
                    if (bin.getInventoryID() != 1) {
                    } else {
                        ptm = conn.prepareStatement(UPDATE_QUANTITY_BIN);
                        ptm.setInt(1, a);
                        ptm.setInt(2, invenID);
                        ptm.setString(3, bID);
                        check = ptm.executeUpdate() > 0 ? true : false;
                        count = 1;
                    }
                }
                if (count == 0) {
                    ptm = conn.prepareStatement(CREATE);
                    String binId = binID + "-" + checkCount;
                    ptm.setString(1, binId);
                    ptm.setInt(2, bin.getCapacity());
                    ptm.setString(3, bin.getAvailable());
                    check = ptm.executeUpdate() > 0 ? true : false;

                    ptm = conn.prepareStatement(UPDATE_QUANTITY_BIN);
                    ptm.setInt(1, begin);
                    ptm.setInt(2, invenID);
                    ptm.setString(3, binId);
                    check = ptm.executeUpdate() > 0 ? true : false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs == null) {
                rs.close();
            }
            if (conn != null) {
                conn.close();
            }
            if (ptm != null) {
                ptm.close();
            }

        }
        return check;
    }

    public List<BinDTO> getListBin(int invenID) throws SQLException {
        List<BinDTO> listBin = new ArrayList<>();
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(SHOW_BINS);
                ptm.setInt(1, invenID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String binID = rs.getString("binID");
                    int inventoryID = rs.getInt("inventoryID");
                    int quantity = rs.getInt("quantity");
                    int capacity = rs.getInt("capacity");
                    String available = rs.getString("available");
                    listBin.add(new BinDTO(binID, inventoryID, quantity, capacity, available));
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
        return listBin;
    }

    public List<BinDTO> getListBin(String prodBrand) throws SQLException {
        List<BinDTO> listBin = new ArrayList<>();
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(SHOW_BINS_BRAND);
                ptm.setString(1, prodBrand);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String binID = rs.getString("binID");
                    int inventoryID = rs.getInt("inventoryID");
                    int quantity = rs.getInt("quantity");
                    int capacity = rs.getInt("capacity");
                    String available = rs.getString("available");
                    listBin.add(new BinDTO(binID, inventoryID, quantity, capacity, available));
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
        return listBin;
    }

    public BinDTO getBinChooseCorrect(String bID) throws SQLException {
        BinDTO bin = null;
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(GET_BIN_CORRECT);
                ptm.setString(1, bID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int invenID = rs.getInt("inventoryID");
                    int quantity = rs.getInt("quantity");
                    int capacity = rs.getInt("capacity");
                    String available = rs.getString("available");
                    bin = new BinDTO(bID, invenID, quantity, capacity, available);
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
        return bin;
    }

    public BinDTO getBinChoose(String bID) throws SQLException {
        BinDTO bin = null;
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(GET_BIN);
                ptm.setString(1, bID + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int quantity = rs.getInt("quantity");
                    int capacity = rs.getInt("capacity");
                    String available = rs.getString("available");
                    bin = new BinDTO(bID, 1, quantity, capacity, available);
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
        return bin;
    }

    public boolean checkDuplicate(List<ChooseBinDTO> listChoose, BinDTO bin) {
        for (ChooseBinDTO b : listChoose) {
            if (bin.getBinID().equals(b.getBinID())) {
                return false;
            }
        }
        return true;
    }

    public List<BinDTO> getBins() throws SQLException {
        List<BinDTO> listBin = new ArrayList<>();
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null, rsI = null, rsP = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(BIN);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String binID = rs.getString("binID");
                    int inventoryID = rs.getInt("inventoryID");
                    int quantity = rs.getInt("quantity");
                    int capacity = rs.getInt("capacity");
                    String available = rs.getString("available");
                    listBin.add(new BinDTO(binID, inventoryID, quantity, capacity, available));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rsP != null) {
                rsP.close();
            }
            if (rsI != null) {
                rsI.close();
            }
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
        return listBin;
    }

    public boolean removeBin(String bID) throws SQLException {
        boolean check = false;
        Connection con = null;
        PreparedStatement ptm = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(DELETE);
                ptm.setString(1, bID + "%");
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

    public boolean createBin(String binID, int capacity, String available) throws SQLException {
        boolean check = false;
        Connection con = null;
        PreparedStatement ptm = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(CREATE);
                ptm.setString(1, binID.toUpperCase());
                ptm.setInt(2, capacity);
                ptm.setString(3, available.toUpperCase());
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

    public boolean updateBin(String binID, int capacity, String available) throws SQLException {
        boolean check = false;
        Connection con = null;
        PreparedStatement ptm = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(UPDATE);
                ptm.setInt(1, capacity);
                ptm.setString(2, available);
                ptm.setString(3, binID);
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

    public boolean addBin(ChooseBinDTO b) throws SQLException {
        boolean check = false;
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(ADD);
                ptm.setInt(1, b.getQuantity());
                ptm.setString(2, b.getBinID());
                check = ptm.executeUpdate() > 0 ? true : false;
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
        return check;
    }

    public boolean updatePositionBin(UserUpdatePosition user) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_BIN_SK);
                ptm.setInt(1, user.getCapacity());
                ptm.setString(2, user.getAvailable());
                ptm.setString(3, user.getBinID() + "%");
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

    public boolean checkIssueStatus(int issueID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_STATUS_ISSUE);
                ptm.setInt(1, issueID);
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

    public boolean updateIssueStockeep(UserUpdateStatus user) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_ISSUE_SK);
                ptm.setString(1, user.getNote());
                ptm.setInt(2, user.getIssueID());
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

    public List<UserInventoryID> getBinID(String invenID) throws SQLException {
        List<UserInventoryID> listBin = new ArrayList<>();
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(GET_INVENID);
                ptm.setString(1, invenID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int inventoryID = Integer.parseInt(rs.getString("inventoryID"));
                    listBin.add(new UserInventoryID(inventoryID));
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
        return listBin;
    }

    public List<UserBinQuantity> getBinQuantity(int invenID) throws SQLException {
        List<UserBinQuantity> listBin = new ArrayList<>();
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(GET_BIN_QUANTITY);
                ptm.setInt(1, invenID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int quantity = Integer.parseInt(rs.getString("quantity"));
                    String binID = rs.getString("binID");
                    listBin.add(new UserBinQuantity(quantity, binID));
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
        return listBin;
    }

    public boolean updateBinQ(int binQ, String binID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_BIN_Q);
                ptm.setInt(1, binQ);
                ptm.setString(2, binID);
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

    public List<BinDTO> getListBinSum(String brand) throws SQLException {
        List<BinDTO> listBin = new ArrayList<>();
        List<BinDTO> listBinSum = new ArrayList<>();
        boolean check = true;
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(SHOW_BINS_BRAND);
                ptm.setString(1, brand);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String binID = rs.getString("binID");
                    int inventoryID = rs.getInt("inventoryID");
                    int quantity = rs.getInt("quantity");
                    int capacity = rs.getInt("capacity");
                    String available = rs.getString("available");
                    listBin.add(new BinDTO(binID, inventoryID, quantity, capacity, available));
                }
                if (listBin.size() > 0) {
                    ptm = con.prepareStatement(GET_BIN_SUM);
                    for (BinDTO b : listBin) {
                        ptm.setString(1, b.getBinID().split("-")[0] + "%");
                        rs = ptm.executeQuery();
                        if (rs.next()) {
                            int quantity = rs.getInt("quantity");
                            if (listBinSum.size() > 0) {
                                for (BinDTO bin : listBinSum) {
                                    if (b.getBinID().split("-")[0].equals(bin.getBinID())) {
                                        check = false;
                                        break;
                                    }
                                }
                            }
                            if (check) {
                                if (quantity < b.getCapacity()) {
                                    listBinSum.add(new BinDTO(b.getBinID().split("-")[0], b.getInventoryID(), quantity, b.getCapacity(), b.getAvailable()));
                                }
                            }
                        }
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
            if (con != null) {
                con.close();
            }
        }
        return listBinSum;
    }

    public boolean clearBin() throws SQLException {
        boolean check = false;
        Connection con = null;
        PreparedStatement ptm = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(CLEAR_BINS);
                check = ptm.executeUpdate() > 0 ? true:false;
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
}
