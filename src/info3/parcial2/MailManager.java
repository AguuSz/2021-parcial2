package info3.parcial2;

import info3.parcial2.Structures.AVLTree;
import info3.parcial2.Structures.LinkedList;
import info3.parcial2.Structures.Node;

public class MailManager {
    private final AVLTree<String, Mail> dateTree    = new AVLTree<>();
    private final AVLTree<Long, Mail>   idTree      = new AVLTree<>();
    private long lastIdIntroduced = 0;

    public MailManager() {
        loadStructures();
    }

    /**
     * Carga las estructuras del mailManager con los datos correspondientes.
     *
     */
    private void loadStructures() {
        LinkedList<Mail> mailList = MailParser.parseFromFile("src/info3/emails/mails-20.txt");
        setLastIdIntroduced(mailList.getSize());

        for (int i = 0; i < mailList.getSize(); i++) {
            dateTree.insert(mailList.get(i).getDate(), mailList.get(i));
            idTree.insert(mailList.get(i).getId(), mailList.get(i));
        }
    }

    /**
     * Agrega un mail al gestor
     *
     * @param m mail a agregar
     */
    public void addMail(Mail m) {
        dateTree.insert(m.getDate(), m);
        idTree.insert(m.getId(), m);
        // TODO: Faltan agregar las demas estructuras

        lastIdIntroduced++;
        System.out.println("Se ha agregado correctamente!");
    }

    public long getLastIdIntroduced() {
        return this.lastIdIntroduced;
    }

    public void setLastIdIntroduced(long id) {
        this.lastIdIntroduced = id;
    }

    /**
     * Elimina un mail del gestor
     *
     * @param id identificador del mail a borrar
     */
    public void deleteMail(long id) {
        Mail tempMail = idTree.get(id);
        idTree.delete(id);
        dateTree.delete(tempMail.getDate());
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

    /**
     * Imprime el arbol ordenado por fecha
     *
     */
    public void printDateTree() {
        dateTree.print();
    }

    // ------------------------------------------ Developer functions ------------------------------------------
    public void printIdTree() {
        idTree.print();
    }
}
