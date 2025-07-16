package med.voll.api.domain.consultas.validaciones;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consultas.DatosReservaConsulta;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteActivo implements ValidadorDeConsultas {

    @Autowired
    private PacienteRepository repository;

    public void validar(DatosReservaConsulta datos) {
        var pacienteEstaActivo = repository.findActivoById(datos.idPaciente());
        if(!pacienteEstaActivo) {
            throw new ValidacionException("El paciente no se encuentra activo");
        }
    }
}
