package leetcode;
//164 最大间距
public class LeetCode_164 {
    class Bucket{
        int min=Integer.MAX_VALUE;
        int max=Integer.MIN_VALUE;
    }
    //算法本质就是桶排序
    public int maximumGap(int[] nums) {
        int min=Integer.MAX_VALUE;
        int max=Integer.MIN_VALUE;
        if (nums == null || nums.length < 2) {
            return 0;
        }
        for (int i=0;i<nums.length;i++){
            min=Math.min(min,nums[i]);
            max=Math.max(max,nums[i]);
        }

        // 分配桶的长度和个数是桶排序的关键
        // 在 n 个数下，形成的两两相邻区间是 n - 1 个，比如 [2,4,6,8] 这里
        // 有 4 个数，但是只有 3 个区间，[2,4], [4,6], [6,8]
        // 因此，桶长度 = 区间总长度 / 区间总个数 = (max - min) / (nums.length - 1)
        //每个桶的范围(一个桶方元素的范围)
        int grap = Math.max(1,(max-min)/(nums.length-1));
        //要多少个桶(保证会有一个空桶)
        int szie = (max-min)/grap+1;
        Bucket buckets[]=new Bucket[szie];
        for (int i=0;i<nums.length;i++){
            //    看它在那个桶
            int cle=(nums[i]-min)/grap;
            if (buckets[cle]==null)
                buckets[cle]=new Bucket();
            buckets[cle].max=Math.max(buckets[cle].max,nums[i]);
            buckets[cle].min=Math.min(buckets[cle].min,nums[i]);
        }
        //遍历桶数组得到结果
        int previousMax = Integer.MAX_VALUE; int maxGap = Integer.MIN_VALUE;
        for (int i = 0; i < buckets.length; ++i) {
            if (buckets[i] != null && previousMax != Integer.MAX_VALUE) {
                maxGap = Math.max(maxGap, buckets[i].min - previousMax);
            }

            if (buckets[i] != null) {
                previousMax = buckets[i].max;
                maxGap = Math.max(maxGap, buckets[i].max - buckets[i].min);
            }
        }

        return maxGap;


    }
}
