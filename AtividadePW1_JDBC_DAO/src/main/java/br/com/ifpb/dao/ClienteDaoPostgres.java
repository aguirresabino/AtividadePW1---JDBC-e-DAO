/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifpb.dao;

import br.com.ifpb.factory.ConFactory;
import br.com.ifpb.model.Cliente;
import br.com.ifpb.model.ClienteAtivoEnum;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aguirre
 */
public class ClienteDaoPostgres implements ClienteDaoIF{

    @Override
    public boolean incluir(Cliente cliente) {
        try {
            Connection con = getConnection();
            
            PreparedStatement stmt = con.prepareStatement("INSERT INTO Cliente(Nome, Documento, Saldo, Ativo) VALUES (?,?,?,?)");

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getDocumento());
            stmt.setDouble(3, cliente.getSaldo());
            stmt.setString(4, cliente.getAtivo().getValor());

            int resultado = stmt.executeUpdate();
            stmt.close();
            con.close();

            return resultado > 0;
            
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ClienteDaoPostgres.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean alterar(Cliente cliente) {
        try {
            Connection con = getConnection();
            
            PreparedStatement stmt = con.prepareStatement("UPDATE Cliente SET Nome = ?, Documento = ?, Saldo = ?, Ativo = ? WHERE Id = ?");

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getDocumento());
            stmt.setDouble(3, cliente.getSaldo());
            stmt.setString(4, cliente.getAtivo().getValor());
            stmt.setInt(5, cliente.getId());
            
            int resultado = stmt.executeUpdate();
            stmt.close();
            con.close();

            return resultado > 0;
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ClienteDaoPostgres.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean excluir(Cliente cliente) {
        try {
            Connection con = getConnection();
            
            PreparedStatement stmt = con.prepareStatement("DELETE FROM Cliente  WHERE Id = ?");

            stmt.setInt(1, cliente.getId());

            int resultado = stmt.executeUpdate();
            stmt.close();
            con.close();

            return resultado > 0;
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ClienteDaoPostgres.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public List<Cliente> listar() {
        try {
            Connection con = getConnection();
            
            List<Cliente> clientes = new ArrayList<>();            
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM Cliente");
            
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Cliente cliente = new Cliente();
                
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setDocumento(rs.getString("documento"));
                cliente.setSaldo(rs.getDouble("saldo"));
                
                if(rs.getString("ativo").equals(ClienteAtivoEnum.ATIVO.getValor())){
                    cliente.setAtivo(ClienteAtivoEnum.ATIVO);
                }else{
                    cliente.setAtivo(ClienteAtivoEnum.INATIVO);
                }

                clientes.add(cliente);
            }
            
            stmt.close();
            con.close();

            return clientes;
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ClienteDaoPostgres.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    @Override
    public Cliente getClienteById(int id) {
        try {
            Connection con = getConnection();
            
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM Cliente WHERE id = ?");

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            
            rs.next();
            
            Cliente cliente = new Cliente();
            cliente.setId(rs.getInt("id"));
            cliente.setNome(rs.getString("nome"));
            cliente.setDocumento(rs.getString("documento"));
            cliente.setSaldo(rs.getDouble("saldo"));
            if(rs.getString("Ativo").equals(ClienteAtivoEnum.ATIVO.getValor())){
                cliente.setAtivo(ClienteAtivoEnum.ATIVO);
            }else{
                cliente.setAtivo(ClienteAtivoEnum.INATIVO);
            }
            
            stmt.close();
            con.close();
            
            return cliente;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ClienteDaoPostgres.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    @Override
    public Cliente getClienteByDocumento(String documento) {
        try {
            Connection con = getConnection();
            
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM Cliente WHERE documento = ?");

            stmt.setString(1, documento);

            ResultSet rs = stmt.executeQuery();
            
            rs.next();
            
            Cliente cliente = new Cliente();
            cliente.setId(rs.getInt("id"));
            cliente.setNome(rs.getString("nome"));
            cliente.setDocumento(rs.getString("documento"));
            cliente.setSaldo(rs.getDouble("saldo"));
            if(rs.getString("Ativo").equals(ClienteAtivoEnum.ATIVO.getValor())){
                cliente.setAtivo(ClienteAtivoEnum.ATIVO);
            }else{
                cliente.setAtivo(ClienteAtivoEnum.INATIVO);
            }
            
            stmt.close();
            con.close();
            
            return cliente;
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ClienteDaoPostgres.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    private Connection getConnection() throws ClassNotFoundException, SQLException{
       return new ConFactory().getConnection();
    }
}
