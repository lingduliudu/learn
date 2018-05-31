package com.zhiwang.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.support.converter.MessageConverter;

import com.zhiwang.redis.RedisService;
import com.zhiwang.utils.SpringContextUtils;

/**
 * 公共队列监听器
 * @author 顾斌
 *
 */
public class CommonMessageListener implements MessageListener{
	
	public static final Logger LOGGER = LoggerFactory.getLogger(CommonMessageListener.class);
	
	// 消息转换器，从配置文件注入
	private MessageConverter messageConverter;
	
	@Autowired
	RedisService redisService;
	
	/**
	 * 监听到消息
	 */
	//@Transactional
	@Override
	public void onMessage(Message message) {
        if (message instanceof TextMessage) {
        	TextMessage textMessage = (TextMessage) message;

            try {
                IntegrationMessage integrationMessage = (IntegrationMessage) messageConverter.fromMessage(textMessage);
                LOGGER.info("接收消息：" + IntegrationMessageUtil.format(integrationMessage));
                
            	// 该消息是否已经被成功处理过，如果是则丢弃该消息
            	if(isDuplicated(textMessage)){
            		LOGGER.warn("重複消息：{}", integrationMessage.toString());
            		return;
            	}
            	
            	// 开始处理消息
                LOGGER.info("开始处理消息：" + IntegrationMessageUtil.format(integrationMessage));
                IMessageListner messageListner = (IMessageListner) SpringContextUtils.getBeanById(integrationMessage.getModelName());
                messageListner.execute(integrationMessage);
                LOGGER.info("处理消息成功：" + IntegrationMessageUtil.format(integrationMessage));
                
                // 将成功消息放入缓存中，默认存放时间2小时
                try{
                	String messageID = "credit:mq:" + textMessage.getJMSMessageID();
                	redisService.set(messageID, messageID, 60*60*24);
                }catch(Throwable e){
                	LOGGER.error("存入缓存失败",e);
                }
            } catch (Exception e) {
            	LOGGER.error("failed to handle message", e);
            	throw new RuntimeException(e);
            }
            
        }
	}
	
    public MessageConverter getMessageConverter() {
        return messageConverter;
    }
 
    public void setMessageConverter(MessageConverter messageConverter) {
        this.messageConverter = messageConverter;
    }
    
    /**
     * 检查该消息是否已经被成功处理过.
     * @param textMessage
     * @return
     */
    public boolean isDuplicated(TextMessage textMessage){
    	boolean isDuplicated = false;
    	boolean JMSRedelivered = false;
    	try{
    		JMSRedelivered = textMessage.getJMSRedelivered();
    	}catch(JMSException e){
    		JMSRedelivered = true;
    	}
    	LOGGER.info("JMSRedelivered:{}", JMSRedelivered);
    	if(JMSRedelivered){
    		String messageID = null;
    		try{
    			messageID = textMessage.getJMSMessageID();
        		String storedMessageId = redisService.get("credit:mq:" + messageID);
        		isDuplicated = storedMessageId == null ? false : true;
    		}catch(Throwable e){
    			return false;
    		}
    	}
    	return isDuplicated;
    }

}
