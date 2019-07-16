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

import org.apache.commons.codec.digest.DigestUtils;


/**
 * <p>
 * Java class for object complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="object">
 *   &lt;complexContent>
 *     &lt;extension base="{http://exchange.mule.ptr/}complextype">
 *       &lt;sequence>
 *         &lt;element name="item" type="{http://exchange.mule.ptr/}item" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="sender" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Object", propOrder = {  "item", "hashKey","sender", "reply", "time" })
@XmlRootElement(name = "Object", namespace = "http://exchange.mule.ptr/")
public class Object extends Complextype implements Serializable, iExchangable {

		private static final long serialVersionUID = -2852516469102052593L;
		@XmlElement(name = "item", nillable = true)
	    protected List<Item> item;
	    @XmlElement(name = "hashKey")
	    protected String hashKey = "";
	    @XmlAttribute(name = "reply")
	    protected String reply = "";
	    @XmlAttribute(name = "sender", required = true)
	    protected String sender = "";
	    @XmlAttribute(name = "time")
	    protected String time = "";
	    
		public Object() {
		};

	/**
	 * Gets the value of the item property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the item property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getItem().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link Item }
	 * 
	 * 
	 */
	public List<Item> getItem() {
		if (item == null) {
			item = new ArrayList<Item>();
		}
		return this.item;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	/**
	 * Gets the value of the sender property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSender() {
		return sender;
	}

	/**
	 * Sets the value of the sender property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSender(String value) {
		this.sender = value;
	}

	public List<String> getRecipients() {
		
		if(this.recipients == null){
			this.recipients = new ArrayList<>();
		}
		
		return recipients;
	}
	
	public void setRecipients(List<String> recipients){
		
		super.recipients = recipients;
		
	}

	public String getRecipient() {

		String recipient = "";

		if (this.recipients != null && this.recipients.size() != 0) {

			recipient = this.recipients.get(0);

		}

		return recipient;
	}

	public void setItem(List<Item> item) {
		this.item = item;
	}

	public Object(Item item, String sender, String type, String recipient) {
		super();
		this.item = new ArrayList<>();
		this.item.add(item);
		this.sender = sender;
		super.type = type;
		super.recipients = new ArrayList<>();
		super.recipients.add(recipient);
	}

	public Object(Item item, String sender, String type, List<String> recipient) {
		super();
		this.item = new ArrayList<>();
		this.item.add(item);
		this.sender = sender;
		super.type = type;
		super.recipients = recipient;
	}


	
	public Object(List<Item> items, String type, String recipient, String sender){
		
		super.recipients = new ArrayList<>();
		super.recipients.add(recipient);
		super.type = type;
		this.sender = sender;
		this.item = items;
	}
	
	public Object(List<Item> items, String type, String recipient, String sender, String reply){
		
		super.recipients = new ArrayList<>();
		super.recipients.add(recipient);
		super.type = type;
		this.sender = sender;
		this.item = items;
		this.reply = reply;
	}

	public void addItems(List<Item> items) {

		if (this.item == null) {
			this.item = new ArrayList<>();
		}

		for (Item i : items) {
			String t = i.getType();
			if (t.contains("request") == false
					&& t.contains("Удаление") == false)
				this.item.add(i);
		}
	}

	public void addRows(String itemType, Row row) {

		if (this.item == null) {
			this.item = new ArrayList<>();
		}

		Item i = this.getItemByName(itemType);

		if (i == null) {
			this.item.add(new Item(itemType, row));
		} else {
			i.addRows(row);
		}

	}

	public Item getItemByName(String name) {

		for (Item i : this.item) {
			if (i.getType().equals(name)) {
				return i;
			}
		}

		return null;
	}
	
	public String getType(){
		return this.type;
	}
	
	public void addItem(Item item){
		
		if(this.item == null){
			this.item = new ArrayList<>();
		}
		
		this.item.add(item);		
	}
	

	public String getHashCode() {
		
		if (this.getItem() == null)
			return null;
		
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(getType());
		this.getItem().forEach(i -> i.getRow().forEach(r -> r.getProperty()
				.forEach(p -> stringBuffer
						.append(i.getType())
						.append(p.getName())
						.append(p.getType())
						.append(p.getValue())						
						)));
		
		stringBuffer.append(this.getSender());
		stringBuffer.append(this.getRecipient());

		return DigestUtils.md5Hex(stringBuffer.toString());
					
	}
	
	public List<String> getListOfQueue(){
		
		List<String> result = new ArrayList<>();

		for (String recipient : getRecipients()){
			
			switch (recipient) 
			{
			case "УТ_СПБ":
				result.add("ut.spb.set");
				break;
			case "УТ_СЗФО":
				result.add("ut.reg.set");
				break;
			case "УТ_МСК":
				result.add("ut.msk.set");
				break;	
			case "УТ_ЮНИОН":
				result.add("ut.uni.set");
				break;	
			case "УТ_ЕСН":
				result.add("ut.esn.set");
				break;		
			case "b2b":
				result.add("ws.dev.local");
				break;	
			case "SHOP":
				result.add("order-forbid-changes");
				break;	
			case "PORTAL":
				result.add("ws.stdp.ru");
				if ("Справочник.ПТР_УчетныеЗаписиПользователейЭкстранет".equals(getType()))
				{
					result.add("portal-portal");
				}
				break;	
			case "zabbix":
				result.add("zabbix");
				break;
			case "EDI":
				result.add("edi.in");
				break;
			case "MD.CRM":
				result.add("ms.crm");
				break;
			case "NAUMEN":
				result.add("naumen");
				break;
			case "JBPM":
				result.add("jbpm");
				break;
			case "УУ_ЮНИОН":
				result.add("uu.uni.set");
				break;	
			case "TALEND":
				result.add("talend");
				break;
			case "SITE":
				result.add("site");
				break;
			case "УТЦ_ЮНИОН":
				result.add("utc.set");
				break;
			case "ХРАНИЛИЩЕ_ФАЙЛОВ":
				result.add("file.store");
				break;
			case "APOLLO":
				result.add("apollo");
				break;
			case "УТ_СИЛТА":
				result.add("ut.silta.set");
				break;
			case "УТ_ПЕТРОЦЕНТР":
				result.add("ut.metal.set");
				break;
			case "PRODUCTS":
				result.add("products");
				break;
			case "DOCS_VISION":
				result.add("docs.vision");
				break;	
			case "DOCS_VISION_TEST":
				result.add("docs.vision.test");
				break;	
			case "ЕГАИС":
				result.add("egais");
				break;	
			case "ЛМС":
				result.add("lms.set");
				break;		
			case "УУ_СИЛТА":
				result.add("uu.silta.set");
				break;
			case "УУ_ПЕТРОЦЕНТР":
				result.add("uu.metal.set");
				break;	
			case "DiState":
				result.add("DiState");
				break;	
			case "EQUIPMENT RENT":
				result.add("equipment.rent");
				break;
			case "BITRIX":
				result.add("BITRIX");
				break;
			case "ActiveDirect":
				result.add("ActiveDirect");
				break;
			case "Проведение":
				result.add("mule.processing");
				break;
			case "БУ_ЛСК":
				result.add("uu.lsk.set");
				break;
			case "СОП_БИЗ":
				result.add("uu.sop.set");
				break;
			case "СПБ_ЗУП":
				result.add("zup.set");
				break;
			case "ЦФС":
				result.add("cfs.set");
				break;
			}			
		}
		return result;		
	}
	
	@Override
	public Object getObject() {
		return this;
	}
	
	public String getRequestHashCode(){
		
		if (this.getItem() == null)
			return null;
		StringBuffer stringBuffer = new StringBuffer();
		
		this.getItem().forEach(i ->{
			
			if("request".equals(i.getType())){
				i.getRow().forEach(
						row -> row.getProperty().forEach(
								p -> stringBuffer.append(p.getName()).append(p.getType()).append(p.getValue())));
			}
			
		});
		
		stringBuffer.append(this.getSender());
		stringBuffer.append(this.getRecipient());
		
		if(! "".equals(stringBuffer.toString()))
			return DigestUtils.md5Hex(stringBuffer.toString());
				
		return null;
	}

	public String getHashKey() {
		return hashKey;
	}

	public void setHashKey(String hashKey) {
		this.hashKey = hashKey;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Object [item=" + item + ", hashKey=" + hashKey + ", reply=" + reply + ", sender=" + sender + ", time="
				+ time + "]";
	}
	
	public void clear(String reciver)
	{
		this.getItem().removeIf(item -> ! item.getRecipients().contains(reciver) && !"request".equals(item.getType()));	
		this.getItem().forEach(item -> item.getRecipients().removeIf(rec -> ! rec.equals(reciver)));
		this.getRecipients().removeIf(rec -> ! rec.equals(reciver));
	}
	
	
}
