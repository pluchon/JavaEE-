import java.io.*;
import java.util.Scanner;

/**
 * @author pluchon
 * @create 2025-11-07-17:03
 * 作者代码水平一般，难免难看，请见谅
 */
public class Demo1 {
    public static void main1(String[] args) {
        File file = new File("C:\\JavaCode\\java-ee-career\\TestProjectNewForEE20251107");
        String [] fileNames = file.list();
        for(String fileName : fileNames){
            System.out.println(fileName);
        }
        File file1 = new File("C:\\JavaCode\\java-ee-career\\TestProjectNewForEE20251107\\Ijava\\Ijavas");
        file1.mkdirs();
    }

    public static void main2(String[] args) {
        File oldPath = new File("C:\\JavaCode\\java-ee-career\\TestProjectNewForEE20251107\\Ijava\\Ijavas\\test.txt");
        File destPath = new File("C:\\JavaCode\\java-ee-career\\TestProjectNewForEE20251107\\test.txt");
        System.out.println(oldPath.renameTo(destPath));
    }

    public static void main3(String[] args) throws IOException {
        //我们提前在文件内容中写入"abcdefg"内容
        InputStream inputStream = new FileInputStream("C:\\JavaCode\\java-ee-career\\TestProjectNewForEE20251107\\test.txt");
        while(true){//不知什么时候读取完，等到读取完毕再判断退出
            int n = inputStream.read();
            if(n == -1){
                //读取完毕
                break;
            }
            //我们使用十六进制打印内容
            System.out.printf("%x\n",n);//61 62 63 64 65 66 67 正好对应我们Unicode码值
        }
    }

    public static void main4(String[] args){
        try (InputStream inputStream = new FileInputStream("C:\\JavaCode\\java-ee-career\\TestProjectNewForEE20251107\\test.txt")) {
            while (true) {
                byte[] bytes = new byte[1024];//每次读取1024个字节
                //如果已经读取完毕就退出
                int n = inputStream.read(bytes);
                if (n == -1) {
                    break;
                }
                for (int i = 0; i < n; i++) {
                    System.out.printf("%x\n", bytes[i]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main5(String[] args) {
        try(OutputStream outputStream = new FileOutputStream("C:\\JavaCode\\java-ee-career\\TestProjectNewForEE20251107\\test.txt")){
            outputStream.write(65);//对于字符A
            outputStream.write(66);//对于字符B
            byte[] bytes = {67,68};//对应字符C,D
            outputStream.write(bytes);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main6(String[] args) {
        try(Reader reader = new FileReader("C:\\JavaCode\\java-ee-career\\TestProjectNewForEE20251107\\test.txt")){
            while(true) {
                int n = reader.read();//读取到末尾返回-1
                if (n == -1) {
                    break;
                }
                char c = (char)n;
                System.out.println(c);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main7(String[] args) {
        try(Writer writer = new FileWriter("C:\\JavaCode\\java-ee-career\\TestProjectNewForEE20251107\\test.txt")){
            writer.write(66);
            char[] ch = {'a','b'};
            writer.write(ch);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main8(String[] args) throws FileNotFoundException {
        InputStream inputStream = new FileInputStream("C:\\JavaCode\\java-ee-career\\TestProjectNewForEE20251107\\test.txt");
        Scanner scanner = new Scanner(inputStream);
        String a = scanner.next();
        System.out.println(a);
    }

    public static void main9(String[] args) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter("C:\\JavaCode\\java-ee-career\\TestProjectNewForEE20251107\\test.txt");
        printWriter.printf("%d+%d=%d\n",10,20,30);
        printWriter.close();
    }
}
