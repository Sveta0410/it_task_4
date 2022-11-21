import java.util.*;
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

        System.out.println("Задание 4");
        double[] array1 = new double[]{9, 17, 30, 1.5};
        double[] array2 = new double[]{16, 18, 30, 1.8};
        double[] array3 = new double[]{13.25, 15, 30, 1.5};
        System.out.println(overTime(array1));
        System.out.println(overTime(array2));
        System.out.println(overTime(array3));

        System.out.println("Задание 5");
        System.out.println(BMI("205 pounds", "73 inches"));
        System.out.println(BMI("55 kilos", "1.65 meters"));
        System.out.println(BMI("154 pounds", "2 meters"));

        System.out.println("Задание 6");
        System.out.println(bugger(39));
        System.out.println(bugger(999));
        System.out.println(bugger(4));

        System.out.println("Задание 7");
        System.out.println(toStarShorthand("abbccc"));
        System.out.println(toStarShorthand("77777geff"));
        System.out.println(toStarShorthand("abc"));
        System.out.println(toStarShorthand(""));

        System.out.println("Задание 8");
        System.out.println(doesRhyme("Sam I am!", "Green eggs and ham."));
        System.out.println(doesRhyme("Sam I am!", "Green eggs and HAM."));
        System.out.println(doesRhyme("You are off to the races", "a splendid day."));
        System.out.println(doesRhyme("and frequently do?", "you gotta move."));


        System.out.println("Задание 9");
        System.out.println(trouble(451999277, 41177722899L)); // L т.к. Integer number too large -> используем long
        System.out.println(trouble(1222345, 12345));
        System.out.println(trouble(666789, 12345667));
        System.out.println(trouble(33789, 12345337));

        System.out.println("Задание 10");
        System.out.println(countUniqueBooks("AZYWABBCATTTA", 'A'));
        System.out.println(countUniqueBooks("$AA$BBCATT$C$$B$", '$'));
        System.out.println(countUniqueBooks("ZZABCDEF", 'Z'));


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
            result.append(str, 0, str.indexOf("_")); // добавляем к результату всё до _
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
            result.append(str, 0, m.start()); // добавляем к результату всё до заглавной буквы
            // Заглавная буква (теперь строчная) + всё остальное
            // к ASCII для прописной буквы прибавляем 32 - получаем строчную
            str = "_" + (char) (str.charAt(m.start()) + 32) +
                    str.substring(m.start() + 1);
            m = p.matcher(str);
        }
        result.append(str);
        return result;
    }

    // вычисляем сверхурочную работу и оплату, связанную с сверхурочной работой
    //Работа с 9 до 5: обычные часы работы
    //После 5 вечера - сверхурочная работа
    public static String overTime(double[] arr) {
        double start = arr[0]; // Начало рабочего дня
        double finish = arr[1]; // Конец рабочего дня.
        double hourlyRate = arr[2]; // Почасовая ставка
        double overtimePayFactor = arr[3]; // Множитель сверхурочных работ
        double payment = 0;

        if (start >= 9 && start < 17 && finish <= 17) {
            // если работал в промежуток с 9 до 17
            payment = (finish - start) * hourlyRate;
        } else if (start >= 17 && finish > 17) {
            // если начал работать после 17, закончил до 12 ночи
            payment = (finish - 17) * hourlyRate * overtimePayFactor;
        } else if (start >= 17 && finish <= 9) {
            // если начал работать после 17, работал ночью, закончил до 9 утра
            // 24 - 17 -> до полуночи + finish -> от полуночи до момента, когда закончил
            payment = (24 - 17 + finish) * hourlyRate * overtimePayFactor;
        } else if (start < 17 && finish > 17) {
            // если начал работать до 17, закончил до 12 ночи
            payment = (17 - start) * hourlyRate +
                    (finish - 17) * hourlyRate * overtimePayFactor;
        } else if (start < 17 && finish <= 9) {
            // если начал работать до 17, работал ночью, закончил до 9 утра
            payment = (17 - start) * hourlyRate +
                    (24 - 17 + finish) * hourlyRate * overtimePayFactor;
        }
        return "$" + String.format("%.2f", payment); // округляем до сотой
    }

    // расчёт индекса массы тела
    public static String BMI(String weight, String height) {
        String[] weightArr = weight.split(" ");
        String[] heightArr = height.split(" ");
        double w;
        double h;
        double bmi;

        if ("pounds".equals(weightArr[1])) {
            w = Double.parseDouble(weightArr[0]) * 0.453592; // переводим из фунтов в килограммы
        } else {
            w = Double.parseDouble(weightArr[0]);
        }

        if ("inches".equals(heightArr[1])) {
            h = Double.parseDouble(heightArr[0]) / 39.37; // из дюймов в метры
        } else {
            h = Double.parseDouble(heightArr[0]);
        }

        bmi = (w / (h * h));
        double bmiRound = (double) Math.round(bmi * 10) / 10; // округляем до десятых

        if (bmiRound < 18.5) {
            return (bmiRound + " Underweight"); // Недостаточный вес: <18,5
        } else if (bmiRound >= 18.5 && bmiRound <= 24.9) {
            return (bmiRound + " Normal weight"); //Нормальный вес: 18.5-24.9
        } else {
            return (bmiRound + " Overweight"); // Избыточный вес: 25 и более
        }
    }

    // принимаем число и возвращаем его мультипликативное постоянство
    // (количество раз, которое мы должны умножать цифры в num, пока не достигнем одной цифры)
    public static int bugger(int num) {
        int count = 0; // счётчик
        int product; // произведение

        // пока в числе больше одной цифры (иначе само число будет меньше 10)
        while (num > 10) {
            product = 1;
            // пока мы не перемножим все цифры в числе (5 / 10 = 0)
            while (num > 0) {
                product *= num % 10;
                num /= 10;
            }
            count++;
            num = product;
            System.out.println(num);
        }
        return count;
    }

    // преобразование строки в звездную стенографию
    // (Если символ повторяется n раз, преобразуйте его в символ *n.)
    public static StringBuilder toStarShorthand(String str) {
        StringBuilder result = new StringBuilder();
        int strLength = str.length();
        int n; // число повторений
        for (int i = 0; i < strLength; i++) {
            n = 1;
            for (int j = i + 1; j < strLength; j++) {
                // если элемент 1 равен элементу 2
                if (str.charAt(i) == str.charAt(j)) {
                    n++; // + одно повторение
                    i++; // следовательно, мы больше по этому элементу не проходим в i
                }
            }
            result.append(str.charAt(i)); // добавляем букву
            // если буква не одна такая, то добавляем множитель
            if (n != 1) {
                result.append("*").append(n);
            }
        }
        return result;
    }

    // возвращаем true, если две строки рифмуются, и false в противном случае
    // (две строки рифмуются, если последнее слово из каждого предложения содержит одни и те же гласные)
    public static boolean doesRhyme(String a, String b) {
        String[] aArr = a.split(" "); // делаем из строки массив
        String[] bArr = b.split(" ");
        String aLastWord = aArr[aArr.length - 1].toLowerCase(); // берём последнее слово и делаем все буквы маленькими
        String bLastWord = bArr[bArr.length - 1].toLowerCase();

        return findVowels(aLastWord).equals(findVowels(bLastWord));
    }

    // вспомогательная функция для doesRhyme, находим все главные в слове
    public static String findVowels(String word) {
        StringBuilder result = new StringBuilder();
        String[] wordArr = word.split("");
        for (String i : wordArr) {
            if ("a".equals(i) || "e".equals(i) || "i".equals(i) || "o".equals(i) || "u".equals(i) || "y".equals(i)) {
                result.append(i);
            }
        }
        return result.toString();
    }

    // принимаем два целых числа и возвращаем true, если число повторяется три раза подряд в любом месте в num1
    // и то же самое число повторяется два раза подряд в num2
    public static boolean trouble(long num1, long num2) {
        char[] n1 = Long.toString(num1).toCharArray(); // делаем массив из символов
        char[] n2 = Long.toString(num2).toCharArray(); // делаем массив из символов
        // число, которое будет повторяться с num1 и num2
        char number = 'a'; // ('a' просто чтобы у number было какое-то значение)

        // перебираем символы в массиве
        for (int i = 0; i < (n1.length - 2); i++) {
            // если есть три равных символа подряд
            if (n1[i] == n1[i + 1] && n1[i] == n1[i + 2]) {
                number = n1[i];
                break;
            }
        }
        for (int i = 0; i < (n2.length - 1); i++) {
            // если есть два равных символа подряд + они равны нашему number
            // если number == 'a', мы не нашои три одинаковые цифры подряд
            if (number != 'a' && number == n2[i] && n2[i] == n2[i + 1]) {
                return true;
            }
        }
        return false;
    }

    // возвращаем общее количество уникальных символов (книг, так сказать) между всеми парами концов книги
    // (пара одинаковых символов служит концами книги для всех символов между ними)
    public static int countUniqueBooks(String stringSequence, char bookEnd) {
        // hashset - набор значений (Набор — это коллекция, которая не содержит повторяющихся элементов)
        Set<String> res = new HashSet<>();
        int result = 0;
        String[] temp; // чтобы разбивать слова на массив из букв
        String[] strArr = stringSequence.split("[" + bookEnd + "]"); // [A], например (регулярное выражение)
        // (нас интересуют нечётные элементы массива/отсчёт от нуля)
        // 0_1_2_3_4 (_ - разделитель, между двумя разделителями оказываются элементы 1, 3 и т.д.)
        for (int i = 1; i < strArr.length; i += 2) {
            temp = strArr[i].split("");
            for (String j : temp) {
                if (!"".equals(j)) {
                    res.add(j);
                }
            }
            result += res.size();
            res.clear(); // Удаляет все элементы из объекта HashSet<T>.
        }
        return result;
    }
}