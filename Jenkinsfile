pipeline {
    agent any

    parameters {
        choice(name: 'ENV', choices: ['qa', 'staging'], description: 'Environment')
        choice(name: 'BROWSER', choices: ['chrome', 'firefox', 'edge'], description: 'Browser')
        choice(name: 'EXECUTION', choices: ['local', 'grid'], description: 'Execution mode')
        string(name: 'TAGS', defaultValue: '@smoke', description: 'Cucumber tags')
        booleanParam(name: 'HEADLESS', defaultValue: true, description: 'Headless mode')
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Start Grid') {
            when {
                expression { params.EXECUTION == 'grid' }
            }
            steps {
                sh 'docker compose up -d'
            }
        }

        stage('Run Tests') {
            steps {
                sh '''
                    mvn clean test                       -Denv=${ENV}                       -Dbrowser=${BROWSER}                       -Dexecution=${EXECUTION}                       -Dheadless=${HEADLESS}                       -Dcucumber.filter.tags="${TAGS}"
                '''
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'target/**/*,logs/**/*',
                             allowEmptyArchive: true
            script {
                if (params.EXECUTION == 'grid') {
                    sh 'docker compose down || true'
                }
            }
        }
    }
}
