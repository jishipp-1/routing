# Country Routing Service

A Spring Boot REST API that calculates the shortest land route between two countries using Breadth First Search (BFS) on real-world country border data.

##  Features
- Find land route between countries using `cca3` codes
- BFS algorithm for shortest path
- Loads real-time country data from GitHub JSON
- REST API end point `/routing/{origin}/{destination}`
- Handles invalid inputs and no-route scenarios
- Unit tested service layer

##  Algorithm

### Breadth First Search (BFS)

- Each country is treated as a node
- Borders represent edges between nodes
- BFS ensures the shortest path is found

**Time Complexity:** O(V + E)  
**Space Complexity:** O(V)

##  API Endpoint

### Get Route
GET /routing/{origin}/{destination}

### Example Request
GET http://localhost:8080/routing/CZE/ITA

###  Example Response
# json
{
  "route": ["CZE", "AUT", "ITA"]
}

#Error Response
If no route exists or invalid country code:
#Response
Invalid country code

#Data Source
Country data is loaded from:
https://raw.githubusercontent.com/mledoze/countries/master/countries.json

#Each country contains:
cca3 - 3-letter country code
borders - list of neighboring countries

#Tech Stack
Java 17
Spring Boot 3
Maven
Jackson (JSON parsing)
JUnit 5

#How to Build & Run
1. Clone Repository
git clone https://github.com/<your-username>/routing.git
cd routing

2. Build Project
mvn clean install

3. Run Application
mvn spring-boot:run

4. Access API
http://localhost:8080/routing/CZE/ITA

5. Run Tests
 mvn test

#Test Coverage
Valid route test (CZE → ITA)
Same country test
Invalid country handling
No route scenario

# Project Structure
src/main/java/com/country/routing
 ├── controller
 ├── service
 ├── config
 ├── model
 ├── exception
 
# Notes
Country data is loaded at application startup
Graph is stored in-memory for fast access
BFS guarantees shortest possible land route
