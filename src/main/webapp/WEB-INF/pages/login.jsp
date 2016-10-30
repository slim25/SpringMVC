<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
           xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Mentorship program | Spring MVC | LogIN page</title>

      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>

	  <link href="/resources/css/main.css" rel="stylesheet">
	  <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
	  <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" />
  </head>

  <body >
      <div th:if="${param.error}">
          Invalid username and password.
      </div>
      <div th:if="${param.logout}">
          You have been logged out.
      </div>
      <form th:action="/loginAction"method="post">
          <div><label> User Name : <input type="text" name="username"/> </label></div>
          <div><label> Password: <input type="password" name="password"/> </label></div>
          <div><input type="submit" value="Sign In"/></div>
      </form>

  </body>
</html>
