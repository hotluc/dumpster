package javaBase;
class A1{

}
class A2{}

public class B {
    public static void reverse(String str){
        char[] chars = str.toCharArray();
        int left = 0,right = str.length() - 1;
        while (left++<right--){
            swap(chars[left],chars[right]);
        }

        /*for (int i = 0; i < chars.length/2; i++) {
            char temp = chars[i];
            chars[i] = chars[chars.length-1-i];
            chars[chars.length-1-i] = temp;
        }*/
        System.out.println(chars);
    }
    public static void swap(char a,char b){
        char temp = a;
        a = b;
        b = temp;
        System.out.println(a);
        System.out.println(b);
    }

    public static int try1(){
        try {
            System.out.println("try");
            return 0;

        }
        finally {
            System.out.println("finally");
        }
    }
    public static void main(String[] args) {
        try1();
        reverse("abc");
        StringBuilder stringBuilder = new StringBuilder("abc");
        swap('a','b');
    }
}
