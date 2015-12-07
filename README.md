# randomforest_spark

Ê¾Àýµ÷ÓÃ£º
ÑµÁ·£º
[root@node88 spark-1.4.1-bin-hadoop2.6]# bin/spark-submit --class algorithm.RandomForest --master spark://node88:7077 --executor-memory 512m --total-executor-cores 1 /opt/spark141_h26.jar file:///opt/naivebayes.txt file:///opt/data/naive_model 4 gini 5 32 , 0

²âÊÔ£º
[root@node88 spark-1.4.1-bin-hadoop2.6]# bin/spark-submit --class algorithm.RandomForestClassify --master spark://node88:7077 --executor-memory 512m --total-executor-cores 1 /opt/spark141_h26.jar file:///opt/naivebayes_predict.txt file:///opt/data/naive_model , file:///opt/data/naive_predict


