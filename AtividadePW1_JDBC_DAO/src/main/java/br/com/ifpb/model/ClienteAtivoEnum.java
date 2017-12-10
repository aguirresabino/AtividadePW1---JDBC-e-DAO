/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifpb.model;

/**
 *
 * @author aguirre
 */
public enum ClienteAtivoEnum {
    ATIVO("Ativo"), INATIVO("Inativo");

    private String valor;

    ClienteAtivoEnum(String valor){
        this.valor = valor;
    }

    public String getValor(){
        return valor;
    }
}
