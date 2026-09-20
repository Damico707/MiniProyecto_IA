package Campuslands.Automatizacion_IA.service;


import Campuslands.Automatizacion_IA.dto.CorreoRequest;
import Campuslands.Automatizacion_IA.dto.CorreoResponse;

import java.util.List;

public interface CorreoService {

    CorreoResponse guardar(
            CorreoRequest request
    );

    List<CorreoResponse> obtenerTodos();

    List<CorreoResponse> obtenerPorPeriodo(
            String periodo
    );
}