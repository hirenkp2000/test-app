# Test App

`v0.0.1`

* [Prerequisites](#prerequisites)
* [Build](#build)
* [Run](#run)
* [Author](#author)


## Prerequisites

* Apache Maven 3.6+ (for building app)
* JDK 1.8+



## Build

Run following command in terminal:

```shell
mvn clean install
```

It should generate artifact - ./target/test-app-0.0.1.jar




## Run

Run following command:

```shell
java -jar target/test-app-0.0.1.jar
```

It should print the unique list of dependencies as expected:

```shell
2021-03-11 18:31:55,197 INFO  [main] com.test.app.DepTest (lambda$printOutputMap$2:54) {}- Dependency => Second, v2.0.0
2021-03-11 18:31:55,197 INFO  [main] com.test.app.DepTest (lambda$printOutputMap$2:54) {}- Dependency => Trans 02.01, v1.2.1
2021-03-11 18:31:55,198 INFO  [main] com.test.app.DepTest (lambda$printOutputMap$2:54) {}- Dependency => Trans 01.01, v2.1.1
2021-03-11 18:31:55,198 INFO  [main] com.test.app.DepTest (lambda$printOutputMap$2:54) {}- Dependency => Trans 02, v2.2.0
2021-03-11 18:31:55,198 INFO  [main] com.test.app.DepTest (lambda$printOutputMap$2:54) {}- Dependency => Trans 01, v1.1.0
2021-03-11 18:31:55,199 INFO  [main] com.test.app.DepTest (lambda$printOutputMap$2:54) {}- Dependency => First, v1.0.0
```



## Author

hirenkp2000@gmail.com
