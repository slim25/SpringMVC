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
<div class="container" style="margin-left:2%,margin-right:2%">
    <!-- Search AREA -->
    <div class="row">
        <form action="searchPage" method="POST">
            <tr>
                <td colspan="2"><input type="text"  name="criteria" /></td>
                <td colspan="2"><input type="submit" value="Search" /></td>
            </tr>
            </table>
        </form>
    </div>

    <!-- JPA related -->

    <div class="row">
            <form action="JPATaskPage" method="POST">
                <tr>
                    <td colspan="2"><input type="submit" value="CheckJPATask" /></td>
                </tr>
                </table>
            </form>
        </div>

    <div class="row">

          <!-- User AREA -->
        <div class="col-md-3">
                <h1>Mentors</h1>
            	<table border="0">
            	    <c:forEach items="${mentors}" var="mentor">

            	    <div>
            			<div>
            				<p>Name: ${mentor.name}</p>
            			</div>
            			<div>
                            <p>Last Name: ${mentor.lastName}</p>
                        </div>
            			<div>
            				<p>Email:${mentor.email}</p>
            			</div>
            			<c:if test="${mentor.birthday != null}">
            				<div>
            					<p>
            						Bitrhday:
            						<fmt:formatDate pattern="yyyy-MM-dd" value="${mentor.birthday}" />
            					</p>
            				</div>
            			</c:if>
            			<c:if test="${mentor.mentorshipGroup.groupName != null}">
                            <div>
                                <p>Mentorship Group Name :${mentor.mentorshipGroup.groupName}</p>
                            </div>
                        </c:if>

            			<c:if test="${mentor.level != null}">
            				<div>
            					<p>Level :${mentor.level}</p>
            				</div>
            			</c:if>
            			<c:if test="${mentor.primarySkill != null}">
            				<div>
            					<p>Primary Skill:${mentor.primarySkill}</p>
            				</div>
            			</c:if>
            			<c:if test="${ not empty mentor.mentees }">
                             <div>Mentees name :
                                 <c:forEach items="${mentor.mentees}" var="mentee">
                                     <p> ${mentee.name},</p>
                                </c:forEach>
                            </div>
                        </c:if>
            		</div>
            		<form action="deleteUser" method="POST">
            			<input type="hidden" value="${mentor.id}" name="id" />
            			<tr>
            				<td colspan="2"><input type="submit" value="Delete" /></td>
            			</tr>
            			</table>
            		</form>
            		<form action="editPage" method="POST">
            			<input type="hidden" value="${mentor.id}" name="id" />
            			<tr>
            				<td colspan="2"><input type="submit" value="Edit" /></td>
            			</tr>
            			</table>
            		</form>
            		<hr>
            	    </c:forEach>

            	    <!-- Mentees -->

            	    <h1>Mentees</h1>
                    <table border="0">
                        <c:forEach items="${mentees}" var="mentee">

                        <div>
                            <div>
                                <p>Name: ${mentee.name}</p>
                            </div>
                            <div>
                                <p>Last Name: ${mentee.lastName}</p>
                            </div>
                            <div>
                                <p>Email:${mentee.email}</p>
                            </div>
                            <c:if test="${mentee.birthday != null}">
                                <div>
                                    <p>
                                        Bitrhday:
                                        <fmt:formatDate pattern="yyyy-MM-dd" value="${mentee.birthday}" />
                                    </p>
                                </div>
                            </c:if>
                            <c:if test="${mentee.mentorshipGroup.groupName != null}">
                                <div>
                                    <p>Mentorship Group Name :${mentee.mentorshipGroup.groupName}</p>
                                </div>
                            </c:if>

                            <c:if test="${mentee.level != null}">
                                <div>
                                    <p>Level :${mentee.level}</p>
                                </div>
                            </c:if>
                            <c:if test="${mentee.primarySkill != null}">
                                <div>
                                    <p>Primary Skill:${mentee.primarySkill}</p>
                                </div>
                            </c:if>
                            <c:if test="${not empty mentee.userMentor}">
                                 <div><p>Mentor name : ${mentee.userMentor.name}</p></div>
                            </c:if>
                        </div>
                        <form action="deleteUser" method="POST">
                            <input type="hidden" value="${mentee.id}" name="id" />
                            <tr>
                                <td colspan="2"><input type="submit" value="Delete" /></td>
                            </tr>
                            </table>
                        </form>
                        <form action="editPage" method="POST">
                            <input type="hidden" value="${mentee.id}" name="id" />
                            <tr>
                                <td colspan="2"><input type="submit" value="Edit" /></td>
                            </tr>
                            </table>
                        </form>
                        <hr>
                        </c:forEach>

            	</table>

            	<hr>
            	<form action="createUser" method="POST">
                		<table border="0">

                			<tr>
                				<td><b>Name</b></td>
                				<td><input type="text" name="name" /></td>

                			</tr>

                			<tr>
                                <td><b>Last Name</b></td>
                                <td><input type="text" name="lastName" /></td>

                            </tr>

                			<tr>
                				<td><b>Email</b></td>
                				<td><input type="text" name="email" /></td>

                			</tr>
                			<tr>
                				<td><b>Birthday</b></td>
                				<td><input type="date" name="birthday" /></td>

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
                                <td>Mentorship Group</td>
                                <td><select name="selectedMentorshipGroupId">
                                     <c:forEach items="${mentorshipGroups}" var="mentorshipGroup">
                                        <option value="${mentorshipGroup.id}">${mentorshipGroup.groupName}</option>
                                    </c:forEach>
                                </select></td>

                            </tr>
                            <tr>
                                <td>Choose mentor</td>

                                    <c:forEach items="${mentors}" var="mentor">
                                        <td colspan="2"><input type="checkbox" name="mentorId" value="${mentor.id}" >${mentor.name}<br></td>
                                    </c:forEach>

                            </tr>

                            <tr>
                                <td>Choose mentee</td>
                                <td>
                                     <c:forEach items="${mentees}" var="mentee">
                                         <tr>
                                            <td colspan="2"><input type="checkbox" name="menteeId" value="${mentee.id}" >${mentee.name}<br></td>
                                        </tr>
                                    </c:forEach>
                                </td>

                            </tr>

                			<tr>
                				<td>Level</td>
                				<td><select name="level">
                						<option value="L1">L1</option>
                						<option value="L2">L2</option>
                						<option value="L3">L3</option>
                						<option value="L4">L4</option>
                				</select></td>

                			</tr>
                            <tr>
                                 <td colspan="2"><input type="checkbox" name="isMentor" value="true" >User is mentor<br></td>

                            </tr>
                			<tr>
                				<td colspan="2"><input type="submit" value="Create User" /></td>
                			</tr>
                		</table>
                	</form>

        </div>

        <!-- Mentorship Group AREA -->


        <div class="col-md-3">
                <h1>Mentorship Groups</h1>
                    <table border="0">
                        <c:forEach items="${mentorshipGroups}" var="mentorshipGroup">

                            <div>
                                <div>
                                    <p>Group name: ${mentorshipGroup.groupName}</p>
                                </div>
                                <div>
                                    <p>Mentorship Program name: ${mentorshipGroup.mentorshipProgram.name}</p>
                                </div>


                                 <c:forEach items="${mentorshipGroup.attendendingUsers}" var="attendendedUser">
                                    <div>
                                        <p>Attended user name: ${attendendedUser.name}</p>
                                    </div>
                                    <div>
                                        <p>Attended user last name: ${attendendedUser.lastName}</p>
                                    </div>
                                 </c:forEach>

                            </div>
                            <form action="deleteMentorshipGroup" method="POST">
                                <input type="hidden" value="${mentorshipGroup.id}" name="id" />
                                <tr>
                                    <td colspan="2"><input type="submit" value="Delete Group" /></td>
                                </tr>
                                </table>
                            </form>
                            <form action="editGroupPage" method="POST">
                                <input type="hidden" value="${mentorshipGroup.id}" name="id" />
                                <tr>
                                    <td colspan="2"><input type="submit" value="Edit Mentorship Group" /></td>
                                </tr>
                                </table>
                            </form>
                            <hr>

                        </c:forEach>
                    </table>

                <hr>
                <form action="createMentorshipGroup" method="POST">
                        <table border="0">

                            <tr>
                                <td><b>Mentorship Group Name</b></td>
                                <td><input type="text" name="groupName" /></td>
                            </tr>

                              <tr>
                                <td>Mentorship Program</td>
                                <td><select name="mentorshipProgram">
                                     <c:forEach items="${mentorshipPrograms}" var="mentorshipProgram">
                                        <option name="choosedMentorshipProgramId" value="${mentorshipProgram.id}">${mentorshipProgram.name}</option>
                                    </c:forEach>
                                </select></td>
                            </tr>

                            <tr>
                                <td colspan="2"><input type="submit" value="Create Group" /></td>
                            </tr>
                        </table>
                    </form>

            </div>


        <!-- Mentorship Program AREA -->
        <div class="col-md-3">

        <h1>Mentorship Programms</h1>
                    	<table border="0">
                    	    <c:forEach items="${mentorshipPrograms}" var="mentorshipProgram">

                                <div>
                                    <div>
                                        <p>Mentorship Program Name: ${mentorshipProgram.name}</p>
                                    </div>
                                    <div>
                                        <p>Mentorship Date of start : ${mentorshipProgram.dateOfStart}</p>
                                    </div>
                                    <div>
                                        <p>Mentorship Date of end : ${mentorshipProgram.dateOfEnd}</p>
                                    </div>
                                    <div>
                                        <p>City : ${mentorshipProgram.city}</p>
                                    </div>
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
                    	<form action="createMentorshipProgram" method="POST">
                        		<table border="0">

                        			<tr>
                        				<td><b>Name</b></td>
                        				<td><input type="text" name="name" /></td>
                        			</tr>

                        			<tr>
                                        <td><b>Date Of Start</b></td>
                                        <td><input type="date" name="dateOfStart" /></td>
                                    </tr>
                                    <tr>
                                        <td><b>Date Of End</b></td>
                                        <td><input type="date" name="dateOfEnd" /></td>
                                    </tr>

                        			<tr>
                                        <td><b>City</b></td>
                                        <td><input type="text" name="city" /></td>
                                    </tr>
                        			<tr>
                        				<td colspan="2"><input type="submit" value="Create" /></td>
                        			</tr>
                        		</table>
                        	</form>

        </div>
         </div>
          </div>
    </div>



  </body>
</html>
