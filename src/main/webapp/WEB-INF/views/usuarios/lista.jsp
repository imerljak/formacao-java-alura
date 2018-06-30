<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tags:pageTemplate titulo="Livros de Java, Android, iPhone, Ruby, PHP e muito mais ....">

    <section id="index-section" class="container middle">

        <c:if test="${not empty message}">
            <p>
                <fmt:message key="${message}"/>
            </p>
        </c:if>

        <a href="${s:mvcUrl('UC#form').build()}"><fmt:message key="navegacao.usuarios.novo"/></a>

        <h1><fmt:message key="views.usuarios.lista"/></h1>
        <table>
            <thead>
            <tr>
                <th><fmt:message key="lists.usuarios.nome"/></th>
                <th><fmt:message key="lists.usuarios.email"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${usuarios}" var="usuario">
                <tr>
                    <td>${usuario.nome}</td>
                    <td>${usuario.email}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </section>

</tags:pageTemplate>
