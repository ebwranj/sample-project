<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

        <h1>Search Book</h1>
       <form:form method="post" action="viewBook" modelAttribute="subject">
        <table >
         <tr>
          <td>Title : </td>
          <td><form:input path="title"  /></td>
         </tr>
          <tr>
          <td><input type="submit" value="search" /></td>
         </tr>
        </table>
       </form:form>