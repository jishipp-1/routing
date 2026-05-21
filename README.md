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
```text
GET /routing/{origin}/{destination} 
```
### Example Request
```text
GET http://localhost:8080/routing/CZE/ITA 
```
###  Example Response
```text 
{
  "route": ["CZE", "AUT", "ITA"]
}
```
## Error Response
### If no route exists or invalid country code:
```text 
- HTTP 400 Bad Request
- No land route found
```
## Data Source
Country data is loaded from:
```text 
https://raw.githubusercontent.com/mledoze/countries/master/countries.json
```
### Each country contains:
- ```text cca3 ``` - 3-letter country code
-  ```text borders ``` - list of neighboring countries

## Tech Stack
- Java 17
- Spring Boot 3
- Maven
- Jackson (JSON parsing)
- JUnit 5

## How to Build & Run
1. Clone Repository
   ```text
   - git clone https://github.com/your-username/routing.git
   - cd routing
   ```
3. Build Project
   ```text
   - mvn clean install
   ```
5. Run Application
   ```text
   - mvn spring-boot:run
    ```
7. Access API
   ```text
   - http://localhost:8080/routing/CZE/ITA
    ```
9. Run Tests
    ```text
    - mvn test
    ```
## Test Coverage
   - Valid route test (CZE → ITA)
   - Same country test
   - Invalid country handling
   - No route scenario

## Project Structure
  - controller → REST API layer
  - service → business logic (BFS routing)
  - config → data loader configuration
  - model → response/data models
  - exception → custom exceptions handling
 
## Notes
-  Country data is loaded at application startup
-  Graph is stored in-memory for fast access
-  BFS guarantees shortest possible land route
