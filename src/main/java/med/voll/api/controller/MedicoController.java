package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.util.UriComponentsBuilder;

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
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroMedico datos, UriComponentsBuilder uriComponentsBuilder){
        var medico = new Medico(datos);
        repository.save(medico);
        var uri = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return  ResponseEntity.created(uri).body(new DatosDetalleMedico(medico));
    }

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<DatosListaMedico>>> listar(@PageableDefault(size = 10, sort = {"nombre"}) Pageable paginacion){
        Page<DatosListaMedico> pagina = repository.findAllByActivoTrue(paginacion).map(DatosListaMedico::new);
        PagedModel<EntityModel<DatosListaMedico>> pagedModel = pagedResourcesAssembler.toModel(pagina, datosListaMedicoModelAssembler);

        return ResponseEntity.ok(pagedModel);
    }

    @Transactional
    @PutMapping
    public ResponseEntity actualizar(@RequestBody @Valid DatosActualizacionMedico datos){
        var medico = repository.getReferenceById(datos.id());
        medico.actualizarInformaciones(datos);

        return ResponseEntity.ok(new DatosDetalleMedico(medico));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.eliminar();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detallar(@PathVariable Long id){
        var medico = repository.getReferenceById(id);

        return ResponseEntity.ok(new DatosDetalleMedico(medico));
    }
}
