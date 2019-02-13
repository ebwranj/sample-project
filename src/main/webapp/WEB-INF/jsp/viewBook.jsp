<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

        <h1>View Book</h1>

        <table >
         <tr>
              <td>ID :</td>
              <td>${book.id}</td>
         </tr>
         <tr>
          <td>Title : </td>
          <td>${book.title}</td>
         </tr>
         <tr>
          <td>Cost :</td>
          <td>${book.price}</td>
         </tr>
         <tr>
            <td>Volume :</td>
            <td>${book.volume}</td>
         </tr>
         <tr>
            <td>Published date :</td>
            <td>${book.publishDate}</td>
         </tr>
        </table>
       <a href="/listSubject">Go back to Subject</a>