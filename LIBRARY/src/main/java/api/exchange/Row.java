
package api.exchange;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for row complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="row">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="property" type="{http://exchange.mule.ptr/}property" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Row", propOrder = {
    "property"
})
public class Row implements Serializable{

	private static final long serialVersionUID = 512864843942548012L;
	@XmlElement(name = "property", nillable = true)
    protected List<Property> property;


    public void setProperty(List<Property> property) {
		this.property = property;
	}
    
    public Row add(Property value){
    	
    	if(this.property == null){
    		this.property = new ArrayList<Property>();
    	}
    	
    	this.property.add(value);
    	
    	return this;
    }
    
    public Row add(String type, String name, java.lang.Object value){
    	
    	return this.add(new Property(type, name, String.valueOf(value)));
    }
    
    public Row add(String type, String name, String value){
    	
    	return this.add(new Property(type, name, value));
    }
    
 

	/**
     * Gets the value of the property property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the property property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProperty().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Property }
     * 
     * 
     */
    public List<Property> getProperty() {
        if (property == null) {
            property = new ArrayList<Property>();
        }
        return this.property;
    }
    
    public Row(){};
    
    public Row(List<Property> property){
    	
    	this.property = property;
    }
    
    public Row(Property property){
    	
    	if(this.property == null){
    		this.property = new ArrayList<>();
    	}
    	
    	this.property.add(property);
    	
    }
    
    public Property getProperty(String name){
    	  	
    	for(Property p: this.getProperty()){
    		
    		if (p.getName().equals(name)){
    			return p;
    		}
    		
    	}
    	
    	return new Property();
    }
    
 
}
