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

import com.linecode.util.dto.OpcaoDto;
import com.linecode.util.servico.UtilServico;

@RestController
@RequestMapping("util")
public class UtilControlador {

    @Autowired
    private UtilServico utilServico;
    
    @GetMapping("lista-perfil")
    public ResponseEntity<List<OpcaoDto<Long>>> getListaPerfil() {
        return ResponseEntity.ok(utilServico.getListaPerfil());
    }
    
    @GetMapping("lista-especie")
    public ResponseEntity<List<OpcaoDto<Long>>> getListaEspecie() {
        return ResponseEntity.ok(utilServico.getListaEspecie());
    }
    
    @GetMapping("lista-bioterio")
    public ResponseEntity<List<OpcaoDto<Long>>> getListaBioterio() {
        return ResponseEntity.ok(utilServico.getListaBioterio());
    }
    
    @GetMapping("lista-status")
    public ResponseEntity<List<OpcaoDto<Long>>> getListaStatusProtocolo() {
        return ResponseEntity.ok(utilServico.getListaStatusProtocolo());
    }
}   
