package algorithm.printtable;

//打表
public class EatGrass {
    public static String win1(int n) {
        return f(n, "A");
    }

    private static String f(int rest, String cur) {
        String enemy = cur.equals("A") ? "B" : "A";
        if (rest < 5) {
            return (rest == 0 || rest == 2) ? enemy : cur;
        }
        int pick = 1;
        while (pick <= rest) {
            if (f(rest - pick, enemy).equals(cur)) {
                return cur;
            }
            pick *= 4;
        }
        return enemy;
    }

    public static String win2(int n) {
        if (n % 5 == 0 || n % 5 == 2) {
            return "B";
        } else {
            return "A";
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i <= 50; i++) {
            System.out.println(i + ":" + win1(i));
        }
    }
}
