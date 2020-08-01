package leetcode;

public class DFS {
    char[][] board; String word;
    boolean status[][];
    public boolean exist(char[][] board, String word) {
          this.board=board; this.word=word;
          this.status=new boolean[board[0].length][board.length];
            for(int i=0;i<board.length;i++)
                for (int j=0;j<board[0].length;j++){
                        //status[i][j]=true;
                        if (dfs(i,j,0)==true)
                            return true;

                }
                return false;
    }

    private boolean dfs( int x, int y,int index) {
        if (index==word.length())
            return true;
        if (status[x][y]==true)
            return false;
        if (x<0||y<0) return false;
        if (x>=board.length||y>=board[0].length)
            return false;
        if(board[x][y]!=word.charAt(index))
            return false;
        else
            status[x][y]=true;
            boolean ans= (dfs(x+1,y,index+1)||dfs(x-1,y,index+1)||dfs(x,y+1,index+1)||
                    dfs(x,y-1,index+1));
            //不管成功与否此处都应回溯
        // 因为是false回到上一层重新走
        status[x][y]=false;

        return ans;
        }

}
