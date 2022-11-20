import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        System.out.println("Задание 1");
        System.out.println(bessi(10, 7, "hello my name is Bessie and this is my essay"));

        System.out.println("Задание 2");
        System.out.println(Arrays.toString(split("()()()")));
        System.out.println(Arrays.toString(split("((()))")));
        System.out.println(Arrays.toString(split("((()))(())()()(()())")));
        System.out.println(Arrays.toString(split("((())())(()(()()))")));

        System.out.println("Задание 3");
        System.out.println(toCamelCase("hello_edabit"));
        System.out.println(toCamelCase("is_modal_open"));
        System.out.println(toSnakeCase("helloEdabit"));
        System.out.println(toSnakeCase("getColor"));

    }

    // помогаем Бесси правильно оформить эссе
    public static String bessi(int n, int k, String str) {
        String[] words = str.split(" "); // массив со словами из строки
        // StringBuilder - для работы со строками, которые часто модифицируются
        StringBuilder strTemp = new StringBuilder(); // temporary - временная строка
        StringBuilder result = new StringBuilder();
        // перебиреем слова в массиве
        for (String word : words) {
            // если слово в строчку не помещается, создаём новую
            if (strTemp.length() + word.length() > k) {
                result.append(strTemp).append('\n');
                strTemp = new StringBuilder();
            }

            // условия для добавления слов во временную строку
            if (strTemp.length() == 0) {
                strTemp.append(word); // если слово в строке первое - прибавляем просто его
            } else {
                strTemp.append(" ").append(word); // если слово НЕ первое - прибавляем сначала пробел, потом слово
            }
        }
        // если последнее слово не поместилось в последнюю строку
        if (strTemp.length() != 0) {
            result.append(strTemp);
        }
        return result.toString();
    }

    // группируем строку в кластер скобок
    public static String[] split(String str) {
        int opened = 0; // число откртых скобочек (
        int closed = 0; // число закрытых скобочек )
        List<String> list = new ArrayList<>(); // альтернатива изменяемому массиву
        StringBuilder strTemp = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') { // если открытая скобочка
                opened += 1;
                strTemp.append("(");

            } else if (str.charAt(i) == ')') { // если закрытая
                closed += 1;
                strTemp.append(")");
            }
            // кластер сбалансирован, когда число открытых скобок равно числу закрытых
            if (opened == closed) {
                list.add(strTemp.toString()); // добавляем кластер
                opened = 0; // обнуляем все изменённые временные переменные
                closed = 0;
                strTemp = new StringBuilder();
            }
        }
        return list.toArray(new String[]{}); // делаем массив
    }

    // преобразуем строку в CamelCase
    public static StringBuilder toCamelCase(String str) {
        StringBuilder result = new StringBuilder();
        while (str.contains("_")) {
            result.append(str.substring(0, str.indexOf("_"))); // добавляем к результату всё до _
            // первая буква (заглавная) после _ + всё остальное
            // из ASCII для строчной буквы вычитаем 32 - получаем заглавную
            str = (char) (str.charAt(str.indexOf("_") + 1) - 32) +
                    str.substring(str.indexOf("_") + 2);
        }
        result.append(str);
        return result;
    }

    // преобразуем строку в SnakeCase
    public static StringBuilder toSnakeCase(String str) {
        StringBuilder result = new StringBuilder();
        // пока в строке есть 1 и более заглавные буквы
        Pattern p = Pattern.compile("[A-Z]+");
        Matcher m = p.matcher(str);
        while (m.find()) {
            result.append(str.substring(0, m.start())); // добавляем к результату всё до заглавной буквы
            // Заглавная буква (теперь строчная) + всё остальное
            // к ASCII для прописной буквы прибавляем 32 - получаем строчную
            str = "_" + (char) (str.charAt(m.start()) + 32) +
                    str.substring(m.start() + 1);
            m = p.matcher(str);
        }
        result.append(str);
        return result;
    }
}