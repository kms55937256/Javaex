package mylab.student.control;

import mylab.student.entity.Student;

import mylab.student.exception.InvalidGradeException;
   // �����ٶ󸶹�
public class StudentTest {
	
	public static void main(String[] args) {
        try {
            Student s1 = new Student("2023001", "��μ�", "��ǻ�Ͱ���", 3);
            System.out.println(s1.getName() + " / " + s1.getMajor() + " / " + s1.getGrade() + "�г�");

            System.out.println("5�г����� ����");
            s1.setGrade(5); 

        } catch (InvalidGradeException e) {
            System.out.println(e.getMessage());
        }
    }

}
