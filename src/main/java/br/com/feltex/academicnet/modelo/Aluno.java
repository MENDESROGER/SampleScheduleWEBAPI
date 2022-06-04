package br.com.feltex.academicnet.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.Instant;


public class Aluno{

   
    private Long matricula;
    private String nome;
    private String telefone;
    private String email;
    private Instant dataCadastro;

}
