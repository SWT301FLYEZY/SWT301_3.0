/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.productionplan;

import dal.DepartmentDBContext;
import dal.ProductionPlanDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Department;
import model.ProductionPlan;

/**
 *
 * @author Admin
 */
public class ProductionPlanListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductionPlanDBContext dbPlan = new ProductionPlanDBContext();
        ArrayList<ProductionPlan> plans = new ArrayList<>();
        plans = dbPlan.list();

        DepartmentDBContext dbDept = new DepartmentDBContext();

        request.setAttribute("plans", plans);

        request.getRequestDispatcher("../view/productionplan/list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if ("delete".equals(action)) {
            int planId = Integer.parseInt(request.getParameter("planId"));
            ProductionPlanDBContext dbPlan = new ProductionPlanDBContext();
            ProductionPlan planToDelete = new ProductionPlan();
            planToDelete.setId(planId);
            
            dbPlan.delete(planToDelete);
        }
        
        response.sendRedirect("list"); // Quay lại trang danh sách sau khi xóa
    

    }

}
