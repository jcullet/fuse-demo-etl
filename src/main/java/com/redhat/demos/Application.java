package com.redhat.demos;

import org.apache.activemq.artemis.jms.client.ActiveMQJMSConnectionFactory;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.spring.boot.CamelSpringBootApplicationController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class Application {

  @Bean
  public ActiveMQJMSConnectionFactory artemisConnectionFactory() {
    return new ActiveMQJMSConnectionFactory("tcp://broker-amq-tcp.fuse-demo.svc:61616");
  }

  @Bean
  public JmsComponent jms() {
    JmsComponent jmsComponent = new JmsComponent();
    jmsComponent.setConnectionFactory(artemisConnectionFactory());
    return jmsComponent;
  }

  public static void main(String[] args) {
    ApplicationContext applicationContext = new SpringApplication(Application.class).run(args);
    CamelSpringBootApplicationController applicationController =
            applicationContext.getBean(CamelSpringBootApplicationController.class);
    applicationController.blockMainThread();
  }
}