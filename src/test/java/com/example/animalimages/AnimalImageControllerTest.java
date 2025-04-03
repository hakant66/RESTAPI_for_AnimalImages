/*
 * AnimalImage Test Service
 * -------------------------
 * MockMvc is a tool to test Spring MVC controllers by simulating HTTP requests.
 * These tests ensure that the REST API behaves correctly for both valid and invalid inputs.
 * No actual web server or browser is involved — tests run in-memory, making them fast and isolated.
 */
package com.example.animalimages;

import org.junit.jupiter.api.Test; // JUnit 5 import
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Integration tests for AnimalImageController using MockMvc to simulate HTTP requests.
 */

@SpringBootTest // Bootstraps the full Spring context for integration testing (like running the app)
@AutoConfigureMockMvc // Automatically configures and injects MockMvc. to the controller without starting a real server
public class AnimalImageControllerTest {

    @Autowired               // Injected MockMvc to simulate HTTP requests to controller
    private MockMvc mockMvc; // MockMvc simulates HTTP calls without starting the actual server

    /*
     * Test: Valid request to /api/animals/last
     * Verifies that a GET request for a known animal type returns HTTP 200 OK.
     * Makes sure at least one image for each animal (cat, dog, bear) has already been stored if available.
     */

    /**
     * Utility method to accept 200 OK or 204 No Content as valid statuses.
     */
    private void assertOkOrNoContent(int status, String message) {
        assertTrue(status == 200 || status == 204, message + " → Got: " + status);
    }

    /**
     * Setup method to insert one image for each valid animal type before each test runs.
     */
    @BeforeEach
    public void setup() throws Exception {
        String[] animalTypes = {"dog", "cat", "bear", "duck"};

        for (String type : animalTypes) {
            MockHttpServletResponse response = mockMvc.perform(post("/api/animals/fetch")
                            .param("type", type)
                            .param("count", "1")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andReturn()
                    .getResponse(); // ✅ returns MockHttpServletResponse

            assertOkOrNoContent(response.getStatus(),
                    "Expected 200 or 204 during setup for type: " + type);
        }
    }


    /**
     * Test: Valid animal type request
     * Calls GET /api/animals/last/image to fetch the raw binary image of the last stored bear
     * Verifies it returns 200 OK with image content
     * Confirms the backend properly stores and serves binary image data
     */
    @Test
    public void shouldReturnLastImageMetadataForValidAnimal() throws Exception {
        mockMvc.perform(get("/api/animals/last?type=bear"))
                .andExpect(status().isOk());
    }

    /**
     * Test: Invalid animal type request
     * Calls the same GET /api/animals/last endpoint with an invalid type
     * Verifies that a GET request with an unknown animal type returns HTTP 404 Not Found.
     */
    @Test
    public void shouldReturnNotFoundForUnknownAnimal() throws Exception {
        mockMvc.perform(get("/api/animals/last?type=unknown"))
                .andExpect(status().isNotFound());
    }

    /**
     * Test: Fetch and store image for a valid animal type
     * Sends a POST request to /api/animals/fetch to fetch and store 1 dog image
     * Returns 200 OK if successful
     * Verifies the service correctly fetches from the external image API and saves it to the database
     */
    @Test
    public void shouldFetchAndStoreAnimalImages() throws Exception {
        mockMvc.perform(post("/api/animals/fetch?type=dog&count=1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    /**
     * Calls GET /api/animals/last/image to fetch the raw binary image of the last stored dog
     * Verifies it returns 200 OK with image content
     * Confirms the backend properly stores and serves binary image data
     */
    @Test
    public void shouldReturnLastImageBinary() throws Exception {
        mockMvc.perform(get("/api/animals/last/image?type=dog"))
                .andExpect(status().isOk());
    }

    /**
     * Test: Attempt to retrieve image for invalid animal type
     */
    @Test
    public void shouldReturnNotFoundForImageOfUnknownAnimal() throws Exception {
        mockMvc.perform(get("/api/animals/last/image?type=unicorn"))
                .andExpect(status().isNotFound());
    }
}

/**
 * Custom BeanFactoryPostProcessor to demonstrate bean inspection before context initialization.
 * Logs all Spring-managed beans when the test context starts
 */
@Component
class CustomBeanLogger implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("\n--- BeanFactoryPostProcessor activated: Listing registered beans ---");
        String[] beanNames = beanFactory.getBeanDefinitionNames();
        for (String name : beanNames) {
            System.out.println("Bean: " + name);
        }
    }
}
