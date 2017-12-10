/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifpb.dao;

import java.util.List;

/**
 *
 * @author aguirre
 */
public interface DaoGenericIF <T> {
    
    public boolean incluir(T o);
    public boolean alterar(T o);
    public boolean excluir(T o);
    public List<T> listar();
    
}
