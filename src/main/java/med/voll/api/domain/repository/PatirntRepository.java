package med.voll.api.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import med.voll.api.domain.entities.PatientEntity;

public interface PatirntRepository extends JpaRepository<PatientEntity,Long> {

    Page<PatientEntity> findByActiveTrue(Pageable pagination);
    
}
