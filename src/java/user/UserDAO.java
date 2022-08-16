/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import productManager.UserProducNotiManager;

import utils.DBUtils;

/**
 *
 * @author 84348
 */
public class UserDAO {

    private static final String LOGIN = "SELECT name,role,status,phoneNumber FROM Account WHERE accountID=? AND password=?";
    private static final String SHOW_PRODUCT = "SELECT productID,model,brand,status,type,width,depth,height,screenSize,voiceRemote,bluetooth,manufacturingDate,madeIn,quantity,name FROM Product WHERE productID like ? ";
    private static final String SHOW_PRODUCT_NOTIFY = "SELECT p.name FROM Product AS p, Inventory AS i \n"
            + "WHERE p.productID =? AND i.inventoryID = (SELECT TOP 1 inventoryID FROM Inventory \n"
            + "WHERE productID=? ORDER BY YEAR(inputDate) DESC, MONTH(inputDate) DESC, DAY(inputDate) DESC) \n"
            + "AND p.productID=i.productID \n"
            + "AND p.quantity>=(SELECT TOP 1 quantityOnHand FROM Inventory \n"
            + "WHERE productID=? \n"
            + "ORDER BY YEAR(inputDate) DESC, MONTH(inputDate) DESC, DAY(inputDate) DESC)";
    private static final String SHOW_PRODUCT_PRO = "SELECT productID,model,brand,status,type,width,depth,height,screenSize,voiceRemote,bluetooth,manufacturingDate,madeIn,quantity,name FROM Product WHERE productID like ? AND name like ? AND brand like ? AND model like ?";
    private static final String ACCOUNT = "SELECT name,accountID FROM Account WHERE role='SK'";
    private static final String SHOW_LIST_PRODUCT = "SELECT productID,model,brand,status,type,width,depth,height,screenSize,voiceRemote,bluetooth,manufacturingDate,madeIn,quantity,name FROM Product";
    private static final String PRODUCT_NOT_FULL = "SELECT productID,name,quantity FROM Product WHERE name like ?";
    private static final String SHOW_PRODUCT_VIEW = "SELECT productID,model,brand,status,type,width,depth,height,screenSize,voiceRemote,bluetooth,manufacturingDate,madeIn,quantity,name FROM Product WHERE productID = ? ";
    private static final String COUNT_ZERO = "SELECT productID,name FROM Product WHERE quantity = 0";

    public UserDTO checkLogin(String accountID, String password) throws SQLException {
        UserDTO user = null;
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(LOGIN);
                ptm.setString(1, accountID);
                ptm.setString(2, password);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String name = rs.getString("name");
                    String role = rs.getString("role");
                    String status = rs.getString("status");
                    String phoneNumber = rs.getString("phoneNumber");
                    user = new UserDTO(accountID, password, name, role, status, phoneNumber);

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
        return user;
    }

    public List<UserProduct> getListShowProduct(String search) throws SQLException {
        List<UserProduct> listProduct = new ArrayList<>();
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(SHOW_PRODUCT);
                ptm.setString(1, "%" + search + "%");
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

    public String getListNotify(String a,String b,String c) throws SQLException {
        String check = null;
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(SHOW_PRODUCT_NOTIFY);
                ptm.setString(1, a);
                ptm.setString(2, b);
                ptm.setString(3, c);                
                rs = ptm.executeQuery();
                while (rs.next()) {
                    check = rs.getString("name");
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
        return check;
    }

    public List<UserProduct> getListShowPro(String search, String search1, String search2, String search3) throws SQLException {
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
                ptm.setString(3, "%" + search2 + "%");
                ptm.setString(4, "%" + search3 + "%");
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

    public List<UserAccount> getListAccount() throws SQLException {
        List<UserAccount> listAccount = new ArrayList<>();
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(ACCOUNT);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String name = rs.getString("name");
                    String accountID = rs.getString("accountID");
                    listAccount.add(new UserAccount(name, accountID));

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
        return listAccount;
    }

    public List<UserProduct> getListProduct() throws SQLException {
        List<UserProduct> listProduct = new ArrayList<>();
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(SHOW_LIST_PRODUCT);
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
    
    public List<UserProductNotFull> getListShowNotFull(String search) throws SQLException {
        List<UserProductNotFull> listProduct = new ArrayList<>();
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(PRODUCT_NOT_FULL);
                ptm.setString(1, "%" + search + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                                        String name = rs.getString("name");
                    int quantity = Integer.parseInt(rs.getString("quantity"));
                    listProduct.add(new UserProductNotFull(productID, name, quantity));

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

    public List<UserProduct> getListShowProductView(String search) throws SQLException {
        List<UserProduct> listProduct = new ArrayList<>();
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(SHOW_PRODUCT_VIEW);
                ptm.setString(1, search );
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
    
    
    public List<UserProducNotiManager> getZore() throws SQLException {
        List<UserProducNotiManager> listProduct = new ArrayList<>();
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(COUNT_ZERO);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String name = rs.getString("name");
                    listProduct.add(new UserProducNotiManager(productID, name));

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
