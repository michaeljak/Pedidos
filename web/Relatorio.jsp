<%-- 
    Document   : Relatorio
    Created on : 12/04/2017, 22:00:58
    Author     : Avell 1513
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="css/Style.css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Relatorios</title>
       
    </head>
    <body>
        <form method="POST" action='RelatorioBll' name="frmDadosRelatorio">
            <h3 class="text-center">Relatorio</h3>
            <br>
            <br>
        </form>
        <form method="GET" action='PedidoBll' name="frmTabelaRelatorio">
            <table  class="table table-hover"  border=1>
                <thead>
                    <tr>
                        <th>Número do Pedido</th>
                        <th>Data do Pedido</th>
                        <th>Cliente</th>
                        </tr>
                </thead>
                <tbody>
                    <c:forEach items="${pedidos}" var="pedido">
                        <tr>
                            <td><c:out value="${pedido.numero}" /></td>
                            <td><c:out value="${pedido.data}" /> </td>
                            <td><c:out value="${pedido.cliente.nome}" /> </td>
                            
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </form>
    </body>
</html>

