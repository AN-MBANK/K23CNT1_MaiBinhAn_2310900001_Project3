package com.AnNoOfDev.lesson01_Mba_k23cnt1.tight_loosely_coupling;

import java.util.Arrays;

public class TighCouplingService{
    private BubbleSortAlgorithm bubbleSortAlgorithm =new BubbleSortAlgorithm();
    public TighCouplingService (){}
    public TighCouplingService (BubbleSortAlgorithm bubbleSortAlgorithm){
        this.bubbleSortAlgorithm = bubbleSortAlgorithm;
    }
    public void complexBusinessSort (int[] arr){
        bubbleSortAlgorithm.sort(arr);
        Arrays.stream(arr).forEach(System.out::println);
    }
    public static void main(String[] args){
        TighCouplingService tCouplingService = new TighCouplingService();
        tCouplingService.complexBusinessSort(new int[]{10,11,12,13,14,43,51});
    }
}
