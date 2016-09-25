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
        <form action="searchPage" method="POST">
            <tr>
                <td colspan="2"><input type="text"  name="criteria" /></td>
                <td colspan="2"><input type="submit" value="Search" /></td>
            </tr>
            </table>
        </form>
    </div>
    <div class="row">
        <div class="col-md-6">
                <h1>Users</h1>
            	<table border="0">
            	    <c:forEach items="${matchedUsers}" var="user">

            	    <div>
            			<div>
            				<p>Name: ${user.name}</p>
            			</div>
            			<div>
                            <p>Last Name: ${user.lastName}</p>
                        </div>
            			<div>
            				<p>Email:${user.email}</p>
            			</div>
            			<c:if test="${user.birthday != null}">
            				<div>
            					<p>
            						Bitrhday:
            						<fmt:formatDate pattern="yyyy-MM-dd" value="${user.birthday}" />
            					</p>
            				</div>
            			</c:if>
            			<c:if test="${user.mentorshipProgram.name != null}">
                            <div>
                                <p>Mentorship Program Name :${user.mentorshipProgram.name}</p>
                            </div>
                        </c:if>

            			<c:if test="${user.level != null}">
            				<div>
            					<p>Level :${user.level}</p>
            				</div>
            			</c:if>
            			<c:if test="${user.primarySkill != null}">
            				<div>
            					<p>Primary Skill:${user.primarySkill}</p>
            				</div>
            			</c:if>
            		</div>
            		<form action="deleteUser" method="POST">
            			<input type="hidden" value="${user.id}" name="id" />
            			<tr>
            				<td colspan="2"><input type="submit" value="Delete" /></td>
            			</tr>
            			</table>
            		</form>
            		<form action="editPage" method="POST">
            			<input type="hidden" value="${user.id}" name="id" />
            			<tr>
            				<td colspan="2"><input type="submit" value="Edit" /></td>
            			</tr>
            			</table>
            		</form>
            		<hr>
            	    </c:forEach>
            	</table>

            	<hr>


        </div>

<hr>

        <div class="col-md-6">

  <h1>Mentorship Programm</h1>

        <table border="0">
                            	    <c:forEach items="${matchedMentorshipProgramms}" var="mentorshipProgram">

                                        <div>
                                            <div>
                                                <p>Name: ${mentorshipProgram.name}</p>
                                            </div>
                                             <c:forEach items="${mentorshipProgram.participants}" var="participant">
                                                <div>
                                                    <p>Name: ${participant.name}</p>
                                                </div>
                                                <div>
                                                    <p>Last Name: ${participant.lastName}</p>
                                                </div>
                                             </c:forEach>

                                        </div>
                                        <form action="deleteMentorshipProgram" method="POST">
                                            <input type="hidden" value="${mentorshipProgram.id}" name="id" />
                                            <tr>
                                                <td colspan="2"><input type="submit" value="Delete" /></td>
                                            </tr>
                                            </table>
                                        </form>
                                        <form action="editMentorshipPage" method="POST">
                                            <input type="hidden" value="${mentorshipProgram.id}" name="id" />
                                            <tr>
                                                <td colspan="2"><input type="submit" value="Edit" /></td>
                                            </tr>
                                            </table>
                                        </form>
                                        <hr>
                            	    </c:forEach>
                            	</table>
                <hr>
        </div>
         </div>
    </div>



  </body>
</html>
