<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SignUp</title>
    <style>
        /* Universal background image and body styling */
        body {
            margin: 0;
            font-family: 'Arial', sans-serif;
            background: url('https://asset.gecdesigns.com/img/wallpapers/beautiful-fantasy-lush-green-forest-with-river-and-mountain-ai-generated-wallpaper-sr03072417-cover.webp') no-repeat center center fixed;
            background-size: cover;
            color: #fff;
            height: 100vh; /* Full screen height */
            display: flex;
            justify-content: center; /* Horizontally center the form */
            align-items: center; /* Vertically center the form */
            overflow: hidden; /* Prevent scrolling */
        }

       h2 {
		    color: #ffeb3b;
		    text-shadow: 2px 2px 4px #000;
		    margin: 0 auto 15px; /* Center the title and add spacing below */
		    font-size: 1.8rem;
		    text-align: center; /* Ensure text is horizontally aligned */
		    display: block; /* Make it a block-level element to center */
}

        form {
            background: rgba(0, 0, 0, 0.7); /* Semi-transparent background */
            padding: 30px;
            border-radius: 10px;
            width: 400px; /* Compressed width */
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.5); /* Soft shadow */
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
		    font-size: 0.9rem; /* Slightly smaller font size */
		    padding: 8px 20px; /* Reduce padding */
		    border: none;
		    border-radius: 5px;
		    background: #2196F3; /* Light green color */
		    color: #fff;
		    cursor: pointer;
		    width: 50%; /* Make button width smaller */
		    margin: 10px auto 0; /* Add spacing at the top and center horizontally */
		    display: block; /* Center-align the button */
		    box-shadow: 3px 3px 10px rgba(0, 0, 0, 0.5);
		}
		
		button:hover {
		    background: #45a049; /* Slightly darker green on hover */
}


        textarea {
            resize: none; /* Prevent resizing */
            height: 60px; /* Reduced height for address */
        }
    </style>
</head>
<body>
    <form action="Register" method="post">
        <h2>SignUp</h2>

        <!-- Name -->
        <label for="name">Name:</label>
        <input type="text" name="name" maxlength="45" required>

        <!-- Mobile Number -->
        <label for="mobileNumber">Mobile Number:</label>
        <input type="text" name="mobileNumber" maxlength="20" required>

        <!-- Address -->
        <label for="address">Address:</label>
        <textarea id="address" name="address" maxlength="255" required></textarea>

        <!-- Date of Registration -->
        <label for="dateOfRegisteration">Date of Registration:</label>
        <input type="date" name="dateOfRegisteration" required>
        
          <!-- User Name -->
        <label for="userName">User Name:</label>
        <input type="text" name="userName" maxlength="255" required>

        <!-- Password -->
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" maxlength="128" required>

      

        <!-- Submit Button -->
        <button type="submit">Register</button>
    </form>
</body>
</html>
