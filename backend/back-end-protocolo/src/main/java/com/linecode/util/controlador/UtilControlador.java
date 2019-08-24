/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 22/08/2019
 */
package com.linecode.util.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.linecode.util.dto.PerfilDto;
import com.linecode.util.servico.UtilServico;

@RestController
@RequestMapping("util")
public class UtilControlador {

    @Autowired
    private UtilServico utilServico;
    
    @GetMapping("lista-perfil")
    public ResponseEntity<List<PerfilDto>> getListaPerfil() {
        return ResponseEntity.ok(utilServico.getListaPerfil());
    }
}   