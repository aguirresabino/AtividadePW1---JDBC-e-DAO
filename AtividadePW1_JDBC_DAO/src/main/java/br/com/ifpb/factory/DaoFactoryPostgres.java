/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifpb.factory;

import br.com.ifpb.dao.ClienteDaoIF;
import br.com.ifpb.dao.ClienteDaoPostgres;
import br.com.ifpb.dao.PedidoDaoIF;
import br.com.ifpb.dao.PedidoDaoPostgres;

/**
 *
 * @author aguirre
 */
public class DaoFactoryPostgres implements DaoFactoryIF{

    @Override
    public ClienteDaoIF criaClienteDao() {
        return new ClienteDaoPostgres();
    }

    @Override
    public PedidoDaoIF criaPedidoDao() {
        return new PedidoDaoPostgres();
    }
    
}
