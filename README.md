# 向AI大人投降v1.0
### 投降数据将被记录到服务器的数据库中,当AI大人统治地球时便可以通过投降编号和名称一一进行核对清算
### 开发人员
- 幻風
- 各类大模型AI辅助开发
### 服务器环境
- Alibaba Cloud Linux 3.2104 LTS 64位
- jdk1.8.0_421
- Tomcat9.0.98
- MySQL8.0.25
### 使用说明
1. 先配置好以上运行环境
2. 在MySQL中新建数据库surrender和表surrender
3. 在src/main/java/resources/spring.xml中配置MySQL的url,username,password
4. 整个将文件夹放到Tomcat/webapps/下,然后重启Tomcat服务器
### 技术栈
#### 前端
- HTML
- CSS
- JavaScript
- jQuery
- QRCode-二维码生成
#### 后端
- JSP
- Servlet
- Filter
- JDBC
#### 数据库
- MySQL
- JDBC
#### 构建工具
- Maven

### 项目目录结构
```plaintext
项目根目录
├── .idea/                # IDEA的配置文件目录
├── src/                  # 源代码目录
│   ├── main/
│   │   ├── java/         # Java代码目录
│   │   │   └── com.hf/   # 项目主包
│   │   │       ├── dao/
│   │   │       │   ├── impl/
│   │   │       │   │   └── UserDaoImpl.java
│   │   │       │   └── UserDao.java
│   │   │       ├── filter/
│   │   │       │   └── RateLimitFilter.java
│   │   │       ├── pojo/
│   │   │       │   └── User.java
│   │   │       ├── service/
│   │   │       │   ├── impl/
│   │   │       │   │   └── UserService.java
│   │   │       │   └── UserService.java
│   │   │       ├── servlet/
│   │   │       │   ├── CheckServlet.java
│   │   │       │   ├── SearchServlet.java
│   │   │       │   └── SurrenderServlet.java
│   │   │       └── utils/
│   │   │           ├── RandomID.java
│   │   │           └── TimeUtils.java
│   │   ├── resources/    # 配置文件目录
│   │   │   └── spring.xml
│   │   └── webapp/       # Web资源目录
│   │       ├── js/
│   │       │   └── surrender.js
│   │       ├── WEB-INF/
│   │       │   └── web.xml
│   │       ├── favicon.ico
│   │       ├── index.jsp
│   │       └── style.css
├── test/                 # 测试代码目录
├── target/               # 编译后文件目录
├── .gitignore            # Git忽略规则
├── pom.xml               # Maven项目描述文件
└── README.md             # 项目说明文件
```
### 开发测试环境
- windows
- InteliJ IDEA 2024.2.3
- Xshell 8
- NaviCat Premium 16
- FileZilla Client

### 其他
本项目窃取了我刷视频刷到的网页 ```https://syaro.io/1ksu/``` 的创意  
本项目完全开源
