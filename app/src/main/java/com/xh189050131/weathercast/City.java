package com.xh189050131.weathercast;


import java.util.UUID;

public class City {
    private UUID Cid;
    private String Cname;

    public City(){
        this(UUID.randomUUID());
    }

    public City(UUID id){
        Cid = id;
    }

    public UUID getCid() {
        return Cid;
    }

    public String getCname() {
        return Cname;
    }

    public void setCname(String cname) {
        Cname = cname;
    }
}
