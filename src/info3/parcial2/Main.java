package info3.parcial2;

import info3.parcial2.Structures.LinkedList;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MailManager manager = new MailManager();
        int option;
        boolean exit = false;

        System.out.println("Bienvenido al gestor de correos!");

        while (!exit) {
            System.out.println("***************** Que desea hacer? *****************");
            System.out.println("\t1) Agregar mails al gestor.");
            System.out.println("\t2) Borrar mails del gestor.");
            System.out.println("\t3) Filtrar mails por fecha.");
            System.out.println("\t4) Filtrar por remitente.");
            System.out.println("\t5) Mostrar mails ordenados por remitente.");
            System.out.println("\t6) Filtrar mails por palabras o asunto.");
            System.out.println("\t0) Salir.");
            System.out.println("****************************************************");

            System.out.print("Opcion: ");
            option = scanner.nextInt();

            switch (option) {
                case 1 -> addEmail();
                case 2 -> deleteEmail();
                case 3 -> getSortedByDate();
                case 4 -> getByFrom();
                case 5 -> getSortedByFrom();
                case 6 -> getByQuery();
                case 0 -> exit = true;
                default -> System.out.println("Opcion no contemplada.");
            }
        }
    }

    private static void addEmail() { }

    private static void deleteEmail() {}

    private static void getSortedByDate() {}

    private static void getByFrom() {}

    private static void getSortedByFrom() {}

    private static void getByQuery() {}
}
