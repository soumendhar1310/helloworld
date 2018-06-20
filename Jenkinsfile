podTemplate(label: 'mypod', containers: [
    containerTemplate(name: 'maven', image: 'maven:3.3.9-jdk-8-alpine', ttyEnabled: true, command: 'cat'),
    containerTemplate(name: 'kubectl', image: 'smesch/kubectl', ttyEnabled: true, command: 'cat'),
    containerTemplate(name: 'docker', image: 'docker', ttyEnabled: true, command: 'cat',
        envVars: [containerEnvVar(key: 'DOCKER_CONFIG', value: '/tmp/'),])],
        volumes: [/*secretVolume(secretName: 'docker-config', mountPath: '/tmp'),*/
                  hostPathVolume(hostPath: '/var/run/docker.sock', mountPath: '/var/run/docker.sock')
  ]) {
  node ('mypod') {
      
    def DOCKER_HUB_ACCOUNT = 'soumendhar1310'
    def DOCKER_IMAGE_NAME = 'helloworld'
    def K8S_DEPLOYMENT_NAME = 'helloworld'
      
    stage 'Get a Maven project'
    git url: 'https://github.com/soumendhar1310/helloworld.git', credentialsId: '0a0a9295-4198-4061-a165-1450ef931895', branch: 'master'
    container('maven') {
        
        
        //Has to bring it on top as Sonar needs compiled code. Once we do Junit and run Maven, then Complete maven build can be pushed later.
      	stage 'Build a Maven project'
          	sh 'mvn clean install'
      
        stage('QA') {
    
            withSonarQubeEnv('sonar') {
                sh 'mvn sonar:sonar -Dsonar.host.url=http://35.224.147.145:9000'
            }
        }
     
        stage("Quality Gate"){
            timeout(time: 1, unit: 'HOURS') {
                def qg = waitForQualityGate()
                if (qg.status != 'OK') {
                      error "Pipeline aborted due to quality gate failure: ${qg.status}"
                }
            }
        }
          
        //stage 'Build a Maven project'
          //  sh 'mvn clean install'
    }
 


    container('docker') {
                stage('Docker Build & Push Current & Latest Versions') {
                    sh ("docker build -t ${DOCKER_HUB_ACCOUNT}/${DOCKER_IMAGE_NAME}:${env.BUILD_NUMBER} .")
                    sh ("docker login -u soumendhar1310 -p gullu123")
                    sh ("docker push ${DOCKER_HUB_ACCOUNT}/${DOCKER_IMAGE_NAME}:${env.BUILD_NUMBER}")
                    sh ("docker tag ${DOCKER_HUB_ACCOUNT}/${DOCKER_IMAGE_NAME}:${env.BUILD_NUMBER} ${DOCKER_HUB_ACCOUNT}/${DOCKER_IMAGE_NAME}:latest")
                    sh ("docker push ${DOCKER_HUB_ACCOUNT}/${DOCKER_IMAGE_NAME}:latest")
                }
            }
            
            
    stage ('Deploy to Targeted Env')
    {
        timeout(time: 60, unit: 'SECONDS')
        {
            input 'Ready to go?'
        }
    }
            
    container('kubectl') {
                stage('Deploy New Build To Kubernetes') {
                    sh ("kubectl set image deployment/${K8S_DEPLOYMENT_NAME} ${K8S_DEPLOYMENT_NAME}=${DOCKER_HUB_ACCOUNT}/${DOCKER_IMAGE_NAME}:${env.BUILD_NUMBER}")
                }
            }
 
  }
}