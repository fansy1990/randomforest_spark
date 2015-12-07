import test.Test1;

/**
 * Created by Administrator on 2015/8/23.
 */
public class Test {

    public static void main(String[] args){
        String[] arg=new String[]{
            "spark://node101:7077",
                "hdfs://node101/user/root/log.txt",
                "hdfs://node101:/user/root/badLines02"
        };

        Test1.main(arg);
    }
}
