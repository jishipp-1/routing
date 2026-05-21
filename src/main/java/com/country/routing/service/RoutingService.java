package com.country.routing.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.country.routing.config.CountryDataLoader;
import com.country.routing.exception.RouteNotFoundException;

/**
 * Core service for routing logic using BFS
 */

@Service
public class RoutingService {
	private final Map<String, List<String>> graph;

	public RoutingService(CountryDataLoader loader) {
		this.graph = loader.getGraph();
	}

	/**
	 * Find shortest land route using BFS
	 */
	public List<String> findRoute(String origin, String destination) {

		origin = origin.toUpperCase();
		destination = destination.toUpperCase();

		if (origin.equals(destination)) {
			return List.of(origin);
		}

		if (!graph.containsKey(origin) || !graph.containsKey(destination)) {
			throw new RouteNotFoundException("Invalid country code");
		}

		Queue<String> queue = new LinkedList<>();
		Map<String, String> parent = new HashMap<>();
		Set<String> visited = new HashSet<>();

		queue.add(origin);
		visited.add(origin);
		parent.put(origin, null);

		// BFS traversal
		while (!queue.isEmpty()) {

			String current = queue.poll();

			if (current.equals(destination)) {
				return buildPath(parent, destination);
			}

			for (String neighbor : graph.getOrDefault(current, List.of())) {

				if (!visited.contains(neighbor)) {
					visited.add(neighbor);
					parent.put(neighbor, current);
					queue.add(neighbor);
				}
			}
		}

		throw new RouteNotFoundException("No land route found");
	}

	/**
	 * Reconstruct path from parent map
	 */
	private List<String> buildPath(Map<String, String> parent, String dest) {

		LinkedList<String> path = new LinkedList<>();

		for (String at = dest; at != null; at = parent.get(at)) {
			path.addFirst(at);
		}

		return path;
	}
}
