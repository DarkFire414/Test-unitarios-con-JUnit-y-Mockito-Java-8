package com.boot.bookingrestaurantapi.controllers;

import com.boot.bookingrestaurantapi.exceptions.BookingException;
import com.boot.bookingrestaurantapi.jsons.RestaurantRest;
import com.boot.bookingrestaurantapi.jsons.TurnRest;
import com.boot.bookingrestaurantapi.responses.BookingResponse;
import com.boot.bookingrestaurantapi.services.RestaurantService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

public class RestaurantControllerTest {

    private static final String SUCCES_STATUS = "Succes";
    private static final String SUCCES_CODE = "200 OK";
    private static final String SUCCES_MESSAGE = "OK";

    private static final List<RestaurantRest> RESTAURANT_REST_LIST = new ArrayList<>();
    private static final RestaurantRest RESTAURANT_REST = new RestaurantRest();
    private static final Long RESTAURANT_ID = 1L;
    private static final String RESTAURANT_NAME = "Test_name";
    private static final String RESTAURANT_ADDRESS = "Test_address";
    private static final String RESTAURANT_DESCRIPTION = "Test_description";
    private static final String RESTAURANT_IMAGE = "Test_image";
    private static final List<TurnRest> RESTAURANT_TURNS = new ArrayList<>();

    @Mock
    private RestaurantService restaurantService;

    @InjectMocks
    private RestaurantController restaurantController;

    @Before
    public void setUp() throws BookingException {
        MockitoAnnotations.initMocks(this);

        RESTAURANT_REST.setId(RESTAURANT_ID);
        RESTAURANT_REST.setName(RESTAURANT_NAME);
        RESTAURANT_REST.setAddress(RESTAURANT_ADDRESS);
        RESTAURANT_REST.setDescription(RESTAURANT_DESCRIPTION);
        RESTAURANT_REST.setImage(RESTAURANT_IMAGE);
        RESTAURANT_REST.setTurns(RESTAURANT_TURNS);
    }

    @Test
    public void getRestaurantByIdTest() throws BookingException {
        Mockito.when(restaurantService.getRestaurantById(RESTAURANT_ID)).thenReturn(RESTAURANT_REST);
        final BookingResponse<RestaurantRest> response = restaurantController.getRestaurantById(RESTAURANT_ID);
        Assert.assertEquals(SUCCES_STATUS, response.getStatus());
        Assert.assertEquals(SUCCES_CODE, response.getCode());
        Assert.assertEquals(SUCCES_MESSAGE, response.getMessage());
        Assert.assertEquals(RESTAURANT_REST, response.getData());
    }

    @Test
    public void getRestaurantsTest() throws BookingException {
        BookingResponse<List<RestaurantRest>> response = restaurantController.getRestaurants();
        Assert.assertEquals(SUCCES_STATUS, response.getStatus());
        Assert.assertEquals(SUCCES_CODE, response.getCode());
        Assert.assertEquals(SUCCES_MESSAGE, response.getMessage());
        Assert.assertEquals(RESTAURANT_REST_LIST, response.getData());
    }
}
