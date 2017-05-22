/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.senai.dal;

import br.com.senai.model.Item;
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
public class ItemDal {
    private Connection connection;

	public ItemDal() {
		connection = Conexao.getConnection();
	}

	public void Inserir(Item item) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into item(descricao) values (?)");
			// Parameters start with 1
			preparedStatement.setString(1, item.getDescricao());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void Remover(int codigo) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("delete from item where codigo=?");
			// Parameters start with 1
			preparedStatement.setInt(1, codigo);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void Alterar(Item item) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("update item set descricao=? where codigo=?");
			// Parameters start with 1
			preparedStatement.setString(1, item.getDescricao());
			preparedStatement.setLong(2, item.getCodigo());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Item> ConsultarTodos() {
		List<Item> itens = new ArrayList<Item>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from item");
			while (rs.next()) {
				Item item = new Item();
                                item.setCodigo(rs.getInt("codigo"));
                                item.setDescricao(rs.getString("descricao"));
				itens.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return itens;
	}
	
	public Item ConsultarPorCodigo(int codigo) {
		Item item = new Item();
		try {
			PreparedStatement preparedStatement = connection.
					prepareStatement("select * from item where codigo=?");
			preparedStatement.setInt(1, codigo);
			ResultSet rs = preparedStatement.executeQuery();
			
			if (rs.next()) {
				item.setCodigo(rs.getInt("codigo"));
                                item.setDescricao(rs.getString("descricao"));
                        }
                        
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return item;
	}
}
