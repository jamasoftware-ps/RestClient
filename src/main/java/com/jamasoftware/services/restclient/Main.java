package com.jamasoftware.services.restclient;

import com.jamasoftware.services.restclient.exception.RestClientException;
import com.jamasoftware.services.restclient.jamadomain.lazyresources.JamaItem;
import com.jamasoftware.services.restclient.jamadomain.core.JamaInstance;
import com.jamasoftware.services.restclient.jamadomain.lazyresources.JamaProject;
import com.jamasoftware.services.restclient.jamadomain.stagingresources.StagingItem;

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

    public static void main(String[] ignore) {

        try {
            // TODO fail good (John) attempted to retireve item types for invalid item
            JamaInstance jamaInstance = new JamaInstance(new JamaConfig(true));

//            JamaItem jamaItem = new JamaItem();
//            jamaItem.associate(1972342, jamaInstance);
//            System.out.println(jamaItem);
//            JamaProject jamaProject = new JamaProject();
//            jamaProject.associate(20183, jamaInstance);
            JamaProject jamaProject = jamaInstance.getProject(20183);
//
//            JamaItem jamaItem = jamaInstance.getItem(2119559);
//            jamaItem.edit()
//                    .setFieldValue("status", "approved")
//                    .commit();
//            for(JamaFieldValue value : jamaItem.getFieldValues()) {
//                System.out.println(value);
//            }


            JamaItem component = jamaInstance
                    .createItem("John Component", jamaProject, jamaInstance.getItemType("Component"))
                    .commit();

            JamaItem set = jamaInstance
                    .createItem("John Set", component, jamaInstance.getItemType("Set"))
                    .setChildItemType(jamaInstance.getItemType("Requirement"))
                    .setFieldValue("setKey", jamaInstance.getItemType("Requirement").getTypeKey())
                    .commit();

            JamaItem item = jamaInstance
                    .createItem("John Item", set, jamaInstance.getItemType("Requirement"))
                    .setFieldValue("description", "hi nathan")
                    .commit();

            StagingItem stagingItem = item.edit();
            stagingItem.setFieldValue("status", "approved");
            stagingItem.commit();
            stagingItem.setFieldValue("BREAK", "BUSTED");


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
//            List<JamaProject> projects = jamaInstance.getProjects();
//            JamaProject aProject = projects.get(0);
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
//            List<JamaProject> projects = jamaInstance.getProjects();
//            List<JamaItem> items = new ArrayList<>();
//            for(JamaProject project : projects) {
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
