#include <stdio.h>
/*
//动态规划
int main()
{
	int a[20]={0};
	int dp[20]={0};
int n,temp;
scanf("%d",&n);
dp[0]=1;
int max=0;
for(int i=0;i<n;i++)
{
   scanf("%d",&a[i]);
   for(int j=0;j<i;j++)
   {
     if(a[j]>a[i])
	 {
	   temp=dp[j]+1;
	   if(temp>dp[i])
	   {
	     dp[i]=temp;
	   }	 
	 }
   
   }
   if(max<dp[i])
	   max=dp[i];
}
printf("%d",max);
	return 0;
}
*/
//递归解

int n;
int a[20];

int max(int a,int b)
{
if(a>b)
return a;
else
return b;
}
int  f(int s,int i,int t)
{
if(i==n)
return s;


if(t>a[i])
  return max(f(s+1,i+1,a[i]),f(s,i+1,t));
if(a[i]>t)
{
return f(s,i+1,t);
}
  
}

int main()
{
	int ans;
scanf("%d",&n);
for(int i=0;i<n;i++)
   scanf("%d",&a[i]);

ans=f(0,0,1000000);
printf("%d",ans);
return 0;
}








