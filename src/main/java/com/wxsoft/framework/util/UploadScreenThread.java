package com.wxsoft.framework.util;

public class UploadScreenThread implements Runnable {
	private String[] heightParameters;
	private String[] widthParameters;
	private String filePath;
	private String lastFileName;

	public UploadScreenThread(String[] heightParameters,
			String[] widthParameters, String filePath, String lastFileName) {
		this.heightParameters = heightParameters;
		this.widthParameters = widthParameters;
		this.filePath = filePath;
		this.lastFileName = lastFileName;
	}

	@Override
	public void run() {

		CompressPicDemo mypic = new CompressPicDemo();

		for (int i = 0; i < heightParameters.length; i++) {
			try {
				int width = Integer.parseInt(widthParameters[i]);
				int height = Integer.parseInt(heightParameters[i]);

				mypic.compressPic(filePath, filePath, lastFileName, lastFileName, width, height, true);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
