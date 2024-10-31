/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import dal.*;
import dal.DBContext;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ProductionPlan;
import java.sql.*;
import model.Department;
import model.Product;
import model.ProductionPlanHeader;

/**
 *
 * @author sonnt-local
 */
public class ProductionPlanDBContext extends DBContext<ProductionPlan> {

    @Override
    public void insert(ProductionPlan model) {
        try {
            connection.setAutoCommit(false);
            String sql_insert_plan = "INSERT INTO [Plans]\n"
                    + "           ([plname]\n"
                    + "           ,[startdate]\n"
                    + "           ,[enddate]\n"
                    + "           ,[did])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";

            PreparedStatement stm_insert_plan = connection.prepareStatement(sql_insert_plan);
            stm_insert_plan.setString(1, model.getName());
            stm_insert_plan.setDate(2, model.getStart());
            stm_insert_plan.setDate(3, model.getEnd());
            stm_insert_plan.setInt(4, model.getDept().getId());
            stm_insert_plan.executeUpdate();

            String sql_select_plan = "SELECT @@IDENTITY as plid";
            PreparedStatement stm_select_plan = connection.prepareStatement(sql_select_plan);
            ResultSet rs = stm_select_plan.executeQuery();
            if (rs.next()) {
                model.setId(rs.getInt("plid"));
            }

            String sql_insert_header = "INSERT INTO [PlanHeaders]\n"
                    + "           ([plid]\n"
                    + "           ,[pid]\n"
                    + "           ,[quantity]\n"
                    + "           ,[estimatedeffort])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";

            for (ProductionPlanHeader header : model.getHeaders()) {
                PreparedStatement stm_insert_header = connection.prepareStatement(sql_insert_header);
                stm_insert_header.setInt(1, model.getId());
                stm_insert_header.setInt(2, header.getProduct().getId());
                stm_insert_header.setInt(3, header.getQuantity());
                stm_insert_header.setFloat(4, header.getEstimatedeffort());
                stm_insert_header.executeUpdate();
            }

            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(ProductionPlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(ProductionPlanDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(ProductionPlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductionPlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public void update(ProductionPlan model) {
        
    }

    @Override
    public void delete(ProductionPlan model) {
       try {
        connection.setAutoCommit(false);

        // Xóa dữ liệu trong PlanHeaders liên quan đến ProductionPlan
        String sql_delete_headers = "DELETE FROM [PlanHeaders] WHERE plid = ?";
        PreparedStatement stm_delete_headers = connection.prepareStatement(sql_delete_headers);
        stm_delete_headers.setInt(1, model.getId());
        stm_delete_headers.executeUpdate();

        // Xóa bản ghi chính trong Plans
        String sql_delete_plan = "DELETE FROM [Plans] WHERE plid = ?";
        PreparedStatement stm_delete_plan = connection.prepareStatement(sql_delete_plan);
        stm_delete_plan.setInt(1, model.getId());
        stm_delete_plan.executeUpdate();

        connection.commit();
    } catch (SQLException ex) {
        Logger.getLogger(ProductionPlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
        try {
            connection.rollback();
        } catch (SQLException ex1) {
            Logger.getLogger(ProductionPlanDBContext.class.getName()).log(Level.SEVERE, null, ex1);
        }
    } finally {
        try {
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            Logger.getLogger(ProductionPlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductionPlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    }

    @Override
    public ArrayList<ProductionPlan> list() {
        ArrayList<ProductionPlan> plans = new ArrayList<>();
        ArrayList<ProductionPlanHeader> headers = new ArrayList<>();

        PreparedStatement stm = null;
        String sql = "select p.plid, plname,startdate,enddate,did,pr.pid,pname,quantity,estimatedeffort\n"
                + "from Plans p join PlanHeaders  h on p.plid=h.plid\n"
                + "join Products pr on pr.pid=h.pid";
        try {

            stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            ProductionPlan cPlan = new ProductionPlan();
            cPlan.setId(-1);
            while (rs.next()) {
                int plid = rs.getInt("plid");
                if (plid != cPlan.getId()) {
                    cPlan = new ProductionPlan();
                    cPlan.setId(plid);
                    cPlan.setName(rs.getString("plname"));
                    cPlan.setStart(rs.getDate("startdate"));
                    cPlan.setEnd(rs.getDate("enddate"));
                    plans.add(cPlan);

                    Department d = new Department();
                    d.setId(rs.getInt("did"));
                    cPlan.setDept(d);
                    headers = new ArrayList<>();

                }

                Product p = new Product();
                p.setId(rs.getInt("pid"));
                p.setName(rs.getString("pname"));

                ProductionPlanHeader h = new ProductionPlanHeader();

                h.setProduct(p);
                h.setQuantity(rs.getInt("quantity"));
                h.setEstimatedeffort(rs.getFloat("estimatedeffort"));
                headers.add(h);
                cPlan.setHeaders(headers);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductionPlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductionPlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return plans;
    }

    @Override
    public ProductionPlan get(int id) {
        PreparedStatement stm = null;
        String sql = "select p.plid,pname, plname,startdate,enddate,did,h.phid,h.pid,quantity,estimatedeffort\n"
                + "from Plans p join PlanHeaders h on p.plid=h.plid\n"
                + "join Products pr on pr.pid=h.pid\n"
                + "where p.plid=?";
        ArrayList<ProductionPlanHeader> headers = new ArrayList<>();
        ProductionPlan cPlan = new ProductionPlan();
        try {

            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();

            cPlan.setId(-1);
            while (rs.next()) {
                int plid = rs.getInt("plid");
                if (plid != cPlan.getId()) {
                    cPlan = new ProductionPlan();
                    cPlan.setId(plid);
                    cPlan.setName(rs.getString("plname"));
                    cPlan.setStart(rs.getDate("startdate"));
                    cPlan.setEnd(rs.getDate("enddate"));

                    Department d = new Department();
                    d.setId(rs.getInt("did"));
                    cPlan.setDept(d);
                    headers = new ArrayList<>();

                }

                Product p = new Product();
                p.setId(rs.getInt("pid"));
                p.setName(rs.getString("pname"));

                ProductionPlanHeader h = new ProductionPlanHeader();
                h.setProduct(p);
                h.setId(rs.getInt("phid"));
                h.setQuantity(rs.getInt("quantity"));
                h.setEstimatedeffort(rs.getFloat("estimatedeffort"));
                headers.add(h);
                cPlan.setHeaders(headers);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductionPlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cPlan;
    }
    
    

}
