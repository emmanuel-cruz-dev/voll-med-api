package med.voll.api.domain.consultas.validaciones;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consultas.DatosReservaConsulta;

import java.time.DayOfWeek;

public class ValidadorFueraHorarioConsultas {

    public void validar(DatosReservaConsulta datos) {
        var fechaConsulta = datos.fecha();
        var domingo = fechaConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var horarioAntesDeAperturaClinica = fechaConsulta.getHour() < 7;
        var horarioDespuesDeCierreClinica = fechaConsulta.getHour() > 18;
        if (domingo || horarioAntesDeAperturaClinica || horarioDespuesDeCierreClinica) {
            throw new ValidacionException("Horario seleccionado fuera del horario de atención de la clínica");
        }
    }
}
