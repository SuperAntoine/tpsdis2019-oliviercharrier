package net.jambon.rillettes.tpsdisoliviercharrier.rest;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Message implements Serializable {
    private @Id @GeneratedValue int id;
    private Date timestamp;
    private String content;

    public Message(String content) {
        this.timestamp = new Date();
        this.content = content;
    }

    public Message() {}

    public int getId() {
        return id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getContenu() {
        return content;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public void setContenu(String content) {
        this.content = content;
    }

    public String toString() {
        return String.format("%d\t%s\t%s", this.id, this.content, this.timestamp.toString());
    }
}
