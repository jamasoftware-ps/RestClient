package com.jamasoftware.services.restclient.json;

import com.jamasoftware.services.restclient.exception.JsonException;
import com.jamasoftware.services.restclient.exception.RestClientException;
import com.jamasoftware.services.restclient.jamadomain.core.JamaInstance;

public class ItemTypeImage {
    private String imageUrl;
    private byte [] image;
    private JamaInstance jamaInstance;

    ItemTypeImage(){}

    public byte[] getImage() throws JsonException {
        try {
            image = jamaInstance.retrieveItemTypeImage(imageUrl);
        } catch (RestClientException e) {
            throw new JsonException(e);
        }
        return image;
    }

    public void setJamaInstance(JamaInstance jamaInstance) {
        this.jamaInstance = jamaInstance;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
