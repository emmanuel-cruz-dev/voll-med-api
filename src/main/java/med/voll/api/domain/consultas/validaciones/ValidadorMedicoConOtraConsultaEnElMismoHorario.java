package med.voll.api.domain.consultas.validaciones;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consultas.ConsultaRepository;
import med.voll.api.domain.consultas.DatosReservaConsulta;

public class ValidadorMedicoConOtraConsultaEnElMismoHorario {

    private ConsultaRepository repository;

    public void validar(DatosReservaConsulta datos) {
        var medicoTieneOtraConsultaEnElMismoHorario = repository.existsByMedicoIdAndFecha(datos.idMedico(), datos.fecha());
        if(medicoTieneOtraConsultaEnElMismoHorario) {
            throw new ValidacionException("Médico ya tiene otra consulta en esa misma fecha y horario");
        }
    }
}
