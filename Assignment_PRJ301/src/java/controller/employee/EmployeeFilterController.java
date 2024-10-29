/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.employee;


import dal.EmployeeDBContext;
import dal.DepartmentDBContext;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import model.Employees;
import model.Departments;
import model.auth.Users;
import dal.DepartmentDBContext;
import dal.SalaryDBContext;
import model.Salaries;

/**
 *
 * @author Admin
 */
public class EmployeeFilterController extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Lấy các tham số từ request
        String raw_eid = request.getParameter("eid");
        String raw_ename = request.getParameter("ename");
        String raw_gender = request.getParameter("gender");
        String raw_from = request.getParameter("from");
        String raw_to = request.getParameter("to");
        String raw_address = request.getParameter("address");
        String raw_did = request.getParameter("did");
        String raw_phonenumber = request.getParameter("phonenumber");
        String raw_sid = request.getParameter("sid");

        // Xử lý dữ liệu từ request, bao gồm việc chuyển đổi các giá trị null, blank
        Integer eid = (raw_eid != null) && (!raw_eid.isBlank())
                ? Integer.parseInt(raw_eid) : null;
        String ename = raw_ename;
        Boolean gender = (raw_gender!=null)&&(!raw_gender.equals("both"))
                ?raw_gender.equals("male"):null;
        Date from = (raw_from != null) && (!raw_from.isBlank())
                ? Date.valueOf(raw_from) : null;
        Date to = (raw_to != null) && (!raw_to.isBlank())
                ? Date.valueOf(raw_to) : null;
        String address = raw_address;
        Integer did = (raw_did != null) && (!raw_did.equals("-1"))
                ? Integer.parseInt(raw_did) : null;
        String phonenumber = raw_phonenumber;
        Integer sid = (raw_sid != null) && (!raw_sid.isBlank())
        ? Integer.parseInt(raw_sid) : null;
        
        
        EmployeeDBContext dbEmp = new EmployeeDBContext();
        ArrayList<Employees> emps = dbEmp.search(eid, ename, gender, from, to, address, did, phonenumber, sid);
        
        DepartmentDBContext dbDept = new DepartmentDBContext();
        ArrayList<Departments> depts = dbDept.list();
        
        SalaryDBContext dbSals = new SalaryDBContext();
        ArrayList<Salaries> sals = dbSals.list();
        
        
        request.setAttribute("depts", depts);
        request.setAttribute("emps", emps);
        request.setAttribute("sals", sals);

        request.getRequestDispatcher("../view/employee/filter.jsp").forward(request, response);

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

}
