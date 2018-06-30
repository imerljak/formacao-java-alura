<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<tags:pageTemplate titulo="Livros de Java, Android, iPhone, Ruby, PHP e muito mais ....">

    <section id="index-section" class="container middle">

        <h1>Lista de Pedidos Atuais</h1>
        <table class="table table-bordered table-striped table-hover">
            <thead>
            <tr>
                <th>ID</th>
                <th>Valor</th>
                <th>Data Pedido</th>
                <th>Titulos</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${pedidos}" var="pedido">
                <fmt:formatDate value="${pedido.data.time}" type="date" pattern="dd/MM/yy" var="dataPedido" />
                <tr>
                    <td>${pedido.id}</td>
                    <td>${pedido.valor}</td>
                    <td>${dataPedido}</td>
                    <td>${pedido.titulosString}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </section>

</tags:pageTemplate>
