package K23CNT1_.Mba_day02.tight_loosely_coupling;

import java.util.Arrays;

public class LooselyBubbleSortAlgorithm implements SortAlgorithm {
    @Override
    public void sort(int[] array){
        System.out.println("Sorted using bubble sort algorithm");
        Arrays.stream(array).sorted().forEach(System.out::println);
    }
}
