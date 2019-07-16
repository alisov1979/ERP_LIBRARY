
package api.exchange;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="order" type="{http://exchange.mule.ptr/}property"/>
 *         &lt;element name="object" type="{http://exchange.mule.ptr/}row"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "order",
    "object"
})
@XmlRootElement(name = "CreateCertificateOrder")
public class CreateCertificateOrder {

    @XmlElement(required = true)
    protected Property order;
    @XmlElement(required = true)
    protected Row object;

    /**
     * Gets the value of the order property.
     * 
     * @return
     *     possible object is
     *     {@link Property }
     *     
     */
    public Property getOrder() {
        return order;
    }

    /**
     * Sets the value of the order property.
     * 
     * @param value
     *     allowed object is
     *     {@link Property }
     *     
     */
    public void setOrder(Property value) {
        this.order = value;
    }

    /**
     * Gets the value of the object property.
     * 
     * @return
     *     possible object is
     *     {@link Row }
     *     
     */
    public Row getObject() {
        return object;
    }

    /**
     * Sets the value of the object property.
     * 
     * @param value
     *     allowed object is
     *     {@link Row }
     *     
     */
    public void setObject(Row value) {
        this.object = value;
    }

}
