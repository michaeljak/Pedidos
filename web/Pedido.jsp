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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pedido</title>
        <script>
            $(function() {
    $('input[name=data]').datepicker();
});
            </script>
        
    </head>
    <body>
        <form method="POST" action='PedidoBll' name="frmDadosPedido">
            <table class="table">
                <tbody>
                    <h3 class="text-center">Pedidos</h3>
                    <tr>
                        <td>Numero:</td>
                        <td> <input type="text" readonly="readonly" name="numero"disabled="" value="<c:out value="${pedido.numero}" />" /> </td> 
                    </tr>
                    <tr>
                        <td>Data:</td>
                        <td> <input type="text" name="data" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${pedido.data}" />" /> </td> 
                    </tr>
                    <tr>
                        <td>Cliente:</td>
                        <td> 
                            <select name ="codcliente" >
                                <c:forEach items="${clientes}" var="cliente">
                                    <option value="<c:out value="${cliente.codigo}"/>" 
                                            ${cliente.codigo ==  pedido.cliente.codigo ? 'selected' : ''}>
                                                   <c:out value="${cliente.nome}"/>
                                    </option >
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                </tbody>
            </table>
            <br>
            
            <input class="btn btn-danger" type="submit" value="Gravar" />
            <br>
            <br>
        </form>
        <form method="GET" action='PedidoBll' name="frmTabelaPedido">
            <table  class="table table-hover"  border=1>
                <thead>
                    <tr>
                        <th>Número do Pedido</th>
                        <th>Data do Pedido</th>
                        <th>Cliente</th>
                        <th colspan=2>Ação</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${pedidos}" var="pedido">
                        <tr>
                            <td><c:out value="${pedido.numero}" /></td>
                            <td><c:out value="${pedido.data}" /> </td>
                            <td><c:out value="${pedido.cliente.nome}" /> </td>
                            <td><a href="PedidoBll?action=alterar&numero=<c:out value="${pedido.numero}"/>">Alterar</a></td>
                            <td><a href="PedidoBll?action=remover&numero=<c:out value="${pedido.numero}"/>">Remover</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </form>
    </body>
</html>
