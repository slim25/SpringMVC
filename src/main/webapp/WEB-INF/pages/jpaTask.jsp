<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Mentorship program | Spring MVC </title>

      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>

	  <link href="/resources/css/main.css" rel="stylesheet">
	  <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
	  <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" />
  </head>

  <body >
<div class="container">
    <div class="row">
        <form action="getMenteesWithoutMentorsInLocationJPA" method="POST">
            <table>
                <tr>
                    <td colspan="2">City : <input type="text" name="city" /></td>
                    <td colspan="2"><input type="submit" value="getMenteesWithoutMentorsInLocation" /></td>
                </tr>
            </table>
        </form>
        <form action="getMenteesWithMentorshipDurationDESCOrderedJPA" method="POST">
            <table>
                <tr>
                    <td colspan="2">page index : <input type="text" name="pageIndex" /></td>
                    <td colspan="2">; number of records : <input type="text" name="noOfRecords" /></td>
                    <td colspan="2"><input type="submit" value="getMenteesWithMentorshipDurationDESCOrdered" /></td>
                </tr>
            </table>
        </form>
         <form action="getCitiesStatisticJPA" method="POST">
            <table>
                <tr>
                    <td colspan="2"><input type="submit" value="getCitiesStatistic" /></td>
                </tr>
            </table>
        </form>
         <form action="getStatisticOfSuccessCompletionsJPA" method="POST">
            <table>
                <tr>
                    <td colspan="2"><input type="submit" value="getStatisticOfSuccessCompletions" /></td>
                </tr>
            </table>
        </form>
        <form action="getMentorsWhoMentorsMoreThanTwoMenteeJPA" method="POST">
            <table>
                <tr>
                    <td colspan="2"><input type="submit" value="getMentorsWhoMentorsMoreThanTwoMentee" /></td>
                </tr>
            </table>
        </form>
    </div>

    </div>



  </body>
</html>
