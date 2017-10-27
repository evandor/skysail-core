/**
 * Pipeline Definition File
 * Information about the syntax can be found here: https://jenkins.io/doc/book/pipeline/syntax/
 *
 * To run this File as a Pipeline job atleast the following plugins are required:
 * https://wiki.jenkins-ci.org/display/JENKINS/Pipeline+Model+Definition+Plugin
 * https://wiki.jenkins-ci.org/display/JENKINS/Pipeline+Utility+Steps+Plugin
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
                    stageUnitTests()
                }
                post {
                    always {
                        junit(testResults: '**/test-results/test/TEST-*.xml', allowEmptyResults: true)
                    }
                }
            }

            stage('Sonar') {
                steps {
                    stageSonar()
                }
            }

            stage('Upload') {
                steps {
                    stageUpload()
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
