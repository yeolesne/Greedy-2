/*
 * #621. Task Scheduler
 * 
 * Given a characters array tasks, representing the tasks a CPU needs to do, where each letter represents a different task. Tasks could be done in any order. Each task is done in one unit of time. For each unit of time, the CPU could complete either one task or just be idle.

However, there is a non-negative integer n that represents the cooldown period between two same tasks (the same letter in the array), that is that there must be at least n units of time between any two same tasks.

Return the least number of units of times that the CPU will take to finish all the given tasks.

 

Example 1:

Input: tasks = ["A","A","A","B","B","B"], n = 2
Output: 8
Explanation: 
A -> B -> idle -> A -> B -> idle -> A -> B
There is at least 2 units of time between any two same tasks.

Example 2:

Input: tasks = ["A","A","A","B","B","B"], n = 0
Output: 6
Explanation: On this case any permutation of size 6 would work since n = 0.
["A","A","A","B","B","B"]
["A","B","A","B","A","B"]
["B","B","B","A","A","A"]
...
And so on.

Example 3:

Input: tasks = ["A","A","A","A","A","A","B","C","D","E","F","G"], n = 2
Output: 16
Explanation: 
One possible solution is
A -> B -> C -> A -> D -> E -> A -> F -> G -> A -> idle -> idle -> A -> idle -> idle -> A
 

Constraints:

1. 1 <= task.length <= 104
2. tasks[i] is upper-case English letter.
3. The integer n is in the range [0, 100].

 */


/*
 * Time Complexity: O (N) -> To traverse the characters to compute its frequency
 * 
 * Space Complexity: O (N) -> HashMap to store 'N' characters and its frequency
 * 
 * Did this code successfully run on leetcode: Yes
 * 
 * Any problem you faced while coding this: No
 * 
 */

package com.s30.edu.greedy2;

import java.util.HashMap;

public class TaskScheduler {
	public int leastInterval(char[] tasks, int n) {
        
        // #1. Base condition, if tasks is null, no processes to schedule, return 0
        if(tasks == null || tasks.length == 0){
            return 0;
        }
        
        // #2. Create a HashMap to store frequency of each character in given string
        HashMap<Character, Integer> map = new HashMap<>();
        
        // #3. Initialize maxFreq and maxCount
        int maxFreq = Integer.MIN_VALUE;
        int maxCount = 0;
        
        // #4. Traverse through all the characters and fill the map with its frequencies
        for(char ch : tasks){
            map.put(ch, map.getOrDefault(ch, 0) + 1);
            
            // #4.1. Calculate the max frequency
            if(map.get(ch) > maxFreq){
                maxFreq = map.get(ch);
            }
            
        }
        
        // #5. Calculate the maxCount -> number of processes having maximum frequency
        for(int value : map.values()){
            if(value == maxFreq){
                maxCount += 1;
            }
        }
        
        // #6. Compute number of intervals required for processing all characters/processes
        int partition = maxFreq - 1; // number of partitions
        int empty = (n - (maxCount - 1)) * partition; // number of empty spaces
        int pending = tasks.length - (maxFreq * maxCount); // pending processes to compute
        int idle = Math.max(0, empty - pending); // number of idle, if negative take max(0, -ve)
        
        // #7. return total intervals required to process all characters
        return (idle + tasks.length);
        
    }

}
