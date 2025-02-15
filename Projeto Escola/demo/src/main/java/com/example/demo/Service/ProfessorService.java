package com.example.demo.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Professor;
import com.example.demo.Repository.ProfessorRepository;

@Service
public class ProfessorService {
    
    @Autowired
    private ProfessorRepository professorRepository;

    private static final String CPF_REGEX = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}";
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
    private static final String NAME_REGEX = "^(\\S+\\s+\\S+.*)$";

    private boolean validcpf(String cpf) {
        return Pattern.matches(CPF_REGEX, cpf);
    }

    private boolean validEmail(String email) {
        return Pattern.matches(EMAIL_REGEX, email);
    }
    
    private boolean validName(String name) {
        return Pattern.matches(NAME_REGEX, name);
    }

    private boolean validEspecialidade(String especialidade) {
        return especialidade != null && especialidade.trim().split("\\s+").length >= 2;
    }

    public String save(Professor professor) {
        if (!validName(professor.getNome())) {
            return "Nome inválido. Deve conter pelo menos duas palavras.";
        }
        if (!validcpf(professor.getCpf())) {
            return "CPF inválido.";
        }
        if (!validEmail(professor.getEmail())) {
            return "Email inválido.";
        }
        if (professor.getEspecialidade() != null && !professor.getEspecialidade().trim().isEmpty() && !validEspecialidade(professor.getEspecialidade())) {
            return "Especialidade inválida. Deve conter pelo menos duas palavras.";
        }
        professorRepository.save(professor);
        return "Professor salvo com sucesso!";
    }

    public String delete(long id) {
        if (professorRepository.existsById(id)) {
            professorRepository.deleteById(id);
            return "Professor deletado com sucesso!";
        } else {
            return "Professor não encontrado.";
        }
    }

    public Optional<Professor> findById(long id) {
        return professorRepository.findById(id);
    }

    public String update(long id, Professor professor) {
        if (!validName(professor.getNome())) {
            return "Nome inválido. Deve conter pelo menos duas palavras.";
        }
        if (!validcpf(professor.getCpf())) {
            return "CPF inválido.";
        }
        if (!validEmail(professor.getEmail())) {
            return "Email inválido.";
        }
        if (professor.getEspecialidade() != null && !professor.getEspecialidade().trim().isEmpty() && !validEspecialidade(professor.getEspecialidade())) {
            return "Especialidade inválida. Deve conter pelo menos duas palavras.";
        }
        if (professorRepository.existsById(id)) {
            professor.setId(id);
            professorRepository.save(professor);
            return "Atualização realizada com sucesso!";
        } else {
            return "Professor não encontrado.";
        }
    }

    public List<Professor> findAll() {
        return professorRepository.findAll();
    }
}
