# rocketmq
rocketmq java demo
## 启动配置
* step1：配置环境变量：ROCKETMQ_HOME = D:\java\rocketmq\rocketmq-all-4.3.0-bin-release
* step2：启动 mqnamesrv 服务，执行 mqnamesrv.cmd 启动服务
* step3：启动 broker 服务，start mqbroker.cmd -n 127.0.0.1:9876 autoCreateTopicEnable=true
* step4：启动 rocketmq-console 工程，java -jar rocketmq-console-ng-1.0.0.jar、
* step5：浏览中访问 http://127.0.0.1:8089/
