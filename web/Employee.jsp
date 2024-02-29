<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Employee List</title>
        <%@ include file="includes/header.jsp" %>
    </head>
    <body>
        <br>
        <br>
        <br>
        <br>
        <div class="container">
            <h2>Employee List</h2>
            <table class="table table-light">
                <thead>
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Name</th>
                        <th scope="col">Email</th>
                        <th scope="col">Position</th>
                        <th scope="col">Action</th> 
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${employeeList}" var="employee">
                        <tr>
                            <td>${employee.employeeId}</td>
                            <td>${employee.fullName}</td>
                            <td>${employee.email}</td>
                            <td>${employee.position}</td>
                            <td>
                                <!-- Update button -->
                                <button onclick="location.href = 'EditDeleteEmployeeServlet?employeeId=${employee.employeeId}&action=update'" type="button" class="btn btn-warning">Update</button>

                                <!-- Delete button -->
                                <button onclick="if (confirm('Are you sure you want to delete this employee?'))
                                            location.href = 'EditDeleteEmployeeServlet?employeeId=${employee.employeeId}&action=delete'" type="button" class="btn btn-danger">Delete</button>

                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <br>
            <br>
            <a href="CreateEmployee.jsp" class="btn btn-primary">Create Employees</a>
            <a href="Home" class="btn btn-primary">Back to Homepage</a>
        </div>
        <footer>
            <%@ include file="includes/footer.jsp" %>
        </footer>
    </body>
</html>
