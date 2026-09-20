package Campuslands.Automatizacion_IA.service;


import Campuslands.Automatizacion_IA.dto.CorreoRequest;
import Campuslands.Automatizacion_IA.dto.CorreoResponse;
import Campuslands.Automatizacion_IA.model.Correo;
import Campuslands.Automatizacion_IA.repository.CorreoRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor

public class CorreoServiceImpl
        implements CorreoService {

    private final CorreoRepository correoRepository;


    @Override
    public CorreoResponse guardar(
            CorreoRequest request
    ) {

        Correo correo = Correo.builder()

                .remitente(
                        request.remitente()
                )

                .emailRemitente(
                        request.emailRemitente()
                )

                .asunto(
                        request.asunto()
                )

                .contenido(
                        request.contenido()
                )

                .fecha(
                        request.fecha()
                )

                .destacado(
                        request.destacado() != null
                                ? request.destacado()
                                : false
                )

                .build();


        Correo correoGuardado =
                correoRepository.save(correo);


        return convertirAResponse(
                correoGuardado
        );
    }


    @Override
    public List<CorreoResponse> obtenerTodos() {

        return correoRepository
                .findAll()
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }


    @Override
    public List<CorreoResponse> obtenerPorPeriodo(
            String periodo
    ) {

        LocalDateTime fechaInicio;


        switch (periodo.toLowerCase()) {

            case "hoy" ->

                    fechaInicio =
                            LocalDateTime
                                    .now()
                                    .toLocalDate()
                                    .atStartOfDay();


            case "semana" ->

                    fechaInicio =
                            LocalDateTime
                                    .now()
                                    .minusDays(7);


            case "mes" ->

                    fechaInicio =
                            LocalDateTime
                                    .now()
                                    .minusMonths(1);


            default ->

                    throw new IllegalArgumentException(
                            "Periodo no válido"
                    );

        }


        return correoRepository
                .findByFechaAfterOrderByFechaDesc(
                        fechaInicio
                )
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }


    private CorreoResponse convertirAResponse(
            Correo correo
    ) {

        return new CorreoResponse(

                correo.getId(),

                correo.getRemitente(),

                correo.getEmailRemitente(),

                correo.getAsunto(),

                correo.getContenido(),

                correo.getFecha(),

                correo.getDestacado()

        );
    }
}