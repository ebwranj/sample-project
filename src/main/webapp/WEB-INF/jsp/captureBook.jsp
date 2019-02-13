<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

        <h1>Add Book</h1>
       <form:form method="post" action="/addBook/${subtitle}" modelAttribute="book">
        <table >
         <tr>
          <td>Title : </td>
          <td><form:input path="title"  /></td>
         </tr>
         <tr>
          <td>Price :</td>
          <td><form:input path="price" /></td>
         </tr>
         <tr>
           <td>Volume :</td>
           <td><form:input path="volume" /></td>
          </tr>

          <tr>
           <td>Pubish Date :</td>
           <td><form:input type="date" path="publishDate" /></td>
          </tr>

          <td><input type="submit" value="add" /></td>
         </tr>
        </table>
       </form:form>