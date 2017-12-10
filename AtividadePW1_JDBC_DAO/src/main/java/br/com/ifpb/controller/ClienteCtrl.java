/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifpb.controller;

import br.com.ifpb.dao.ClienteDaoIF;
import br.com.ifpb.factory.DaoFactory;
import br.com.ifpb.factory.DaoFactoryIF;
import br.com.ifpb.model.Cliente;
import java.util.List;

/**
 *
 * @author aguirre
 */
public class ClienteCtrl implements ClienteDaoIF{
    private DaoFactoryIF factory = null;
    private ClienteDaoIF clienteDao = null;
    
    public ClienteCtrl(){
        factory = DaoFactory.createFactory();
        clienteDao = factory.criaClienteDao();
    }

    @Override
    public Cliente getClienteById(int id) {
        return clienteDao.getClienteById(id);
    }

    @Override
    public boolean incluir(Cliente o) {
        return clienteDao.incluir(o);
    }

    @Override
    public boolean alterar(Cliente o) {
        return clienteDao.alterar(o);
    }

    @Override
    public boolean excluir(Cliente o) {
        return clienteDao.excluir(o);
    }

    @Override
    public List<Cliente> listar() {
        return clienteDao.listar();
    }
    
    
}
