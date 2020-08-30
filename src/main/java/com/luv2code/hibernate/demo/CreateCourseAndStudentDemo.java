package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.entities.Course;
import com.luv2code.hibernate.entities.Instructor;
import com.luv2code.hibernate.entities.InstructorDetail;
import com.luv2code.hibernate.entities.Review;
import com.luv2code.hibernate.entities.Student;

public class CreateCourseAndStudentDemo {

	public static void main(String[] args) {

		//create the SessionFactory
		SessionFactory factory=new Configuration()
				               .configure("hibernate.cfg.xml")
				               .addAnnotatedClass(Instructor.class)
				               .addAnnotatedClass(InstructorDetail.class)
				               .addAnnotatedClass(Course.class)
				               .addAnnotatedClass(Review.class)
				               .addAnnotatedClass(Student.class)
				               .buildSessionFactory();
		//create the session 
		Session session = factory.getCurrentSession();
		
		try {
	
			//start/begin a transaction
			session.beginTransaction();
			
		    //create a course
			Course theCourse = new Course("The Piano-Ultimate piano class");
			
			//create few Students
			Student theStudent1 = new Student("John", "Doe", "john@luv2code.com");
			Student theStudent2 = new Student("Mary", "Public", "mary@luv2code.com");
			
			//add students to the course
			theCourse.addStudents(theStudent1);
			theCourse.addStudents(theStudent2);
			
			//save the course
			System.out.println("\nsaving course :"+theCourse);
			System.out.println("\nand students enroled for the course :"+theCourse.getStudents());
			session.persist(theCourse);
			
			//commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!!");
			
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			factory.close();
		}
	}

}
