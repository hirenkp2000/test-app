package com.test.app;

import java.util.Map;

public class DependencyItem {
	protected String name;
	protected int majorVersion;
	protected int minorVersion;
	protected int patchVersion;
	protected Map<String, DependencyItem> transMap;

	public DependencyItem(String name, int majorVersion, int minorVersion, int patchVersion,
			Map<String, DependencyItem> transMap) {
		super();
		this.name = name;
		this.majorVersion = majorVersion;
		this.minorVersion = minorVersion;
		this.patchVersion = patchVersion;
		this.transMap = transMap;
	}
	
	
	public DependencyItem(String name, int majorVersion, int minorVersion, int patchVersion) {
		super();
		this.name = name;
		this.majorVersion = majorVersion;
		this.minorVersion = minorVersion;
		this.patchVersion = patchVersion;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMajorVersion() {
		return majorVersion;
	}
	public void setMajorVersion(int majorVersion) {
		this.majorVersion = majorVersion;
	}
	public int getMinorVersion() {
		return minorVersion;
	}
	public void setMinorVersion(int minorVersion) {
		this.minorVersion = minorVersion;
	}
	public int getPatchVersion() {
		return patchVersion;
	}
	public void setPatchVersion(int patchVersion) {
		this.patchVersion = patchVersion;
	}
	public Map<String, DependencyItem> getTransMap() {
		return transMap;
	}
	public void setTransMap(Map<String, DependencyItem> transMap) {
		this.transMap = transMap;
	}


	@Override
	public String toString() {
		return "DependencyItem [name=" + name + ", majorVersion=" + majorVersion + ", minorVersion=" + minorVersion
				+ ", patchVersion=" + patchVersion + ", transMap=" + transMap + "]";
	}
	
}
