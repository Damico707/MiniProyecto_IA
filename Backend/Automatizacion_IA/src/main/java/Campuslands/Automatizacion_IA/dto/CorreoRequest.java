package Campuslands.Automatizacion_IA.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CorreoRequest(

        @NotBlank(message = "El remitente es obligatorio")
        String remitente,

        @NotBlank(message = "El correo del remitente es obligatorio")
        @Email(message = "El correo no tiene un formato válido")
        String emailRemitente,

        @NotBlank(message = "El asunto es obligatorio")
        String asunto,

        String contenido,

        @NotNull(message = "La fecha es obligatoria")
        LocalDateTime fecha,

        Boolean destacado

) {
}
