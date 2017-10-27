/**
 * Pipeline Definition File
 * Information about the syntax can be found here: https://jenkins.io/doc/book/pipeline/syntax/
 *
 * To run this File as a Pipeline job atleast the following plugins are required:
 * https://wiki.jenkins-ci.org/display/JENKINS/Pipeline+Model+Definition+Plugin
 * https://wiki.jenkins-ci.org/display/JENKINS/Pipeline+Utility+Steps+Plugin
 */

def call(deployEnvironment, mainHost, backupHost) {

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

            stage('Deploy on 02-host') {
                steps {
                    sh "ssh -T kvbapp@odin-mp 'odin -H ${backupHost} target:mp_${userInput['project']},${deployEnvironment} deploy_container_app_restart:${userInput['version']}'"
                }
            }

            stage('Decide Continue Deployment') {
                steps {
                    script {
                        env.CONTINUE_DEPLOYMENT = input message: 'Continue with deployment on mitgliederportal01?',
                                parameters: [choice(name: 'Continue Deployment', choices: 'yes\nno', description: 'Choose "yes" to proceed.')]
                    }
                }
            }

            stage('Deploy on 01-host') {
                when {
                    expression { env.CONTINUE_DEPLOYMENT == 'yes' }
                }
                steps {
                    sh "ssh -T kvbapp@odin-mp 'odin -H ${mainHost} target:mp_${userInput['project']},${deployEnvironment} deploy_container_app_restart:${userInput['version']}'"
                }
            }
        }
    }
}
