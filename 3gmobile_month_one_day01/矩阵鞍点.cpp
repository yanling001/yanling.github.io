#include <stdio.h>
 struct node 
 {
   int lift;
   int right;
   int flog;
 };
int main ()
{
	int head;
	int a[100][100];
	struct node mm[100];
int t,m,n,max,min,count,index;
	head=count=0;
scanf("%d",&t);
for(int i=0;i<t;i++)
{
head=count;
	if(i>0)
		printf("\n");
  scanf("%d",&m);
scanf("%d",&n);
 for(int i=0;i<m;i++)
   for(int q=0;q<n;q++)
		scanf("%d",&a[i][q]);

		for(int w=0;w<m;w++)
		{
			min=a[w][0];
			index=0;
			   for(int e=0;e<n;e++)
			     if(min>a[w][e])
				 {
				  min=a[w][e];
				  max=min;
				  index=e;
				 }
					  for(int y=0;y<m;y++)
								  if(max<a[y][index])
								  {
									  max=a[y][index];
									  
								  }
					    if(min==max)
						{
						   mm[count].lift=w;
						   mm[count].right=index;
						   mm[count].flog=0;
						   count++;
						}
								  
		}
		if((count-head)==0)
		{
			mm[count].flog=-1;
			count++;
		}
		else
		{

			mm[count].flog=1;
			count++;
		}
}

for(int u=0;u<count;u++)
{
	
  if(mm[u].flog==1)
  {
	   printf("\n");
	   printf("\n");
	 
  }
  if(mm[u].flog==-1)
  {
	  printf("NO.");
	 printf("\n");
  }
  if(mm[u].flog==0)
  {
   printf("a[%d][%d] ",mm[u].lift+1,mm[u].right+1);
  }
}
return 0;
}
