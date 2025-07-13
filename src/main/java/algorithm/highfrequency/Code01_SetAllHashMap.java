package algorithm.highfrequency;
// setAll功能的哈希表
// 测试链接 : https://www.nowcoder.com/practice/7c4559f138e74ceb9ba57d76fd169967
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的code，提交时请把类名改成"Main"，可以直接通过

import java.io.*;
import java.util.Map;

public class Code01_SetAllHashMap {
    public static Map<Integer,int[]> map;
    public static int setAllValue;
    public static int setAllTime;
    public static int cnt;
    public static void put(int key,int value){
        if(map.containsKey(key)){
            int[] values = map.get(key);
            values[0]=value;
            values[1]=cnt++;
        }
        else{
            map.put(key,new int[]{value,cnt++});
        }
    }
    public static void setAll(int value){
        setAllValue=value;
        setAllTime=cnt++;
    }
    public static int get(int k){
        if(!map.containsKey(k)){
           return -1;
        }
        int[] values = map.get(k);
        if (values[1]>setAllTime){
            return values[0];
        }
        else{
            return setAllValue;
        }
    }
    public static int n, op, a, b;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            map.clear();
            setAllValue = 0;
            setAllTime = -1;
            cnt = 0;
            n = (int) in.nval;
            for (int i = 0; i < n; i++) {
                in.nextToken();
                op = (int) in.nval;
                if (op == 1) {
                    in.nextToken();
                    a = (int) in.nval;
                    in.nextToken();
                    b = (int) in.nval;
                    put(a, b);
                } else if (op == 2) {
                    in.nextToken();
                    a = (int) in.nval;
                    out.println(get(a));
                } else {
                    in.nextToken();
                    a = (int) in.nval;
                    setAll(a);
                }
            }
        }
        out.flush();
        out.close();
        br.close();
    }

}
