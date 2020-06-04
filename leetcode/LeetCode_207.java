package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//课程表 判断图有没有环
public class LeetCode_207 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
     //存储有向图的信息
        List<List<Integer>> lists=new ArrayList<>();
        int indu[]=new int[numCourses];//存储入度
        for(int i=0;i<numCourses;i++){
            lists.add(new ArrayList<>());
        }
        for(int i=0;i<prerequisites.length;i++){
            lists.get(prerequisites[i][1]).add(prerequisites[i][0]);
            indu[prerequisites[i][0]]++;
        }
        Queue<Integer> queue=new LinkedList();
        //找到图的入口 入度为零
        for (int i=0;i<numCourses;i++){
            if (indu[i]==0) queue.add(i);
        }
        //bfs
        while (!queue.isEmpty()){
            int pre=queue.poll();
            numCourses--;
        for (int cur : lists.get(pre)){
            if (--indu[cur]==0) queue.add(cur);
        }
        }
        return  numCourses==0;
    }
}
