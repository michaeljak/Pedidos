/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.senai.dal;

import br.com.senai.model.Pedido;
import br.com.senai.util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Elisabete
 */
public class PedidoDal {
    private Connection connection;

	public PedidoDal() {
		connection = Conexao.getConnection();
	}

	public Pedido Inserir(Pedido pedido) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into pedido(data, codcliente) values (?, ?) RETURNING numero ");
			// Parameters start with 1
			preparedStatement.setDate(1, new java.sql.Date(pedido.getData().getTime()));
                        preparedStatement.setInt(2, pedido.getCliente().getCodigo());
			ResultSet rs = preparedStatement.executeQuery();
                        if(rs.next()) {
                          return ConsultarPorCodigo(rs.getInt("numero"));
                        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
                return null;
	}
	
	public void Remover(int numero) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("delete from pedido where numero=?");
			// Parameters start with 1
			preparedStatement.setInt(1, numero);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void Alterar(Pedido pedido) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("update pedido set data=?, codcliente=? where numero=?");
			// Parameters start with 1
			preparedStatement.setDate(1, new java.sql.Date(pedido.getData().getTime()));
                        preparedStatement.setInt(2, pedido.getCliente().getCodigo());
                        preparedStatement.setInt(3, pedido.getNumero());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Pedido> ConsultarTodos() {
		List<Pedido> pedidos = new ArrayList<Pedido>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from pedido p, cliente c where p.codcliente = c.codigo");
			while (rs.next()) {
				Pedido pedido = new Pedido();
                                pedido.setNumero(rs.getInt("numero"));
                                pedido.setData(rs.getDate("data"));
                                ClienteDal dalc = new ClienteDal();
                                pedido.setCliente(dalc.ConsultarPorCodigo(rs.getInt("codigo")));
				pedidos.add(pedido);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return pedidos;
	}
	
	public Pedido ConsultarPorCodigo(int numero) {
		Pedido pedido = new Pedido();
                ClienteDal dalc = new ClienteDal();
		try {
			PreparedStatement preparedStatement = connection.
					prepareStatement("select * from pedido p, cliente c where p.codcliente = c.codigo and numero=?");
			preparedStatement.setInt(1, numero);
			ResultSet rs = preparedStatement.executeQuery();
			
			if (rs.next()) {
				pedido.setNumero(rs.getInt("numero"));
                                pedido.setData(rs.getDate("data"));
                                pedido.setCliente(dalc.ConsultarPorCodigo(rs.getInt("codigo")));
                        }
                        
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return pedido;
	}
}
