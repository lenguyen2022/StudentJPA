package ca.lenguyen.studentjpa.beans;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void getName() {
        Student student = new Student();
        student.setName("John");
        assertEquals("John", student.getName());
    }

    @Test
    void setName() {
        Student student = new Student();
        student.setName("Alex");
        assertEquals("Alex", student.getName());
    }

    @Test
    void setId() {
        Student student = new Student();
        student.setId(1000L);
        assertEquals(1000L, student.getId());
    }
}