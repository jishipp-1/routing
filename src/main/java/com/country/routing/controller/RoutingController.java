package com.country.routing.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.country.routing.model.RouteResponse;
import com.country.routing.service.RoutingService;

/**
 * REST API for routing
 */
@RestController
@RequestMapping("/routing")
public class RoutingController {

	private final RoutingService service;

	public RoutingController(RoutingService service) {
		this.service = service;
	}

	/**
	 * Example: GET /routing/CZE/ITA
	 */
	@GetMapping("/{origin}/{destination}")
	public RouteResponse getRoute(@PathVariable String origin, @PathVariable String destination) {

		List<String> route = service.findRoute(origin, destination);

		return new RouteResponse(route);
	}

}
