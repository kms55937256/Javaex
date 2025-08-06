package mylab.student.control;

import mylab.student.entity.Student;

import mylab.student.exception.InvalidGradeException;
   // 가나다라마바
public class StudentTest {
	
	public static void main(String[] args) {
        try {
            Student s1 = new Student("2023001", "김민수", "컴퓨터공학", 3);
            System.out.println(s1.getName() + " / " + s1.getMajor() + " / " + s1.getGrade() + "학년");

            System.out.println("5학년으로 변경");
            s1.setGrade(5); 

        } catch (InvalidGradeException e) {
            System.out.println(e.getMessage());
        }
    }

}
