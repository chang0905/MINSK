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
	public static ArrayList<Double> soluteCubic(double a,double b,double c,double d)
	{
		double[] peak;
		ArrayList<Double>ans=new ArrayList<Double>();
		peak=getPeak(3*a,2*b,c);
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
		ArrayList ans=soluteCubic(1.2,-0.3,90,3);
		for (int i=0;i<ans.size();i++)
			System.out.println(ans.get(i));
	}
}
