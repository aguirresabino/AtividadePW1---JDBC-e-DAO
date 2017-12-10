/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifpb.model;

import java.time.LocalDate;

/**
 *
 * @author aguirre
 */
public class Pedido {
    private int id;
    private LocalDate data;
    private Cliente cliente;
    private double valor;

    public Pedido(){}

    public Pedido(int id, LocalDate data, Cliente cliente, double valor) {
        this.id = id;
        this.data = data;
        this.cliente = cliente;
        this.valor = valor;
    }

    public Pedido(LocalDate data, Cliente cliente, double valor) {
        this.data = data;
        this.cliente = cliente;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pedido pedido = (Pedido) o;

        if (id != pedido.id) return false;
        if (Double.compare(pedido.valor, valor) != 0) return false;
        if (data != null ? !data.equals(pedido.data) : pedido.data != null) return false;
        return cliente != null ? cliente.equals(pedido.cliente) : pedido.cliente == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (data != null ? data.hashCode() : 0);
        result = 31 * result + (cliente != null ? cliente.hashCode() : 0);
        temp = Double.doubleToLongBits(valor);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", data=" + data +
                ", cliente=" + cliente +
                ", valor=" + valor +
                '}';
    }
}
