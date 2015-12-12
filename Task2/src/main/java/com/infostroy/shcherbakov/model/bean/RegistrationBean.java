package com.infostroy.shcherbakov.model.bean;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class RegistrationBean implements Serializable {


    private String name;

    private String lastName;

    private String email;

    private String password;

    private String repeatedPassword;

    private String avatarName;

    public RegistrationBean(){

    }

    public RegistrationBean(String name, String lastName, String email, String password, String repeatedPassword, String avatarName) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.repeatedPassword = repeatedPassword;
        this.avatarName = avatarName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatedPassword() {
        return repeatedPassword;
    }

    public void setRepeatedPassword(String repeatedPassword) {
        this.repeatedPassword = repeatedPassword;
    }

    public String getAvatarName() {
        return avatarName;
    }

    public void setAvatarName(String avatarName) {
        this.avatarName = avatarName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        RegistrationBean that = (RegistrationBean) o;

        return new EqualsBuilder()
                .append(name, that.name)
                .append(lastName, that.lastName)
                .append(email, that.email)
                .append(password, that.password)
                .append(repeatedPassword, that.repeatedPassword)
                .append(avatarName, that.avatarName)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(name)
                .append(lastName)
                .append(email)
                .append(password)
                .append(repeatedPassword)
                .append(avatarName)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("lastName", lastName)
                .append("email", email)
                .append("password", password)
                .append("repeatedPassword", repeatedPassword)
                .append("avatarName", avatarName)
                .toString();
    }
}