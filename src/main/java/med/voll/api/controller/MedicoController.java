package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @Autowired
    private PagedResourcesAssembler<DatosListaMedico> pagedResourcesAssembler;

    @Autowired
    private DatosListaMedicoModelAssembler datosListaMedicoModelAssembler;

    @Transactional
    @PostMapping
    public void registrar(@RequestBody @Valid DatosRegistroMedico datos){
        repository.save(new Medico(datos));
    }

    @GetMapping
    public PagedModel<EntityModel<DatosListaMedico>> listar(@PageableDefault(size = 10, sort = {"nombre"}) Pageable paginacion){
        Page<DatosListaMedico> pagina = repository.findAll(paginacion).map(DatosListaMedico::new);
        return pagedResourcesAssembler.toModel(pagina, datosListaMedicoModelAssembler);
    }

    @Transactional
    @PutMapping
    public void actualizar(@RequestBody @Valid DatosActualizacionMedico datos){
        var medico = repository.getReferenceById(datos.id());
        medico.actualizarInformaciones(datos);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id){
        repository.deleteById(id);
    }
}
