package helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.stream.Collectors;

/**
 * Класс помощник для выполнения bash команд
 */
public class DeviceHelper {


    public static String executeSh(String command) throws IOException, ExecutionException, InterruptedException {
        Process p = Runtime.getRuntime().exec(new String[]{"bash", "-l", "-c", "adb devices"});//получаем инстатс терминала и выполняем скрипт
        FutureTask<String > future = new FutureTask<>(()->{ //создаем FutureTask
            return new BufferedReader(new InputStreamReader(p.getInputStream())) //читаем поток информации из консоли
                    .lines().map(Object::toString) //информацию преобразуем в строку
                    .collect(Collectors.joining("\n")); //все строки собираем в одну с разделением в виде новой строки
        });
        new Thread(future).start(); //запускаем поток
        return future.get(); //ждем завершения CallBack для получения полной конечной информации из консоли
    }

    public static String executeBash(String command) {
        Process p;
        try {
            p = Runtime.getRuntime().exec(new String[]{"bash", "-l", "-c", "adb devices"}); //получаем инстанс терминала и посылаем скрипт
        } catch (IOException e){
            throw new RuntimeException(e);
        }
        final String[] message = {""}; //массив с 1 элементом для записи строк из терминала

        new Thread(()->{//запускаем новый поток чтобы не было сообщения "Процесс не отвечает", в случае если команда будет выполнятся бесконечно
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));//получаем поток информации
            String line = null;
            while (true){
                try {
                    if ((line = input.readLine()) == null) {//читаем строки пока они есть
                        break;
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                message[0] += line + "\n"; //записываем строки в первый элемент массива
            }
            System.out.println(message[0]);//выводим в консоль для дебагинга
        }).start();//стартуем поток
        try {
            p.waitFor();//ждем завершения потока
        } catch (InterruptedException e){
            throw new RuntimeException(e);
        }
        return message[0];
    }
}