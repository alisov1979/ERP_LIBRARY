package ptr.mule.exchange.services;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "bases", })
public class BaseWsdlSettings {
	
	@JsonProperty("bases")
	protected List<Base> bases;
	@JsonProperty("bases")
	public List<Base> getBases() {
		return bases;
	}
	@JsonProperty("bases")
	public void setBases(List<Base> bases) {
		this.bases = bases;
	}
	
	public Base getBaseSetting(String basename){
		
		for (Base b : this.bases){
			
			if(b.getName().equals(basename)){
				return b;
			}	
		}
		
		return null;
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonPropertyOrder({ "name", "baseName", "wsdl", "login", "password", "get", "set", "use" })
	public static class Base {

		@JsonProperty("name")
		protected String name  = "";
		@JsonProperty("baseName")
		protected String baseName = "";
		@JsonProperty("wsdl")
		protected String wsdl = "";
		@JsonProperty("login")
		protected String login = "";
		@JsonProperty("password")
		protected String password;
		@JsonProperty("get")
		protected String get = "";
		@JsonProperty("set")
		protected String set = "";
		@JsonProperty("use")
		protected Boolean use = true;
		
		
		@JsonProperty("wsdl")
		public String getWsdl() {
			return wsdl;
		}
		@JsonProperty("wsdl")
		public void setWsdl(String wsdl) {
			this.wsdl = wsdl;
		}
		@JsonProperty("login")
		public String getLogin() {
			return login;
		}
		@JsonProperty("login")
		public void setLogin(String login) {
			this.login = login;
		}
		@JsonProperty("password")
		public String getPassword() {
			return password;
		}
		@JsonProperty("password")
		public void setPassword(String password) {
			this.password = password;
		}
		@JsonProperty("name")
		public String getName() {
			return name;
		}
		@JsonProperty("name")
		public void setName(String name) {
			this.name = name;
		}
		@JsonProperty("get")
		public String getGet() {
			return get;
		}
		@JsonProperty("get")
		public void setGet(String get) {
			this.get = get;
		}
		@JsonProperty("set")
		public String getSet() {
			return set;
		}
		@JsonProperty("set")
		public void setSet(String set) {
			this.set = set;
		}
		@JsonProperty("baseName")
		public String getBaseName() {
			return baseName;
		}
		@JsonProperty("baseName")
		public void setBaseName(String baseName) {
			this.baseName = baseName;
		}

		@JsonProperty("use")
		public Boolean getUse() {
			return use == null ? true : use;
		}
		@JsonProperty("use")
		public void setUse(Boolean use) {
			this.use = use;
		}
		@Override
		public String toString() {
			return  "*************************************"  
					+ System.lineSeparator() + "name: " + name 
					+ System.lineSeparator() + "baseName: " + baseName 
					+ System.lineSeparator() + "wsdl: " + wsdl 
					+ System.lineSeparator() + "login: " + login
					+ System.lineSeparator() + "password: " + password 
					+ System.lineSeparator() + "get: " + get 
					+ System.lineSeparator() + "set: " + set + ""
					+ System.lineSeparator() + "use: " + use + ""
					+ System.lineSeparator() + "*************************************";
		}


	}

}
