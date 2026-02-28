package Main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Main.entity.Insurance;

@Repository
public interface InsuranceRepository extends JpaRepository<Insurance, Long> {

}
