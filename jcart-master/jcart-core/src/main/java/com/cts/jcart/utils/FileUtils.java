/*
 * Copyright(C) 2008 D-CIRCLE, INC. All rights reserved.
 *
 * (1)このソフトウェアは、ディサークル株式会社に帰属する機密情報
 *    であり開示を固く禁じます。
 * (2)この情報を使用するには、ディサークル株式会社とのライセンス
 *    契約が必要となります。
 *
 * This software is the confidential and proprietary information
 * of D-CIRCLE, INC. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms
 * of the license agreement you entered into with D-CIRCLE.
 */

package com.cts.jcart.utils;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * ファイル処理用
 * @author duy
 */
public class FileUtils {

//	/* ファイルエンドポイント */
//	@SuppressWarnings("unused")
//    private static final int EOF = -1;

	/* サーバーログ */
	private static final Log log = LogFactory.getLog(FileUtils.class);

	/* システムテンプフォルダパス */
	public static final String SYSTEM_TEMP_DIR = System.getProperty("java.io.tmpdir");

	/* パス区切文字 */
	public static final String PATH_SEPERATOR = System.getProperties().getProperty("file.separator");

//	/**
//	 * アンジップ行い
//	 *
//	 * @param ジップファイルパス
//	 * @param アンジップフォルダパス
//	 * @return アンジップしたロケーションパス
//	 */
//	@SuppressWarnings("unchecked")
//	public static String unzip(String zipPath, String unzipFolderPath) {
//		// 戻り値
//		String unzipPath = "";
//		if (StringUtils.nullOrBlank(unzipFolderPath)) {
//			unzipFolderPath = SYSTEM_TEMP_DIR + "/";
//		} else {
//		    if (!unzipFolderPath.endsWith("/")) {
//		        unzipFolderPath = unzipFolderPath + "/";
//		    }
//		}
//    	try {
//    		ZipFile zipFile = new ZipFile(zipPath, "windows-31j");
//    		// ジップエントリ
//    		Enumeration<ZipEntry> zipEnum = (Enumeration<ZipEntry>)zipFile.getEntries();
//    		// 最初フラグ
//    		boolean firstFlag = true;
//    		// 読む
//    		while (zipEnum.hasMoreElements()) {
//    			ZipEntry zipEntry = (ZipEntry)zipEnum.nextElement();
//    			// アンジップロケーションパス
//    			if (firstFlag) {
//    				String zipName = zipEntry.getName().replace("/", RRDAppImporter.PATH_SEPERATOR);
//    				unzipPath = unzipFolderPath +
//    							zipName.substring(0, zipName.indexOf(RRDAppImporter.PATH_SEPERATOR) + 1);
//    				firstFlag = false;
//    			}
//    			// アンジップ
//    			saveEntry(zipFile, zipEntry, unzipFolderPath);
//    		}
//    		if(zipFile != null) {
//    			zipFile.close();
//    			zipFile = null;
//    		}
//        } catch (FileNotFoundException ex) {
//        	unzipPath = "";
//            log.error("FileNotFoundException:", ex);
//        } catch (ZipException ex) {
//        	unzipPath = "";
//        	log.error("ZipException:", ex);
//        } catch (IOException ex) {
//        	unzipPath = "";
//        	log.error("IOException:", ex);
//        } catch (IndexOutOfBoundsException ex) {
//        	unzipPath = "";
//        	log.error("IndexOutOfBoundsException:", ex);
//        }
//        return unzipPath;
//	}

//	/**
//	 * アンジップ行い
//	 *
//	 * @param ジップファイルパス
//	 * @param アンジップフォルダパス
//	 * @return アンジップしたロケーションパス
//	 */
//	@SuppressWarnings("unchecked")
//	public static void xdbUnzip(String zipPath, String unzipFolderPath,String encode) {
//		if (StringUtils.nullOrBlank(unzipFolderPath)) {
//			unzipFolderPath = SYSTEM_TEMP_DIR + "/";
//		} else {
//		    if (!unzipFolderPath.endsWith("/")) {
//		        unzipFolderPath = unzipFolderPath + "/";
//		    }
//		}
//    	try {
//    		String encoding = "windows-31j";
//    		if(!StringUtils.isEmpty(encoding)){
//    			encoding = encode;
//    		}
//    		ZipFile zipFile = new ZipFile(zipPath,encoding);
//    		// ジップエントリ
//    		Enumeration<ZipEntry> zipEnum = (Enumeration<ZipEntry>)zipFile.getEntries();
//    		// 読む
//    		while (zipEnum.hasMoreElements()) {
//    			ZipEntry zipEntry = (ZipEntry)zipEnum.nextElement();
//    			// アンジップ
//    			saveEntry(zipFile, zipEntry, unzipFolderPath);
//    		}
//    		if(zipFile != null) {
//    			zipFile.close();
//    			zipFile = null;
//    		}
//        } catch (FileNotFoundException ex) {
//            log.error("FileNotFoundException:", ex);
//        } catch (ZipException ex) {
//        	log.error("ZipException:", ex);
//        } catch (IOException ex) {
//        	log.error("IOException:", ex);
//        } catch (IndexOutOfBoundsException ex) {
//        	log.error("IndexOutOfBoundsException:", ex);
//        }
//
//	}
	
//	/**
//	 * アンジップ処理
//	 *
//	 * @param ＺＩＰファイル
//	 * @param ＺＩＰエントリ
//	 * @param ベースパス
//	 *
//	 * @throws ZipException
//	 * @throws IOException
//	 */
//	public static void saveEntry(ZipFile zipFile, ZipEntry zipEntry, String unzipFolderPath)
//		throws ZipException,IOException {
//		try {
//            File file = new File(unzipFolderPath + zipEntry.getName());
//            // フォルダの場合
//            if (zipEntry.isDirectory()) {
//                file.mkdirs();
//            } else {
//                // 上位フォルダを作る
//                File dir = new File(file.getParent());
//                dir.mkdirs();
//                InputStream is = null;
//                BufferedInputStream bis = null;
//                FileOutputStream fos = null;
//                BufferedOutputStream bos = null;
//                byte[] buf = null;
//                try {
//	                // ファイルを書く
//	                int count = -1;
//	                is = zipFile.getInputStream(zipEntry);
//	                bis = new BufferedInputStream(is);
//	                fos = new FileOutputStream(file);
//	                bos = new BufferedOutputStream(fos);
//	                buf = new byte[8192];
//	                while((count = bis.read(buf)) > 0) {
//	                     bos.write(buf, 0, count);
//	                }
//                } catch (IOException ioe) {
//                	throw ioe;
//                } finally {
//                	if (null != bis) {
//                		try {
//                			bis.close();
//                		} catch (IOException ioe) { // NOP
//                		}
//                	}
//                	if (null != is) {
//                		try {
//                			is.close();
//                		} catch (IOException ioe) { // NOP
//                		}
//                	}
//                	if(null != bos) {
//                		try {
//                			bos.close();
//                		} catch (IOException ioe) {
//                			//NOP
//                		}
//                	}
//                	if (null != fos) {
//                		try {
//                			fos.close();
//                		} catch (IOException ioe) { // NOP
//                		}
//                	}
//                }
//            }
//		} catch (ZipException e){
//			throw e;
//       	} catch(IOException e){
//       		throw e;
//       	}
//	}

	/**
	 * フォルダと下位のファイル・フォルダを削除する
	 *
	 * @param 削除フォルダパス
	 * @return true : 削除に成功
	 * 		   false: 削除に失敗
	 */
	public static boolean deleteFileFolder(String parentPath) {
		if (StringUtils.nullOrBlank(parentPath)) {
			return false;
		}
		try {
			File folder = new File(parentPath);
			// 存在しない場合
			if (!folder.exists()) {
				return false;
			}
			// ファイルの場合
			if (folder.isFile()) {
				folder.delete();
				return true;
			}
			// フォルダの場合
			File[] children = folder.listFiles();
			for (int i = 0; i < children.length; i++) {
				// 再帰削除
				deleteFileFolder(children[i].getAbsolutePath());
			}
			folder.delete();
		} catch (SecurityException ex) {
			// ファイル・フォルダ削除できない
			log.error("SecurityException:", ex);
			return false;
		}
		return true;
	}

	/**
	 * 最小ファイル・フォルダ名を取る
	 * 		C:\Folder\file.doc → file.doc
	 * 		D:\Folder\Sub\ →　Sub
	 * 		E:\Folder\Sub → Folder
	 * 		…
	 *
	 * @param ファイルパス
	 * @param 区切文字
	 */
	public static String extractPathFromLastSeparator(String path, String separator) {
		if (StringUtils.nullOrBlank(path)) {
			return null;
		}
		if (separator == null) {
			separator = PATH_SEPERATOR;
		}
		try {
    		int lastSeparator = path.lastIndexOf(separator);
    		if (lastSeparator > 0) {
    			return path.substring(lastSeparator + 1);
    		}
    	} catch (IndexOutOfBoundsException ex) {
    		return null;
    	}
    	return path;
	}

	/**
	 * 最小ファイル・フォルダ名を取る
	 * 		C:\Folder\file.doc → C:\Folder
	 * 		D:\Folder\Sub\ →　D:\Folder\Sub
	 * 		E:\Folder\Sub → E:\Folder
	 * 		…
	 *
	 * @param ファイルパス
	 * @param 区切文字
	 */
	public static String extractPathToLastSeparator(String path, String separator) {
		if (StringUtils.nullOrBlank(path)) {
			return null;
		}
		if (separator == null) {
			separator = PATH_SEPERATOR;
		}
		try {
    		int lastSeparator = path.lastIndexOf(separator);
    		if (lastSeparator > 0) {
    			return path.substring(0, lastSeparator + 1);
    		}
    	} catch (IndexOutOfBoundsException ex) {
    		return null;
    	}
    	return path;
	}

	/**
	 *
	 * @param srPath
	 * @param dtPath
	 */
	public static void copyFile(String srFile, String dtFile){
	    try{
	        File f1 = new File(srFile);
	        File f2 = new File(dtFile);
	        InputStream in = new FileInputStream(f1);

	        OutputStream out = new FileOutputStream(f2);

	        byte[] buf = new byte[1024];
	        int len;
	        while ((len = in.read(buf)) > 0){
	          out.write(buf, 0, len);
	        }
	        in.close();
	        out.close();
	      }
	      catch(FileNotFoundException ex){
	        return;
	      }
	      catch(IOException e){
	        return;
	      }
	}
	static public String getContents(File aFile) {
	    //...checks on aFile are elided
	    StringBuilder contents = new StringBuilder();

	    try {
	      //use buffering, reading one line at a time
	      //FileReader always assumes default encoding is OK!
	      BufferedReader input =  new BufferedReader(new FileReader(aFile));
	      try {
	        String line = null; //not declared within while loop
	        /*
	        * readLine is a bit quirky :
	        * it returns the content of a line MINUS the newline.
	        * it returns null only for the END of the stream.
	        * it returns an empty String if two newlines appear in a row.
	        */
	        while (( line = input.readLine()) != null){
	          contents.append(line);
	          contents.append(PATH_SEPERATOR);
	        }
	      }
	      finally {
	        input.close();
	      }
	    }
	    catch (IOException ex){
	      ex.printStackTrace();
	    }

	    return contents.toString();
	  }
	
//	/**
//	 * アンジップ行い
//	 *
//	 * @param ジップファイルパス
//	 * @param アンジップフォルダパス
//	 * @return アンジップしたロケーションパス
//	 * @throws IOException
//	 */
//	@SuppressWarnings("unchecked")
//	public static void xdbUnzip2(String zipPath, String unzipFolderPath) throws IOException {
//		if (StringUtils.nullOrBlank(unzipFolderPath)) {
//			unzipFolderPath = SYSTEM_TEMP_DIR + "/";
//		} else {
//		    if (!unzipFolderPath.endsWith("/")) {
//		        unzipFolderPath = unzipFolderPath + "/";
//		    }
//		}
//		ZipFile zipFile = new ZipFile(zipPath, "windows-31j");
//		// ジップエントリ
//		Enumeration<ZipEntry> zipEnum = (Enumeration<ZipEntry>)zipFile.getEntries();
//		// 読む
//		while (zipEnum.hasMoreElements()) {
//			ZipEntry zipEntry = (ZipEntry)zipEnum.nextElement();
//			// アンジップ
//			saveEntry(zipFile, zipEntry, unzipFolderPath);
//		}
//		if(zipFile != null) {
//			zipFile.close();
//			zipFile = null;
//		}
//	}
	
//	/**
//	 * アンジップ行い
//	 *
//	 * @param ジップファイルパス
//	 * @param アンジップフォルダパス
//	 * @return アンジップしたロケーションパス
//	 */
//	@SuppressWarnings("unchecked")
//	public static String unzipForImportCSVWebDB(String zipPath, String unzipFolderPath,String originalName,String encode) {
//		String encoding = "windows-31j";
//		if(!StringUtils.isEmpty(encode)){
//			encoding = encode;
//		}
//		// 戻り値
//		String unzipPath = "";
//		if (StringUtils.nullOrBlank(unzipFolderPath)) {
//			unzipFolderPath = SYSTEM_TEMP_DIR + "/"
//					+ originalName.substring(0, originalName.length() - 4)
//					+ "/";
//		} else {
//		    if (!unzipFolderPath.endsWith("/")) {
//		        unzipFolderPath = unzipFolderPath + "/";
//		    }
//		}
//    	try {
//    		ZipFile zipFile = new ZipFile(zipPath, encoding);
//    		// ジップエントリ
//    		Enumeration<ZipEntry> zipEnum = (Enumeration<ZipEntry>)zipFile.getEntries();
//
//    		// 読む
//    		while (zipEnum.hasMoreElements()) {
//    			ZipEntry zipEntry = (ZipEntry)zipEnum.nextElement();
//    			// アンジップ
//    			saveEntry(zipFile, zipEntry, unzipFolderPath);
//    		}
//    		unzipPath = unzipFolderPath;
//    		if(zipFile != null) {
//    			zipFile.close();
//    			zipFile = null;
//    		}
//
//        } catch (FileNotFoundException ex) {
//        	unzipPath = "";
//            log.error("FileNotFoundException:", ex);
//        } catch (ZipException ex) {
//        	unzipPath = "";
//        	log.error("ZipException:", ex);
//        } catch (IOException ex) {
//        	unzipPath = "";
//        	log.error("IOException:", ex);
//        } catch (IndexOutOfBoundsException ex) {
//        	unzipPath = "";
//        	log.error("IndexOutOfBoundsException:", ex);
//        }
//        return unzipPath;
//	}

	//phapnv-Start:#10903
	public static String getExtension(String fileName){
		int index = fileName.lastIndexOf('.');
		if (index > 0) {
		    return fileName.substring(index+1);
		}
		return null;
	}

	public static String getFileNameWithoutExt(String fileName){
		if(null!=fileName){
			int index = fileName.lastIndexOf('.');
			if (index > 0) {
			    return fileName.substring(0,index);
			}
		}
		return StringUtils.EMPTY;
	}

	public static boolean writeFile(String pathFile, byte[] bytes){
		BufferedOutputStream bs = null;
		FileOutputStream fs=null;
		try {
			File file=new File(pathFile);
		    fs = new FileOutputStream(file);
		    bs = new BufferedOutputStream(fs);
		    bs.write(bytes);
		    bs.close();
		    fs.close();
		    bs = null;
		    fs=null;
		    return true;
		} catch (Exception e) {
			return false;
		}finally{
			if(null!=fs){
				try{
					fs.close();
				}catch (Exception e) {
					return false;
				}
			}
			if (bs != null){
				try {
					bs.close();
				} catch (Exception e) {
					return false;
				}
			}
		}
	}

	public static byte[] readFile(InputStream fileStream,long length){
	    if (length > Integer.MAX_VALUE) {
	        return null;
	    }
	    byte[] bytes = new byte[(int)length];
	    int offset = 0;
	    int numRead = 0;
	    try {
			while (offset < bytes.length
			       && (numRead=fileStream.read(bytes, offset, bytes.length-offset)) >= 0) {
			    offset += numRead;
			}
			if (offset < bytes.length) {
		        return null;
		    }

		    fileStream.close();
		} catch (IOException e) {
			return null;
		}
	    return bytes;
	}

	//phapnv-End:#10903

    /**
     * <p>I/Oストリームをクローズします。</p>
     *
     * <p>クローズ時に内部でIOExceptionが発生しても処理は継続されますので、
     * 呼び出し元でIOExceptionをcatchする必要はありません。</p>
     *
     * @param stream
     *            クローズ対象のストリーム
     */
	public static void close(Closeable stream) {
	    try {
	        if(null != stream) {
	            stream.close();
	        }
	    } catch (IOException ioe) {
	        //IOExceptionは無視する
	        if(log.isDebugEnabled()) {
	            log.debug("FileUtils.close " + ioe.getMessage());
	        }
	    }
	}

//    /**
//     * ディスクの使用率を取得する
//     *
//     * JDK1.5ではディスク容量を取得するメソッドがないため
//     * org.apache.commons.io.FileSystemUtilsをもとに実装
//     *
//     * @param path
//     * @param addKb
//     * @return freePercen
//     * @throws IOException
//     */
//    public static long getUsePercent(String path, long addKb)
//            throws IOException {
//
//        long ret = 0;
//
//        String osName = System.getProperty("os.name");
//        if (osName == null) {
//            throw new IOException("os.name not found");
//        }
//        osName = osName.toLowerCase();
//
//        // match
//        if (osName.indexOf("windows") != -1) {
//            ret = getFreePercentWindows(path, addKb);
//        } else if (osName.indexOf("linux") != -1) {
//            ret = getFreePercentLinux(path, addKb);
//        } else {
//            throw new IOException("Unsupported OS");
//        }
//        return 100 - ret;
//    }

//    private static long getFreePercentWindows(String path, long addKb)
//            throws IOException {
//
//        String prefix = FilenameUtils.getPrefix(path);
//
//        // build and run the 'dir' command
//        String[] cmdAttribs = new String[] { "cmd.exe", "/C",
//                "fsutil volume diskfree " + prefix };
//
//        // read in the output of the command to an ArrayList
//        List<String> lines = performCommand(cmdAttribs, Integer.MAX_VALUE);
//
//        // all lines are blank
//        if (lines == null || lines.size() == 0) {
//            throw new IOException(
//                    "Command line 'dir /-c' did not return any info "
//                            + "for path '" + prefix + "'");
//        }
//
//        Pattern p = Pattern.compile(".+[:].(\\d+)");
//
//        BigDecimal total = null;
//        BigDecimal free = null;
//
//        String line = (String) lines.get(1);
//        if (line.length() > 0) {
//            Matcher m = p.matcher(line);
//            if (m.find()) {
//                int cnt = m.groupCount();
//                if (cnt == 1) {
//                    total = new BigDecimal(m.group(1));
//                }
//            }
//        }
//
//        line = (String) lines.get(2);
//        if (line.length() > 0) {
//            Matcher m = p.matcher(line);
//            if (m.find()) {
//                int cnt = m.groupCount();
//                if (cnt == 1) {
//                    free = new BigDecimal(m.group(1));
//                }
//            }
//        }
//
//        if (free == null || total == null) {
//            throw new IOException(
//                    "Command line 'df -k' did not return any info "
//                            + "for path '" + prefix + "'");
//        }
//
//        addKb = addKb * 1024;
//
//        free = free.subtract(new BigDecimal(addKb));
//        BigDecimal freePar = free.divide(total, 2, BigDecimal.ROUND_HALF_UP)
//                .multiply(new BigDecimal(100));
//
//        return freePar.longValue();
//    }
//
//    private static long getFreePercentLinux(String path, long addKb)
//            throws IOException {
//
//        // build and run the 'dir' command
//    	String[] cmdAttribs = new String[] { "df", "-Pk", path };
//
//        // read in the output of the command to an ArrayList
//        List<String> lines = performCommand(cmdAttribs, Integer.MAX_VALUE);
//
//        // all lines are blank
//        if (lines == null || lines.size() == 0) {
//            throw new IOException(
//                    "Command line 'df -k' did not return any info "
//                            + "for path '" + path + "'");
//        }
//
//        Pattern p = Pattern.compile("^[^\\s]+\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)");
//
//        BigDecimal total = null;
//        BigDecimal free = null;
//
//        String line = (String) lines.get(lines.size() - 1);
//
//        Matcher m = p.matcher(line);
//        if (m.find()) {
//            int cnt = m.groupCount();
//            if (cnt == 3) {
//                total = new BigDecimal(m.group(1));
//                free = new BigDecimal(m.group(3));
//            }
//        }
//
//        if (free == null || total == null) {
//            throw new IOException(
//                    "Command line 'df -k' did not return any info "
//                            + "for path '" + path + "'");
//        }
//
//        free = free.subtract(new BigDecimal(addKb));
//        BigDecimal freePar = free.divide(total, 2, BigDecimal.ROUND_HALF_UP)
//                .multiply(new BigDecimal(100));
//
//        return freePar.longValue();
//    }
//
//    /**
//     * Performs the os command.
//     *
//     * @see org.apache.commons.io.FileSystemUtils#performCommand
//     *
//     * @param cmdAttribs
//     *        the command line parameters
//     * @param max
//     *        The maximum limit for the lines returned
//     * @return the parsed data
//     * @throws IOException
//     *         if an error occurs
//     */
//    private static List<String> performCommand(String[] cmdAttribs, int max)
//            throws IOException {
//
//        List<String> lines = new ArrayList<String>(20);
//        Process proc = null;
//        InputStream in = null;
//        OutputStream out = null;
//        InputStream err = null;
//        BufferedReader inr = null;
//        try {
//            proc = openProcess(cmdAttribs);
//            in = proc.getInputStream();
//            out = proc.getOutputStream();
//            err = proc.getErrorStream();
//            // inr = new BufferedReader(new InputStreamReader(in,
//            // "Windows-31J"));
//            inr = new BufferedReader(new InputStreamReader(in));
//            String line = inr.readLine();
//            while (line != null && lines.size() < max) {
//                line = line.toLowerCase().trim();
//                lines.add(line);
//                line = inr.readLine();
//            }
//
//            proc.waitFor();
//            if (proc.exitValue() != 0) {
//                // os command problem, throw exception
//                throw new IOException("Command line returned OS error code '"
//                        + proc.exitValue() + "' for command "
//                        + Arrays.asList(cmdAttribs));
//            }
//            if (lines.size() == 0) {
//                // unknown problem, throw exception
//                throw new IOException("Command line did not return any info "
//                        + "for command " + Arrays.asList(cmdAttribs));
//            }
//            return lines;
//
//        } catch (InterruptedException ex) {
//            throw new IOException(
//                    "Command line threw an InterruptedException '"
//                            + ex.getMessage() + "' for command "
//                            + Arrays.asList(cmdAttribs));
//        } finally {
//            in.close();
//            out.close();
//            err.close();
//            inr.close();
//            if (proc != null) {
//                proc.destroy();
//            }
//        }
//    }

//    /**
//     * Opens the process to the operating system.
//     *
//     * @see org.apache.commons.io.FileSystemUtils#openProcess
//     *
//     * @param cmdAttribs
//     *        the command line parameters
//     * @return the process
//     * @throws IOException
//     *         if an error occurs
//     */
//    private static Process openProcess(String[] cmdAttribs) throws IOException {
//        return Runtime.getRuntime().exec(cmdAttribs);
//    }
//    
//    /**
//     * ディスクの使用率の取得可否をチェックする
//     *
//     * @return boolean
//     * @throws IOException
//     */
//    public static boolean isCheckDiskUsage()
//            throws IOException {
//        String osName = System.getProperty("os.name");
//        if (osName == null) {
//            throw new IOException("os.name not found");
//        }
//        osName = osName.toLowerCase();
//
//        String[] cmdAttribs = null;
//        // match
//        if (osName.indexOf("windows") != -1) {
//            cmdAttribs = new String[] { "cmd.exe", "/C",
//                    "fsutil" };
//            try {
//                performCommand(cmdAttribs, Integer.MAX_VALUE);
//            } catch (IOException e) {
//                return false;
//            }
//        } else if (osName.indexOf("linux") != -1) {
//        } else {
//            throw new IOException("Unsupported OS");
//        }
//        return true;
//    }
}