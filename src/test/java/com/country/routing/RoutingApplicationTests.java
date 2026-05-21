package com.country.routing;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.country.routing.exception.RouteNotFoundException;
import com.country.routing.service.RoutingService;
import static org.junit.jupiter.api.Assertions.*;
/**
 * Integration test for RoutingService
 */
@SpringBootTest
class RoutingApplicationTests {

    @Autowired
    private RoutingService service;

    @Test
    void shouldFindValidRoute() {

        List<String> route = service.findRoute("CZE", "ITA");

        assertNotNull(route);
        assertTrue(route.size() >= 2);
        assertEquals("CZE", route.get(0));
        assertEquals("ITA", route.get(route.size() - 1));
    }

    @Test
    void sameCountryShouldReturnItself() {

        List<String> route = service.findRoute("IND", "IND");

        assertEquals(List.of("IND"), route);
    }
    @Test
    void shouldThrowExceptionForInvalidOrigin() {

        assertThrows(RouteNotFoundException.class,
                () -> service.findRoute("XXX", "ITA"));
    }
    @Test
    void shouldThrowExceptionForInvalidDestination() {

        assertThrows(RouteNotFoundException.class,
                () -> service.findRoute("CZE", "YYY"));
    }
    @Test
    void shouldThrowExceptionWhenNoRouteExists() {

        assertThrows(RouteNotFoundException.class,
                () -> service.findRoute("USA", "FRA"));
    }
    @Test
    void shouldHandleLowercaseInput() {

        List<String> route = service.findRoute("cze", "ita");

        assertNotNull(route);
        assertEquals("CZE", route.get(0));
        assertEquals("ITA", route.get(route.size() - 1));
    }
  
}
