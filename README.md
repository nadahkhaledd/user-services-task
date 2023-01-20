# user-services-task

### Microservice for  user services management
A Java Microservice to manage users and their services.

##
### Tools and Technologies used
- Springboot **3.0.0**
- Couchbase
- JUnit4

## 
### Models
- User  
> serialNumber  
> name  
> phoneNumber  
> type(Anonymous, Normal)  
> associated services  

- Service
> uid  
> vendor  
> dateCreated  
> status(Active, Inactive)  

##
### Main endpoints  
#### localhost:8080
- get all users:  (GET, /users)  
- get a user by serialNumber: (GET, /users/{serialNumber})  
- add user: (POST, /users)

### other available endpoints
- get all services  
- get service by uid, status, vendor  
- get user's associated services  
- add service to a user  

### Example of user Json Request

```
{
    "serialNumber": "4321-0987-7654-032j",
    "name":"justin",
    "phoneNumber":"01255554332"
}
``` 

### Example of user Json response 
```
    {
        "serialNumber": {
            "value": "3487-6598-0943-763n"
        },
        "name": "Nadah",
        "phoneNumber": {
            "value": "01387553670"
        },
        "type": "Normal",
        "services": [
            {
                "uid": 11,
                "vendor": "vodafone",
                "dateCreated": "2022-09-15T00:00:00.000+00:00",
                "status": "Inactive"
            },
            {
                "uid": 12,
                "vendor": "eras",
                "dateCreated": "2022-03-12T00:00:00.000+00:00",
                "status": "Active"
            }
        ]
    }
```    
    
