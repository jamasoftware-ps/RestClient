package com.jamasoftware.services.restclient.jamadomain.stagingresources;

import com.jamasoftware.services.restclient.exception.RestClientException;
import com.jamasoftware.services.restclient.jamadomain.lazyresources.JamaUser;

public class StagingUser extends JamaUser implements StagingResource {

    public StagingUser setUsername(String username) {
        this.username = username;
        return this;
    }

    public StagingUser setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public StagingUser setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public StagingUser setEmail(String email) {
        this.email = email;
        return this;
    }

    public StagingUser setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public StagingUser setTitle(String title) {
        this.title = title;
        return this;
    }

    public StagingUser setLocation(String location) {
        this.location = location;
        return this;
    }

    public StagingUser setLicenseType(String licenseType) {
        this.licenseType = licenseType;
        return this;
    }

    @Override
    protected String getEditUrl() throws RestClientException {
        return "users/" + getId();
    }

    @Override
    protected String getCreateUrl() throws RestClientException {
        return "users/";
    }
}
