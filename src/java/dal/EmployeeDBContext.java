/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.Employees;
import model.Departments;
import model.Salaries;
import dal.DBContext;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class EmployeeDBContext extends DBContext<Employees> {

   public ArrayList<Employees> search(Integer eid, String name, Boolean gender, Date from, Date to, String address, Integer did, String phonenumber, Integer sid) {
        String sql = "SELECT e.eid, e.ename, d.did, d.dname, e.phonenumber, e.address, s.sid, s.slevel, s.salary, e.gender, e.dob\n"
                + "FROM Employees e\n"
                + "INNER JOIN Departments d ON e.did = d.did\n"
                + "INNER JOIN Salaries s ON s.sid = e.sid\n"
                + "WHERE 1=1";
        ArrayList<Employees> emps = new ArrayList<>();
        ArrayList<Object> paramValues = new ArrayList<>();

        // Điều kiện tìm kiếm theo id
        if (eid != null) {
            sql += " AND e.eid = ?";
            paramValues.add(eid);
        }
        // Điều kiện tìm kiếm theo tên (LIKE)
        if (name != null && !name.trim().isEmpty()) {
            sql += " AND e.ename LIKE ?";
            paramValues.add("%" + name + "%"); // Sử dụng % trước và sau tên
        }
         // Điều kiện tìm kiếm theo ngày sinh từ
        if (from != null) {
            sql += " AND e.dob >= ?";
            paramValues.add(from);
        }
        // Điều kiện tìm kiếm theo ngày sinh đến
        if (to != null) {
            sql += " AND e.dob <= ?";
            paramValues.add(to);
        }

         // Điều kiện tìm kiếm theo giới tính
        if (gender != null) {
            sql += " AND e.gender = ?";
            paramValues.add(gender ? 1 : 0); // Chuyển boolean thành 1 (nam) và 0 (nữ)
        }
        // Điều kiện tìm kiếm theo địa chỉ (LIKE)
        if (address != null && !address.trim().isEmpty()) {
            sql += " AND e.address LIKE ?";
            paramValues.add("%" + address + "%"); // Sử dụng % trước và sau địa chỉ
        }
        // Điều kiện tìm kiếm theo số điện thoại
        if (phonenumber != null && !phonenumber.trim().isEmpty()) {
            sql += " AND e.phonenumber LIKE ?";
            paramValues.add("%" + phonenumber + "%"); // Tìm kiếm giống LIKE với số điện thoại
        }
        
        // Điều kiện tìm kiếm theo mã phòng ban
        if (did != null) {
            sql += " AND d.did = ?";
            paramValues.add(did);
        }
        // Điều kiện tìm kiếm theo Salary ID (sid)
        if (sid != null) {
            sql += " AND s.sid = ?";
            paramValues.add(sid);
        }
        PreparedStatement stm = null;

        try {
            // Chuẩn bị câu truy vấn với các tham số
            stm = connection.prepareStatement(sql);
            for (int i = 0; i < paramValues.size(); i++) {
                stm.setObject((i + 1), paramValues.get(i));
            }

            // Thực thi truy vấn
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Employees e = new Employees();
                e.setId(rs.getInt("eid"));
                e.setName(rs.getNString("ename"));
                e.setGender(rs.getBoolean("gender")); // Đọc giá trị giới tính
                e.setDob(rs.getDate("dob"));
                e.setAddress(rs.getNString("address"));
                e.setPhonenumber(rs.getNString("phonenumber")); // Đọc số điện thoại

                Departments d = new Departments();
                d.setId(rs.getInt("did"));
                d.setName(rs.getNString("dname"));

                e.setDept(d); // Gán phòng ban cho nhân viên

                Salaries s = new Salaries();
                s.setId(rs.getInt("sid")); // Đọc Salary ID
                s.setLevel(rs.getNString("slevel"));
                s.setSalary(rs.getBigDecimal("salary"));

                e.setSals(s); // Gán Salary ID cho nhân viên

                emps.add(e);

            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return emps;
    }

    @Override
    public void insert(Employees model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Employees model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Employees model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Employees> list() {
        ArrayList<Employees> emps = new ArrayList<>();
        PreparedStatement stm = null;
        try {
            String sql = "String sql = \"SELECT e.eid, e.ename, d.did, e.phonenumber, e.address, s.sid, e.gender, e.dob\\n\"\n"
                    + "                + \"FROM Employees e\\n\"\n"
                    + "                + \"INNER JOIN Departments d ON e.did = d.did\\n\"\n"
                    + "                + \"INNER JOIN Salaries s ON s.sid = e.sid\\n\"\n"
                    + "  + \"WHERE 1=1\";";

            stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Employees e = new Employees();
                e.setId(rs.getInt("eid"));
                e.setName(rs.getNString("ename"));
                e.setGender(rs.getBoolean("gender")); // Đọc giá trị giới tính
                e.setDob(rs.getDate("dob"));
                e.setAddress(rs.getNString("address"));
                e.setPhonenumber(rs.getNString("phonenumber")); // Đọc số điện thoại

                Departments d = new Departments();
                d.setId(rs.getInt("did"));
                d.setName(rs.getNString("dname"));

                e.setDept(d); // Gán phòng ban cho nhân viên

                Salaries s = new Salaries();
                s.setId(rs.getInt("sid")); // Đọc Salary ID
                s.setLevel(rs.getNString("slevel"));
                s.setSalary(rs.getBigDecimal("salary"));

                e.setSals(s); // Gán Salary ID cho nhân viên

                emps.add(e);

            }

        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
                connection.close();

            } catch (SQLException ex) {
                Logger.getLogger(EmployeeDBContext.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return emps;
    }

    @Override
    public Employees get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
