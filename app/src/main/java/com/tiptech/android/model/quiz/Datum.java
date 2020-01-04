
package com.tiptech.android.model.quiz;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum implements Serializable
{

    @SerializedName("Text")
    @Expose
    private String text;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("Photo")
    @Expose
    private String photo;
    @SerializedName("Id")
    @Expose
    private long id;
    private final static long serialVersionUID = -1871960028577394406L;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
