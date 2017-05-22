<%-- 
    Document   : Pedido
    Created on : 27/03/2014, 09:35:06
    Author     : Elisabete
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        
        <link rel="stylesheet" type="text/css" href="css/Style.css"/>
        <link rel="stylesheet" type="text/css" href="css/Style.css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/bootstrap-datepicker.js"></script>
        <title>Pedido com Item</title> 
        <script>
            $(function () {
                $('input[name=data]').datepicker();
            });
        </script>


    </head>
    <body>
        <form method="POST" action='PedidoItemBll' name="frmDadosPI">
            <table class="table">
                <tbody>
                <h3 class="text-center">Criar Pedido</h3>
                <tr>
                <input type="hidden" name="objeto" value="<%= request.getAttribute("pi")%>">
                <td>Numero:</td>
                <td> <input type="number" name="numero" value="<c:out value="${pi.pedido.numero}" />" /> </td> 
                <td>Data:</td>
                <td> <input type="text" name="data" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${pi.pedido.data}" />" /> </td> 
                </tr>
                <tr>
                    <td>Cliente:</td>
                    <td colspan="2"> 
                        <select name ="codcliente" >
                            <c:forEach items="${clientes}" var="cliente">
                                <option value="<c:out value="${cliente.codigo}"/>" 
                                        ${cliente.codigo ==  pi.pedido.cliente.codigo ? 'selected' : ''}>
                                    <c:out value="${cliente.nome}"/>
                                </option >
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Item:</td>
                    <td colspan = "2"> 
                        <select name ="coditem" >
                            <c:forEach items="${itens}" var="item">
                                <option value="<c:out value="${item.codigo}"/>" 
                                        ${item.codigo ==  pi.item.codigo ? 'selected' : ''}>
                                    <c:out value="${item.descricao}"/>
                                </option >
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Quantidade:</td>
                    <td> <input type="number" name="quantidade" value="<c:out value="${pi.quantidade}" />" /> </td> 
                    <td>Valor Unitário:</td>
                    <td> <input type="number" name="valor" value="<c:out value="${pi.valorunitario}" />" /> </td> 
                </tr>
                </tbody>
            </table>
            <br>
            <h3 class="text-center">Itens</h3>
            <input class="btn btn-primary" type="submit" value="Novo" name="botao" />
            <input class="btn btn-warning" type="submit" value="Salvar" name="botao" />
            <input class="btn btn-danger" type="submit" value="Pesquisar" name="botao" required="" />


            <br>
            <br>
        </form>
        <form method="GET" action='PedidoItemBll' name="frmTabelaPI">
            <table class="table table-hover" border=1>
                <thead>
                    <tr>
                        <th>Nr. Pedido</th>
                        <th>Código Item</th>
                        <th>Descrição do Item</th>
                        <th>Quantidade</th>
                        <th>Valor Unitário</th>
                        <th class=" text-center" colspan="2 " >Ação</th>

                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${pis}" var="pi">
                        <tr>
                            <td><c:out value="${pi.pedido.numero}" /></td>
                            <td><c:out value="${pi.item.codigo}" /></td>
                            <td><c:out value="${pi.item.descricao}" /> </td>
                            <td><c:out value="${pi.quantidade}" /> </td>
                            <td><c:out value="${pi.valorunitario}" /></td>
                            <td><a class="btn btn-danger" href="PedidoItemBll?action=remover&codpedido=<c:out value="${pi.pedido.numero}"/>&coditem=<c:out value="${pi.item.codigo}"/>">Alterar</a></td>
                            <td><a class="btn btn-warning" href="PedidoItemBll?action=remover&codpedido=<c:out value="${pi.pedido.numero}"/>&coditem=<c:out value="${pi.item.codigo}"/>">Remover</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </form>
    </body>
</html>
