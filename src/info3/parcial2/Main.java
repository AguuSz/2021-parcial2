package info3.parcial2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    private static final MailManager manager = new MailManager();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int option;
        boolean exit = false;

        System.out.println("Bienvenido al gestor de correos!");

        while (!exit) {
            System.out.println("***************** Que desea hacer? *****************");
            System.out.println("\t1) Agregar mails al gestor.");
            System.out.println("\t2) Borrar mails del gestor.");
            System.out.println("\t3) Mostrar mails ordenados por fecha.");
            System.out.println("\t4) Filtrar mails por fecha.");
            System.out.println("\t5) Filtrar por remitente.");
            System.out.println("\t6) Mostrar mails ordenados por remitente.");
            System.out.println("\t7) Filtrar mails por palabras o asunto.");
            System.out.println("\t0) Salir.");

            System.out.println("\t9) Imprimir arbol.");
            System.out.println("****************************************************");

            System.out.print("Opcion: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    addEmail();
                    break;
                case 2:
                    deleteEmail();
                    break;
                case 3:
                    getSortedByDate();
                    break;
                case 4:
                    getSortedByDateSegmented();
                    break;
                case 5:
                    getByFrom();
                    break;
                case 6:
                    getSortedByFrom();
                    break;
                case 7:
                    getByQuery();
                    break;
                case 0:
                    exit = true;
                    break;
                case 9:
                    manager.printIdTree();
                    break;
                default:
                    System.out.println("Opcion no contemplada.");
                    break;
            }
        }
    }

    private static void addEmail() {
        Mail tempMail = new Mail();
        Scanner scanner = new Scanner(System.in);


        // Obtiene la ultima ID e incrementa en uno para setearselo al correo temporal
        tempMail.setId(manager.getLastIdIntroduced() + 1);

        System.out.print("Remitente: ");
        tempMail.setFrom(scanner.nextLine());

        tempMail.setDate(getTodayDateFormatted());

        System.out.print("Asunto: ");
        tempMail.setSubject(scanner.nextLine());

        System.out.print("Contenido: ");
        tempMail.setContent(scanner.nextLine());

        manager.addMail(tempMail);
    }

    private static String getTodayDateFormatted() {
        //Get current date time
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        return now.format(formatter);
    }

    private static void deleteEmail() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el ID: ");
        long id = scanner.nextLong();

        if (id < 0) {
            System.out.println("ID invalido.");
            return;
        }
        manager.deleteMail(id);
    }

    private static void getSortedByDate() {
        Mail[] mails = manager.getSortedByDate();
        for (Mail i : mails) {
            System.out.println(i);
        }
    }

    private static void getSortedByDateSegmented() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese fecha desde donde buscar: ");
        String from = scanner.nextLine();

        System.out.print("Ingrese fecha hasta donde buscar: ");
        String to = scanner.nextLine();

        for (Mail i : manager.getSortedByDate(from, to)) {
            System.out.println(i);
        }
    }

    private static void getByFrom() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el remitente que busca: ");
        String from = scanner.nextLine();

        for(Mail i : manager.getByFrom(from)) {
            System.out.println(i);
        }
    }

    private static void getSortedByFrom() {
        for(Mail i : manager.getSortedByFrom()) {
            System.out.println(i);
        }
    }

    private static void getByQuery() {
    }
}