
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <%@include file="includes/header.jsp" %>
    </head>

    <body>
        <!-- Spinner Start -->
        <div id="spinner" class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
            <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
                <span class="sr-only">Loading...</span>
            </div>
        </div>
        <!-- Spinner End -->

        <!-- Topbar Start -->
        <div class="container-fluid bg-dark px-5 d-none d-lg-block">
            <div class="row gx-0">
                <div class="col-lg-8 text-center text-lg-start mb-2 mb-lg-0">
                    <div class="d-inline-flex align-items-center" style="height: 45px;">
                        <small class="me-3 text-light"><i class="fa fa-map-marker-alt me-2"></i> 286 Nguyen Van Linh Street, Da Nang, Viet Nam</small>
                        <small class="me-3 text-light"><i class="fa fa-phone-alt me-2"></i>091-756-5960</small>
                        <small class="text-light"><i class="fa fa-envelope-open me-2"></i>C4T@gmail.com</small>
                    </div>
                </div>
                <div class="col-lg-4 text-center text-lg-end">
                    <div class="d-inline-flex align-items-center" style="height: 45px;">
                        <a class="btn btn-sm btn-outline-light btn-sm-square rounded-circle me-2" href=""><i class="fab fa-twitter fw-normal"></i></a>
                        <a class="btn btn-sm btn-outline-light btn-sm-square rounded-circle me-2" href=""><i class="fab fa-facebook-f fw-normal"></i></a>
                        <a class="btn btn-sm btn-outline-light btn-sm-square rounded-circle me-2" href=""><i class="fab fa-linkedin-in fw-normal"></i></a>
                        <a class="btn btn-sm btn-outline-light btn-sm-square rounded-circle me-2" href=""><i class="fab fa-instagram fw-normal"></i></a>
                        <a class="btn btn-sm btn-outline-light btn-sm-square rounded-circle" href=""><i class="fab fa-youtube fw-normal"></i></a>
                    </div>
                </div>
            </div>
        </div>
        <!-- Topbar End -->

        <!-- Navbar & Hero Start -->
        <div class="container-fluid position-relative p-0">
            <%@include file="includes/navbar.jsp" %>


        </div>
        <!-- Navbar & Hero End -->


        <div style="padding-top: 50px">
            <h1 style="margin-bottom: 10px; text-align: center;">Traveling from ${location}</h1>
            <hr style="margin-top: 10px; margin-bottom: 10px"/>
            <div style="display: flex; padding-left: 80px">
                <div style="width: 20%; margin-right: 10px; background-color: #f7f7f7; padding: 15px">
                    <div style="margin-bottom: 20px">
                        <div style="margin-bottom: 10px; font-weight: bold; font-size: larger; color: #0a0061">Start Location</div>
                        <select class="form-select">
                            <option>Choose your destination</option>
                            <c:forEach items="${Menuloc}" var="m">
                                <option ${m.name.equals(location)?'selected':''}>
                                    ${m.name}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div  style="margin-bottom: 20px">
                        <div style="margin-bottom: 10px; font-weight: bold; font-size: larger; color: #0a0061">End Location</div>
                        <select class="form-select">
                            <option>Choose your destination</option>
                            <c:forEach items="${Menuloc}" var="m">
                                <option>
                                    ${m.name}
                                </option>
                            </c:forEach></select>
                    </div>
                    <div style="margin-bottom: 20px">
                        <div style="margin-bottom: 10px; font-weight: bold; font-size: larger; color: #0a0061">Start Date</div>
                        <input class="form-control" type="date" value="${date}"/>
                    </div>
                </div>
                <div style="width: 80%">
                    <div style="margin-bottom: 10px">We found ${Tours.size()} tour(s) for you</div>
                    <div style="display: flex; flex-wrap: wrap">
                        <c:forEach items="${Tours}" var="t">
                            <div style="width: 30%; margin-right: 10px; margin-bottom: 40px; border: 1px solid; border-color: #c7c7c7">
                                <img src="images/${t.image}" style="width: 100%"/>
                                <div style="padding: 20px">
                                    <div>${date}</div>
                                    <h3>${t.location} - ${t.secondlocation}</h3>
                                    <h5>Tour ID: ${t.tourId}</h5>
                                    <h5>Start location: ${t.location}</h5>
                                    <h5 style="color:red">${t.price} $</h5>
                                    <div style="display: flex; justify-content: space-between">
                                        <a href="OrderDetailServlet?id=${t.tourId}" style="background: red; color: white; padding: 5px 30px 5px 30px; border-radius: 10%">Order</a>
                                        <a href="Detail?id=${t.tourId}" style="background: white; border: 1px solid; border-color: #b1a8ff; color: #b1a8ff; padding: 5px 30px 5px 30px; border-radius: 10%">Detail</a>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>


 




        <%@include file="/includes/footer.jsp"%>


        <!-- Back to Top -->
        <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>

        <!-- JavaScript Libraries -->
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="lib/wow/wow.min.js"></script>
        <script src="lib/easing/easing.min.js"></script>
        <script src="lib/waypoints/waypoints.min.js"></script>
        <script src="lib/owlcarousel/owl.carousel.min.js"></script>
        <script src="lib/tempusdominus/js/moment.min.js"></script>
        <script src="lib/tempusdominus/js/moment-timezone.min.js"></script>
        <script src="lib/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>

        <!-- Template Javascript -->
        <script src="js/main.js"></script>

    </body>

</html>
