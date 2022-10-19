# Remote Weather Sensor Reporter Service

## Sensor Simulator - Producer
This acts as a real sensor that will gather current environment status and publishes message to an API
* The sensor information is currently received from CSV. (This is Kaggle Weather report data)
* It runs delayed by 5 seconds to mimic sensor which mostly periodically gathers information and posts message

## Sensor Report - Consumer
* Expose Endpoints to receive sensor information from the Producer and this information is then stored in in-memory database
* This information is utilized to generate statistical report


### Reference Documentation
For further reference, please consider the following sections:

* [Weather Database](https://www.kaggle.com/datasets/muthuj7/weather-dataset )


### Guides
* Have two command prompt opened side by side, one pointing to producer and other consumer
* First run Consumer(Sensor Report) using `mvn spring-boot:run` and then do the same for Producer(Sensor Simulator)

### TODOs
- [x] The application can receive new metric values as the weather changes around the sensor via an API call.
	- This is accomplished using Producer-Consumer Pattern Application
- [x] One or more (or all sensors) to include in results.
	- Using `http://localhost:8080/weatherreport/` API URL, the all the sensors datas are returned
- [x] The metrics (e.g. temperature and humidity); the application should return the average value for these metrics.
	- Partially completed - Only Temperature is done `http://localhost:8080/weatherreport/temperature/average`
- [x] The statistic for the metric: min, max, sum or average.
	- Partially completed - Only Temperature is done `http://localhost:8080/weatherreport/temperature/average`
- [ ] A date range (between one day and a month, if not specified, the latest data should be queried).
	- This is not completed due to time constraint
- [ ] The application data must be persisted in some kind of database/storage.
	- Used in-memory database, as this will be easy if you have to quickly run throught
- [ ] Include input validation and exception handling, as and where you find necessary.
	- Only Page not found handled 


### Improvements
- Statistical information of the sensor looks like quering the database - Suggest [Hasura](https://hasura.io/), this has built in capability to query database API like interface
- Validation
- Error Handling
- Pagination and Limits
- Unit & Integration Test
- Use Actual Database


