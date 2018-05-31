package com.zhiwang.jms;

import java.io.Serializable;
import java.util.Map;

/**
 * 消息实体
 * @author 顾斌
 *
 */
public class IntegrationMessage implements Serializable {
	
	private static final long serialVersionUID = 9031260148449520482L;

	// 模型名称
	private String modelName;
	
	// 模型版本
	private String modelVersion;
	
	// 消息主键
	private String messageKey;
	
	// 消息体
	private String messageBody;
	
	// 发送端
	private String source;
	
	// 接收端
	private String target;
	
	private Map<String, Object> properties;

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getModelVersion() {
		return modelVersion;
	}

	public void setModelVersion(String modelVersion) {
		this.modelVersion = modelVersion;
	}

	public String getMessageKey() {
		return messageKey;
	}

	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}

	public String getMessageBody() {
		return messageBody;
	}

	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public Map<String, Object> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}

	@Override
	public String toString() {
		return "IntegrationMessage [modelName=" + modelName + ", modelVersion=" + modelVersion + ", messageKey="
				+ messageKey + ", messageBody=" + messageBody + ", source=" + source + ", target=" + target
				+ ", properties=" + properties + "]";
	}
}
