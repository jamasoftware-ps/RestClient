package com.jamasoftware.services.restclient.jamadomain.lazyresources;

import com.jamasoftware.services.restclient.jamadomain.core.JamaDomainObject;
import com.jamasoftware.services.restclient.jamadomain.core.LazyResource;

public class JamaUser extends LazyResource {
    protected String username;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String phone;
    protected String title;
    protected String location;
    protected String licenseType;
    protected boolean active;

    @Override
    protected String getResourceUrl() {
        return "users/" + getId();
    }

    @Override
    protected void copyContentFrom(JamaDomainObject jamaDomainObject) {
        checkType(JamaUser.class, jamaDomainObject);

        JamaUser user = (JamaUser) jamaDomainObject;
        username = user.username;
        firstName = user.firstName;
        lastName = user.lastName;
        email = user.email;
        phone = user.phone;
        title = user.title;
        location = user.location;
        licenseType = user.licenseType;
        active = user.active;
    }

    @Override
    protected void writeContentTo(JamaDomainObject jamaDomainObject) {
        checkType(JamaUser.class, jamaDomainObject);
        ((JamaUser)jamaDomainObject).copyContentFrom(this);
    }

    public String getUsername() {
        fetch();
        return username;
    }

    public String getFirstName() {
        fetch();
        return firstName;
    }

    public String getLastName() {
        fetch();
        return lastName;
    }

    public String getEmail() {
        fetch();
        return email;
    }

    public String getPhone() {
        fetch();
        return phone;
    }

    public String getTitle() {
        fetch();
        return title;
    }

    public String getLocation() {
        fetch();
        return location;
    }

    public String getLicenseType() {
        fetch();
        return licenseType;
    }

    public boolean isActive() {
        fetch();
        return active;
    }

    @Override
    public String toString() {
        return getUsername();
    }



}
