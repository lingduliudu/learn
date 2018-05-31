package com.zhiwang.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

import com.zhiwang.utils.JSONUtils;



/**
 * 公共消息转换器
 * @author 顾斌
 *
 */
public class IntegrationMessageConverter implements MessageConverter {

	public static final Logger LOGGER = LoggerFactory.getLogger(IntegrationMessageConverter.class);
	 
    public Message toMessage(Object object, Session session)
            throws JMSException, MessageConversionException {
    	
    	IntegrationMessage integrationMessage = (IntegrationMessage) object;
    	
    	TextMessage message = session.createTextMessage();
    	try {
			message.setText(JSONUtils.objectToJson(integrationMessage));
			message.setJMSMessageID(integrationMessage.getMessageKey());
		} catch (Exception e) {
			LOGGER.error("Failed to convert message", e);
			throw new MessageConversionException("Failed to convert message", e);
		}
        return message;
    }
 
    public Object fromMessage(Message message) throws JMSException,
            MessageConversionException {
    	TextMessage textMessage = (TextMessage) message;
    	
    	IntegrationMessage integrationMessage = null;
		try {
			integrationMessage = JSONUtils.jsonToObject(textMessage.getText(), IntegrationMessage.class);
		} catch (Exception e) {
			LOGGER.error("Failed to convert message", e);
			throw new MessageConversionException("Failed to convert message", e);
		}
    	
        return integrationMessage;
    }

}
