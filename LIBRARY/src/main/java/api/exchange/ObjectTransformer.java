package api.exchange;

import java.util.ArrayList;
import java.util.List;

public class ObjectTransformer {
	
	private Object object;

	public ObjectTransformer(Object object) {
		super();
		this.object = object;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}
	
	public List<Object> transform(String base){
		
		List<Object> result = new ArrayList<>();
				
		if("УТ_ЮНИОН".equals(base))
			result =  ut_union_transform();
		else if("УУ_ЮНИОН".equals(base) || "УУ_СИЛТА".equals(base) || "УУ_ПЕТРОЦЕНТР".equals(base))
			result =  uu_union_transform();
		else if("УТ_СПБ".equals(base) || "УТ_МСК".equals(base) || "УТ_СЗФО".equals(base) || "УТ_СИЛТА".equals(base) || "УТ_ПЕТРОЦЕНТР".equals(base))
			result =  ut_spb_transform();
		
		result.add(this.object);
				
		return result;
		
	}
	
	private List<Object> ut_spb_transform(){
		
		List<Object> result = new ArrayList<>();
		
		Map map = new Map(this.object);
		
		if (map != null && map.NextItem("Документ.ЗаявкаНаРасходованиеСредств") && map.NextRow(0)) {
			map.getPropertyByName("Состояние").setType("Перечисление.СостоянияОбъектов");
			map.getPropertyByName("Ссылка").setType("Документ.ЗаявкаНаРасходованиеСредств");
			object.setType("Документ.ЗаявкаНаРасходованиеСредств");
			if (map.getPropertyByName("ДоговорКонтрагента") != null) {
				Row row = new Row();
				row.add(map.getPropertyByName("ДоговорКонтрагента"));
				row.add(map.getPropertyByName("СуммаВзаиморасчетов"));
				row.add(map.getPropertyByName("СуммаПлатежа"));
				row.add(new Property("Число", "КурсВзаиморасчетов", "1"));
				row.add(new Property("Число", "КратностьВзаиморасчетов", "1"));
				Item item = new Item(row, "Документ.ЗаявкаНаРасходованиеСредств.РасшифровкаПлатежа", object.getRecipient());
				object.getItem().add(item);
			}
		}
		
	return result;	
		
	}
	
	private List<Object> ut_union_transform(){
		
		List<Object> result = new ArrayList<>();
		
		Map map = new Map(this.object);
		
		if (map != null && map.NextItem("Документ.ЗаказПокупателя.Телефоны")) 
		{
			HistoryCallTable historyCallTable = new HistoryCallTable(map);
			
			if (historyCallTable.getItem() != null && historyCallTable.getItem().size() != 0) 
				result.add(historyCallTable);								
		}
		else if (map != null && map.NextItem("Документ.ЗаявкаНаРасходованиеСредств") && map.NextRow(0))
		{
			map.getPropertyByName("Состояние").setType("Перечисление.СостоянияОбъектов");
			map.getPropertyByName("Ссылка").setType("Документ.ЗаявкаНаРасходованиеСредств");
							
			this.object.setType("Документ.ЗаявкаНаРасходованиеСредств");
			if (map.getPropertyByName("ДоговорКонтрагента") != null) {
				Row row = new Row();
				row.add(map.getPropertyByName("ДоговорКонтрагента"));
				row.add(map.getPropertyByName("СуммаВзаиморасчетов"));
				row.add(map.getPropertyByName("СуммаПлатежа"));
				row.add(new Property("Число", "КурсВзаиморасчетов", "1"));
				row.add(new Property("Число", "КратностьВзаиморасчетов", "1"));
				this.object.getItem().add(new Item(row, "Документ.ЗаявкаНаРасходованиеСредств.РасшифровкаПлатежа", "УТ_ЮНИОН"));
			}			
		}

		return result;
		
	}
	
	private List<Object> uu_union_transform(){
		
		List<Object> result = new ArrayList<>();

		Map map = new Map(this.object);
		String type = this.object.getType();

		if (map != null && map.NextItem("РегистрСведений.БИТ_СдачаДокументовВБухгалтерию")) {
			int j = 0;
			while (map.NextRow(j)) {
				Property property = map.getPropertyByName("ПТР_Город");
				if (property != null)
					property.setType("Справочник.ПТР_Города");
				j++;
			}
		} else if (map != null && map.NextItem("Документ.ПринятиеКУчетуОС") && map.NextRow(0)) {
			Property property = map.getPropertyByName("ПорядокВключенияСтоимостиВСоставРасходовНУ");
			if (property != null) {
				property.setType("Перечисление.ПорядокВключенияСтоимостиОСВСоставРасходовНУ");
				String value = property.getValue();
				if ("Начисление амортизации".equals(value)) 
					property.setValue("Начисление амортизации");
				else if ("Списание при принятии к учету".equals(value)) 
					property.setValue("Включение в расходы при принятии к учету");
				
			}
		} else if ("Документ.БИТ_ОбщееПоступлениеУслуг".equals(type)) {
			if (map != null && map.NextItem("Документ.ПоступлениеДопРасходов") && map.NextRow(0)) {
				Item item = object.getItemByName("Документ.ПоступлениеДопРасходов");
				if (item != null && item.getRow() != null && !item.getRow().isEmpty()) {
					item.getRow().get(0)
					.add("Перечисление.СпособыРаспределенияДопРасходов", "СпособРаспределения","По количеству")
					.add("Перечисление.СтавкиНДС", "СтавкаНДС", "БезНДС")
					.add("Перечисление.бит_ВидОбщегоПоступления", "бит_ВидОбщегоПоступления","Общее поступление услуг");
				}
			}
		} else if ("Документ.БИТ_ОбщееПоступлениеДопРасходов".equals(type)) {			
			Item item = object.getItemByName("Документ.ПоступлениеДопРасходов");
			if (item != null && item.getRow() != null && !item.getRow().isEmpty()) {				
				item.getRow().get(0)
				.add("Перечисление.СтавкиНДС", "СтавкаНДС", "БезНДС")
				.add("Перечисление.бит_ВидОбщегоПоступления", "бит_ВидОбщегоПоступления","Общее поступление доп расходов");				
			}
		} else if ("Документ.ПоступлениеТоваровУслуг".equals(type)) {
			if (map != null && map.NextItem("Документ.ПоступлениеТоваровУслуг") && map.NextRow(0)) {
				Property property = map.getPropertyByName("бит_СрокДоставки");
				if (property != null)
					property.setType("Строка");
			}
		} else if ("Документ.РасходныйКассовыйОрдер".equals(type)) {
			if (map != null && map.NextItem("ДополнительныеСвойства") && map.NextRow(0)) {
				Property property = map.getPropertyByName("ДокументОснование");
				if (property != null)
					property.setType("Документ.бит_ЗаявкаНаРасходованиеСредств");
			}
		} else if ("Документ.ОтчетОРозничныхПродажах".equals(type)) {
			if (map != null && map.NextItem("Документ.ОтчетОРозничныхПродажах.ПТР_Оплата")) {
				int i = 0;
				while (map.NextRow(i)) {
					Property property = map.getPropertyByName("БИТ_ВидОплаты");
					if (property != null) {
						property.setType("Справочник.бит_ВидыОплатЧекаККМ");
						property.setName("БИТ_ВидОплатыЧекККМ");
					}
					i++;
				}
			}
		} else if ("Документ.Документ.БИТ_ОбщееПоступлениеУслуг".equals(type)) {
			if (map != null && map.NextItem("Документ.ПоступлениеДопРасходов") && map.NextRow(0)) {
				Property property = map.getPropertyByName("бит_ВидОбщегоПоступления");
				if (property != null) {
					property.setType("Перечисление.бит_ВидОбщегоПоступления");
					property.setValue("ОбщееПоступлениеУслуг");
				}
			}
		} else if ("Справочник.ПТР_Оборудование".equals(type)) {
			if (map != null && map.NextItem("Справочник.ОсновныеСредства") && map.NextRow(0)) {
				Property property = map.getPropertyByName("Код");
				if (property != null) {
					String code = property.getValue();
					String pref = code.substring(0, 1);
					code = pref + code.substring(3);
					property.setValue(code);
				}
			}
		} else if ("Документ.КорректировкаЗаписейРегистров".equals(type)) {
			if (map != null && map.NextItem("ДополнительныеСвойства")) {
				int i = 0;
				while (map.NextRow(i)) {
					Property property = map.getPropertyByName("Подразделение");
					if (property != null)
						property.setType("Справочник.бит_Подразделения");
					i++;
				}
			}
		}
		
		return result;
	}

}
