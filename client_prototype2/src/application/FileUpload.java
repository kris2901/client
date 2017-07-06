package application;

import java.awt.Desktop;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.Callable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Alert.AlertType;

public class FileUpload
{
	private String path;
	private Client c;
	private ArrayList<Client> cFile;
	private boolean isUploadFailed = false;
	private boolean isRecieveFailed = false;

	public FileUpload()
	{
	}

	/**
	 * SendFile(FilePart file, Callable<Void> funcSucceed, Callable<Void> funcFailed, Object pbProgress) function is use to handle the files sending progress
	 * @param file
	 * @param funcSucceed
	 * @param funcFailed
	 * @param pbProgress
	 */
	public void SendFile(FilePart file, Callable<Void> funcSucceed, Callable<Void> funcFailed, Object pbProgress)
	{
		int SIZE = 10240; // 1K
		byte[] buffer;

		File f = new File(file.fileName);
		String filename = f.getName();
		long total = f.length();
		int parts = (int) (total / SIZE) + 1;
		int read = 0;

		ArrayList<FilePart> fileParts = new ArrayList<>();
		try (FileInputStream ios = new FileInputStream(f))
		{
			for (int i = 1; i <= parts; i++)
			{
				buffer = new byte[SIZE];
				read = ios.read(buffer, 0, SIZE);
				if (read > 0)
				{
					if (read < SIZE)
						buffer = Arrays.copyOf(buffer, read);
					fileParts.add(new FilePart(file.uploaderType, file.date, file.title, file.description, file.userId, file.courseId, file.assignmentName, filename, total, read, buffer, i, file.grade, file.comments));
				}
			}
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
			try
			{
				funcFailed.call();
			}
			catch (Exception e1)
			{
				e1.printStackTrace();
			}
			return;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			try
			{
				funcFailed.call();
			}
			catch (Exception e1)
			{
				e1.printStackTrace();
			}
			return;
		}
		cFile = new ArrayList<>();
		for (FilePart fp : fileParts)
		{
			SendFilePart(fp, funcSucceed, funcFailed, pbProgress);
		}
	}

	/**
	 * SendFilePart(FilePart fp, Callable<Void> funcSucceed, Callable<Void> funcFailed, Object pbProgress) function help the sendFile function with the handling with the sending file progress
	 * @param fp
	 * @param funcSucceed
	 * @param funcFailed
	 * @param pbProgress
	 */
	private void SendFilePart(FilePart fp, Callable<Void> funcSucceed, Callable<Void> funcFailed, Object pbProgress)
	{
		Client c = Client.createClient(new Callable<Void>()
		{
			@Override
			public Void call() throws Exception
			{
				String res = (String) cFile.get(fp.order - 1).getResult();

				if (pbProgress == null)
				{
					if (res.equals("continue"))
					{
						// do nothing
					}
					else if (res.contains("succeed"))
					{
						try
						{
							if (funcSucceed != null)
								funcSucceed.call();
						}
						catch (Exception e)
						{
							e.printStackTrace();
						}
					}
					else
					{
						setUploadFailed(true);
						try
						{
							if (funcFailed != null)
								funcFailed.call();
						}
						catch (Exception e)
						{
							e.printStackTrace();
						}
					}
				}
				else
				{
					Platform.runLater(new Runnable()
					{
						@Override
						public void run()
						{
							if (res.equals("continue"))
							{
								if (pbProgress instanceof ProgressBar)
									((ProgressBar) pbProgress).setProgress(((ProgressBar) pbProgress).getProgress() + ((double) fp.length / fp.totalLength));
								else if (pbProgress instanceof ProgressIndicator)
									((ProgressIndicator) pbProgress).setProgress(((ProgressIndicator) pbProgress).getProgress() + ((double) fp.length / fp.totalLength));
							}
							else if (res.contains("succeed"))
							{
								if (pbProgress instanceof ProgressBar)
									((ProgressBar) pbProgress).setProgress(100);
								else if (pbProgress instanceof ProgressIndicator)
									((ProgressIndicator) pbProgress).setProgress(100);

								new Alert(AlertType.INFORMATION, res, ButtonType.OK).showAndWait();
								try
								{
									if (funcSucceed != null)
										funcSucceed.call();
								}
								catch (Exception e)
								{
									e.printStackTrace();
								}
							}
							else
							{
								setUploadFailed(true);
								new Alert(AlertType.ERROR, res, ButtonType.OK).showAndWait();
								try
								{
									if (funcFailed != null)
										funcFailed.call();
								}
								catch (Exception e)
								{
									e.printStackTrace();
								}
							}
						}
					});
				}
				return null;
			}
		});
		cFile.add(c);
		c.accept("send file", fp);
	}

	/**
	 * RecieveFile(String path, FilePart file, Callable<Void> funcSucceed, Callable<Void> funcFailed, Object pbProgress) function is use to handle the files receiving progress
	 * @param path
	 * @param file
	 * @param funcSucceed
	 * @param funcFailed
	 * @param pbProgress
	 */
	public void RecieveFile(String path, FilePart file, Callable<Void> funcSucceed, Callable<Void> funcFailed, Object pbProgress)
	{
		this.path = path;
		c = Client.createClient(new Callable<Void>()
		{
			@Override
			public Void call() throws Exception
			{
				Object res = c.getResult();
				if (((String) res).equals("{}"))
				{
					setRecieveFailed(true);
					if (pbProgress != null)
					{
						Platform.runLater(new Runnable()
						{
							@Override
							public void run()
							{
								new Alert(AlertType.ERROR, "The student don't have assaigment for the specified course.", ButtonType.OK).showAndWait();
							}
						});
					}
					try
					{
						if (funcFailed != null)
							funcFailed.call();
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
					return null;
				}
				try
				{
					Gson gson = new Gson();
					FilePart fp = ((FilePart) gson.fromJson((String) res, new TypeToken<FilePart>()
					{
					}.getType()));

					if (fp.totalLength == null)
					{
						setRecieveFailed(true);
						if (pbProgress != null)
						{
							Platform.runLater(new Runnable()
							{
								@Override
								public void run()
								{
									new Alert(AlertType.ERROR, "The file " + fp.fileName + " doesn't exist on server.", ButtonType.OK).showAndWait();
								}
							});
						}

						try

						{
							if (funcFailed != null)
								funcFailed.call();
						}
						catch (Exception e)
						{
							e.printStackTrace();
						}
						return null;

					}

					int parts = (int) (fp.totalLength / 10240) + 1;
					cFile = new ArrayList<>();
					for (int i = 0; i < parts; i++)
					{
						ReadFilePart(new FilePart(null, null, null, null, fp.userId, fp.courseId, fp.assignmentName, fp.fileName, fp.totalLength, null, null, i + 1, null, null), funcSucceed, funcFailed, pbProgress);
					}
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
				return null;
			}
		});
		c.accept("receive file header", file);

	}

	/**
	 * ReadFilePart(FilePart fp, Callable<Void> funcSucceed, Callable<Void> funcFailed, Object pbProgress) function help the RecieveFile function with the handling with the receiving file progress
	 * @param fp
	 * @param funcSucceed
	 * @param funcFailed
	 * @param pbProgress
	 */
	private void ReadFilePart(FilePart fp, Callable<Void> funcSucceed, Callable<Void> funcFailed, Object pbProgress)
	{
		Client c = Client.createClient(new Callable<Void>()
		{
			@Override
			public Void call() throws Exception
			{
				String res = (String) cFile.get(fp.order - 1).getResult();
				Gson gson = new Gson();
				System.out.println(res);
				FilePart pr = ((FilePart) gson.fromJson((String) res, new TypeToken<FilePart>()
				{
				}.getType()));

				if (res.contains("failed"))
				{
					if (pbProgress != null)
					{
						setRecieveFailed(true);
						Platform.runLater(new Runnable()
						{
							@Override
							public void run()
							{
								new Alert(AlertType.ERROR, res, ButtonType.OK).showAndWait();

							}
						});
					}

					try
					{
						if (funcFailed != null)
							funcFailed.call();
					}
					catch (Exception e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else // succeeded
				{
					if (pbProgress != null)
					{
						Platform.runLater(new Runnable()
						{
							@Override
							public void run()
							{
								if (pbProgress instanceof ProgressBar)
									((ProgressBar) pbProgress).setProgress(((ProgressBar) pbProgress).getProgress() + ((double) pr.length / pr.totalLength));
								else if (pbProgress instanceof ProgressIndicator)
									((ProgressIndicator) pbProgress).setProgress(((ProgressIndicator) pbProgress).getProgress() + ((double) pr.length / pr.totalLength));

							}
						});
					}

					if (isRecieveFailed)
						return null;

					File dir1 = new File("Temp");
					if (!dir1.exists())
					{
						if (!dir1.mkdir())
						{
							setRecieveFailed(true);
							return null;
						}
					}
					File f = new File("Temp/" + getFileNameByPath(pr.fileName) + ".part" + fp.order);
					if (f.exists())
						f.delete();
					try (FileOutputStream fos = new FileOutputStream(f))
					{
						fos.write(pr.buffer, 0, pr.length);
					}
					catch (FileNotFoundException e1)
					{
						e1.printStackTrace();
						setRecieveFailed(true);
						return null;
					}
					catch (IOException e1)
					{
						e1.printStackTrace();
						setRecieveFailed(true);
						return null;
					}

					if (isGetAllFiles("Temp", getFileNameByPath(pr.fileName), pr.totalLength))
					{
						if (!combineFiles("Temp", getFileNameByPath(pr.fileName), pr.totalLength, 10240))
						{
							setRecieveFailed(true);
							if (pbProgress != null)
							{
								Platform.runLater(new Runnable()
								{
									@Override
									public void run()
									{
										new Alert(AlertType.ERROR, res, ButtonType.OK);
									}
								});
							}

							try
							{
								if (funcFailed != null)
									funcFailed.call();
							}
							catch (Exception e)
							{
								e.printStackTrace();
							}
						}
						else
						{
							if (pbProgress != null)
							{
								Platform.runLater(new Runnable()
								{
									@Override
									public void run()
									{
										new Alert(AlertType.INFORMATION, "File downloaded successfully!", ButtonType.OK);
									}
								});
							}
							try
							{
								if (funcSucceed != null)
									funcSucceed.call();
							}
							catch (Exception e)
							{
								e.printStackTrace();
							}

							File newFile = new File(path + "/" + getFileNameByPath(pr.fileName));
							if (newFile != null)
							{
								try
								{
									Desktop.getDesktop().open(newFile);
								}
								catch (IOException e)
								{
									e.printStackTrace();
								}
							}
						}
					}
				}
				return null;
			}
		});
		cFile.add(c);
		c.accept("receive file", fp);

	}

	/**
	 * combineFiles(String dir, String fileName, long totalLength, int size) function merge the parts into one (the original)
	 * @param dir
	 * @param fileName
	 * @param totalLength
	 * @param size
	 * @return
	 */
	private boolean combineFiles(String dir, String fileName, long totalLength, int size)
	{
		byte[] buffer = new byte[size];
		File directory = new File(dir);
		File newFile = new File(path + "/" + fileName);
		FileFilter ff = new FileFilter()
		{
			@Override
			public boolean accept(File f)
			{
				return f.isFile() && f.getName().substring(0, f.getName().length() - 1).contains(fileName + ".part");
			}
		};

		if (newFile.exists())
			newFile.delete();
		try (FileOutputStream fos = new FileOutputStream(newFile, true))
		{
			File[] files = directory.listFiles(ff);
			Arrays.sort(files, new Comparator<File>()
			{
				@Override
				public int compare(File f1, File f2)
				{
					if (f1 == null || f2 == null)
						return -1;

					String str1 = f1.getName();
					str1 = str1.substring(str1.indexOf(".part") + 5);
					int num1 = Integer.parseInt(str1);

					String str2 = f2.getName();
					str2 = str2.substring(str2.indexOf(".part") + 5);
					int num2 = Integer.parseInt(str2);

					if (num1 > num2)
						return 1;
					else if (num1 == num2)
						return 0;
					return -1;
				}

			});
			for (File fi : files)
			{
				try (FileInputStream fis = new FileInputStream(fi))
				{
					int read = fis.read(buffer);
					fos.write(buffer, 0, read);
				}
				catch (Exception e1)
				{
					e1.printStackTrace();
					fos.close();
					return false;
				}
				fi.delete();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * isGetAllFiles(String dir, String fileName, long totalLength) function check if he gets all the files of the original files
	 * @param dir
	 * @param fileName
	 * @param totalLength
	 * @return
	 */
	private boolean isGetAllFiles(String dir, String fileName, long totalLength)
	{
		try
		{
			File directory = new File(dir);
			FileFilter ff = new FileFilter()
			{
				@Override
				public boolean accept(File f)
				{
					return f.isFile() && f.getName().contains(fileName + ".part");
				}
			};

			long currentLength = 0;
			for (File f : directory.listFiles(ff))
			{
				currentLength += f.length();
			}

			return (currentLength == totalLength);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * deleteTemporaryFiles() function delete the temporary files when the original is recieved
	 */
	private void deleteTemporaryFiles()
	{
		File dir1 = new File("Temp");
		FileFilter ff = new FileFilter()
		{
			@Override
			public boolean accept(File f)
			{
				return f.isFile() && f.getName().contains(".part");
			}
		};
		if (!dir1.exists())
			return;

		for (File f : dir1.listFiles(ff))
			f.delete();
	}

	/**
	 * getFileNameByPath(String path) function use to get the file name
	 * @param path
	 * @return file name
	 */
	private String getFileNameByPath(String path)
	{
		String[] arr = path.split("/");
		return arr[arr.length - 1];
	}

	/**
	 * isUploadFailed() function use to determine if the file was upload successfully
	 * @return true if it was upload successfully, false when it doesn't.
	 */
	public boolean isUploadFailed()
	{
		return isUploadFailed;
	}

	/**setUploadFailed(boolean isUploadFailed) if the upload has been done successfully it will set the value and delete temporary
	 * 
	 * @param isUploadFailed
	 */
	public void setUploadFailed(boolean isUploadFailed)
	{
		this.isUploadFailed = isUploadFailed;
		if (isUploadFailed)
			deleteTemporaryFiles();
	}

	/**
	 * isRecieveFailed() check if the file was reached to destination 
	 * @return true if it succeeded, false otherwise.
	 */
	public boolean isRecieveFailed()
	{
		return isRecieveFailed;
	}

	/**
	 * setRecieveFailed(boolean isRecieveFailed) set the answer from isRecieveFailed()
	 * @param isRecieveFailed
	 */
	public void setRecieveFailed(boolean isRecieveFailed)
	{
		this.isRecieveFailed = isRecieveFailed;
	}
}
