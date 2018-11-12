# company-cab
Manage cab transportation services for employees of a company 

This application is built on Spring Boot with JPA. It makes use of the in-memoty H2 DB as the backend storage.

The swagger documentation is available at this link
http://localhost:8000/swagger-ui.html

Some Assumptions considered:
1)  Create droppoint request will contain All existing droppoints + new droppoints to be saved.
2)  Delete droppoint need not have all droppoints
3) Distance between all drop points wll be mentiond in the request based on he number of droppoints
4) Delete request is created only for droppoint resource.
5) Update request is not implemented for all the 4 resources.



Below are the sample requests for all create services. THE Urls for GET services can be obtained from swagger-ui.

Sample requests to create droppoint
URL : http://localhost:8000/drop_points 
Method: POST
{
"target_headquarter" :"0,1,2",
"pointA" :"1,0,1",
"pointB" :"2,1,0"
}

Sample requests to delete droppoint
URL : http://localhost:8000/drop_points 
Method: DELETE
{
"target_headquarter" :"0,1",
"pointA" :"1,0"
}

Sample requests to create a cab
URL : http://localhost:8000/cabs 
Method: POST
{
	"cost":"2",
	"capacity":"5",
	"droppoints":"target_headquarter,pointA,pointB",
	"lastDroppoint":"pointB"
}

Sample request for team member registration
URL : http://localhost:8000/register 
Method: POST
{
	"id":"1",
	"gender":"M",
	"droppointname":"pointB"
}






