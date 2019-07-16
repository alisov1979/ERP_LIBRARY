package ptr.mule.exchange.loging;

import java.util.Arrays;

import api.exchange.*;
import api.exchange.Object;


public class Log extends Object{
	
	private static final long serialVersionUID = -6454135789519041344L;

	public Log(String projectName, String queueName, String error, Object object){
		
		this.type 	= "РегистрСведений.mule_ЖурналЛогированияОшибокВОбменах";
		this.sender = "MULE";
		
		Map map = new Map(object);
		
		String objectType = object.getType();
		
		Row row = new Row();
		
		if(objectType.contains("Справочник.")
				|| objectType.contains("Документ.")
				|| objectType.contains("Задача.")
				|| objectType.contains("БизнесПроцесс.")
				|| objectType.contains("ПланВидовХарактеристик."))
		{
			if(map!= null && map.NextItem("request") && map.NextRow(0)){
				
				Property ref = map.getPropertyByName("Ссылка");				
				String md5   = object.getRequestHashCode();
				
				if(ref != null)
				{
					row.add(ref.getType(), "Объект", ref.getValue());
					row.add("Строка", "Хэш", 		 md5);
					row.add("Строка", "Отбор", 	"");
				}

			}
				
		}
		else
		{
			if(map!= null && map.NextItem("request") && map.NextRow(0)){
				
				String md5 = object.getRequestHashCode();
				
				Item item = object.getItemByName("request");
				if(item != null)
				{
					row.add("Строка", "Отбор", 	item.toString());
					row.add("Строка", "Хэш", 	md5);
					row.add("Документ.ЗаказПокупателя", "Объект", "Неопределено");	
				}	
			}
		}
		
		row.add("Строка", "Отправитель", 	object.getSender());
		row.add("Строка", "Получатель", 	object.getRecipient());
		row.add("Строка", "ОписаниеОшибки", error);
		row.add("Строка", "Очередь", 		queueName);
		row.add("Строка", "Проект", 		projectName);
		row.add("Строка", "ИмяОбъекта", 	object.getType());
		
		Item item = new Item("request", row);
		
		this.item = Arrays.asList(item);
		this.recipients = Arrays.asList("УТ_ЮНИОН");
		
	}

	@Override
	public String toString() {
		
		StringBuilder stringBuilder  = new StringBuilder();
		stringBuilder.append("<object xmlns=\"http://exchange.mule.ptr/\">");
		this.item.forEach(i -> stringBuilder.append(i.toString()));
		stringBuilder.append("</exc:object>");
		
		return stringBuilder.toString();
	}
	
	
	
	
}
