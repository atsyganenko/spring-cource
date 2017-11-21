package com.booking.beans.models;

import com.booking.beans.helpers.xml.LocalDateAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "http://booking.com/booking-web-service", propOrder = {"id", "name", "email", "birthday", "roles", "encryptedPassword"})
public class User {

    @XmlAttribute
    private long id;

    @XmlElement(required = true)
    private String email;

    @XmlElement(required = true)
    private String name;

    @XmlElement(required = true)
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate birthday;
    @XmlElement(required = true)
    private String encryptedPassword;

    @XmlElement(required = true)
    private String roles;
    @XmlTransient
    private UserAccount account;

    public User() {
    }

    public User(long id, String name, String email, LocalDate birthday) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.birthday = birthday;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }


    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public Set<String> getRolesSet() {
        return new HashSet<>(Arrays.asList(roles.split(",")));
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getRoles() {
        return Optional.ofNullable(roles).orElse("");
    }

    public void addRole(String role) {
        this.roles = this.roles == null ? role : String.format("%s,%s", this.roles, role);
    }

    public UserAccount getAccount() {
        return account;
    }

    public void setAccount(UserAccount account) {
        this.account = account;
    }

    public User(String email, String name, LocalDate birthday) {
        this(-1, name, email, birthday);
    }

    public User withId(long id) {
        return new User(id, name, email, birthday);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        User user = (User) o;

        if (id != user.id)
            return false;
        if (email != null ? !email.equals(user.email) : user.email != null)
            return false;
        if (name != null ? !name.equals(user.name) : user.name != null)
            return false;
        return birthday != null ? birthday.equals(user.birthday) : user.birthday == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
