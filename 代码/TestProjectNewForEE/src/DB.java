import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;

/**
 * @author pluchon
 * @create 2025-11-05-09:44
 * 作者代码水平一般，难免难看，请见谅
 */
public class DB {
    private static volatile DataSource dataSource;

    public static DataSource getInstance(){
        if(dataSource == null){
            synchronized (DB.class){
                if(dataSource == null){
                    MysqlDataSource tmp = new MysqlDataSource();
                    tmp = new MysqlDataSource();
                    tmp.setURL("jdbc:mysql://127.0.0.1:3306/home?CharacterEncoding=utf8&useSSL=false");
                    tmp.setUser("root");
                    tmp.setPassword("123456789");
                    //关键一步
                    dataSource = tmp;
                }
            }
        }
        return dataSource;
    }
}
