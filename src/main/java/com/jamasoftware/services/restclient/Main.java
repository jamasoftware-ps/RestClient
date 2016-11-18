package com.jamasoftware.services.restclient;

import com.jamasoftware.services.restclient.jamadomain.lazyresources.JamaItem;
import com.jamasoftware.services.restclient.jamadomain.JamaInstance;
import com.jamasoftware.services.restclient.jamadomain.lazyresources.JamaProject;
import com.jamasoftware.services.restclient.jamadomain.lazyresources.JamaRelationship;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class Main {
    public static void main(String[] ignore) {

        try {
            // TODO fail good (John) attempted to retireve item types for invalid item
            JamaInstance jamaInstance = new JamaInstance(new JamaConfig(true));
            JamaItem item = new JamaItem();
            item.associate(2119533, jamaInstance);
            byte[] imageData = item.getItemTypeImage();
            FileOutputStream fos = new FileOutputStream("out.png");
            fos.write(imageData);
            fos.close();

//            System.out.println(item.isLocked());
//            System.out.println(item.lockedBy());
//            item.lock();
//            System.out.println(item.isLocked());
//            System.out.println(item.lockedBy());
//            List<JamaProject> projects = jamaInstance.getProjects();
//            JamaProject aProject = projects.get(0);
//            List<JamaItem> items = aProject.getItems();
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
