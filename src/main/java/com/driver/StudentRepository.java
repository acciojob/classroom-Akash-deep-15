package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Repository
public class StudentRepository {

   HashMap<String, Student> studentMap = new HashMap<>();
   HashMap<String, Teacher> teacherMap = new HashMap<>();
    HashMap<String, List<String>> pair = new HashMap<>();

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


        if(studentMap.containsKey(student) && teacherMap.containsKey(teacher)){
            studentMap.put(student, studentMap.get(student));
            teacherMap.put(teacher, teacherMap.get(teacher));
            List<String> current = new ArrayList<String>();
            if(pair.containsKey(teacher))
             current = pair.get(teacher);
            current.add(student);
            pair.put(teacher, current);
        }
    }

    public List<String> getStudentsByTeacherName(String teacher) {    // get list by teacher name
        List<String> ans = new ArrayList<>();

        if(pair.containsKey(teacher))
        ans = pair.get(teacher);
        return ans;
    }

    public void deleteTeacherByName(String name) {           // delete teacher and students

        List<String> movies = new ArrayList<String>();
        if(pair.containsKey(name)){
            movies = pair.get(name);
            for(String movie: movies){
                if(studentMap.containsKey(movie)){
                    studentMap.remove(movie);
                }
            }

            pair.remove(name);
        }

        if(teacherMap.containsKey(name)){
            teacherMap.remove(name);
        }
    }

    public void deleteAllTeachers() {
        HashSet<String> set = new HashSet<String>();

        //directorMap = new HashMap<>();

        for(String director: pair.keySet()){
            for(String movie: pair.get(director)){
                set.add(movie);
            }
        }

        for(String movie: set){
            if(studentMap.containsKey(movie)){
                studentMap.remove(movie);
            }
        }
    }

}
