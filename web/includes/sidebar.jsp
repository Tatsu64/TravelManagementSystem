<div class="container-fluid">
    <div class="row flex-nowrap">
        <div class="col-auto col-md-3 col-xl-2 px-sm-2 px-0 bg-dark">
            <div class="d-flex flex-column align-items-center align-items-sm-start px-3 pt-2 text-white min-vh-100">
            <br>
            <br>
                <a href="/" class="d-flex align-items-center pb-3 mb-md-0 me-md-auto text-white text-decoration-none">
                    <span class="fs-5 d-none d-sm-inline">Admin Menu</span>
                </a>
                <ul class="nav nav-pills flex-column mb-sm-auto mb-0 align-items-center align-items-sm-start" id="menu">
                    <li class="nav-item">
                        <a href="Home" class="nav-link align-middle px-0">
                            <i class="fs-4 bi-house"></i> <span class="ms-1 d-none d-sm-inline">Home</span>
                        </a>
                    </li>
                    <li>
                        <a href="#submenu1" data-bs-toggle="collapse" class="nav-link px-0 align-middle">
                            <i class="fs-4 bi-speedometer2"></i> <span class="ms-1 d-none d-sm-inline">Dashboard</span> </a>
                    </li>
                    <li>
                        <a href="ManageEmployeeServlet" class="nav-link px-0 align-middle">
                            <i></i> <span class="ms-1 d-none d-sm-inline">Manage Employee</span></a>
                    </li>
                    <li>
                        <a href="LocationServlet" data-bs-toggle="collapse" class="nav-link px-0 align-middle ">
                            <i></i> <span class="ms-1 d-none d-sm-inline">Manage Location</span></a>
                    </li>
                    <li>
                        <a href="TransportationServlet" data-bs-toggle="collapse" class="nav-link px-0 align-middle">
                            <i></i> <span class="ms-1 d-none d-sm-inline">Manage Transportation</span> </a>
                    </li>
                    <li>
                        <a href="ApprovalTourServlet" class="nav-link px-0 align-middle">
                            <i></i> <span class="ms-1 d-none d-sm-inline">Manage Tour</span> </a>
                    </li>
                    <li>
                        <a href="ManageHotelsServlet" class="nav-link px-0 align-middle">
                            <i></i> <span class="ms-1 d-none d-sm-inline">Manage Hotel</span> </a>
                    </li>
                    <li>
                        <a href="ManageRestaurantServlet" class="nav-link px-0 align-middle">
                            <i></i> <span class="ms-1 d-none d-sm-inline">Manage Restaurant</span> </a>
                    </li>
                    <li>
                        <a href="ManageReviewServlet" class="nav-link px-0 align-middle">
                            <i></i> <span class="ms-1 d-none d-sm-inline">Manage Review</span> </a>
                    </li>
                    <li>
                        <a href="BillServlet" class="nav-link px-0 align-middle">
                            <i></i> <span class="ms-1 d-none d-sm-inline">Manage Bill</span> </a>
                    </li>
                    <li>
                        <a href="ManageUserServlet" class="nav-link px-0 align-middle">
                            <i></i> <span class="ms-1 d-none d-sm-inline">Manage User</span> </a>
                    </li>
                    <li>
                        <a href="LogoutServlet" class="nav-link px-0 align-middle">
                            <i></i> <span class="ms-1 d-none d-sm-inline">Sign out</span> </a>
                    </li>
                </ul>
                <hr>
                <div class="dropdown pb-4">
                    <a href="#" class="d-flex align-items-center text-white text-decoration-none dropdown-toggle" id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
                        <img src="https://github.com/mdo.png" alt="hugenerd" width="30" height="30" class="rounded-circle">
                        <span class="d-none d-sm-inline mx-1">${sessionScope.auth.name}</span>
                    </a>
                </div>
            </div>
        </div>
                   

    