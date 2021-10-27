package com.hzvanke.report.commons;

import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public final class FileUtils {


    /**
     * 判断文件夹是否存在，不存在就创建
     *
     * @param path
     */
    public static void judeDirExists(String path) {
       judeDirExists(new File(path));
    }

    /**
     * 判断文件夹是否存在，不存在就创建
     *
     * @param file
     */
    public static void judeDirExists(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
                System.out.println("文件夹存在");
            } else {
                System.out.println("同名文件存在，不能创建文件夹");
            }
        } else {
            System.out.println("文件夹不存在, create it ...");
            file.mkdir();
        }
    }

    /**
     * 将存放在sourceFileName目录下的源文件，打包成fileName名称的zip文件，并存放到zipFilePath路径下
     *
     * @param sourceFileName 需要打包的路径
     * @param zipFilePath    :压缩后存放路径
     * @param fileName       :压缩后文件的名称
     * @throws Exception
     */
    public static void toZip(String sourceFileName, String zipFilePath,
                             String fileName) throws Exception {
        // File zipFile = new File(zipFileName);
        System.out.println("压缩中...");
        // 创建zip输出流
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(
                zipFilePath + fileName + ".zip"));
        File sourceFile = new File(sourceFileName);
        File[] filelist = sourceFile.listFiles();
        for (File file : filelist) {
            compress(zos, file, file.getName());
        }
        zos.close();
        System.out.println("压缩完成");
    }

    public static void compress(ZipOutputStream zos, File sourceFile,
                                String base) throws Exception {
        // 如果路径为目录（文件夹）
        if (sourceFile.isDirectory()) {
            // 取出文件夹中的文件（或子文件夹）
            File[] flist = sourceFile.listFiles();
            if (flist.length == 0)// 如果文件夹为空，则只需在目的地zip文件中写入一个目录进入点
            {
                System.out.println(base + "/");
                zos.putNextEntry(new ZipEntry(base + "/"));
            } else// 如果文件夹不为空，则递归调用compress，文件夹中的每一个文件（或文件夹）进行压缩
            {
                for (int i = 0; i < flist.length; i++) {
                    compress(zos, flist[i], base + "/" + flist[i].getName());
                }
            }
        } else {// 如果不是目录（文件夹），即为文件，则先写入目录进入点，之后将文件写入zip文件中
            zos.putNextEntry(new ZipEntry(base));
            FileInputStream fos = new FileInputStream(sourceFile);
            int tag;
            System.out.println(base);
            // 将源文件写入到zip文件中
            while ((tag = fos.read()) != -1) {
                zos.write(tag);
            }
            fos.close();
        }
    }

    /**
     * 将存放在sourceFilePath目录下的源文件，打包成fileName名称的zip文件，并存放到zipFilePath路径下
     *
     * @param sourceFilePath :待压缩的文件路径
     * @param zipFilePath    :压缩后存放路径
     * @param fileName       :压缩后文件的名称
     * @return
     */
    @SuppressWarnings("resource")
    public static boolean fileToZip(String sourceFilePath, String zipFilePath,
                                    String fileName) {
        boolean flag = false;
        File sourceFile = new File(sourceFilePath);
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        ZipOutputStream zos = null;

        if (sourceFile.exists() == false) {
            System.out.println("待压缩的文件目录：" + sourceFilePath + "不存在.");
            sourceFile.mkdir(); // 新建目录
        }
        try {
            File zipFile = new File(zipFilePath + "/" + fileName + ".zip");
            if (zipFile.exists()) {
                System.out.println(zipFilePath + "目录下存在名字为:" + fileName
                        + ".zip" + "打包文件.");
            } else {

                File[] sourceFiles = sourceFile.listFiles();
                if (null == sourceFiles || sourceFiles.length < 1) {
                    System.out.println("待压缩的文件目录：" + sourceFilePath
                            + "里面不存在文件，无需压缩.");
                } else {
                    fos = new FileOutputStream(zipFile);
                    zos = new ZipOutputStream(new BufferedOutputStream(fos));
                    byte[] bufs = new byte[1024 * 10];
                    for (int i = 0; i < sourceFiles.length; i++) {
                        // 创建ZIP实体，并添加进压缩包
                        ZipEntry zipEntry = new ZipEntry(
                                sourceFiles[i].getName());
                        zos.putNextEntry(zipEntry);
                        // 读取待压缩的文件并写进压缩包里
                        fis = new FileInputStream(sourceFiles[i]);
                        bis = new BufferedInputStream(fis, 1024 * 10);
                        int read = 0;
                        while ((read = bis.read(bufs, 0, 1024 * 10)) != -1) {
                            zos.write(bufs, 0, read);
                        }
                    }
                    flag = true;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            // 关闭流
            try {
                if (null != bis)
                    bis.close();
                if (null != zos)
                    zos.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        return flag;
    }

    /**
     * 创建多级目录
     *
     * @param path     文件路径
     * @param pathType files代表创建多级文件，dirs代表创建多级文件目录
     * @throws IOException
     */
    public static void createFile(String path, String pathType)
            throws IOException {
        if (StringUtils.isNotEmpty(path)) {
            File file = new File(path);
            if ("files".equals(pathType)) {
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                file.createNewFile();
            }
            if ("dirs".equals(pathType)) {
                file.mkdirs();
            }
        }
    }

    /**
     * 删除文件，可以是文件或文件夹
     *
     * @param fileName 要删除的文件名
     * @return 删除成功返回true，否则返回false
     */
    public static boolean delete(String fileName) throws Exception {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("删除文件失败:" + fileName + "不存在！");
            return false;
        } else {
            if (file.isFile())
                return deleteFile(fileName);
            else
                return deleteDirectory(fileName);
        }
    }

    /**
     * 删除单个文件
     *
     * @param fileName 要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String fileName) throws Exception{
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除单个文件" + fileName + "成功！");
                return true;
            } else {
                System.out.println("删除单个文件" + fileName + "失败！");
                return false;
            }
        } else {
            System.out.println("删除单个文件失败：" + fileName + "不存在！");
            return false;
        }
    }

    /**
     * 删除目录及目录下的文件
     *
     * @param dir 要删除的目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String dir) throws Exception{
        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
        if (!dir.endsWith(File.separator))
            dir = dir + File.separator;
        File dirFile = new File(dir);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
            System.out.println("删除目录失败：" + dir + "不存在！");
            return false;
        }
        boolean flag = true;
        // 删除文件夹中的所有文件包括子目录
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = FileUtils.deleteFile(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
            // 删除子目录
            else if (files[i].isDirectory()) {
                flag = FileUtils.deleteDirectory(files[i]
                        .getAbsolutePath());
                if (!flag)
                    break;
            }
        }
        if (!flag) {
            System.out.println("删除目录失败！");
            return false;
        }
        // 删除当前目录
        if (dirFile.delete()) {
            System.out.println("删除目录" + dir + "成功！");
            return true;
        } else {
            return false;
        }
    }
}