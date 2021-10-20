package uz.azizbek.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.azizbek.model.Attachment;
import uz.azizbek.model.Input;

@Repository
public interface InputRepository extends JpaRepository<Input, Long> {
}
