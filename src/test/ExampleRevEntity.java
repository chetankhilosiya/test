package test;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

@Entity
@RevisionEntity(ExampleListener.class)
@AuditTable(value="audit_revision_info")
public class ExampleRevEntity	{
	@Id
	@GeneratedValue
	@RevisionNumber
	private int rev_id;
	
	@RevisionTimestamp
	private long rev_timestamp;
	
	private String username;
	
	public int getRev_id() {
		return rev_id;
	}

	public void setRev_id(int rev_id) {
		this.rev_id = rev_id;
	}

	public long getRev_timestamp() {
		return rev_timestamp;
	}

	public void setRev_timestamp(long rev_timestamp) {
		this.rev_timestamp = rev_timestamp;
	}	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
