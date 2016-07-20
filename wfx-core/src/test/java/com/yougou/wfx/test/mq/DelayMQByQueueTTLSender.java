
package com.yougou.wfx.test.mq;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.QueueingConsumer.Delivery;
import com.yougou.tools.common.utils.DatetimeUtil;
import com.yougou.wfx.order.dto.output.OrderInfoDto;

/**
 * rabbitMq延迟消息测试(基于Queue TTL)
 * @author wuyang
 */
public class DelayMQByQueueTTLSender {
	
	// 工作队列
	private final static String WORK_QUEUE = "wfx.queue_ttl.work.queue";
	// 延迟队列
	private final static String DELAP_QUEUE = "wfx.queue_ttl.2minute.delay.queue";

	public static void main(String[] args) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("10.0.30.187");
		factory.setPort(5672);
		factory.setVirtualHost("/");

		Connection connection = null;
		Channel channel = null;
		try {
			connection = factory.newConnection();
			channel = connection.createChannel();

			// 工作queue
			channel.queueDeclare(WORK_QUEUE, true, false, false, null);
			channel.queueBind(WORK_QUEUE, "amq.direct", WORK_QUEUE);
			
			// 延迟过期queue
			HashMap<String, Object> delayQueuearguments = new HashMap<String, Object>();
			delayQueuearguments.put("x-expires", 2*60*1000);// 2分钟
			channel.queueDeclare(DELAP_QUEUE, true, false, false, delayQueuearguments);

			MessageProperties messageProperties = new MessageProperties();
			messageProperties.setContentType(MessageProperties.CONTENT_TYPE_JSON);
			messageProperties.setType(String.class.getName());
			
			MessageConverter converter = new JsonMessageConverter();
			OrderInfoDto vo = new OrderInfoDto();
			vo.setWfxOrderNo("W6409231203231");
			Message msg = converter.toMessage(vo, messageProperties);

			Map<String,Object> headersMap = new HashMap<String,Object>();
			headersMap.put("__TypeId__", OrderInfoDto.class.getName());
			
			AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties.Builder();
			AMQP.BasicProperties properties = builder
					.contentType(MessageProperties.CONTENT_TYPE_JSON)
					.deliveryMode(2)
					.headers(headersMap).build();

			channel.basicPublish("",DELAP_QUEUE, properties,msg.getBody());
			System.out.println(DatetimeUtil.getCurrentDate(DatetimeUtil.LONG_DATE_TIME_PATTERN) + ",send message[" + msg + "] to " + DELAP_QUEUE);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			try {
//				if (channel != null) {
//					channel.close();
//				}
//				if (connection != null) {
//					connection.close();
//				}
//			} catch (Exception e2) {
//				e2.printStackTrace();
//			}
		}
		
		// 监听收取工作队列消息
		start(channel);
	}
	
	private static void start(Channel channel) throws Exception{
        QueueingConsumer consumer = new QueueingConsumer(channel);  
        channel.basicConsume(WORK_QUEUE, true, consumer);  
        while(true){
            Delivery delivery = consumer.nextDelivery();
            String msg = new String(delivery.getBody());
            System.out.println(DatetimeUtil.getCurrentDate(DatetimeUtil.LONG_DATE_TIME_PATTERN) + "received message[" + msg + "] from " + DELAP_QUEUE);
        }
	}
}
