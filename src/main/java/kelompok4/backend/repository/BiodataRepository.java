package kelompok4.backend.repository;

import kelompok4.backend.entity.Biodata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BiodataRepository extends JpaRepository<Biodata, Long> {
    Biodata findByUserName(String name); // method ini yang kamu butuhkan
}
