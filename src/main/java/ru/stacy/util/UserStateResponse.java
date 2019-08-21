package ru.stacy.util;

public class UserStateResponse {
    private Long id;
    private Boolean oldUserState;
    private Boolean newUserState;

    public UserStateResponse() {}

    public UserStateResponse(Long id, Boolean oldUserState, Boolean newUserState) {
        this.id = id;
        this.oldUserState = oldUserState;
        this.newUserState = newUserState;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getOldUserState() {
        return oldUserState;
    }

    public void setOldUserState(Boolean oldUserState) {
        this.oldUserState = oldUserState;
    }

    public Boolean getNewUserState() {
        return newUserState;
    }

    public void setNewUserState(Boolean newUserState) {
        this.newUserState = newUserState;
    }
}
