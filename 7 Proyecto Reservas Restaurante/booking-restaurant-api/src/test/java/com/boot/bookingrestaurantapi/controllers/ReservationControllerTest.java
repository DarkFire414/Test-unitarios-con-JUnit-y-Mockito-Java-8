package com.boot.bookingrestaurantapi.controllers;

import com.boot.bookingrestaurantapi.exceptions.BookingException;
import com.boot.bookingrestaurantapi.jsons.CreateReservationRest;
import com.boot.bookingrestaurantapi.jsons.ReservationRest;
import com.boot.bookingrestaurantapi.responses.BookingResponse;
import com.boot.bookingrestaurantapi.services.ReservationService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Date;

public class ReservationControllerTest {

    private static final String SUCCES_STATUS = "Succes";
    private static final String SUCCES_CODE = "200 OK";
    private static final String SUCCES_MESSAGE = "OK";

    private static final ReservationRest RESERVATION_REST = new ReservationRest();
    private static final Long RESERVATION_ID = 1L;
    private static final String RESERVATION_LOCATOR = "Test_locator";
    private static final Date RESERVATION_DATE = new Date();
    private static final Long RESERVATION_PERSON = 1L;
    private static final Long RESERVATION_TURN_ID = 1L;

    private static final CreateReservationRest CREATE_RESERVATION_REST = new CreateReservationRest();
    private static final Date CREATE_RESERVATION_DATE = new Date();
    private static final Long CREATE_RESERVATION_PERSON = 1L;
    private static final Long CREATE_RESERVATION_TURN_ID = 1L;
    private static final Long CREATE_RESERVATION_RESTAURANT_ID = 1L;

    @Mock
    ReservationService reservationService;

    @InjectMocks
    ReservationController reservationController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        RESERVATION_REST.setRestaurantId(RESERVATION_ID);
        RESERVATION_REST.setLocator(RESERVATION_LOCATOR);
        RESERVATION_REST.setDate(RESERVATION_DATE);
        RESERVATION_REST.setPerson(RESERVATION_PERSON);
        RESERVATION_REST.setTurnId(RESERVATION_TURN_ID);

        CREATE_RESERVATION_REST.setDate(CREATE_RESERVATION_DATE);
        CREATE_RESERVATION_REST.setPerson(CREATE_RESERVATION_PERSON);
        CREATE_RESERVATION_REST.setTurnId(CREATE_RESERVATION_TURN_ID);
        CREATE_RESERVATION_REST.setRestaurantId(CREATE_RESERVATION_RESTAURANT_ID);
    }

    @Test
    public void getReservationByIdTest() throws BookingException {
        Mockito.when(reservationService.getReservation(RESERVATION_ID)).thenReturn(RESERVATION_REST);
        final BookingResponse<ReservationRest> response = reservationController.getReservationById(RESERVATION_ID);
        Assert.assertEquals(SUCCES_STATUS, response.getStatus());
        Assert.assertEquals(SUCCES_CODE, response.getCode());
        Assert.assertEquals(SUCCES_MESSAGE, response.getMessage());
        Assert.assertEquals(RESERVATION_REST, response.getData());
    }

    @Test
    public void createReservationTest() throws BookingException {
        Mockito.when(reservationService.createReservation(CREATE_RESERVATION_REST)).thenReturn(RESERVATION_LOCATOR);
        final BookingResponse<String> response = reservationController.createReservation(CREATE_RESERVATION_REST);
        Assert.assertEquals(SUCCES_STATUS, response.getStatus());
        Assert.assertEquals(SUCCES_CODE, response.getCode());
        Assert.assertEquals(SUCCES_MESSAGE, response.getMessage());
        Assert.assertEquals(RESERVATION_LOCATOR, response.getData());
    }
}
