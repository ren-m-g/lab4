package ru.spbstu.telematics.ren.lab4;

public class App {
	public static void main( String[] args )
	{
		
//		System.out.println("Input initial function 1");
//		Scanner sc=new Scanner(System.in);
//		String str1=new String();
//		str1=sc.next();
//
//		System.out.println("Input initial function 2");
//		sc=new Scanner(System.in);
//		String str2=new String();
//		str2=sc.next();
//		
//		System.out.println("Input initial value x");
//		sc=new Scanner(System.in);
//		double x=sc.nextDouble();
//		System.out.println("Input initial value t");
//		sc=new Scanner(System.in);
//		double t=sc.nextDouble();
//		System.out.println(x+t);
//		
		String str1,str2;
		str1="sin(x)+cos(x)";
		str2="x^2";
		double x=1.;
		double t=2.;
		int n=10000;	
		
		Function han=new Function(x,t,str1,str2);
		double a1=han.getx1();
		double b1=han.getx2();
		han.sum(han.integral(a1,b1, n));
		
		Function han2=new Function(x,t,str1,str2);
		int nub_thread=4;
		double a=han2.getx1();
		double b=han2.getx2();
		double cha=(b-a)/nub_thread;
		int sub_n=n/nub_thread;
		Thread[]arr=new Thread[nub_thread];
		for(int i=0;i<nub_thread;i++)
		{
			arr[i]=new Thread(new IntegralThread(han2,a+i*cha,a+(i+1)*cha,sub_n));
		    arr[i].start();
		}
		for(int i=0;i<nub_thread;i++)
		{
			try {
				arr[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("Правильный результат:"+han.getResult());
		System.out.println("Результаты в многопоточности:"+ han2.getResult());
		
		
		Function han3=new Function(x,t,str1,str2);
		nub_thread=2;
		double a2=han2.getx1();
		double b2=han2.getx2();
		double cha2=(b2-a2)/nub_thread;
		int sub_n2=n/nub_thread;
		int pa=200;
		long time=0;
		long startTime,endTime;
		Thread[]arr2=new Thread[nub_thread];
		while(pa!=0)
		{
			startTime=System.currentTimeMillis(); 
			
			for(int i=0;i<nub_thread;i++)
			{
				arr2[i]=new Thread(new IntegralThread(han3,a2+i*cha2,a2+(i+1)*cha2,sub_n2));
			    arr2[i].start();
			}
			for(int i=0;i<nub_thread;i++)
			{
				try {
					arr2[i].join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			endTime=System.currentTimeMillis();
			
			//System.out.println("Время выпонения программы："+(endTime-startTime)+"ms");
			time+=endTime-startTime;
			pa--;
		}
		time/=200;
		System.out.println("Время выпонения программы：" + time + "ms");

		
		}

}