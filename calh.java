import java.util.Scanner;

public class calh {
    static Scanner scanner = new Scanner(System.in);
    static int n1, n2, check = 0, check1=0;
    static int result;
    static char operation;

    public static void main(String[] args) throws Exception {
        System.out.println("Программа калькулятор предназначена для выполнения простых операций с римскими и арабскими цифрами от 0 до 10");
        System.out.println("Введите выражение в формате a + b, Внимание, используйте только либо римские, либо арабские цифры");
        System.out.println("Поддерживаемые операции это Сложение +, Умножение *, Деление / и Вычетание -, другие символы не поддерживаются");
        // прянятие данных из строки
        String userEnter = scanner.nextLine();
        if (userEnter.length()<3){
            System.err.println("строка не является математической операцией");
            throw new Exception();
        }
        // подготовка и создание массива под ввод пользовательских данных.
        //Максимальный размер массива выбран из гипотетического ввода максимального числа символов (VIII + VIII )
        char[] numsEndOp = new char[12];
        //неаполнение массива, определение знака операции
        for (int i = 0; i < userEnter.length(); i++) {
            numsEndOp[i] = userEnter.charAt(i);
            if (numsEndOp[i] == '+') {
                operation = '+';
                check1++;
            }
            if (numsEndOp[i] == '-') {
                operation = '-';
                check1++;
            }
            if (numsEndOp[i] == '*') {
                operation = '*';
                check1++;
            }
            if (numsEndOp[i] == '/') {
                operation = '/';
                check1++;
            }
        }
        if (check1>1){
            System.out.printf("формат математической операции не удовлетворяет заданию - два операнда и один оператор");
            throw new Exception();
        }
        // определив оператор, переходим к вылавливанию и определению чисел a и b
        // сменим тип перменной с char на string, поместив его в новую переменную
        String strNumsEndOp = String.valueOf(numsEndOp);
        // порежим массив на входные числа, использую сплит по оператору калькуляции
        // создадим новый массив для разделённых чисел
        String[] num = strNumsEndOp.split("[+-/*]");
        // создадим новые перменные для полученых чисел и запишем их туда
        String num1 = num[0];
        String num2 = num[1];
        // обрежем хвост массива и поместим второе значение в другую перменную
        String stringNums1 = num1.trim();
        String stringNums2 = num2.trim();

        // проверяем через функцию романтранслятор числа на их принадлежность к римским и если всё ок отправляем их функцию калькуляции
        // если цифры не пренадлежат к римским функция вернет ключевое значение -1, за счет которого мы сможем реализовать проверку
        n1 = romanTranslator(stringNums1);
        n2 = romanTranslator(stringNums2);
        if (n1 < 0 || n2 < 0) {
            result = 0;}
        // если всё впорядке переходим к блоку калькуляции
        else {
            result = calcul(n1, n2, operation);

            // создадим переменную для перевода вычмсоений ищ арабских обратно в римские
            // обращаемся к методу переводчика и передаем ему результат наших вычислений


            try {
                String resRim = convertRim(result);
                System.out.println("Ваш ответ: " + resRim);

                // проверяем на ошибки
            } catch (Exception e){
                System.err.println(" в римской системе нет отрицательных чисел");
                throw new Exception();
            };
            if (result==0){
                System.err.println(" в римской системе нет значения 0");
                throw new Exception();
            }
            // переменная для проверки захода в цикл
            check++;
        }
        if (check == 0){
            try {
                n1 = Integer.parseInt(stringNums1);
                n2 = Integer.parseInt(stringNums2);
            }catch (Exception ex){
                System.err.println("ошибка, используются одновременно разные системы счисления ");
                throw new Exception();
            }
            if (n1 > 10 || n2>10){
                System.err.println("Калькулятор принимает на вход числа от 0 до 10");
                throw new Exception();
            }
            result = calcul(n1, n2, operation);
            if (result == -127){
                System.err.println("Ошибка, деление на 0");
                throw new ArithmeticException();
            } else  {
                System.out.println("Ваш ответ: " + result);
            }
        }
    }
    private static int romanTranslator(String roman) {

            if (roman.equals("I")) {
                return 1;
            } else if (roman.equals("II")) {
                return 2;
            } else if (roman.equals("III")) {
                return 3;
            } else if (roman.equals("IV")) {
                return 4;
            } else if (roman.equals("V")) {
                return 5;
            } else if (roman.equals("VI")) {
                return 6;
            } else if (roman.equals("VII")) {
                return 7;
            } else if (roman.equals("VIII")) {
                return 8;
            } else if (roman.equals("IX")) {
                return 9;
            } else if (roman.equals("X")) {
                return 10;
            }
        return -1;
    }
    public static int calcul (int num1, int num2, char op) {

        int result = 0;

        switch (op) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                try {
                    result = num1 / num2;
                } catch (ArithmeticException e) {
                    result = -127;
                    break;
                }
                break;
        }
        return result;
    }
    private static String convertRim (int num)  {
        String[] roman = {"ошибка, результат вычислений для римских чисел не может быть равен 0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
        };
        final String s = roman[num];
        return s;
    }
}