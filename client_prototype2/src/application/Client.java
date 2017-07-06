package application;

import java.io.IOException;
import java.util.concurrent.Callable;

import com.google.gson.Gson;
import OCSF.AbstractClient;

public class Client extends AbstractClient
{
	private Object result = null;

	public Client(String ip, int port)
	{
		super(ip, port);
		try
		{
			openConnection();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static Client createClient(Callable<Void> func)
	{
		try
		{
			Client cli = new Client(Main.ip, Integer.parseInt(Main.port))
			{
				protected void handleMessageFromServer(Object arg0)
				{
					super.handleMessageFromServer(arg0);
					try
					{
						if (func != null)
							func.call();
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
			};
			return cli;
		}
		catch (Exception ex)
		{ /** check for errors */
			System.out.println(ex.getMessage());
		}
		return null;
	}

	public Object getResult()
	{
		return result;
	}

	@Override
	protected void handleMessageFromServer(Object arg0)
	{
		/** we get message by String from the server */
		result = arg0;
		println("Result = " + arg0.toString());

		try
		{
			closeConnection(); /** close the connection to the server. */
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void println(String msg)
	{
		System.out.println(msg);
	}

	public void quit()
	{
		try
		{
			closeConnection();
		}
		catch (IOException e)
		{
		}
		System.exit(0);
	}

	public void accept(String action, Object obj)
	{
		try
		{
			result = null;
			String jsonToSend = getJson(obj, action);
			sendToServer(jsonToSend); /** send the json to server **/

		}
		catch (Exception ex)
		{
			println(ex.getMessage()); /** check for errors **/
		}
	}

	public String getJson(Object obj, String action)
	{
		Gson gson = new Gson(); // Google json for deliver JSON for server (we
								// can deliver object but there are
								// Serialization issues) including issues with
								// inheritance
		String gsonString = gson.toJson(obj).substring(1);
		String json = "JSON: { \"type\":\"" + obj.getClass().getSimpleName().toLowerCase() + "\", \"action\":\"" + action + "\", " + gsonString; // JSON format: { type:"table_name", fields in format key:"value" }
		if (json.endsWith(", }"))
			json = json.substring(0, json.length() - 3) + "}";
		return json;
	}
}
