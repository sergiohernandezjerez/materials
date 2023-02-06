package materials;

public class Materials {

	private String name;
	private String materialId;
	private boolean sostenible;
	private String materialBaseId;
	private int porcentaje;
	private String title;
	
	public Materials() {
		
	}
	
	

	public Materials(String name, String materialId, boolean sostenible, String materialBaseId, int porcentaje,
			String title) {
		this.name = name;
		this.materialId = materialId;
		this.sostenible = sostenible;
		this.materialBaseId = materialBaseId;
		this.porcentaje = porcentaje;
		this.title = title;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMaterialId() {
		return materialId;
	}

	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}

	public boolean getSostenible() {
		return sostenible;
	}

	public void setSostenible(boolean sostenible) {
		this.sostenible = sostenible;
	}

	public String getMaterialBaseId() {
		return materialBaseId;
	}

	public void setMaterialBaseId(String materialBaseId) {
		this.materialBaseId = materialBaseId;
	}

	public int getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(int porcentaje) {
		this.porcentaje = porcentaje;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Materials [name=" + name + ", materialId=" + materialId + ", sostenible=" + sostenible
				+ ", materialBaseId=" + materialBaseId + ", porcentaje=" + porcentaje + ", title=" + title + "]";
	}

	
	
	
}
