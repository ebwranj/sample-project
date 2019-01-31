<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

        <h1>View Subject</h1>

        <table >
         <tr>
              <td>ID :</td>
              <td>${subject.id}</td>
         </tr>
         <tr>
          <td>Title : </td>
          <td>${subject.subtitle}</td>
         </tr>
         <tr>
          <td>Duration :</td>
          <td>${subject.durationInHours}</td>
         </tr>
        </table>
       <a href="/sample-mvc/listSubject">Go back to Subject</a>