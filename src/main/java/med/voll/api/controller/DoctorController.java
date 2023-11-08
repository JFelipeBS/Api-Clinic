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
import med.voll.api.domain.dto.doctor.DoctorDetailDto;
import med.voll.api.domain.dto.doctor.DoctorDto;
import med.voll.api.domain.dto.doctor.DoctorListDto;
import med.voll.api.domain.dto.doctor.DoctorUpdateDto;
import med.voll.api.domain.entities.DoctorEntity;
import med.voll.api.domain.repository.DoctorRepository;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DoctorDetailDto> createdDoctor(@RequestBody @Valid DoctorDto data,
            UriComponentsBuilder uriBuilder) {

        DoctorEntity doctorEntity = new DoctorEntity(data);
        repository.save(doctorEntity);
        var uri = uriBuilder.path("doctor/{id}").buildAndExpand(doctorEntity.getId()).toUri();
        return ResponseEntity.created(uri).body(new DoctorDetailDto(doctorEntity));
    }

    @GetMapping
    public ResponseEntity<List<DoctorListDto>> getByAll() {
        var list = repository.findAll().stream().map(DoctorListDto::new).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping(path = "pagination")
    public ResponseEntity<Page<DoctorListDto>> getByPagination(
            @PageableDefault(size = 10, sort = "named") Pageable pagination) {
        var page = repository.findAllByActiveTrue(pagination).map(DoctorListDto::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DoctorDetailDto> update(@RequestBody @Valid DoctorUpdateDto dto) {
        DoctorEntity doctor = repository.getReferenceById(dto.id());
        doctor.updateData(dto);
        return ResponseEntity.ok(new DoctorDetailDto(doctor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<DoctorDetailDto> delete(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Inativar Medico
    @DeleteMapping("/disable/{id}")
    @Transactional
    public ResponseEntity<DoctorDetailDto> disable(@PathVariable Long id) {
        DoctorEntity doctor = repository.getReferenceById(id);
        doctor.disable();
        return ResponseEntity.noContent().build();
    }

}
