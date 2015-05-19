# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/stone/Documents/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

#缺省情况下，proguard 会混淆所有代码，但是下面几种情况是不能改变java 元素的名称，否则就会这样就会导致程序出错。
#一， 我们用到反射的地方。
#二， 我们代码依赖于系统的接口，比如被系统代码调用的回调方法，这种情况最复杂。
#三， 是我们的java 元素名称是在配置文件中配置好的。
#常见的不能混淆的androidCode
#Android 程序 ，下面这样代码混淆的时候要注意保留。
#Android系统组件，系统组件有固定的方法被系统调用。
#被Android Resource 文件引用到的。名字已经固定，也不能混淆，比如自定义的View 。
#Android Parcelable ，需要使用android 序列化的。
#其他Anroid 官方建议 不混淆的，如
#android.app.backup.BackupAgentHelper
#android.preference.Preference
#com.android.vending.licensing.ILicensingService
#Java序列化方法，系统序列化需要固定的方法。
#枚举 ，系统需要处理枚举的固定方法。
#本地方法，不能修改本地方法名
#annotations 注释
#数据库驱动
#有些resource 文件
#用到反射的地方
#所以使用proguard时，我们需要有个配置文件告诉proguard 那些java 元素是不能混淆的。

#-optimizationpasses 105
-dontusemixedcaseclassnames  #不使用大小写混合类名

-mergeinterfacesaggressively #合并接口

-verbose
-dontoptimize
-optimizations !code/simplification/arithmetic,!code/simplification/cast,!field/*,!class/merging/*
-dontpreverify
-keepattributes *Annotation*
-keep public class com.google.vending.licensing.ILicensingService
-keep public class com.android.vending.licensing.ILicensingService
-keepclasseswithmembernames class * {
    native <methods>;
}
-keepclassmembers public class * extends android.view.View {
   void set*(***);
   *** get*();
}
#-keepclassmembers class * extends android.app.Activity {
#   public void *(android.view.View);
#}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
#-keepclassmembers class **.R$* {
#    public static <fields>;
#}
-dontwarn android.support.**

#-flattenpackagehierarchy com.stone.myapplication.interfaces # 将包里的类混淆成n个再重新打包到一个个的package中
#-repackageclasses com.stone.myapplication.interfaces #将包里的类混淆成n个再重新打包到一个统一的package中  会覆盖flattenpackagehierarchy选项

-dontshrink #反射调用的eventbus的方法 不瘦身 不移除
#-dontoptimize #不优化
#-dontobfuscate #不混淆
-keep ,allowoptimization,allowshrinking,allowobfuscation public interface *  #dontobfuscate时 该keep 无效

-dontskipnonpubliclibraryclasses #不跳过(即混淆)类库中的非public classes
-printseeds ** #打印-keep处理的类和类成员列表

#-forceprocessing #强制处理，即使混淆后的输出文件已经是最新

#-overloadaggressively #过度加载，多个属性和方法使用相同的名字，只是参数和返回类型不同 可能各种异常
-useuniqueclassmembernames #类和类成员都使用不一样的名字

-keepattributes *Annotation*
# 混淆时可能被移除下面这些东西，如果想保留，需要用该选项。对于一般注解处理如上
# 可以保持的参数有：Exceptions, Signature, Deprecated, SourceFile, SourceDir, LineNumberTable,
#LocalVariableTable, LocalVariableTypeTable, Synthetic,
#EnclosingMethod, RuntimeVisibleAnnotations, RuntimeInvisibleAnnotations, RuntimeVisibleParameterAnnotations,
#RuntimeInvisibleParameterAnnotations, and AnnotationDefault.

-keepattributes LocalVariableTable
-keepparameternames

-keep public class com.stone.myapplication.*Activity {
#     public <methods>;
#     public *;
 }

#,allowobfuscation  #启用混淆  本例表示 只启用瘦身和优化
# returntype methodname(argumenttype,...)
-keep ,allowoptimization,allowshrinking,allowobfuscation public class com.stone.myapplication.*Activity {
    protected <methods>;#keep activity时，继承的方法自动受保护了
#    public void onEvent*(android.app.Activity);
#    public void onEvent*(android.app.Activity, %);
#    public % *();
#    private int ll;
    public *;
#    public void onEventMainThread(android.app.Activity,...);
#    public void onEventAsync(android.app.Activity,...);
#    public void onEventBackgroundThread(android.app.Activity,...);
}
# 上面保护，下面启用 启用无效； 上面启用，下面保护，保护有效。 重在保护

-keep public class com.stone.myapplication.*Activity {
     public void onEvent*(android.app.Activity);
#      *;
 }

-assumenosideeffects public class com.stone.myapplication.Alph {
    public String testassumenosideeffects();
}

#-keep public class * extends java.lang.annotation.Annotation  #保持任意 继承了注解的 class的声明(package和名不变，所有使用到该class的地方都不变)


#-keep ,allowoptimization,allowshrinking public class com.stone.myapplication.Alph {
#,allowoptimization,allowshrinking 受全局 -dontshrink影响 无效
#不使用allowobfuscation时，类名相关不混淆，成员未定义keep，混淆了
#使用allowobfuscation时，类名也混淆了
#}

-keepnames public class com.stone.myapplication.Alph {

}

-keep  @com.stone.myapplication.annotation.Say public class * { #保持使用了@Say的任意class 声明   User声明
    public <methods>; #保持所有public 方法
    private <fields>; #操持所有private 属性
}

-keepclasseswithmembers @com.stone.myapplication.annotation.Say public class * { #保持使用了@Say的任意class 声明   User声明
#    public <methods>; #保持所有public 方法
#    private <fields>; #操持所有private 属性
    private int ll;
}

-keep @com.stone.myapplication.annotation.Say  @ class * #保持使用了@Say的注解@Ha 的声明

-keepclassmembers !final class *  implements java.io.Serializable { #保持非final 的实现了序列化接口的类成员
    public private protected static volatile transient <fields>;   # 属性
    public private  <methods>; #public 和private 方法
}
#测试 反编译  是否  混淆
