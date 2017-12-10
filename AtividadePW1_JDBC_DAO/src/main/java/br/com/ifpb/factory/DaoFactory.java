/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifpb.factory;

/**
 *
 * @author aguirre
 */
public class DaoFactory {
    public static DaoFactoryIF createFactory(){
        return new DaoFactoryPostgres();
    }
}
