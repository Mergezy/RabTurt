class AnimalThread extends Thread {
    private String name;
    private int meters;

    public AnimalThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (meters < 100) {
            meters++;
            // Выводим информацию о текущем положении зверюшки.
            System.out.println(name + " преодолел " + meters + " метров.");
            try {
                Thread.sleep(100); // Имитируем движение с задержкой.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Завершаем зверюшку при достижении финиша.
        System.out.println(name + " финишировал!");
    }
}

public class Main {
    public static void main(String[] args) {
        // Создаем зверюшек (кролика и черепаху).
        AnimalThread rabbit = new AnimalThread("Кролик");
        AnimalThread turtle = new AnimalThread("Черепаха");

        // Запускаем зверюшек на бег.
        rabbit.start();
        turtle.start();

        try {
            // Ожидаем завершения забега зверюшек.
            rabbit.join();
            turtle.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Определяем результат забега и выводим соответствующее сообщение.
        System.out.println(
            rabbit.isAlive() && !turtle.isAlive() ? "Кролик выиграл!" :
            (!rabbit.isAlive() && turtle.isAlive() ? "Черепаха выиграла!" : "Ничья!")
        );
    }
}
