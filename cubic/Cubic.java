package cubic;

import java.util.ArrayList;

public class Cubic {
	public static double MAX=1000000;
	public static double MIN=-1000000;
	public static Double binarySearch(double minv,double maxv,double a,double b,double c,double d)
	{
		double l=minv;
		double r=maxv;
		if (cubicCount(minv,a,b,c,d)*cubicCount(maxv,a,b,c,d)>0)
		return null;
		else
		{
			while (r-l>0.000000000001)
			{
				double m=(r+l)/2;
				if (cubicCount(m,a,b,c,d)*cubicCount(l,a,b,c,d)>0)
					l=m;
				else
					r=m;
			}
			return l;
		}
	}
	public static boolean isPrime(int s)
	{
		for (int i=2;i*i<=s;i++)
		{
			if (s%i==0)
				return false;
		}
		return true;
	}
	public static ArrayList<Double> soluteCubic(int a,int b,int c,int d)
	{
		int sum=a+b+c+d;
		if (sum<0)
			sum=-sum;
		double[] peak;
		ArrayList<Double>ans=new ArrayList<Double>();
		peak=getPeak(3*a,2*b,c);
		if (sum%2==0||isPrime(sum))
		{
			System.out.println("sum of a,b,c and d is not odd composite");
			return ans;
		}
		if (a==0)
		{
			System.out.println("a shoule not be 0");
			return ans;
		}
		if (peak==null)
		ans.add(binarySearch(MIN,MAX,a,b,c,d));
		else
		{
			if (cubicCount(peak[0],a,b,c,d)==0)
			{
				ans.add(peak[0]);
				Double r = null;
				r=binarySearch(peak[1],MAX,a,b,c,r);
				if (r!=null)
				ans.add(r);
			}
			else 
			{
				Double r;
				r=binarySearch(MIN,peak[0],a,b,c,d);
				if (r!=null)
				ans.add(r);
				if (cubicCount(peak[1],a,b,c,d)==0)
				ans.add(peak[1]);
				else
				{
					r=binarySearch(peak[0],peak[1],a,b,c,d);
					if (r!=null)
					ans.add(r);
					r=binarySearch(peak[1],MAX,a,b,c,d);
					if (r!=null)
					ans.add(r);
				}
			}
			
		}
		return ans;
		
	}
	
	public static double cubicCount(double x,double a,double b,double c,double d)
	{
		return ((a*x+b)*x+c)*x+d;
	}
	
	public static double[]getPeak(double a,double b,double c)
	{
		if (delta(a,b,c)<=0)
		return null;
		else
		return quadraticSolute(a,b,c);
		
	}
	
	public static double delta(double a,double b,double c)
	{
		return b*b-4*a*c;
	}
	
	public static double[] quadraticSolute(double a,double b,double c)
	{
		double[] ret=new double[2];
		ret[0]=(-b-sqrt(delta(a,b,c)))/2/a;
		ret[1]=(-b+sqrt(delta(a,b,c)))/2/a;
		if(a<0)
		{
			double q=ret[0];
			ret[0]=ret[1];
			ret[1]=q;
		}
		return ret;
	}
	
	public static double sqrt(double n)
	{
		double l;
		double r;
		l=0;
		r=MAX;
		while (r-l>0.000000000001)
		{
			double m=(l+r)/2;
			if (m*m<n)
			l=m;
			else
			r=m;
		}
		return l;
	}
	
	public static void main(String[] args)
	{
		ArrayList ans=soluteCubic(1,3,90,11);
		for (int i=0;i<ans.size();i++)
			System.out.println(ans.get(i));
	}
}
