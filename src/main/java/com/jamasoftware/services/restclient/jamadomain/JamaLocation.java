package com.jamasoftware.services.restclient.jamadomain;

import com.jamasoftware.services.restclient.JamaParent;

public class JamaLocation {
    private Integer sortOrder;
    private Integer globalSortOrder;
    private String sequence;
    private JamaParent parent;

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Integer getGlobalSortOrder() {
        return globalSortOrder;
    }

    public void setGlobalSortOrder(Integer globalSortOrder) {
        this.globalSortOrder = globalSortOrder;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public JamaParent getParent() {
        return parent;
    }

    public void setParent(JamaParent parent) {
        this.parent = parent;
    }
}
