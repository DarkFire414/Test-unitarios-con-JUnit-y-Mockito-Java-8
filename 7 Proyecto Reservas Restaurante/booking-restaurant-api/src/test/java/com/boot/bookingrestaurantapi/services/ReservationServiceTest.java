package com.boot.bookingrestaurantapi.services;

import com.boot.bookingrestaurantapi.entities.Board;
import com.boot.bookingrestaurantapi.entities.Reservation;
import com.boot.bookingrestaurantapi.entities.Restaurant;
import com.boot.bookingrestaurantapi.entities.Turn;
import com.boot.bookingrestaurantapi.exceptions.BookingException;
import com.boot.bookingrestaurantapi.jsons.CreateReservationRest;
import com.boot.bookingrestaurantapi.jsons.ReservationRest;
import com.boot.bookingrestaurantapi.repositories.ReservationRespository;
import com.boot.bookingrestaurantapi.repositories.RestaurantRepository;
import com.boot.bookingrestaurantapi.repositories.TurnRepository;
import com.boot.bookingrestaurantapi.services.impl.ReservationServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class ReservationServiceTest {

    private static final Reservation RESERVATION = new Reservation();
    private static final String RESERVATION_LOCATOR = "Test_locator";
    private static final Date RESERVATION_DATE = new Date();
    private static final Long RESERVATION_PERSON = 1L;
    private static final Long RESERVATION_ID = 1L;
    private static final String RESERVATION_TURN = "Test_turn";

    private static final Restaurant RESERVATION_RESTAURANT = new Restaurant();
    private static final Long RESTAURANT_ID = 1L;
    private static final String RESTAURANT_NAME = "Test_name";
    private static final String RESTAURANT_ADDRESS = "Test_address";
    private static final String RESTAURANT_DESCRIPTION = "Test_description";
    private static final String RESTAURANT_IMAGE = "Test_image";
    private static final List<Reservation> RESTAURANT_RESERVATIONS = new ArrayList<>();
    private static final List<Board> RESTAURANT_BOARDS = new ArrayList<>();
    private static final List<Turn> RESTAURANT_TURNS = new ArrayList<>();

    private static final CreateReservationRest CREATE_RESERVATION_REST = new CreateReservationRest();
    private static final Date CREATE_RESERVATION_DATE = new Date();
    private static final Long CREATE_RESERVATION_PERSON = 1L;
    private static final Long CREATE_RESERVATION_TURN_ID = 1L;
    private static final Long CREATE_RESERVATION_RESTAURANT_ID = 1L;

    private static final Turn TURN = new Turn();
    private static final String TURN_NAME = "Test_turn";

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private TurnRepository turnRepository;

    @Mock
    private ReservationRespository reservationRespository;

    @InjectMocks
    private ReservationServiceImpl reservationServiceImpl;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        RESERVATION_RESTAURANT.setId(RESTAURANT_ID);
        RESERVATION_RESTAURANT.setName(RESTAURANT_NAME);
        RESERVATION_RESTAURANT.setAddress(RESTAURANT_ADDRESS);
        RESERVATION_RESTAURANT.setDescription(RESTAURANT_DESCRIPTION);
        RESERVATION_RESTAURANT.setImage(RESTAURANT_IMAGE);
        RESERVATION_RESTAURANT.setTurns(RESTAURANT_TURNS);
        RESERVATION_RESTAURANT.setReservations(RESTAURANT_RESERVATIONS);
        RESERVATION_RESTAURANT.setBoards(RESTAURANT_BOARDS);

        TURN.setId(CREATE_RESERVATION_TURN_ID);
        TURN.setName(TURN_NAME);
        TURN.setRestaurant(RESERVATION_RESTAURANT);

        RESERVATION.setId(RESERVATION_ID);
        RESERVATION.setLocator(RESERVATION_LOCATOR);
        RESERVATION.setTurn(RESERVATION_TURN);
        RESERVATION.setPerson(RESERVATION_PERSON);
        RESERVATION.setDate(RESERVATION_DATE);
        RESERVATION.setRestaurant(RESERVATION_RESTAURANT);

        CREATE_RESERVATION_REST.setDate(CREATE_RESERVATION_DATE);
        CREATE_RESERVATION_REST.setPerson(CREATE_RESERVATION_PERSON);
        CREATE_RESERVATION_REST.setTurnId(CREATE_RESERVATION_TURN_ID);
        CREATE_RESERVATION_REST.setRestaurantId(CREATE_RESERVATION_RESTAURANT_ID);
    }

    @Test
    public void getReservationTest() throws BookingException {
        Mockito.when(reservationRespository.findById(RESERVATION_ID)).thenReturn(Optional.of(RESERVATION));

        final ReservationRest response = reservationServiceImpl.getReservation(RESERVATION_ID);

        Assert.assertNotNull(response);
        Assert.assertEquals(RESERVATION_LOCATOR, response.getLocator());
    }

    @Test(expected = BookingException.class)
    public void getReservationNotFoundTest() throws BookingException {
        Mockito.when(reservationRespository.findById(RESERVATION_ID)).thenReturn(Optional.empty());

        reservationServiceImpl.getReservation(RESERVATION_ID);

        Assert.fail();
    }

    @Test
    public void createReservationTest() throws BookingException {
        Mockito.when(restaurantRepository.findById(CREATE_RESERVATION_RESTAURANT_ID)).thenReturn(Optional.of(RESERVATION_RESTAURANT));
        Mockito.when(turnRepository.findById(CREATE_RESERVATION_TURN_ID)).thenReturn(Optional.of(TURN));
        Mockito.when(reservationRespository.findByTurnAndRestaurantId(TURN.getName(), RESERVATION_RESTAURANT.getId())).thenReturn(Optional.empty());
        Mockito.when(reservationRespository.save(Mockito.any(Reservation.class))).thenReturn(RESERVATION);

        reservationServiceImpl.createReservation(CREATE_RESERVATION_REST);
    }

    @Test(expected = BookingException.class)
    public void createReservationRestaurantNotFoundTest() throws BookingException {
        Mockito.when(restaurantRepository.findById(CREATE_RESERVATION_RESTAURANT_ID)).thenReturn(Optional.empty());

        reservationServiceImpl.createReservation(CREATE_RESERVATION_REST);

        Assert.fail();
    }

    @Test(expected = BookingException.class)
    public void createReservationTurnNotFoundTest() throws BookingException {
        Mockito.when(restaurantRepository.findById(CREATE_RESERVATION_RESTAURANT_ID)).thenReturn(Optional.of(RESERVATION_RESTAURANT));
        Mockito.when(turnRepository.findById(CREATE_RESERVATION_TURN_ID)).thenReturn(Optional.empty());

        reservationServiceImpl.createReservation(CREATE_RESERVATION_REST);

        Assert.fail();
    }

    @Test(expected = BookingException.class)
    public void createReservationReservationFoundTest() throws BookingException {
        Mockito.when(restaurantRepository.findById(CREATE_RESERVATION_RESTAURANT_ID)).thenReturn(Optional.of(RESERVATION_RESTAURANT));
        Mockito.when(turnRepository.findById(CREATE_RESERVATION_TURN_ID)).thenReturn(Optional.of(TURN));
        Mockito.when(reservationRespository.findByTurnAndRestaurantId(TURN.getName(), RESERVATION_RESTAURANT.getId())).thenReturn(Optional.of(RESERVATION));

        reservationServiceImpl.createReservation(CREATE_RESERVATION_REST);

        Assert.fail();
    }

    @Test(expected = BookingException.class)
    public void createReservationSaveErrorTest() throws BookingException {
        Mockito.when(restaurantRepository.findById(CREATE_RESERVATION_RESTAURANT_ID)).thenReturn(Optional.of(RESERVATION_RESTAURANT));
        Mockito.when(turnRepository.findById(CREATE_RESERVATION_TURN_ID)).thenReturn(Optional.of(TURN));
        Mockito.when(reservationRespository.findByTurnAndRestaurantId(TURN.getName(), RESERVATION_RESTAURANT.getId())).thenReturn(Optional.empty());
        Mockito.doThrow(Exception.class).when(reservationRespository).save(Mockito.any(Reservation.class));
        reservationServiceImpl.createReservation(CREATE_RESERVATION_REST);

        Assert.fail();
    }
}
