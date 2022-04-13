<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Insert title here</title>
        <style>
            form fieldset {
                width : 40%;
            }
            form fieldset label {
                display : block;
                /*width : 50%;
                float :left;*/
            }
            form fieldset input, form fieldset textarea {
                width : 100%;
            }
        </style>
    </head>
    <body>
        <%-- Обращение к uiModel--%>
        <h1>${user.hello}</h1>
        <%-- метод, которым будет отправлен запрос после нажатия кнопки submit--%>
        <%-- адрес, по которому осуществляется отправка, указана в action--%>
        <form method="POST" action="hello">

            <fieldset>
                <div>
                    <label for="userName-field">
                        <spring:message code="header_your_name"/>
                    </label>
                    <%-- имя элемента управления совпадает с именем свойства модели--%>
                    <%-- для автоматического связывания--%>
                    <%-- В этом случае значение из этого параметра будет помещено--%>
                    <%-- в одноименное свойство userName нашей модели userVM--%>
                    <input id="userName-field" type="text"
                            name="userName"
                            value="${user.userName}">
                </div>
                <div>
                    <input type="submit" value="OK">
                    <input type="hidden"
                           name="${_csrf.parameterName}"
                           value="${_csrf.token}"/>
                </div>
            </fieldset>
        </form>
    </body>
</html>