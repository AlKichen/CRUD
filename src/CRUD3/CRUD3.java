package CRUD3;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/*
Прайсы 2
программа, которая считывает с консоли путь к файлу для операций CRUD и при запуске в зависимости от флага, переданного в параметрах обновляет данные товара с заданным id или производит физическое удаление товара с заданным id (удаляет из файла все данные, которые относятся к переданному id).
-u id productName price quantity
-d id

Значения параметров:
-u - флаг, который означает обновление данных товара с заданным id
-d - флаг, который означает физическое удаление товара с заданным id (из файла удаляются все данные, которые относятся к переданному id)
id - id товара, 8 символов
productName - название товара, 30 символов
price - цена, 8 символов
quantity - количество, 4 символа

В файле данные хранятся в следующей последовательности (без разделяющих пробелов):
id productName price quantity

Данные дополнены пробелами до их длины.

Для чтения и записи файла нужно использовать FileReader и FileWriter соответственно.

Пример содержимого файла:
19847   Шорты пляжные синие           159.00  12
198479  Шорты пляжные черные с рисунко173.00  17
19847983Куртка для сноубордистов, разм10173.991234
*/

public class CRUD3 {
    public static void main(String[] args) {
        if (args.length == 0) {
            return;
        }
        switch (args[0]) {
            case "-u":
                update(args[1], args[2], args[3], args[4]);
                break;
            case "-d":
                delete(args[1]);
                break;
        }
    }

    public static void update(String idIn, String productNameIn, String priceIn, String quantityIn) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String fileName = br.readLine();
            ArrayList<String> arrayListRead = new ArrayList<>();
            FileReader fr = new FileReader(fileName);
            BufferedReader br2 = new BufferedReader(fr);
            String str = "";
            while (true) {
                str = br2.readLine();
                if (str == null) {
                    break;
                }
                arrayListRead.add(str);
            }
            ArrayList<Integer> idList = new ArrayList<>();
            for (String s : arrayListRead) {
                String id = s.substring(0, 8);
                String idWithoutSpace = id.replaceAll("\\s", "");
                Integer idInt = Integer.parseInt(idWithoutSpace);
                idList.add(idInt);
            }
            int indexS;
            for (int i : idList) {
                if (i == Integer.parseInt(idIn)) {
                    indexS = idList.indexOf(i);
                    String sAdd = rightpad(idIn, 8) +
                            rightpad(productNameIn, 30) +
                            rightpad(priceIn, 8) +
                            rightpad(quantityIn, 4);
                    arrayListRead.set(indexS, sAdd);
                }
            }
            FileWriter fw = new FileWriter(fileName);
            for (int i = 0; i < arrayListRead.size(); i++) {
                fw.write(arrayListRead.get(i) + "\n");
            }
            br2.close();
            fr.close();
            fw.close();
        } catch (IOException e) {
            e.getStackTrace();

        }
    }


    public static void delete(String idIn) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String fileName = br.readLine();
            ArrayList<String> arrayListRead = new ArrayList<>();
            FileReader fr = new FileReader(fileName);
            BufferedReader br2 = new BufferedReader(fr);
            String str = "";
            while (true) {
                str = br2.readLine();
                if (str == null) {
                    break;
                }
                arrayListRead.add(str);
            }
            ArrayList<Integer> idList = new ArrayList<>();
            for (String s : arrayListRead) {
                String id = s.substring(0, 8);
                String idWithoutSpace = id.replaceAll("\\s", "");
                Integer idInt = Integer.parseInt(idWithoutSpace);
                idList.add(idInt);
            }
            int indexS;
            for (int i : idList) {
                if (i == Integer.parseInt(idIn)) {
                    indexS = idList.indexOf(i);
                    arrayListRead.remove(indexS);
                }
            }
            FileWriter fw = new FileWriter(fileName);
            for (int i = 0; i < arrayListRead.size(); i++) {
                fw.write(arrayListRead.get(i) + "\n");
            }
            br2.close();
            fr.close();
            fw.close();
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    public static String rightpad(String text, int length) {
        return String.format("%-" + length + "." + length + "s", text);
    }
}

