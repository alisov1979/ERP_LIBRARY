package api.exchange;

public enum EnumExchangeParticipants {
		
	UT_SPB("��_���"), 
	UT_ESN("��_���"), 
	UT_SZFO("��_����"),
	UT_UNION("��_�����"),
	UT_MSK("��_���"),
	UU_UNION("��_�����");	

	private final String baseName;
	
	EnumExchangeParticipants(String basename){
		this.baseName = basename;
	};
	
	public String getBaseName(){		
		return baseName;		
	}


}
