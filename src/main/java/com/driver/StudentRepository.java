package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class StudentRepository {

   HashMap<String, Student> studentMap = new HashMap<>();
   HashMap<String, Teacher> teacherMap = new HashMap<>();
    HashMap<String, ArrayList<String>> pair = new HashMap<>();

    public void addStudent(Student student) {   // add a student
        studentMap.put(student.getName(), student);
    }

    public void addTeacher(Teacher teacher) {    // add a teacher
       teacherMap.put(teacher.getName(), teacher);
    }

    public Student getStudentByName(String student) {    // get student by name
      if(studentMap.containsKey(student))
          return studentMap.get(student);
      else return null;
    }

    public Teacher getTeacherByName(String teacher) {     // get teacher by name
        if(teacherMap.containsKey(teacher))
            return teacherMap.get(teacher);
        else return null;
    }

    public List<String> getAllStudents() {              // get list of all students
        List<String> ans = new ArrayList<>();

        for(String x: studentMap.keySet()) {
            ans.add(x);
        }
        return ans;
    }

    public void addStudentTeacherPair(String student, String teacher) {   // making a pair of student teacher

        ArrayList<String> before = new ArrayList<>();
        before = pair.get(teacher);
        before.add(student);

        pair.put(teacher, before);
    }

    public List<String> getStudentsByTeacherName(String teacher) {    // get list by teacher name
        List<String> ans = new ArrayList<>();

        ans = pair.get(teacher);
        return ans;
    }

    public void deleteTeacherByName(String name) {           // delete teacher and students

        List<String> sList = new ArrayList<>();

        if(pair.containsKey(name)) {
            sList = pair.get(name);

            for(String i: sList) {
                if(studentMap.containsKey(i))
                    studentMap.remove(i);
            }
            pair.remove(name);
        }

        if(teacherMap.containsKey(name))
            teacherMap.remove(name);

    }

    public void deleteAllTeachers() {
        studentMap = new HashMap<>();
        teacherMap = new HashMap<>();
        pair = new HashMap<>();
    }

}
