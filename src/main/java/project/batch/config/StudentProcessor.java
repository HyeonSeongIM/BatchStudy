package project.batch.config;

import org.springframework.batch.item.ItemProcessor;
import project.batch.entity.Student;

public class StudentProcessor implements ItemProcessor<Student, Student> {

    @Override
    public Student process(Student student) throws Exception {
        //TODO : all the business logic goes here
        return student;
    }
}
