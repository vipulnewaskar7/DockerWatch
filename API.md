# CRUD Host connection


## api/savehost

Request:

{
  "requestid":"uuid",
  "user":"someone",
  "time":"timestamp with timezone",
  "message":{
	"id":"uuid",
	"name":"something",
	"address":"somewhere",
  }
}


Response:

{
  "requestid":"uuid",
  "user":"someone",
  "time":"timestamp with timezone",
  "message":{
	"status":"success"
  }
}


## api/updatehost

Request:
{
  "requestid":"uuid",
  "user":"someone",
  "time":"timestamp with timezone",
  "message":{
	"id":"uuid",
	"name":"something",
	"address":"somewhere",
  }
}


Response:
{
  "requestid":"uuid",
  "user":"someone",
  "time":"timestamp with timezone",
  "message":{
	"status":"success"
  }
}


## api/deletehost

Request:
{
  "requestid":"uuid",
  "user":"someone",
  "time":"timestamp with timezone",
  "message":{
	"id":"uuid",
	"name":"something",
	"address":"somewhere",
  }
}


Response:
{
  "requestid":"uuid",
  "user":"someone",
  "time":"timestamp with timezone",
  "message":{
	"status":"success"
  }
}


## Get All Hosts

Request:

{
  "requestid":"uuid",
  "user":"someone",
  "time":"timestamp with timezone",
  "message":{
	"id":"uuid",
	"name":"something",
	"address":"somewhere",
  }
}


Response:

{
  "requestid":"uuid",
  "user":"someone",
  "time":"timestamp with timezone",
  "message":{
	"Hosts":[
           {
             "id":"uuid",
	     "name":"something",
	     "address":"somewhere",
             "status":"connected"
           },
           {
             "id":"uuid",
	     "name":"something",
	     "address":"somewhere",
             "status":"disconnected"
           },
           {
             "id":"uuid",
	     "name":"something",
	     "address":"somewhere",
             "status":"disconnected"
           }
        ]
  }
}


