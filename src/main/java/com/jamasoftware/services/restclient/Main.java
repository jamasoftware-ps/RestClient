package com.jamasoftware.services.restclient;

import com.jamasoftware.services.restclient.jamadomain.lazyresources.JamaItem;
import com.jamasoftware.services.restclient.jamadomain.JamaInstance;
import com.jamasoftware.services.restclient.jamadomain.lazyresources.JamaItemType;
import com.jamasoftware.services.restclient.jamadomain.lazyresources.JamaProject;
import com.jamasoftware.services.restclient.jamadomain.lazyresources.JamaRelationship;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] ignore) {

        try {
            JamaInstance jamaInstance = new JamaInstance(new JamaConfig(true));
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
//            List<JamaRelationship> relationships = items.get(1).getDownstream();
            JamaItem item = jamaInstance.getItem(1972370);
            item.getName();

            System.out.println("done");

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
