package test;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

@Notified
@AuditTable(value="emp_aud")
public class Employee {
	private int id;
	
	private String firstName;
	private String lastName;
	private int salary;
	private Address address;
	
	public Employee()	{}
	
	public Employee(String fname,String lname,int sal)	{
		this.firstName=fname;
		this.lastName=lname;
		this.salary=sal;
		this.address=null;
	}
	
	public Employee(String fname,String lname,int sal,Address add)	{
		this.firstName=fname;
		this.lastName=lname;
		this.salary=sal;
		this.address=add;
	}

	@Audited
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Audited
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Audited
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Audited
	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	@Audited
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	@Override
	public String toString()	{
		return "id: "+this.id+"first name: "+this.firstName+"  last_name: "+this.lastName+" salary: "+this.salary+"\n"+this.address; 
	}
	
	
	public void eventPrePersist()	{
		System.out.println("The eventPrePersist method is called");
	}
	
	public void eventPreUpdate()	{
		System.out.println("The evenetPreUpdate method is called");
	}
}
