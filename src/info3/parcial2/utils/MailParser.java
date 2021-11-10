package info3.parcial2.utils;

import info3.parcial2.models.Mail;
import info3.parcial2.structures.LinkedList;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Clase encargada de cargar los mails de un .txt a una lista enlazada
 */

public class MailParser {

    public static LinkedList<Mail> parseFromFile(String pathFile) {

        String str;
        String content = "";
        LinkedList<Mail> mailList = new LinkedList<>();
        Mail mailTemp = new Mail();
        int counter = 0;

        try {
            FileReader fileReader = new FileReader(pathFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            bufferedReader.readLine();    //Discard the first line
            while ((str = bufferedReader.readLine()) != null) {
                if (str.equals("-.-.-:-.-.-")) {
                    mailTemp.setContent(content);
                    Mail mail = mailTemp.clone();
                    mail.setId(++counter);
                    mailList.add(mail);
                    content = "";
                } else if (str.contains("date: "))
                    mailTemp.setDate(str.substring(6));

                else if (str.contains("from: "))
                    mailTemp.setFrom(str.substring(6));

                else if (str.contains("to: "))
                    mailTemp.setTo(str.substring(4));

                else if (str.contains("subject: "))
                    mailTemp.setSubject(str.substring(9));
                else
                    content = content.concat(str + "\n");
            }
            bufferedReader.close();
        } catch (Error | Exception e) {
            System.out.println(e);
        }
        return mailList;
    }
}
