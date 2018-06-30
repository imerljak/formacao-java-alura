<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tags:pageTemplate titulo="Livros de Java, Android, iPhone, Ruby, PHP e muito mais ....">

    <section id="index-section" class="container middle">

        <h1><fmt:message key="views.usuarios.cadastro"/></h1>
        <form:form action="${s:mvcUrl('UC#gravar').build()}" method="POST" commandName="usuarioModel">
            <div class="form-group">
                <label for="nome"><fmt:message key="forms.usuarios.nome"/></label>
                <form:input path="nome" cssClass="form-control"/>
                <form:errors path="nome" />
            </div>

            <div class="form-group">
                <label for="email"><fmt:message key="forms.usuarios.email"/></label>
                <form:input type="email" path="email" cssClass="form-control"/>
                <form:errors path="email" />
            </div>

            <div class="form-group">
                <label for="senha"><fmt:message key="forms.usuarios.senha"/></label>
                <form:password path="senha" cssClass="form-control"/>
                <form:errors path="senha" />
            </div>

            <div class="form-group">
                <label for="confirmaSenha"><fmt:message key="forms.usuarios.confirmaSenha"/></label>
                <form:password path="confirmaSenha" cssClass="form-control"/>
                <form:errors path="confirmaSenha" />
            </div>

            <button type="submit"><fmt:message key="forms.cadastrar"/></button>
        </form:form>

    </section>

</tags:pageTemplate>
