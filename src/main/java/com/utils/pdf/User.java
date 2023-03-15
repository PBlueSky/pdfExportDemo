package com.utils.pdf;

/*
 ** @Author:         blue_sky
 ** @CreateDate:     2023-03-15  16:10
 ** @ProjectName:    pdf-utils
 ** @Package:        com.utils.pdf
 */

public class User {
    Integer id;
    String nick;
    String phone;
    String address;

    public User() {
    }

    public User(Integer id, String nick, String phone, String address) {
        this.id = id;
        this.nick = nick;
        this.phone = phone;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nick='" + nick + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
