package OOP;

public class Abstract {
    private void smallFunction_1(){
        // малая функция 1
        System.out.println("Выполняется первая часть работы");
    }

    private void smallFunction_2(){
        // малая функция 2
        System.out.println("Выполняется вторая часть работы");
    }

    private void smallFunction_3(){
        // малая функция 3
        System.out.println("Выполняется третья часть работы");
    }

    public void mainFunction(){
        // Основная функция доступная пользователю
        // Для упрощения написания кода функция разбита на подфункции
        smallFunction_1();
        smallFunction_2();
        smallFunction_3();
        System.out.println("Финальный результат");

    }
}
