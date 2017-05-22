package com.allen.activemq;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Configuration {

	protected static String queuename = "maxiaojia";
	protected static String username = ActiveMQConnectionFactory.DEFAULT_USER;
	protected static String password = ActiveMQConnectionFactory.DEFAULT_PASSWORD;
	protected static String brokerURL = ActiveMQConnectionFactory.DEFAULT_BROKER_BIND_URL;
	protected static ActiveMQConnectionFactory factory = null;
	protected static Connection connection = null;
	protected static Session session = null;
	protected static Destination queue = null;
	protected static MessageProducer producer = null;
	protected static MessageConsumer consumer = null;

	public Configuration() {
		
	}

	public Configuration(String queuename) {
		this.queuename = queuename;
	}
	
	public static void start() {
		try {
			factory = new ActiveMQConnectionFactory(username, password, "tcp://localhost:61616");
//			factory = new ActiveMQConnectionFactory();
			connection = factory.createConnection();
			connection.start();
			/* 1.是否持久化；2.回执方式 */
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	public static void initQueueName(String queueame){
		Configuration.queuename = queuename;
	}
	public static void initProducer(){
		try {
			queue = null == queue ? session.createTopic(queuename) : queue;
			producer = null == producer ? session.createProducer(queue) : producer;
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	public static void initConsumer(){
		try {
			queue = null == queue ? session.createTopic(queuename) : queue;
			consumer = null == consumer ? session.createConsumer(queue) : consumer;
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	public static void close() {
		if (null != session) {
			try {
				session.close();
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
		if (null != connection) {
			try {
				connection.close();
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}
}
