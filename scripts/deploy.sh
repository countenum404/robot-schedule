#!/usr/bin/env bash

mvn clean package

echo 'Send files to server'

scp -i ~/.server/vkr_key \
        target/schedule-0.0.1-SNAPSHOT.jar \
        shabashov@188.225.56.133:/home/shabashov/

echo 'Restart server'

ssh -i ~/.server/vkr_key shabashov@188.225.56.133 << EOF

pgrep java | xargs kill -9
nohup java -jar schedule-0.0.1-SNAPSHOT.jar > log.txt &

EOF

echo 'deploy ended!'