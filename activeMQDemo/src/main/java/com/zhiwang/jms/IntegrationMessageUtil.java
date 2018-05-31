package com.zhiwang.jms;

import java.text.MessageFormat;

/**
 * 消息工具类
 * @author 顾斌
 *
 */
public class IntegrationMessageUtil {

	/**
	 * 格式化消息
	 * @param integrationMessage
	 * @return
	 */
	public final static String format(IntegrationMessage integrationMessage) {
		return MessageFormat.format("[modelName:{0}, modelVersion:{1}, messageKey={2}]",
				new Object[] { integrationMessage.getModelName(), integrationMessage.getModelVersion(),
						integrationMessage.getMessageKey() });
	}

}
