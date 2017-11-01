package com.sap.iot.ch.slack.json;

public class SlackChannel {
    private String         id;
    private String         name;
    
    public SlackChannel(){
    	
    }

    public SlackChannel(String id, String name)
    {
        this.id = id;
        this.name = name;

    }

    public String getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }
}
