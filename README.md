# nekurabe-android

An Android app.

nekurabe-android is one of the [w010][bouzuya/w010] apps.

## How to build

### debug build

```
$ ./gradlew assembleDebug
```

### release build

```
$ # require `nekurabe.jks`
$ ./gradlew -PpropertyKeyPassword=$KEY_PASSWORD -PpropertyStorePassword=$STORE_PASSWORD assembleRelease
```

[bouzuya/w010]: https://github.com/bouzuya/w010
