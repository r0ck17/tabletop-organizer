package by.javaguru.entity;

import java.util.Objects;

public class User {
    private Long id;
    private Integer roleId;
    private String name;
    private String login;
    private String password;

    public User() {
    }

    public User(Integer roleId, String name, String login, String password) {
        this.roleId = roleId;
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(roleId, user.roleId) && Objects.equals(name, user.name) && Objects.equals(login, user.login) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleId, name, login, password);
    }

    @Override
    public String toString() {
        return "User{" +
               "id=" + id +
               ", role_id=" + roleId +
               ", name='" + name + '\'' +
               ", login='" + login + '\'' +
               ", password='" + password + '\'' +
               '}';
    }
}
