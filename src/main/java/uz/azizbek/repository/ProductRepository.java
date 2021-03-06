package uz.azizbek.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uz.azizbek.model.Product;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByActiveTrue(Pageable pageable);

    boolean existsByNameAndCategoryIdAndPhotoIdAndMeasurementIdAndActiveTrue(String name, Long category_id, Long photo_id, Long measurement_id);

    Optional<Product> findByIdAndActiveTrue(Long id);

    @Transactional
    @Query(value = "UPDATE product SET active = false WHERE category_id = ?1", nativeQuery = true)
    void deActiveQueryNative(Long id);
}
