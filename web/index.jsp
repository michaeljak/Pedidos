
<%-- 
    Document   : index
    Created on : 26/03/2014, 23:16:16
    Author     : Elisabete
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="css/Style.css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pedidos</title>
        <script src="./js/carregarFrame.js"></script>
    </head>
    <body>
        <button class="btn btn-success" onclick="return carregarCliente();">Cliente</button>
        <button class="btn btn-success" onclick="return carregarItem();">Item</button>
        <button class="btn btn-success" onclick="return carregarPedido();">Pedido</button>
        <button class="btn btn-success" onclick="return carregarPedidoItem();">PedidoItem</button>
        <button class="btn btn-success" onclick="return carregarRelatorio();">Relatorio</button>
        <br>
        <br>
        <iframe id="fraFormulario" width="100%" height="750"></iframe>
    </body>
</html>
