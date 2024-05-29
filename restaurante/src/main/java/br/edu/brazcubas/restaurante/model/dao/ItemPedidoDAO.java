package br.edu.brazcubas.restaurante.model.dao;

import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import br.edu.brazcubas.restaurante.database.PostgresConfig;
import br.edu.brazcubas.restaurante.model.entity.ItemPedido;
import br.edu.brazcubas.restaurante.model.entity.Prato;

public class ItemPedidoDAO implements IDAO<ItemPedido>{
    @Override
    public void registrar(ItemPedido itemPedido) throws SQLException {
        try {
            Connection conn = PostgresConfig.getConnection();
            String query = "INSERT INTO item_pedido VALUES (default, ?, ?, ?);";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, itemPedido.getPedido().getId());
            stmt.setInt(2, itemPedido.getPrato().getId());
            stmt.setInt(3, itemPedido.getQuantidade());
            stmt.executeUpdate();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Override
    public void atualizar(ItemPedido itemPedido) throws SQLException {
        try {
            Connection conn = PostgresConfig.getConnection();
            String query = "UPDATE item_pedido SET qtd = ? WHERE id_item_pedido = ?;";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, itemPedido.getQuantidade());
            stmt.executeUpdate();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Override
    public void excluir(ItemPedido itemPedido) {
        try {
            Connection conn = PostgresConfig.getConnection();
            String query = "DELETE FROM item_pedido WHERE id_item_pedido = ?;";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, itemPedido.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Override
    public List<ItemPedido> retornarTodos() {
        /* List<ItemPedido> minhaList = new ArrayList<ItemPedido>();
        try {
            Connection conn = PostgresConfig.getConnection();
            String query = "SELECT "+
                                "ip.id_itemPedido AS id_itemPedido, ip.qtd AS qtd_itemPedido "+
                                "p.id_prato AS id_prato, p.nome AS nome_prato, p.preco AS preco_prato, p.descricao AS descricao_prato, p.avaliacao AS avaliacao_prato "+
                                "FROM itemPedido ip INNER JOIN prato p ON ip.id_pedido = p.id_pedido WHERE ip.id_pedido = ?;";
            PreparedStatement stmt = conn.prepareStatement(query);
                        stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id_pedido = rs.getInt("id_pedido");
                String nome_prato = rs.getString("nome_prato");
                int quantidade = rs.getInt("quantidade");

                //ItemPedido itemPedido = new ItemPedido(id_pedido, quantidade, nome_prato);

                //minhaList.add(itemPedido);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return minhaList; */
        return null;
    }

    @Override
    public ItemPedido retornar(ItemPedido itemPedidoLocal) {
        ItemPedido itemPedido = new ItemPedido(itemPedidoLocal.getId(), null, 0, null);
        try {
            Connection conn = PostgresConfig.getConnection();
            String query = "SELECT ip.id_item_pedido AS id_item_pedido, ip.qtd AS qtd_item_pedido, " 
                            +"p.id_prato AS id_prato, p.nome AS nome_prato, p.preco AS preco_prato, p.descricao AS descricao_prato, p.preco AS preco_prato, p.avaliacao AS avaliacao_prato "
                            +"FROM item_pedido ip INNER JOIN prato p ON ip.id_prato = p.id_prato WHERE ip.id_pedido = ?;";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, itemPedidoLocal.getPedido().getId());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int quantidade = rs.getInt("qtd_item_pedido");
                Prato prato = new Prato(rs.getInt("id_prato"), rs.getString("nome_prato"), rs.getString("descricao_prato"), rs.getDouble("preco_prato"), rs.getDouble("avaliacao_prato"));

                itemPedido.setPrato(prato);
                itemPedido.setQuantidade(quantidade);
            }

        } catch (Exception e) {
            System.err.println(e);
        }
        return  null;//itemPedido;
    }

    public List<ItemPedido> retornarTodosPorPedido(int id){
        List<ItemPedido> listaItensPedido = new ArrayList<>();
        try {
            Connection conn = PostgresConfig.getConnection();
            String query = "SELECT ip.id_item_pedido AS id_item_pedido, ip.qtd AS qtd_item_pedido, " 
                            +"p.id_prato AS id_prato, p.nome AS nome_prato, p.preco AS preco_prato, p.descricao AS descricao_prato, p.preco AS preco_prato, p.avaliacao AS avaliacao_prato "
                            +"FROM item_pedido ip INNER JOIN prato p ON ip.id_prato = p.id_prato WHERE ip.id_pedido = ?;";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int quantidade = rs.getInt("qtd_item_pedido");
                Prato prato = new Prato(rs.getInt("id_prato"), rs.getString("nome_prato"), rs.getString("descricao_prato"), rs.getDouble("preco_prato"), rs.getDouble("avaliacao_prato"));

                ItemPedido itemPedido = new ItemPedido(quantidade, prato);  
                listaItensPedido.add(itemPedido);
            }

        } catch (Exception e) {
            System.err.println(e);
        }
        return  listaItensPedido;
    }
    
}