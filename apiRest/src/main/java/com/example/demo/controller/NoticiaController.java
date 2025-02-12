package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.NoticiaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/noticias")
public class NoticiaController {

    @Autowired
    private NoticiaService noticiaService;
    @Operation(summary = "Consulta noticias por texto",description = "Realiza una búsqueda de noticias a partir del texto proporcionado como parámetro.")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Noticias encontradas"),
            @ApiResponse(responseCode = "400", description = "Parámetros inválidos"),
            @ApiResponse(responseCode = "404", description = "No se encuentran noticias para el texto dado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        })
    @GetMapping("/consulta")
    public ResponseEntity<?> consulta(@RequestParam(name = "q", required = false)
                                    @Parameter(description = "Texto para buscar noticias") String q) {
      
        if (q == null || q.trim().isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("{\"codigo\": \"g268\", \"error\": \"Parámetros inválidos\"}");
        }

        try {
            List<String> noticias = noticiaService.buscarNoticias(q);

           
            if (noticias.isEmpty()) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("{\"codigo\": \"g267\", \"error\": \"No se encuentran noticias para el texto: " + q + "\"}");
            }

            return ResponseEntity.ok(noticias);

        } catch (IOException e) {
           
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"codigo\": \"g100\", \"error\": \"Error interno del servidor\"}");
        }
    }
}
