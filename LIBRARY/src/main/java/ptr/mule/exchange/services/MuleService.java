package ptr.mule.exchange.services;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.ws.BindingProvider;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import api.exchange.Item;
import api.exchange.MuleExchangePortType;
import api.exchange.Object;
import api.exchange.ObjectTransformer;
import api.exchange.Row;
import ptr.mule.exchange.services.BaseWsdlSettings.Base;

public class MuleService {
	
	private ConcurrentHashMap<String, List<String>> organizations = new ConcurrentHashMap<>();
	
	static Log logger = LogFactory.getLog("ptr.mule.exchange.service.MuleService");

	protected static BaseWsdlSettings wsdlSettings;
	protected static Map<String, String> getQueue;
	protected static Map<String, String> setQueue;
	protected static Map<String, String> setQueueRev;
	protected static Map<String, String> baseNames;
	protected static Map<String, MuleExchangePortType> map;
	
	static {
		
		wsdlSettings = getWsdlSettings();

		map 		= new HashMap<>();
		getQueue 	= new HashMap<>();
		setQueue 	= new HashMap<>();
		baseNames 	= new HashMap<>();
		setQueueRev = new HashMap<>();
		
		String log = "";
		
		for (Base base : wsdlSettings.getBases()) {			
			
			if(! base.getUse())
				continue;
			
			if (base.getGet() != null && !base.getGet().equals(""))
				getQueue.put(base.getName(), base.getGet());
			if (base.getSet() != null && !base.getSet().equals("")) {
				setQueue.put(base.getSet(), base.getName());
				setQueueRev.put(base.getName(), base.getSet());
			}
			if (base.getBaseName() != null && !base.getBaseName().equals(""))
				baseNames.put(base.getName(), base.getBaseName());
			
			log = log + base.toString() + System.lineSeparator(); 
		}
		
		log = System.lineSeparator()
		+ "////////////////////////////////////////////////////" + System.lineSeparator()
		+ log  + System.lineSeparator();
		
		logger.info(log);
	}
	
	public static String getGetQueue(String base)
	{
		return getQueue.get(base);
	}
	
	public static String getSender(String queue)
	{
		return setQueue.get(queue);
	}
	
	public static String getSetQueue(String base)
	{
		return setQueueRev.get(base);
	}
	
	public static String getBaseName(String base)
	{
		return baseNames.get(base);
	}
	
	public static List<String> getBases()
	{
		return new ArrayList<>(baseNames.values());
	}
	

	protected static MuleExchangePortType getService(String base) {
				
		try {
			if ("УТ_СЗФО".equals(base)) {
				return  new UT_REG_Exchange().getMuleExchangeSoap();
			} else if ("УТ_СПБ".equals(base)) {
				return new UT_SPB_Exchange().getMuleExchangeSoap();
			} else if ("УТ_МСК".equals(base)) {
				return new UT_MSK_Exchange().getMuleExchangeSoap();
			} else if ("УТ_СИЛТА".equals(base)) {
				return new UT_SILTA_Exchange().getMuleExchangeSoap();
			} else if ("УТ_ПЕТРОЦЕНТР".equals(base)) {
				return new UT_METAL_Exchange().getMuleExchangeSoap();
			} else if ("УТ_ЮНИОН".equals(base)) {
				return new UT_UNI_Exchange().getMuleExchangeSoap();
			} else if ("УУ_ЮНИОН".equals(base)) {
				return new UU_UNION_Exchange().getMuleExchangeSoap();
			} else if ("УТ_ЕСН".equals(base)) {
				return new UT_ESN_Exchange().getMuleExchangeSoap();
			} else if ("УУ_СИЛТА".equals(base)) {
				return new UU_SILTA_Exchange().getMuleExchangeSoap();
			} else if ("УУ_ПЕТРОЦЕНТР".equals(base)) {
				return new UU_METAL_Exchange().getMuleExchangeSoap();
			} else if ("УТЦ_ЮНИОН".equals(base)) {
				return new UTC_UNION_Exchange().getMuleExchangeSoap();
			} else if ("СПБ_ЗУП".equals(base)) {
				return new UT_ZUP_Exchange().getMuleExchangeSoap();
			} else if ("ЛМС".equals(base)) {
				return new LMS_Exchange().getMuleExchangeSoap();
			} else if ("БУ_ПСС".equals(base)) {
				return new UU_PSS().getMuleExchangeSoap();
			} else if ("БУ_ЛСК".equals(base)) {
				return new UU_LSK().getMuleExchangeSoap();
			} else if ("СОП_БИЗ".equals(base)) {
				return new UU_SOAP_Exchange().getMuleExchangeSoap();
			}else if ("ЦФС".equals(base)) {
				return new CFS_Exchange().getMuleExchangeSoap();
			}
		} catch (Exception e) {

		}

		return null;
	}
	
	public static BaseWsdlSettings getWsdlSettings() {

		ObjectMapper jsonMapper = new ObjectMapper();

		FileInputStream settings = null;
		BaseWsdlSettings wsdlSettings = null;
		try {
			
			settings = new FileInputStream("/opt/mule/wsdlSettings.json");
			String data = IOUtils.toString(settings, "UTF-8");
			wsdlSettings = jsonMapper.readValue(data, BaseWsdlSettings.class);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				if (settings != null)
					settings.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return wsdlSettings;
	}

	public static BaseWsdlSettings.Base getBaseSettings(String name) {
		return wsdlSettings.getBaseSetting(name);
	}
	
	protected MuleExchangePortType getWsPort(String base)
	{
		if (map.get(base) == null) 
		{
			map.put(base, getService(base));						
		}
		
		if (map.get(base) == null)
			throw new NullPointerException("НЕ НАЙДЕН СЕРВИС ДЛЯ ВЫПОЛНЕНИЯ ЗАПРОСА. БАЗА - " + base);
		
		return map.get(base);
	}
	
	private void setTimeout(int timeout, MuleExchangePortType wsPort) {
		if (timeout == 0)
			timeout = 1 * 60 * 1000;

		((BindingProvider) wsPort).getRequestContext().put("javax.xml.ws.client.connectionTimeout", timeout);
		((BindingProvider) wsPort).getRequestContext().put("javax.xml.ws.client.receiveTimeout", timeout);

	}

	public Object getObject(String base, Object object, int timeout) 
	{
			
		MuleExchangePortType wsPort = getWsPort(base);
		
		setTimeout(timeout, wsPort);
	
		object.getItem().removeIf(item -> !item.getType().equals("request"));
		object.setTime("");
				
		return wsPort.getObject(object);
	};
		
	public Object setObject(String base, Object object, int timeout) 
	{
		MuleExchangePortType wsPort = getWsPort(base);
		
		setTimeout(timeout, wsPort);
		
		Object reply = null;
				
		if(object.getTime() == null || object.getTime().equals(""))
		{
			object.setTime(String.valueOf(System.currentTimeMillis()));
		}
					
		object.setHashKey(DigestUtils.md5Hex(object.getHashKey()));						
		
		List<Object> requests = new ObjectTransformer(object).transform(object.getRecipient());
				
		requests.forEach(request -> wsPort.setObject(request));
				
		return reply;					
	}
	
	public Object getPrintForms(String base, Object object, int timeout)
	{
		MuleExchangePortType wsPort = getWsPort(base);
		
		setTimeout(timeout, wsPort);
		
		return wsPort.getPrintForms(object);
	}

	public Row closeOrder(String base, Row order, int timeout)
	{
		MuleExchangePortType wsPort = getWsPort(base);
		
		setTimeout(timeout, wsPort);
		
		return wsPort.closeOrder(order);
	}
	
	public boolean createPaymentDocument(String base, Item item, int timeout)
	{
		MuleExchangePortType wsPort = getWsPort(base);
		
		setTimeout(timeout, wsPort);
		
		return wsPort.createPaymentDocument(item);		
	}
	
	public boolean createPaymentDocumentFullSum(String base,  Item item, int timeout){
		
		MuleExchangePortType wsPort = getWsPort(base);
		
		setTimeout(timeout, wsPort);
		
		return wsPort.createPaymentDocumentFullSum(item);	
		
	}
	
	public Object calculatePrices(String base, Object object,  int timeout){
		
		MuleExchangePortType wsPort = getWsPort(base);
		
		setTimeout(timeout, wsPort);
		
		return wsPort.calculatePrices(object);	
		
	}
	
	public Object prepareOrderProductTable(String base, Item item, int timeout){
		
		MuleExchangePortType wsPort = getWsPort(base);
		
		setTimeout(timeout, wsPort);
		
		return wsPort.prepareOrderProductTable(item);	
		
	}

	public Object compareItems(String base, Object object, int timeout){
		
		MuleExchangePortType wsPort = getWsPort(base);
		
		setTimeout(timeout, wsPort);
		
		return wsPort.compareItems(object);	
		
	}
	
	public Object getData(String base, Object object, int timeout){
		
		MuleExchangePortType wsPort = getWsPort(base);
		
		setTimeout(timeout, wsPort);
		
		return wsPort.getData(object);	
		
	}
	
	public Object execute(String base, Object object, int timeout){
		
		MuleExchangePortType wsPort = getWsPort(base);
		
		setTimeout(timeout, wsPort);
		
		return wsPort.execute(object);			
	}
	
	public Object getPrintForm(String base, Object object, int timeout)
	{
		MuleExchangePortType wsPort = getWsPort(base);
		
		setTimeout(timeout, wsPort);
		
		return wsPort.getPrintForm(object);	
	}
	
	public Object getMetadata(String base, String type, int timeout){
		
		MuleExchangePortType wsPort = getWsPort(base);
		
		setTimeout(timeout, wsPort);
		
		return wsPort.getMetadata(type);	
	}
	
    public Object getBaseStructure(String base, int timeout)
    {
    	MuleExchangePortType wsPort = getWsPort(base);
		
		setTimeout(timeout, wsPort);
		
		return wsPort.getBaseStructure();	
    }
    
	public List<String> getArrayofCities(String base){
		
		List<String> orgId = new ArrayList<>();
		
		if(organizations.get(base) == null)
		{
			GetBase getBase = new GetBase(base);
			
			Object reply = getData("УТ_СПБ", getBase, 10 * 60 * 1000);
			
			orgId = getBaseId(reply);
			
			if (orgId.size() == 0)
				throw new RuntimeException("Не могу определить базу для получения данных по накладной!!! Проверьте UUID подразделения.");
			
			organizations.put(base, orgId);			
		}
		else
		{
			orgId = organizations.get(base);			
		}
		
		return orgId;
		
	}
	
	private List<String> getBaseId(Object object){
		
		List<String> orgId = new ArrayList<>();
		
		api.exchange.Map map = new api.exchange.Map(object);
		if(map != null && map.NextItem("РезультатЗапроса")){			
			int i = 0;			
			while(map.NextRow(i))
			{
				orgId.add(map.getReference("Организация"));				
				i ++;
			}
		}		
		return orgId;		
	}
	
	
	private static class GetBase extends Object{
		
		public GetBase(String base){
			
			super();
			this.type = "Произвольный запрос";
			
			String request = "ВЫБРАТЬ "
					+ " Т.Объект КАК Организация "
					+ " ИЗ"
					+ " РегистрСведений.mule_СоответствиеОбъектаУчастникуОбмена КАК Т"
					+ " ГДЕ"
					+ " Т.УчастникОбмена.Наименование = &База";
			
			this.item = Arrays.asList(new Item("request", 
					new Row().add("Строка", "ТекстЗапроса", request).add("Строка", "База", base)));
			
		}
	}
	
}
