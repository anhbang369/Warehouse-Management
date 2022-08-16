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
import utils.DBUtils;

/**
 *
 * @author GMT
 */
public class ProductDAO {
    private static final String SHOW_PRODUCTS = "SELECT * FROM Product";
    private static final String SHOW_PRODUCT = "SELECT * FROM Product WHERE productID = ?";
    private static final String LIST_BRAND = "SELECT brand FROM Product GROUP BY brand";
    private static final String PRODUCT = "SELECT productID FROM Product WHERE name = ?";
    private static final String UPDATE_QUANTITY = "UPDATE Product SET quantity = quantity + ? WHERE productID = ?";
    
    public List<ProductDTO> getListProduct() throws SQLException {
        List<ProductDTO> listProduct = new ArrayList<>();
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(SHOW_PRODUCTS);
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
                    listProduct.add(new ProductDTO(productID, model, brand, status, type, width, depth, height, screenSize, voiceRemote, bluetooth, manufacturingDate, madeIn, quantity, name));
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
    
    public ProductDTO getProduct(String pID) throws SQLException {
        ProductDTO product = null;
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(SHOW_PRODUCT);
                ptm.setString(1, pID);
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
                    product = new ProductDTO(productID, model, brand, status, type, width, depth, height, screenSize, voiceRemote, bluetooth, manufacturingDate, madeIn, quantity, name);
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
        return product;
    }

    public List<String> getListBrandCode() throws SQLException {
        List<String> brand = new ArrayList<>();
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(LIST_BRAND);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    brand.add(rs.getString("brand").split("-")[0]);
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
        return brand;
    }

    public String getProductID(String prodName) throws SQLException {
        String pID = null;
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(PRODUCT);
                rs = ptm.executeQuery();
                if (rs.next()) {
                   pID = rs.getString("productID");
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
        return pID;
    }

    public boolean updateProductQuantity(String productID, int quantity) throws SQLException {
        boolean check = false;
        Connection con = null;
        PreparedStatement ptm = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(UPDATE_QUANTITY);
                ptm.setInt(1, quantity);
                ptm.setString(2, productID);
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
