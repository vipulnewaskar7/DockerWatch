# CRUD Host connection

## /api/v1/images - GET

Response:
```
[
    {
        "Created": 1608071572,
        "Id": "sha256:b4b9bf31ec035024f036a42d33857f1ee1eeae11e652a03bb1ed2b2f82e712ea",
        "ParentId": "",
        "RepoTags": [
            "python:3.8-buster"
        ],
        "RepoDigests": [
            "python@sha256:cd7610a0e50e32d7c43c7e86ab4f78a0311461d835ecba4c92ac552348b36cf0"
        ],
        "Size": 881985858,
        "VirtualSize": 881985858,
        "SharedSize": -1,
        "Labels": null,
        "Containers": -1
    },
    {
        "Created": 1608063743,
        "Id": "sha256:686282c805911045b707b253f89698d2fc3fe07d6625e33826c1d3cee2d20204",
        "ParentId": "",
        "RepoTags": [
            "nginx:1.19.6-alpine"
        ],
        "RepoDigests": [
            "nginx@sha256:260bab5962c53602e8ca516b430fe7981ee0d79c137b94d2c50a8e0df3dc1a9d"
        ],
        "Size": 22334395,
        "VirtualSize": 22334395,
        "SharedSize": -1,
        "Labels": {
            "maintainer": "NGINX Docker Maintainers <docker-maint@nginx.com>"
        },
        "Containers": -1
    }
]
```

## /api/v1/containers - GET 

Response:
```
[
{
    "Command": "/docker-entrypoint.sh nginx -g 'daemon off;'",
    "Created": 1610786654,
    "Id": "2213415f3393727b0cdf479d6e60d80aa8c64171521524e6d7bb6350ec047abe",
    "Image": "nginx:1.19.6-alpine",
    "ImageID": "sha256:686282c805911045b707b253f89698d2fc3fe07d6625e33826c1d3cee2d20204",
    "Names": [
        "/docker-demo_nginx_1"
    ],
    "Ports": [
        {
            "IP": "0.0.0.0",
            "PrivatePort": 80,
            "PublicPort": 80,
            "Type": "tcp"
        }
    ],
    "Status": "Up 14 seconds",
    "State": "running",
    "SizeRw": null,
    "SizeRootFs": null,
    "HostConfig": {
        "NetworkMode": "docker-demo_default"
    },
    "Mounts": [
        {
            "Name": null,
            "Source": "/run/desktop/mnt/host/d/Downloads/chrome/demo/docker-demo/nginx/nginx.conf",
            "Destination": "/etc/nginx/nginx.conf",
            "Driver": null,
            "Mode": null,
            "RW": false,
            "Propagation": "rprivate"
        }
    ]
},
{
    "Command": "yarn start",
    "Created": 1610786653,
    "Id": "a09998903b85f29c4d677cef2b220a09da87f2556a2f4e9534f2f4e162bcfaff",
    "Image": "demo/frontend",
    "ImageID": "sha256:273b214a7fa244060d5eeb23153521c19207f9b89a48fd07e2b78be51bac6cb8",
    "Names": [
        "/docker-demo_frontend_1"
    ],
    "Ports": [
        {
            "IP": "0.0.0.0",
            "PrivatePort": 3000,
            "PublicPort": 49153,
            "Type": "tcp"
        }
    ],
    "Status": "Up 14 seconds",
    "State": "running",
    "SizeRw": null,
    "SizeRootFs": null,
    "HostConfig": {
        "NetworkMode": "docker-demo_default"
    },
    "Mounts": []
}
]
```


## api/savehost

Request:
```
{
  "requestid":"uuid",
  "user":"someone",
  "time":"timestamp with timezone",
  "message":{
	"name":"something",
	"address":"somewhere",
  }
}
```

Response:
```
{
  "requestid":"uuid",
  "user":"someone",
  "time":"timestamp with timezone",
  "message":{
	"status":"success"
  }
}
```
## api/login

Request:
```
{
    "requestid":"123",
    "user":"default",
    "message":{
        "username":"default",
        "password":"Rahul@123"
    }
}
```

Response:
```
{
   "requestid":"123",
   "user":"default",
   "timestamp":1614514811,
   "message":{
      "status":"success",
      "message":"User Logged In"
   }
}
```

## api/updatehost

Request:
```
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
```

Response:
```
{
  "requestid":"uuid",
  "user":"someone",
  "time":"timestamp with timezone",
  "message":{
	"status":"success"
  }
}
```

## api/deletehost
```
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
```

Response:
```
{
  "requestid":"uuid",
  "user":"someone",
  "time":"timestamp with timezone",
  "message":{
	"status":"success"
  }
}
```

## Get All Hosts

Request:
```
{
  "requestid":"uuid",
  "user":"someone",
  "time":"timestamp with timezone"
}
```

Response:
```
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
```

