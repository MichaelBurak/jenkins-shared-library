#!/usr/bin/env groovy


def call() {
    echo "Building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')])
            {
                sh "docker build -t michaelburak/priv-test:jma-2.0 ."
                sh "echo $PASS | docker login -u $USER --password-stdin"
                sh "docker push michaelburak/priv-test:jma-2.0"
            }
}
