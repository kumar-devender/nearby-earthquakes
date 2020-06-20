#### Prerequisite
* Java 8+ installed
* Maven installed

#### How to run on local system
* Clone the project ``` https://github.com/kumar-devender/nearby-earthquakes.git```
* Navigate into `nearby-earthquakes` directory
* Run the following command 
```
mvn clean package 
java -jar target/nearby-earthquakes-1.0.0.jar
 ```
* Enter latitude and longitude like following
```
40.730610  
-73.935242
```
* Sample input and output 
```
40.730610
-73.935242
21:15:46.734 INFO  c.s.service.EarthquakesService - Input coordinates [Coordinates(latitude=40.73061, longitude=-73.935242, depth=0.0)] and count [10]
21:15:46.735 INFO  c.s.r.FeedEarthquakesRepository - Getting Earthquakes summary from api
21:15:52.848 INFO  c.s.service.EarthquakesService - Total Earthquakes count [20025]
21:15:52.849 INFO  c.s.service.EarthQuakePrinter - M 4.5 - 2497 km E of Tolhuin, Argentina || 4719.717635417491
21:15:52.849 INFO  c.s.service.EarthQuakePrinter - M 4.9 - 2458 km E of Tolhuin, Argentina || 4731.8817353269
21:15:52.849 INFO  c.s.service.EarthQuakePrinter - M 4.8 - 1339 km ESE of Tolhuin, Argentina || 4732.089349596797
21:15:52.850 INFO  c.s.service.EarthQuakePrinter - M 4.7 - 2545 km E of Tolhuin, Argentina || 4745.401567976947
21:15:52.850 INFO  c.s.service.EarthQuakePrinter - M 4.4 - 2415 km E of Tolhuin, Argentina || 4749.711383258197
21:15:52.850 INFO  c.s.service.EarthQuakePrinter - M 4.4 - 2458 km SSW of Edinburgh of the Seven Seas, Saint Helena || 4812.317561741801
21:15:52.850 INFO  c.s.service.EarthQuakePrinter - M 5.2 - 2489 km SSW of Edinburgh of the Seven Seas, Saint Helena || 4814.438967240921
21:15:52.850 INFO  c.s.service.EarthQuakePrinter - M 4.1 - 2439 km E of Tolhuin, Argentina || 4834.298356207044
21:15:52.850 INFO  c.s.service.EarthQuakePrinter - M 4.6 - 3255 km WNW of Puerto Natales, Chile || 4840.01970830778
21:15:52.850 INFO  c.s.service.EarthQuakePrinter - M 4.9 - 2357 km SSW of Edinburgh of the Seven Seas, Saint Helena || 4863.341264329173
```


