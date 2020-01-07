
package com.tiptech.android.model.courses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Datum implements Serializable {

    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("DeveloperName")
    @Expose
    private String developerName;
    @SerializedName("NameSite")
    @Expose
    private String nameSite;
    @SerializedName("Evaluation")
    @Expose
    private long evaluation;
    @SerializedName("Photo")
    @Expose
    private String photo;
    @SerializedName("Id")
    @Expose
    private long id;
    @SerializedName("Link")
    @Expose
    private String link;
    private final static long serialVersionUID = 5587276280119600597L;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeveloperName() {
        return developerName;
    }

    public void setDeveloperName(String developerName) {
        this.developerName = developerName;
    }

    public String getNameSite() {
        return nameSite;
    }

    public void setNameSite(String nameSite) {
        this.nameSite = nameSite;
    }

    public long getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(long evaluation) {
        this.evaluation = evaluation;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
