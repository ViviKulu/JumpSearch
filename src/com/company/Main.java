package com.company;

import sun.security.util.Length;

import java.sql.Time;

public class Main {

//    The basic idea is to check fewer elements (than linear search)
//    by jumping ahead by fixed steps or skipping some elements in place
//    of searching all elements.

//    Let’s consider the following array: (0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610).
//    Length of the array is 16. Jump search will find the value of 55 with the following steps assuming
//    that the block size to be jumped is 4.

//    STEP 1: Jump from index 0 to index 4;
//    STEP 2: Jump from index 4 to index 8;
//    STEP 3: Jump from index 8 to index 12;
//    STEP 4: Since the element at index 12 is greater than 55 we will jump back a step to come to index 9.
//    STEP 5: Perform linear search from index 9 to get the element 55.

//    In the worst case, we have to do n/m jumps and if the last checked value is greater than the element
//    to be searched for, we perform m-1 comparisons more for linear search. Therefore the total number of
//    comparisons in the worst case will be ((n/m) + m-1). The value of the function ((n/m) + m-1) will be
//    minimum when m = √n. Therefore, the best step size is m = √n.

//    Time Complexity : O(√n)
//    Auxiliary Space : O(1)


//    How to choose the step’s length --

//    We know that it is a good practice to use a fixed size step. Actually when the step is 1, the algorithm
//    is the traditional sequential search. The question is what should be the length of the step and is there
//    any relation between the length of the list (n) and the length of the step (k)? Indeed there is such a
//    relation and often you can see sources directly saying that the best length k = √n. Why is that?
//
//    Well, in the worst case, we do n/k jumps and if the last checked value is greater than the desired one,
//    we do at most k-1 comparisons more. This means n/k + k – 1 comparisons. Now the question is for what
//    values of k this function reaches its minimum. For those of you who remember maths classes this can be
//    found with the formula -n/(k^2) + 1 = 0. Now it’s clear that for k = √n the minimum of the function is reached.
//
//    Of course you don’t need to prove this every time you use this algorithm. Instead you can directly
//    assign √n to be the step length. However it is good to be familiar with this approach when trying
//    to optimize an algorithm.

    public static void main(String[] args) {
        // write your code here

        int a[] = {0, 1, 1, 2, 3, 5, 8, 13, 21,
                34, 55, 89, 144, 233, 377, 610};
        int x = 2;

        int index = jumpSearch(a, x);

        System.out.println("Our number is at " + index);

    }

    public static int jumpSearch(int[] a, int x) {

        int n = a.length;

        int step = (int) Math.floor(Math.sqrt(n)); // Step finds the block size to be jumped.
        int prev = 0;

        //This instruction is in charge of finding the block where the element is present,
        //if the element is present.
        while (a[Math.min(step, n) - 1] < x) {
            prev = step;
            step += (int) Math.floor(Math.sqrt(n));
            if (prev >= n)
                return -1;
        }

        //This instruction is in charge of performing the linear search for x in the block
        //beginning with prev.
        while (a[prev] < x) {
            prev++;

            //If we reached the next block or the end of the array,
            //we can consider our element not present in array.
            if (prev == Math.min(step, n))
                return -1;
        }

        //This instruction returns prev, if the element is found.
        if (a[prev] == x) {
            return prev;

        }
        return -1;
    }

}

//    Like binarySearch, jumpSearch only works on sorted arrays.
//    The optimal size of a block to be jumped is (√ n).
//    The time complexity of Jump Search is between Linear Search ( ( O(n) ) and Binary Search ( O (Log n) ).
