FROM jenkins/jenkins:2.249.1-lts-centos

# /usr/share/jenkins/ref は /var/jenkins_home となる。

# CASC_JENKINS_CONFIG に jenkins の設定を置くと、起動時にリストアされる。
COPY configurations/jenkins_config.yml /usr/share/jenkins/ref/casc/jenkins_config.yaml
ENV CASC_JENKINS_CONFIG=/var/jenkins_home/casc/jenkins_config.yaml

# init.groovy.d ディレクトリ以下に配置されたスクリプトは Jenkins 起動時に辞書順に実行されます。
## プラグインのインストールをするスクリプトを配置
COPY init.groovy.d/1_install_plugins.groovy /usr/share/jenkins/ref/init.groovy.d/1_install_plugins.groovy
## backup ディレクトリ以下にあるジョブを読み込むスクリプトを配置
COPY init.groovy.d/2_load_jobs.groovy /usr/share/jenkins/ref/init.groovy.d/2_load_jobs.groovy

# backup ディレクトリ以下にジョブ設定ファイルを配置
RUN mkdir /usr/share/jenkins/ref/backup
COPY configurations/backup_jobs/zatsu_job.xml /usr/share/jenkins/ref/backup/zatsu_job.xml
