package med.voll.api.medico;

import lombok.NonNull;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class DatosListaMedicoModelAssembler implements RepresentationModelAssembler<DatosListaMedico, EntityModel<DatosListaMedico>> {

    @Override
    @NonNull
    public EntityModel<DatosListaMedico> toModel(@NonNull DatosListaMedico datosListaMedico) {
        return EntityModel.of(datosListaMedico);
    }
}
