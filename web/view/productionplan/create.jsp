<%-- 
    Document   : create
    Created on : Oct 16, 2024, 11:19:35 AM
    Author     : DAO TUAN ANH
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
       <head>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>  
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/create.css">
</head>
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
    <button class="back-button" onclick="window.location.href='/Assignment_PRJ301/home'"></button>

</head>
    </head>
    <body>
        <form action="create" method="POST">
            Plan Name: <input type="text" name="name"/> <br/>
            From: <input type="date" name="from"/> To: <input type="date" name="to"/> <br/>
            Workshop: <select name="did">
                <c:forEach items="${requestScope.depts}" var="d">
                    <option value="${d.id}">${d.name}</option>
                </c:forEach>
            </select>
            <br/>
            <table border="1px">
                <tr>
                    <td>Product</td>
                    <td>Quantity</td>
                    <td>Estimated Effort</td>
                </tr>
                <c:forEach items="${requestScope.products}" var="p">
                <tr>
                    <td>${p.name}<input type="hidden" name="pid" value="${p.id}"></td>
                    <td><input type="text" name="quantity${p.id}"/></td>
                    <td><input type="text" name="effort${p.id}"/></td>
                </tr>    
                </c:forEach>
            </table>
            <input type="submit" value="Save"/>
        </form>
    </body>
</html>
