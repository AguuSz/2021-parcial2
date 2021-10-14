package info3.parcial2;

public class MailManager {
    /**
     * Agrega un mail al gestor
     *
     * @param m mail a agregar
     */
    public void addMail(Mail m) {

    }

    /**
     * Elimina un mail del gestor
     *
     * @param id identificador del mail a borrar
     */
    public void deleteMail(long id) {

    }

    /**
     * Devuelve una lista de mails ordenados por fecha
     *
     * @return lista de mails ordenados
     */
    public Mail[] getSortedByDate() {
        return new Mail[0];
    }

    /**
     * Devuelve una lista de mails oredenados por fecha que estan en el rango
     * desde - hasta
     *
     * @param desde Fecha desde donde buscar
     * @param hasta Fecha hasta donde buscar
     * @return lista de mails ord-enados
     */
    public Mail[] getSortedByDate(String desde, String hasta) {
        return new Mail[0];
    }

    /**
     * Devuelve una lista de mails ordenados por Remitente
     *
     * @return lista de mails ordenados
     */
    public Mail[] getSortedByFrom() {
        return new Mail[0];
    }

    /**
     * Devuelve una lista de mails de un determinado remitente
     *
     * @param from String con direccion del remitente
     * @return lista de mails del remitente
     */
    public Mail[] getByFrom(String from) {
        return new Mail[0];
    }

    /**
     * Devuelve una lista de mails que contengan las palabras de 'query'
     * en su asunto o en su contenido
     *
     * @param query String con palabra/s a buscar
     * @return lista de mails que contienen dicha/s palabra/s
     */
    public Mail[] getByQuery(String query) {
        return new Mail[0];
    }
}
