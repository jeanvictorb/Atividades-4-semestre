package com.example.demo.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Aluno;
import com.example.demo.Repository.AlunoRepository;

@Service
public class AlunoService {
    
    @Autowired
    private AlunoRepository alunoRepository;

    private static final String NAME_REGEX = "^(\\S+\\s+\\S+.*)$";

    private boolean validName(String name) {
        return Pattern.matches(NAME_REGEX, name);
    }

    public String save(Aluno aluno) {
        if (!validName(aluno.getNome())) {
            return "Nome inválido. Deve conter pelo menos duas palavras.";
        }
        alunoRepository.save(aluno);
        return "Aluno salvo com sucesso!";
    }

    public String delete(long id) {
        if (alunoRepository.existsById(id)) {
            alunoRepository.deleteById(id);
            return "Aluno deletado com sucesso!";
        } else {
            return "Aluno não encontrado.";
        }
    }

    public Optional<Aluno> findById(long id) {
        return alunoRepository.findById(id);
    }

    public String update(long id, Aluno aluno) {
        if (!validName(aluno.getNome())) {
            return "Nome inválido. Deve conter pelo menos duas palavras.";
        }
        if (alunoRepository.existsById(id)) {
            aluno.setId(id);
            alunoRepository.save(aluno);
            return "Atualização realizada com sucesso!";
        } else {
            return "Aluno não encontrado.";
        }
    }

    public List<Aluno> findAll() {
        return alunoRepository.findAll();
    }
}
