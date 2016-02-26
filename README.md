# trafficConfirmer
confirm accessing to given URLs

# jarの作り方
mvn package assembly:single

# 使い方
application.confに、下記のようにして疎通確認をしたいURLのリストを記述します。

```
urls: [
  "https://github.com/"
  "https://www.google.co.jp/"
  "http://localhost"
]
```

依存ライブラリつきのjarを、下記のようにapplication.confを指定して実行します。

`java -Dconfig.file=application.conf -jar trafficConfirmer-x.y.z-jar-with-dependencies.jar` 

下記のようなものが標準出力されます。またlogs以下に、同じ内容のlogファイルが作成されます。

```
2015-10-22 13:32:36,344 [main] INFO  TrafficConfirmer - https://github.com/ [OK], status_code:200 
2015-10-22 13:32:36,602 [main] INFO  TrafficConfirmer - https://www.google.co.jp/ [OK], status_code:200 
```

test

