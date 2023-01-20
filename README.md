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
