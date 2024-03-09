<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Employee</title>
    <%@ include file="includes/header.jsp" %>
</head>
<body>
    <br>
    <br>
    <br>
    
    <br>
    <br>
    <div class="container">
        <h2>Update Employee</h2>
        <form id="updateEmployeeForm" action="EditDeleteEmployeeServlet" method="post">
            <input type="hidden" name="employeeId" value="${employee.employeeId}">
            <div class="form-group">
                <label for="fullName">Full Name:</label>
                <input type="text" class="form-control" id="fullName" name="fullName" value="${employee.fullName}" required>
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" class="form-control" id="email" name="email" value="${employee.email}" required>
            </div>
            <div class="form-group">
                <label for="position">Position:</label>
                <input type="text" class="form-control" id="position" name="position" value="${employee.position}" required>
            </div>
            <br>
            <button type="submit" class="btn btn-primary">Update</button>
            <button type="button" onclick="history.back();" class="btn btn-secondary">Cancel</button>
        </form>
    </div>
 <!-- Bootstrap JS and jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Success Modal -->
    <div class="modal fade" id="successModal" tabindex="-1" role="dialog" aria-labelledby="successModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="successModalLabel">Success!</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    Employee details updated successfully.
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
    <script>
        // Show success modal after form submission
        document.getElementById("updateEmployeeForm").addEventListener("submit", function(event) {
            event.preventDefault(); // Prevent default form submission
            $('#successModal').modal('show'); // Show success modal
            // Optional: Submit the form after a delay if needed
             setTimeout(function() {
                 document.getElementById("updateEmployeeForm").submit();
             }, 2000); // Submit after 2 seconds
        });
    </script>
</body>
</html>
