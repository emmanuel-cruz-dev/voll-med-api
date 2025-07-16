package med.voll.api.domain.consultas.validaciones;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consultas.DatosReservaConsulta;
import med.voll.api.domain.medico.MedicoRepository;

public class ValidadorMedicoActivo {

    private MedicoRepository repository;

    public void validar(DatosReservaConsulta datos) {
        if(datos.idMedico() == null) {
            return;
        }
        var medicoEstaActivo = repository.findActivoById(datos.idMedico());
        if(!medicoEstaActivo) {
            throw new ValidacionException("Consulta no puede ser reservada con médico no activo");
        }
    }
}
