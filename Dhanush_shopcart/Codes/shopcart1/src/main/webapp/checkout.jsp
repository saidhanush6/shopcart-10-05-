<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.models.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Checkout</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Josefin Sans">
   <style>
        body {
            font-family: "Josefin Sans", sans-serif;
           background-image:url("sc2.jpg");
background-repeat:no-repeat;
 background-position: center;
 background-size: cover;
 background-attachment: fixed;
            color: #fff;
        }

        .container {
            max-width: 800px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h2 {
            font-size: 24px;
            color: #333;
            margin-bottom: 20px;
        }

        .card {
            border: 1px solid #dee2e6; 
            border-radius: 8px;
            padding: 15px;
            margin-bottom: 15px;
            background-color: #fff;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .card-content {
            padding: 15px;
        }

        .card h3 {
            font-size: 18px;
            color: #333;
            margin-bottom: 10px;
            text-decoration: underline;
        }

        .card p {
            color: #666;
            margin-bottom: 5px;
        }

        .order-details {
            border: 2px solid #f8f9fa; 
            border-radius: 8px;
            padding: 15px;
            margin-top: 20px;
            background-color: #fff;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .order-details h2 {
            color: #333; 
            font-size: 24px;
            margin-bottom: 10px;
        }

        .order-details p {
            font-size: 16px;
            color: #333; 
            margin-bottom: 5px;
        }

        .back-to-cart {
            display: block;
            width: fit-content;
            margin: 20px auto;
            padding: 10px 20px;
            text-decoration: none;
            border-radius: 8px; 
            font-weight: bold; 
            border: none; 
            background-color: #17a2b8; 
            color: #fff;
            cursor: pointer;
            transition: background-color 0.25s;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Order Summary</h2>
        
        <div>
            <% 
            List<Product> cartItems = (List<Product>) request.getSession().getAttribute("cart2");
            double orderTotal = 0.0;
            int totalProductGST = 0;
            if (cartItems != null) {
                for (Product item : cartItems) {
                    orderTotal += item.getPrice();
                    int productGST = generateRandomGST();
                    totalProductGST += productGST;
            %>
                <div class="card">
                    <div class="card-content">
                        <h3><%= item.getProductName() %></h3>
                        <p>Product ID: <%= item.getProductId() %></p>
                        <p>Price: <%= item.getPrice() %></p>
                        <p>GST: <%= productGST %>%</p>
                    </div>
                </div>
            <%          
                }
            }
            %>
        </div>
        
        <div class="order-details">
            <h2>Order Details</h2>
            <p>Order ID: <span id="ordid"><%= generateOrderID() %></span></p>
            <p>Order Date: <%= getCurrentDate() %></p>
            <p>Total Product GST: <%= totalProductGST %>%</p>
            <p>Shipping GST: <%= generateRandomGST() %>%</p>
            <p>Order Total: <strong id="orto"><%= orderTotal + orderTotal * totalProductGST/100 + orderTotal * generateRandomGST()/100 %></strong></p>
        </div>
        
        <button id="payButton" class="back-to-cart">Payment</button>
        
        <%!
            String generateOrderID() {
                return "ORD" + (int)(Math.random() * 1000);
            }

            String getCurrentDate() {
                Date currentDate = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                return dateFormat.format(currentDate);
            }
            
            int generateRandomGST() {
                return (int) (Math.random() * (15 - 5 + 1)) + 5;
            }
        %>
    </div>
    
    <script src="https://checkout.razorpay.com/v1/checkout.js"></script>
    <script>
        document.addEventListener("DOMContentLoaded", function() {
            document.getElementById("payButton").addEventListener("click", function() {
                initiatePayment();
            });

            function initiatePayment() {
                var orderId = document.getElementById("ordid").innerText;
                var orderDate = getCurrentDate();
                var totalProductGST = <%= totalProductGST %>;
                var orderTotal = document.getElementById("orto").innerText;

                var razorpayOptions = {
                    key: 'rzp_test_x2opF2PLN1VQH0', // Replace with your Razorpay API key
                    amount: orderTotal*100, // Amount in paisa
                    currency: 'INR',
                    name: 'Burada Dhanush',
                    description: 'Payment for your product',
                    handler: function(response) {
                        alert('Payment successful! Payment ID: ' + response.razorpay_payment_id);
                    }
                };

                var rzp1 = new Razorpay(razorpayOptions);
                rzp1.open();
            }

            function getCurrentDate() {
                var currentDate = new Date();
                return currentDate.toISOString();
            }
        });
    </script>
</body>
</html>
