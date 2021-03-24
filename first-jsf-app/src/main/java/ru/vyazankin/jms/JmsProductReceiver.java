package ru.vyazankin.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vyazankin.common.dto.ProductDto;
import ru.vyazankin.services.ProductService;

import javax.annotation.PostConstruct;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;


@MessageDriven(
    activationConfig = {
            @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
            @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:jboss/exported/jms/productQueue")
    })
public class JmsProductReceiver implements MessageListener {

    @EJB
    private ProductService productService;

    private Logger logger;

    @PostConstruct
    private void init(){
        logger = LoggerFactory.getLogger(JmsProductReceiver.class);
        logger.info("Логгер создан");
    }


    @Override
    public void onMessage(Message message) {
        logger.info("Получено новое сообщение");

        if (message instanceof ObjectMessage){
            try {
                productService.saveOrUpdate((ProductDto) ((ObjectMessage) message).getObject());
            } catch (JMSException e) {
                throw new RuntimeException("Не удалось привести message к ProductDto", e);
            }
        }

    }
}
