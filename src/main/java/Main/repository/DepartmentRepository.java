package Main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Main.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
