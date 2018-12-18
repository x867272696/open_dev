package com.htl.cloudmemory.util.coderobot.bean;

/**
 * 
 * @author htl
 * @since CodeRobot 1.0
 */
public class Model {
	
	private String modelName;
	private String modelData;
	
	public Model(String modelName,String modelData) {
		this.modelName=modelName;
		this.modelData=modelData;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getModelData() {
		return modelData;
	}

	public void setModelData(String modelData) {
		this.modelData = modelData;
	}

}
