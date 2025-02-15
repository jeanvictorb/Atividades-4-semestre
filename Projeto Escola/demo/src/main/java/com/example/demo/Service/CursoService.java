package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Curso;
import com.example.demo.Repository.CursoRepository;

@Service
public class CursoService {
    
    @Autowired
    private CursoRepository cursoRepository;

    public String save(Curso curso) {
        try {
            if (curso.getNome() == null || curso.getNome().trim().isEmpty()) {
                throw new IllegalArgumentException("O nome do curso é obrigatório.");
            }
            cursoRepository.save(curso);
            return "Curso salvo com sucesso!";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "Erro ao salvar o curso: " + e.getMessage();
        }
    }

    public String delete(long id) {
        try {
            cursoRepository.deleteById(id);
            return "Curso deletado com sucesso!";
        } catch (EmptyResultDataAccessException e) {
            return "Curso não encontrado.";
        } catch (Exception e) {
            return "Erro ao deletar o curso: " + e.getMessage();
        }
    }

    public Optional<Curso> findById(long id) {
        return cursoRepository.findById(id);
    }

    public String update(long id, Curso curso) {
        try {
            if (curso.getNome() == null || curso.getNome().trim().isEmpty()) {
                throw new IllegalArgumentException("O nome do curso é obrigatório.");
            }
            if (!cursoRepository.existsById(id)) {
                throw new IllegalArgumentException("Curso não encontrado.");
            }
            curso.setId(id);
            cursoRepository.save(curso);
            return "Atualização realizada com sucesso!";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "Erro ao atualizar o curso: " + e.getMessage();
        }
    }

    public List<Curso> findAll() {
        return cursoRepository.findAll();
    }
}
