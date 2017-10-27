/**
 * Pipeline Definition File
 * Information about the syntax can be found here: https://jenkins.io/doc/book/pipeline/syntax/
 *
 * To run this File as a Pipeline job atleast the following plugins are required:
 * https://wiki.jenkins-ci.org/display/JENKINS/Pipeline+Model+Definition+Plugin
 * https://wiki.jenkins-ci.org/display/JENKINS/Pipeline+Utility+Steps+Plugin
 * https://wiki.jenkins-ci.org/display/JENKINS/Lockable+Resources+Plugin
 */

def call(project) {

    env.PROJECT_NAME = project

    pipeline {
        agent any

        options {
            buildDiscarder(logRotator(numToKeepStr: '10'))
        }

        stages {

            stage('Prepare') {
                steps {
                    stagePrepare()
                }

            }

            stage('Unit Tests') {
                steps {
                    lock("${env.PROJECT_NAME}-db") {
                        stageUnitTests()
                    }
                }
                post {
                    always {
                        junit "**/test-results/test/TEST-*.xml"
                    }
                }
            }

            stage('Sonar') {
                steps {
                    stageSonar()
                }
            }

            stage('Deploy') {
                steps {
                    stageDeploy()
                }
            }

            stage('merge in master') {
                steps {
                    stageMergeMaster()
                }
            }

            stage('merge in higher versions') {
                steps {
                    stageMergeHigherVersion()
                }
            }
        }
    }
}
