package materials;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.omg.CosNaming._BindingIteratorImplBase;

public class MaterialsUse {

	public static void main(String[] args) {
		
		List<Materials> materialList = createMaterials();
		Map<String, List<Materials>> materialMap = new HashMap<>(); 
		
		for(Materials material:materialList) {
			List<Materials> listMaterials = new ArrayList<>();
			if(!materialMap.containsKey(material.getTitle())) {
				listMaterials.add(material);
				materialMap.put(material.getTitle(), listMaterials);
			}else {
				materialMap.get(material.getTitle()).add(material);
			}
		}
		
		Map<String, List<Materials>> materialCalculado = materialCalculado(materialMap); 
		
		for(String clave: materialCalculado.keySet()) {
			System.out.println("---------------------");
			System.out.println(clave);
			for(Materials material:materialCalculado.get(clave)) {
				System.out.println("Name: " + material.getName());
				System.out.println("MaterialId: " + material.getMaterialId());
				System.out.println("Sostenible: " + material.getSostenible());
				System.out.println("MaterialBaseId: " + material.getMaterialBaseId());
				System.out.println("Porcentaje: " + material.getPorcentaje());
			}
			System.out.println("***********************");
			
		}
		

	}
	
	private static Map<String, List<Materials>> materialCalculado(Map<String, List<Materials>> materialMap) {
		List<Materials> materialsIgualados = new ArrayList<>();
		List<String> materialId = new ArrayList<>();
		for(String clave:materialMap.keySet()) {
			if(esSostenible(materialMap.get(clave))) {
				//System.out.println("Tiene material sostenible: " + esSostenible(materialMap.get(clave)));
				materialId = getMaterialBaseId(materialMap.get(clave));
				//System.out.println("Material base Id: " + materialId);
				materialsIgualados = igualarMateiralBase(materialMap.get(clave), materialId);

				materialMap.put(clave, materialsIgualados);
			}
		}
	
		return materialMap;
	}

	private static List<String> getMaterialBaseId(List<Materials> list) {
		String materialBaseId = null;
		List<String> materialSostenible = new ArrayList<>();
		for(Materials materials:list) {
			if(materials.getSostenible()) {
				materialBaseId = materials.getMaterialBaseId() + "," + materials.getMaterialId();
			}else {
				materialBaseId = null;
			}
			if(materialBaseId != null) {
				materialSostenible.add(materialBaseId);
			}
			
		}
		return materialSostenible;
	}

	private static List<Materials> igualarMateiralBase(List<Materials> list, List<String> materialId) {
		List<Materials> igualar = new ArrayList<>(); 
		
		for(String ids:materialId) {
			Materials newMaterials = new Materials();
			int suma = 0;
			boolean tieneBase = false;
			String materialBaseId = ids.split(",")[0];
			String materialSostenibleId = ids.split(",")[1];
			
			for(Materials materials:list) {
				
				if(materials.getMaterialId() != null && materials.getMaterialId().equals(materialBaseId)) {
					tieneBase = true;
					newMaterials.setName(materials.getName());
					newMaterials.setMaterialId(materials.getMaterialId());
					newMaterials.setTitle(materials.getTitle());
					newMaterials.setSostenible(false);
					suma = suma + materials.getPorcentaje();
				}else if(materials.getMaterialBaseId() != null && 
						tieneBase && 
						materials.getMaterialBaseId().equals(materialBaseId)) {
						suma = suma + materials.getPorcentaje();
				}else if(materials.getMaterialId() != null &&
						!tieneBase &&
							materials.getMaterialId().equals(materialSostenibleId) &&
							materials.getSostenible()) {
						newMaterials.setName("Nombre Material base");
						newMaterials.setMaterialId(materials.getMaterialBaseId());
						newMaterials.setTitle(materials.getTitle());
						newMaterials.setSostenible(false);
						newMaterials.setPorcentaje(materials.getPorcentaje());
				}else{
					newMaterials = new Materials(materials.getName(), 
							materials.getMaterialId(), 
							materials.getSostenible(), 
							materials.getMaterialBaseId(), 
							materials.getPorcentaje(), 
							materials.getTitle());
				}
				if(tieneBase) {
					newMaterials.setPorcentaje(suma);
				}
				igualar.add(newMaterials);
			}
		}
		
		
		
		//return igualar;
		return eliminarDuplicados(igualar);
	}

	private static List<Materials> eliminarDuplicados(List<Materials> igualar) {
		List<Materials> sinDuplicados = new ArrayList<>();
		List<String> materialId = new ArrayList<>();
		for(Materials mat:igualar) {
			if(!materialId.contains(mat.getMaterialId()) && !mat.getSostenible()) {
				sinDuplicados.add(mat);
				materialId.add(mat.getMaterialId());
			}
		}
		return sinDuplicados;
	}

	private static boolean esSostenible(List<Materials> list) {
		boolean esSostenible = false;
		for(Materials materials:list) {
			if(materials.getSostenible()) {
				esSostenible = true;
			}
		}
		return esSostenible;
	}

	public static List<Materials> createMaterials() {
		List<Materials> listMaterials = new ArrayList<>();
		Materials material1 = new Materials();
		material1.setTitle("Cinturon");
		//material1.setMaterialBaseId("200");
		material1.setMaterialId("100");
		material1.setName("Algodon");
		material1.setPorcentaje(50);
		material1.setSostenible(false);
		listMaterials.add(material1);
		
		Materials material2 = new Materials();
		material2.setTitle("Cinturon");
		material2.setMaterialBaseId("100");
		material2.setMaterialId("101");
		material2.setName("Algodon reciclado");
		material2.setPorcentaje(25);
		material2.setSostenible(true);
		listMaterials.add(material2);
		
		Materials material3 = new Materials();
		material3.setTitle("Cinturon");
		material3.setMaterialBaseId("100");
		material3.setMaterialId("102");
		material3.setName("Algodon organico");
		material3.setPorcentaje(25);
		material3.setSostenible(true);
		listMaterials.add(material3);
		
		Materials material11 = new Materials();
		material11.setTitle("Cinturon");
		material1.setMaterialBaseId("260");
		material11.setMaterialId("108");
		material11.setName("Pelo reciclado");
		material11.setPorcentaje(50);
		material11.setSostenible(true);
		listMaterials.add(material11);
		
		Materials material4 = new Materials();
		material4.setTitle("Bata");
		//material4.setMaterialBaseId("200");
		material4.setMaterialId("300");
		material4.setName("Poliester");
		material4.setPorcentaje(50);
		material4.setSostenible(false);
		listMaterials.add(material4);
		
		Materials material5 = new Materials();
		material5.setTitle("Bata");
		//material5.setMaterialBaseId("200");
		material5.setMaterialId("400");
		material5.setName("Alpaca");
		material5.setPorcentaje(50);
		material5.setSostenible(false);
		listMaterials.add(material5);
		
		Materials material51 = new Materials();
		material51.setTitle("Bata");
		material51.setMaterialBaseId("800");
		material51.setMaterialId("401");
		material51.setName("Pluma pato");
		material51.setPorcentaje(99);
		material51.setSostenible(true);
		listMaterials.add(material51);
		
		Materials material511 = new Materials();
		material511.setTitle("Bata");
		material511.setMaterialBaseId("700");
		material511.setMaterialId("402");
		material511.setName("Pelo reciclado");
		material511.setPorcentaje(75);
		material511.setSostenible(true);
		listMaterials.add(material511);
		
		Materials material6 = new Materials();
		material6.setTitle("Pantalon");
		//material6.setMaterialBaseId("200");
		material6.setMaterialId("500");
		material6.setName("Lana");
		material6.setPorcentaje(50);
		material6.setSostenible(false);
		listMaterials.add(material6);
		
		Materials material7 = new Materials();
		material7.setTitle("Pantalon");
		material7.setMaterialBaseId("500");
		material7.setMaterialId("501");
		material7.setName("Lana organica");
		material7.setPorcentaje(15);
		material7.setSostenible(true);
		listMaterials.add(material7);
		
//		Materials material8 = new Materials();
//		material8.setTitle("Pantalon");
//		material8.setMaterialBaseId("500");
//		material8.setMaterialId("502");
//		material8.setName("Lana reciclada");
//		material8.setPorcentaje(35);
//		material8.setSostenible(true);
//		listMaterials.add(material8);
//		
//		Materials material9 = new Materials();
//		material9.setTitle("Pantalon");
//		//material9.setMaterialBaseId("200");
//		material9.setMaterialId("600");
//		material9.setName("Poliester");
//		material9.setPorcentaje(100);
//		material9.setSostenible(false);
//		listMaterials.add(material9);
		
		return listMaterials;
	}

}
