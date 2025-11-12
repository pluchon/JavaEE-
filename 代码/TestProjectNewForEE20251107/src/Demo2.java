import java.io.*;
import java.util.Scanner;
import java.util.zip.InflaterInputStream;

/**
 * @author pluchon
 * @create 2025-11-07-18:22
 * 作者代码水平一般，难免难看，请见谅
 */
public class Demo2 {
    public static void main1(String[] args) {
        //使用递归查看当前目录的所有包含指定名字的文件
        Scanner scanner = new Scanner(System.in);
        String rootPath = scanner.next();
        String fileName = scanner.next();
        //判断输入合法性
        if(rootPath.isEmpty() || fileName.isEmpty()){
            System.out.println("输入非法");
            return;
        }
        File rootFile = new File(rootPath);
        //判断这个是否存在或者是这个路径是否是目录
        if(!rootFile.exists() || !rootFile.isDirectory()){
            System.out.println("非法目录");
            return;
        }
        search(rootFile,fileName);
    }

    private static void search(File rootFile,String fileName){
        //列出当前目录中的所有文件包括子目录
        File[] files = rootFile.listFiles();
        //判定空目录
        if(files == null){
            return;  // 空目录直接返回，不需要打印
        }
        for(File file : files){
            if(file.isFile()){
                if(file.getName().contains(fileName)){
                    //找到了就尝试去删除 - 这里应该传入当前文件，不是根目录
                    tryToDelete(file,fileName);
                }
            }else if(file.isDirectory()){
                //如果是一个子目录就进行递归操作 - 这里递归参数错了
                search(file,fileName);  // 应该是file不是rootFile
            }
        }
    }

    private static void tryToDelete(File targetFile,String fileName){
        Scanner scanner = new Scanner(System.in);
        System.out.println("是否要删除 " + targetFile.getAbsolutePath() + " ？（Y/N）");
        String choice = scanner.next();
        if(choice.equals("Y") || choice.equals("y")){
            boolean success = targetFile.delete();
            if(success){
                System.out.println("删除成功: " + targetFile.getName());
            }else{
                System.out.println("删除失败: " + targetFile.getName());
            }
        }else{
            System.out.println("删除取消");
        }
    }

    public static void main2(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String oldPath = scanner.next();//原文件路径
        String destPath = scanner.next();//目标文件路径
        File oldFile = new File(oldPath);
        if(!oldFile.isFile()){
            //如果传入的不是文件就不能执行复制操作
            System.out.println("非法输入");
            return;
        }
        //复制的同时重命名
        File destFile = new File(destPath);
        //先找到目标路径的双亲路径
        File destParent = destFile.getParentFile();
        if(!destParent.isDirectory()){
            System.out.println("路径非法");
        }
        //同时打开两个文件路径进行复制操作
        try(InputStream inputStream = new FileInputStream(oldFile);
            OutputStream outputStream = new FileOutputStream(destFile)){
            while(true){
                //我们一次性1024字节即1kb/次速度进行复制
                byte[] bytes = new byte[1024];
                int n = inputStream.read(bytes);
                if(n == -1){
                    break;
                }
                outputStream.write(bytes,0,n);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String rootPath = scanner.next();//要搜索的根目录
        String word = scanner.next();//要搜索的关键字
        if(word.isEmpty() || rootPath.isEmpty()){
            System.out.println("输入非法");
            return;
        }
        File rootFile = new File(rootPath);
        if(!rootFile.isDirectory()){
            System.out.println("输入非法，不是目录！");
            return;
        }
        searchs(rootFile,word);
    }

    private static void searchs(File rootFile,String word){
        //列出所有的内容
        File[] files = rootFile.listFiles();
        if(files == null){
            return;
        }
        for(File file : files){
            if(file.isFile()){
                //如果是文件，就要看看内容是否包含
                trySearch(file,word);
            }else if(file.isDirectory()){
                //递归子目录
                searchs(file,word);
            }
        }
    }

    private static void trySearch(File file,String word){
        //先判断文件名是不是我们想要的
        if(file.getName().contains(word)){
            System.out.println("找到了，文件路径是：");
            System.out.println(file.getAbsolutePath());
        }
        //接下来查看文件内容，即使文件名不匹配内容也可能匹配
        try(Reader reader = new FileReader(file)){
            //把读取到的字符进行拼接
            StringBuilder str = new StringBuilder();
            while(true){
                char [] chars = new char[1024];
                int n = reader.read();
                if(n == -1){
                    //文件读取到末尾了
                    break;
                }
                str.append(chars);
            }
            if(str.indexOf(word) >= 0){
                System.out.println("找到了");
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
