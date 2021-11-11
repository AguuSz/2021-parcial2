package info3.parcial2;

import info3.parcial2.models.Mail;
import info3.parcial2.utils.Validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static final MailManager manager = new MailManager();
    private static final String localMail = "juan@librecorreo.com";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int option;
        boolean exit = false;

        System.out.println("Bienvenido al gestor de correos!");

        while (!exit) {
            System.out.println("***************** Que desea hacer? *****************");
            System.out.println("\t1) Agregar mails al gestor.");
            System.out.println("\t2) Borrar mails del gestor.");
            System.out.println("\t3) Mostrar mails ordenados por fecha (Ultimos primeros).");
            System.out.println("\t4) Filtrar mails por un rango de fecha.");
            System.out.println("\t5) Filtrar por remitente.");
            System.out.println("\t6) Mostrar mails ordenados por remitente.");
            System.out.println("\t7) Buscar correos mediante coincidencia de palabra.");
            System.out.println("\t8) Buscar un correo por ID.");
            System.out.println("\t9) Imprimir IDs arbol.");
            System.out.println("\n\t0) Salir.");


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
                case 8:
                    getDetailedEmail();
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
        tempMail.setTo(localMail);

        while (true) {
            System.out.print("Remitente: ");
            String temp = scanner.nextLine();
            if (Validator.isAValidEmailAddress(temp)) {
                tempMail.setFrom(temp);
                break;
            } else
                System.out.println("Input invalido, intente de nuevo.");
        }

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
        try {
            Mail[] mails = manager.getSortedByDate();
            for (Mail i : mails) {
                System.out.println(i);
            }
        } catch (Exception e) {
            System.out.println("No hay correos cargados.");
        }
    }

    private static void getSortedByDateSegmented() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese fecha desde donde buscar (YYYY-MM-dd): ");
        String from = scanner.nextLine();
        while (!isAValidDate(from)) {
            System.out.println("Fecha invalida, intentelo de nuevo.");
            System.out.print("Ingrese fecha desde donde buscar (YYYY-MM-dd): ");
            from = scanner.nextLine();
        }

        System.out.print("Ingrese fecha hasta donde buscar (YYYY-MM-dd): ");
        String to = scanner.nextLine();
        while (!isAValidDate(from)) {
            System.out.println("Fecha invalida, intentelo de nuevo.");
            System.out.print("Ingrese fecha desde donde buscar (YYYY-MM-dd): ");
            to = scanner.nextLine();
        }

        try {
            for (Mail i : manager.getSortedByDate(from, to)) {
                System.out.println(i);
            }
        } catch (Exception e) {
            System.out.println("No se han encontrado correos.");
        }

    }

    private static boolean isAValidDate(String input) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            format.parse(input);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private static void getByFrom() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el remitente que busca: ");

        try {
            String from = scanner.nextLine();
            for (Mail i : manager.getByFrom(from)) {
                System.out.println(i);
            }
        } catch (NullPointerException e) {
            System.out.println("\nNo se ha encontrado ningun correo con ese remitente....");
        } catch (Exception e) {
            System.out.println("Se ha producido un error.");
        }
    }

    private static void getSortedByFrom() {
        try {
            for (Mail i : manager.getSortedByFrom()) {
                System.out.println(i);
            }
        } catch (NullPointerException e) {
            System.out.println("\nNo se ha encontrado ningun correo....");
        } catch (Exception e) {
            System.out.println("Se ha producido un error.");
        }
    }

    private static void getByQuery() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese la palabra a buscar: ");
        String contentSearch = scanner.nextLine();

        Mail[] mailArray = manager.getByQuery(contentSearch);
        if (mailArray.length > 0) {
            System.out.print("Se han encontrado " + mailArray.length + " correos. Desea imprimirlos? (1: Si | 2: No): ");
            int option = scanner.nextInt();

            if (option == 1) {
                for (Mail mail : mailArray) {
                    System.out.println(mail);
                }
            }
        } else {
            System.out.println("\nNo se ha encontrado dicha query!");
        }
    }

    private static void getDetailedEmail() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el ID a buscar: ");

        try {
            Long id = scanner.nextLong();
            System.out.println(manager.getById(id).getFullInfo());
        } catch (NullPointerException e) {
            System.out.println("\nNo se ha encontrado ningun correo con ese ID....");
        } catch (InputMismatchException inputMismatchException) {
            System.out.println("Solo se permiten IDs numericos. Intente de nuevo.\n");
        } catch (Exception e) {
            System.out.println("Se ha producido un error.");
        }
    }
}