package net.jambon.rillettes.tpsdisoliviercharrier.client.message;

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

    public String fromString(String str) {
        String[] params = str.split("\t");
        this.id = Integer.parseInt(params[1]);
        this.content = params[2];
        this.timestamp = params[3];
        return params[0];
    }

    public int getId() {
        return id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getContenu() {
        return content;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp.toString();
    }

    public void setContenu(String content) {
        this.content = content;
    }

    public String toString() {
        return String.format("--------------------------------\n" +
                "Message envoyé le : %s\n" +
                "%s\n" +
                "--------------------------------", this.timestamp, this.content);
    }
}
