package com.telehealth.services.scheduling;

import java.util.Date;

public class ZoomCreateMeetingResponse
{
    //private Integer id;
    private String uuid;
    private String host_email;
    private String host_id;
    private String topic;
    private int type;
    private long id;
    private String status;
    private int duration;
    private Date start_time;

    public Date getStart_time()
    {
        return start_time;
    }

    public void setStart_time(Date start_time)
    {
        this.start_time = start_time;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public int getDuration()
    {
        return duration;
    }

    public void setDuration(int duration)
    {
        this.duration = duration;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getHost_id()
    {
        return host_id;
    }

    public void setHost_id(String host_id)
    {
        this.host_id = host_id;
    }

    public String getTopic()
    {
        return topic;
    }

    public void setTopic(String topic)
    {
        this.topic = topic;
    }

    public int getType()
    {
        return type;
    }

    public void setType(int type)
    {
        this.type = type;
    }

    public String getUuid()
    {
        return uuid;
    }

    public void setUuid(String uuid)
    {
        this.uuid = uuid;
    }
/*public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }*/

    public String getHost_email()
    {
        return host_email;
    }

    public void setHost_email(String host_email)
    {
        this.host_email = host_email;
    }
}
