package net.jambon.rillettes.tpsdisoliviercharrier.rest;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Message implements Serializable {
    private @Id @GeneratedValue int id;
    private String timestamp;
    private String content;

    public Message(String content) {
        this.timestamp = new Date().toString();
        this.content = content;
    }

    public Message() {}

    public int getId() {
        return id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getContenu() {
        return content;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setContenu(String content) {
        this.content = content;
    }

    public String toString(String type) {
        return String.format("%s\t%d\t%s\t%s", type, this.id, this.content, this.timestamp.toString());
    }
}
