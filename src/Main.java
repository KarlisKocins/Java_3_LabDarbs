// 123rdb456 Jānis Programmētājs 1. grupa

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

class Prece {
    String nosaukums;
    double cena;
    int daudzums;

    public Prece(String n, double c, int d) {
        nosaukums = n;
        cena = c;
        daudzums = d;
    }

    static Prece inputPrece(Scanner sc) {
        System.out.print("nosaukums: ");
        String n = sc.next();
        System.out.print("cena: ");
        double c = sc.nextDouble();
        System.out.println("daudzums: ");
        int d = sc.nextInt();
        return new Prece(n, c, d);
    }

    void outputPrece() {
        System.out.printf("%-20s%-10.2f%-10d\n", nosaukums, cena, daudzums);
    }
    public String getNosaukums() {
        return nosaukums;
    }

    public double getCena() {
        return cena;
    }

    public int getDaudzums() {
        return daudzums;
    }

    public void incrementDaudzums() {
        daudzums++;
    }

}

public class Main {
    public static Scanner sc;

    public static void main(String[] args) {
        HashMap<String, LinkedList<Prece>> pasutijumi =
                new HashMap<String, LinkedList<Prece>>();

        sc = new Scanner(System.in);
        String cmd = "";

        while (!cmd.equals("done")) {
            System.out.print("command:> ");
            cmd = sc.next();
            switch (cmd) {
                case "add":
                    add(pasutijumi);
                    break;

                case "print":
                    print(pasutijumi);
                    break;

                case "sum":
                    sum(pasutijumi);
                    break;

                case "inc":
                    inc(pasutijumi);
                    break;

                case "del":
                    del(pasutijumi);
                    break;

                case "done":
                    System.out.println("good bye");
                    break;
                default:
                    System.out.println("unknown command");
                    break;
            }
        }

        sc.close();
    }

    public static void add(HashMap<String, LinkedList<Prece>> pasutijumi) {

        LinkedList<Prece> grozs;

        System.out.print("klienta ID: ");
        String id = sc.next();

        Prece p = Prece.inputPrece(sc);
        grozs = pasutijumi.get(id);
        if (grozs != null) {
            grozs.add(p);
        } else {
            grozs = new LinkedList<Prece>();
            grozs.add(p);
            pasutijumi.put(id, grozs);
        }

    }

    public static void print(HashMap<String, LinkedList<Prece>> pasutijumi) {
        LinkedList<Prece> grozs;

        for (String id : pasutijumi.keySet()) {
            System.out.println("ID: " + id);
            grozs = pasutijumi.get(id);
            String str = String.format(
                    "%-20s%-10s%-10s", "nosaukums", "cena", "daudzums");
            System.out.println(str);
            for (Prece prece : grozs) {
                prece.outputPrece();
            }
        }
        System.out.println();
    }
    public static void sum(HashMap<String, LinkedList<Prece>> orders) {
        System.out.print("klienta ID: ");
        String id = sc.next();
        double total = 0;
        LinkedList<Prece> cart = orders.get(id);
        if (cart != null) {
            for (Prece item : cart) {
                total += item.getCena() * item.getDaudzums();
            }
            System.out.printf("total: %.2f\n", total);
        } else {
            System.out.println("unknown client");
        }
    }
    public static void inc(HashMap<String, LinkedList<Prece>> orders) {
        System.out.print("klienta ID: ");
        String id = sc.next();
        System.out.print("nosaukums: ");
        String productName = sc.next();

        LinkedList<Prece> items = orders.get(id);

        if (items == null) {
            System.out.println("unknown client");
            return;
        }

        boolean found = false;
        for (Prece prece : items) {
            if (prece.getNosaukums().equals(productName)) {
                prece.incrementDaudzums();
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("not found");
        }

        System.out.println();
    }

    public static void del(HashMap<String, LinkedList<Prece>> orders) {

        System.out.print("klienta ID: ");
        String id = sc.next();

        System.out.print("nosaukums: ");
        String productName = sc.next();

        LinkedList<Prece> customerOrders = orders.get(id);
        if (customerOrders == null) {
            System.out.println("unknown client");
            return;
        }

        boolean foundProduct = false;
        for (Prece prece : customerOrders) {
            if (prece.getNosaukums().equals(productName)) {
                customerOrders.remove(prece);
                foundProduct = true;
                break;
            }
        }

        if (!foundProduct) {
            System.out.println("not found");
        }
    }


}
