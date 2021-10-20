package uz.azizbek.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.azizbek.model.Attachment;
import uz.azizbek.model.Warehouse;
import uz.azizbek.model.Worker;

import java.util.Optional;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> {

    Optional<Worker> findByPhoneNumber(String phoneNumber);

    Page<Worker> findAllByActiveTrue(Pageable pageable);

    Optional<Worker> findByIdAndActiveTrue(Long id);

    Optional<Worker> findByPhoneNumberAndActiveFalse(String phoneNumber);
}
