package leetcode;

import java.util.*;

public class LeetCode_347 {

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> map=new HashMap<>();
        for (int i=0;i<nums.length;i++){
            map.put(nums[i],map.getOrDefault(nums[i],0)+1);
        }

        PriorityQueue<Integer> priorityQueue=new PriorityQueue(( n1,n2)->map.get(n1)-map.get(n2));
   for (Integer i:map.keySet()){
       priorityQueue.add(i);

       if (priorityQueue.size() > k)
           priorityQueue.poll();
   }
       int[] top_k = new int[k];
       int i=k-1;
        while (!priorityQueue.isEmpty()){
            top_k[i--]=priorityQueue.poll();
        }
       return top_k;


    }
//    class  Mycomparter implements Comparator<Integer>{
//
//        @Override
//        public int compare(Integer o1, Integer o2) {
//            if (map)
//            return 0;
//        }

}

/*
//基于桶排序求解「前 K 个高频元素」
class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> res = new ArrayList();
        // 使用字典，统计每个元素出现的次数，元素为键，元素出现的次数为值
        HashMap<Integer,Integer> map = new HashMap();
        for(int num : nums){
            if (map.containsKey(num)) {
               map.put(num, map.get(num) + 1);
             } else {
                map.put(num, 1);
             }
        }

        //桶排序
        //将频率作为数组下标，对于出现频率不同的数字集合，存入对应的数组下标
        List<Integer>[] list = new List[nums.length+1];
        for(int key : map.keySet()){
            // 获取出现的次数作为下标
            int i = map.get(key);
            if(list[i] == null){
               list[i] = new ArrayList();
            }
            list[i].add(key);
        }

        // 倒序遍历数组获取出现顺序从大到小的排列
        for(int i = list.length - 1;i >= 0 && res.size() < k;i--){
            if(list[i] == null) continue;
            res.addAll(list[i]);
        }
        return res;
    }
}
 */
