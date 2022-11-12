pipeline {

    agent { label 'validation' }


    stages {
        stage ('GIT') {
            steps {
            echo "Getting Project from Git"; 
                git branch: "farjallah", 
                    url: "https://github.com/oumazou/devops_project_2.git";
            }
        }
       
        stage("Build") {
            steps {
                sh "mvn clean package";
            }
        }

        stage('test'){
            steps {
                sh "mvn test -e"
            }
        }

        stage("Sonar") {
            steps {
                sh 'mvn sonar:sonar -Dsonar.login="admin" -Dsonar.password="vagrant"'
            }
        }
        
        stage("docker compose") {
            steps {
                sh "sudo docker compose up -d";
            }
        }

        stage('Deployment nexus') {
            steps {
                sh 'mvn deploy -Dmaven.test.skip=true'
            }
        }

        stage("Build artifact") {
            steps {
                sh "sudo docker build -t tpachat .";
            }
        }

        
    }
    post {
        always {
            cleanWs()
        }
    }




}