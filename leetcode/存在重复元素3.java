class Solution {
    // 1.  使用java的 TreeSet， 中的ceiling(E e)   floor(E e)
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Integer> set= new TreeSet();
        for(int i=0;i<nums.length;i++){
            Integer s = set.ceiling(nums[i]);
            if(s!=null && s<=t+nums[i]) return true;  //s-nums[i]<=t 能这么写因为有绝对值
            Integer d= set.floor(nums[i]);
            if(d!=null && nums[i]<=t+d) return true;
            set.add(nums[i]);
            if(set.size()>k){
                set.remove(nums[i-k]);
            }
        }
        return false;
    }
}