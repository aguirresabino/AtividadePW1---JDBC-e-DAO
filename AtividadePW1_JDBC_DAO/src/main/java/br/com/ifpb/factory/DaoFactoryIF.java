/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifpb.factory;

import br.com.ifpb.dao.ClienteDaoIF;
import br.com.ifpb.dao.PedidoDaoIF;

/**
 *
 * @author aguirre
 */
public interface DaoFactoryIF {
    public ClienteDaoIF criaClienteDao();
    public PedidoDaoIF criaPedidoDao();
}
