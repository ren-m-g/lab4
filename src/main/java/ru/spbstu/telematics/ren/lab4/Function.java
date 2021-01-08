package ru.spbstu.telematics.ren.lab4;
import org.nfunk.jep.JEP;

public class Function{
	static double a=330.;
	double x;
	double t;
	String str1;
	String str2;
	double result;
	
	JEP jep1;
	JEP jep2;
	
	Function(double x,double t,String s1,String s2){
		this.x=x;
		this.t=t;
		this.str1=s1;
		this.str2=s2;
		
		
		jep1 = new JEP();
		jep2 = new JEP();
        jep1.addStandardFunctions();
        jep1.addStandardConstants();
        jep2.addStandardFunctions();
        jep2.addStandardConstants();
        
        this.result=sec1();
	}
	double sec1()
	{
		double d1=0,d2=0;
		jep1.addVariable("x", x+a*t);
		jep1.parseExpression(str1);
		d1=jep1.getValue();

		jep1.addVariable("x", x-a*t);
		jep1.parseExpression(str1);
		d2=jep1.getValue();
		return d1+d2;
	}
	
	double integral(double x1,double x2,int n)
	{
        double d=0;
		for(int i=0;i<n;i++)
		{
			jep2.addVariable("x", x1+i*(x2-x1)/n);
			jep2.parseExpression(str2);
			d+=((x2-x1)/n)*jep2.getValue();
		}
		return d;
	}
	double getResult()
	{
		return result;
	}
	void setStr1(String s)
	{
		str1=s;
	}
	void setStr2(String s)
	{
		str2=s;
	}
	void setinit(double x,double t)
	{
		this.x=x;
		this.t=t;
	}
	double getx1()
	{
		return x-a*t;
	}
	double getx2()
	{
		return x+a*t;
	}
	void sum(double sec2)
	{
		this.result=this.result+sec2;

	}
}