package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.consultas.DatosDetalleConsulta;
import med.voll.api.domain.consultas.DatosReservaConsulta;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @PostMapping
    @Transactional
    public ResponseEntity reservar(@RequestBody @Valid DatosReservaConsulta datos) {
        System.out.println(datos);
        return ResponseEntity.ok(new DatosDetalleConsulta(null, null, null, null));
    }
}
