<%-- 
    Document   : Home
    Created on : Nov 1, 2024, 7:48:38 AM
    Author     : DAO TUAN ANH
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Production Scheduling System</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
    <style>
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }
        body {
            font-family: 'Roboto', sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            background: linear-gradient(135deg, #f4f4f9, #dfe4ea);
        }
        .container {
            text-align: center;
            padding: 40px;
            border-radius: 15px;
            background: white;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
        }
        h1 {
            color: #2d3436;
            font-size: 2.5em;
            font-weight: 700;
            margin-bottom: 20px;
        }
        .button-container {
            display: flex;
            gap: 20px;
            justify-content: center;
            margin-top: 30px;
        }
        .button {
            padding: 12px 30px;
            font-size: 16px;
            font-weight: 500;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            transition: transform 0.3s, box-shadow 0.3s;
        }
        .button:hover {
            transform: translateY(-3px);
            box-shadow: 0 8px 15px rgba(0, 0, 0, 0.15);
        }
        .create-btn {
            background: linear-gradient(135deg, #60a5fa, #3b82f6);
            color: white;
        }
        .view-btn {
            background: linear-gradient(135deg, #60a5fa, #3b82f6);
            color: white;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Production Scheduling System</h1>
        <div class="button-container">
            <button class="button create-btn" onclick="window.location.href='/Assignment_PRJ301/productionplan/create'">Create Plan</button>
            <button class="button view-btn" onclick="window.location.href='/Assignment_PRJ301/productionplan/list'">View Plan</button>
        </div>
    </div>
</body>
</html>