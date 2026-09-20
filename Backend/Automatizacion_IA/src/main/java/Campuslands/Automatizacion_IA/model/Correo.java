package Campuslands.Automatizacion_IA.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "correos")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Correo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String remitente;

    @Column(nullable = false)
    private String emailRemitente;

    @Column(nullable = false)
    private String asunto;

    @Column(columnDefinition = "TEXT")
    private String contenido;

    @Column(nullable = false)
    private LocalDateTime fecha;

    private Boolean destacado;
}
