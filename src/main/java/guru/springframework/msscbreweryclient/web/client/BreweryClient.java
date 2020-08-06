package guru.springframework.msscbreweryclient.web.client;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import guru.springframework.msscbreweryclient.web.model.BeerDto;
import guru.springframework.msscbreweryclient.web.model.CustomerDto;

/**
 * BeerClient
 */
@Component
@ConfigurationProperties(value = "sfg.brewery", ignoreInvalidFields = false)
public class BreweryClient {

    private final String BEER_PATH_V1 = "/api/v1/beer/";
    private final String CUSTOMER_PATH_V1 = "/api/v1/customer/";

    private String apiHost;
    private final RestTemplate restTemplate;

    public BreweryClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public BeerDto getBeerById(UUID beerId){
        return this.restTemplate.getForObject( this.apiHost + BEER_PATH_V1 + beerId.toString(), BeerDto.class);
    }

    public URI saveNewBeer(BeerDto beer){
        return this.restTemplate.postForLocation(this.apiHost + BEER_PATH_V1, beer);
    }

    public void updateBeer(UUID beerId, BeerDto beer){
        this.restTemplate.put(this.apiHost + BEER_PATH_V1 + "/" + beerId, beer);
    }

    public void deleteBeer(UUID beerId){
        this.restTemplate.delete(this.apiHost + BEER_PATH_V1 + "/" + beerId);
    }
    
    public void setApiHost(String apiHost) {
        this.apiHost = apiHost;
    }

	public CustomerDto getCustomerById(UUID customerId) {
		return this.restTemplate.getForObject(this.apiHost + CUSTOMER_PATH_V1 + customerId.toString(), CustomerDto.class);
    }
    
    public URI saveNewCustomer(CustomerDto customer){
        return this.restTemplate.postForLocation(this.apiHost + CUSTOMER_PATH_V1, customer);
    }

    public void udpateCustomer(UUID customerId, CustomerDto customer){
        this.restTemplate.put(this.apiHost + CUSTOMER_PATH_V1 + customerId, customer);
    }

    public void deleteCustomer(UUID customerId){
        this.restTemplate.delete(this.apiHost + CUSTOMER_PATH_V1 + customerId);
    }

	public ResponseEntity<List<BeerDto>> getAllBeers() {
        ResponseEntity<List<BeerDto>> response = this.restTemplate.exchange(this.apiHost + BEER_PATH_V1, HttpMethod.GET, null,  new ParameterizedTypeReference<List<BeerDto>>() {});
		return response;
	}

}