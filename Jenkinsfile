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

        stage('Test Dynamique Junit and  Mock'){
            steps {
                sh "mvn clean test -Ptest";
            }
        }

        stage("Sonar") {
            steps {
                sh 'mvn sonar:sonar -Dsonar.login="admin" -Dsonar.password="vagrant"'
            }
        }

        stage("Build packacge") {
            steps {
                sh "mvn clean package -Pprod";
            }
        }

        stage("Build artifact") {
            steps {
                sh "sudo docker build -t tpachat .";
            }
        }
        
        stage('Deployment nexus') {
            steps {
                sh 'mvn deploy -Dmaven.test.skip=true -Djib.skipExistingImages'
            }
        }

        stage("docker compose up ") {
            steps {
                sh "sudo docker compose up -d";
            }
        }

        stage("docker compose down") {
            steps {
                sh "sudo docker down";
            }
        }   
    }
    post {
        always {
            cleanWs()
        }
    }




}