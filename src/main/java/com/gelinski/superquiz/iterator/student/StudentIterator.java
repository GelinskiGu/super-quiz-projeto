package com.gelinski.superquiz.iterator.student;

import com.gelinski.superquiz.iterator.Iterator;
import com.gelinski.superquiz.model.Student;

import java.util.List;

public class StudentIterator implements Iterator<Student> {
    private final List<Student> students;
    private int position;

    public StudentIterator(List<Student> students) {
        this.students = students;
        this.position = 0;
    }

    @Override
    public boolean hasNext() {
        return position < students.size();
    }

    @Override
    public Student next() {
        return students.get(position++);
    }
}
