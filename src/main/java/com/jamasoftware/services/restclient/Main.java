package com.jamasoftware.services.restclient;

import com.jamasoftware.services.restclient.exception.RestClientException;
import com.jamasoftware.services.restclient.jamadomain.core.JamaInstance;
import com.jamasoftware.services.restclient.jamadomain.fields.*;
import com.jamasoftware.services.restclient.jamadomain.lazyresources.JamaItem;
import com.jamasoftware.services.restclient.jamadomain.lazyresources.JamaItemType;
import com.jamasoftware.services.restclient.jamadomain.lazyresources.JamaProject;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class Main {
    public static void printAll(JamaParent jamaParent, int indent) throws RestClientException {
        String indentString = "";
        for(int i = 0; i < indent; ++i) {
            indentString += "==";
        }
        if(jamaParent.isProject()) {
            JamaProject project = (JamaProject)jamaParent;
            System.out.println(indentString + project.getName() + " - " + project.getId());
        } else {
            JamaItem item = (JamaItem)jamaParent;
            System.out.println(indentString + item.getName() + " - " + item.getSequence());
        }
        for(JamaItem child : jamaParent.getChildren()) {
            printAll(child, indent + 1);
        }
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] ignore) throws UnsupportedEncodingException, RestClientException {



        try {
            // TODO fail good (John) attempted to retireve item types for invalid item
            JamaInstance jamaInstance = new JamaInstance(new JamaConfig(true));



//            System.out.println(new String(fieldValue.getValue().toString().getBytes("UTF-8")));
//            assertTrue(unicodeString.equals(fieldValue.getValue().toString()));
//            PrintStream out = new PrintStream(System.out, true, "UTF-8");
//
//            out.println("Unicoded: " + fieldValue.getValue().toString());
//
//            out.println("description: " + jamaItem.getFieldValueByName("description"));
            System.out.println("done");



//            itemA.edit().setFieldValue("description", unicodeString).commit();
//            JamaFieldValue fieldValue = itemA.getFieldValueByName("description");
//            System.out.println(fieldValue.getValue());
//            assertTrue(unicodeString.equals(fieldValue.getValue().toString()));

            JamaItemType itemType = jamaInstance.getItemType(89009);
            ArrayList<JamaField> fields = (ArrayList<JamaField>) itemType.getFields();
            for(JamaField field : fields) {
                if(field.type.equals("DATE")) {
                    System.out.println(field.getValue().getName());
                }
            }
//            String description = jamaItem.getFieldValueByName("description").getValue().toString();
//            updated = updated.edit().setFieldValue("description", description).commit();
//            System.out.println(updated.getFieldValueByName("description"));
//            System.out.println("done");

//            System.out.println("Jama item is " + jamaItem.isLocked() + " lock status");
//            System.out.println("Jama item is locked by : " + jamaItem.lockedBy().getUsername());
//            System.out.println("Jama item is " + jamaItem.isLockedByCurrentUser() + " locked by current user");
//            System.out.println("Now unlocking item");
//
////            for org admin users with override capabilities:
////            jamaItem.unlock();
////            jamaItem.lock();     //optional to acquire lock on the item
//
////            for non org admin users, they will need to verify unlocking/acquiring lock before proceeding:
//            System.out.println(jamaItem.releaseLock());     //will return false if item could not be unlocked, true otherwise
//            System.out.println(jamaItem.acquireLock());     //will return true if item was locked, false otherwise
//
//            System.out.println("Status to release lock : " + jamaItem.releaseLock());
//            System.out.println("I have gotten the lock with status : " + jamaItem.acquireLock());
//            System.out.println("Jama item is " + jamaItem.isLocked() + " lock status");
//            System.out.println("Jama item is " + jamaItem.isLockedByCurrentUser() + " locked by current user");


//            JamaItem newParentFolder = jamaInstance.getItem(1972340);
//            JamaParent jamaParent = jamaItem.getParent();
//            System.out.println("Jama item: " + jamaItem.toString() + " has parent: " + jamaParent.toString());
//
//            System.out.println("And this item's open url is : " + jamaInstance.getOpenUrl(jamaItem));
//            JamaProject jamaProject = jamaInstance.getProject(2120041);
//
////            System.out.println("New jama parent is: " + newParentFolder.toString());


//            System.out.println(jamaItem.getName());
//            System.out.println("Added child to new parent");
//            System.out.println("NOW:: Jama item: " + jamaItem.toString() + " has parent: " + jamaItem.getParent().toString());

//            jamaItem.associate(1972342, jamaInstance);
//            System.out.println(jamaItem);
//            JamaProject jamaProject = new JamaProject();
//            jamaProject.associate(20183, jamaInstance);
//            JamaItemType jamaItemType = new JamaItemType();
//            jamaItemType.associate(89029, jamaInstance);
//          //  System.out.println(jamaItemType.getDisplay());
//            jamaItemType.getImage();

//            JamaProject jamaProject = jamaInstance.getProject(20183);
//            jamaInstance.getItemTypes();

//            JamaItem jamaItem = jamaInstance.getItem(2119354);
//            jamaItem.getFieldValues();
//            JamaFieldValue fieldValue = jamaItem.getFieldValueByName("description");
//            System.out.println(fieldValue.toString());
//            jamaInstance.createItem("name", JamaParent, JamaIteType)
//            List<JamaRelationshipType> relationshipTypes = jamaInstance.getRelationshipTypes();

//            JamaProject jamaProject = jamaInstance.getProject(20183);
//            List<JamaItem> children = jamaProject.getChildren();
//            for(JamaItem j : children) {
//                System.out.println(j.toString());
//            }

//            jamaInstance.createRelationship(relationshipId, fromItem, toItem);
//            jamaItem.edit().setFieldValue("Name", "Timbuktoo").setFieldValue("description", "Not your description;=").commit();
//            jamaItem.edit()
//                    .setFieldValue("status", "approved")
//                    .commit();
//            for(JamaFieldValue value : jamaItem.getFieldValues()) {
//                System.out.println(value);
//            }

//
//            JamaItem component = jamaInstance
//                    .createItem("John Component", jamaProject, jamaInstance.getItemType("Component"))
//                    .commit();
//
//            jamaInstance.createItem();
//
//            JamaItem set = jamaInstance
//                    .createItem("John Set", component, jamaInstance.getItemType("Set"))
//                    .setChildItemType(jamaInstance.getItemType("Requirement"))
//                    .setFieldValue("setKey", jamaInstance.getItemType("Requirement").getTypeKey())
//                    .commit();
//            set.getItemTypeImage();
//            set.getChildItemType().getImage();
//
//            JamaItem item = jamaInstance
//                    .createItem("John Item", set, jamaInstance.getItemType("Requirement"))
//                    .setFieldValue("description", "hi nathan")
//                    .commit();
//
//            item.getItemType().getImage();



//            StagingItem stagingItem = item.edit();
//            stagingItem.setFieldValue("status", "approved");
//            stagingItem.commit();
//            stagingItem.setFieldValue("BREAK", "BUSTED");


//
//            System.out.println(component.getDocumentKey());
//            System.out.println(set.getDocumentKey());
//            System.out.println(item.getDocumentKey());
//
//            jamaItem.edit()
//                    .setName("NEW NAME ALERT")
//                    .setFieldValue("description", "textblahblhablhasdbkljhsdflkhjasfkljhsd")
//                    .commit();

//            jamaItem.edit()
//                    .setName("Edited")
//                    .setDescription("Desc")
//                    .commit();
//            jamaInstance.createItem("name", jamaProject, jamaInstance.getItemType("Text"))
//                    .setName("Day before Thanksgiving")
//                    .commit();

//            jamaItem.associate(2119331, jamaInstance);

//            System.out.println("Item is: " + jamaItem);
//            System.out.println("Parent is: " + jamaItem.getParent());
//            System.out.println("blah");
//            List<JamaItem> children = jamaItem.getChildren();
//            System.out.println("CHildren are : ");
//            for(JamaItem item: children){
//                System.out.println(item);
//            }
//            printAll(jamaItem, 0);
//            printAll(jamaItem, 0);
//            JamaItem item = new JamaItem();
//            item.associate(2119533, jamaInstance);
//            byte[] imageData = item.getItemTypeImage();
//            FileOutputStream fos = new FileOutputStream("out.png");
//            fos.write(imageData);
//            fos.close();

//            System.out.println(item.isLocked());
//            System.out.println(item.lockedBy());
//            item.lock();
//            System.out.println(item.isLocked());
////            System.out.println(item.lockedBy());
//            List<JamaProject> hostedProjects = jamaInstance.getProjects();
//            JamaProject aProject = hostedProjects.get(0);
//            List<JamaItem> items = aProject.getItems();
//            for(JamaItem item : items){
//                System.out.println(item);
//            }
//            for(JamaItem item : items) {
//                if(item.getName().toString().contains("dont care")) {
//                    item.forceLockItem();
//                    if(item.isLockedByCurrentUser())
//                        System.out.println("DONZO");
////                    JamaItem upItem = item.getUpstreamItems().get(0);
//                    System.out.println(upItem);
//                    System.out.println("-----");
//                    System.out.println(upItem.getDownstreamItems().get(0));
//                    System.out.println("Hellow");




//            List<JamaRelationship> relationships = aProject.getRelationships();
//            JamaRelationship relationship = relationships.get(0);
//            System.out.println(relationship.getFromItem());
//            System.out.println(relationship.getToItem());
//            System.out.println(relationship.getRelationshipType());

//            JamaProject jamaProject = new JamaProject();
//            jamaProject.associate(20540, jamaInstance);
//            List<JamaItemType> types = jamaInstance.getItemTypes();
//            JamaItem jamaItem = jamaInstance.getItem(2169992);
//            System.out.println(jamaItem.getName());
//            jamaInstance.ping();
//            JamaProject jamaProject = null;
//            List<JamaProject> hostedProjects = jamaInstance.getProjects();
//            List<JamaItem> items = new ArrayList<>();
//            for(JamaProject project : hostedProjects) {
//                if(project.getName().equals("zzzzz")) {
//                    jamaProject = project;
//                    items.addAll(project.getItems());
//                }
//            }
//            List<JamaItem> items = jamaProject.getItems();
//
//            for (JamaItem item : items) {
//                System.out.println(item.getName());
//            }
//
//            List<JamaRelationship> relationships = items.get(1).getDownstreamRelationships();
            //JamaItem item = jamaInstance.getItem(1972370);
//            item.getName();

            System.out.println("done");

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
