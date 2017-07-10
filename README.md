# Jama Software
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
#### GET all Item Types in a Jama Instance 
```
try {

    JamaInstance jamaInstance = new JamaInstance(new JamaConfig(true));
    List<JamaItemType> jamaItemTypes = jamaInstance.getItemTypes();
    System.out.println("Listing all Jama Instance Item Type names and IDs:");
    for (JamaItemType jamaItemType : jamaItemTypes) {
        System.out.println(jamaItemType.getDisplayPlural() + " with API ID " + jamaItemType.getId());
    }
} catch(RestClientException e) {
    e.printStackTrace();
}
```
#### GET all Fields of a Jama Item Type
```
try {

    JamaInstance jamaInstance = new JamaInstance(new JamaConfig(true));
    JamaItem jamaItem = jamaInstance.getItem(1972331);
    JamaItemType itemType = jamaItem.getItemType();
    List<JamaField> jamaFields = itemType.getFields();
    for(JamaField jamaField : jamaFields) {
        System.out.println("Field label: " +  jamaField.getLabel());
    }
} catch (RestClientException e) {
    e.printStackTrace();
}
```
#### GET all Projects in a Jama Instance
```
try {
    JamaInstance jamaInstance = new JamaInstance(new JamaConfig(true));
    ArrayList<JamaProject> projects = (ArrayList<JamaProject>) jamaInstance.getProjects();
    System.out.println("Listing all Project names and IDs:");
    for (JamaProject project : projects) {
        System.out.println(project.getName() + " with API ID " + project.getId());
    }
} catch(RestClientException e) {
        e.printStackTrace();
}
```
#### GET all Relationships in a Jama Project
```
try {

    JamaInstance jamaInstance = new JamaInstance(new JamaConfig(true));
    JamaProject project = jamaInstance.getProject(20183);
    List<JamaRelationship>  jamaRelationships = project.getRelationships();
    System.out.println("Listing all Relationships in Project");
    for (JamaRelationship jamaRelationship : jamaRelationships) {
        System.out.println(
                "Relationship ID: " + jamaRelationship.getId() 
                + " Upstream Item ID: " + jamaRelationship.getFromItem().getId() 
                + "Downstream ItemID: " + jamaRelationship.getToItem().getId());
    }
} catch(RestClientException e) {
    e.printStackTrace();
}
```
#### GET all Items in a Jama Project
```
try {

    JamaInstance jamaInstance = new JamaInstance(new JamaConfig(true));
    JamaProject project = jamaInstance.getProject(20183);
    List<JamaItem> jamaItems = project.getItems();
    System.out.println("Listing all Project Item names and IDs:");
    for (JamaItem jamaItem : jamaItems) {
        System.out.println(jamaItem.getName() + " with API ID " + jamaItem.getId());
    }
} catch(RestClientException e) {
    e.printStackTrace();
}
```
#### Update a Jama Item's Description
```
try {

    JamaInstance jamaInstance = new JamaInstance(new JamaConfig(true));
    JamaItem jamaItem = jamaInstance.getItem(2120043);
    JamaItem newParent = jamaInstance.getItem(2120044);
    System.out.println("Original parent ID: " + jamaItem.getParent().getId());
    jamaItem.edit().setParent(newParent).commit();
    System.out.println("Updated parent ID: " + jamaItem.getParent().getId());
} catch (RestClientException e) {
    e.printStackTrace();
}
```
#### Change a Jama Item's Location in a Project's Hierarchy Tree
```
try {
        JamaInstance jamaInstance = new JamaInstance(new JamaConfig(true));
        ArrayList<JamaProject> projects = (ArrayList<JamaProject>) jamaInstance.getProjects();
        System.out.println("Listing all project names and IDs:");
        for (JamaProject project : projects) {
            System.out.println(project.getName() + " with API ID " + project.getId());
        }
    } catch(RestClientException e) {
            e.printStackTrace();
    }
```
#### GET all Downstream Related Items from a Jama Item
```
try {

    JamaInstance jamaInstance = new JamaInstance(new JamaConfig(true));
    JamaItem jamaItem = jamaInstance.getItem(2120042);
    List<JamaItem> downstreamRelatedItems = jamaItem.getDownstreamItems();
    System.out.println("Listing all Downstream Related Items from Item: ");
    for (JamaItem downstreamRelatedItem : downstreamRelatedItems) {
        System.out.println("Item : " + downstreamRelatedItem.getName() + " with ID: " + downstreamRelatedItem.getId());
    }
} catch(RestClientException e) {
    e.printStackTrace();
}
```
#### Lock/Unlock a Jama Item
```
try {

    JamaInstance jamaInstance = new JamaInstance(new JamaConfig(true));
    JamaItem jamaItem = jamaInstance.getItem(2120042);
    jamaItem.lock();
    System.out.println(jamaItem.isLocked());
    jamaItem.unlock();
    System.out.println(jamaItem.isLocked());
} catch(RestClientException e) {
    e.printStackTrace();
}
```
