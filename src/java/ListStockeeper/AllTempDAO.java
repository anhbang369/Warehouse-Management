/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ListStockeeper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author GMT
 */
public class AllTempDAO {

    private final String LIST_LOCATION = "SELECT b.binID, i.inputDate, b.quantity, b.capacity, b.available FROM Bin as b, Inventory as i, Product as p WHERE b.inventoryID = i.inventoryID AND i.productID = p.productID";

    public List<LocationDTO> getListLocation() throws SQLException {
        List<LocationDTO> listLocation = new ArrayList<>();
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = utils.DBUtils.getConnection();
            if (con != null){
                ptm = con.prepareStatement(LIST_LOCATION);
                rs = ptm.executeQuery();
                while (rs.next()){
                    String bID = rs.getString("binID");
                    String inputDate = rs.getString("inputDate");
                    int quantity = rs.getInt("quantity");
                    int capacity = rs.getInt("capacity");
                    String available = rs.getString("available");
                    String binID = bID.split("-")[0];
                    listLocation.add(new LocationDTO(binID, inputDate, quantity, capacity, available));
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
        return listLocation;
    }
}
