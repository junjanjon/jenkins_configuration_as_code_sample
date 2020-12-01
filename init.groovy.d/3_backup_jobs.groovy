import jenkins.model.*

def instance  = Jenkins.getInstance()
def topLevelItems = instance.getItems()

def backupDirectory = new File("/var/jenkins_home/backup")
backupDirectory.mkdir()

topLevelItems.each { topLevelItem ->
  topLevelItem.getAllJobs().each { job ->
    println configPath = job.getBuildDir().getParent() + "/config.xml"
    println backupPath = backupDirectory.getAbsolutePath() + "/" + job.name + ".xml"
    new File(backupPath).setBytes(new File(configPath).getBytes())
  }
}
