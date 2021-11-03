package info3.parcial2;

/**
 * Clase con los datos de un email. Puede tener mas propiedades o metodos
 */
class Mail {

    private long id;
    private String from;    // Remitente del mail
    private String to;      // destinatario del mail
    private String date;    // Fecha de envio
    private String subject; // Asunto del mail
    private String content; // Contenido del mail.

    public Mail() {}

    public Mail(String from, String to, String date, String subject, String content) {
        this.from = from;
        this.to = to;
        this.date = date;
        this.subject = subject;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public Mail clone() {
        return new Mail(this.from, this.to, this.date, this.subject, this.content);
    }

    @Override
    public String toString() {
        return "Mail{" +
                "id= " + id + + '\n' +
                ", from= " + from + '\n' +
                ", to= " + to + '\n' +
                ", date= " + date + '\n' +
               // ", subject='" + subject + '\n' +
               // ", content='" + content + '\n' +
                '}';
    }
}
