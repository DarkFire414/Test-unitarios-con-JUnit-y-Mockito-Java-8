package com.boot.bookingrestaurantapi.controllers;

import com.boot.bookingrestaurantapi.exceptions.BookingException;
import com.boot.bookingrestaurantapi.responses.BookingResponse;
import com.boot.bookingrestaurantapi.services.CancelReservationService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class CancelReservationControllerTest {

    private static final String LOCATOR = "Test_locator";

    private static final String SUCCES_STATUS = "Succes";
    private static final String SUCCES_CODE = "200 OK";
    private static final String SUCCES_MESSAGE = "OK";
    private static final String RESERVATION_DELETED = "LOCATOR_DELETED";

    @Mock
    private CancelReservationService cancelReservationService;

    @InjectMocks
    private CancelReservationController cancelReservationController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void deleteReservationTest() throws BookingException {
        Mockito.when(cancelReservationService.deleteReservation(LOCATOR)).thenReturn(RESERVATION_DELETED);

        final BookingResponse<String> response = cancelReservationController.deleteReservation(LOCATOR);

        Assert.assertEquals(SUCCES_STATUS, response.getStatus());
        Assert.assertEquals(SUCCES_CODE, response.getCode());
        Assert.assertEquals(SUCCES_MESSAGE, response.getMessage());
        Assert.assertEquals(RESERVATION_DELETED, response.getData());
    }
}
