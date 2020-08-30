package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.entities.Course;
import com.luv2code.hibernate.entities.Instructor;
import com.luv2code.hibernate.entities.InstructorDetail;
import com.luv2code.hibernate.entities.Review;
import com.luv2code.hibernate.entities.Student;

public class AddCoursesForMaryDemo {

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
			
			//get the Mary from DB
			int theId=4;
			Student theStudent = session.get(Student.class, theId);
			
			//create more courses
			Course theCourse1 = new Course("Drum-Beat the beat out of it!!");
			Course theCourse2 = new Course("Songs-Can touch the soul!!");
			
			//add Mary to the courses
			theCourse1.addStudents(theStudent);
			theCourse2.addStudents(theStudent);
			
			//save the courses
			//session.save(theCourse1);
			session.save(theCourse2);
			
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
