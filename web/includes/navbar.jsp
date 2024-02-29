<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-expand-lg navbar-light px-4 px-lg-5 py-3 py-lg-0">
    <a href="Home" class="navbar-brand p-0">
        <h1 class="text-primary m-0"><i class="fa fa-map-marker-alt me-3"></i>Tourist</h1>
        <!-- <img src="img/logo.png" alt="Logo"> -->
    </a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
        <span class="fa fa-bars"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarCollapse">
        <div class="navbar-nav ms-auto py-0">
            <c:if test="${sessionScope.auth == null}"> 
                <a href="Home" class="nav-item nav-link">Home</a>
                <div class="nav-item dropdown">
                </div>
                <a href="contact.jsp" class="nav-item nav-link">Contact</a>
            </c:if>

            <c:if test="${sessionScope.auth.role == 'User'}"> 
                <a href="Home" class="nav-item nav-link">Home</a>
                <div class="nav-item dropdown">
                    <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">Pages</a>
                    <div class="dropdown-menu m-0">
                        <a href="destination.jsp" class="dropdown-item">Destination</a>
                        <a href="booking.jsp" class="dropdown-item">Booking</a>
                        <a href="Change-Password.jsp" class="dropdown-item">Change Password</a>
                        <a href="404.jsp" class="dropdown-item">404 Page</a>
                    </div>

                </div>
                <a href="contact.jsp" class="nav-item nav-link">Contact</a>
                <a class="nav-link me-4" href="profile.jsp">Profile</a>
                <a class="nav-link me-4" href="LogoutServlet">Log out</a>
                <%--<a class="nav-link me-4" href="EmployeeListServlet?">Create Tour</a>--%>
                <a class="nav-link me-4" href="#">HELLO ${sessionScope.auth.name}</a>
            </c:if>

            <c:if test="${sessionScope.auth.role == 'Admin'}">
                <div class="nav-item dropdown">
                    <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">Manage</a>
                    <div class="dropdown-menu m-0">
                        <a href="destination.jsp" class="dropdown-item">Employees</a>
                        <a href="ApprovalTourServlet?" class="dropdown-item">Tours</a>
                        <a href="team.jsp" class="dropdown-item">Locations</a>
                        <a href="404.jsp" class="dropdown-item">Restaurants</a>
                        <a href="team.jsp" class="dropdown-item">Transportation</a>
                        <a href="404.jsp" class="dropdown-item">Reviews</a>
                    </div>

                </div>

                <a class="nav-link me-4" href="LogoutServlet">Log out</a>
                <a class="nav-link me-4" href="#">HELLO ${sessionScope.auth.name}</a>
            </c:if>
        </div>
        <c:if test="${sessionScope.auth == null}">
            <a href="login.jsp" class="btn btn-primary rounded-pill py-2 px-4">Login</a>
        </c:if>
    </div>
</nav>