package ca.lenguyen.studentjpa.database;

import ca.lenguyen.studentjpa.beans.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Long> {


    List<Student> readStudentByNameStartingWith(String letter);

    List<Student> findStudentByIdAndName(Long id, String name);

    @Query("SELECT s FROM STUDENT s WHERE s.name like 'B%'")
    List<Student> findStudentByName();

}
