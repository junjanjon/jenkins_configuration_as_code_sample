import jenkins.model.*

def instance  = Jenkins.getInstance()
def plugins   = [
  "jdk-tool",
  "trilead-api",
  "command-launcher",
  "apache-httpcomponents-client-4-api",
  "structs",
  "bouncycastle-api",
  "pam-auth",
  "antisamy-markup-formatter",
  "external-monitor-job",
  "ssh-agent",
  "ssh-slaves",
  "scm-api",
  "matrix-project",
  "ldap",
  "git",
  "workflow-step-api",
  "mailer",
  "matrix-auth",
  "workflow-scm-step",
  "script-security",
  "jsch",
  "ssh-credentials",
  "plain-credentials",
  "categorized-view",
  "workflow-api",
  "display-url-api",
  "scoring-load-balancer",
  "credentials-binding",
  "jquery3-api",
  "snakeyaml-api",
  "jackson2-api",
  "plugin-util-api",
  "echarts-api",
  "junit",
  "credentials",
  "git-client",
  "configuration-as-code"
]

pm = instance.getPluginManager()
uc = instance.getUpdateCenter()

uc.updateAllSites()

def enablePlugin(pluginName) {
  if (! pm.getPlugin(pluginName)) {
    deployment = uc.getPlugin(pluginName).deploy(true)
    deployment.get()
  }

  def plugin = pm.getPlugin(pluginName)
  if (! plugin.isEnabled()) {
    plugin.enable()
  }

  plugin.getDependencies().each {
    enablePlugin(it.shortName)
  }
}

plugins.each {
  def plugin = pm.getPlugin(it)
  enablePlugin(it)
}
