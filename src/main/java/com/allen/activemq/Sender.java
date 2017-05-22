package com.allen.activemq;

import javax.jms.Message;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Sender extends Configuration{
	public Sender(){
		
	}
	public Sender(String queuename){
		super(queuename);
	}
	public static void main(String[] args) {
		try {
			initQueueName("maxiaojia");
			start();
			initProducer();
			String msg = "hello activemq";
			Message message = session.createTextMessage(msg);
			producer.send(message);
			System.out.println("send message : " + msg);
			close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}

class Receiver extends Configuration{
	private static Logger logger = LoggerFactory.getLogger(Receiver.class);
	public static void main(String[] args) {
		try {
			initQueueName("maxiaojia");
			start();
			initConsumer();
			while(true){
				TextMessage message = (TextMessage)consumer.receive();
				logger.info("consumer received message : " + message.getText());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class Receiver2 extends Configuration{
	private static Logger logger = LoggerFactory.getLogger(Receiver2.class);
	public static void main(String[] args) {
		try {
			initQueueName("maxiaojia");
			start();
			initConsumer();
			while(true){
				TextMessage message = (TextMessage)consumer.receive();
				logger.info("consumer2 received message : " + message.getText());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
