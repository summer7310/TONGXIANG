package org.resource.objectclass;

public class PdPipeBracket {
	private int autoId;
	private Long staticId;
	private String name;
	private Integer objectType;
	private Integer rfid;
	private Integer material;
	private Long wellId;
	private Long mirrorWellId;
	private Integer bracketInterval;
	private String bracketModel;
	private String Builder;
	private Long belongsPpSegment;
	private Integer numbers;
	
	
	public PdPipeBracket() {
		
	}

	public PdPipeBracket(int autoId) {
		this.autoId = autoId;
	}
	
	public PdPipeBracket(int autoId, Long staticId, String name,
			Integer objectType, Integer rfid, Long wellId, Long mirrorWellId,
			Integer bracketInterval, String bracketModel, String builder,
			Long belongsPpSegment, Integer numbers) {
		this.autoId = autoId;
		this.staticId = staticId;
		this.name = name;
		this.objectType = objectType;
		this.rfid = rfid;
		this.wellId = wellId;
		this.mirrorWellId = mirrorWellId;
		this.bracketInterval = bracketInterval;
		this.bracketModel = bracketModel;
		Builder = builder;
		this.belongsPpSegment = belongsPpSegment;
		this.numbers = numbers;
	}
	
	public int getAutoId() {
		return autoId;
	}
	public void setAutoId(int autoId) {
		this.autoId = autoId;
	}
	public Long getStaticId() {
		return staticId;
	}
	public void setStaticId(Long staticId) {
		this.staticId = staticId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getObjectType() {
		return objectType;
	}
	public void setObjectType(Integer objectType) {
		this.objectType = objectType;
	}
	public Integer getRfid() {
		return rfid;
	}
	public void setRfid(Integer rfid) {
		this.rfid = rfid;
	}
	public Long getWellId() {
		return wellId;
	}
	public void setWellId(Long wellId) {
		this.wellId = wellId;
	}
	public Long getMirrorWellId() {
		return mirrorWellId;
	}
	public void setMirrorWellId(Long mirrorWellId) {
		this.mirrorWellId = mirrorWellId;
	}
	public Integer getBracketInterval() {
		return bracketInterval;
	}
	public void setBracketInterval(Integer bracketInterval) {
		this.bracketInterval = bracketInterval;
	}
	public String getBracketModel() {
		return bracketModel;
	}
	public void setBracketModel(String bracketModel) {
		this.bracketModel = bracketModel;
	}
	public String getBuilder() {
		return Builder;
	}
	public void setBuilder(String builder) {
		Builder = builder;
	}
	public Long getBelongsPpSegment() {
		return belongsPpSegment;
	}
	public void setBelongsPpSegment(Long belongsPpSegment) {
		this.belongsPpSegment = belongsPpSegment;
	}
	public Integer getNumbers() {
		return numbers;
	}
	public void setNumbers(Integer numbers) {
		this.numbers = numbers;
	}
	public void setMaterial(Integer material){
		this.material = material;
	}
	public Integer getMaterial(){
		return  material;
	}
	
	
}
