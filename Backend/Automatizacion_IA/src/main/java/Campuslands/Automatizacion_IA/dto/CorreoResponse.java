package Campuslands.Automatizacion_IA.dto;

import java.time.LocalDateTime;

public record CorreoResponse(

        Long id,
        String remitente,
        String emailRemitente,
        String asunto,
        String contenido,
        LocalDateTime fecha,
        Boolean destacado

) {
}
