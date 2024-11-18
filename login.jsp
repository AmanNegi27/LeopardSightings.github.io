<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <style>
        /* General Reset */
        body, h2, form, label, input, button, div {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: Arial, sans-serif;
            background-color: #f2efe5; /* Soft beige background for a nature vibe */
            color: #333;
            display: flex;
            align-items: center;
            justify-content: center;
            min-height: 100vh;
            background-image: url('https://img.freepik.com/free-photo/elusive-jaguar-stealthily-prowling-through-dense-underbrush-eyes-fixed-potential-prey_1268-34991.jpg?t=st=1731817251~exp=1731820851~hmac=31eb5fad90f6ff5d8e8fabd2af4b4b16f34de55317d324508d160f4c33b3ab85&w=1060'); /* Add a jungle-themed background */
            background-size: cover;
            background-position: center;
        }

        h2 {
            font-size: 2em;
            text-align: center;
            color: black; /* Rich brown for a nature vibe */
            text-shadow: 1px 1px 4px rgba(0, 0, 0, 0.3);
        }

        form {
            background: rgba(255, 255, 255, 0.5); /* Semi-transparent white */
            padding: 20px 30px;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            display: left;
            width: 100%;
        }

        label {
            display: block;
            font-size: 1em;
            margin-bottom: 8px;
            color: black;
            font-weight: bold;
        }

        input {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 1em;
        }

        input:focus {
            border-color: #6c873f; /* Forest green border on focus */
            outline: none;
            box-shadow: 0 0 6px rgba(108, 135, 63, 0.5);
        }

        button {
            background-color: #6c873f; /* Forest green */
            color: #fff;
            font-size: 1em;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            width: 100%;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #597230; /* Darker green on hover */
        }

        .error-message {
            background-color: #ffdddd;
            color: #a33b3b;
            padding: 10px;
            border: 1px solid #a33b3b;
            border-radius: 5px;
            margin-bottom: 15px;
            text-align: center;
        }

        /* Add some extra flair */
        form::before {
            content: '';
            display: block;
            width: 80px;
            height: 80px;
            background: url('https://png.pngtree.com/png-vector/20221013/ourmid/pngtree-leopard-face-color-vector-illustration-png-image_6277973.png') no-repeat center/cover;
            margin: 0 auto 20px auto;
            border-radius: 50%;
            border: 2px solid #6c873f;
        }
    </style>
</head>
<body>
    <form action="LoginServlet" method="post">
        <h2>Leopard Sighting Login</h2>
        
        <!-- Display error message if it exists -->
        <c:if test="${not empty errorMessage}">
            <div class="error-message">
                ${errorMessage}
            </div>
        </c:if>

        <label for="username">Username:</label>
        <input type="text" name="username" id="username" placeholder="Enter your username" required>

        <label for="password">Password:</label>
        <input type="password" name="password" id="password" placeholder="Enter your password" required>

        <button type="submit">Login</button>
    </form>
</body>
</html> 