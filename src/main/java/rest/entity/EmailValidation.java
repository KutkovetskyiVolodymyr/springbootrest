package rest.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "emailvalidation")
public class EmailValidation {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "from")
    private String from;

    @Column(name = "reply_to")
    private String reply_to;

    @Column(name = "to")
    private String to;
    @Column(name = "cc")
    private String cc;
    @Column(name = "bcc")
    private String bcc;
    @Column(name = "subject")
    private String subject;
/*
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "content", nullable = false)
    private ContentJson content;


    public ContentJson getContent() {
        return content;
    }

    public void setContent(ContentJson content) {
        this.content = content;
    }

*/
    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getReply_to() {
        return reply_to;
    }

    public void setReply_to(String reply_to) {
        this.reply_to = reply_to;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getBcc() {
        return bcc;
    }

    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }


}

