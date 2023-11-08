package med.voll.api.domain.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import med.voll.api.domain.entities.ScheduleEntity;

public interface ScheduleRepository extends JpaRepository<ScheduleEntity,Long> {

    Boolean existsByDoctorIdAndDate(Long doctor,LocalDateTime date);

    Boolean existsByPatientIdAndDateBetween(Long patient, LocalDateTime firstTime, LocalDateTime lasttTime);
    
    
}
