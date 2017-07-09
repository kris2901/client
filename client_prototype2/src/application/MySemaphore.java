package application;

import java.util.concurrent.Semaphore;

public class MySemaphore extends Semaphore
{
	private Object result;

	public MySemaphore(int num)
	{
		super(num);
	}

	public Object getResult()
	{
		return result;
	}

	public void setResult(Object result)
	{
		this.result = result;
	}
}
