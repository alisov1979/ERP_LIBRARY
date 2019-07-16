package api.exchange;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlElementRef;


/**
 * <p>
 * Java class for property complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="property">
 *   &lt;complexContent>
 *     &lt;extension base="{http://exchange.mule.ptr/}simpletype">
 *       &lt;sequence>
 *         &lt;element name="file" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="sfields" type="{http://exchange.mule.ptr/}property" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="value" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "property", propOrder = { "name", "value", "file", "sfields", "adds" })
public class Property extends Simpletype implements Serializable{

	private static final long serialVersionUID = 2424513295967449627L;
	@XmlAttribute(name = "name", required = true)
	protected String name;
	@XmlAttribute(name = "value", required = false)
	protected String value;
	@XmlElementRef(name = "file", namespace = "http://exchange.mule.ptr/", type = JAXBElement.class, required = false)
	protected JAXBElement<byte[]> file;
	@XmlElement(name = "sfields", nillable = true)
	protected List<Property> sfields;
	@XmlElement(name = "adds", nillable = true)
	protected List<String> adds;

	/** 
	 * Gets the value of the file property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link byte[]}
	 *         {@code >}
	 * 
	 */
	public JAXBElement<byte[]> getFile() {
		return file;
	}

	/**
	 * Sets the value of the file property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link byte[]}
	 *            {@code >}
	 * 
	 */
	public void setFile(JAXBElement<byte[]> value) {
		this.file = value;
	}

	/**
	 * Gets the value of the sfields property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the sfields property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getSfields().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link Property
	 * }
	 * 
	 * 
	 */
	public List<Property> getSfields() {
		if (sfields == null) {
			sfields = new ArrayList<Property>();
		}
		return this.sfields;
	}

	/**
	 * Gets the value of the name property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the value of the name property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setName(String value) {
		this.name = value;
	}

	/**
	 * Gets the value of the value property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets the value of the value property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setValue(String value) {
		this.value = value;
	}
		

	public List<String> getAdds() {
		return adds;
	}

	public void setAdds(List<String> adds) {
		this.adds = adds;
	}

	public void setSfields(List<Property> sfields) {
		this.sfields = sfields;
	}

	Property() {
	};

	public Property(String type, String name, String value, byte[] file, List<Property> sfields) {

		super.type = type;
		this.name = name;
		this.value = value;
		if (file != null) {			
			ObjectFactory objectFactory = new ObjectFactory();
			this.file = objectFactory.createPropertyFile(file);
		} else {
			this.file = null;
		}
		this.sfields = sfields;
	};
	
	public Property(String type, String name, String value, Property sfields) {

		super.type = type;
		this.name = name;
		this.value = value;
		this.sfields = new ArrayList<>();
		this.sfields.add(sfields);
	};


	public Property(String type, String name, String value) {
		super.type = type;
		this.name = name;
		this.value = value;
	}

	public static java.lang.Object modifyValue(Property p) {

		java.lang.Object f = null;

		try {
			if (p != null) {

				if (p.getType().equals("Число")) 
				{
					f = Double.parseDouble(p.getValue().replace(",", "."));
				} 
				else if (p.getType().equals("Строка")) 
				{
					f = p.getValue();
				} 
				else if (p.getType().contains("Перечисление")) 
				{
					f = p.getValue();
				} 
				else if (p.getType().contains("Null")) 
				{
					f = "";
				} 
				else if (p.getType().contains("ВидДвижения")) 
				{
					f = p.getValue();
				} 
				else if (p.getType().equals("Дата")) 
				{					
					f = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse(p.getValue());
					
				} else if (p.getType().equals("Булево")) 
				{
					f = p.getValue().equalsIgnoreCase("Да") ? true : false;
				} 
				else if (p.getType().equals("Неопределено") || p.getType().equals("Не определено")) 
				{
					f = "";
				} 
				else if (p.getType().equals("ХранилищеЗначения")) 
				{
					f = p.getFile().getValue();
				} else 
				{
					f = null;
				}
			}
		} catch (Exception e) {

			if (p.getType().equals("Число")) 
			{
				f = 0D;
			} 
			else if (p.getType().equals("Строка") || p.getType().contains("Перечисление")
					|| p.getType().contains("ВидДвижения")) 
			{
				f = "";
			} 
			else if (p.getType().equals("Дата")) 
			{
				f = new Date();
			} 
			else if (p.getType().equals("Неопределено") || p.getType().equals("Не определено")) 
			{
				f = null;
			} else if (p.getType().equals("Булево")) 
			{
				f = false;
			} 
			else if (p.getType().equals("ХранилищеЗначения")) 
			{
				f = null;
			} else 
			{
				f = null;
			}
		}

		return f;

	}

	public static Property copy(Property value) {

		return new Property(value);
	}

	public Property(Property value) {

		this.name 		= value.getName();
		this.type 		= value.getType();
		this.value 		= value.getValue();
		this.file 		= value.getFile();
		this.sfields 	= value.getSfields();

	}
}
