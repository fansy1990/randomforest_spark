
import org.apache.spark.{SparkConf, SparkContext}


/**
 * Created by Administrator on 2015/8/23.
 */
object Scala_Test {
  def main(args:Array[String]): Unit ={
    if(args.length!=2){
      System.err.println("Usage:Scala_Test <input> <output>")
    }
    // 初始化SparkConf
    val conf = new SparkConf().setAppName("Scala filter")
    val sc = new SparkContext(conf)

    //  读入数据
    val lines = sc.textFile(args(0))

    // 转换
    val errorsRDD = lines.filter(line => line.contains("ERROR"))
    val warningsRDD = lines.filter(line => line.contains("WARN"))
    val  badLinesRDD = errorsRDD.union(warningsRDD)

    // 写入数据
    badLinesRDD.saveAsTextFile(args(1))

    // 关闭SparkConf
    sc.stop()
  }
}
