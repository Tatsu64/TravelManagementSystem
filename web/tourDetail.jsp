<%-- 
    Document   : tourDetail
    Created on : 16 thg 2, 2024, 23:51:01
    Author     : TATSU
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <%@include file="includes/header.jsp" %>
        <script src="https://kit.fontawesome.com/5a5f994da7.js" crossorigin="anonymous"></script>
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

        <!-- About Start -->
        <div class="container-xxl py-5">
            <div class="container">
                <div style="display: flex; align-items: center; padding-top: 50px; font-size: 200%; font-weight: bold">
                    <div style="width: 50%">
                        <div>${tour.tourName} - ${tour.startLocation}</div>
                    </div>
                    <div style="width: 50%; display: flex; align-items: center">
                        <div style="width: 50%;font-size: 70%; text-align: right; padding-right: 10px; color: red">$ ${tour.tourPrice}/guest</div>
                        <div style="width: 50%;font-size: 50%">
                            <a href="OrderDetailServlet?id=${tour.tourId}" style="display: inline-block; text-align: center; border:solid 1px; padding: 10px; margin-bottom: 5px; width: 300px; background-color: red; color:white; border-radius: 3%"><i class="fa-solid fa-cart-shopping"></i> Book Now</a>
                            
                        </div>
                    </div>
                </div>

                <img alt="tour image" src="images/${tour.imageUrl}" style="width: 100%; margin-top: 50px;"/>
            </div>
            <div style="display: flex; margin-top: 40px;">
                <div style="border: 1px solid; border-color: #e0e0de; padding: 20px; width: 40%; margin-right: 40px">
                    <h4><span style="font-weight: 100">Start date:</span> ${tour.startDate}</h4>
                    <h4><span style="font-weight: 100">Day(s):</span> ${days}</h4>
                    <h4><span style="font-weight: 100">Start location:</span> ${tour.startLocation}</h4>
                    <h4><span style="font-weight: 100">Capacity:</span> ${tour.maxCapacity}</h4>
                    <h4><span style="font-weight: 100">Current:</span> ${tour.current}</h4>
                </div>
                <div style="width: 60%; display: flex; justify-content: space-around; flex-wrap: wrap">
                    <div>
                        <i class="fa-regular fa-flag"></i>
                        <h5>Time</h5>
                        <div>${days} day(s)</div>
                    </div>
                    <div><i class="fa-solid fa-car"></i>
                        <h5>Transportation</h5>
                        <div><c:forEach items="${transports}" var="t">
                                ${t.transportationName}, 
                            </c:forEach></div>
                    </div>
                    <div><i class="fa-solid fa-hotel"></i>
                        <h5>Hotel</h5>
                        <div><c:forEach items="${hotels}" var="h">
                                ${h.hotelName},
                            </c:forEach></div>
                    </div>
                    <div><i class="fa-solid fa-utensils"></i>
                        <h5>Restaurant</h5>
                        <div><c:forEach items="${restaurants}" var="r">
                                ${r.restaurantName},
                            </c:forEach></div>
                    </div>

                    <hr style="width: 100%; margin-top: 30px" />
                </div>
            </div>

            <div style="display: flex; margin-top: 40px">
                <div  style="border: 1px solid; border-color: #e0e0de; width:60%; padding: 20px;">
                    <h3>tour guide information</h3>
                    <div style="padding: 10px; color: #d1d1d1; border: 1px solid;">
                        <div>Company tour guide</div>
                        <h3>${tour.employee.fullName}</h3>
                        <div>${tour.employee.email}</div>
                    </div>
                </div>
                <div style="width: 40%;margin-left: 40px">
                    <h5 style="text-align: center; ">Schedule</h5>
                    <c:forEach items="${activities}" var="a">
                        <div style="display: flex; align-items: center">
                            <div>Day</div>
                            <h5 style="padding-left: 7px; padding-right: 7px; background-color: #cf2604; color: white; margin-right: 10px; margin-left: 5px; border: 1px solid; border-radius: 100%"> ${a.dayNumber}</h5>
                            <div>
                                <div>${a.startTime.toString()} - ${a.endTime.toString()}</div>
                                <h6>${a.activityName} - ${a.location}</h6>
                                <div>${a.description}</div>
                                <br>
                                <br>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>

            <!-- About End -->



            <!-- Process Start -->
            <div class="container-xxl py-5">
                <div class="container">
                    <div class="text-center pb-4 wow fadeInUp" data-wow-delay="0.1s">
                        <h6 class="section-title bg-white text-center text-primary px-3">Process</h6>
                        <h1 class="mb-5">3 Easy Steps</h1>
                    </div>
                    <div class="row gy-5 gx-4 justify-content-center">
                        <div class="col-lg-4 col-sm-6 text-center pt-4 wow fadeInUp" data-wow-delay="0.1s">
                            <div class="position-relative border border-primary pt-5 pb-4 px-4">
                                <div class="d-inline-flex align-items-center justify-content-center bg-primary rounded-circle position-absolute top-0 start-50 translate-middle shadow" style="width: 100px; height: 100px;">
                                    <i class="fa fa-globe fa-3x text-white"></i>
                                </div>
                                <h5 class="mt-4">Choose A Destination</h5>
                                <hr class="w-25 mx-auto bg-primary mb-1">
                                <hr class="w-50 mx-auto bg-primary mt-0">
                                <p class="mb-0">Tempor erat elitr rebum clita dolor diam ipsum sit diam amet diam eos erat ipsum et lorem et sit sed stet lorem sit</p>
                            </div>
                        </div>
                        <div class="col-lg-4 col-sm-6 text-center pt-4 wow fadeInUp" data-wow-delay="0.3s">
                            <div class="position-relative border border-primary pt-5 pb-4 px-4">
                                <div class="d-inline-flex align-items-center justify-content-center bg-primary rounded-circle position-absolute top-0 start-50 translate-middle shadow" style="width: 100px; height: 100px;">
                                    <i class="fa fa-dollar-sign fa-3x text-white"></i>
                                </div>
                                <h5 class="mt-4">Pay Online</h5>
                                <hr class="w-25 mx-auto bg-primary mb-1">
                                <hr class="w-50 mx-auto bg-primary mt-0">
                                <p class="mb-0">Tempor erat elitr rebum clita dolor diam ipsum sit diam amet diam eos erat ipsum et lorem et sit sed stet lorem sit</p>
                            </div>
                        </div>
                        <div class="col-lg-4 col-sm-6 text-center pt-4 wow fadeInUp" data-wow-delay="0.5s">
                            <div class="position-relative border border-primary pt-5 pb-4 px-4">
                                <div class="d-inline-flex align-items-center justify-content-center bg-primary rounded-circle position-absolute top-0 start-50 translate-middle shadow" style="width: 100px; height: 100px;">
                                    <i class="fa fa-plane fa-3x text-white"></i>
                                </div>
                                <h5 class="mt-4">Fly Today</h5>
                                <hr class="w-25 mx-auto bg-primary mb-1">
                                <hr class="w-50 mx-auto bg-primary mt-0">
                                <p class="mb-0">Tempor erat elitr rebum clita dolor diam ipsum sit diam amet diam eos erat ipsum et lorem et sit sed stet lorem sit</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Process Start -->

            <a href="Home" class="btn btn-primary">Back to Homepage</a>
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
