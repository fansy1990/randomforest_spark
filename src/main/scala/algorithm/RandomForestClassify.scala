package algorithm

import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.tree.model.DecisionTreeModel
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Created by Fansy on 2015/11/3.
 */
object RandomForestClassify {
  def main(args:Array[String]): Unit ={

    if(args.length!=4){
      println("Usage:algorithm.RandomForestClassify <testdata> <model> <splitter> <output>")
      System.exit(-1)
    }
    val testdata = args(0)
    val model=args(1)
    val splitter=args(2)
    val output = args(3)
    predict(testdata,model,splitter,output)
  }

  /**
   *
   * @param testdata
   * @param modelPath
   * @param splitter
   * @param outputfile
   */
  def predict(testdata:String,modelPath:String ,splitter:String,outputfile:String): Unit ={
    val sc: SparkContext = new SparkContext(new SparkConf().setAppName("Random Forest model classify"))
    val model = DecisionTreeModel.load(sc, modelPath)
    val testData =sc.textFile(testdata).map { line =>
      Vectors.dense(line.trim().split(splitter).map(_.toDouble))
    }
    val predictionAndLabel = testData.map { point => model.predict(point) }

    predictionAndLabel.saveAsTextFile(outputfile )
    sc.stop()
  }



}
