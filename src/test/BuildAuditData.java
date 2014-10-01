package test;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class BuildAuditData {
	private static SessionFactory factory;
	public static void main(String args[])	{
		Configuration configuration = new Configuration().configure();
		ServiceRegistry serviceReg=new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		factory=configuration.buildSessionFactory(serviceReg);
		
		BuildAuditData data=new BuildAuditData();
		Integer emp;
		
		Address add=new Address("sb road","pune","MH");
	    data.addEmployee("Zara", "Ali", 1000,add);
	    emp=data.addEmployee("Daisy", "Das", 5000,add);
	    data.updateEmployee(emp, 23000);
	    emp=data.addEmployee("anna", "kareneina", 50000, add);
	    data.updateEmployee(emp, 15000);
	    
	    
	    add=new Address("jm road","pune","MH");
	    data.addEmployee("will", "smith", 10000, add);
	    emp=data.addEmployee("jason", "stathon", 35000, add);
	    data.updateEmployee(emp, 25000);
	    data.addEmployee("jennifer", "lopez", 45000, add);
	    
	    add=new Address("main road","mumbai","MH");
	    data.addEmployee("John", "Paul", 10000,add);
	    data.addEmployee("bruce", "willis", 28000, add);
	    emp=data.addEmployee("katy", "perry", 15000, add);
	    data.updateEmployee(emp, 70000);
	    
	    factory.close();
	}
	
	public Integer addEmployee(String fname, String lname, int salary,Address add){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      Integer employeeID = null;
	      try{
	         tx = session.beginTransaction();
	         session.save(add);
	         Employee employee = new Employee(fname, lname, salary,add);
	         employeeID = (Integer) session.save(employee); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	      return employeeID;
	   }
	   /* Method to  READ all the employees */
	   public void listEmployees( ){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         List<?> employees = session.createQuery("FROM Employee").list(); 
	         for (Iterator<?> iterator = 
	                           employees.iterator(); iterator.hasNext();){
	            Employee employee = (Employee) iterator.next(); 
	            System.out.print("First Name: " + employee.getFirstName()); 
	            System.out.print("  Last Name: " + employee.getLastName()); 
	            System.out.println("  Salary: " + employee.getSalary());
	            System.out.println(" Address: "+employee.getAddress());
	         }
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	   }
	   /* Method to UPDATE salary for an employee */
	   public void updateEmployee(Integer EmployeeID, int salary ){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         Employee employee = 
	                    (Employee)session.get(Employee.class, EmployeeID); 
	         employee.setSalary( salary );
			 session.update(employee); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	   }
	   /* Method to DELETE an employee from the records */
	   public void deleteEmployee(Integer EmployeeID){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         Employee employee = 
	                   (Employee)session.get(Employee.class, EmployeeID); 
	         session.delete(employee); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	   }
}
