# VerticalMarqueeView
### 垂直跑马灯,实现消息公告类滚动效果


### 效果图如下:
![](https://github.com/baixxx/VerticalMarqueeView/raw/master/resource/marqueeview.gif)  



### 自定义属性
| Attribute属性    | Description描述 |
| :----------: | :-----------:  | 
| interval	     | 文字滚动间隔时间 |
| animaDuration	 | 动画持续时间 |
| textSize	| 文字大小  |
| textColor | 文字颜色  |



### xml文件引用
```
 <com.bx.marqueeviewlibrary.MarqueeView
        android:id="@+id/marqueeview"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:paddingLeft="20dp"
        app:interval="1500"
        app:textColor="#000"
        app:animDuration="800"
        app:textSize="5sp">
    </com.bx.marqueeviewlibrary.MarqueeView>
```

###集成
第 1 步、在工程的 build.gradle 中添加：
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
第 2 步、在应用的 build.gradle 中添加：
```
dependencies {
	        compile 'com.github.baixxx:VerticalMarqueeView:v1.0'
	}
 ```
 
[![](https://jitpack.io/v/baixxx/VerticalMarqueeView.svg)](https://jitpack.io/#baixxx/VerticalMarqueeView)



