/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.bll;

import br.com.senai.dal.ClienteDal;
import br.com.senai.dal.PedidoDal;
import br.com.senai.model.Cliente;
import br.com.senai.model.Pedido;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Avell 1513
 */
@WebServlet("/RelatorioBll")
public class RelatorioBll extends HttpServlet {
 private static final long serialVersionUID = 1L;
    private static String FORMULARIO = "./Relatorio.jsp";
    private ClienteDal dalc;
    private PedidoDal dal;
    
    public RelatorioBll(){
          super();
        dal = new PedidoDal();
        dalc = new ClienteDal();
    }
   @Override
     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("remover")){
            int numero = Integer.parseInt(request.getParameter("numero"));
            dal.Remover(numero);
        } 
        if (action.equalsIgnoreCase("alterar")){
            int numero = Integer.parseInt(request.getParameter("numero"));
            Pedido pedido = dal.ConsultarPorCodigo(numero);
           
         
            request.setAttribute("pedido", pedido); 
        }
        
        RequestDispatcher view = request.getRequestDispatcher(FORMULARIO);
        request.setAttribute("pedidos", dal.ConsultarTodos());
        request.setAttribute("clientes", dalc.ConsultarTodos());
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Pedido pedido = new Pedido();
        pedido.setCliente(new Cliente());
        
        try {
            Date data = new SimpleDateFormat("MM/dd/yyyy").parse(request.getParameter("data"));
            pedido.setData(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        pedido.getCliente().setCodigo(Integer.parseInt(request.getParameter("codcliente")));
       
        String numero = request.getParameter("numero");
        
        if(numero == null || numero.isEmpty())
        {
            dal.Inserir(pedido);
        }
        else
        {
            pedido.setNumero(Integer.parseInt(numero));
            dal.Alterar(pedido);
        }
        RequestDispatcher view = request.getRequestDispatcher(FORMULARIO);
        request.setAttribute("pedidos", dal.ConsultarTodos());
        view.forward(request, response);
    }

}
