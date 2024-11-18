<%@ page import="java.util.ArrayList, java.util.List, java.util.Map, java.sql.*, java.io.InputStream" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Portfolio</title>
    <style>
        /* Body styling */
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(to bottom, #fefbd8, #f4e8c1);
            color: #333;
            margin: 0;
            padding: 0;
        }

        /* Header styling */
        h2 {
            text-align: center;
            color: #2a6f2e;
            margin-top: 20px;
            font-size: 2rem;
            text-shadow: 2px 2px 5px rgba(0, 0, 0, 0.2);
        }

        /* Table styling */
        table {
            width: 90%;
            margin: 20px auto;
            border-collapse: collapse;
            background-color: #fff;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
        }

        th, td {
            padding: 10px;
            text-align: center;
            border: 1px solid #ddd;
        }

        th {
            background-color: #2a6f2e;
            color: #fff;
            text-transform: uppercase;
            letter-spacing: 1px;
        }

        td img {
            border: 2px solid #ddd;
            border-radius: 10px;
            width: 120px;
            height: 100px;
            object-fit: cover;
        }

        /* Alternating row colors */
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        /* Welcome message styling */
        p {
            text-align: center;
            font-size: 1.2rem;
            color: #4b7b4b;
        }

        /* Footer message */
        footer {
            text-align: center;
            margin: 20px 0;
            color: #666;
            font-size: 0.9rem;
        }
    </style>
</head>
<body>
    <h2>Leopard Sighting Portfolio</h2>
    <%
        String username = request.getParameter("username");
        if (username == null) {
            out.println("<p>Error: No username provided.</p>");
        } else {
            out.println("<p>Welcome, " + username + "!</p>");

            // Connect to the database and fetch portfolio data
            String url = "jdbc:mysql://localhost:3306/leopard";
            String dbUser = "root";
            String dbPassword = "negs27@sql";
            List<Map<String, Object>> portfolioData = new ArrayList<>();

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
                String query = "SELECT latitude, longitude, date, timestamp, image, accuracy FROM collection WHERE username = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, username);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    Map<String, Object> data = new java.util.HashMap<>();
                    data.put("latitude", rs.getDouble("latitude"));
                    data.put("longitude", rs.getDouble("longitude"));
                    data.put("date", rs.getString("date"));

                    // Extract the time portion from the timestamp
                    java.sql.Timestamp timestamp = rs.getTimestamp("timestamp");
                    if (timestamp != null) {
                        String time = timestamp.toString().substring(11); // HH:mm:ss.SSS
                        data.put("timestamp", time);
                    } else {
                        data.put("timestamp", "N/A");
                    }

                    // Fetch the image as a binary stream
                    InputStream imageStream = rs.getBinaryStream("image");
                    byte[] imageBytes = null;

                    if (imageStream != null) {
                        imageBytes = imageStream.readAllBytes();
                    }

                    data.put("image", imageBytes);
                    data.put("accuracy", rs.getInt("accuracy"));
                    portfolioData.add(data);
                }
                rs.close();
                stmt.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
                out.println("<p>Error retrieving data.</p>");
            }

            // Display data in a table
            if (portfolioData.isEmpty()) {
                out.println("<p>No portfolio data found for user: " + username + "</p>");
            } else {
    %>
                <table>
                    <tr>
                        <th>Longitude</th>
                        <th>Latitude</th>
                        <th>Timestamp</th>
                        <th>Date</th>
                        <th>Image</th>
                        <th>Accuracy</th>
                    </tr>
                    <%
                        for (Map<String, Object> data : portfolioData) {
                            byte[] imageBytes = (byte[]) data.get("image");
                            String imageUrl = (imageBytes != null && imageBytes.length > 0)
                                ? "data:image/jpeg;base64," + java.util.Base64.getEncoder().encodeToString(imageBytes)
                                : null;
                    %>
                    <tr>
                        <td><%= data.get("longitude") %></td>
                        <td><%= data.get("latitude") %></td>
                        <td><%= data.get("timestamp") %></td>
                        <td><%= data.get("date") %></td>
                        <td>
                            <% if (imageUrl != null) { %>
                                <img src="<%= imageUrl %>" alt="Leopard Image">
                            <% } else { %>
                                Image not available
                            <% } %>
                        </td>
                        <td><%= data.get("accuracy") %></td>
                    </tr>
                    <%
                        }
                    %>
                </table>
    <%
            }
        }
    %>
    <footer>
        <p>Tracking Leopard Sightings for a Safer Environment</p>
    </footer>
</body>
</html>
