
/*
背包装重问题：例有{2(weight),3(value)}{1,2}{3,4}{2,2}
若背包容量为5求所能装的最大价值
*/
#include <stdio.h>
int max(int a,int b)
{
if(a>b)
return a;
else
return b;
}
int w[20];
int v[20];

//解法一递归解

int f(int  a,int n,int b)
{
	int v2;
	if(a==n||b<=0)
		return 0;
	v2=f(a+1,n,b);
	if(b>=w[a])
	{
       return max(v[a]+f(a+1,n,b-w[a]), v2);
                        
	}else 
	return v2;
}
int main()
{
 int n;
 int ww,num;
 scanf("%d",&n);
 scanf("%d",&ww);
 for(int i=0;i<n;i++)
 {
	 scanf("%d",&w[i]);
     scanf("%d",&v[i]);
 }
num=f(0,n,ww);
printf("%d",num);
return 0;
}

//解法二记忆+递归
int dp[20][20]={-1};
int max(int a,int b)
{
if(a>b)
return a;
else
return b;
}
int f(int  a,int n,int b)
{
	if(dp[a][b]>0)
		return dp[a][b];
	int v2,ans;
	if(a==n||b<=0)
		return 0;
	v2=f(a+1,n,b);
	if(b>=w[a])
	{
       ans=  max(v[a]+f(a+1,n,b-w[a]), v2);
                        
	}else 
    ans= v2;
	dp[a][b]=ans;
	return ans;
}
int main()
{
 int n;
 int ww,num;
 scanf("%d",&n);
 scanf("%d",&ww);
 for(int i=0;i<n;i++)
 {
	 scanf("%d",&w[i]);
     scanf("%d",&v[i]);
 }
num=f(0,n,ww);
printf("%d",num);
return 0;
}
//解法三动态规划
int dp[20][20];
int dp_f(int n,int ww)
{
//初始化dp数组第一行
	for(int i=0;i<=ww;i++)
		if(i<w[0])
	    	dp[0][i]=0;
		else
             dp[0][i]=v[0];
//通过递推关系递推出其他行
	for(int k=1;k<n;k++)//代表物品编号
		for(int j=0;j<=ww;j++)//代表背包容量
		{
			if(j>=w[k])
			{
				dp[k][j]=max(v[k]+dp[k-1][j-w[k]],dp[k-1][j]);
			}else
			dp[k][j]=dp[k-1][j];
		}
return dp[n-1][ww];
}

int main()
{
 int n;
 int ww,num;
 scanf("%d",&n);
 scanf("%d",&ww);
 for(int i=0;i<n;i++)
 {
	 scanf("%d",&w[i]);
     scanf("%d",&v[i]);
 }
num=dp_f(n,ww);
printf("%d",num);
return 0;
}
