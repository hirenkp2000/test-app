package com.test.app;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class DepTest {
	
	private static Logger logger = LoggerFactory.getLogger(DepTest.class);
	
	public void run() {
		// Input Map
		final Map<String, DependencyItem> inDepMap = prepareInputMap();
		final Map<String, DependencyItem> outMap = new HashMap<>();
		
		// Identify dependencies and add to output list
		populateOutputMap(inDepMap, outMap);
		
		// Print output list
		printOutputMap(outMap);
	}

	
	
	protected void populateOutputMap(final Map<String, DependencyItem> inMap, final Map<String, DependencyItem> outMap) {
		inMap.forEach((name, depItem) -> {
			// add this dependency into output (if not already there)
			outMap.compute(name, (k,v) -> {
				if (v==null
						|| depItem.getMajorVersion() > v.getMajorVersion()
						|| ( depItem.getMajorVersion() == v.getMajorVersion() && depItem.getMinorVersion() > v.getMinorVersion() ) 
						|| ( depItem.getMajorVersion() == v.getMajorVersion() && depItem.getMinorVersion() == v.getMinorVersion() && depItem.getPatchVersion() > v.getPatchVersion() ) 
						) {
					return depItem;
				}
				else return v;
			});
			
			// and then add all transitive dependencies (recursively)
			if (depItem.getTransMap()!=null) {
				populateOutputMap(depItem.getTransMap(), outMap);
			}
		});
	}

	
	
	protected void printOutputMap(final Map<String, DependencyItem> outMap) {
		outMap.forEach((name, depItem) -> {
			logger.info("Dependency => {}, v{}.{}.{}", depItem.getName(), depItem.getMajorVersion(), depItem.getMinorVersion(), depItem.getPatchVersion());
		});
	}
	
	
	
	protected Map<String, DependencyItem> prepareInputMap() {
		final Map<String, DependencyItem> depMap = new TreeMap<>();

		DependencyItem dep1 = new DependencyItem("First", 1, 0, 0);
		{
			DependencyItem dep1_1 = new DependencyItem("Trans 01", 1, 1, 0);
				DependencyItem dep1_1_1 = new DependencyItem("Trans 01.01", 1, 1, 1);   // This will get replaced by v2.1.1 (due to "Second" => "Trans 01.01")
				Map<String, DependencyItem> dep1_1Map = new TreeMap<>();
				dep1_1Map.put(dep1_1_1.getName(), dep1_1_1);
				dep1_1.setTransMap(dep1_1Map);
	
			DependencyItem dep1_2 = new DependencyItem("Trans 02", 1, 2, 0);  // This will get replaced by v2.2.0 (due to "Second" => "Trans 02")
				DependencyItem dep1_2_1 = new DependencyItem("Trans 02.01", 1, 2, 1);
				Map<String, DependencyItem> dep1_2Map = new TreeMap<>();
				dep1_2Map.put(dep1_2_1.getName(), dep1_2_1);
				dep1_2.setTransMap(dep1_2Map);

			Map<String, DependencyItem> dep1_Map = new TreeMap<>();
			dep1_Map.put(dep1_1.getName(), dep1_1);
			dep1_Map.put(dep1_2.getName(), dep1_2);
			dep1.setTransMap(dep1_Map);
		}

		DependencyItem dep2 = new DependencyItem("Second", 2, 0, 0);
		{
			DependencyItem dep2_1 = new DependencyItem("Trans 01", 1, 1, 0);
				DependencyItem dep2_1_1 = new DependencyItem("Trans 01.01", 2, 1, 1);
				Map<String, DependencyItem> dep2_1Map = new TreeMap<>();
				dep2_1Map.put(dep2_1_1.getName(), dep2_1_1);
				dep2_1.setTransMap(dep2_1Map);
	
			DependencyItem dep2_2 = new DependencyItem("Trans 02", 2, 2, 0);
				DependencyItem dep2_2_1 = new DependencyItem("Trans 02.01", 1, 2, 0);  // This will get replaced by v1.2.1 (due to "First" => "Trans 02.01")
				Map<String, DependencyItem> dep2_2Map = new TreeMap<>();
				dep2_2Map.put(dep2_2_1.getName(), dep2_2_1);
				dep2_2.setTransMap(dep2_2Map);
	
			Map<String, DependencyItem> dep2_Map = new TreeMap<>();
			dep2_Map.put(dep2_1.getName(), dep2_1);
			dep2_Map.put(dep2_2.getName(), dep2_2);
			dep2.setTransMap(dep2_Map);
		}

		// Add all dependencies to final Tree
		depMap.put(dep1.getName(), dep1);
		depMap.put(dep2.getName(), dep2);

		return depMap;
	}
	
}
