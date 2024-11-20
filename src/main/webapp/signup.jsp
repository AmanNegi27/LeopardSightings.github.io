<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SignUp</title>
    <style>
        
        body {
            margin: 0;
            font-family: 'Arial', sans-serif;
            background: url('https://asset.gecdesigns.com/img/wallpapers/beautiful-fantasy-lush-green-forest-with-river-and-mountain-ai-generated-wallpaper-sr03072417-cover.webp') no-repeat center center fixed;
            background-size: cover;
            color: #fff;
            height: 100vh;
            display: flex;
            justify-content: center; 
            align-items: center;
            overflow: hidden;
        }

       h2 {
		    color: #ffeb3b;
		    text-shadow: 2px 2px 4px #000;
		    margin: 0 auto 15px; 
		    font-size: 1.8rem;
		    text-align: center; 
		    display: block; 
}

        form {
            background: rgba(0, 0, 0, 0.7);
            padding: 30px;
            border-radius: 10px;
            width: 400px; 
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.5); 
        }

        label {
            font-size: 1rem;
            margin: 10px 0 5px;
            display: block;
            text-align: left;
        }

        input[type="text"], input[type="password"], input[type="date"], textarea {
            width: 100%;
            padding: 8px;
            margin-bottom: 15px;
            font-size: 0.9rem;
            border-radius: 5px;
            border: 1px solid #fff;
            background-color: #333;
            color: #fff;
        }

       button {
		    font-size: 0.9rem; 
		    padding: 8px 20px; 
		    border: none;
		    border-radius: 5px;
		    background: #2196F3; 
		    color: #fff;
		    cursor: pointer;
		    width: 50%; 
		    margin: 10px auto 0;
		    display: block;
		    box-shadow: 3px 3px 10px rgba(0, 0, 0, 0.5);
		}
		
		button:hover {
		    background: #45a049;
}


        textarea {
            resize: none; 
            height: 60px; 
        }
    </style>
</head>
<body>
    <form action="Register" method="post">
        <h2>SignUp</h2>

       
        <label for="name">Name:</label>
        <input type="text" name="name" maxlength="45" required>

       
        <label for="mobileNumber">Mobile Number:</label>
        <input type="text" name="mobileNumber" maxlength="20" required>

        
        <label for="address">Address:</label>
        <textarea id="address" name="address" maxlength="255" required></textarea>

       
        <label for="dateOfRegisteration">Date of Registration:</label>
        <input type="date" name="dateOfRegisteration" required>
        
          
        <label for="userName">User Name:</label>
        <input type="text" name="userName" maxlength="255" required>

       
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" maxlength="128" required>

      

        
        <button type="submit">Register</button>
    </form>
</body>
</html>
