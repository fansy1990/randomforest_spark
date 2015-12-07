package algorithm

import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.tree.DecisionTree
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkContext, SparkConf}

/**
 * Created by Fansy on 2015/11/3.
 */
object RandomForest {
  def main(args:Array[String]): Unit ={
    if(args.length!=8){
      println("Usage: algorithm.RandomForest <input> <model> <numClasses> <impurity> <maxDepth> <maxBins> <splitter> <labelIndex>")
      System.exit(-1)
    }
    val input = args(0)
    val model = args(1)
    val numClasses = args(2).toInt // 10
//    val categoricalFeaturesInfo = Map[Int, Int]()
    val impurity = args(3) // gini
    val maxDepth = args(4).toInt // 5
    val maxBins = args(5).toInt // 32
    val splitter =args(6) // ,
    val labelIndex=args(7).toInt // 0
    train(input,splitter,labelIndex  ,numClasses,impurity,maxDepth,maxBins,model)
  }

  /**
   *
   * @param datafile
   * @param splitter
   * @param labelIndex
   * @param numClasses
   * @param impurity
   * @param maxDepth
   * @param maxBins
   * @param modelPath
   */
  def train(datafile:String,splitter:String,labelIndex:Int,numClasses:Int ,
             impurity:String,maxDepth:Int,maxBins:Int,modelPath:String): Unit ={
    val sc: SparkContext = new SparkContext(new SparkConf().setAppName("Random Forest model training"))
//    val data = MLUtils.loadLibSVMFile(sc, "data/mllib/sample_libsvm_data.txt")

   val data = sc.textFile(datafile)
    val parsedData = data.map { line => val parts = line.split(splitter)
      LabeledPoint(parts(labelIndex*(parts.length-1)).toDouble,
        Vectors.dense(parts.slice(1-labelIndex,parts.length-labelIndex).map(_.toDouble)))
    }

    // Train a DecisionTree model.
    val categoricalFeaturesInfo = Map[Int, Int]()
    val model = DecisionTree.trainClassifier(parsedData, numClasses, categoricalFeaturesInfo,
      impurity, maxDepth, maxBins)
    // Save and load model
    model.save(sc, modelPath)
//    val sameModel = DecisionTreeModel.load(sc, "myModelPath")
    sc.stop()
  }
}
