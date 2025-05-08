package org.example;

import java.util.Arrays;

class Solution {

    public static void main(String[] args) {
        int[] nums1 = {-12,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        int[] nums2 = {-49,-45,-42,-41,-40,-39,-39,-39,-38,-36,-34,-34,-33,-33,-32,-31,-29,-28,-26,-26,-24,-21,-20,-20,-18,-16,-16,-14,-11,-7,-6,-5,-4,-4,-3,-3,-2,-2,-1,0,0,0,2,2,6,7,7,8,10,10,13,13,15,15,16,17,17,19,19,20,20,20,21,21,22,22,24,24,25,26,27,29,30,30,30,35,36,36,36,37,39,40,41,42,45,46,46,46,47,48};
        merge(nums1, 1, nums2,90);
        System.out.println(Arrays.toString(nums1));
    }
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int size = n+m;
        int k = 0;
        if(n==0){
            return;
        }

        if(m== 0){
            for(int i=0;i<nums1.length;i++){
                nums1[i]=nums2[i];
            }
            return;
        }

        for(int i=0;i<size;i++){
            boolean zeroDireita = verificaZeroDireita(nums1,i,m);
            System.out.println("Zero direita: "+zeroDireita + " I: "+i+ " Array: "+Arrays.toString(nums1) +" K: "+k+ " nums1[i]: "+nums1[i]+ " nums2[k]: "+nums2[k]);
            if(!zeroDireita && (nums1[i]<=nums2[k])){
                continue;
            }else{
                int value = nums1[i];
                nums1[i] = nums2[k];
                andarUm(nums1,value,i+1);
                k++;
            }
            if(k>=nums2.length){
                break;
            }
        }
    }

    private static boolean verificaZeroDireita(int[] nums1, int i, int m) {
        int anterior = i-1;
        if(anterior<0){
            return false;
        }
        boolean temPosterior = temPosterior(nums1,i+1);
        return (nums1[anterior] >= nums1[i] && nums1[i] == 0 && !temPosterior);
    }

    private static boolean temPosterior(int[] nums1, int i) {
        if(i>nums1.length){
            return false;
        }
       for(int k=i;k<nums1.length;k++){
           if(nums1[k]>0){
               return true;
           }
       }

       return false;
    }

    private static void andarUm(int[] nums1, int valor, int i) {
        for(int j = i;j<nums1.length;j++){
            int var = nums1[j];
            nums1[j] = valor;
            valor = var;
        }
    }
}