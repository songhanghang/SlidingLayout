# SlidingLayout
SlidingLayout support ListView GridView (ScrollView need yourself..., so easy)

this project was born in letv video android app http://www.letv.com/

![Renderings](https://github.com/songhanghang/SlidingLayout/blob/master/screenshot/A0001LRX22Gsonghang12062015142854.gif)

# usage
* set SlidingLayouut in your xml 
* set handle view ( handle view is layout bar, everytime you can touch it)
```xml
<com.facefont.songhang.slidinglayout.slidingview.SlidingLayout
        android:id="@+id/slidingview"
        android:layout_width="match_parent"
        android:layout_height="400dp"
        android:orientation="vertical"
        app:handle_id="@+id/handler"
        >
        <RelativeLayout
            android:id="@+id/handler"
            android:layout_width="match_parent"
            android:layout_height="38dp">
        </RelativeLayout>
</com.facefont.songhang.slidinglayout.slidingview.SlidingLayout>
```
* in you activity or fragment find SlidingLayout
* then you can dynamic replace contentView { setContentView(SlidingView contentView) } binding with  SlidingLayout.

* last, ContentView will Sliding with SlidingLayout without conflict。

