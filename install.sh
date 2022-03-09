#!/bin/bash

docker run --rm -v "$(pwd)":/code -v "$(pwd)"/.m2:/root/.m2 -w /code shencangsheng/mvnd:0.7.1-jdk11 mvnd clean install
