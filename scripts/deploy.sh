#!/usr/bin/env bash

mvn clean package

echo 'Send files to server'

scp -i ~/{key} \
        target/schedule-0.0.1-SNAPSHOT.jar \
        {user}@{ip/domain}:/{user_dir}/

echo 'Restart server'

ssh -i ~/{key} {user}@{ip/domain} << EOF

pgrep java | xargs kill -9
nohup java -jar schedule-0.0.1-SNAPSHOT.jar > log.txt &

EOF

echo 'deploy ended!'