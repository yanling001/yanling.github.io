package leetcode;

//加油站
public class LeetCode_134 {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int start=0;
        int total_tank = 0;
        int curr_tank = 0;
        for(int i=0;i<gas.length;i++){
            total_tank+=gas[i]-cost[i];
            curr_tank+=gas[i]-cost[i];
            if (curr_tank<0) {
                curr_tank=0;
                start=i;
            }
        }
        if (total_tank>=0) return start;
        return -1;
    }
}
