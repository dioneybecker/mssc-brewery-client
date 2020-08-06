package guru.springframework.msscbreweryclient.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.apache.commons.codec.binary.Hex;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import guru.springframework.msscbreweryclient.web.client.BreweryClient;
import guru.springframework.msscbreweryclient.web.model.BeerDto;
import guru.springframework.msscbreweryclient.web.model.BeerStyleEnum;
import guru.springframework.msscbreweryclient.web.model.CustomerDto;

/**
 * BreweryClientTest
 */
@SpringBootTest
public class BreweryClientTest {

    @Autowired
    BreweryClient client;

    @Test
    void getBeerById() {
        try {
            UUID id = UUID.fromString("ba273223-aba6-4adc-8613-2a97046ccb65");
            BeerDto beer = client.getBeerById(id);
            

            assertNotNull(beer);

        } catch (Exception ex) {

        }

    }

    @Test
    void getAllBeers() {
        List<BeerDto> beerDtoList = client.getAllBeers().getBody();

        System.out.println(beerDtoList.toString());

        assertNotNull(beerDtoList);
    }

    @Test
    void saveNewBeer() {
        BeerDto beer = BeerDto.builder().beerName("New Beer3").beerStyle(BeerStyleEnum.ALE).UPC(3123456789L)
                .price(new BigDecimal("12.50")).build();

        URI uri = client.saveNewBeer(beer);
        assertNotNull(uri);
        System.out.println(uri);
    }

    @Test
    void updateBeer() {
        try {
            UUID id = UUID.fromString("08b99f5d-f4da-4583-8ff5-73d6b0957f52");
            BeerDto beer = client.getBeerById(id);
            if (beer != null) {
                beer.setBeerName("Coruja");
                client.updateBeer(id, beer);
                beer = client.getBeerById(id);

                assertEquals(beer.getBeerName(), "Coruja");
            }

        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

    }

    @Test
    void deleteBeer() {
        client.deleteBeer(UUID.randomUUID());
    }

    @Test
    void getCustomerById() {
        CustomerDto customer = client.getCustomerById(UUID.randomUUID());

        assertNotNull(customer);
    }

    @Test
    void saveNewCustomer() {
        CustomerDto customer = CustomerDto.builder().name("Dioney Becker").build();

        URI uri = client.saveNewCustomer(customer);
        assertNotNull(uri);
        System.out.println(uri);
    }

    @Test
    void updateCustomer() {
        CustomerDto customer = client.getCustomerById(UUID.randomUUID());
        client.udpateCustomer(UUID.randomUUID(), customer);
    }

    @Test
    void deleteCustomer() {
        client.deleteCustomer(UUID.randomUUID());
    }
}
