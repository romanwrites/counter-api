# Counter REST API

## Run
```
mvn package
docker build -t counters-api .
docker run -p 8080:8080 counters-api
```

## API
```
Get counter by id
GET /counters/{id}

Get all counters
GET /counters/{id}

Get sum of all counters values
GET /counters/{id}

Add new counter
POST /counters

Increment counter value by id
PUT /counters/increment/{id}

Remove counter by id
DELETE /counters/{id}
```
