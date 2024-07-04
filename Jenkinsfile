pipeline {
    agent any
    tools {
        maven 'Maven 3.3.9'
        jdk 'jdk8'
    }
    triggers {
        githubPush()
    }
    stages {
        stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                '''
            }
        }
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Florence-ops/github_login_tests.git'
            }
        }
        stage('Build') {
            steps {
                script {
                    // Use Maven to build the project
                    echo $MAVEN_OPTS
                    sh 'mvn clean install'
                    sh 'mvn -B -DskipTests clean package' 
                }
            }
        }
        stage('Test') {
            steps {
                script {
                    // Use Maven to run tests
                    sh 'mvn test'
                }
            }
        }
        stage('Deploy') {
            steps {
                // Add your deploy steps here
                echo 'Deploying...'
            }
        }
    }
}

