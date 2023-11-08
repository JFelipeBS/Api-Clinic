package med.voll.api.domain.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import med.voll.api.domain.entities.DoctorEntity;
import med.voll.api.util.Specialty;

public interface DoctorRepository extends JpaRepository<DoctorEntity, Long> {

    Page<DoctorEntity> findAllByActiveTrue(Pageable pagination);

    @Query("""
            select d from DoctorEntity d
            where
            d.active = true
            and
            d.specialty = :specialty
            and
            d.id not in(
                select s.doctor.id from ScheduleEntity s
                where
                s.date = :date
                and
                s.reasonCancel is null
            )
            order by rand()
            limit 1
            """)
    DoctorEntity chooseDoctorRandomly(Specialty specialty, LocalDateTime date);

    @Query(
    """
        select d.active
        from DoctorEntity d
        where
        d.id = :id
            """
    )
    Boolean findActiveById(Long id);


}
