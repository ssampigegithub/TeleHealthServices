package com.telehealth.services.scheduling;

import java.util.Date;

public class ZoomCreateMeetingRequest
{
    private String topic;
    private Integer type;
    private Date start_time;
    private Integer duration;

    public Integer getDuration()
    {
        return duration;
    }

    public void setDuration(Integer duration)
    {
        this.duration = duration;
    }

    public String getTopic()
    {
        return topic;
    }

    public void setTopic(String topic)
    {
        this.topic = topic;
    }

    public Integer getType()
    {
        return type;
    }

    public void setType(Integer type)
    {
        this.type = type;
    }

    public Date getStart_time()
    {
        return start_time;
    }

    public void setStart_time(Date start_time)
    {
        this.start_time = start_time;
    }
}
