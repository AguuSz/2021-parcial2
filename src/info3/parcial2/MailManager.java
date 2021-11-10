package info3.parcial2;

import info3.parcial2.models.Mail;
import info3.parcial2.structures.AVLTree;
import info3.parcial2.structures.HashMap;
import info3.parcial2.structures.LinkedList;
import info3.parcial2.structures.Node;
import info3.parcial2.utils.MailParser;
import info3.parcial2.utils.TreePrinter;

public class MailManager {
    private final AVLTree<String, Mail> dateTree = new AVLTree<>();
    private final AVLTree<Long, Mail> idTree = new AVLTree<>();
    private final AVLTree<String, LinkedList<Mail>> fromTree = new AVLTree<>();
    private final HashMap<String, LinkedList<Mail>> queryMap = new HashMap<>();
    private final HashMap<String, LinkedList<Mail>> subjectMap = new HashMap<>();

    private long lastIdIntroduced = 0;

    public MailManager() {
        try {
            loadStructures();
        } catch (Exception e) {
            System.out.println("Error al cargar las estructuras.");
        }
    }

    /**
     * Carga las estructuras del mailManager con los datos correspondientes.
     */
    private void loadStructures() throws Exception {
        long startTime = System.currentTimeMillis();
        LinkedList<Mail> mailList = MailParser.parseFromFile("src/info3/emails/mails-1000.txt");
        setLastIdIntroduced(mailList.getSize());

        for (Mail mail : mailList) {
            String[] content = mail.getContent().split(" ");
            String[] subjectWords = mail.getSubject().split(" ");

            for (String contentWord : content) {
                // Llenamos el queryMap
                try {
                    queryMap.put(contentWord, new LinkedList<Mail>());
                    queryMap.get(contentWord).add(mail);
                } catch (Exception e) {
                    queryMap.get(contentWord).add(mail);
                }
            }
            for (String subjectWord : subjectWords) {
                // Llenamos el queryMap
                try {
                    subjectMap.put(subjectWord, new LinkedList<Mail>());
                    subjectMap.get(subjectWord).add(mail);
                } catch (Exception e) {
                    subjectMap.get(subjectWord).add(mail);
                }
            }

            dateTree.insert(mail.getDate(), mail);
            idTree.insert(mail.getId(), mail);
            insertIntoLinkedList(fromTree, mail.getFrom(), mail);
        }
        long endTime = System.currentTimeMillis();
        long timeElapsed = endTime - startTime;

        System.out.println("Execution time in milliseconds: " + timeElapsed);
    }

    /**
     * Agrega un mail al gestor
     *
     * @param m mail a agregar
     */
    public void addMail(Mail m) {
        dateTree.insert(m.getDate(), m);
        idTree.insert(m.getId(), m);
        insertIntoLinkedList(fromTree, m.getFrom(), m);
        insertContentIntoHashTable(m);
        // TODO: Faltan agregar las demas estructuras

        lastIdIntroduced++;
        System.out.println("\nSe ha agregado correctamente!");
    }

    private void insertContentIntoHashTable(Mail m) {
        String[] content = m.getContent().split(" ");
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
        try {
            Mail tempMail = idTree.get(id).getData();
            idTree.delete(id);
            dateTree.delete(tempMail.getDate());
            deleteFromLinkedList(fromTree, tempMail.getFrom(), id);
        } catch (NullPointerException e) {
            System.out.println("\nNo se ha encontrado el mail con dicho ID.");
        }
    }

    /**
     * Devuelve una lista de mails ordenados por fecha
     *
     * @return lista de mails ordenados
     */
    public Mail[] getSortedByDate() {
        LinkedList<Mail> mailList = dateTree.getSorteredInOrderList();
        Object[] objects = mailList.toObjectArray();

        return toEmailArray(objects, mailList.getSize());
    }

    /**
     * Devuelve una lista de mails oredenados por fecha que estan en el rango
     * desde - hasta
     *
     * @param from Fecha desde donde buscar
     * @param to   Fecha hasta donde buscar
     * @return lista de mails ord-enados
     */
    public Mail[] getSortedByDate(String from, String to) {
        LinkedList<Mail> mailList = dateTree.getInorderedSegmentedList(from, to);
        return toEmailArray(mailList.toObjectArray(), mailList.getSize());
    }

    /**
     * Devuelve una lista de mails ordenados por Remitente
     *
     * @return lista de mails ordenados
     */
    public Mail[] getSortedByFrom() {
        LinkedList<LinkedList<Mail>> mailListList = fromTree.getSorteredInOrderList();
        LinkedList<Mail> mailList = new LinkedList<>();
        for (LinkedList<Mail> i : mailListList) {
            for (Mail j : i) {
                try {
                    mailList.add(j);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
        Mail[] mails = new Mail[mailList.getSize()];
        int contador = 0;
        for (Mail i : mailList) {
            mails[contador++] = i;
        }
        return mails;
    }

    /**
     * Devuelve una lista de mails de un determinado remitente
     *
     * @param from String con direccion del remitente
     * @return lista de mails del remitente
     */
    public Mail[] getByFrom(String from) {
        Node<String, LinkedList<Mail>> mailList = fromTree.get(from);
        Mail[] mails = new Mail[mailList.getData().getSize()];
        int contador = 0;
        for (Mail i : mailList.getData()) {
            mails[contador++] = i;
        }
        return mails;
    }

    /**
     * Devuelve una lista de mails que contengan las palabras de 'query'
     * en su asunto o en su contenido
     *
     * @param query String con palabra/s a buscar
     * @return lista de mails que contienen dicha/s palabra/s
     */
    public Mail[] getByQuery(String query) {
        try {
            LinkedList<Mail> list = queryMap.get(query);
            return toEmailArray(list.toObjectArray(), list.getSize());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Devuelve un correo con el ID asociado
     *
     * @param id Long con la id del correo a buscar
     * @return si lo encuentra retorna el correo con el id asociado. Caso contrario, retorna null
     */
    public Mail getById(Long id) {
        Node<Long, Mail> mailNode = idTree.get(id);
        if (mailNode.getData() != null) {
            return mailNode.getData();
        }
        return null;
    }

    /**
     * Imprime el arbol ordenado por fecha
     */
    public void printDateTree() {
        TreePrinter.print(dateTree.getRoot());
    }

    /**
     * Inserta un dato en una lista enlazada dentro de una estructura en caso de que exista
     * Caso contrario crea la lista y luego inserta el dato
     *
     * @param tree AVLTree donde se va a insertar el dato
     * @param key  String clave donde guardar el dato
     * @param data Mail que se va a insertar
     */
    private void insertIntoLinkedList(AVLTree<String, LinkedList<Mail>> tree, String key, Mail data) {
        try {
            if (tree.get(key) == null) {
                tree.insert(key, new LinkedList<Mail>());
                tree.get(key).getData().add(data);
            } else {
                tree.get(key).getData().add(data);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void deleteFromLinkedList(AVLTree<String, LinkedList<Mail>> tree, String key, long id) {
        try {
            if (tree.get(key) != null || tree.get(key).getData().getSize() > 0)
                tree.get(key).getData().delete(findMailPos(tree.get(key).getData(), id));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private Integer findMailPos(LinkedList<Mail> mailList, long id) {
        for (int i = 0; i < mailList.getSize(); i++) {
            if (mailList.get(i).getId() == id)
                return i;
        }
        return null;
    }

    private Mail[] toEmailArray(Object[] objects, int size) {
        Mail[] mailArray = new Mail[size];
        int i = 0;
        for (Object o : objects) {
            mailArray[i++] = (Mail) o;
        }
        return mailArray;
    }

    // ------------------------------------------ Developer functions ------------------------------------------
    public void printIdTree() {
        TreePrinter.print(idTree.getRoot());
    }

    public void printFromTree() {
        TreePrinter.print(fromTree.getRoot());
    }
}
