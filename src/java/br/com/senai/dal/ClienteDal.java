/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.senai.dal;

import br.com.senai.model.Cliente;
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
public class ClienteDal {
    private Connection connection;

	public ClienteDal() {
		connection = Conexao.getConnection();
	}

	public void Inserir(Cliente cliente) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into cliente(nome,cpf,telefone,endereco) values (?, ?, ?, ? )");
			// Parameters start with 1
			preparedStatement.setString(1, cliente.getNome());
			preparedStatement.setLong(2, cliente.getCpf());
			preparedStatement.setString(3, cliente.getTelefone());
			preparedStatement.setString(4, cliente.getEndereco());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void Remover(int codigo) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("delete from cliente where codigo=?");
			// Parameters start with 1
			preparedStatement.setInt(1, codigo);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void Alterar(Cliente cliente) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("update cliente set nome=?, cpf=?, telefone=?, endereco=?" +
							"where codigo=?");
			// Parameters start with 1
			preparedStatement.setString(1, cliente.getNome());
			preparedStatement.setLong(2, cliente.getCpf());
			preparedStatement.setString (3, cliente.getTelefone());
			preparedStatement.setString(4, cliente.getEndereco());
			preparedStatement.setInt(5, cliente.getCodigo());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Cliente> ConsultarTodos() {
		List<Cliente> clientes = new ArrayList<Cliente>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from cliente");
			while (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setCodigo(rs.getInt("codigo"));
                                cliente.setNome(rs.getString("nome"));
                                cliente.setCpf(rs.getLong("cpf"));
                                cliente.setTelefone(rs.getString("telefone"));
                                cliente.setEndereco(rs.getString("endereco"));
				clientes.add(cliente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return clientes;
	}
	
	public Cliente ConsultarPorCodigo(int codigo) {
		Cliente cliente = new Cliente();
		try {
			PreparedStatement preparedStatement = connection.
					prepareStatement("select * from cliente where codigo=?");
			preparedStatement.setInt(1, codigo);
			ResultSet rs = preparedStatement.executeQuery();
			
			if (rs.next()) {
				cliente.setCodigo(rs.getInt("codigo"));
                                cliente.setNome(rs.getString("nome"));
                                cliente.setCpf(rs.getLong("cpf"));
                                cliente.setTelefone(rs.getString("telefone"));
                                cliente.setEndereco(rs.getString("endereco"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cliente;
	}
}
