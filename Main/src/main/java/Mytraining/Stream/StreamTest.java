package Mytraining.Stream;

import javax.annotation.processing.SupportedAnnotationTypes;
import java.util.Arrays;
import java.util.List;

/**
 * Testing methods and properties of Stream Apis.
 */
public class StreamTest {
    public static void main(String[] args) {
        List list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);

        /**
         * Testing methods,{@code filter, sorted, forEach} of Stream Apis.
         */
        list.stream().filter(a -> (int)a % 2 == 0).sorted((o1, o2) -> {
            if((int)o1 >= (int)o2){
                return -1;
            }else{
                return 1;
            }
        }).forEach(a -> {
                System.out.println(a);
            System.out.println(a + " " + (int)a*(int)a);
        });

        /**
         * Not did the stream change the original source data.
         */
        list.stream().forEach( a -> {
            System.out.println(a);
        });
    }
}
