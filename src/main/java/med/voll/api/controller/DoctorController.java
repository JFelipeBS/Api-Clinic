package med.voll.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.doctor.DoctorDto;
import med.voll.api.dto.doctor.DoctorListDto;
import med.voll.api.dto.doctor.DoctorUpdateDto;
import med.voll.api.entities.DoctorEntity;
import med.voll.api.repository.DoctorRepository;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorRepository repository;

    @PostMapping
    @Transactional
    public void createdDoctor(@RequestBody @Valid DoctorDto data) {
        repository.save(new DoctorEntity(data));
    }

    @GetMapping
    public List<DoctorListDto> getByAll() {

        return repository.findAll().stream().map(DoctorListDto::new).toList();

    }

    @GetMapping(path = "pagination")
    public Page<DoctorListDto> getByPagination(@PageableDefault(size = 10, sort = "named") Pageable pagination) {
        return repository.findAllByActiveTrue(pagination).map(DoctorListDto::new);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid DoctorUpdateDto dto ){
        DoctorEntity doctor = repository.getReferenceById(dto.id());
        doctor.updateData(dto);

    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id){
        repository.deleteById(id);
    }

    // Inativar Medico
    @DeleteMapping("/disable/{id}")
    @Transactional
    public void disable(@PathVariable Long id){
        DoctorEntity doctor = repository.getReferenceById(id);
        doctor.disable();
    }

}
