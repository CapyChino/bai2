<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>Murach's Java Servlets and JSP</title>
    <link rel="stylesheet" href="styles/main.css" type="text/css"/>
</head>

<body>
    <main class="survey-container">
        <h1>Thanks for joining our email list</h1>
        <p class="intro-text">Here is the information that you entered:</p>

        <div class="result-row">
            <label>First Name:</label>
            <span>${firstName}</span>
        </div>

        <div class="result-row">
            <label>Last Name:</label>
            <span>${lastName}</span>
        </div>

        <div class="result-row">
            <label>Email:</label>
            <span>${email}</span>
        </div>

        <div class="result-row">
            <label>Date of Birth:</label>
            <span>${dob}</span>
        </div>

        <div class="result-row">
            <label>Heard about us from:</label>
            <span>${hearAbout}</span>
        </div>

        <div class="result-row">
            <label>Contact me by:</label>
            <span>${contactBy}</span>
        </div>

        <div class="result-row">
            <label>Announcements:</label>
            <span>
                <c:if test="${not empty announcements}">
                    <ul class="announcement-list">
                        <c:forEach var="a" items="${announcements}">
                            <li><c:out value="${a}"/></li>
                        </c:forEach>
                    </ul>
                </c:if>
            </span>
        </div>

        <br>
        <button type="button" class="submit-btn"
                onclick="window.location.href='/';">
            Return to Survey</button>
    </main>
</body>
</html>