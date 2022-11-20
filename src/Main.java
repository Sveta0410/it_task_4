public class Main {
    public static void main(String[] args) {
        System.out.println("Задание 1");
        System.out.println(bessi(10, 7, "hello my name is Bessie and this is my essay"));

    }
    // помогаем Бесси правильно оформить эссе
    public static String bessi(int n, int k, String str) {
        String[] words = str.split(" "); // массив со словами из строки
        String strTemp = ""; // temporary - временная строка
        String result = "";
        // перебиреем слова в массиве
        for (String word : words){
            // если слово в строчку не помещается, создаём новую
            if (strTemp.length() + word.length() > k){
                result += strTemp + '\n';
                strTemp = "";
            }

            // условия для добавления слов во временную строку
            if (strTemp.length() == 0) {
                strTemp += word; // если слово в строке первое - прибавляем просто его
            } else {
                strTemp += " " + word; // если слово НЕ первое - прибавляем сначала пробел, потом слово
            }

        }
        // если последнее слово не поместилось в последнюю строку
        if (strTemp.length() != 0){
            result += strTemp;
        }
        return result;
    }

}