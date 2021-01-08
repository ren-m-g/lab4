package ru.spbstu.telematics.ren.lab4;

 public class IntegralThread implements Runnable{
	Function lock;
	double a;
	double b;
	int sub_n;
	IntegralThread(Function f,double x1,double x2,int n)
	{
		lock=f;
		a=x1;
		b=x2;
		sub_n=n;
	}
	@Override
	public void run() {
		synchronized (lock) {
		//System.out.println("子线程" + Thread.currentThread().getName() + "正在执行");
		lock.sum(lock.integral(a, b, sub_n));
		//System.out.println("子线程" + Thread.currentThread().getName() + "执行完毕");
		//App.latch.countDown();
		}
	}
}