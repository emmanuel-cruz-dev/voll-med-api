package med.voll.api.domain.consultas.validaciones;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consultas.DatosReservaConsulta;

import java.time.Duration;
import java.time.LocalDateTime;

public class ValidacionConsultaConAnticipacion {

    public void validar(DatosReservaConsulta datos) {
        var fechaConsulta = datos.fecha();
        var ahora = LocalDateTime.now();
        var diferenciaEnMinutos = Duration.between(ahora, fechaConsulta).toMinutes();
        if(diferenciaEnMinutos < 30) {
            throw new ValidacionException("La consulta no puede ser agendada con menos de 30 minutos de anticipación");
        }
    }
}
