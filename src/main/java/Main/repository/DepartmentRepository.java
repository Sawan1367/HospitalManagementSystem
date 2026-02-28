package Main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Main.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
