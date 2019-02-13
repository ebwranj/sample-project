   <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h1>Book List</h1>
<table border="2" width="70%" cellpadding="2" modelAttribute="subjectList">
<tr><th>Title</th><th>Cost</th><th>Volume</th><th>Pubish Date</th><th>Action</th>
   <c:forEach var="book" items="${bookList}">
   <tr>

   <td>${book.title}</td>
   <td>${book.price}</td>
   <td>${book.volume}</td>
   <td>${book.publishDate}</td>
   <td><a href="/deleteBook/${book.title}/${subtitle}">Delete Book</a></td>
   </tr>
   </c:forEach>
   </table>
   <br/>
   <a href="/captureBook/${subtitle}">Add New Book</a>
   <a href="/listSubject">Go back to Subject</a>