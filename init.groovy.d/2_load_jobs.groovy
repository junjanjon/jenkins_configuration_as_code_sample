import jenkins.model.*
import javax.xml.transform.stream.StreamSource

def instance = Jenkins.getInstance()
def jobNames = instance.getItems()

def backupDirectory = new File("/var/jenkins_home/backup")
println backupDirectory
backupDirectory.mkdir()
File[] files = backupDirectory.listFiles();

for (int i = 0; i < files.length; i++) {
  File file = files[i]
  String fileName = file.getName()
  String jobName = fileName.substring(0, fileName.lastIndexOf('.'))

  if ( jobNames.any { it.fullName == jobName } ) {
    println "update job ${jobName}"
    InputStream is = new FileInputStream(file);
    instance.getItem(jobName).updateByXml(new StreamSource(is));
    continue
  }

  println "create job ${jobName}"
  def configXml = file.text
  def xmlStream = new ByteArrayInputStream(configXml.getBytes())
  instance.createProjectFromXML(jobName, xmlStream)
}
