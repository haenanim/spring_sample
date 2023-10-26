<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>hello</title>
</head>
<body>
<h1>hello</h1>
<hr>
현재 날짜와 시간은 <%= java.time.LocalDateTime.now() %> 입니다.
<br>
메시지 : ${msg}
</body>
</html>