/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.senai.bll;

import br.com.senai.dal.ClienteDal;
import br.com.senai.model.Cliente;
import java.io.IOException;
import java.text.ParseException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Elisabete
 */
@WebServlet("/ClienteBll")
public class ClienteBll extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String FORMULARIO = "./Cliente.jsp";
    private static String Erro= "./Erro.jsp";
    private ClienteDal dal;

    public ClienteBll() {
        super();
        dal = new ClienteDal();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("remover")){
            int codigo = Integer.parseInt(request.getParameter("codigo"));
            dal.Remover(codigo);
        } 
        if (action.equalsIgnoreCase("alterar")){
            int codigo = Integer.parseInt(request.getParameter("codigo"));
            Cliente cliente = dal.ConsultarPorCodigo(codigo);
            request.setAttribute("cliente", cliente); 
        }
        
        RequestDispatcher view = request.getRequestDispatcher(FORMULARIO);
        request.setAttribute("clientes", dal.ConsultarTodos());
        view.forward(request, response);
        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            
        
        Cliente cliente = new Cliente();
        cliente.setNome(request.getParameter("nome"));
        cliente.setCpf(Long.parseLong(request.getParameter("cpf")));
        cliente.setTelefone(request.getParameter("telefone"));
        cliente.setEndereco(request.getParameter("endereco"));
        
        String codigo = request.getParameter("codigo");
        
        if(codigo == null || codigo.isEmpty())
        {
            dal.Inserir(cliente);
        }
        else
        {
            cliente.setCodigo(Integer.parseInt(codigo));
            dal.Alterar(cliente);
        }
        RequestDispatcher view = request.getRequestDispatcher(FORMULARIO);
        request.setAttribute("clientes", dal.ConsultarTodos());
        view.forward(request, response);
        
         } 
       catch (Exception e) {
           
                RequestDispatcher view = request.getRequestDispatcher(Erro);
           
                view.forward(request, response);
            }
    }
}
