<%-- 
    Document   : listdetail
    Created on : Oct 18, 2024, 11:52:51 PM
    Author     : DAO TUAN ANH
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/productionplandetails.css">
        <style>
            .back-button {
                position: absolute;
                top: 20px;
                left: 20px;
                padding: 8px 12px;
                font-size: 16px;
                background-color: rgba(0, 123, 255, 0.1); /* Nền xanh nhạt trong suốt */
                color: #007BFF;
                border: 1px solid #007BFF;
                border-radius: 5px;
                cursor: pointer;
                display: flex;
                align-items: center;
                font-weight: bold;
                transition: background-color 0.3s, color 0.3s;
            }
            .back-button::before {
                content: "←";
                font-size: 20px;
                margin-right: 8px;
            }
            .back-button:hover {
                background-color: #007BFF; /* Nền đậm hơn khi hover */
                color: white; /* Đổi màu chữ thành trắng khi hover */
            }
    </style>
    <button class="back-button" onclick="window.location.href='/Assignment_PRJ301/productionplan/list'"></button>
        
        
        
        
        
        
        
        
        
    </head>  
    <body>
        <h1>${requestScope.plan.name} Detail</h1>
        
        
        
        <form action="detail" method="POST">
            <table border="1px">
                <thead>
                    <tr>
                        <td colspan="2">Product</td>
                        <c:forEach items="${requestScope.plan.headers}" var="h">
                            <td>${h.product.name}</td>
                        </c:forEach>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.datePlan}" var="d">
                    <input type="hidden" name="date" value="${d}">
                        <tr>
                            
                            <td rowspan="3">${d}</td>
                            <td>Shift 1<input type="hidden" name="sid${d}" value="1"></td>
                            <c:forEach items="${requestScope.plan.headers}" var="h">
                            <input type="hidden" name="hid${d}" value="${h.id}">
                            <td><input type="text" name="quantity${h.id}1${d}"
                                       <c:forEach items="${requestScope.details}" var="detail">
                                           <c:if test="${(detail.header.id eq h.id) and (detail.date eq d)  and (detail.sid eq 1)}"> value="${detail.quantity}"</c:if>
                                       </c:forEach>></td>
                            </c:forEach>
                            
                        </tr>
                        <tr>
                            <td>Shift 2<input type="hidden" name="sid${d}" value="2"></td>
                            <c:forEach items="${requestScope.plan.headers}" var="h">
                           
                                
                                           <td><input type="text" name="quantity${h.id}2${d}"
                                       <c:forEach items="${requestScope.details}" var="detail">
                                           <c:if test="${(detail.header.id eq h.id) and (detail.date eq d)  and (detail.sid eq 2)}"> value="${detail.quantity}"</c:if>
                                       </c:forEach>></td>
                            </c:forEach>      
                        </tr>
                        <tr>
                            <td>Shift 3<input type="hidden" name="sid${d}" value="3"></td>
                            <c:forEach items="${requestScope.plan.headers}" var="h">
                           
                                <td><input type="text" name="quantity${h.id}3${d}"
                                       <c:forEach items="${requestScope.details}" var="detail">
                                           <c:if test="${(detail.header.id eq h.id) and (detail.date eq d)  and (detail.sid eq 3)}"> value="${detail.quantity}"</c:if>
                                       </c:forEach>></td>
                            </c:forEach>
                        </tr>
                    </c:forEach>
                </tbody>
        </table>
            <input type="submit" value="Save">
        </form>     
    </body>
</html>
