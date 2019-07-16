package api.exchange;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.HashMap;

public class Map {

	private HashMap<String, HashMap<Integer, HashMap<String, Property>>> map;
	private HashMap<Integer, HashMap<String, Property>> item;
	private HashMap<String, Property> row;

	public Map(Object object) {

		createObject(object);

	}

	public HashMap<String, Property> getRow() {
		return this.row;
	}

	private void createObject(Object object) {

		List<Item> items = object.getItem();

		this.map = new HashMap<>();

		for (Item i : items) {
			Integer z = 0;
			map.put(i.getType(), new HashMap<>());

			if (i.getRow() != null) {
				for (Row r : i.getRow()) {

					map.get(i.getType()).put(z, new HashMap<>());

					if (r != null && r.getProperty() != null) {
						for (Property p : r.getProperty()) {
							map.get(i.getType()).get(z).put(p.getName(), p);
						}
					}
					z++;
				}
			}

		}

	}

	private void selectItem(String itemName) {
		if (map != null) {
			this.item = map.get(itemName);
		}
	}

	
	private void selectRow(Integer rowNumber) {
		if (item != null) {
			this.row = item.get(rowNumber);
		}
		else
		{
			throw new NullPointerException("Объект Item не определен.");
		}
	}

	public Property getPropertyByName(String name) {

		Property result = null;

		if (row != null) {
			result = this.row.get(name);
		}

		return result;
	}

	public boolean NextRow(Integer value) {

		this.selectRow(value);

		if (this.row == null) {
			return false;
		}

		return true;
	}

	public boolean NextItem(String value) {

		this.selectItem(value);

		if (this.item == null) {
			return false;
		}

		return true;
	}

	public static List<Object> createObjects(Object object, String idName) {

		List<String> idList = new ArrayList<>();
		List<Object> objectList = new ArrayList<>();

		for (Item i : object.getItem()) {
			outer: for (Row r : i.getRow()) {
				for (Property p : r.getProperty()) {
					if (p.getName().equals(idName)) {
						if (idList.contains(p.getValue()) == false) {
							idList.add(p.getValue());
							continue outer;
						}

					}
				}
			}
		}

		Integer z = 0;

		for (String id : idList) {
			objectList.add(new Object());

			for (Item i : object.getItem()) {

				if (i.getType().contains(".Удаление")) {

					objectList.get(z).addRows(i.getType(), null);

				} else {

					outer: for (Row r : i.getRow()) {
						for (Property p : r.getProperty()) {
							if (p.getName().equals(idName)
									&& p.getValue().equals(id)) {
								objectList.get(z).addRows(i.getType(), r);
								continue outer;
							}
						}
					}
				}
			}
			z++;
		}

		return objectList;
	}

	public static String BooleanToString(Boolean value) {

		String result = "0";

		if (value == true) {
			result = "1";
		}

		return result;

	}

	public Property changeName(Property property, String newName){
		
		property.setName(newName);
		
		return property;				
	}
	

	public Double getDouble(Property property) {

		try {
			return Double.valueOf(property.getValue().replace(",", "."));
		} catch (NullPointerException | NumberFormatException e) {
			return 0D;
		}

	}
	
	public Double getDouble(String value) {
		
		Property property = getPropertyByName(value);

		try {
			return Double.valueOf(property.getValue().replace(",", "."));
		} catch (NullPointerException | NumberFormatException e) {
			return 0D;
		}

	}

	public Float getFloat(Property property) {

		try {
			return Float.valueOf(property.getValue().replace(",", "."));
		} catch (NullPointerException | NumberFormatException e) {
			return 0F;
		}

	}
	
	public Float getFloat(String value) {
		
		Property property = getPropertyByName(value);

		try {
			return Float.valueOf(property.getValue().replace(",", "."));
		} catch (NullPointerException | NumberFormatException e) {
			return 0F;
		}

	}

	public Integer getInteger(Property property) {

		try {
			return Integer.valueOf(property.getValue().replace(",", "."));
		} catch (NullPointerException | NumberFormatException e) {
			return 0;
		}
	}
	
	public Integer getInteger(String value) {
		
		Property property = getPropertyByName(value);

		try {
			return Integer.valueOf(property.getValue().replace(",", "."));
		} catch (NullPointerException | NumberFormatException e) {
			return 0;
		}
	}

	public Boolean getBoolean(Property property) {

		if (property != null) {

			String tmp = property.getValue();
			Boolean value = false;

			switch (tmp) {
			case "Да":
				value = true;
				break;

			case "Нет":
				value = false;
				break;
			default:
				value = false;
				break;
			}

			return value;

		} else {

			return null;
		}
	}
	
	public Boolean getBoolean(String value) {
		
		Property property = getPropertyByName(value);

		if (property != null) {

			String tmp = property.getValue();
			Boolean b = false;

			switch (tmp) {
			case "Да":
				b = true;
				break;

			case "Нет":
				b = false;
				break;
			default:
				b = false;
				break;
			}

			return b;

		} else {

			return null;
		}
	}
	

	public String getString(Property property) {

		if (property != null) {

			return property.getValue();

		} else {

			return "";
		}

	}
	
	public String getString(String value) {
		
		Property property = getPropertyByName(value);

		if (property != null) {

			return property.getValue();

		} else {

			return "";
		}

	}
	
	public String getString(String value, Boolean getSeparator) {
		
		Property property = getPropertyByName(value);
		if (property != null && getSeparator == true && property.getAdds() != null) {
			String result = "";
			for (String s : property.getAdds()) {
				result = result + s + System.lineSeparator();
			}
			return result;
		} else if (property != null) {
			return property.getValue();
		} else {

			return "";
		}
	}

	public String getReference(Property property) {

		if (property != null) {

			String tmp = property.getValue();

			if ("".equals(tmp.trim()) || "00000000-0000-0000-0000-000000000000".equals(tmp.trim())) {

				return null;
			}

			return property.getValue();

		} else {

			return null;

		}

	}
	
	public String getReference(String value) {
		
		Property property = getPropertyByName(value);

		if (property != null) {

			String tmp = property.getValue();

			if ("".equals(tmp.trim()) || "00000000-0000-0000-0000-000000000000".equals(tmp.trim())) {

				return "00000000-0000-0000-0000-000000000000";
			}

			return property.getValue();

		} else {

			return "00000000-0000-0000-0000-000000000000";

		}

	}

	public XMLGregorianCalendar getDate(Property property) {

		if (property == null) {
			return null;
		}

		DateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
		GregorianCalendar gregory = new GregorianCalendar();
		Date tmp = null;
		XMLGregorianCalendar value = null;

		try {
			tmp = format.parse(property.getValue());
			gregory.setTime(tmp);
			value = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregory);
		} catch (ParseException | DatatypeConfigurationException e) {

		}

		return value;

	}
	
	public XMLGregorianCalendar getDate(String value ) {
		
		Property property = getPropertyByName(value);

		if (property == null) {
			return null;
		}

		DateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
		GregorianCalendar gregory = new GregorianCalendar();
		Date tmp = null;
		XMLGregorianCalendar dt = null;

		try {
			tmp = format.parse(property.getValue());
			gregory.setTime(tmp);
			dt = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregory);
		} catch (ParseException | DatatypeConfigurationException e) {

		}

		return dt;

	}
	
	public String getSQLDate(String value ) {
		
		Property property = getPropertyByName(value);

		if (property == null) {
			return null;
		}

		DateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
		DateFormat formatout = new SimpleDateFormat("yyyy-MM-dd");
		
		GregorianCalendar gregory = new GregorianCalendar();
		Date tmp = null;
		String result = "";

		try {
			tmp = format.parse(property.getValue());
			gregory.setTime(tmp);
			XMLGregorianCalendar dt = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregory);
			
			result = formatout.format(dt.toGregorianCalendar().getTime());
			
		} catch (ParseException | DatatypeConfigurationException e) {

		}

		return result;

	}
	
	public String getDateRFCFormat(String value){
		
		Property property = getPropertyByName(value);

		if (property == null) {
			return null;
		}

		DateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
		Date tmp = null;
	

		try {
			tmp = format.parse(property.getValue());
		} catch (ParseException e) {

		}

		return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").format(tmp);
		
		
	}
	
	public byte[] getByte(String value){
		
		Property property = getPropertyByName(value);

		if (property == null) return new byte[0];
		
		if (property.getFile() != null) return property.getFile().getValue();
		
		return new byte[0];		
	}	

}
