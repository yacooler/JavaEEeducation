import ru.vyazankin.common.services.ProductServiceRemote;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.util.Properties;

public class MainApp {


    public static void main(String[] args) throws IOException, NamingException {
        Context context = createInitialContext();

        String jndiServiceName = "ejb:/first-jsf-app/ProductServiceImpl!ru.vyazankin.common.services.ProductServiceRemote";
        ProductServiceRemote productService = (ProductServiceRemote) context.lookup(jndiServiceName);

        productService.findAll()
                .forEach(prod -> System.out.println(prod.getId() + "\t" + prod.getName() + "\t" + prod.getPrice()));
    }

    public static Context createInitialContext() throws IOException, NamingException {
        final Properties env = new Properties();
        env.load(MainApp.class.getClassLoader().getResourceAsStream("wildfly-jndi.properties"));
        return new InitialContext(env);
    }
}
