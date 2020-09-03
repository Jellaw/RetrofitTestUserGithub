package com.example.retrofittest.data.model;

import android.text.Editable;

import com.google.gson.annotations.SerializedName;
public class User {
    private String node_id;
    private int id;
    private String login;
    @SerializedName("url")
    private String text;


    public String getNode_id() {
        return node_id;
    }

    public String getLogin() {
        return login;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }


    @Override
    public String toString() {
        return "User " +
                 login + '\n' +
                "node_id= " + node_id + '\n' +
                "id=" + id + '\n' +
                "text= " + text + '\n';
    }
}
