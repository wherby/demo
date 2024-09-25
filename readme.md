
## start by Mvnw
./mvnw spring-boot:run
visit http://localhost:8090/

## start by Java 
java -jar target/demo-0.0.1-SNAPSHOT.jar
visit  http://localhost:8080/


## api

http://localhost:8080/api/v1/incident/

## GET
GET http://localhost:8080/api/v1/incident/0/3


## POST

POST http://localhost:8080/api/v1/incident/
{"id":0,"type":"Client","description":"Client can't access web","priority":1,"createTime":"2024-03-21","updateTime":"2024-03-25","reportName":"Alice","fixName":"Bob"}

HTTP/1.1 200 OK


POST http://localhost:8080/api/v1/incident/
{"id":2,"type":"Client","description":"Client can't access web","priority":1,"createTime":"2024-03-21","updateTime":"2024-03-25","reportName":"Alice","fixName":"Bob"}


{"timestamp":"2024-09-24T08:34:00.350+00:00","status":500,"error":"Internal Server Error","message":"Id should be set by db","path":"/api/v1/incident/"}

## Put
PUT http://localhost:8080/api/v1/incident/1
{
"type": "Client2",
"description": "Client can't access web",
"priority": 1,
"fixName": "Bob"
}

## DELET

DELETE http://localhost:8080/api/v1/incident/{incidentId}