# Company Cab Transport Services
This application enables managing the cab transportation services for employees of company ABCD. 

Company ABCD offers cab service for employees to drop them to drop-points nearest to their home. Each employee has to register to the travel portal to avail this service and select the drop point. The travel team staff make use of the application to:
* Configure the number of cabs available along with their cost and capacity
* Configure the available drop points
* Distance from company headquarters to the drop points 
* Distance from one drop point to another.

## Approach
* REST Services - Spring Boot with JPA
* Backend storage - H2 in-memory DB
* Endpoint and API documentation - Swagger UI 

## Documentation
The swagger documentation is available at the following link, once the application is launched:
http://localhost:8000/swagger-ui.html

## Assumptions:
1) Create droppoint request will contain all existing droppoints + new droppoints to be saved.
2) Delete droppoint need not have all droppoints. Droppoints which are not present in the request will be deleted.
3) Distance between all drop points wll be mentiond in the request.

## Usage
Below are the sample requests for create services to get started. THE URLs for GET and DELETE requests can be obtained from swagger-ui.

* Sample requests to create droppoint

URL: http://localhost:8000/drop_points 

Method: `POST`

```
{
"abcd_headquarter" :"0,1,2",
"pointA" :"1,0,1",
"pointB" :"2,1,0"
}
```

* Sample requests to delete droppoint

URL: http://localhost:8000/drop_points

Method: `DELETE`
```
{
"abcd_headquarter" :"0,1",
"pointA" :"1,0"
}
```

* Sample requests to create a cab

URL: http://localhost:8000/cabs 

Method: `POST`
```
{
	"cost":"2",
	"capacity":"5",
	"droppoints":"abcd_headquarter,pointA,pointB",
	"lastDroppoint":"pointB"
}
```

* Sample request for team member registration

URL: http://localhost:8000/register 

Method: `POST`
```
{
	"id":"1",
	"gender":"M",
	"droppointname":"pointB"
}
```

## Future Enhancements
* Delete request is created only for droppoint resource.
* Update request is not implemented for all the 4 resources.
