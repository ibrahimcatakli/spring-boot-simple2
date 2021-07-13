# spring-boot-simple2


## Garage Api




#Api Documentation


 [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
 
 [http://localhost:8080/v2/api-docs](http://localhost:8080/v2/api-docs)





#Methods

```sh
POST      /api/            parking process
DELETE    /api/leave/      leave the garage 
GET       /api/status      garage status 
```

```sh
park    > (Post)   >  http://localhost:8080/api/
```
```json
		   body :
			{
			  "registrationNumber": "34-VO-2019",
			  "vehicleType": "Truck",
			  "color": "Red"
			}
```
```sh
leave   > (Delete) >  http://localhost:8080/api/leave/{slotnumber}
```
```sh
status  > (Get)    >  http://localhost:8080/api/status/
```



#Test

```sh
mvn -Dtest=GarageApplicationTests test
```




