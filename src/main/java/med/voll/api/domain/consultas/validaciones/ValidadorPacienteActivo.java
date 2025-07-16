package med.voll.api.domain.consultas.validaciones;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consultas.DatosReservaConsulta;
import med.voll.api.domain.paciente.PacienteRepository;

public class ValidadorPacienteActivo {

    private PacienteRepository repository;

    public void validar(DatosReservaConsulta datos) {
        var pacienteEstaActivo = repository.findActivoById(datos.idPaciente());
        if(!pacienteEstaActivo) {
            throw new ValidacionException("El paciente no se encuentra activo");
        }
    }
}
