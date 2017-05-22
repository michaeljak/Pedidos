/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.senai.model;

/**
 *
 * @author Elisabete
 */
public class PedidoItem {
    private Pedido pedido;
    private Item item;
    private int quantidade;
    private double valorunitario;
   
    /**
     * @return the quantidade
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * @param quantidade the quantidade to set
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * @return the valorunitario
     */
    public double getValorunitario() {
        return valorunitario;
    }

    /**
     * @param valorunitario the valorunitario to set
     */
    public void setValorunitario(double valorunitario) {
        this.valorunitario = valorunitario;
    }

    /**
     * @return the pedido
     */
    public Pedido getPedido() {
        return pedido;
    }

    /**
     * @param pedido the pedido to set
     */
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    /**
     * @return the item
     */
    public Item getItem() {
        return item;
    }

    /**
     * @param item the item to set
     */
    public void setItem(Item item) {
        this.item = item;
    }
    
    
    
}
