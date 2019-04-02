 //组合问题例从n个数中选m个数的选法
#include <stdio.h>
int f(int  a,int b)
{
	if(a<b)
		return 0;
	if(a==b)
		return 1;
if(a==0||b==0)
   return 1;
	return f(a-1,b-1)+f(a-1,b);

}
int main()
{
  int a,b,n;
  printf("依次输入n和m");
  scanf("%d",&a);
   scanf("%d",&b);
  n=  f(a,b);
  printf("%d ",n);
return 0;
}
