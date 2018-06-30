<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tags:pageTemplate titulo="Livros de Java, Android, iPhone, Ruby, PHP e muito mais ....">

    <section id="index-section" class="container middle">

        <h1><fmt:message key="views.usuarios.cadastro"/></h1>
        <form:form action="${s:mvcUrl('UC#editar').build()}" method="POST" commandName="usuario">
            <button type="submit"><fmt:message key="forms.atualizar"/></button>
        </form:form>

    </section>

</tags:pageTemplate>
