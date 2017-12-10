/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifpb.controller;

import br.com.ifpb.dao.PedidoDaoIF;
import br.com.ifpb.factory.DaoFactory;
import br.com.ifpb.factory.DaoFactoryIF;
import br.com.ifpb.model.Cliente;
import br.com.ifpb.model.Pedido;
import java.util.List;

/**
 *
 * @author aguirre
 */
public class PedidoCtrl implements PedidoDaoIF{
    private DaoFactoryIF factory = null;
    private PedidoDaoIF pedidoDao = null;
    
    public PedidoCtrl(){
        factory = DaoFactory.createFactory();
        pedidoDao = factory.criaPedidoDao();
    }

    @Override
    public List<Pedido> getPedidoByCliente(Cliente cliente) {
        return pedidoDao.getPedidoByCliente(cliente);
    }

    @Override
    public boolean incluir(Pedido o) {
        return pedidoDao.incluir(o);
    }

    @Override
    public boolean alterar(Pedido o) {
        return pedidoDao.alterar(o);
    }

    @Override
    public boolean excluir(Pedido o) {
        return pedidoDao.excluir(o);
    }

    @Override
    public List<Pedido> listar() {
        return pedidoDao.listar();
    }
}
