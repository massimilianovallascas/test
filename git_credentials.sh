#!/bin/bash

gitCredentials=$(git config --list | grep "url.https://${GIT_TOKEN}@${GIT_PREFIX}.insteadOf=https://${GIT_PREFIX}" | wc -l)

if [[ "gitCredentials" == "0" ]]; then 
    git config --global url."https://${GIT_TOKEN}@${GIT_PREFIX}".insteadOf https://${GIT_PREFIX}
fi