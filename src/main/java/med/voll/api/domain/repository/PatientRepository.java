package med.voll.api.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import med.voll.api.domain.entities.PatientEntity;

public interface PatientRepository extends JpaRepository<PatientEntity, Long> {

    Page<PatientEntity> findByActiveTrue(Pageable pagination);

    @Query("""
            select p.active
            from PatientEntity p
            where
            p.id = :id
                """)
    Boolean findActiveById(Long id);

}
