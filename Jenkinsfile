node {

   stage('Preparation') {
      git 'https://github.com/evandor/skysail-core.git'
   }
   
   /*stage('ng build') {
      sh 'cd /home/carsten/.hudson/jobs/skysail-core.pipeline/workspace/skysail.core/client && npm install'
      sh 'cd /home/carsten/.hudson/jobs/skysail-core.pipeline/workspace/skysail.core/client && ng build --prod'
      //sh '(cd skysail.core/client && ng build --prod)'
   }*/
   
   stage('gradle build') {
      //buildCode()
      sh './gradlew build'
   }

   stage('cucumber') {
	 //build 'skysail.cucumber'
	 //step([$class: 'CucumberReportPublisher', failedFeaturesNumber: 0, failedScenariosNumber: 0, failedStepsNumber: 0, fileExcludePattern: '', fileIncludePattern: '**/cucumber.json', jsonReportDirectory: '', parallelTesting: false, pendingStepsNumber: 0, skippedStepsNumber: 0, trendsLimit: 0, undefinedStepsNumber: 0])
   }   
   
   stage('coverage') {
      sh './gradlew reportScoverage'   
   }   
   
   /*stage('deploy') {
       parallel (
           server_int: { build 'skysail-core.export.int'}
       )
   }*/

   stage('buildJar') {
     sh 'sudo ./gradlew skysail.core:export.core.int'
   }

   stage('buildDockerImage') {
     sh 'sudo ./gradlew runnable buildImage'
   }

   stage('restartDockerContainer') {
     sh 'sudo ./skysail.core/deployment/scripts/stop_docker.sh'
     script{
       withEnv(['JENKINS_NODE_COOKIE =dontkill']) {
         sh "sudo ./skysail.core/deployment/scripts/run_docker.sh &"
       }
     }
   }

   /*stage('restartDockerContainer') {
     // docker run --name skysail-server -t --rm -p 9102:9102 evandor/skysail-server
     docker.image('evandor/skysail-server:latest').run('--name skysail-server -t --rm -p 9102:9102') {c ->

     }
   }*/

   stage('publishHTML') {
     publishHTML([
       allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, 
       reportDir: '', reportFiles: 'index.html', reportName: 'HTML Report'
     ])
     publishHTML([
       allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, 
       reportDir: 'skysail.core/generated/reports/scoverage', reportFiles: 'index.html', reportName: 'Scoverage Report'
     ])
   }
   
   /*stage('deployment.int') {
      parallel (
  	    demo:            { build 'ssp.demo.export.int' }
	    //pact_int:        { build 'ssp.pact.export.int' },
	    //pact_standalone: { build 'ssp.pact.export.standalone' }
	  )
   }*/

   /*stage('stresstest') {
     sh './gradlew skysail.product.demo.e2e.gatling:gatlingRun -DbaseUrl=http://192.168.100.3:8391/'
     gatlingArchive()
   }*/

   stage('document') {
      parallel (
	    //code:    { buildCode() },
		//doc:     { build 'skysail.doc' },
   	    scaladoc: { buildScaladoc() }
	  )
   }   
   
}

def buildCode() {
  sh './gradlew build'
}

def buildScaladoc() {
  sh './gradlew scaladoc'
  publishHTML([allowMissing: true, alwaysLinkToLastBuild: false, keepAll: false, reportDir: 'skysail.core/generated/docs/scaladoc', reportFiles: 'index.html', reportName: 'Scaladoc'])
}