package api.exchange;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class HistoryCallTable extends Object implements iExchangable {

	private static final long serialVersionUID = -2052853063053096574L;

	public HistoryCallTable(Map map) {

		List<Property> properties = new ArrayList<>();

		if (map != null && map.NextItem("Документ.ЗаказПокупателя.Телефоны")) {
			Integer j = 0;
			List<String> arrayOfPhonesIN = new ArrayList<>();
			List<String> arrayOfPhonesOUT = new ArrayList<>();
			while (map.NextRow(j)) {
				if (j == 0) {
					properties.add(map.getPropertyByName("ЗаказПокупателя"));
					properties.add(map.getPropertyByName("Номер"));
					properties.add(map.getPropertyByName("Дата"));
					properties.add(map.getPropertyByName("Контрагент"));
					properties.add(map.getPropertyByName("ИНН"));
					properties.add(map.getPropertyByName("НомерКарты"));
					properties.add(map.getPropertyByName("СтатусЗаказа"));
					properties.add(map.getPropertyByName("Ответственный"));
					properties.add(new Property("Строка", "Идентификатор", map.getReference("Идентификатор")));
				}
				arrayOfPhonesIN.add(map.getString("Телефон"));
				j++;
			}			
			arrayOfPhonesIN.forEach(phone ->	
				convertPhoneNumbersToArray(phone).forEach(number -> arrayOfPhonesOUT.add(number.replaceAll("\\D", ""))));	
			j = 0;
			/* объект создается только при наличии телефонов в заказе */
			if (arrayOfPhonesOUT.isEmpty() == false) {
				while (j < arrayOfPhonesOUT.size() && j < 3) {
					Byte n = (byte) (j + 1);
					properties.add(new Property("Строка", "Телефон".concat((n).toString()), arrayOfPhonesOUT.get(j)));
					j++;
				}

				this.item = Arrays.asList(new Item("РегистрСведений.ПТР_ИсторияЗвонковПоЗаказамПокупателей", new Row(properties),
						"УТ_ЮНИОН"));
				this.type = "РегистрСведений.ПТР_ИсторияЗвонковПоЗаказамПокупателей";
				this.recipients = Arrays.asList("УТ_ЮНИОН");
				this.sender = "УТ_СПБ";
			}
		}
	}
	
	private List<String> convertPhoneNumbersToArray(String phoneNumber) {

		List<String> arrayOfNumbers = new ArrayList<>();
		String[] pNumbers = phoneNumber.split(";");
		for (String str : pNumbers) {
			Integer j = str.length() - 1;
			if (str.contains("#")) {
				while (j > 0) {
					String symbol = str.substring(j);
					if (symbol.contains("#")) {
						arrayOfNumbers.add(str.substring(0, j));
						break;
					}
					j--;
				}

			} else {
				if (str.trim().length() > 0) {
					arrayOfNumbers.add(str);
				}
			}
		}
		return arrayOfNumbers;
	}

}
