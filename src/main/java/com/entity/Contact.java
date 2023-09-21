package com.entity;

import java.io.Serializable;

public class Contact implements Serializable {
    private static final long serialVersionUID = 357594945855586053L;
    private Integer contactId;

    private String contactName;

    private String contactEmail;

    private String contextMessage;

    private Integer userId;

    public Contact(Integer contactId, String contactName, String contactEmail, String contextMessage, Integer userId) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
        this.contextMessage = contextMessage;
        this.userId = userId;
    }

    public Contact() {
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContextMessage() {
        return contextMessage;
    }

    public void setContextMessage(String contextMessage) {
        this.contextMessage = contextMessage;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "contactId=" + contactId +
                ", contactName='" + contactName + '\'' +
                ", contactEmail='" + contactEmail + '\'' +
                ", contextMessage='" + contextMessage + '\'' +
                ", userId=" + userId +
                '}';
    }
}