package leetcode;


/*
 1,0,0,0,0
 0,1,0,2,2
 0,1,0,2,0
 0,1,0,0,2
 */
public class CheckWuziqi {
   static int a[][]=new int[][]{{1,0,0,0,0},{ 0,1,0,2,2},{0,1,0,2,0},{0,1,0,0,2}};
    public  static boolean dfs(int [][] a,int x,int y,int flag,int count){
        if (count==3) return true;
        if (x>=4||y>=5) return false;
        if (x<3&&a[x+1][y]==flag) return dfs(a,x+1,y,flag,count+1);
        if (y<4&&a[x][y+1]==flag) return dfs(a,x,y+1,flag,count+1);
        if (x<3&&y<4&&a[x+1][y+1]==flag) return dfs(a,x+1,y+1,flag,count+1);
        return false;

    }

    public static void main(String[] args) {
        for (int i=0;i<a.length;i++){
            for (int j=0;j<a.length;j++){
                if (a[i][j]!=0){
                   if (dfs(a,i,j,a[i][j],1)){
                       System.out.println(a[i][j]);
                       if (a[i][j]==1) System.out.println("1 win");
                       else System.out.println("2 win");
                   }
                }
            }
        }
    }
}
