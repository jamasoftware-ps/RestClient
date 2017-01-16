package com.jamasoftware.services.restclient.jamadomain;

import com.jamasoftware.services.restclient.jamadomain.lazyresources.JamaUser;

import java.util.Date;

public class LockStatus {
    private boolean isLocked;
    private Date lastLocked;
    private JamaUser lockedBy;

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public Date getLastLocked() {
        return lastLocked;
    }

    public void setLastLocked(Date lastLocked) {
        this.lastLocked = lastLocked;
    }

    public JamaUser getLockedBy() {
        return lockedBy;
    }

    public void setLockedBy(JamaUser lockedBy) {
        this.lockedBy = lockedBy;
    }

    @Override
    public String toString(){
        return isLocked ? "Locked" : "Not locked";
    }


}
