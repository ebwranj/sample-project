   <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h1>Subject List</h1>
<table border="2" width="70%" cellpadding="2" modelAttribute="subjectList">
<tr><th>Title</th><th>Duration</th><th>Links</th><th>Action</th>
   <c:forEach var="sub" items="${subjectList}">
   <tr>

   <td>${sub.subtitle}</td>
   <td>${sub.durationInHours}</td>
   <td><a href="listBook/${sub.subtitle}">Books</a></td>
   <td><a href="deleteSubject/${sub.subtitle}">Delete Subject</a></td>
   </tr>
   </c:forEach>
   </table>
   <br/>
   <a href="captureSubject">Add New Subject</a>