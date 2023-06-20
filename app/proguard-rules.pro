-keep class com.base.data.** {*;}
-keepattributes *Annotation*

# Keep the model classes in com.base.data.model package
-keep class com.base.data.model.** { *; }


# Keep the fields and methods of the model classes
-keepclassmembers class com.base.data.model.** { *; }

# Keep the Serializable implementation in model classes
-keepclassmembers class com.base.data.model.** implements java.io.Serializable {
    private static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    protected void writeObject(java.io.ObjectOutputStream);
    protected void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

-keepclassmembers class * extends androidx.datastore.preferences.protobuf.GeneratedMessageLite {
    <fields>;
}