package test;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

@AuditTable(value="addr_aud")
public class Address {
	private int id;
	
	private String street;
	private String city;
	private String state;

	public Address()	{}
	
	public Address(String street,String city,String state)	{
		this.street=street;
		this.city=city;
		this.state=state;
	}
	
	@Audited
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Audited
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	@Audited
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Audited
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Address [street=" + street + ", city=" + city + ", state="
				+ state + "]";
	}
	
}
