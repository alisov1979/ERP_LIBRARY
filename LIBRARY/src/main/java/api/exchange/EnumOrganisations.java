package api.exchange;

import java.util.ArrayList;
import java.util.List;

public enum EnumOrganisations {
	
	Novgorod("cc4b5e7c-77e8-11e5-89d5-00259038e9f2"), 
	Kingisepp("e66c0a53-77e8-11e5-89d5-00259038e9f2"), 
	Tver("c3572167-77e8-11e5-89d5-00259038e9f2"),
	Viborg("d6398a4b-77e8-11e5-89d5-00259038e9f2"),
	Luga("dc556767-77e8-11e5-89d5-00259038e9f2"),	
	Petrozavodsk("cc4b5e7b-77e8-11e5-89d5-00259038e9f2"),
	Moscow("6f65b0f9-77e8-11e5-89d5-00259038e9f2"),
	SaintPeterburg("64c03c7b-77e8-11e5-89d5-00259038e9f2"),
	Gatchina("955ebd7c-bd6f-11e7-ad18-00259038e9f2"),
	Vladimir("0a635aab-c532-11e7-ad18-00259038e9f2"),
	Kaluga("2379592f-c532-11e7-ad18-00259038e9f2"),
	Russia("d31cf195-2928-11e9-a76e-00259038e9f2");
	
	private final String id;
	
	EnumOrganisations(String id){
		this.id = id;
	};
	
	public String getId(){		
		return id;		
	}
	
	public static String getBase(String id){
		
		String base = "";
		
		if(id.equals("64c03c7b-77e8-11e5-89d5-00259038e9f2")){
			base = "spb";
		}
		else if(id.equals("6f65b0f9-77e8-11e5-89d5-00259038e9f2")){
			base = "msk";
		}
		else if(id.equals("cc4b5e7c-77e8-11e5-89d5-00259038e9f2")){
			base = "szfo";
		}
		else if(id.equals("6f65b0f9-77e8-11e5-89d5-00259038e9f2")){
			base = "szfo";
		}
		else if(id.equals("e66c0a53-77e8-11e5-89d5-00259038e9f2")){
			base = "szfo";
		}
		else if(id.equals("c3572167-77e8-11e5-89d5-00259038e9f2")){
			base = "szfo";
		}
		else if(id.equals("d6398a4b-77e8-11e5-89d5-00259038e9f2")){
			base = "szfo";
		}
		else if(id.equals("dc556767-77e8-11e5-89d5-00259038e9f2")){
			base = "szfo";
		}
		else if(id.equals("cc4b5e7b-77e8-11e5-89d5-00259038e9f2")){
			base = "szfo";
		}
		
		
		return base;
		
	}
	
	public static String getBaseName(String id){
		
		String base = "";
		
		if(id.equals("64c03c7b-77e8-11e5-89d5-00259038e9f2")){
			base = "УТ_СПБ";
		}
		else if(id.equals("6f65b0f9-77e8-11e5-89d5-00259038e9f2")){
			base = "УТ_МСК";
		}
		else if(id.equals("cc4b5e7c-77e8-11e5-89d5-00259038e9f2")){
			base = "УТ_СЗФО";
		}
		else if(id.equals("6f65b0f9-77e8-11e5-89d5-00259038e9f2")){
			base = "УТ_СЗФО";
		}
		else if(id.equals("e66c0a53-77e8-11e5-89d5-00259038e9f2")){
			base = "УТ_СЗФО";
		}
		else if(id.equals("c3572167-77e8-11e5-89d5-00259038e9f2")){
			base = "УТ_СЗФО";
		}
		else if(id.equals("d6398a4b-77e8-11e5-89d5-00259038e9f2")){
			base = "УТ_СЗФО";
		}
		else if(id.equals("dc556767-77e8-11e5-89d5-00259038e9f2")){
			base = "УТ_СЗФО";
		}
		else if(id.equals("cc4b5e7b-77e8-11e5-89d5-00259038e9f2")){
			base = "УТ_СЗФО";
		}else{
			base = "УТ_ЕСН";
		}
		
		
		return base;
		
	}
	
	public static List<String> getArrayOfCities(String baseName) {

		List<String> result = new ArrayList<>();
		switch (baseName) {
		case "УТ_СПБ":
			result.add(EnumOrganisations.SaintPeterburg.getId());
			break;
		case "УТ_МСК":
			result.add(EnumOrganisations.Moscow.getId());
			result.add(EnumOrganisations.Vladimir.getId());
			result.add(EnumOrganisations.Kaluga.getId());
			result.add(EnumOrganisations.Russia.getId());
			break;
		case "УТ_СЗФО":
			result.add(EnumOrganisations.Novgorod.getId());
			result.add(EnumOrganisations.Kingisepp.getId());
			result.add(EnumOrganisations.Tver.getId());
			result.add(EnumOrganisations.Viborg.getId());
			result.add(EnumOrganisations.Luga.getId());
			result.add(EnumOrganisations.Petrozavodsk.getId());
			result.add(EnumOrganisations.Gatchina.getId());
			break;
		case "УТ_ЕСН":
			result.add(EnumOrganisations.Novgorod.getId());
			result.add(EnumOrganisations.Kingisepp.getId());
			result.add(EnumOrganisations.Tver.getId());
			result.add(EnumOrganisations.Viborg.getId());
			result.add(EnumOrganisations.Luga.getId());
			result.add(EnumOrganisations.Petrozavodsk.getId());
			result.add(EnumOrganisations.Gatchina.getId());
			result.add(EnumOrganisations.SaintPeterburg.getId());
			result.add(EnumOrganisations.Moscow.getId());
			result.add(EnumOrganisations.Vladimir.getId());
			result.add(EnumOrganisations.Kaluga.getId());
			result.add(EnumOrganisations.Russia.getId());
			break;
		default:
			break;
		}
		return result;
	}



}
