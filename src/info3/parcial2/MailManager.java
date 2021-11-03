package info3.parcial2;

import info3.parcial2.Structures.AVLTree;
import info3.parcial2.Structures.LinkedList;
import info3.parcial2.Structures.LinkedNode;
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
        Mail tempMail = idTree.get(id).getData();
        idTree.delete(id);
        dateTree.delete(tempMail.getDate());
    }

    /**
     * Devuelve una lista de mails ordenados por fecha
     *
     * @return lista de mails ordenados
     */
    public Mail[] getSortedByDate() {
        LinkedList<Mail> mailList = new LinkedList<>();
        getSortedByDate(dateTree.getRoot(), mailList);
        Mail[] mails = new Mail[mailList.getSize()];
        for(int i = 0; mailList.getSize() > 0; i++) {
            mails[i] = mailList.get(0);
            try {
                mailList.delete(0);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return mails;
    }

    private void getSortedByDate(Node<String, Mail> node, LinkedList<Mail> mailList) {
        if(node == null) {
            return;
        }
        getSortedByDate(node.getLeftChild(), mailList);
        try {
            mailList.add(node.getData());
        } catch (Exception e) {
            System.out.println(e);
        }
        getSortedByDate(node.getRightChild(), mailList);
    }

    /**
     * Devuelve una lista de mails oredenados por fecha que estan en el rango
     * desde - hasta
     *
     * @param from Fecha desde donde buscar
     * @param to Fecha hasta donde buscar
     * @return lista de mails ord-enados
     */
    public Mail[] getSortedByDate(String from, String to) {
        LinkedList<Mail> mailList = new LinkedList<>();
        getSortedByDate(dateTree.getRoot(), mailList, from, to);
        Mail[] mails = new Mail[mailList.getSize()];
        for(int i = 0; mailList.getSize() > 0; i++) {
            mails[i] = mailList.get(0);
            try {
                mailList.delete(0);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return mails;
    }

    private void getSortedByDate(Node<String, Mail> node, LinkedList<Mail> mailList, String from, String to) {
        if (node == null){
            return;
        }

        if(node.getData().getDate().compareTo(from) > 0)
            getSortedByDate(node.getLeftChild(), mailList, from, to);
        if(node.getData().getDate().compareTo(from) > 0 && node.getData().getDate().compareTo(to) < 0)
        try {
            mailList.add(node.getData());
        } catch (Exception e) {
            System.out.println(e);
        }
        if(node.getData().getDate().compareTo(to) < 0)
        getSortedByDate(node.getRightChild(), mailList, from, to);

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
        TreePrinter.print(dateTree.getRoot());
    }

    // ------------------------------------------ Developer functions ------------------------------------------
    public void printIdTree() {
        TreePrinter.print(idTree.getRoot());
    }
}
