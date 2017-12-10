/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifpb.dao;

import br.com.ifpb.factory.ConFactory;
import br.com.ifpb.model.Cliente;
import br.com.ifpb.model.Pedido;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aguirre
 */
public class PedidoDaoPostgres implements PedidoDaoIF{

    @Override
    public boolean incluir(Pedido pedido) {
        try {
            Connection con = getConnection();
            
            PreparedStatement stmt = con.prepareStatement("INSERT INTO Pedido(Data, Valor, idCliente) VALUES (?,?,?)");

            stmt.setDate(1, Date.valueOf(pedido.getData()));
            stmt.setDouble(2, pedido.getValor());
            stmt.setInt(3, pedido.getCliente().getId());

            int resultado = stmt.executeUpdate();
            stmt.close();
            con.close();

            return resultado > 0;
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PedidoDaoPostgres.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean alterar(Pedido pedido) {
        try {
            Connection con = getConnection();
            
            PreparedStatement stmt = con.prepareStatement("UPDATE Pedido SET Data = ?, Valor = ?, idCliente = ? WHERE Id = ?");

            stmt.setDate(1, Date.valueOf(pedido.getData()));
            stmt.setDouble(2, pedido.getValor());
            stmt.setInt(3, pedido.getCliente().getId());
            stmt.setInt(4, pedido.getId());

            int resultado = stmt.executeUpdate();
            stmt.close();
            con.close();

            return resultado > 0;
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PedidoDaoPostgres.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean excluir(Pedido pedido) {
        try {
            Connection con = getConnection();
            
            PreparedStatement stmt = con.prepareStatement("DELETE FROM Pedido WHERE Id = ?");

            stmt.setInt(1, pedido.getId());

            int resultado = stmt.executeUpdate();
            stmt.close();
            con.close();

            return resultado > 0;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PedidoDaoPostgres.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public List<Pedido> listar() {
        try {
            Connection con = getConnection();
            
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM Pedido");
            
            ResultSet rs = stmt.executeQuery();
            List<Pedido> pedidos = new ArrayList<>();
            
            while(rs.next()){
                Pedido pedido = new Pedido();
                pedido.setId(rs.getInt("Id"));
                
                Instant instant = Instant.ofEpochMilli(rs.getDate("Data").getTime());
                pedido.setData(LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate());
                
                pedido.setCliente(new ClienteDaoPostgres().getClienteById(rs.getInt("idCliente")));
                pedido.setValor(rs.getDouble("Valor"));
                
                pedidos.add(pedido);
            }
            stmt.close();
            con.close();
            return pedidos;
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PedidoDaoPostgres.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    
    @Override
    public List<Pedido> getPedidoByCliente(Cliente cliente) {
        try {
            Connection con = getConnection();
            
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM Pedido WHERE IdCliente = ?");
            
            stmt.setInt(1, cliente.getId());
            
            ResultSet rs = stmt.executeQuery();
            List<Pedido> pedidos = new ArrayList<>();
            
            while(rs.next()){
                Pedido pedido = new Pedido();
                
                pedido.setId(rs.getInt("Id"));
                
                Instant instant = Instant.ofEpochMilli(rs.getDate("Data").getTime());
                pedido.setData(LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate());
                
                pedido.setCliente(new ClienteDaoPostgres().getClienteById(rs.getInt("idCliente")));
                pedido.setValor(rs.getDouble("Valor"));
                
                pedidos.add(pedido);
            }
            stmt.close();
            con.close();
            return pedidos;
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PedidoDaoPostgres.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    private Connection getConnection() throws ClassNotFoundException, SQLException{
        return new ConFactory().getConnection();
    }
}
