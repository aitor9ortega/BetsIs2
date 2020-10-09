package domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Admin extends Person implements Serializable{
	public Admin() {
		super();
	}

	public Admin(String username,String password) {
		super(username,password);
	}
}
