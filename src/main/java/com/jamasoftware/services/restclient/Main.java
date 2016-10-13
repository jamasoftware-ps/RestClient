package com.jamasoftware.services.restclient;

import com.jamasoftware.services.restclient.JamaDomain.lazyresources.Item;
import com.jamasoftware.services.restclient.JamaDomain.JamaInstance;
import com.jamasoftware.services.restclient.JamaDomain.lazyresources.Project;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] ignore) {

        try {
            JamaInstance jamaInstance = new JamaInstance(new JamaConfig(true));
            List<Project> projects = jamaInstance.getProjects();
            List<Item> items = new ArrayList<>();
            for(Project project : projects) {
                items.addAll(project.getItems());
            }
            System.out.println("done");

        } catch(Exception e) {
            e.printStackTrace();
        }

    }
}
