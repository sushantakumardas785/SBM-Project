package in.ashokit;

import com.fasterxml.jackson.databind.ObjectMapper;
import in.ashokit.dto.Customer;

import java.io.File;
import java.util.Arrays;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws Exception {
        App a = new App();
        // a.convertJavaToJson();
        a.convertJsonToJava();
    }

    public void convertJsonToJava() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Customer[] customers = mapper.readValue(new File("customer.json"), Customer[].class);
        for(Customer c : customers){
            System.out.println(c);
        }
    }

    public void convertJavaToJson() throws Exception {

        Customer c1 = new Customer();
        c1.setId(101);
        c1.setName("Ashok");
        c1.setPhno(7979799l);

        Customer c2 = new Customer();
        c2.setId(102);
        c2.setName("John");
        c2.setPhno(7565699l);

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("customer.json"), Arrays.asList(c1,c2));
        mapper.writeValue(System.out, Arrays.asList(c1,c2));

        System.out.println("JSON created...");

    }
}
