package domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class ReplicateUser implements Serializable {
	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer replicateId;
	private User replicator;
	@XmlIDREF
	private User replicated;
	private Double amount;
	
	public ReplicateUser() {
		
	}
	public ReplicateUser(User replicator, User replicated, Double amount) {
		super();
		this.replicator = replicator;
		this.replicated = replicated;
		this.amount = amount;
	}
	public User getReplicator() {
		return replicator;
	}
	public void setReplicator(User replicator) {
		this.replicator = replicator;
	}
	public User getReplicated() {
		return replicated;
	}
	public void setReplicated(User replicated) {
		this.replicated = replicated;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
}
