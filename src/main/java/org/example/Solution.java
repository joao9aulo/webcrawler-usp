package org.example;

import java.util.Arrays;

class Solution {

    public static void main(String[] args) {
        int[] nums1 = {-1,0,1,1,0,0,0,0,0};
        int[] nums2 = {-1,0,2,2,3};
        merge(nums1, 4, nums2, 5);
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
        int posterior = i+1;
        boolean temPosterior = posterior<nums1.length && nums1[posterior]!=0;
        return (nums1[anterior] >= nums1[i] && nums1[i] == 0 && !temPosterior);
    }

    private static void andarUm(int[] nums1, int valor, int i) {
        for(int j = i;j<nums1.length;j++){
            int var = nums1[j];
            nums1[j] = valor;
            valor = var;
        }
    }
}