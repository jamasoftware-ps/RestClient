#Jama Software
Jama Software is the definitive system of record and action for product development. The company’s modern requirements and test management solution helps enterprises accelerate development time, mitigate risk, slash complexity and verify regulatory compliance. More than 600 product-centric organizations, including NASA, Boeing and Caterpillar use Jama to modernize their process for bringing complex products to market. The venture-backed company is headquartered in Portland, Oregon. For more information, visit [jamasoftware.com](http://jamasoftware.com).

Please visit [dev.jamasoftware.com](http://dev.jamasoftware.com) for additional resources and join the discussion in our community [community.jamasoftware.com](http://community.jamasoftware.com).

## RestClient
RestClient is a Java REST API client application for Jama Software customers. The client will allow customers to easily access the REST API to retrieve, and modify data within their Jama Instance. 
The client will be continuously updated in hopes of creating a more robust client that addresses all endpoints available through the API.

Please note that this client is distributed as-is as an example and will likely require modification to work for your specific use-case.


## Requirements
- Module SDK `Java 1.8` or higher.
- Language level `7 - Diamonds, ARM, multi-catch etc.`


## Setup
1. As always, set up a test environment and test project.

2. Fill out the CONFIG section of the jama.properties file.  The necessary fields are:
  - ```username```
  - ```password```
  - ```base_url```
  - ```resourceTimeOut```     - refresh time for items
  
3. Change the name of the `jama.properties.dist`  to  `jama.properties`



### REST Calls Supported in the Client

#####Projects: 
- GET all projects 
- GET project by ID

#####Item Types
- GET all Item Types in a Jama instance 
- GET an Item Type by id
- GET an Item Type by name
- GET an Item Type's image

#####Items
- GET all items by project 
- PUT/POST items by project
- GET/PUT/DELETE item locks
- Change an item's location 
- GET all downstream/upstream related items

#####Relationship Types
- GET all relationship types in a project

#####Relationships
- GET all relationships in a Jama project 
- GET a relationship's upstream/downstream items
- GET all upstream/downstream relationships of an item
- GET upstream/downstream item of a relationship



## Usage Examples



## Version
Version 1.0 released on March 1, 2017

