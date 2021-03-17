
import ru.vyazankin.common.dto.ProductDto;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.io.IOException;
import java.util.Properties;

public class JmsClient {


    public static void main(String[] args) throws IOException, NamingException {
        Context context = createInitialContext();

        ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("jms/RemoteConnectionFactory");
        JMSContext jmsContext = connectionFactory.createContext("jms", "jmsjms_1");
        //<secret value="am1zam1zXzE=" />
        Destination destination = (Destination) context.lookup("jms/productQueue");

        JMSProducer producer = jmsContext.createProducer();

//        private Long id;
//        private String name;
//        private String description;
//        private Long price;
//        private Long categoryId;
//        private String categoryName;

        ObjectMessage om = jmsContext.createObjectMessage(
                new ProductDto(
                    null,
                    "Колонки настольные",
                    "Колонки настольные компактные",
                    1500L,
                    4L,
                    null)
            );

        producer.send(destination, om);
    }

    public static Context createInitialContext() throws IOException, NamingException {
        final Properties env = new Properties();
        env.load(JmsClient.class.getClassLoader().getResourceAsStream("wildfly-jndi.properties"));
        return new InitialContext(env);
    }
}

