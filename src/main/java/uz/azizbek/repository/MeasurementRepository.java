package uz.azizbek.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.azizbek.model.Attachment;
import uz.azizbek.model.Measurement;

import java.util.Optional;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Long> {
    Page<Measurement> findAllByActiveTrue(Pageable pageable);

    Optional<Measurement> findByName(String name);

    Optional<Measurement> findByIdAndActiveTrue(Long id);

    Optional<Measurement> findByNameAndActiveFalse(String name);
}
