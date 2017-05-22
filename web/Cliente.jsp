<%-- 
    Document   : Cliente
    Created on : 27/03/2014, 09:30:23
    Author     : Elisabete
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="css/Style.css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cliente</title>
    </head>
    <body>
        <form method="POST" action='ClienteBll' name="frmDadosCliente">
            <table class="table">
                <tbody>
                    <h3 class="text-center">Cadastro de Cliente</h3>
                    <tr class="info">
                        <td>Codigo:</td>
                        <td> <input type="number" readonly="readonly" name="codigo" value="<c:out value="${cliente.codigo}" />" /> </td> 
                    </tr>
                    <tr class="warning">
                        <td>Nome:</td>
                        <td> <input type="text" name="nome" value="<c:out value="${cliente.nome}" />" /> </td> 
                    </tr>
                    <tr class="active">
                        <td>CPF:</td>
                        <td> <input type="number" name="cpf" value="<c:out value="${cliente.cpf}" />" /> </td> 
                    </tr>
                    <tr class="danger">
                        <td>Telefone:</td>
                        <td> <input type="number" name="telefone" value="<c:out value="${cliente.telefone}" />" /> </td>  
                    </tr>
                    <tr class="success">
                        <td>Endereço:</td>
                        <td> <input type="text" name="endereco" value="<c:out value="${cliente.endereco}" />" /> </td> 
                    </tr>
                </tbody>
            </table>
            <br>
            
            <input class="btn btn-danger" type="submit" value="Gravar" required="TESTE" />
            <br>
            <br>
        </form>
        <form method="GET" action='ClienteBll' name="frmTabelaCliente">
            <table  class="table table-hover"  border=1>
                <thead>
                    <tr class="warning">
                        <th>Código</th>
                        <th>Nome</th>
                        <th>CPF</th>
                        <th>Telefone</th>
                        <th>Endereço</th>
                        <th colspan=2>Ação</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${clientes}" var="cliente">
                        <tr class="active"c>
                            <td><c:out value="${cliente.codigo}" /></td>
                            <td><c:out value="${cliente.nome}" /></td>
                            <td><c:out value="${cliente.cpf}" /></td>
                            <td><c:out value="${cliente.telefone}" /></td>
                            <td><c:out value="${cliente.endereco}" /></td>
                            <td><a href="ClienteBll?action=alterar&codigo=<c:out value="${cliente.codigo}"/>">Alterar</a></td>
                            <td><a href="ClienteBll?action=remover&codigo=<c:out value="${cliente.codigo}"/>">Remover</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </form>  
    </body>
</html>
