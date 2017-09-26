package rest.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;
import rest.Runner;
import rest.entity.User;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Runner.class)
@SpringBootTest
@WebAppConfiguration
public class UserControllerIT {

    @Test
    public void postAddUser() throws Exception {

        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8097/users";
        String requestJson = "{\n" +
                "\"name\": \"vova\",\n" +
                "\"surname\": \"kutkovetskiy\"\n" +
                "}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);
        User user = restTemplate.postForObject(url, entity, User.class);


        //validate
        assertEquals(user.getName(),"vova");

    }

    @Test
    public void postNotAddUser() throws Exception {

        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8097/users";
        String requestJson = "{\n" +
                "\"name\": \"vova\"" +
                "}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);
        ResponseEntity<String> responseEntity = restTemplate.postForObject(url, entity, ResponseEntity.class);

        //validate
        assertEquals(responseEntity.getBody().toString(),"Введите все данные");
        assertEquals(responseEntity.getStatusCodeValue(), HttpStatus.NO_CONTENT);
    }


}