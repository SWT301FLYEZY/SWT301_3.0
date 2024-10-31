/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import model.ProductionPlanDetail;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ProductionPlanHeader;

/**
 *
 * @author ASUS
 */
public class ProductionPlanDetailDBContext extends DBContext<ProductionPlanDetail> {
    private String url = "jdbc:sqlserver://localhost\\MSSQLSERVER02:1433;databaseName=ProductionSchedulingSystem_DB;trustServerCertificate=true;";
    private String user = "tuananh";
    private String password = "123";
    @Override
    public void insert(ProductionPlanDetail model) {
        String sql = "INSERT INTO [PlanDetails]\n"
                + "           ([phid]\n"
                + "           ,[sid]\n"
                + "           ,[date]\n"
                + "           ,[quantity])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
        PreparedStatement stm = null;

        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, model.getHeader().getId());
            stm.setInt(2, model.getSid());
            stm.setDate(3, model.getDate());
            stm.setInt(4, model.getQuantity());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductionPlanDetailDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        }
    }

    @Override
    public void update(ProductionPlanDetail model) {
       
    }

    @Override
    public void delete(ProductionPlanDetail model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<ProductionPlanDetail> list() {
        String sql = "SELECT [pdid]\n"
                + "      ,[phid]\n"
                + "      ,[sid]\n"
                + "      ,[date]\n"
                + "      ,[quantity]\n"
                + "  FROM [PlanDetails]";
        ArrayList<ProductionPlanDetail> details= new ArrayList<>();
        PreparedStatement stm=null;
        try {
            stm= connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                ProductionPlanDetail detail=new ProductionPlanDetail();
                detail.setDate(rs.getDate("date"));
                detail.setQuantity(rs.getInt("quantity"));
                detail.setSid(rs.getInt("sid"));
                ProductionPlanHeader header= new ProductionPlanHeader();
                header.setId(rs.getInt("phid"));
                detail.setHeader(header);
                details.add(detail);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductionPlanDetailDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductionPlanDetailDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        
        return details;
    }

    @Override
    public ProductionPlanDetail get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
  public void update(int id, int quantity) {
        String sql = "update PlanDetails\n"
                + "set quantity = ?\n"
                + "where pdid = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, quantity);
            ps.executeUpdate();
        } catch (Exception e) {

        }
    }


public boolean quantityExists(ProductionPlanDetail detail) {
    boolean exists = false;
    String query = "SELECT COUNT(*) FROM [PlanDetails] WHERE sid = ? AND header_id = ? AND date = ?";

    try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setInt(1, detail.getSid());
        statement.setInt(2, detail.getHeader().getId());
        statement.setDate(3, detail.getDate());

        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                exists = resultSet.getInt(1) > 0; 
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return exists;
}


}
