/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.senai.bll;

import br.com.senai.dal.ItemDal;
import br.com.senai.model.Item;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
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
@WebServlet("/ItemBll")
public class ItemBll extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String FORMULARIO = "./Item.jsp";
    private static String Erro= "./Erro.jsp";
    private ItemDal dal;

    public ItemBll() {
        super();
        dal = new ItemDal();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("remover")){
            int codigo = Integer.parseInt(request.getParameter("codigo"));
            dal.Remover(codigo);
        } 
        if (action.equalsIgnoreCase("alterar")){
            int codigo = Integer.parseInt(request.getParameter("codigo"));
            Item item = dal.ConsultarPorCodigo(codigo);
            request.setAttribute("item", item); 
        }
        
        RequestDispatcher view = request.getRequestDispatcher(FORMULARIO);
        request.setAttribute("itens", dal.ConsultarTodos());
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            
        
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        
        Enumeration allParameterNames = request.getParameterNames();
            while(allParameterNames.hasMoreElements())
            {
                Object object = allParameterNames.nextElement();
                String param =  (String)object;
                String value =  request.getParameter(param);
                out.println("Parameter Name is '"+param+"' and Parameter Value is '"+value+"'");
            }
        
        Item item = new Item();
        item.setDescricao(request.getParameter("descricao"));
                
        String codigo = request.getParameter("codigo");
        
        if(codigo == null || codigo.isEmpty())
        {
            dal.Inserir(item);
        }
        else
        {
            item.setCodigo(Integer.parseInt(codigo));
            dal.Alterar(item);
        }
        RequestDispatcher view = request.getRequestDispatcher(FORMULARIO);
        request.setAttribute("itens", dal.ConsultarTodos());
        view.forward(request, response);
        
        }catch (Exception e) {
           
                RequestDispatcher view = request.getRequestDispatcher(Erro);
           
                view.forward(request, response);

        }
    }
}
