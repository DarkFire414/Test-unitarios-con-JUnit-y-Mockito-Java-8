package com.boot.bookingrestaurantapi.services;

import com.boot.bookingrestaurantapi.entities.Restaurant;
import com.boot.bookingrestaurantapi.entities.Turn;
import com.boot.bookingrestaurantapi.exceptions.BookingException;
import com.boot.bookingrestaurantapi.jsons.RestaurantRest;
import com.boot.bookingrestaurantapi.repositories.RestaurantRepository;
import com.boot.bookingrestaurantapi.services.impl.RestaurantServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class RestaurantServiceTest {

    private static final Restaurant RESTAURANT = new Restaurant();
    public static final Long RESTAURANT_ID = 1L;
    private static final String RESTAURANT_NAME = "Test_name";
    private static final String RESTAURANT_ADDRESS = "Test_address";
    private static final String RESTAURANT_DESCRIPTION = "Test_description";
    private static final String RESTAURANT_IMAGE = "Test_image";
    private static final List<Turn> RESTAURANT_TURNS = new ArrayList<>();

    @Mock
    RestaurantRepository restaurantRepository;

    @InjectMocks
    RestaurantServiceImpl restaurantServiceImpl;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        RESTAURANT.setId(RESTAURANT_ID);
        RESTAURANT.setName(RESTAURANT_NAME);
        RESTAURANT.setAddress(RESTAURANT_ADDRESS);
        RESTAURANT.setDescription(RESTAURANT_DESCRIPTION);
        RESTAURANT.setImage(RESTAURANT_IMAGE);
        RESTAURANT.setTurns(RESTAURANT_TURNS);
        RESTAURANT.setBoards(new ArrayList<>());
        RESTAURANT.setReservations(new ArrayList<>());
    }

    @Test
    public void getRestaurantByIdTest() throws BookingException {
        Mockito.when(restaurantRepository.findById(RESTAURANT_ID)).thenReturn(Optional.of(RESTAURANT));

        restaurantServiceImpl.getRestaurantById(RESTAURANT_ID);

        Assert.assertNotNull(RESTAURANT);
        Assert.assertEquals(RESTAURANT_ID, RESTAURANT.getId());
    }

    @Test(expected = BookingException.class)
    public void getRestaurantByIdNotFoundTest() throws BookingException {
        Mockito.when(restaurantRepository.findById(RESTAURANT_ID)).thenReturn(Optional.empty());

        restaurantServiceImpl.getRestaurantById(RESTAURANT_ID);

        Assert.fail();
    }

    @Test
    public void getRestaurantsTest() throws BookingException {
        Mockito.when(restaurantRepository.findAll()).thenReturn(Arrays.asList(RESTAURANT));

        List<RestaurantRest> restaurants = restaurantServiceImpl.getRestaurants();

        Assert.assertNotNull(restaurants);
        Assert.assertFalse(restaurants.isEmpty());
        Assert.assertEquals(1, restaurants.size());
    }
}
