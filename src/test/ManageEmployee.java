package test;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.BootstrapServiceRegistryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class ManageEmployee {
   private static SessionFactory factory; 
   public static void main(String[] args) {
      try{
		Configuration config=new Configuration().configure();
		/*MyIntegrator myintegrator=new MyIntegrator();
		BootstrapServiceRegistryBuilder bootstrapBuilder=new BootstrapServiceRegistryBuilder().with(myintegrator);*/
		BootstrapServiceRegistryBuilder bootstrapBuilder=new BootstrapServiceRegistryBuilder();
		StandardServiceRegistryBuilder standardBuilder=new StandardServiceRegistryBuilder(bootstrapBuilder.build());
		ServiceRegistry serviceRegistry=standardBuilder.applySettings(config.getProperties()).build();
		factory=config.buildSessionFactory(serviceRegistry);
      }catch (Throwable ex) { 
         System.err.println("Failed to create sessionFactory object." + ex);
         throw new ExceptionInInitializerError(ex); 
      }
      DemoObserver demoObserver=new DemoObserver();
      NotificationManager manager=NotificationManager.getInstance();
      manager.registerObserver(demoObserver);
      ManageEmployee ME = new ManageEmployee();
      Address addr1=new Address("jm road", "pune", "maharashtra");
      Integer emp1=ME.addEmployee("john", "travolta", 50000, addr1);
      ME.updateEmployee(emp1, 4000);
      
      factory.close();
   }
   /* Method to CREATE an employee in the database */
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
   
   public Integer addAddress(Address addr)	{
	   Session session=factory.openSession();
	   Transaction tx=null;
	   Integer id=null;
	   try	{
		   tx=session.beginTransaction();
		   id=(Integer)session.save(addr);
		   tx.commit();
	   }
	   catch(HibernateException ex)	{
		   ex.printStackTrace();
	   }
	   finally	{
		   session.close();
	   }
	   return id;
   }
   
   public void updateAddress(Integer id,String city)	{
	   Session session = factory.openSession();
	   Transaction tx = null;
	   try	{
		   tx=session.beginTransaction();
		   Address address=(Address)session.get(Address.class,id);
		   address.setCity(city);
		   session.update(address);
		   tx.commit();
	   }
	   catch(HibernateException ex)	{
		   ex.printStackTrace();
	   }
	   finally{
		   session.close();
	   }
   }
}