package med.voll.api.domain.consultas;

import java.time.LocalDateTime;

public record DatosDetalleConsulta(Long id, Long idMedico, Long idPaciente, LocalDateTime fecha) {
}
