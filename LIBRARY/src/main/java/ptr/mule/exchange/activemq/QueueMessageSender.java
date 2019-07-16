package ptr.mule.exchange.activemq;

import java.util.UUID;

import javax.inject.Inject;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.xml.soap.SOAPException;

import org.apache.activemq.command.ActiveMQQueue;
import org.mule.transport.jms.activemq.ActiveMQJmsConnector;
import org.springframework.context.ApplicationContext;

import api.exchange.Object;

public class QueueMessageSender {

	private Session session;
	@Inject
	private ApplicationContext context;	
	private ActiveMQJmsConnector mqGet;
	
	public void sendMessage(String queueName, String message) throws JMSException, RuntimeException {
		
		sendMessage(queueName, message, "Domain-MQ");
	}
	
	public void sendToQueue(Object object, String queueName) throws JMSException, RuntimeException {
		
		sendToQueue(object, queueName, "Domain-MQ");
	}
			
	public void sendMessage(String queueName, String message, String ActiveMQBeanName) throws JMSException, RuntimeException {

		if (this.mqGet == null)
			this.mqGet = context.getBean(ActiveMQBeanName, ActiveMQJmsConnector.class);

		if (this.session == null)
			this.session = mqGet.getConnection().createSession(false, Session.AUTO_ACKNOWLEDGE);

		MessageProducer producer = null;
		
		try {

			producer = session.createProducer(new ActiveMQQueue(queueName));

			producer.setDeliveryMode(DeliveryMode.PERSISTENT);

			TextMessage msg = session.createTextMessage(message);

			msg.setJMSType("TEXT");

			msg.setJMSCorrelationID(UUID.randomUUID().toString());

			producer.send(msg);

		} 	
		catch (JMSException e) 
		{
			throw new RuntimeException(e);
		} 
		finally 
		{
			if(producer != null)
				producer.close();
			else
			{
				this.session.close();
				this.session = null;	
			}
				
		}
	}

	public void destroy() throws JMSException {

		this.session.close();
	}

	public void sendToQueue(Object object, String queueName, String ActiveMQBeanName) throws JMSException, RuntimeException {
				
		if (this.mqGet == null)
			this.mqGet = context.getBean(ActiveMQBeanName, ActiveMQJmsConnector.class);

		if (this.session == null)
			this.session = mqGet.getConnection().createSession(false, Session.AUTO_ACKNOWLEDGE);

		MessageProducer producer = null;
		
		try 
		{
			producer = session.createProducer(new ActiveMQQueue(queueName));
			
			producer.setDeliveryMode(DeliveryMode.PERSISTENT);

			ObjectMessage msg = session.createObjectMessage(object);
			
			msg.setJMSType("OBJECT"); 
			
			msg.setJMSCorrelationID(UUID.randomUUID().toString());
			
			producer.send(msg);
		} 
		catch (JMSException e) 
		{
			throw new RuntimeException(e);
		} 
		finally 
		{
			if(producer != null)
				producer.close();
			else
			{
				this.session.close();
				this.session = null;	
			}
				
		}

	}


}
