package api.exchange;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for listOfCatalog complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="listOfCatalog">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="catalog" type="{product.specification.stdp.ru}Catalog" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfObjects", propOrder = { "object", "baseId"})
@XmlRootElement(name = "ArrayOfObjects", namespace = "http://exchange.mule.ptr/")

public class ArrayOfObjects implements iExchangable, Serializable {

	private static final long serialVersionUID = 6366286241776802258L;
	
	@XmlElement(name = "object", required = true)
	protected List<Object> object;
	@XmlAttribute(name="baseId")
	protected String baseId;
	


	/**
	 * Gets the value of the catalog property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the catalog property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getCatalog().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link Object }
	 * 
	 * 
	 */
	@Override
	public String getSender() {

		String result = "";

		if (this.object != null && this.object.isEmpty() == false) {
			Object fObject = this.object.get(0);
			if (fObject != null) {
				result = fObject.getSender();
			}
		}

		return result;

	}

	public String getType() {

		String result = "";

		if (this.object != null && this.object.isEmpty() == false) {
			Object fObject = this.object.get(0);
			if (fObject != null) {
				result = fObject.getType();
			}
		}

		return result;

	}

	public ArrayOfObjects(List<Object> object) {
		super();
		this.object = object;
	}

	public ArrayOfObjects() {
	};

	public ArrayOfObjects(Object value) {

		if (this.object == null) {
			this.object = new ArrayList<>();
		}

		this.object.add(value);
	}
	
	

	public String getBaseId() {
		return baseId;
	}

	@Override
	public Object getObject() {

		if (this.object != null && this.object.isEmpty() == false) {

			return this.object.get(0);

		}

		return null;
	}

	@Override
	public List<String> getRecipients() {
		if (this.getObject() != null) {
			return this.getObject().getRecipients();
		} else
			return new ArrayList<String>();
	}

	
	public ArrayOfObjects(Property property, String recipient, String method, String sender) {

		List<Property> properties = new ArrayList<>();
		properties.add(property);
		this.object = new ArrayList<>();

		this.object.add(new Object(new Item(new Row(properties)), sender, method, recipient));

	}
	

}
