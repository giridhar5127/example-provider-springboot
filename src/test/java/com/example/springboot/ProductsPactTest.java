package com.example.springboot;

import au.com.dius.pact.provider.junitsupport.*;
import au.com.dius.pact.provider.junitsupport.loader.*;
import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Provider("pactflow-example-provider-springboot")
@PactBroker(url = "${PACT_BROKER_BASE_URL}", providerBranch = "master",
        enablePendingPacts = "true",
        includeWipPactsSince = "2024-05-07",
        authentication = @PactBrokerAuth(token = "${PACT_BROKER_TOKEN}"))
@IgnoreNoPactsToVerify
@IgnoreMissingStateChange
class ProductsPactTest {

  @Autowired
  ProductRepository repository;

  @BeforeEach
  public void setupTestTarget(PactVerificationContext context) {
    context.setTarget(new HttpTestTarget("localhost", 8080));
  }

  @TestTemplate
  @ExtendWith(PactVerificationInvocationContextProvider.class)
  public void pactVerificationTestTemplate(PactVerificationContext context) {
    context.verifyInteraction();
  }

  @State("a product with ID 10 exists")
  public void setupProductX010000021() throws IOException {
    System.out.println("a product with ID 10 exists");
    repository.save(new Product(10L, "test", "product description", "1.0.0", "desc", "1.0", "f"));
  }

  @State("a product with ID 11 does not exist")
  public void setupProductX010000022() throws IOException {
    System.out.println("a product with ID 11 does not exist");
    repository.deleteAll();
  }

  @State("products exist")
  public void setupProducts() throws IOException {
    repository.deleteAll();
    System.out.println("a product with ID 11 does not exist");
    repository.save(new Product(10L, "test", "product description", "1.0.0", "desc", "1.0", "f"));
  }

  @au.com.dius.pact.provider.junitsupport.loader.PactBrokerConsumerVersionSelectors
  public static SelectorBuilder consumerVersionSelectors() {
    // Select Pacts for consumers deployed to production with branch 'FEAT-123' 
    return new SelectorBuilder()
            .mainBranch();
  }

}