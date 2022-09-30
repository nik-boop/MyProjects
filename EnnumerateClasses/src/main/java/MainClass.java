import java.util.Locale;
import java.util.Scanner;

public class MainClass {
    enum DayOfWeek
    {

        MONDAY("понедельник")
                {
                    public int getNumberOfDay(){
                        return 0;
                    }
                },
        TUESDAY("вторник")
                {
                    public int getNumberOfDay(){
                        return 1;
                    }
                },
        WEDNESDAY("среда")
                {
                    public int getNumberOfDay(){
                        return 2;
                    }
                },
        THURSDAY("четверг")
                {
                    public int getNumberOfDay(){
                        return 3;
                    }
                },
        FRIDAY("пятница")
                {
                    public int getNumberOfDay(){
                        return 4;
                    }
                },
        SATURDAY("субота")
                {
                    public int getNumberOfDay(){
                        return 5;
                    }
                },
        SUNDAY("воскресенье")
                {
                    public  int getNumberOfDay(){
                        return 6;
                    }
                };
        private String representation;
        public abstract int getNumberOfDay();
        private DayOfWeek(String representation){
            this.representation = representation;
        }
        String getRepresentation(){
            return representation;
        }
    }

    public static void main(String[] args){
        DayOfWeek day = DayOfWeek.SATURDAY;
        Scanner in = new Scanner(System.in);
        day = DayOfWeek.valueOf(in.next().toUpperCase(Locale.ROOT));
        switch (day){
            case SATURDAY -> {
                System.out.println("Saturday");
            }
            case SUNDAY -> {
                System.out.println("Sunday");
            }
            default -> {
                System.out.printf("this day %s%n", day.toString());

            }
            }
        DayOfWeek[] days = DayOfWeek.values();
        for (DayOfWeek myDays:days){
            System.out.printf("day: %12s | number bay: %s | number in enumerate: %s%n", myDays.representation, myDays.ordinal(), myDays.getNumberOfDay());
        }
    }
}
