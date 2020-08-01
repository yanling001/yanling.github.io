package leetcode;

//130. 被围绕的区域
public class LeetCode_130 {
    public void solve(char[][] board) {
        int row,clo;
        row=board.length;
        clo=board[0].length;
        //先找到边上的0
        for(int i=0;i<row;i++){
            for(int j=0;j<clo;j++){
                boolean temp=i==0||j==0||i==row-1||j==clo-1;
                if (temp&&board[i][j]=='O'){
                     dfs(i,j,board);
                }
            }
        }
        for(int i=0;i<row;i++){
            for(int j=0;j<clo;j++){
                if (board[i][j]=='O'){
                     board[i][j]='X';
                }if (board[i][j]=='#')  board[i][j]='O';
            }
        }
    }

    private void dfs(int i, int j, char[][] board) {
        //出口的判断（当为"X"或已经走过的标记）
        if(board[i][j]=='X'||board[i][j]=='#'||i<0||j<0||i>=board.length||j>=board[0].length)
           return;
        board[i][j]='#';
        dfs(i-1,j,board);
        dfs(i,j-1,board);
        dfs(i+1,j,board);
        dfs(i,j+1,board);
    }
}
