package com.zhiwang.jms;

/**
 * 消息监听器接口
 * @author 顾斌
 *
 */
public interface IMessageListner {
	
	/**
	 * 执行消息
	 * @param integrationMessage
	 * @throws RuntimeException
	 */
	public void execute(IntegrationMessage integrationMessage) throws RuntimeException;

}
