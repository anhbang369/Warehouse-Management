/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import user.UserProduct;
import utils.DBUtils;

/**
 *
 * @author 84348
 */
public class DAOProduct {
    private static final String CREATE = "INSERT INTO Product(productID,model, brand, status, type,width,depth,height,screenSize,voiceRemote,bluetooth,manufacturingDate,madeIn,quantity,name ) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String GET_MODEL = "SELECT model FROM Product WHERE model=?";
    private static final String UPDATE_PRODUCT = "UPDATE Product SET status=?,type=?, width=?,depth=?,height=?, screenSize=?,voiceRemote=?,bluetooth=?, manufacturingDate=?,madeIn=?, quantity=?,name=? WHERE productID=?";
    private static final String GET_BLUE = "SELECT bluetooth FROM Product WHERE productID=?";
    private static final String GET_VOICE = "SELECT voiceRemote FROM Product WHERE productID=?";
    
    public boolean createProduct(UserProduct user) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CREATE);
                ptm.setString(1, user.getProductID());
                ptm.setString(2, user.getModel());
                ptm.setString(3, user.getBrand());
                ptm.setString(4, user.getStatus());
                ptm.setString(5, user.getType());
                ptm.setString(6, String.valueOf(user.getWidth()));
                ptm.setString(7, String.valueOf(user.getDepth()));
                ptm.setString(8, String.valueOf(user.getHeight()));
                ptm.setString(9, String.valueOf(user.getScreenSize()));
                ptm.setString(10, user.getVoiceRemote());
                ptm.setString(11, user.getBluetooth());
                ptm.setInt(12, user.getManufacturingDate());
                ptm.setString(13, user.getMadeIn());
                ptm.setInt(14, user.getQuantity());
                ptm.setString(15, user.getName());
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

public boolean getModel(String search) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_MODEL);
                ptm.setString(1, search);               
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

public boolean updateProduct(UserProduct user) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_PRODUCT);
                ptm.setString(1, user.getStatus());
                ptm.setString(2, user.getType());
                ptm.setString(3, String.valueOf(user.getWidth()));
                ptm.setString(4, String.valueOf(user.getDepth()));
                ptm.setString(5, String.valueOf(user.getHeight()));
                ptm.setString(6, String.valueOf(user.getScreenSize()));
                ptm.setString(7, user.getVoiceRemote());
                ptm.setString(8, user.getBluetooth());
                ptm.setInt(9, user.getManufacturingDate());
                ptm.setString(10, user.getMadeIn());
                ptm.setInt(11, user.getQuantity());
                ptm.setString(12, user.getName());
                ptm.setString(13, user.getProductID());
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

public String getBlue(String productID) throws SQLException {
        String check = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_BLUE);
                ptm.setString(1, productID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String status = rs.getString("bluetooth");
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

public String getVoice(String productID) throws SQLException {
        String check = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_VOICE);
                ptm.setString(1, productID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String status = rs.getString("voiceRemote");
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

}
