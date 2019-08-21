package com.linecode.docente.cmd;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CadastroDocenteCmd {
    
    @NotBlank(message = "Informe o e-mail.")
    @Email(message = "E-mail inválido.")
    private String email;
    
    @NotBlank(message = "Informe o nome do docente")
    private String nome;
    
    @JsonIgnore
    private String senha;
        
    @Min(value = 1, message = "Id do perfil inválido.")
    private long idPerfil;
    
    public CadastroDocenteCmd() {
        //construtor padrão do jackson
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public long getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(long idPerfil) {
        this.idPerfil = idPerfil;
    }
}
