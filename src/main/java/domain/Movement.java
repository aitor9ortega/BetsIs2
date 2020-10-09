package domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Movement implements Comparable<Movement>, Serializable {
	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer moveId;
	public float amount;
	public Date date;
	public String type;
	@OneToOne
	@XmlIDREF
	private User user;

	public Movement() {

	}

	/**
	 * This method creates a movement indicating the amount of money, the date and
	 * the type.
	 * 
	 * @param amount The amount of money of the movement
	 * @param d      The date
	 * @param type   If the user has won or lost.
	 */
	public Movement(float amount, Date d, String type, User us) {
		this.amount = amount;
		this.date = d;
		this.type = type;
		this.user = us;
	}

	/**
	 *
	 * @return
	 */
	public float getAmount() {
		return amount;
	}

	/**
	 *
	 * @param amount
	 */

	public void setAmount(float amount) {
		this.amount = amount;
	}

	/**
	 *
	 * @return
	 */

	public Date getDate() {
		return date;
	}

	/**
	 *
	 * @param date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 *
	 * @return
	 */
	public String getType() {
		return type;
	}

	/**
	 *
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 *
	 * @return
	 */
	@Override
	public String toString() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return "Mota: " + type + ", " + "Dirua: " + amount + ", " + simpleDateFormat.format(date);
	}


	public int compareTo(Movement o) {
		return getDate().compareTo(o.getDate());
	}

}
