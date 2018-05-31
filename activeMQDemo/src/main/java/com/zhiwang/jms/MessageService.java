package com.zhiwang.jms;

import javax.jms.Destination;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 消息服务
 * @author 顾斌
 *
 */
@Service
public class MessageService implements IMessageService {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(MessageService.class);
	
	@Autowired
	@Qualifier("jmsTemplate")
    private JmsTemplate jmsTemplate;
	
    @Autowired
    @Qualifier("commonQueueDestination")
    private Destination commonQueueDestination;
    
    @Autowired  
    @Qualifier("taskExecutor")  
    private TaskExecutor  taskExecutor;  

    /**
     * 发送消息到公共队列
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public boolean publish(final IntegrationMessage integrationMessage) {
		LOGGER.info("发送消息：" + IntegrationMessageUtil.format(integrationMessage));
		boolean flag = true;

		try{
			jmsTemplate.convertAndSend(commonQueueDestination, integrationMessage); 
		}catch(Exception e){
			LOGGER.error("发送消息失败" + IntegrationMessageUtil.format(integrationMessage), e);
			flag = false;
		}
		return flag;
	}
    
    /**
     * 发送消息到公共队列
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public boolean publishAsync(final IntegrationMessage integrationMessage) {
		LOGGER.info("发送消息：" + IntegrationMessageUtil.format(integrationMessage));
		boolean flag = true;
		taskExecutor.execute(new Runnable() {
			@Override
			public void run() {
				try{
					jmsTemplate.convertAndSend(commonQueueDestination, integrationMessage); 
				}catch(Exception e){
					LOGGER.error("发送消息失败" + IntegrationMessageUtil.format(integrationMessage), e);
				}
			}
		});
		return flag;
	}

	/**
	 * 发送消息到指定队列
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean publish(final Destination destination, final IntegrationMessage integrationMessage) {
		LOGGER.info("发送消息：" + IntegrationMessageUtil.format(integrationMessage));
		boolean flag = true;

		try{
			jmsTemplate.convertAndSend(destination, integrationMessage); 
		}catch(Exception e){
			LOGGER.error("发送消息失败" + IntegrationMessageUtil.format(integrationMessage), e);
			flag = false;
		}

		return flag;
	}
	
	/**
	 * 发送消息到指定队列
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean publishAsync(final Destination destination, final IntegrationMessage integrationMessage) {
		LOGGER.info("发送消息：" + IntegrationMessageUtil.format(integrationMessage));
		boolean flag = true;
		taskExecutor.execute(new Runnable() {
			@Override
			public void run() {
				try{
					jmsTemplate.convertAndSend(destination, integrationMessage); 
				}catch(Exception e){
					LOGGER.error("发送消息失败" + IntegrationMessageUtil.format(integrationMessage), e);
				}
			}
		});
		return flag;
	}

}
