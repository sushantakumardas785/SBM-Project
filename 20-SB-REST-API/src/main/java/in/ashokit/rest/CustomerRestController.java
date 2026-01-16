package in.ashokit.rest;

import in.ashokit.dto.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class CustomerRestController {

    @GetMapping(
            value = "/customer",
            produces = { "application/json", "application/xml" }
    )
    public ResponseEntity<Customer> getCustomer() {

        Customer c1 = new Customer(1, "John", "john@gmail.com");

        return new ResponseEntity<>(c1, HttpStatus.OK);
    }

    @GetMapping(value = "/customers", produces = "application/json")
    public ResponseEntity<List<Customer>> getCustomers() {
        Customer c1 = new Customer(1, "John", "john@gmail.com");
        Customer c2 = new Customer(2, "Smith", "smith@gmail.com");
        Customer c3 = new Customer(3, "David", "david@gmail.com");
        List<Customer> customerList = Arrays.asList(c1, c2, c3);
        return new ResponseEntity<>(customerList, HttpStatus.OK);
    }


    @PostMapping(
            value = "/customer",
            consumes = { "application/json", "application/xml"},
            produces = { "application/json", "application/xml" }
    )
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {

        System.out.println(customer);
        // logic to save customer in DB

        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @PutMapping(
            value = "/customer/{cid}",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<Customer> updateCustomer(@PathVariable Integer cid, @RequestBody Customer customer) {

        System.out.println(customer);

        // logic to update the customer in DB based on given CID

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @DeleteMapping("/customer/{cid}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Integer cid) {

        // logic to delete customer in DB based on given CID

        return new ResponseEntity<>("Customer Deleted", HttpStatus.OK);
    }
}
