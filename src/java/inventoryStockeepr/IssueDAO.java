/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventoryStockeepr;

import java.sql.Connection;
import java.sql.Date;
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
public class IssueDAO {

    private String SHOW_ISSUES = "SELECT * FROM Issue";
    private String SHOW_ISSUE = "SELECT * FROM Issue WHERE issueID = ?";
    private String SAVE = "UPDATE Issue SET note = 'finished' WHERE issueID = ?";
    private static final String CHECK_STATUS_ISSUE = "SELECT * FROM Issue WHERE issueID=? AND note='Not Finish'";
    private static final String UPDATE_ISSUE_SK = "UPDATE Issue SET note=? WHERE issueID=?";

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
                    check=true;
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
    
    public List<IssueDTO> getListIssue() throws SQLException {
        List<IssueDTO> listIssue = new ArrayList<>();
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(SHOW_ISSUES);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int issueID = rs.getInt("issueID");
                    String note = rs.getString("note");
                    String accountantID = rs.getString("accountantID");
                    String sellerID = rs.getString("sellerID");
                    int orderID = rs.getInt("orderID");
                    Date DateP = rs.getDate("DateP");
                    listIssue.add(new IssueDTO(issueID, note, accountantID, sellerID, orderID, DateP));
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

    public IssueDTO getIssue(int iID) throws SQLException {
        IssueDTO issue = null;
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(SHOW_ISSUE);
                ptm.setInt(1, iID);
                rs = ptm.executeQuery();

                while (rs.next()) {
                    int issueID = rs.getInt("issueID");
                    String note = rs.getString("note");
                    String accountantID = rs.getString("accountantID");
                    int orderID = Integer.parseInt(rs.getString("orderID"));
                    String sellerID = rs.getString("sellerID");
                    Date DateP = rs.getDate("DateP");
                    issue = new IssueDTO(issueID, note, accountantID, sellerID, orderID, DateP);
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
        return issue;
    }

    public boolean save(int iID) throws SQLException {
        boolean check = true;
        Connection con = null;
        PreparedStatement ptm = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(SAVE);
                ptm.setInt(1, iID);
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
