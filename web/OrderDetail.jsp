<%-- 
    Document   : OrderDetail
    Created on : Mar 4, 2024, 8:22:16 PM
    Author     : toden
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <%@include file="includes/header.jsp" %>
        <style>
            .numeric-updown {
                display: inline-block;
                position: relative;
                width: 100px;
            }
            .numeric-updown input {
                width: 100%;
                text-align: center;
                padding: 5px;
                box-sizing: border-box;
            }
            .numeric-updown button {
                position: absolute;
                top: 0;
                width: 30px;
                height: 50%;
                border: none;
                cursor: pointer;
                outline: none;
            }
            .numeric-updown button:first-child {
                left: 0;
            }
            .numeric-updown button:last-child {
                right: 0;
            }
        </style>
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

        <form action="BookingServlet" method="get">
        <div style="padding: 100px 50px 50px 50px;">
            <h1 style="margin-bottom: 40px">Trip Overall</h1>
            <div style="display: flex">
                <div style="width: 70%;margin-right: 20px">
                    <h4>Contact information</h4>
                    <div style="padding: 20px; background-color: #ededed; display: flex; flex-wrap: wrap">
                        <input type="hidden" id="userId" name="userId" value="${auth.userId}">
                        <div style="width: 50%; padding: 20px">
                            <label>Full Name</label><span style="color: red">*</span>
                            <input name="name" value="${auth.name}" class="form-control"/>
                        </div>
                        <div style="width: 50%; padding: 20px">
                            <label>Email</label><span style="color: red">*</span>
                            <input name="email" value="${auth.email}" class="form-control"/>
                        </div>
                        <div style="width: 50%; padding: 20px">
                            <label>Phone number</label><span style="color: red">*</span>
                            <input value="${auth.phone}" class="form-control"/>
                        </div>
                        <div style="width: 50%; padding: 20px">
                            <label>Address</label>
                            <input value="${auth.address}" class="form-control"/>
                        </div>

                    </div>
                    <div style="margin-top: 20px; display: flex">
                        <h3>Guest(s)</h3>
                        <div class="numeric-updown">
                            <button onclick="decrementValue()">-</button>
                            <input disabled="true" type="number" id="numeric-input" name="people" value="1" min="1">
                            <button onclick="incrementValue()">+</button>
                        </div>
                    </div>
                </div>

                <div style="width: 30%; padding: 20px; border: 1px solid">
                    <h3>Trip summary</h3>
                    <input type="hidden" id="id" name="id" value="${id}">
                    <div style="display: flex; align-items: center">
                        <div style="width: 30%">
                            <img style="width: 100%" src="images/${Tour.image}"/>
                        </div>
                        <div style="width:70%; padding-left: 10px">
                            <h4>${Tour.location} - ${Tour.secondlocation}</h4>
                        </div>
                    </div>

                    <div style="margin-top: 20px; padding: 10px">
                        <h5>Trip start</h5>
                        <div style="padding-left: 20px">${Tour.dateStart}</div>
                        <h5>Trip end</h5>
                        <div style="padding-left: 20px">${Tour.dateEnd}</div>
                        <h5>Current</h5>
                        <div style="padding-left: 20px">${Tour.current}</div>
                    </div>

                    <div style="display: flex; justify-content: space-between">
                        <h5>Per Guest: </h5>
                        <h5>${Tour.price} $</h5>
                    </div>
                    
                     <div style="display: flex; justify-content: space-between">
                        <div>Guest: </div>
                        <div><span id='nog'>1</span> X <span>${Tour.price}</span> $</div>
                    </div>
                    
                    <div style="display: flex; justify-content: space-between">
                        <h4>Bill: </h4>
                        <h4 id="price" name="price" >${Tour.price} $</h4>
                    </div>
                    
                    <button type="submit" style="width: 100%; padding: 15px; color: white; background-color: #5fc400; border: 0px solid; font-weight: bold">Payment</button>
                </div>
            </div>
                    <button type="button" onclick="history.back();" class="btn btn-secondary">Back</button>
        </div>
               </form>     



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

        <script>
                                function incrementValue() {
                                    var value = parseInt(document.getElementById('numeric-input').value, 10);
                                    value = isNaN(value) ? 0 : value;
                                    value++;
                                    document.getElementById('numeric-input').value = value;
                                    updateNOG();
                                }

                                function decrementValue() {
                                    var value = parseInt(document.getElementById('numeric-input').value, 10);
                                    value = isNaN(value) ? 0 : value;
                                    if (value > 1) {
                                        value--;
                                        document.getElementById('numeric-input').value = value;
                                        updateNOG();
                                    }
                                }
                                
                                function updateNOG(){
                                    var value = document.getElementById("numeric-input").value
                                    document.getElementById("nog").innerHTML = value
                                    var newprice = value * ${price}
                                    document.getElementById("price").innerHTML = newprice+" $"
                                }
        </script>

    </body>

</html>

