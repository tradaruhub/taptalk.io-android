package com.moselo.HomingPigeon.Model;

import android.arch.persistence.room.Ignore;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class PairIdNameModel {
    @JsonProperty("id") private String id;
    @JsonProperty("name") private String name;

    @Ignore
    public PairIdNameModel(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public PairIdNameModel() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
