package com.jamasoftware.services.restclient;

import com.jamasoftware.services.restclient.jamadomain.lazyresources.JamaItem;
import com.jamasoftware.services.restclient.jamadomain.JamaInstance;
import com.jamasoftware.services.restclient.jamadomain.lazyresources.JamaProject;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] ignore) {

        try {
            JamaInstance jamaInstance = new JamaInstance(new JamaConfig(true));
            jamaInstance.ping();
            List<JamaProject> projects = jamaInstance.getProjects();
            List<JamaItem> items = new ArrayList<>();
            for(JamaProject project : projects) {
                items.addAll(project.getItems());
            }
            for(JamaItem item : items) {
                System.out.println(item.getName());
            }
            System.out.println("done");

        } catch(Exception e) {
            e.printStackTrace();
        }

    }
}
