//排列问题例abcdef共有多少种组合方式
#include <stdio.h>
#include <string.h>

void f(char a[15],int t)
{
char temp;
if(t==strlen(a)-1)
   printf("%s\n",a);
 for(int i=t;i<strlen(a);i++)
 {
   temp=a[t]; a[t]=a[i]; a[i]=temp;
   f(a,t+1);
   temp=a[t]; a[t]=a[i]; a[i]=temp;//回溯保证基准不变
 }

}
int main()
{
	char a[15];
	scanf("%s",a);
    f(a,0);
return 0;
}


