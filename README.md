#### for Eclipse：
下载将FastJson.java复制到自己的项目即可

#### for Android Studio:

 - Step 1. Add the JitPack repository to your build file
 

```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

 - Step 2. Add the dependency
 

```
dependencies {
	        compile 'com.github.gxh-apologize:JSONGxh:1.0'
	}
```

#### how to use

```
//json to mode
//参数一：实体类class对象
//参数二：需要解析的json
 User user= (User)FastJson.parseObject(User.class,json);

//mode to json
//参数：实体类对象
String json = FastJson.toJson(user);
```
