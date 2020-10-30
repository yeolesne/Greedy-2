/*
 * #135. Candy
 * 
 * There are N children standing in a line. Each child is assigned a rating value.

You are giving candies to these children subjected to the following requirements:

1. Each child must have at least one candy.
2. Children with a higher rating get more candies than their neighbors.

What is the minimum candies you must give?

Example 1:

Input: [1,0,2]
Output: 5
Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.

Example 2:

Input: [1,2,2]
Output: 4
Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
             The third child gets 1 candy because it satisfies the above two conditions.
             
 */

/*
 * Time Complexity: O (3N) = O (N) -> 3 for loops
 * 
 * Space Complexity: O (N) -> Array is created to store/keep track of number of candies assigned to each children
 * 
 * Did this code successfully run on leetcode: Yes
 * 
 * Any problem you faced while coding this: No
 * 
 */

package com.s30.edu.greedy2;

import java.util.Arrays;

public class MinimumCandies {
	public int candy(int[] ratings) {
        
        // #1. Base condition
        if(ratings == null || ratings.length == 0){
            return 0;
        }
        
        // #2. Create a new array to keep track of candies assigned to each children
        int[] candies = new int[ratings.length];
        
        /*
        *   Conditions:
        *      1. Each child must have at least one candy.
        *      2. Children with a higher rating get more candies than their neighbors.
        */
        
        // #3. Initially, all the children will be assigned 1 candy according to 1st condition
        Arrays.fill(candies, 1);
        
        // #4. Traverse the array from 1st index till the end to check rating with left neighbors and assign candies accordingly satisfying 2nd condition
        // O (N)
        for(int i = 1; i < ratings.length; i++){
            
            // #4.1. If rating of a children is greater than its left neighbor
            if(ratings[i] > ratings[i-1]){
                
                // #4.2. candies assign to the child will be maximum of (current candies, left neighbor's candy + 1)
            	candies[i] = candies[i-1] + 1;
            }
            
        }
        
        // #5. Traverse the array from 2nd last index till 0th index to check rating with right neighbors and assign candies accordingly satisfying 2nd condition
        // O (N)
        for(int j = ratings.length - 2; j >= 0; j--){
            
            // #5.1. If rating of a children is greater than its right neighbor
            if(ratings[j] > ratings[j+1]){
                
                // #5.2. candies assign to the child will be maximum of (current candies, right neighbor's candy + 1)
                candies[j] = Math.max(candies[j], candies[j+1] + 1);
            }
        }
        
        // #6. Initialize minimum number of candies to 0
        int minCandies = 0;
        
        // #7. Sum the number of candies assign to each children to get minimum candies given
        // O (N)
        for(int nums : candies){
            minCandies += nums;
        }
        
        // #8. Return the minimum number of candies
        return minCandies;
        
    }

}
