package uz.azizbek.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.azizbek.model.Client;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Page<Client> findAllByActiveTrue(Pageable pageable);

    Optional<Client> findByPhone(String phone);

    Optional<Client> findByIdAndActiveTrue(Long id);

    Optional<Client> findByPhoneAndActiveFalse(String phone);
}
