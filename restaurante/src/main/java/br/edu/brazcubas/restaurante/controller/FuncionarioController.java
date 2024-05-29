package br.edu.brazcubas.restaurante.controller;

import java.sql.SQLException;
import java.util.List;

import br.edu.brazcubas.restaurante.model.dao.IDAO;
import br.edu.brazcubas.restaurante.model.entity.Funcionario;

public class FuncionarioController {
    private final IDAO<Funcionario> dao;

    public FuncionarioController(IDAO<Funcionario> dao) {
        this.dao = dao;
    }

    public String cadastrarFuncionario(Funcionario funcionario) throws SQLException {
        dao.registrar(funcionario);
        return "Funcionário cadastrado com sucesso!";
    }

    public String atualizarFuncionario(Funcionario funcionario) throws SQLException {
        dao.atualizar(funcionario);
        return "Funcionário atualizado com sucesso!";
    }

    public String excluirFuncionario(Funcionario funcionario) throws SQLException {
        dao.excluir(funcionario);
        return "Funcionário excluído com sucesso!";
    }

    public List<Funcionario> listarFuncionarios() {
        return dao.retornarTodos();
    }

    public Funcionario buscarFuncionario(Funcionario funcionario) {
        return dao.retornar(funcionario);
    
    }
}
