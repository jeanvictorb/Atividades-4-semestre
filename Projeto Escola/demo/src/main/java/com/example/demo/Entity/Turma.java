package com.example.demo.Entity;

import java.util.List;

import org.springframework.data.annotation.Id;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String Nome;
    private String semestre;
    private int ano;
    private String turno;

    @ManyToOne(cascade = CascadeType.ALL)
    private Aluno aluno;



    @OneToMany(mappedBy = "professor")
    private List<Professor> professores;

    @OneToOne
    private Curso curso;
}
