package med.voll.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.dto.patient.PatientDetailDto;
import med.voll.api.domain.dto.patient.PatientDto;
import med.voll.api.domain.dto.patient.PatientListDto;
import med.voll.api.domain.dto.patient.PatientUpdateDto;
import med.voll.api.domain.entities.PatientEntity;
import med.voll.api.domain.repository.PatirntRepository;

@RestController
@RequestMapping("/patient")
public class PatirntController {

    @Autowired
    private PatirntRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<PatientDetailDto> creatPatirnt(@RequestBody @Valid PatientDto data, UriComponentsBuilder uriBuilder) {
        PatientEntity patient = new PatientEntity(data);
        repository.save(patient);
        var uri = uriBuilder.path("/patient/{id}").buildAndExpand(patient.getId()).toUri();
         
        return ResponseEntity.created(uri).body(new PatientDetailDto(patient));
    }

    @GetMapping
    public ResponseEntity <List<PatientListDto>> getByAll() {
        var listPatient = repository.findAll().stream().map(PatientListDto::new).toList();
        return ResponseEntity.ok(listPatient);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<PatientDetailDto> getById(@PathVariable Long id) {
        PatientEntity patient = repository.getReferenceById(id);
        return ResponseEntity.ok(new PatientDetailDto(patient));
    }

    @GetMapping(path = "/pagination")
    public ResponseEntity <Page<PatientListDto>> getByPagination(@PageableDefault(size = 10, sort = "named") Pageable pagination) {
        var page = repository.findByActiveTrue(pagination).map(PatientListDto::new);
        return ResponseEntity.ok(page);
    }

    

    @PutMapping
    @Transactional
    public ResponseEntity<PatientDetailDto> update(@RequestBody @Valid PatientUpdateDto dto){
        PatientEntity patient = repository.getReferenceById(dto.id());
        patient.update(dto);
        return ResponseEntity.ok(new PatientDetailDto(patient));

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("disable/{id}")
    @Transactional
    public ResponseEntity disble(@PathVariable Long id){
        PatientEntity patient = repository.getReferenceById(id);
        patient.disable();
        return ResponseEntity.noContent().build();

    }

}
