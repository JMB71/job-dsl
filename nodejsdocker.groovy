job('NodeJS Docker example') {
  scm {
    git('git://github.com/wardviaene/docker-demo.git') { node ->
      node / gitConfigName('DSL User')
      node / gitConfigEmail('jean-marie.bernabeu@laposte.net')
    }
  }
  triggers {
    scm('H/5 * * * *)
  }
  wrappers {
    nodejs('nodejs-12.21.0')
  }
  steps {
    dockerBuildAndPublish {
      repositoryName('jmb25/docker-nodejs-demo')
      tag('${GIT_REVISION,length=9}')
      registryCredentials('dockerhub')
      forcePull(false)
      forceTag(false)
      createFingerprints(false)
      skipDecorate()
    }
  }
}
