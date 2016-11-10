package com.jamasoftware.services.restclient.jamadomain.lazyresources;

import com.jamasoftware.services.restclient.jamadomain.JamaDomainObject;

public class JamaUser extends LazyResource {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String title;
    private String location;
    private String licenseType;
    private boolean active;

    @Override
    protected String getResourceUrl() {
        return "users/" + getId();
    }

    @Override
    protected void copyContentFrom(JamaDomainObject jamaDomainObject) {
        checkType(this.getClass(), jamaDomainObject);

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

    public String getUsername() {
        fetch();
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        fetch();
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        fetch();
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        fetch();
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        fetch();
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTitle() {
        fetch();
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        fetch();
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLicenseType() {
        fetch();
        return licenseType;
    }

    public void setLicenseType(String licenseType) {
        this.licenseType = licenseType;
    }

    public boolean isActive() {
        fetch();
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return getUsername();
    }
}
