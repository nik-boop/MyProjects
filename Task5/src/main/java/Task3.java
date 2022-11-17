import java.util.PriorityQueue;
import java.util.Scanner;

public class Task3 {

    public static boolean isEnd = true;
    public static int choice;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PriorityQueue<Client> queueClients = new PriorityQueue<Client>(10, new ClientComparator());
        Client numberOne = new Client("Nik1", (float) 100);
        queueClients.add(numberOne);

        Client numberTwo = new Client("Nik2", (float) 100);
        queueClients.add(numberTwo);

        Client numberThree = new Client("Nik3", (float) 200);
        queueClients.add(numberThree);

        while (isEnd) {
            System.out.println("""
                        1 - Список клиентов.
                        2 - Клиент покинул очередь.
                        3 - Новый клиент.
                    """);
            System.out.print(">");
            choice = in.nextInt();
            switch (choice) {
                case (1) -> {
                    System.out.println();
                    System.out.println("Очередь клиентов");
                    for (Client client: queueClients) {
                        System.out.printf("(%s, %s)%n", client.getName(), client.getDist());
                    }
                    System.out.println();
                }
                case (2) -> {
                    Client nowCl = queueClients.poll();
                    System.out.printf("(%s, %s)%n", nowCl.getName(), nowCl.getDist());
                }
                case (3) -> {
                    System.out.println();
                    System.out.print("Имя: ");
                    String name = in.next();
                    System.out.print("Расстояние до города клиента: ");
                    Float dist = Float.parseFloat(in.next());
                    System.out.println();
                    try {
                        queueClients.add(new Client(name, dist));
                        System.out.println("Клиент добавлен в очередь");
                    } catch (Exception e) {
                        System.out.print("Ошибка: ");
                        e.printStackTrace();
                    }
                }
                default -> {
                    System.out.println("Выход");
                    isEnd = false;
                }
            }
        }

    }

}
