/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifpb.dao;

import br.com.ifpb.model.Cliente;
import br.com.ifpb.model.Pedido;
import java.util.List;

/**
 *
 * @author aguirre
 */
public interface PedidoDaoIF extends DaoGenericIF<Pedido>{
    public List<Pedido> getPedidoByCliente(Cliente cliente);
}
