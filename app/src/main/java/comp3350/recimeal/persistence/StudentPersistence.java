package comp3350.recimeal.persistence;

import java.util.List;

import comp3350.recimeal.objects.Student;

public interface StudentPersistence {
    List<Student> getStudentSequential();

    List<Student> getStudentRandom(final Student currentStudent);

    Student insertStudent(final Student currentStudent);

    Student updateStudent(final Student currentStudent);

    void deleteStudent(final Student currentStudent);
}
