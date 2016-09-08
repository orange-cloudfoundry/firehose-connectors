package com.orange.oss.cloudfoundry.nozzle.config;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.cloudfoundry.dropsonde.events.Envelope;
import org.cloudfoundry.dropsonde.events.Envelope.EventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.cloudbees.syslog.Facility;
import com.cloudbees.syslog.MessageFormat;
import com.cloudbees.syslog.Severity;
import com.cloudbees.syslog.sender.AbstractSyslogMessageSender;
import com.cloudbees.syslog.sender.TcpSyslogMessageSender;
import com.cloudbees.syslog.sender.UdpSyslogMessageSender;
import com.orange.oss.cloudfoundry.nozzle.Publisher;

@Component
public class SyslogPublisher implements Publisher {
	
	private static Logger logger=LoggerFactory.getLogger(SyslogPublisher.class.getName());

	@Value("${syslog.host}")	
	private String host;
	
	@Value("${syslog.port}")	
	private int port;
	
	@Value("${syslog.tcp}")
	boolean tcp;
	
	private AbstractSyslogMessageSender messageSender;
	

	
	/**
	 * publish events to backend
	 * @param env
	 */
	
	public SyslogPublisher(){

	}
	
	
	@PostConstruct
	public void init(){
		if (tcp){
			TcpSyslogMessageSender messageSender=new TcpSyslogMessageSender();
			//messageSender.setDefaultMessageHostName("myhostname"); // some syslog cloud services may use this field to transmit a secret key
			messageSender.setDefaultAppName("myapp");
			messageSender.setDefaultFacility(Facility.USER);
			messageSender.setDefaultSeverity(Severity.INFORMATIONAL);
			messageSender.setSyslogServerHostname(this.host);
			messageSender.setSyslogServerPort(this.port);
			messageSender.setMessageFormat(MessageFormat.RFC_3164); // optional, default is RFC 3164
			this.messageSender=messageSender;
		}else {
			UdpSyslogMessageSender messageSender=new UdpSyslogMessageSender();
			//messageSender.setDefaultMessageHostName("myhostname"); // some syslog cloud services may use this field to transmit a secret key
			messageSender.setDefaultAppName("myapp");
			messageSender.setDefaultFacility(Facility.USER);
			messageSender.setDefaultSeverity(Severity.INFORMATIONAL);
			messageSender.setSyslogServerHostname(this.host);
			messageSender.setSyslogServerPort(this.port);
			messageSender.setMessageFormat(MessageFormat.RFC_3164); // optional, default is RFC 3164
			this.messageSender=messageSender;
		}
		
	}
	
	
	@Override
	public void publishNozzleToConnector(Envelope env){
		
		EventType eventType=env.eventType;
		switch (eventType){
		case ContainerMetric:
			break;
		case CounterEvent:
			break;
		case Error:
			break;
		case HttpStart:
			break;
		case HttpStartStop:
			break;
		case HttpStop:
			break;
		case LogMessage:{
			// send a Syslog message
			
			try {
				this.messageSender.sendMessage(env.logMessage.message.toString());
			} catch (IOException e) {
				logger.error("Failure sending log. {}",e);
			}
		}
			break;
		case ValueMetric:
			break;
		default:
			break;
		}
	}
	
	
}
