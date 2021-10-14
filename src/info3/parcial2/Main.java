package info3.parcial2;

import info3.parcial2.Structures.LinkedList;

public class Main {

    public static void main(String[] args) {
        //Test carga de mails
        String archivo = "info3/emails/mails-1000.txt";
        LinkedList<Mail> mailList = MailParser.parseFromFile(archivo);
    }
}
