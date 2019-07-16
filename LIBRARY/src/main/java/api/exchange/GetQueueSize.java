
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
 *         &lt;element name="planOfExchange" type="{http://exchange.mule.ptr/}row"/>
 *         &lt;element name="unitOfExchange" type="{http://exchange.mule.ptr/}row"/>
 *         &lt;element name="metaType" type="{http://exchange.mule.ptr/}row"/>
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
    "planOfExchange",
    "unitOfExchange",
    "metaType"
})
@XmlRootElement(name = "GetQueueSize")
@Deprecated
public class GetQueueSize {

    @XmlElement(required = true)
    protected Row planOfExchange;
    @XmlElement(required = true)
    protected Row unitOfExchange;
    @XmlElement(required = true)
    protected Row metaType;

    /**
     * Gets the value of the planOfExchange property.
     * 
     * @return
     *     possible object is
     *     {@link Row }
     *     
     */
    public Row getPlanOfExchange() {
        return planOfExchange;
    }

    /**
     * Sets the value of the planOfExchange property.
     * 
     * @param value
     *     allowed object is
     *     {@link Row }
     *     
     */
    public void setPlanOfExchange(Row value) {
        this.planOfExchange = value;
    }

    /**
     * Gets the value of the unitOfExchange property.
     * 
     * @return
     *     possible object is
     *     {@link Row }
     *     
     */
    public Row getUnitOfExchange() {
        return unitOfExchange;
    }

    /**
     * Sets the value of the unitOfExchange property.
     * 
     * @param value
     *     allowed object is
     *     {@link Row }
     *     
     */
    public void setUnitOfExchange(Row value) {
        this.unitOfExchange = value;
    }

    /**
     * Gets the value of the metaType property.
     * 
     * @return
     *     possible object is
     *     {@link Row }
     *     
     */
    public Row getMetaType() {
        return metaType;
    }

    /**
     * Sets the value of the metaType property.
     * 
     * @param value
     *     allowed object is
     *     {@link Row }
     *     
     */
    public void setMetaType(Row value) {
        this.metaType = value;
    }

}
