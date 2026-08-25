<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Murach Survey — Thanks</title>
    <link rel="stylesheet" href="styles/main.css?v=3" type="text/css"/>
</head>

<body>
    <main class="survey-container">
        <div class="survey-card">

            <header class="hero">
                <img src="image/images.png" alt="Murach Logo" class="logo">
                <div class="hero-text">
                    <h1>Thanks for joining our email list</h1>
                    <p class="intro-text">Here is the information that you entered:</p>
                </div>
            </header>

            <div class="form-body">

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
                    <span>${empty dob ? '—' : dob}</span>
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

                <div class="submit-row">
                    <button type="button" class="submit-btn"
                            onclick="window.location.href='/'">
                        Return to Survey</button>
                </div>

            </div>
        </div>
    </main>
</body>
</html>