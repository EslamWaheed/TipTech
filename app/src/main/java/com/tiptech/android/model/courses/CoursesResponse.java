
package com.tiptech.android.model.courses;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CoursesResponse implements Serializable
{

    @SerializedName("Data")
    @Expose
    private List<Datum> data = null;
    @SerializedName("Succcess")
    @Expose
    private boolean succcess;
    @SerializedName("Errors")
    @Expose
    private List<String> errors = null;
    private final static long serialVersionUID = 397205227591685935L;

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public boolean isSucccess() {
        return succcess;
    }

    public void setSucccess(boolean succcess) {
        this.succcess = succcess;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

}
