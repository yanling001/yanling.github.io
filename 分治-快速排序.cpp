#include <stdio.h>
int k[100];
int find(int a,int b)
{
int left,right,var;
var=k[a];
left=a;
right=b;
while(k[right]>var&&right>left)
    right--;
k[left]=k[right];
while(k[left]<=var&&right>left)
    left++;
if(right==left)
{
	k[left]=var;
  return left;
}
}
void qsort(int a,int b)
{
	if(b<=a)
		return;
int n;
n=find(a,b);
qsort(a,n-1);
qsort(n+1,b);
}
int main()
{
	int n;
scanf("%d",&n);
for(int i=0;i<n;i++)
  scanf("%d",&k[i]);
qsort(0,n-1);
for(int l=0;l<n;l++)
printf("%d ",k[l]);
return 0;
}
