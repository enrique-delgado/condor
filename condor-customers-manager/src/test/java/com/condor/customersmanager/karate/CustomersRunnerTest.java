package com.condor.customersmanager.karate;

import com.intuit.karate.junit5.Karate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class CustomersRunnerTest {

    @LocalServerPort
    int port;

    @Karate.Test
    Karate testCustomers() {
        return Karate.run("customers/CustomersTest")
                .relativeTo(getClass())
                .systemProperty("local.server.port", String.valueOf(port));
    }

    @Karate.Test
    Karate testLoad() {
        return Karate.run("customers/CustomersLoadTest")
                .relativeTo(getClass())
                .systemProperty("local.server.port", String.valueOf(port));
    }

    @Karate.Test
    Karate testHealth() {
        return Karate.run("customers/CustomersHealthTest")
                .relativeTo(getClass())
                .systemProperty("local.server.port", String.valueOf(port));
    }
}
