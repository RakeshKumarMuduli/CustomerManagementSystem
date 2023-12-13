<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>

        <head>
            <title>Customer Management Application</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        </head>

        <body>

            <header>
                <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
                    <div>
                        <a href="#" class="navbar-brand"> Customer Management App </a>
                    </div>

                    <ul class="navbar-nav">
                        <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Customers</a></li>
                    </ul>
                </nav>
            </header>
            <br>

            <div class="row">
                <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

                <div class="container">
                    <h3 class="text-center">List of Customers</h3>
                    <hr>
                    <div class="container text-left">

                        <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add New Customers</a>
                    </div>
                    <br>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>FirstName</th>
                                <th>LastName</th>
                                <th>Address</th>
                                <th>City</th>
                                <th>State</th>                                
                                <th>Email</th>
                                <th>Phone</th>
                                
                            </tr>
                        </thead>
                        <tbody>
                            <!--   for (Todo todo: todos) {  -->
                            <c:forEach var="user" items="${listCustomer}">

                                <tr>
                                    <td>
                                        <c:out value="${user.firstName}" />
                                    </td>
                                    <td>
                                        <c:out value="${user.lastName}" />
                                    </td>
                                    
                                    <td>
                                        <c:out value="${user.address}" />
                                    </td>
                                    <td>
                                        <c:out value="${user.city}" />
                                    </td>
                                    <td>
                                        <c:out value="${user.state}" />
                                    </td>
                                    
                                    
                                    <td>
                                        <c:out value="${user.email}" />
                                    </td>
                                    
                                    <td>
                                        <c:out value="${user.phone}" />
                                    </td>
                                    
                                    <td><a href="edit?email=<c:out value='${user.email}' /> ">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp; <a href="delete?email=<c:out value='${user.email}' />">Delete</a></td>
                                </tr>
                            </c:forEach>
                            <!-- } -->
                        </tbody>

                    </table>
                </div>
            </div>
        </body>

        </html>