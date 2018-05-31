package com.zhiwang.jms;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


/**
 * 监听API消息
 * @author 顾斌
 *
 */
@Component("API_MODEL")
public class ApiMessageListener implements IMessageListner{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ApiMessageListener.class);

	@Transactional
	@Override
	public void execute(IntegrationMessage integrationMessage) throws RuntimeException {
		LOGGER.info("接收到API消息" + IntegrationMessageUtil.format(integrationMessage));
		
		Map<String,Object> messageBodyMap = null;
		try{
			// 解析数据
			String messageBody = integrationMessage.getMessageBody();
			messageBodyMap.put("", "");

		}catch(Exception e){
			throw new RuntimeException(e);
		}
		
	}

}
