


## api

http://localhost:8080/api/v1/incident/


## POST

POST http://localhost:8080/api/v1/incident/
{"id":0,"type":"Client","description":"Client can't access web","priority":1,"createTime":"2024-03-21","updateTime":"2024-03-25","reportName":"Alice","fixName":"Bob"}

HTTP/1.1 200 OK


POST http://localhost:8080/api/v1/incident/
{"id":2,"type":"Client","description":"Client can't access web","priority":1,"createTime":"2024-03-21","updateTime":"2024-03-25","reportName":"Alice","fixName":"Bob"}


{"timestamp":"2024-09-24T08:34:00.350+00:00","status":500,"error":"Internal Server Error","message":"Id should be set by db","path":"/api/v1/incident/"}
## DELET

DELETE http://localhost:8080/api/v1/incident/{incidentId}