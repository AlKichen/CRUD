
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/*
CRUD - Create, Read, Update, Delete.

Программа запускается с одним из следующих наборов параметров:
-c name sex bd
-r id
-u id name sex bd
-d id

Значения параметров:
name - имя, String
sex - пол, "м" или "ж", одна буква
bd - дата рождения в следующем формате 15/04/1990
-c - добавляет человека с заданными параметрами в конец allPeople, выводит id (index) на экран
-r - выводит на экран информацию о человеке с id: name sex (м/ж) bd (формат 15-Apr-1990)
-u - обновляет данные человека с данным id
-d - производит логическое удаление человека с id, заменяет все его данные на null

id соответствует индексу в списке.
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) {
        switch (args[0]) {
            case "-c":
                create(args[1], args[2], args[3]);
                break;
            case "-r":
                read(args[1]);
                break;
            case "-u":
                update(args[1], args[2], args[3], args[4]);
                break;
            case "-d":
                delete(args[1]);
                break;
        }

    }

    public static void create(String name, String sex, String bd) {
        SimpleDateFormat birthDayFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Date birthDayDate = parseDate(birthDayFormat, bd);
        if (sex.equals("м")) {
            Person person = Person.createMale(name, birthDayDate);
            allPeople.add(person);
            System.out.println(allPeople.indexOf(person));
        } else if (sex.equals("ж")) {
            Person person = Person.createFemale(name, birthDayDate);
            allPeople.add(person);
            System.out.println(allPeople.indexOf(person));
        }
    }

    public static void read(String id) {
        Person person = allPeople.get(Integer.parseInt(id));
        String name = person.getName();
        Sex sex = person.getSex();
        String sexString = sex.equals(Sex.MALE) ? "м" : "ж";
        Date bd = person.getBirthDate();
        SimpleDateFormat bdFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        System.out.print(name + " ");
        System.out.print(sexString + " ");
        System.out.print(bdFormat.format(bd));
    }

    public static void update(String id, String name, String sexStr, String bd) {
        Person person = allPeople.get(Integer.parseInt(id));
        Sex sex = sexStr.equals("м") ? Sex.MALE : Sex.FEMALE;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Date birthDayDate = parseDate(dateFormat, bd);
        person.setName(name);
        person.setSex(sex);
        person.setBirthDate(birthDayDate);

    }

    public static void delete(String id) {
        Person person = allPeople.get(Integer.parseInt(id));
        person.setName(null);
        person.setSex(null);
        person.setBirthDate(null);
    }

    public static Date parseDate(SimpleDateFormat dateFormat, String date) {
        Date result = null;
        try {
            result = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }
}
