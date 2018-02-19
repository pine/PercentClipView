## PercentClipView &nbsp;[![Build Status](https://travis-ci.org/pine/PercentClipView.svg?branch=master)](https://travis-ci.org/pine/PercentClipView) [![codebeat badge](https://codebeat.co/badges/5d2dc83f-4a06-49c6-ab80-db195bc3b00f)](https://codebeat.co/projects/github-com-pine-percentclipview-master)

:star: Cropped layouts with percentage for Android

<img src="example.png" width="270" height="480">

## Requirements

- Android 4.0.3 (API Level 15) or later

## Getting Started
Please type it in your build.gradle file.

```groovy
repositories {
    jcenter()
}

dependencies {
    compile 'moe.pine:percent-clip-view:0.1.2'
}
```

## Usage
You can use they layouts in XML:

```xml
<moe.pine.percent_clip_view.LinearLayout
    android:id="@+id/layout"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:orientation="horizontal"
    app:clipRight="0.30">

    <ImageView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:src="@drawable/star" />

    <ImageView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:src="@drawable/star" />

    <ImageView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:src="@drawable/star" />

    <ImageView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:src="@drawable/star" />

    <ImageView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:src="@drawable/star" />
</moe.pine.percent_clip_view.LinearLayout>
```

And, they have getters and setters:

```java
LinearLayout layout = (LinearLayout) this.findViewById(R.id.layout);
layout.setClipLeft(0.50f);
```

## Reference
### Class

- FrameLayout
- LinearLayout
- RelativeLayout

### Properties

- clipTop
- clipRight
- clipBottom
- clipLeft

## Upload to Bintray

```
$ ./gradlew clean assemble bintrayUpload
```

## Acknowledgements

- [@mattak](https://github.com/mattak) I referred his [ClipView](https://github.com/mattak/ClipView)

## License
MIT &copy; Pine Mizune
