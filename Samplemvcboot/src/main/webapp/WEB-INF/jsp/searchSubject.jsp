<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

        <h1>Search Subject</h1>
       <form:form method="post" action="viewSubject" modelAttribute="subject">
        <table >
         <tr>
          <td>Title : </td>
          <td><form:input path="subtitle"  /></td>
         </tr>
         <tr>
          <td>Duration :</td>
          <td><form:input path="durationInHours" /></td>
         </tr>
          <tr>
          <td><input type="submit" value="search" /></td>
         </tr>
        </table>
       </form:form>