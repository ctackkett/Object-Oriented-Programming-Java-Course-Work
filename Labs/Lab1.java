public class Lab1 {
    public static String makeAbba(String a, String b) {
        System.out.println(a+b);
        System.out.println(a+b+b+a);
        return a+b+b+a;
    }

    public static main(String[] args) {
        makeAbba("hello", "world");
        makeAbba("connor", "tackkett");
        makeAbba("big", "dog");
    }
}