package project.batch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.batch.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
