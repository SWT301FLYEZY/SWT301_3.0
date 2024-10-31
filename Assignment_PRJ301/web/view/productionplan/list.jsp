<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Production Plan List</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/productionplanlists.css">
</head>
<body>
    <h1>Production Plan List</h1>
    <table border="1px">
        <tr>
            <td style="font-weight: bold">ID</td>
            <td style="font-weight: bold">Name</td>
            <td style="font-weight: bold">StartDate</td>
            <td style="font-weight: bold">EndDate</td>
            <td style="font-weight: bold">Quantity</td>
            <td style="font-weight: bold">Product</td>
            <td style="font-weight: bold">Estimation</td>
            <td style="font-weight: bold">Action</td>
            
            
        </tr>
        <c:forEach items="${requestScope.plans}" var="p">
            <tr class="even" >
                <td rowspan="${p.headers.size()}">${p.id}</td>
                <td rowspan="${p.headers.size()}">${p.name}</td>
                <td rowspan="${p.headers.size()}">${p.start}</td>
                <td  rowspan="${p.headers.size()}">${p.end}</td>
                <td>
                   
                       ${p.headers[0].quantity}<br/>
                    
                </td>
                <td>
                    ${p.headers[0].product.name}<br/>
                </td>
                <td>
                    ${p.headers[0].estimatedeffort}<br/>
                </td> 
               <td  rowspan="${p.headers.size()}">
                   <button onclick="location.href='detail?plid=${p.id}'">View</button>
                   <!-- Form for delete action -->
                    <form action="list" method="post" style="display:inline;">
                        <input type="hidden" name="planId" value="${p.id}">
                        <input type="hidden" name="action" value="delete">
                        <button type="submit" class="delete" onclick="return confirm('Are you sure you want to delete this plan?');">Delete</button>
                    </form>
                   
               </td>
           

            </tr>
            <c:forEach var="i" begin="1" end="${p.headers.size()-1}" >
                <tr>
                <td>
                   
                       ${p.headers[i].quantity}<br/>
                    
                </td>
                <td>
                   
                       ${p.headers[i].product.name}<br/>
                    
                </td>
                <td>
                    ${p.headers[i].estimatedeffort}<br/>
                </td>
            </tr>
            </c:forEach>
            
        </c:forEach>
    </table>
</body>
</html>
