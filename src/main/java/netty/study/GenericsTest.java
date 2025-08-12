package netty.study;

import javaBase.B;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class GenericsTest {
    static class Box<T> {
        String name;
        int price;
        public Box(String name, int price) {
            this.name = name;
        }
    }
    public static void main(String[] args) {
        String[] strings = new String[3];
        Box[]  boxes = new Box[3];
    }
}
