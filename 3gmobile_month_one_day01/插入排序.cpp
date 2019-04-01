#include <stdio.h>

void main()
{
  int m,n,t,count;
  int a[1000];
  count=0;
  scanf("%d",&m);
  for(int i=0;i<m;i++)
  {
	  
     scanf("%d",&n);
     for(int j=0;j<=n;j++)
	 {
	    scanf("%d",&a[j]);
	 }
	 for(int f=0;f<n-1;f++)
	 {
	    for(int l=f+1;l>0;l--)
		{
		count++;
			if(a[l]<a[f])
			{
			t=a[l];
			a[l]=a[f];
			a[f]=t;
			}else
				break;
		
		}
	 }
	 printf("%d\n",count);
		 for(int s=0;s<n;s++)
			 printf("%d ",a[s]);
	
  }
  
}


