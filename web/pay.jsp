<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link href="css/newcss.css" rel="stylesheet">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Pay</title>
    <%@include file="includes/header.jsp" %>
</head>
<body>
    <br>
    <br>
    <br>
    <div class="container">
        <div class="card w-50 mx-auto my-5">
            <div class="card-header text-center">Credit card</div>
            <div class="card-body">
                <div class="payment-section">
                    <form action="StripeServlet" method="POST" id="payment-form">
                        <input type="hidden" id="name" name="name" value="${name}" />
                        <input type="hidden" id="email" name="email" value="${email}" />
                        <input type="hidden" id="totalPrice" name="totalPrice" value="${totalPrice}" />
                        <div class="form-group">
                            <label for="card-number">Card number</label>
                            <i class="fas fa-credit-card"></i>
                            <input type="text" class="form-control" name="card_number" id="card-number" placeholder="Enter your card number" required pattern="[0-9]{16}" title="Card number must be 16 digits">
                        </div>

                        <div class="form-group">
                            <label for="expiry-date">Expiry date</label>
                            <i class="fas fa-calendar-alt"></i>
                            <input type="text" class="form-control" name="expiry_date" id="expiry-date" placeholder="MM/YY" required pattern="(0[1-9]|1[0-2])\/(2[3-9])|(2[3-9]\/[0-9][0-9])" title="Expiry date must be in MM/YY format, with YY not below 23, and MM between 01 and 12">
                        </div>

                        <div class="form-group">
                            <label for="cvv">CVV</label>
                            <i class="fas fa-lock"></i>
                            <input type="text" class="form-control" name="cvv" id="cvv" placeholder="Enter your CVV" required pattern="[0-9]{3}" title="CVV must be 3 digits">
                        </div>
                        <br>
                        <div style="text-align: center;">
                            <button type="submit" class="btn btn-dark">Pay now</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <!-- Success Modal -->
    <div class="modal fade" id="successModal" tabindex="-1" role="dialog" aria-labelledby="successModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="successModalLabel">Payment Successful</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    Your payment has been successfully processed.
                    You can now leaving a review in the tour detail page.
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>

    <%@include file="includes/footer.jsp" %>

    <!-- jQuery and Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <script>
        $(document).ready(function() {
            $('#payment-form').submit(function(event) {
                event.preventDefault();
                $('#successModal').modal('show');
                setTimeout(function() {
                    document.getElementById("payment-form").submit();
                }, 2000); 
            });
        });
    </script>
</body>
</html>
