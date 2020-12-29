
# 概要

Jenkins Configuration as Code を使って Jenkins を起動するサンプルリポジトリ。

参考記事: https://tech.drecom.co.jp/ac2020-start-jenkins-configuration-as-code-life/

# 起動方法

## Docker ビルド

```
docker build \
  -f Dockerfile \
  -t myjenkins .
```

## Docker 起動

```
docker run \
  -p 8080:8080 -p 50000:50000 \
  -v $(shell pwd)/data:/var/jenkins_home \
  --env JAVA_OPTS=-Djenkins.install.runSetupWizard=false \
  myjenkins
```

localhost:8080 でアクセスできる。
