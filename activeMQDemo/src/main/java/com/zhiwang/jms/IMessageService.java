package com.zhiwang.jms;

import javax.jms.Destination;

/**
 * 服务接口
 * @author 顾斌
 *
 */
public interface IMessageService {
	
	/**
	 * 发送消息，发送到公共队列
	 * @param integrationMessage
	 * @return
	 */
	public boolean publish(IntegrationMessage integrationMessage);
	
	/**
	 * 异步发送消息，发送到公共队列
	 * @param integrationMessage
	 * @return
	 */
	boolean publishAsync(IntegrationMessage integrationMessage);
	
	/**
	 * 发送消息，发送到指定队列
	 * @param destination
	 * @param integrationMessage
	 * @return
	 */
	public boolean publish(Destination destination, IntegrationMessage integrationMessage);

	/**
	 * 异步发送消息，发送到指定队列
	 * @param destination
	 * @param integrationMessage
	 * @return
	 */
	boolean publishAsync(Destination destination, IntegrationMessage integrationMessage);

}
