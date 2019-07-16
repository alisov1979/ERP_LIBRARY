package api.exchange;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;


public interface iExchangable {
	
	public String getType();
	
	public Object getObject();
	
	public List<String> getRecipients();
	
	public String getSender();
	
	public default String getXML(JAXBContext context) throws JAXBException, IOException{
		
		String result = "";
		
		try(StringWriter sw = new StringWriter()){
			
			context.createMarshaller().marshal(this, sw);	
			
			result = sw.toString();
		}
			
		return result;
		
	}

}
