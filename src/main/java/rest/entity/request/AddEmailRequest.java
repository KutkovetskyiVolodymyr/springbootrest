package rest.entity.request;

import rest.entity.ContentJson;

public class AddEmailRequest {

        private String from;

        private String reply_to;

        private String[] to;

        private String[] cc;

        private String[] bcc;

        private String subject;

        private ContentJson content;

    public AddEmailRequest(String from, String reply_to, String[] to, String[] cc, String[] bcc, String subject, ContentJson content) {
        this.from = from;
        this.reply_to = reply_to;
        this.to = to;
        this.cc = cc;
        this.bcc = bcc;
        this.subject = subject;
        this.content = content;
    }

    public ContentJson getContent() {
        return content;
    }

    public void setContent(ContentJson content) {
        this.content = content;
    }

    public AddEmailRequest(){

    }

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

    public String[] getTo() {
        return to;
    }

    public void setTo(String[] to) {
        this.to = to;
    }

    public String[] getCc() {
        return cc;
    }

    public void setCc(String[] cc) {
        this.cc = cc;
    }

    public String[] getBcc() {
        return bcc;
    }

    public void setBcc(String[] bcc) {
        this.bcc = bcc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }



}
