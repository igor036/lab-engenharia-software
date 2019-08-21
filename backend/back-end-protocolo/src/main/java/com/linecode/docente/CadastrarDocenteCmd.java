/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 21/08/2019
 */
package com.linecode.docente;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class CadastrarDocenteCmd {
    
    @Email(message = "E-mail inválido")
    private String email;
    
    @NotBlank(message = "Informe o nome do docente")
    private String nome;

}
