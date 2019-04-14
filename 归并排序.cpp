#include <stdio.h>
int j[10];
int k[10];


void merge(int l,int a,int b,int r)
{
	 
  int left=l;
  int count=l;
  int right=b;

  while(left<=a&&right<=r)
  {

  if(j[left]>j[right])
  {
	   
	    k[count]=j[right];
		count++;
		right++;
  }
  else
  {
   k[count]=j[left];
   count++;
   left++;
  }

  }
 while(left<=a)
	{
		k[count]=j[left];
		count++;
		left++;
	}
while(right<=r)
	{
		   
		k[count]=j[right];
		count++;
		right++;
	}

}


void msort(int a,int b)
{
	if(b<=a)
		return;
int mid=(a+b)/2;
msort(a,mid);
msort(mid+1,b);
merge(a,mid,mid+1,b);
}


int main()
{
	int n;
scanf("%d",&n);
for(int i=0;i<n;i++)
  scanf("%d",&j[i]);
msort(0,n-1);
for(int l=0;l<n;l++)
printf("%d ",k[l]);
return 0;
}