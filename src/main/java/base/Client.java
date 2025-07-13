package base;
class Person{
    String name;
}

public class Client {
    public static void main(String[] args) {
        Person person = new Person();
        person.name = "Bob";
        check(person);
        System.out.println("My name is"+person.name);
    }
    private static void check(Person person) {
        Person jiang = new Person();
        person.name = "Jiang";
    }
}
