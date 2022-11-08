package ru.ivmiit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.ivmiit.dto.response.ProductResponse;
import ru.ivmiit.models.ProductEntity;
import ru.ivmiit.repositories.ProductRepository;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(value = "test")
class HandSentApplicationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProductRepository productRepository;

    private final String USERNAME = "blacklosh2@bk.ru";
    private final String PASSWORD = "123";
    private final String TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJibGFja2xvc2gyQGJrLnJ1Iiwicm9sZSI6IlJPTEVfVVNFUiIsImlhdCI6MTY2NzEzOTM2NCwiZXhwIjozNTAwMDQ3NTM2NH0.jbWRPb6jkmma4p4IdVMi4AM1xjbE0oFHUgQ3xUbcjYc";

    HttpHeaders headers = new HttpHeaders();
    HttpEntity<String> request;
    Gson g = new Gson();
    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void getAllProductsStatus200() throws JsonProcessingException {
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", TOKEN);

        String requestJson = "";
        request = new HttpEntity<String>(requestJson, headers);
        ResponseEntity<String> response = restTemplate.exchange("/products", HttpMethod.GET,
                request, new ParameterizedTypeReference<>() {});

        List<ProductEntity> currentList = productRepository.findAll();
        ProductEntity currentProduct = currentList.get(currentList.size()-1);
        List<ProductResponse> productList = mapper.readValue(response.getBody(), new TypeReference<List<ProductResponse>>(){});
        System.out.println(productList.get(0).getTitle());
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(productList.size(), is(currentList.size()));
        assertThat(productList.get(productList.size()-1).getTitle(), is(currentProduct.getTitle()));
    }


    @Test
    public void getProductByIdStatus200() throws JsonProcessingException {
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", TOKEN);

        String requestJson = "";
        request = new HttpEntity<String>(requestJson, headers);
        List<ProductEntity> currentList = productRepository.findAll();
        ProductEntity currentProduct = currentList.get(currentList.size()-1);
        Long currend_id = currentProduct.getId();
        ResponseEntity<String> response = restTemplate.exchange("/products/" + currend_id.toString(), HttpMethod.GET,
                request, new ParameterizedTypeReference<>() {});

        ProductResponse product = g.fromJson(response.getBody(), ProductResponse.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(product.getTitle(), is(currentProduct.getTitle()));
    }

    @Test
    public void saveProductStatus201() throws JsonProcessingException {
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", TOKEN);

        JSONObject json = new JSONObject();
        json.put("title", "guitar");
        json.put("description", "very good rock guitar");

        request = new HttpEntity<String>(json.toString(), headers);
        ResponseEntity<String> response = restTemplate.exchange("/products/", HttpMethod.POST,
                request, new ParameterizedTypeReference<>() {});

        ProductResponse product = g.fromJson(response.getBody(), ProductResponse.class);


        assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
        assertThat(product.getTitle(), is("guitar"));
    }

    @Test
    public void updateProductStatus202() throws JsonProcessingException {
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", TOKEN);

        List<ProductEntity> currentList = productRepository.findAll();
        ProductEntity currentProduct = currentList.get(currentList.size()-1);
        Long currend_id = currentProduct.getId();

        JSONObject json = new JSONObject();
        json.put("title", "guitar");
        json.put("price", 40001);
        json.put("description", "very good rock guitar");

        request = new HttpEntity<String>(json.toString(), headers);
        ResponseEntity<String> response = restTemplate.exchange("/products/" + currend_id.toString(), HttpMethod.PUT,
                request, new ParameterizedTypeReference<>() {});

        ProductResponse product = g.fromJson(response.getBody(), ProductResponse.class);

        assertThat(response.getStatusCode(), is(HttpStatus.ACCEPTED));
        assertThat(product.getPrice(), is(40001));
    }

    @Test
    public void deleteProductStatus200() throws JsonProcessingException {
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", TOKEN);

        List<ProductEntity> currentList = productRepository.findAll();
        ProductEntity currentProduct = currentList.get(currentList.size()-1);
        Long currend_id = currentProduct.getId();

        request = new HttpEntity<String>("", headers);
        ResponseEntity<String> response = restTemplate.exchange("/products/" + currend_id.toString(), HttpMethod.DELETE,
                request, new ParameterizedTypeReference<>() {});

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
    }

    @Test
    public void deleteProductStatus404() throws JsonProcessingException {
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", TOKEN);

        List<ProductEntity> currentList = productRepository.findAll();
        ProductEntity currentProduct = currentList.get(currentList.size()-1);
        Long currend_id = currentProduct.getId();

        request = new HttpEntity<String>("", headers);
        ResponseEntity<String> response = restTemplate.exchange("/products/" + currend_id.toString() + "1", HttpMethod.DELETE,
                request, new ParameterizedTypeReference<>() {});

        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
    }
}
