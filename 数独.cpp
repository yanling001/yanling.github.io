#include <stdio.h>
#include <stdlib.h>
int a[9][9];
bool check(int x,int y,int i)
{
	int j,k;
  for(int s=0;s<9;s++)//判断行
  {
	  if(a[x][s]==i)
		  return false;
  }
 for(s=0;s<9;s++)//判断列
  {
	  if(a[s][y]==i)
		  return false;
  }
for(j=(x/3)*3;j<(x/3)*3+3;j++)//判断九宫格
for(k=(y/3)*3;k<(y/3)*3+3;k++)
  if(a[j][k]==i)
	  return false;


  return true;

}
void dfs(int x,int y)
{
	if(x==9)
	{
		printf("************************************\n");
		for(int j=0;j<9;j++)//输出
		{
		   for(int k=0;k<9;k++)
			   printf("%d ",a[j][k]);
		   printf("\n");
		}
   exit(0);
	}

if(a[x][y]==0)
{
  for(int i=1;i<=9;i++)
  {
     bool b=check(x,y,i);
	 if(b)
	 {
		 a[x][y]=i;
		 dfs(x+(y+1)/9,(y+1)%9);
	 }
  }
 a[x][y]=0;//回溯
}else
{
 dfs(x+(y+1)/9,(y+1)%9);
}

}
int main()
{
for(int j=0;j<9;j++)//输入
   for(int k=0;k<9;k++)
	   scanf("%d",&a[j][k]);
dfs(0,0);

	return 0;
}
