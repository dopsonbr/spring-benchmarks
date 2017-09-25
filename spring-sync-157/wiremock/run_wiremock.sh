#!/usr/bin/env bash

set -x
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
PROJECT_DIR=$(cd $DIR/.. ;  pwd)

java -jar "${PROJECT_DIR}/wiremock/wiremock-standalone-2.8.0.jar" \
    --port 9000 \
    --https-port 9001 \
    --root-dir "${PROJECT_DIR}/wiremock"
