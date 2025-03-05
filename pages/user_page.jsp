<!DOCTYPE html>
<html>
<head>
    <title>User Page</title>
</head>
<body>
    <h2>Welcome, ${user.username}!</h2>
    <p>${message}</p>
    <form action="logout" method="post">
        <input type="submit" value="Logout">
    </form>
</body>
</html>