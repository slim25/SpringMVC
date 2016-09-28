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
        <a href="searchPage">Search User</a>
    </div>
    <div class="row">
        <div class="col-md-6">

                <c:if test="${user != null}">
                    <h1>Edit User</h1>
                    <form action="editUser" method="POST">
                    <input type="hidden" name="id" value="${user.id}"/>
                    <table border="0">
                            <tr>
                				<td><b>Name</b></td>
                				<td><input type="text" name="name" value="${user.name}"/></td>
                			</tr>

                			<tr>
                                <td><b>Last Name</b></td>
                                <td><input type="text" name="lastName" value="${user.lastName}"/></td>
                            </tr>

                			<tr>
                				<td><b>Email</b></td>
                				<td><input type="text" name="email" value="${user.email}"/></td>
                			</tr>
                			<tr>
                				<td><b>Birthday</b></td>
                				<td><input type="date" name="birthday" value="${user.birthday}"/></td>
                			</tr>

                			<tr>
                				<td><b>Primary Skill</b></td>
                				<td><select name="primarySkill">
                                        <option value="Java">Java</option>
                                        <option value="JavaScript">JavaScript</option>
                                        <option value="Net">Net</option>
                                        <option value="Pyton">Pyton</option>
                                </select></td>
                			</tr>

                            <tr>
                                <td>Mentorship Program</td>
                                <td><select name="mentorshipProgram">
                                     <c:forEach items="${mentorshipPrograms}" var="mentorshipProgram">
                                        <option value="${mentorshipProgram.id}">${mentorshipProgram.name}</option>
                                    </c:forEach>
                                </select></td>
                            </tr>

                			<tr>
                				<td>Level</td>
                				<td><select name="level">
                						<option value="L1">L1</option>
                						<option value="L2">L2</option>
                						<option value="L3">L3</option>
                						<option value="L4">L4</option>
                						<option value="L5">L5</option>
                				</select></td>
                			</tr>

                			<tr>
                				<td colspan="2"><input type="submit" value="Edit User" /></td>
                			</tr>
                		</table>

                	</form>
                </c:if>
        </div>



        <div class="col-md-6">
             <c:if test="${mentorshipProgram != null}">
                                <h1>Edit Mentorship Program</h1>
                                <form action="editMentorshipProgram" method="POST">
                                <input type="hidden" name="id" value="${mentorshipProgram.id}"/>
                                <table border="0">
                                        <tr>
                            				<td><b>Name</b></td>
                            				<td><input type="text" name="name" value="${mentorshipProgram.name}"/></td>
                            			</tr>
                                        <input style="display:none;" type="hidden" name="dateCreated" value="${mentorshipProgram.dateCreated}"/>
                                        <input style="display:none;" type="hidden" name="createdByUser" value="${mentorshipProgram.createdByUser}"/>
                                        <input  style="display:none;" type="hidden" name="dateLastModified" value="${mentorshipProgram.dateLastModified}"/>
                                        <input type="hidden" name="lastModifiedByUser" value="${mentorshipProgram.lastModifiedByUser}"/>

                            			<tr>
                            				<td colspan="2"><input type="submit" value="Edit Mentorship Program" /></td>
                            			</tr>
                            		</table>

                            	</form>
                            </c:if>
        </div>
         </div>
    </div>



  </body>
</html>
