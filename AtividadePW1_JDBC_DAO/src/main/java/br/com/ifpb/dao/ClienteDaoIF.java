/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifpb.dao;

import br.com.ifpb.model.Cliente;

/**
 *
 * @author aguirre
 */
public interface ClienteDaoIF extends DaoGenericIF<Cliente>{
     public Cliente getClienteById(int id);
     public Cliente getClienteByDocumento(String documento);
}
