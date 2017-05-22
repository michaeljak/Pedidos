/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.senai.bll;

import br.com.senai.dal.ClienteDal;
import br.com.senai.dal.ItemDal;
import br.com.senai.dal.PedidoDal;
import br.com.senai.dal.PedidoItemDal;
import br.com.senai.model.Cliente;
import br.com.senai.model.Item;
import br.com.senai.model.Pedido;
import br.com.senai.model.PedidoItem;
import java.io.IOException;
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
 * @author Elisabete
 */

@WebServlet("/PedidoItemBll")
public class PedidoItemBll extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String FORMULARIO = "./PedidoItem.jsp";
    private static String Erro= "./Erro.jsp";
    private PedidoItemDal dal;
    private ClienteDal dalc;
    private PedidoDal dalp;
    private ItemDal dali;

    public PedidoItemBll() {
        super();
        dal = new PedidoItemDal();
        dalp = new PedidoDal();
        dalc = new ClienteDal();
        dali = new ItemDal();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String numero = request.getParameter("codpedido");
                
        if (action.equalsIgnoreCase("remover")){
            int nrpedido = Integer.parseInt(numero);
            int coditem = Integer.parseInt(request.getParameter("coditem"));
            dal.Remover(nrpedido, coditem);
            Pedido p = dalp.ConsultarPorCodigo(nrpedido);
            PedidoItem pi = new PedidoItem();
            pi.setPedido(p);
            request.setAttribute("pi", pi);
        }

        
        if(!(numero == null || numero.isEmpty())){
            request.setAttribute("pis", dal.ConsultarTodosPorPedido(Integer.parseInt(numero)));
        }
        RequestDispatcher view = request.getRequestDispatcher(FORMULARIO);
        request.setAttribute("clientes", dalc.ConsultarTodos());
        request.setAttribute("itens", dali.ConsultarTodos());
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object attribute = request.getAttribute("objeto");
        if(request.getParameter("botao").equalsIgnoreCase("Novo")){
            RequestDispatcher view = request.getRequestDispatcher(FORMULARIO);
            request.setAttribute("clientes", dalc.ConsultarTodos());
            request.setAttribute("itens", dali.ConsultarTodos());
            view.forward(request, response);
        }
        if(request.getParameter("botao").equalsIgnoreCase("Salvar")){
            
             try {
            Pedido pedido = new Pedido();
            Pedido p = new Pedido();
            pedido.setCliente(new Cliente());

           
                Date data = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("data"));
                pedido.setData(data);
            

            pedido.getCliente().setCodigo(Integer.parseInt(request.getParameter("codcliente")));

            String numero = request.getParameter("numero");

            if(numero == null || numero.isEmpty())
            {
                p = dalp.Inserir(pedido);
            }
            else
            {
                p = dalp.ConsultarPorCodigo(Integer.parseInt(numero));
            }

            PedidoItem pi = new PedidoItem();
            pi.setPedido(p);
            pi.setItem(new Item());

            pi.getPedido().setNumero(p.getNumero());
            pi.getItem().setCodigo(Integer.parseInt(request.getParameter("coditem")));
            pi.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
            pi.setValorunitario(Double.parseDouble(request.getParameter("valor")));
            
            dal.Inserir(pi);

            pi.setQuantidade(0);
            pi.setValorunitario(0);
            
            RequestDispatcher view = request.getRequestDispatcher(FORMULARIO);
            request.setAttribute("pi", pi); 
            request.setAttribute("pis", dal.ConsultarTodosPorPedido(pi.getPedido().getNumero()));
            request.setAttribute("clientes", dalc.ConsultarTodos());
            request.setAttribute("itens", dali.ConsultarTodos());
            view.forward(request, response);
            } catch (ParseException e) {
                RequestDispatcher view = request.getRequestDispatcher(Erro);
           
                view.forward(request, response);
            }
        }
        if(request.getParameter("botao").equalsIgnoreCase("Pesquisar")){
            String numero = request.getParameter("numero");

            if(!(numero == null || numero.isEmpty()))
            {
                RequestDispatcher view = request.getRequestDispatcher(FORMULARIO);
                request.setAttribute("pis", dal.ConsultarTodosPorPedido(Integer.parseInt(numero)));
                request.setAttribute("clientes", dalc.ConsultarTodos());
                request.setAttribute("itens", dali.ConsultarTodos());
                view.forward(request, response);
            }
        }
    }
}
