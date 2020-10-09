package domain;
 
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
 
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlIDREF;
@XmlAccessorType(XmlAccessType.FIELD) 
@Entity
public class User extends Person implements Serializable {
	private String name;
    private float money;
    @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
    private Collection<Bet> bets;
    @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
    private Collection<Movement> movements;
    @XmlIDREF
    @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
    private Collection<ReplicateUser> replicatingUsers;                   
    @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
    private Collection<ReplicateUser> replicators; //
    
    public User (String username,String password,String name) {
    	super(username,password);
    	this.name=name;
   	 	this.money=100;
   	 	this.bets=new HashSet<Bet>();
   	 	this.movements = new HashSet<Movement>();
   	 	this.replicatingUsers = new HashSet<ReplicateUser>();
   	 	this.replicators  = new HashSet<ReplicateUser>();
    }
    public User () {
    	super();
    	this.bets=new HashSet<Bet>();
   	 	this.movements = new HashSet<Movement>();
   	 	this.replicatingUsers = new HashSet<ReplicateUser>();
   	 	this.replicators  = new HashSet<ReplicateUser>();
    	
    }
    
    public Collection<ReplicateUser> getReplicatingUsers() {
		return replicatingUsers;
	}

	public void setReplicatingUsers(Collection<ReplicateUser> profetak) {
		this.replicatingUsers = profetak;
	}

	public Collection<ReplicateUser> getReplicators() {
		return replicators;
	}

	public void setJarraitzaileak(Collection<ReplicateUser> jarraitzaileak) {
		this.replicators = jarraitzaileak;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getMoney() {
   	 return money;
    }
    public void setMoney(float money) {
   	 this.money = money;
    }
    public Collection<Bet> getBets() {
   	 return bets;
    }
    public void setBets(Collection<Bet> bets) {
   	 this.bets = bets;
    }
    public Bet addBet(Collection<Odd> oddIds,float fee,float amount,String s) {
    	Bet b=new Bet(oddIds,this,fee,amount,s);
    	this.bets.add(b);
    	return b;
    }
	public Collection<Movement> getMovements() {
		return movements;
	}
	public void setMovements(Collection<Movement> movements) {
		this.movements = movements;
	}
    public MovementBet addMovementBet(float amount,Date date,String s,Bet b) {
    	MovementBet move= new MovementBet(amount,date,s,this,b);
    	this.movements.add(move);
    	return move;
    }
    public Movement addMovement(float amount,Date date,String s) {
    	Movement move= new Movement(amount,date,s,this);
    	this.movements.add(move);
    	return move;
    }
    public ReplicateUser addReplicator(User jarraitzaile,Double amount) {
    	boolean badago=false;
    	ReplicateUser ru=null;
    	for(ReplicateUser reus:this.replicators) {
    		if(reus.getReplicator().equals(jarraitzaile)) {
    			badago=true;
    			ru=reus;
    			break;
    		}
    	}
    	if(badago) {
    		ru.setAmount(ru.getAmount()+amount);
    		return null;
    	}
    	ru=new ReplicateUser(jarraitzaile,this,amount);
    	this.replicators.add(ru);
    	return ru;
    }
    public void removeReplicator(ReplicateUser ru) {
    	this.replicators.remove(ru);
    }
    public void addReplicatingUser(ReplicateUser ru) {
    	this.replicatingUsers.add(ru);
    }
    public void removeReplicatingUser(ReplicateUser ru) {
    	this.replicatingUsers.remove(ru);
    }
    public boolean equals(User u) {
    	return this.getUsername().equals(u.getUsername());
    }
    
}
