package com.boot.bookingrestaurantapi.services;

import com.boot.bookingrestaurantapi.entities.Reservation;
import com.boot.bookingrestaurantapi.entities.Restaurant;
import com.boot.bookingrestaurantapi.exceptions.BookingException;
import com.boot.bookingrestaurantapi.repositories.ReservationRespository;
import com.boot.bookingrestaurantapi.services.impl.CancelReservationServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.Optional;

public class CancelReservationServiceTest {

    private static final Reservation RESERVATION = new Reservation();
    private static final Long RESERVATION_ID = 1L;
    private static final String RESERVATION_LOCATOR = "Test_locator";
    private static final String RESERVATION_TURN = "Test_turn";
    private static final Long RESERVATION_PERSON = 1L;
    private static final Date RESERVATION_DATE = new Date();
    private static final Restaurant RESERVATION_RESTAURANT = new Restaurant();
    private static final String LOCATOR = RESERVATION_LOCATOR;

    private static final String RESPONSE = "LOCATOR_DELETED";

    @Mock
    private ReservationRespository reservationRespository;

    @InjectMocks
    private CancelReservationServiceImpl cancelReservationServiceImpl;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        RESERVATION.setId(RESERVATION_ID);
        RESERVATION.setLocator(RESERVATION_LOCATOR);
        RESERVATION.setTurn(RESERVATION_TURN);
        RESERVATION.setPerson(RESERVATION_PERSON);
        RESERVATION.setDate(RESERVATION_DATE);
        RESERVATION.setRestaurant(RESERVATION_RESTAURANT);
    }

    @Test
    public void deleteReservationSuccessTest() throws BookingException {
        Mockito.when(reservationRespository.findByLocator(LOCATOR)).thenReturn(Optional.of(RESERVATION));
        Mockito.when(reservationRespository.deleteByLocator(LOCATOR)).thenReturn(Optional.of(RESERVATION));

        final String response = cancelReservationServiceImpl.deleteReservation(LOCATOR);

        Assert.assertEquals(RESPONSE, response);
    }

    @Test(expected = BookingException.class)
    public void deleteReservationLocatorNotFoundTest() throws BookingException {
        Mockito.when(reservationRespository.findByLocator(LOCATOR)).thenReturn(Optional.empty());

        final String response = cancelReservationServiceImpl.deleteReservation(LOCATOR);

        Assert.fail();
    }

    @Test(expected = BookingException.class)
    public void deleteReservationServerErrorTest() throws BookingException {
        Mockito.when(reservationRespository.findByLocator(LOCATOR)).thenReturn(Optional.of(RESERVATION));
        Mockito.doThrow(Exception.class).when(reservationRespository).deleteByLocator(LOCATOR);

        final String response = cancelReservationServiceImpl.deleteReservation(LOCATOR);

        Assert.fail();
    }
}
