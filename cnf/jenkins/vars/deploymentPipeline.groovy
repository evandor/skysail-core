/**
 * Pipeline Definition File
 * Information about the syntax can be found here: https://jenkins.io/doc/book/pipeline/syntax/
 *
 * To run this File as a Pipeline job atleast the following plugins are required:
 * https://wiki.jenkins-ci.org/display/JENKINS/Pipeline+Model+Definition+Plugin
 * https://wiki.jenkins-ci.org/display/JENKINS/Pipeline+Utility+Steps+Plugin
 */

def call(deployEnvironment) {

    def userInput
    pipeline {
        agent any

        options {
            buildDiscarder(logRotator(numToKeepStr: '10'))
        }

        stages {
            stage('Prepare') {
                steps {
                    script {
                        userInput = input(id: 'userInput', message: 'Was soll deployed werden?', parameters: [[$class: 'ChoiceParameterDefinition',
                                                                                                               choices: ['mp-approval','mp-dashboard', 'mp-eformulare', 'mp-inbox', 'mp-navbar', 'mp-profile', 'mp-search', 'mp-monitor', 'mp-upload'].join('\n'),
                                                                                                               description : 'Welche Applikation soll deployed werden?', name: 'project'],
                                [$class: 'TextParameterDefinition', defaultValue: 'x.x.x', description: 'Welche Version soll deployed werden?', name: 'version']
                        ])
                        currentBuild.displayName = "${userInput['project']} ${userInput['version']}"
                    }
                }
            }

            stage('Deploy') {
                steps {
                    sh "ssh -T kvbapp@odin-mp 'odin --set confirm=no target:mp_${userInput['project']},${deployEnvironment} deploy_container_app_restart:${userInput['version']}'"
                }
            }
        }
    }
}
