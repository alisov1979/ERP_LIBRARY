package api.exchange;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for item complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="item">
 *   &lt;complexContent>
 *     &lt;extension base="{http://exchange.mule.ptr/}complextype">
 *       &lt;sequence>
 *         &lt;element name="row" type="{http://exchange.mule.ptr/}row" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Item", propOrder = { "row" })
public class Item extends Complextype implements Serializable{

	private static final long serialVersionUID = -7083642188922174885L;
	
	@XmlElement(name = "row", nillable = true)
	protected List<Row> row;

	public void setRow(List<Row> row) {
		this.row = row;
	}

	/**
	 * Gets the value of the row property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the row property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getRow().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link Row }
	 * 
	 * 
	 */
	public List<Row> getRow() {
		if (row == null) {
			row = new ArrayList<Row>();
		}
		return this.row;
	}

	public Item(Row row) {
		super();
		if (this.row == null) {
			this.row = new ArrayList<>();
		}
		this.row.add(row);
		super.type = "request";
	}
	
	public Item(Row row, String type, String recipient) {
		super();
		if (this.row == null) {
			this.row = new ArrayList<>();
		}
		this.row.add(row);
		super.type = type;
		super.recipients = new ArrayList<>();
		super.recipients.add(recipient);
	}
	
	public Item(List<Row> rows, String type, String recipient) {
		super();

		this.row = rows;
		super.type = type;
		super.recipients = new ArrayList<>();
		super.recipients.add(recipient);
	}

	public Item(String type, Row row) {
		super();
		if (this.row == null) {
			this.row = new ArrayList<>();
		}
		this.row.add(row);
		super.type = type;

	}
	
	public Item(String type, Row row, String recipient) {
		super();
		if (this.row == null) {
			this.row = new ArrayList<>();
		}
		this.row.add(row);
		super.type = type;
		this.recipients = new ArrayList<>();
		this.recipients.add(recipient);

	}

	public void addRows(Row row) {
		if (this.row == null) {
			this.row = new ArrayList<>();
		}
		if (row != null) {
			this.row.add(row);
		}

	}

	public Item() {
	};

}
