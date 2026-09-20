package Campuslands.Automatizacion_IA.controller;

import Campuslands.Automatizacion_IA.dto.CorreoRequest;
import Campuslands.Automatizacion_IA.dto.CorreoResponse;
import Campuslands.Automatizacion_IA.service.CorreoService;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController

@RequestMapping("/api/correos")

@RequiredArgsConstructor

@CrossOrigin(origins = {
        "http://127.0.0.1:5500",
        "http://localhost:5500"
})

public class CorreoController {

    private final CorreoService correoService;


    @PostMapping
    public ResponseEntity<CorreoResponse> guardar(

            @Valid
            @RequestBody
            CorreoRequest request

    ) {

        CorreoResponse correo =
                correoService.guardar(
                        request
                );


        return ResponseEntity

                .status(
                        HttpStatus.CREATED
                )

                .body(
                        correo
                );
    }


    @GetMapping
    public ResponseEntity<List<CorreoResponse>>
    obtenerCorreos(

            @RequestParam(
                    required = false
            )
            String periodo

    ) {

        if (periodo == null) {

            return ResponseEntity.ok(
                    correoService
                            .obtenerTodos()
            );
        }


        return ResponseEntity.ok(

                correoService
                        .obtenerPorPeriodo(
                                periodo
                        )
        );
    }
}
