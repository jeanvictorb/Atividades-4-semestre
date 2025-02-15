package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Turma;
import com.example.demo.Repository.TurmaRepository;

@Service
public class TurmaService {
    
    @Autowired
    private TurmaRepository turmaRepository;

    public String save(Turma turma) {
        try {
            if (turma.getNome() == null || turma.getNome().trim().isEmpty()) {
                throw new IllegalArgumentException("O nome da turma é obrigatório.");
            }
            turmaRepository.save(turma);
            return "Turma salva com sucesso!";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "Erro ao salvar a turma: " + e.getMessage();
        }
    }

    public String delete(long id) {
        try {
            turmaRepository.deleteById(id);
            return "Turma deletada com sucesso!";
        } catch (EmptyResultDataAccessException e) {
            return "Turma não encontrada.";
        } catch (Exception e) {
            return "Erro ao deletar a turma: " + e.getMessage();
        }
    }

    public Optional<Turma> findById(long id) {
        return turmaRepository.findById(id);
    }

    public String update(long id, Turma turma) {
        try {
            if (turma.getNome() == null || turma.getNome().trim().isEmpty()) {
                throw new IllegalArgumentException("O nome da turma é obrigatório.");
            }
            if (!turmaRepository.existsById(id)) {
                throw new IllegalArgumentException("Turma não encontrada.");
            }
            turma.setId(id);
            turmaRepository.save(turma);
            return "Atualização realizada com sucesso!";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "Erro ao atualizar a turma: " + e.getMessage();
        }
    }

    public List<Turma> findAll() {
        return turmaRepository.findAll();
    }
}
